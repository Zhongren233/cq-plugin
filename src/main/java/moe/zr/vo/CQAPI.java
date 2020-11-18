package moe.zr.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CQAPI {
     String action;
     Object params;
     String echo;
}
