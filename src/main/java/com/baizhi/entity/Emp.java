package com.baizhi.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 姚天下
 */
@Data
@Accessors(chain = true)
public class Emp  implements Serializable {
    private String id;
    private String name;
    private String path;
    private Double salary;
    private Integer age;

}
