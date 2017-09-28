package com.nothing.dagger2;

import javax.inject.Inject;

/**
 * Created by dengshaomin on 2017/9/28.
 */

public class User {

    @Inject
    public User() {
    }

    private String name;
    private Sex sex;

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
