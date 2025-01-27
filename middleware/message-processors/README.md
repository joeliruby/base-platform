Yinxin-message-processor

1 下载 karaf 4.4.5
https://www.apache.org/dyn/closer.lua/karaf/4.4.5/apache-karaf-4.4.5.zip

![karafconf.png](.\karafconf.png)
2 Update {karaf 目录}\etc\user.properties

![userproperties.png](.\userproperties.png)
加入如下行：
karaf = karaf,_g_:admingroup
_g_\:admingroup = group,admin,manager,viewer,systembundles,ssh
用来打开 hawtio 默认管理员账号。账密默认是 karaf:karaf

3 在 bin 目录运行 .\bin\karaf.bat
启动 karaf,启动成功后 karaf console 是这个样子

![karafconsole.png](.\karafconsole.png)

4 Install karaf features
feature 是 karaf 自带的集成工具 ,包含各种通用协议的 endpoint 和其他工具例如 commons 工具 ,jaxb axis, Gson 等。我们的集成中间件必须要用到一下 features:

4.1 hawtio: one web 管理工具 ,用于管理 karaf 里面部署的所有 osgi bundle, 查看状态 ,启动停止 ,查看 Current 依赖满足情况等。

feature:repo-add hawtio
feature:install hawtio

4.2 用于动态 Configuration 集成路由规则 ,集成 endpoint , Message 格式处理逻辑等。
feature:repo-add camel

4.3 Install camel 组件例如 kafak mqtt, http. jms 等。全部内置于 karaf 不会消耗跟多的硬盘空间。
feature:install camel

4.4 camel-stream 用于 提供 karaf 命令用于查看 Current camel context routes 并且 Activate Deactivate 这些 artifacts.
feature:install camel-stream

4.5 支持在 sping context Configurationcamel 的元数据 xsd 定义
feature:install camel-blueprint

全部 Install 完 4 中的 feature 后在 karaf console 运行 list 可以看到如下已经 Activate 的 karaf featrues:

![karaflist.png](.\karaflist.png)

5 对于非标准的功能 ,例如 Reader Message 格式转换（成为 ,索立得）我建议用代码实现 Detail 请见 maven 项目 message-processors
里面只需要写简单的 java processor 处理 Message 格式处理逻辑。打成 jar 包后放置在 deploy 目录即可完成部署
![deply.png](.\deply.png)

7 上图中 test.xml 是 one camel-blueprint 的 spring xml Configurationxml Configuration File
路由转发 ,格式转换等规则可以动态改变动态生效

![springxml.png](.\springxml.png)
其中 testProcessor 是在 messageProcessor 中自定义的 java bean

8 常见的 karaf 命令：

list 查看 Current 部署的 osgi bundle 和状态
bundle:diag 123 查看 id 为 123 的 bundle 的依赖解析情况帮助查问题
log：clear 清空 Log
log:tai 持续查看 Latest Log
log:打印 Latest Log 不跟踪
start 1234:启动 id 为 1234 的 bundle
stop 1234:停止 id 为 1234 的 bundle
resolve 1234:为 id 为 1234 的 bundle 装载依赖
bundle:headers 1234 查看 id 为 1234 的 bundle 的 meta-info 头

9Hawtio 控制台看 camel 和 osgi bundle 部署情况
http://localhost:8181/hawtio
karaf:karaf

![osgi.png](.\osgi.png)

![qwatio.png](.\qwatio.png)
