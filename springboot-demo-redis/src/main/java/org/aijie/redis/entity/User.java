package org.aijie.redis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4437237512108260024L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
}