package com.lsw.hookinstrumentation;

import android.app.Instrumentation;
import android.os.Handler;

/**
 * Created by sweeneyliu on 2018/10/23.
 */
public class HookHelper {
    public static void hookInstrumentation() {
        //先获取当前的ActivityThread对象
        Object currentActivityThread = RefInvoke.getFieldObject("android.app.ActivityThread",null,"sCurrentActivityThread");
        //拿到原始的mInstrumentation字段
        Instrumentation mInstrumentation = (Instrumentation) RefInvoke.getFieldObject("android.app.ActivityThread",currentActivityThread,"mInstrumentation");
        //创建代理对象
        Instrumentation evilInstrumentation = new EvilInstrumentation(mInstrumentation);
        //偷梁换柱
        RefInvoke.setFieldObject("android.app.ActivityThread",currentActivityThread,"mInstrumentation",evilInstrumentation);
    }
}
