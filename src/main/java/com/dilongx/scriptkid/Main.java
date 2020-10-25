package com.dilongx.scriptkid;

import com.dilongx.scriptkid.example.*;
import com.dilongx.scriptkid.utility.ScriptUtil;

/**
 * @author DilongX
 * @version 1.0.0
 * @date 2020/7/28
 * @description 主类
 */
public class Main {
    public static void main(String[] args) {
        // Nashorn 完整的ScriptEngine实现, 效率良好
        ScriptUtil nashorn = new ScriptUtil(ScriptUtil.NAME, "nashorn");
        long nashorn_start = System.currentTimeMillis();
        Nashorn.run(nashorn);
        print("Finish in " + (System.currentTimeMillis() - nashorn_start) + "ms");

        // LuaJ 残缺的ScriptEngine实现(Invocable未实现), 效率良好
        ScriptUtil luaj = new ScriptUtil(ScriptUtil.NAME, "luaj");
        long luaj_start = System.currentTimeMillis();
        LuaJ.run(luaj);
        print("Finish in " + (System.currentTimeMillis() - luaj_start) + "ms");

        //Rhino 完整的ScriptEngine实现, 效率良好
        ScriptUtil rhino = new ScriptUtil(ScriptUtil.NAME, "rhino");
        long rhino_start = System.currentTimeMillis();
        Rhino.run(rhino);
        print("Finish in " + (System.currentTimeMillis() - rhino_start) + "ms");

        // Groovy 完整的ScriptEngine实现, 效率一般
        ScriptUtil groovy = new ScriptUtil(ScriptUtil.NAME, "groovy");
        long groovy_start = System.currentTimeMillis();
        Groovy.run(groovy);
        print("Finish in " + (System.currentTimeMillis() - groovy_start) + "ms");

        // JRuby 完整的ScriptEngine实现, 效率较差
        ScriptUtil jruby = new ScriptUtil(ScriptUtil.NAME, "jruby");
        long jruby_start = System.currentTimeMillis();
        JRuby.run(jruby);
        print("Finish in " + (System.currentTimeMillis() - jruby_start) + "ms");

        // Jython 完整的ScriptEngine实现, 效率良好
        ScriptUtil jython = new ScriptUtil(ScriptUtil.NAME, "jython");
        long jython_start = System.currentTimeMillis();
        Jython.run(jython);
        print("Finish in " + (System.currentTimeMillis() - jython_start) + "ms");

        // BeanShell 残缺的ScriptEngine实现(Compilable未实现), 效率良好
        ScriptUtil beanshell = new ScriptUtil(ScriptUtil.NAME, "beanshell");
        long beanshell_start = System.currentTimeMillis();
        BeanShell.run(beanshell);
        print("Finish in " + (System.currentTimeMillis() - beanshell_start) + "ms");
    }

    /**
     * @param msg 要打印的内容（可多参数）
     * @param <M> 任意类型
     * @description 全类型通用输出打印方法
     */
    @SafeVarargs
    private static <M> void print(M... msg) {
        for (M m : msg) {
            System.out.println(m);
        }
    }

}
