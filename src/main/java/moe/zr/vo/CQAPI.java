package moe.zr.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import moe.zr.vo.out.SendMessage;

@Data
@Accessors(chain = true)
public class CQAPI {
     String action;
     Object params;
     String echo;
}
