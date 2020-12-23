package moe.zr.handler;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import moe.zr.annotation.MessageContains;
import moe.zr.annotation.MessageStartWith;
import moe.zr.annotation.Probability;
import moe.zr.vo.in.Message;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 用这玩意的静态块去扫描所有此包中包含的 @{@link MessageContains} 和@{@link MessageStartWith}的方法
 * 然后把他们里面所有的方法存到对应的map里
 */
@Slf4j
public class MessageHandlerCenter {
    private static final HashMap<String, Method> CONTAINS_MAPPING = new HashMap<>();
    private static final HashMap<String, Method> START_WITH_MAPPING = new HashMap<>();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(2);

    static {
        Reflections reflections = new Reflections(
                MessageHandlerCenter.class.getPackage().getName(),
                new MethodAnnotationsScanner()
        );
        reflections.getMethodsAnnotatedWith(MessageContains.class).forEach(method ->
                CONTAINS_MAPPING.put(method.getAnnotation(MessageContains.class).value(), method)
        );
        reflections.getMethodsAnnotatedWith(MessageStartWith.class).forEach(method ->
                START_WITH_MAPPING.put(method.getAnnotation(MessageStartWith.class).value(), method)
        );

    }

    public static void onMessage(Message message) {
        if (message.getMessage() == null) return;
        log.info("群 {} 内 {}({}) 的消息:{}",
                message.getGroup_id(),
                message.getSender().getNickname(),
                message.getSender().getUser_id(),
                message.getMessage());
        Runnable task = () -> {
            Method method = searchMethod(message);
            if (method != null && probabilityTest(method)) {
                try {
                    method.invoke(Message.class.newInstance(), message);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        };
        EXECUTOR_SERVICE.execute(task);
    }

    private static Method searchMethod(Message message) {
        for (var entry : CONTAINS_MAPPING.entrySet()) {
            String key = entry.getKey();
            Method value = entry.getValue();
            if (message.getMessage().contains(key)) {
                return value;
            }
        }
        for (var entry : START_WITH_MAPPING.entrySet()) {
            String key = entry.getKey();
            Method value = entry.getValue();
            if (message.getMessage().startsWith(key)) {
                return value;
            }
        }
        return null;
    }

    private static boolean probabilityTest(Method method) {
        Probability annotation = method.getAnnotation(Probability.class);
        if (annotation == null)
            return true;
        boolean result;
        result = annotation.value() >= ThreadLocalRandom.current().nextInt(100);
        return result;
    }

}
