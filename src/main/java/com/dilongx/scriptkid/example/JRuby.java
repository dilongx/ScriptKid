package com.dilongx.scriptkid.example;

import com.dilongx.scriptkid.utility.ScriptUtil;

public class JRuby {
    private static final JRuby instance = new JRuby();

    private void hello(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "puts('')",
                "puts('Hello jruby')"
        );
    }

    private void getInterface(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("def run() puts('My is jruby the call interface example') end");
        Runnable runnable = scriptUtil.engine().getInterface(Runnable.class); // 获取接口
        new Thread(runnable).start(); // 调用接口
    }

    private void invokeMethod(ScriptUtil scriptUtil) {
        scriptUtil.runCompile(
                "class Object\n" +
                        "  def echo(msg)\n" +
                        "    puts(msg)\n" +
                        "  end\n" +
                        "end\n" +
                        "\n" +
                        "object = Object"
        );
        scriptUtil.invokeMethod(scriptUtil.engine().get("object"), "echo", "My is jruby the invoke method example"); // 调用方法
    }

    private void invokeFunction(ScriptUtil scriptUtil) {
        scriptUtil.runCompile("def echo(msg) puts(msg) end");
        scriptUtil.invokeFunction("echo", "My is jruby the invoke function example"); // 调用函数
    }

    public static void run(ScriptUtil scriptUtil) {
        instance.hello(scriptUtil);
        instance.getInterface(scriptUtil);
        instance.invokeMethod(scriptUtil);
        instance.invokeFunction(scriptUtil);
    }
}
