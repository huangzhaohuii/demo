package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @create 2020/3/27
 * @Description:
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String age;
}
