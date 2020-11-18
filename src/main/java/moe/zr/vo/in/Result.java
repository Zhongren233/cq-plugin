package moe.zr.vo.in;

import lombok.Data;

@Data
public class Result {
    String status;
    Integer retcode;
    Object data;
    String echo;
}
