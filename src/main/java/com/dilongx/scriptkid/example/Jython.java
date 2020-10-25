package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class Jython {
    private static final Jython instance = new Jython();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                scriptUtil.factory().getOutputStatement(""),
                scriptUtil.factory().getOutputStatement("Hello jython")
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("def run():  print('My is jython the call interface example')");
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "class Object:\n" +
                        "    @staticmethod\n" +
                        "    def echo(msg):\n" +
                        "        print(msg)\n" +
                        "\n" +
                        "\n" +
                        "object = Object()"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is jython the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("def echo(msg):  print(msg)");
        scriptUtil.invokeFunction("echo", "My is jython the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
