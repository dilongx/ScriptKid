package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class Nashorn {
    private static final Nashorn instance = new Nashorn();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                scriptUtil.factory().getOutputStatement(""),
                scriptUtil.factory().getOutputStatement("'Hello nashorn'")
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function run() { print('My is nashorn the call interface example'); }");
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "var object = new Object()",
                "object.echo = function(msg) { print(msg); }"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is nashorn the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function echo(msg) { print(msg); }");
        scriptUtil.invokeFunction("echo", "My is nashorn the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
