# Java Code Generator

#### Project Introduction

## 1.This project adopts the SpringBoot + JPA + MyBatis architecture

2.Main Features:
Generates code for bean, mapper, service, and controller in the traditional SpringMVC development model

3.The project supports deployment in both Server and Local operation modes

#### Software Architecture

The SpringBoot project is imported using IDEA

#### Installation Guide

1. Create a MySQL database. The SQL scripts can be found in the sql folder. The database name: automation
2. Update the database connection information in application.yml, including the port number and log file history folder path file.url
3. Use the mvn command in IDEA to package the project: mvn clean package
4. Successful packaging screen:
   5.The location of the jar package in the project:

![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/091539_b5066fb2_424304.png "屏幕截图.png")

6.Copy the project to the server or another computer for installation (The environment must be JDK 17) 7.Use the command to start the project: java -jar automation.jar
![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/091816_88d3becd_424304.png "屏幕截图.png")
备注说明：进入放 jar 包的上级目录处按住 Shift+鼠标右击--》点击在此处打开命令 输入启动命令 java -jar amation.jar 出现下面页面表示启动成功

![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092022_146c2dc3_424304.png "屏幕截图.png")

8.Access the application in the browser using IP Address:Port

**Ensure you use your actual IP Address. For local access, use 127.0.0.1 instead of localhost**

#### Usage Instructions

a. Code Download

1. Open the webpage as shown below:
   ![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092306_5dc77fc0_424304.png "屏幕截图.png")
2. Enter the package name and connect to the database:
   ![输入 Image 说明](https://gitee.com/uploads/images/2018/0604/092606_71d87a43_424304.png "屏幕截图.png")
3. Select the table name and check the fields for which bean, mapper, service, and controller mappings need to be generated:
   ![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093031_aab2f72f_424304.png "屏幕截图.png") 4.Click submit to generate and download the code:
   ![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093524_11d879b5_424304.png "屏幕截图.png")

b. Database Design Document Export

1.Open the interface, enter database connection details, read the database, select all or specific tables, and click export:
![输入 Image 说明](https://images.gitee.com/uploads/images/2019/0211/093805_3aa9fe94_424304.png "屏幕截图.png")
