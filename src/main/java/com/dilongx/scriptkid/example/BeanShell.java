package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class BeanShell {
    private static final BeanShell instance = new BeanShell();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runScript(
                "println(msg) { System.out.println(msg); }",
                "println(\"\")",
                "println(\"Hello beanshell\")"
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runScript(
                "run() { println(\"My is beanshell the call interface example\"); }"
        );
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runScript(
                "Object() { echo(msg) { println(msg); } return this; }",
                "object = Object()"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is beanshell the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runScript("echo(msg) { println(msg); }");
        scriptUtil.invokeFunction("echo", "My is beanshell the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
