package com.ysc.designpattern.builder;

/**
 * @author yuanshancheng
 * @date 2021/1/1
 */
public class StudentBuilder {
    private Student student = new Student();

    public StudentBuilder id(int id) {
        student.setId(id);
        return this;
    }

    public StudentBuilder name(String name) {
        student.setName(name);
        return this;
    }

    public Student build() {
        return student;
    }
}
