package com.code.txtinder;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;


/**
 * Created by dengshaomin on 2017/12/21.
 */

public class MineApplication extends TinkerApplication {


    public MineApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.code.txtinder.MineApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
