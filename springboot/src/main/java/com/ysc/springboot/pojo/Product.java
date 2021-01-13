package com.ysc.springboot.pojo;

import lombok.*;

/**
 * class description:
 *
 * @author yuanshancheng
 * @date 2020/12/31
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;

}