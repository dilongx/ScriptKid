package com.dilongx.scriptkid.utility;

import javax.script.*;
import java.io.FileReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * @author DilongX
 * @version 1.0.0
 * @date 2020/7/31
 * @description 脚本工具类，让ScriptEngine使用更方便
 */
public class ScriptUtil {
    public static final int NAME = 0;
    public static final int MIME_TYPE = 1;
    public static final int EXTENSION = 2;

    private static Engine engine;
    private static Context context;
    private static Factory factory;
    private static ScriptEngine scriptEngine;
    private static ScriptEngineManager engineManager;

    /**
     * @param tag 以该标签获取脚本引擎
     * @param key 脚本引擎关键字
     */
    public ScriptUtil(int tag, String key) {
        init();
        switch (tag) {
            case NAME:
                scriptEngine = engineManager.getEngineByName(key);
                break;
            case MIME_TYPE:
                scriptEngine = engineManager.getEngineByMimeType(key);
                break;
            case EXTENSION:
                scriptEngine = engineManager.getEngineByExtension(key);
                break;
            default:
                throw new IllegalArgumentException("This tag is does not exist!");
        }
        // 实例化
        if (engine == null) {
            engine = new Engine();
        }
        if (context == null) {
            context = new Context();
        }
        if (factory == null) {
            factory = new Factory();
        }
    }

    /**
     * @param type    以该类型注册脚本引擎
     * @param key     脚本引擎关键字
     * @param factory 脚本引擎工厂实例
     */
    public static void registerEngine(int type, String key, ScriptEngineFactory factory) {
        init();
        switch (type) {
            case NAME:
                engineManager.registerEngineName(key, factory);
                break;
            case MIME_TYPE:
                engineManager.registerEngineMimeType(key, factory);
                break;
            case EXTENSION:
                engineManager.registerEngineExtension(key, factory);
                break;
            default:
                throw new IllegalArgumentException("This type is does not exist!");
        }
    }

    /**
     * @description 初始化脚本引擎管理器
     */
    private static void init() {
        if (engineManager == null) {
            engineManager = new ScriptEngineManager();
            listEngine();
        }
    }

    /**
     * 列出可用脚本引擎
     */
    public static void listEngine() {
        List<ScriptEngineFactory> factories = getEngineFactories();
        if (factories.size() == 0) {
            print("The JDK does not have any scripting engine!");
        } else {
            for (ScriptEngineFactory factory : factories) {
                print(
                        "Names: " + factory.getNames(),
                        "MineTypes: " + factory.getMimeTypes(),
                        "Extensions: " + factory.getExtensions(),
                        "EngineName: " + factory.getEngineName(),
                        "LanguageName: " + factory.getLanguageName(),
                        "EngineVersion: " + factory.getEngineVersion(),
                        "LanguageVersion: " + factory.getLanguageVersion(),
                        "\n"
                );
            }
        }
    }

    /**
     * @return 脚本引擎管理器
     */
    public ScriptEngineManager getEngineManager() {
        return engineManager;
    }

    /**
     * @return 引擎清单
     */
    public static List<ScriptEngineFactory> getEngineFactories() {
        init();
        return engineManager.getEngineFactories();
    }

    /**
     * @return 脚本引擎内部类实例
     */
    public Engine engine() {
        return engine;
    }

    /**
     * @return 脚本上下文内部类实例
     */
    public Context context() {
        return context;
    }

    /**
     * @return 脚本工厂内部类实例
     */
    public Factory factory() {
        return factory;
    }

    public static class Engine implements ScriptEngine, Compilable, Invocable {
        @Override
        public CompiledScript compile(String script) throws ScriptException {
            if (scriptEngine instanceof Compilable) {
                Compilable compiler = (Compilable) scriptEngine;
                return compiler.compile(script);
            } else {
                print("Compiler is not available!");
            }
            return null;
        }

        @Override
        public CompiledScript compile(Reader script) throws ScriptException {
            if (scriptEngine instanceof Compilable) {
                Compilable compiler = (Compilable) scriptEngine;
                return compiler.compile(script);
            } else {
                print("Compiler is not available!");
            }
            return null;
        }

        @Override
        public Object invokeMethod(Object thiz, String name, Object... args) throws ScriptException, NoSuchMethodException {
            if (scriptEngine instanceof Invocable) {
                Invocable invoker = (Invocable) scriptEngine;
                return invoker.invokeMethod(thiz, name, args);
            } else {
                print("Invoker is not available!");
            }
            return null;
        }

        @Override
        public Object invokeFunction(String name, Object... args) throws ScriptException, NoSuchMethodException {
            if (scriptEngine instanceof Invocable) {
                Invocable invoker = (Invocable) scriptEngine;
                return invoker.invokeFunction(name, args);
            } else {
                print("Invoker is not available!");
            }
            return null;
        }

        @Override
        public <T> T getInterface(Class<T> clazz) {
            if (scriptEngine instanceof Invocable) {
                Invocable invoker = (Invocable) scriptEngine;
                return invoker.getInterface(clazz);
            } else {
                print("Invoker is not available!");
            }
            return null;
        }

        @Override
        public <T> T getInterface(Object thiz, Class<T> clazz) {
            if (scriptEngine instanceof Invocable) {
                Invocable invoker = (Invocable) scriptEngine;
                return invoker.getInterface(thiz, clazz);
            } else {
                print("Invoker is not available!");
            }
            return null;
        }

        @Override
        public Object eval(String script, ScriptContext context) throws ScriptException {
            return scriptEngine.eval(script, context);
        }

        @Override
        public Object eval(Reader reader, ScriptContext context) throws ScriptException {
            return scriptEngine.eval(reader, context);
        }

        @Override
        public Object eval(String script) throws ScriptException {
            return scriptEngine.eval(script);
        }

        @Override
        public Object eval(Reader reader) throws ScriptException {
            return scriptEngine.eval(reader);
        }

        @Override
        public Object eval(String script, Bindings n) throws ScriptException {
            return scriptEngine.eval(script, n);
        }

        @Override
        public Object eval(Reader reader, Bindings n) throws ScriptException {
            return scriptEngine.eval(reader, n);
        }

        @Override
        public void put(String key, Object value) {
            scriptEngine.put(key, value);
        }

        @Override
        public Object get(String key) {
            return scriptEngine.get(key);
        }

        @Override
        public Bindings getBindings(int scope) {
            return scriptEngine.getBindings(scope);
        }

        @Override
        public void setBindings(Bindings bindings, int scope) {
            scriptEngine.setBindings(bindings, scope);
        }

        @Override
        public Bindings createBindings() {
            return scriptEngine.createBindings();
        }

        @Override
        public ScriptContext getContext() {
            return scriptEngine.getContext();
        }

        @Override
        public void setContext(ScriptContext context) {
            scriptEngine.setContext(context);
        }

        @Override
        public ScriptEngineFactory getFactory() {
            return scriptEngine.getFactory();
        }
    }

    public static class Factory implements ScriptEngineFactory {
        /**
         * @return 当前引擎名称
         */
        @Override
        public String getEngineName() {
            return scriptEngine.getFactory().getEngineName();
        }

        /**
         * @return 当前引擎版本
         */
        @Override
        public String getEngineVersion() {
            return scriptEngine.getFactory().getEngineVersion();
        }

        /**
         * @return 当前引擎扩展列表
         */
        @Override
        public List<String> getExtensions() {
            return scriptEngine.getFactory().getExtensions();
        }

        /**
         * @return 当前引擎Mime类型列表
         */
        @Override
        public List<String> getMimeTypes() {
            return scriptEngine.getFactory().getMimeTypes();
        }

        /**
         * @return 当前引擎名称列表
         */
        @Override
        public List<String> getNames() {
            return scriptEngine.getFactory().getNames();
        }

        /**
         * @return 当前引擎语言名称
         */
        @Override
        public String getLanguageName() {
            return scriptEngine.getFactory().getLanguageName();
        }

        /**
         * @return 当前引擎语言版本
         */
        @Override
        public String getLanguageVersion() {
            return scriptEngine.getFactory().getLanguageVersion();
        }

        /**
         * @param key 引擎常量 ScriptEngine.ENGINE...
         * @return 当前引擎常量对应值
         */
        @Override
        public Object getParameter(String key) {
            return scriptEngine.getFactory().getParameter(key);
        }

        /**
         * @param obj  对象
         * @param m    方法名
         * @param args 参数（可多参数）
         * @return 当前引擎方法调用语法
         */
        @Override
        public String getMethodCallSyntax(String obj, String m, String... args) {
            return scriptEngine.getFactory().getMethodCallSyntax(obj, m, args);
        }

        /**
         * @param toDisplay 要输出的字符串
         * @return 输出语句脚本程序
         */
        @Override
        public String getOutputStatement(String toDisplay) {
            return scriptEngine.getFactory().getOutputStatement(toDisplay);
        }

        /**
         * @param statements 代码（可多参数）
         * @return 当前引擎脚本代码
         */
        @Override
        public String getProgram(String... statements) {
            return scriptEngine.getFactory().getProgram(statements);
        }

        /**
         * @return 当前引擎
         */
        @Override
        public ScriptEngine getScriptEngine() {
            return scriptEngine;
        }
    }

    public static class Context implements ScriptContext {
        @Override
        public void setBindings(Bindings bindings, int scope) {
            scriptEngine.getContext().setBindings(bindings, scope);
        }

        @Override
        public Bindings getBindings(int scope) {
            return scriptEngine.getContext().getBindings(scope);
        }

        @Override
        public void setAttribute(String name, Object value, int scope) {
            scriptEngine.getContext().setAttribute(name, value, scope);
        }

        @Override
        public Object getAttribute(String name, int scope) {
            return scriptEngine.getContext().getAttribute(name, scope);
        }

        @Override
        public Object removeAttribute(String name, int scope) {
            return scriptEngine.getContext().removeAttribute(name, scope);
        }

        @Override
        public Object getAttribute(String name) {
            return scriptEngine.getContext().getAttribute(name);
        }

        @Override
        public int getAttributesScope(String name) {
            return scriptEngine.getContext().getAttributesScope(name);
        }

        @Override
        public Writer getWriter() {
            return scriptEngine.getContext().getWriter();
        }

        @Override
        public Writer getErrorWriter() {
            return scriptEngine.getContext().getErrorWriter();
        }

        @Override
        public void setWriter(Writer writer) {
            scriptEngine.getContext().setWriter(writer);
        }

        @Override
        public void setErrorWriter(Writer writer) {
            scriptEngine.getContext().setErrorWriter(writer);
        }

        @Override
        public Reader getReader() {
            return scriptEngine.getContext().getReader();
        }

        @Override
        public void setReader(Reader reader) {
            scriptEngine.getContext().setReader(reader);
        }

        @Override
        public List<Integer> getScopes() {
            return scriptEngine.getContext().getScopes();
        }
    }

    /**
     * @param statements 要运行的脚本代码（可多参数）
     * @return 返回值对象
     */
    public Object runScript(String... statements) {
        try {
            String script = factory().getProgram(statements);
            return engine().eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * @param reader 要运行的脚本文件读取器
     * @return 返回值对象
     */
    public Object runScript(FileReader reader) {
        try {
            return engine().eval(reader);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param statements 要编译运行的脚本代码（可多参数）
     * @return 返回值对象
     */
    public Object runCompile(String... statements) {
        try {
            String script = factory().getProgram(statements);
            return engine().compile(script).eval();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param reader 要编译运行的脚本文件读取器
     * @return 返回值对象
     */
    public Object runCompile(FileReader reader) {
        try {
            return engine().compile(reader).eval();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param name 被调用函数名
     * @param args 要传递给被调用函数的参数
     * @return 被调用函数返回的对象
     */
    public Object invokeFunction(String name, Object... args) {
        try {
            return engine().invokeFunction(name, args);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param thiz 被调用方法所在对象
     * @param name 被调用方法名
     * @param args 要传递给被调用方法的参数
     * @return 被调用方法返回的对象
     */
    public Object invokeMethod(Object thiz, String name, Object... args) {
        try {
            return engine().invokeMethod(thiz, name, args);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
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
