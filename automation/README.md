# java 代码生成器

#### 项目介绍

## 1.本项目采用 SPringBoot+JPA+Mybaties 架构

2.主要功能简介
可连接数据库 MYSQL ,ORACLE（暂未开发）,SQLServer(暂未开发)
生成 SpringMVC 传统开发模式下的 bean ,mapper ,service ,controller 代码

---

3.项目支持部署 Server 和 Local 运行两种方式

#### 软件架构

springBoot 项目使用 IDEA 进行导入

#### Install 教程

1. Create mysql 数据库 项目中 sql File 夹内有执行脚本 数据库 Name 称：automation
2. Update appliaction.yml 中数据库连接信息 ,端口号 ,生成 Log 历史 File 夹路径 file.url
3. 在 IEDA 中使用 mvn 命令进行打包 mvn clean package 4.打包成功界面
   ![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/091444_e22a15bd_424304.png "1.png")

5.jar 包在项目中的位置

![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/091539_b5066fb2_424304.png "屏幕截图.png")

6.拷贝项目至 Server 或者其他电脑 Install （环境必须是 JDK1.8） 7.使用命令启动项目 java -jar amation.jar
![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/091816_88d3becd_424304.png "屏幕截图.png")
备注说明：进入放 jar 包的上级目录处按住 Shift+鼠标右击--》点击在此处打开命令 输入启动命令 java -jar amation.jar 出现下面页面表示启动成功

![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092022_146c2dc3_424304.png "屏幕截图.png")

8.在 Browser 中输入 IP Address :端口进行访问
**_必须是你的 IP Address 本机使用 127.0.0.1 或者 IP 不要使用 localhost_**

#### 使用说明

a.代码下载

1. 打开页面如下
   ![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092306_5dc77fc0_424304.png "屏幕截图.png")
2. 输入包 Name ,链接数据库
   ![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092606_71d87a43_424304.png "屏幕截图.png")
3. 选择表 Name 勾选需要生成 bean,mapper,service,controller 的映射字段
   ![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093031_aab2f72f_424304.png "屏幕截图.png") 4.点击提交生成下载代码
   ![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093524_11d879b5_424304.png "屏幕截图.png")

b.数据库设计文档导出

1.打开界面 ,输入数据库连接信息 , Read 数据库 ,选中所有表或者个别表点击导出
![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093805_3aa9fe94_424304.png "屏幕截图.png")

#### 参 And 贡献

1. 小康

#### 码 Cloud 特技

1. 使用 Readme_XXX.md 来支持不同的语言 ,例如 Readme_en.md, Readme_zh.md
2. 码 Cloud 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个 Address 来了解码 Cloud 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码 Cloud 最有价值开源项目 ,是码 Cloud 综合评定出的优秀开源项目
5. 码 Cloud 官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码 Cloud 封面人物是一档用来展示码 Cloud 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
