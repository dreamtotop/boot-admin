package org.top.thymeboot.system.vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class RedisVO implements Serializable {

    private String key;

    private String value;

}
