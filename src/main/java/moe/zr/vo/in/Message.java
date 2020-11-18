package moe.zr.vo.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String anonymous;
    private String font;
    private Long group_id;
    private String message;
    private Long message_id;
    private String message_type;
    private String post_type;
    private String raw_message;
    private Long self_id;
    private Sender sender;
    private String sub_type;
    private String time;
    private Long user_id;
}
