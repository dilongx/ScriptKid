# ScriptKid

> **项目地址：** http://github.com/dilongx/ScriptKid

**用ScriptEngineManager以相同的方式来调用各种各样的ScriptEngine**






# 0x1 那些Java写的脚本引擎

|  引擎      |  语言      |                           官网                             |                           开源地址                           |
| :-------: | :--------:| :----------------------------------------------------------: | :----------------------------------------------------------: |
|  LuaJ     |  Lua      | [https://www.luaj.org](http://www.luaj.org/luaj/3.0/README.html) | [https://github.com/luaj/luaj](https://github.com/luaj/luaj) |
|  JEXL     |  DSL      | [https://commons.apache.org/proper/commons-jexl](https://commons.apache.org/proper/commons-jexl) | [https://github.com/apache/commons-jexl](https://github.com/apache/commons-jexl) |
|  JRuby    |    Ruby   |        [https://www.jruby.org](https://www.jruby.org)        | [https://github.com/jruby/jruby](https://github.com/jruby/jruby) |
|  Jython   |   Python  |       [https://www.jython.org](https://www.jython.org)       | [https://github.com/jython/jython](https://github.com/jython/jython) |
|  Groovy   |   Groovy  |  [http://www.groovy-lang.org/](http://www.groovy-lang.org)   | [https://github.com/apache/groovy](https://github.com/apache/groovy) |
|  ~~Kawa~~ | ~~Scheme~~| [~~http://www.gnu.org/software/kawa~~ ](http://www.gnu.org/software/kawa) | [~~ftp://ftp.gnu.org/pub/gnu/kawa~~ ](ftp://ftp.gnu.org/pub/gnu/kawa) |
|  Rhino    | JavaScript| [https://developer.mozilla.org/zh-CN/docs/Mozilla/Projects/Rhino](https://developer.mozilla.org/zh-CN/docs/Mozilla/Projects/Rhino) | [https://github.com/mozilla/rhino](https://github.com/mozilla/rhino) |
|  BeanShell| BeanShell |     [http://www.beanshell.org](http://www.beanshell.org)     | [https://github.com/beanshell/beanshell](https://github.com/beanshell/beanshell) |
> **注：Kawa没有实现JSR-223标准故划掉**






# 0x2 使用各脚本引擎前的准备工作

**导入Gradle项目依赖**

```java
// Script engine
def rhino_version = "1.7.13"
implementation 'org.luaj:luaj-jse:3.0.1' // Lua的Java实现
implementation "org.mozilla:rhino:${rhino_version}" // JavaScript的Java实现
implementation "org.mozilla:rhino-runtime:${rhino_version}" // Rhino运行时
implementation "org.mozilla:rhino-engine:${rhino_version}" // Rhino脚本引擎
implementation 'org.jruby:jruby-complete:9.2.13.0' // Ruby的Java实现
implementation 'org.python:jython-standalone:2.7.2' // Python的Java实现
implementation 'org.codehaus.groovy:groovy-all:3.0.6' // Groovy脚本语言
// implementation 'org.apache.commons:commons-jexl3:3.1' // Java表达式语言(DSL)
implementation 'org.apache-extras.beanshell:bsh:2.0b6' // BeanShell脚本语言

// implementation "com.sun.phobos:jsr223-api:1.0" // Android项目中使用
// Android项目中除LuaJ与Rhino外皆疑似依赖冲突无法通过编译
```



## 0x2.1 兼容JSR-223标准的大致步骤

> 除了Kawa其他引擎都自带

1. **继承抽象脚本引擎类**
> 新建XXXEngine类继承AbstractScriptEngine类，

2. **实现脚本引擎工厂接口**
> 新建XXXScriptEngineFactory类实现ScriptEngineFactory接口，

3. **继承简单脚本上下文类并实现脚本上下文接口**
> 新建XXXContext类继承SimpleScriptContext实现ScriptContext接口，

4. **配置META-INF/services/javax.script.ScriptEngineManager**

> 项目resources目录中新建META-INF/services/javax.script.ScriptEngineFactory文本文件，并写入Factory接口实现类的全限定名。





# 0x3 使用脚本引擎管理器来运行脚本和与Java交互

> **javax.script.ScriptEngineManager，可以用来统一管理Java写的脚本引擎**
>
> **虽然自带的Oracle Nashorn引擎和Rhino引擎都是JavaScript，但它们的标准是不一样的，使用起来也有些许差异。**

**运行脚本代码**

```java
ScriptEngineManager manager = new ScriptEngineManager();
ScriptEngine engine = manager.getEnginebyName([jexl|luaj|nashorn|rhino|groovy|jruby|jython|beanshell]);

try {
    engine.eval(code);
} catch (ScriptException e) {
    e.printStackTrace();
}
```

***不想写了看源码去吧！***