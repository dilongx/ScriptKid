package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class Rhino {
    private static final Rhino instance = new Rhino();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "function println(msg) { java.lang.System.out.println(msg) }",
                "println('')",
                "println('Hello rhino')"
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function run() { println('My is rhino the call interface example'); }");
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "var object = new Object()",
                "object.echo = function(msg) { println(msg); }"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is rhino the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function echo(msg) { println(msg); }");
        scriptUtil.invokeFunction("echo", "My is rhino the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
