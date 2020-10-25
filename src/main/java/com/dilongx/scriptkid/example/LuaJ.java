package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class LuaJ {
    private static final LuaJ instance = new LuaJ();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                scriptUtil.factory().getOutputStatement(""),
                scriptUtil.factory().getOutputStatement("'Hello luaj'")
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function run() print('My is luaj the call interface example') end");
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "local object = {}",
                "object.echo = function(msg) print(msg); end"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is luaj the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("function echo(msg) println(msg); end");
        scriptUtil.invokeFunction("echo", "My is luaj the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
