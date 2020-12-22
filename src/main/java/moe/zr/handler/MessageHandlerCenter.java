package moe.zr.handler;

import lombok.extern.slf4j.Slf4j;
import moe.zr.annotation.MessageContains;
import moe.zr.annotation.MessageStartWith;
import moe.zr.vo.in.Message;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用这玩意的静态块去扫描所有此包中包含的 @{@link MessageContains} 和@{@link MessageStartWith}的方法
 * 然后把他们里面所有的方法存到对应的map里
 */
@Slf4j
public class MessageHandlerCenter {
    private static final HashMap<String, Method> containsMapping = new HashMap<>();
    private static final HashMap<String, Method> startWithMapping = new HashMap<>();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    static {
        Reflections reflections = new Reflections(
                MessageHandlerCenter.class.getPackage().getName(),
                new MethodAnnotationsScanner()
        );
        reflections.getMethodsAnnotatedWith(MessageContains.class).forEach(method -> containsMapping.put(method.getAnnotation(MessageContains.class).value(), method)
        );
        reflections.getMethodsAnnotatedWith(MessageStartWith.class).forEach(method ->
                startWithMapping.put(method.getAnnotation(MessageStartWith.class).value(), method)
        );

    }

    public static void onMessage(Message message) {
        if (message.getMessage() == null) return;
        log.info("群 {} 内 {}({}) 的消息:{}",
                message.getGroup_id(),
                message.getSender().getNickname(),
                message.getSender().getUser_id(),
                message.getMessage());
        EXECUTOR_SERVICE.execute(() -> searchMethodAndInvoke(message));
    }

    private static void searchMethodAndInvoke(Message message) {
        containsMapping.forEach((k, v) -> {
            if (message.getMessage().contains(k)) {
                try {
                    v.invoke(Message.class.newInstance(), message);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
        startWithMapping.forEach((k, v) -> {
            if (message.getMessage().startsWith(k)) {
                try {
                    v.invoke(Message.class.newInstance(), message);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
