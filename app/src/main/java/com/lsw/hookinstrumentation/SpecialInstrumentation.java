package com.lsw.hookinstrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by sweeneyliu on 2018/10/23.
 */
public class SpecialInstrumentation extends Instrumentation{

    private static final String TAG = "SpecialInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public SpecialInstrumentation(Instrumentation base) {
        mBase = base;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options){
        Log.d(TAG, "hook Instrumentation");

        // 开始调用原始的方法, 调不调用随你,但是不调用的话, 所有的startActivity都失效了.
        // 由于这个方法是隐藏的,因此需要使用反射调用;首先找到这个方法
        Class[] p1 = {Context.class, IBinder.class,
                IBinder.class, Activity.class,
                Intent.class, int.class, Bundle.class};
        Object[] v1 = {who, contextThread, token, target,
                intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(
                mBase, "execStartActivity", p1, v1);

    }


}
