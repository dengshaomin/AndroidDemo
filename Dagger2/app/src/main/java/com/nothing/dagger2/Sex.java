package com.nothing.dagger2;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by dengshaomin on 2017/9/28.
 */
public class Sex {
    @Inject
    public Sex() {
    }

    private boolean male;

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
}
