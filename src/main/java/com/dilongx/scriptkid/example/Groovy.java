package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class Groovy {
    private static final Groovy instance = new Groovy();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                scriptUtil.factory().getOutputStatement(""),
                scriptUtil.factory().getOutputStatement("Hello groovy")
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("runnable = { println(\"My is groovy the call interface example\") }");
        Runnable runnable = (Runnable) scriptUtil.engine().get("runnable"); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "object = System.out"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "println", "My is groovy the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("def echo(msg) { println(msg); }");
        scriptUtil.invokeFunction("echo", "My is groovy the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
