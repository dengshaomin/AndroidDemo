package com.nothing.dagger2;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by dengshaomin on 2017/9/28.
 */

@Component
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
