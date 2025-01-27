1 依赖 JDK17 maven 3 1.启动
![start.png](.\start.png)
在开发环境启动直接运行 Java 程序 base-platform/bizService/src/main/java/com/matariky/BizApplication
在测试环境启动打好包（在 bizService 目录下运行：maven clean install -DskipTests=true）后运行
java -jar -Dsping.profiles.active=test bizService-2.jar 其中“test”根据具体环境不同。 2.目录结构：
![folderstructure.png](.\folderstructure.png)
2.1. automation 是代码 Automatic 生成项目可以根据 mysql oracle 的数据库生成数据库文档和 Service 端代码。 Service 端代码已经经过优化可以吞吐和前端协定好的非标准 json 数据格式。数据访问权限控制案例代码 , Pagination ,增删改查等功能。
2.2. userService
User 模块：封装租户 , User ,组织机构 ,分组 , App ,角色 ,功能权限 , User 信息 ,菜单树 ,等数据的模块还包含 User Login ,token 生成 ,等 User 访问控制的功能。
2.3. jobService
Scheduled 任务 Service 。独立于 Business Service 单独运行的 Scheduled 任务 Service 。用于定义并触发 Scheduled 任务 ,不和主 Business System 抢占资源。单独运行 jar 在 jobService 目录下运行 mvn clean
install -DskipTests -Pspringboot 如果报 common Service jar 找不到 ,请确保事前把 commonservice 的 jar 打包到 Local maven 仓库。
2.4. orderService
订单 Service ,用于 SAAS System 根据客户/租户付费情况给他们提供相应的 Service 。
2.5. bizService
根据各个不同的 Business 客户 ,客户/租户定制的 Business 功能和逻辑部署在 Business Service 里 ,这个 Service 是各个项目之间变化最大的部分。可以充分利用代码 Automatic 生成提高效率。
2.6. commonService
公共模块：封装 Dictionary , Log ,数据共享 ,数据库备份 ,文件存取 ,和各种和 RFID 相关的 Business 场景下都会出现的通用 Business 功能：例如盘点 ,标签防伪 ,物品管理 ,设备管理 , App Version 管理 ,标签信息 ,标签 Binding 等功能。此外公共模块还分装了各种数据源的 Configuration 类 ,例如 mysql 关系型 Business 数据库 ,clickhouse 分布式 Log 数据库 ,tdengine 时序数据库 ,redis 缓存数据库等等。和 Business 相关的功能都会依赖于公共 Service 。例如 User Service , Scheduled 任务 ,订单和 Business Service 。
2.7 middleware/message-processor 是设备集成中间件下各种设备接入 Message 格式标准化的 Message 处理模块 ,是 osgi 模块可以在 kara 运行时热插拔。常见的 Message 格式转换可以写到 one jar 包里 , Speicial 依赖的或者加解密的 Message 格式转换建议单独做成 processor.jar,否则写在一起日积月累这个 jar 会变得很大 ,依赖很多 ,启动条件越来越苛刻。不同 processor 的依赖都可以以不同的 maven 项目放在 middleware/下。常见的没有 Speicial 依赖的 Message 处理 ,可以直接 Create 类放到现有 message-processor maven 项目下。具体怎么实现热 Configuration 设备接入 ,参考 middleware/message-processor 下 readme.md 文档 ,和问杨程明。
3.customer-userprovider
IOT keycloak 单点 Login 的插件 ,可以在各个租户/客户不开发数据库访问权限的情况下 ,向 keycloak 共享 User 数据 ,进而实现每个客户的 User 权限体系可以和 iot 的 User 体系进行映射。同时也实现不同的租户/客户对于 Iot 来说实际处于不同的域下面。如果向现在在同 one 域下 ,不同客户之间可以用 token 轻松地调用其他客户的数据。必须做域的隔离。
