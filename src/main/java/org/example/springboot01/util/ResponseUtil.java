package org.example.springboot01.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//standard class for response
public class ResponseUtil {
    private int code ;
    private String msg;

    private Object data;
}
