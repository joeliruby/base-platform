1 依赖JDK17 maven 3
1.启动
![start.png](.\start.png)
在开发环境启动直接运行Java程序base-platform/bizService/src/main/java/com/matariky/BizApplication
在测试环境启动打好包（在bizService目录下运行：maven clean install -DskipTests=true）后运行
java -jar -Dsping.profiles.active=test bizService-2.jar 其中“test”根据具体环境不同。
2.目录结构：
![folderstructure.png](.\folderstructure.png)
2.1. automation是代码自动生成项目可以根据mysql oracle的数据库生成数据库文档和服务端代码。服务端代码已经经过优化可以吞吐和前端协定好的非标准json数据格式。数据访问权限控制案例代码，分页列表，增删改查等功能。
2.2. userService
用户模块：封装租户，用户，组织机构，分组，应用，角色，功能权限，用户信息，菜单树，等数据的模块还包含用户登录，token生成，等用户访问控制的功能。
2.3. jobService
定时任务服务。独立于业务服务单独运行的定时任务服务。用于定义并触发定时任务，不和主业务系统抢占资源。单独运行jar在jobService目录下运行mvn clean
install -DskipTests -Pspringboot如果报common服务jar找不到，请确保事前把commonservice的jar打包到本地maven仓库。
2.4. orderService
订单服务，用于SAAS系统根据客户/租户付费情况给他们提供相应的服务。
2.5. bizService
根据各个不同的业务客户，客户/租户定制的业务功能和逻辑部署在业务服务里，这个服务是各个项目之间变化最大的部分。可以充分利用代码自动生成提高效率。
2.6. commonService
公共模块：封装字典，日志，数据共享，数据库备份，文件存取，和各种和RFID相关的业务场景下都会出现的通用业务功能：例如盘点，标签防伪，物品管理，设备管理，应用版本管理，标签信息，标签绑定等功能。此外公共模块还分装了各种数据源的配置类，例如mysql关系型业务数据库，clickhouse 分布式日志数据库，tdengine时序数据库，redis缓存数据库等等。和业务相关的功能都会依赖于公共服务。例如用户服务，定时任务，订单和业务服务。
2.7 middleware/message-processor是设备集成中间件下各种设备接入消息格式标准化的消息处理模块，是osgi模块可以在kara运行时热插拔。常见的消息格式转换可以写到一个jar包里，特殊依赖的或者加解密的消息格式转换建议单独做成processor.jar,否则写在一起日积月累这个jar会变得很大，依赖很多，启动条件越来越苛刻。不同processor的依赖都可以以不同的maven项目放在middleware/下。常见的没有特殊依赖的消息处理，可以直接创建类放到现有message-processor maven项目下。具体怎么实现热配置设备接入，参考middleware/message-processor下readme.md文档，和问杨程明。
3.customer-userprovider
IOT keycloak单点登录的插件，可以在各个租户/客户不开发数据库访问权限的情况下，向keycloak共享用户数据，进而实现每个客户的用户权限体系可以和iot的用户体系进行映射。同时也实现不同的租户/客户对于Iot来说实际处于不同的域下面。如果向现在在同一个域下，不同客户之间可以用token轻松地调用其他客户的数据。必须做域的隔离。