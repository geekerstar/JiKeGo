# 项目关键节点记录
## 项目初始化
数据库初始化

安装IDEA

Maven创建Web项目

git创建仓库及初始化

项目包初始化

Mybatis-generator

Mybatis-plugin

Mybatis-pagehelper

Web.xml初始化

spring配置applicationContext.xml

springmvc配置dispatcher-servlet.xml初始化

logback初始化

ftp开发环境初始化

IDEA注入和实时编译配置

Restlet client插件

FE插件

## 数据库介绍
### 表结构
用户表

分类表

产品表

购物车表

支付信息表

订单表

订单明细表

收货地址表

### 注意点
表关系

唯一索引

单索引

组合索引

时间戳

## 用户管理模块
### 功能模块
登录

用户名验证

注册

忘记密码

提交问题答案

重置密码

获取用户信息

更新用户信息

退出登录

### 技术点
横向越权、纵向越权安全漏洞

MD5明文加密及增加salt值

Guava缓存的使用

高复用服务响应对象的设计思想及抽象封装

Mybatis-plugin

Session的使用

方法局部演进

接口设计

## 分类管理模块
### 功能模块
获取节点

增加节点

修改节点

获取分类ID

递归子节点ID

### 技术点
如何设计及封装无限层级的树状数据结构

递归算法的设计思想

如何处理复杂对象排重

重写Hashcode和equal的注意事项

接口设计

## 商品模块
### 功能模块
#### 前台
商品搜索

动态排序

商品列表

商品详情

#### 后台
商品列表

商品搜索

图片上传

富文本

富文本图片上传

商品详情

商品上下架

增加商品

更新商品

### 技术点
FTP服务器的对接

SpringMVC文件上传

流读取Properties配置文件

抽象POJO、BO、VO对象之间的转换关系及解决思路

joda-time

静态块

Mybatis-PageHelper高效准确地分页及动态排序

Mybatis对List遍历的实现方法

Mybatis对where语句动态拼装的几个版本演变

接口设计

## 购物车模块
### 功能模块
加入商品

更新商品数

查询商品数

移除商品

单选/取消

全选/取消

购物车列表

### 技术点
购物车模块的设计思想

如何封装一个高复用的购物车核心方法

BigDecimal解决浮点型商业运算中丢失精度的问题

接口设计

## 收货地址模块
### 功能模块
添加地址

删除地址

修改地址

地址列表

地址列表分页

地址详情

### 技术点
SpringMVC数据绑定中对象绑定

Mybatis自动生成主键、配置和使用

如何避免横向越权漏洞的巩固

接口设计

## 支付模块
### 功能模块
#### 支付宝对接
参考重要的官方文档

沙箱环境调试

支付宝扫码支付流程

支付宝文档重要的字段

支付宝对接技巧

支付宝官方Demo调试

支付回调

查询支付状态

##### 支付宝支付重要细节
主动轮询

异步回调

回调的处理

回调的频率控制

回调的调试方法


### 技术点
支付宝调试技巧：natapp/tomcat remote debug

熟悉支付宝对接核心文档，调通支付宝支付功能官方Demo

解析支付宝SDK对接源码

RSA1和RSA2验证签名及加解密

避免支付宝重复通知和数据校验

生成二维码，并持久化到图片服务器

接口设计

### 参考资料
> 沙箱登录：https://openhome.alipay.com/platform/appDaily.htm 
沙箱环境使用说明：https://doc.open.alipay.com/doc2/detail.htm?treeId=200&articleId=105311&docType=1 
如何使用沙箱环境：https://support.open.alipay.com/support/hotProblemDetail.htm?spm=a219a.7386793.0.0.uS5uZ6&id=251932&tagId=100248 
当面付产品介绍：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.hV5Clx&treeId=193&articleId=105072&docType=1 
扫码支付接入指引：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Ia6Wqy&treeId=193&articleId=106078&docType=1 
当面付快速接入：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.bROnXf&treeId=193&articleId=105170&docType=1 
当面付接入必读：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.hV5Clx&treeId=193&articleId=105322&docType=1 
当面付进阶功能：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.YFmkxI&treeId=193&articleId=105190&docType=1 
当面付异步通知-仅用于扫码支付：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.BykVSR&treeId=193&articleId=103296&docType=1 
当面付SDK&DEMO：https://support.open.alipay.com/docs/doc.htm?spm=a219a.7386797.0.0.k0rwWc&treeId=193&articleId=105201&docType=1 
服务端SDK：https://doc.open.alipay.com/doc2/detail?treeId=54&articleId=103419&docType=1 
生成RSA密钥：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=105971&docType=1 
线上创建应用说明：https://doc.open.alipay.com/doc2/detail.htm?treeId=200&articleId=105310&docType=1#s0 

## 订单模块
### 功能模块
#### 前台
创建订单

商品信息

订单列表

订单详情

取消订单

#### 后台
订单列表

订单搜索

订单详情

订单发货

### 技术点
避免业务逻辑中横向越权和纵向越权等安全漏洞

设计实用、安全、扩展性强大的常量、枚举类

订单号生成规则，订单严谨性判断

POJO和VO之间的实际操练

Mybatis批量插入

接口设计

## 项目部署
云服务器申请及配置

域名申请及备案

域名解析配置

源配置

自动化发布Shell脚本

线上trouble shooting

线上验证

### 线上环境配置
赋予用户root权限

JDK

Tomcat

Maven

Vsftpd

nginx

文件服务器

MySQL

Git

Iptables

环境变量等安装配置

### 线上部署脚本
```shell
echo "===========进入git项目jikego目录============="
cd /developer/git-repository/jikego


echo "==========git切换分之到"$1"==============="
git checkout $1

echo "==================git fetch======================"
git fetch

echo "==================git pull======================"
git pull


echo "===========编译并跳过单元测试===================="
mvn clean package -Dmaven.test.skip=true -Pprod


echo "============删除旧的ROOT.war==================="
rm /developer/$2/webapps/ROOT.war


echo "======拷贝编译出来的war包到tomcat下-ROOT.war======="
cp /developer/git-repository/jikego/target/jikego.war  /developer/$2/webapps/ROOT.war


echo "============删除tomcat下旧的ROOT文件夹============="
rm -rf /developer/$2/webapps/ROOT



echo "====================关闭tomcat====================="
/developer/$2/bin/shutdown.sh


echo "================sleep 10s========================="
for i in {1..10}
do
	echo $i"s"
	sleep 1s
done


echo "====================启动tomcat====================="
/developer/$2/bin/startup.sh
```














