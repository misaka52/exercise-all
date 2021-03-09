package com.ysc.exercise.service;

import lombok.AllArgsConstructor;

/**
 * @author yuanshancheng
 * @date 2021/1/24
 */
@AllArgsConstructor
public class WrapService {
    private String prefix;
    private String suffix;

    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
