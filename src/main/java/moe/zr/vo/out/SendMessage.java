package moe.zr.vo.out;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import moe.zr.vo.in.Message;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class SendMessage {
    private String message_type;
    private Long user_id;
    private Long group_id;
    private String message;
    private Boolean auto_escape = false;

    public SendMessage(Message m){
        message_type = m.getMessage_type();
        if ("private".equals(message_type)) {
            user_id = m.getUser_id();
        }else {
            group_id = m.getGroup_id();
        }
        message = m.getMessage();
    }
}
