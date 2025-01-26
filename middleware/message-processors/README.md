Yinxin-message-processor


1下载 karaf 4.4.5
https://www.apache.org/dyn/closer.lua/karaf/4.4.5/apache-karaf-4.4.5.zip



![karafconf.png](.\karafconf.png)
2编辑 {karaf目录}\etc\user.properties


![userproperties.png](.\userproperties.png)
加入如下行：
karaf = karaf,_g_:admingroup
_g_\:admingroup = group,admin,manager,viewer,systembundles,ssh
用来打开hawtio默认管理员账号。账密默认是karaf:karaf

3在bin目录运行 .\bin\karaf.bat
启动karaf,启动成功后 karaf console是这个样子


![karafconsole.png](.\karafconsole.png)

4 安装karaf features
feature是karaf自带的集成工具，包含各种通用协议的endpoint和其他工具例如commons 工具，jaxb axis, Gson等。我们的集成中间件必须要用到一下features:

4.1 hawtio: 一个web管理工具，用于管理karaf里面部署的所有osgi bundle, 查看状态，启动停止，查看当前依赖满足情况等。

feature:repo-add hawtio
feature:install hawtio

4.2 用于动态配置集成路由规则，集成endpoint，消息格式处理逻辑等。
feature:repo-add camel

4.3安装camel组件例如kafak mqtt, http. jms等。全部内置于karaf不会消耗跟多的硬盘空间。
feature:install camel

4.4 camel-stream用于 提供karaf命令用于查看当前camel context routes并且启用停用这些artifacts.
feature:install camel-stream

4.5 支持在sping context配置camel的元数据xsd定义
feature:install camel-blueprint

全部安装完4中的feature后在karaf console运行 list可以看到如下已经启用的karaf featrues:

![karaflist.png](.\karaflist.png)

5对于非标准的功能，例如读写器消息格式转换（成为，索立得）我建议用代码实现详情请见maven项目message-processors
里面只需要写简单的java processor处理消息格式处理逻辑。打成jar 包后放置在deploy目录即可完成部署
![deply.png](.\deply.png)

7上图中test.xml是一个camel-blueprint的spring xml配置xml配置文件
路由转发，格式转换等规则可以动态改变动态生效

![springxml.png](.\springxml.png)
其中testProcessor是在messageProcessor中自定义的java bean

8常见的karaf 命令：

list 查看当前部署的osgi bundle和状态
bundle:diag 123查看id为123的bundle的依赖解析情况帮助查问题
log：clear 清空日志
log:tai持续查看最新日志
log:打印最新日志不跟踪
start 1234:启动id为1234的bundle
stop 1234:停止id为1234的bundle
resolve 1234:为id为1234的bundle装载依赖
bundle:headers 1234查看id为1234的bundle 的meta-info 头

9Hawtio 控制台看camel和osgi bundle部署情况
http://localhost:8181/hawtio
karaf:karaf

![osgi.png](.\osgi.png)

![qwatio.png](.\qwatio.png)

