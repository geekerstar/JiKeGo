## 云服务器
阿里云 CentOS 6.8  

自行切换阿里云的源

## 配置线上环境
### JDK
### Tomcat
### Maven
### Vsftpd
#### Nginx
### MySQL
### Git
### 配置防火墙

## shell自动化发布脚本

```shell
echo "===========进入git项目jikego目录============="
cd /developer/git-repository/jikego


echo "==========git切换分之到jikego-v1.0==============="
git checkout v1.0

echo "==================git fetch======================"
git fetch

echo "==================git pull======================"
git pull


echo "===========编译并跳过单元测试===================="
mvn clean package -Dmaven.test.skip=true


echo "============删除旧的ROOT.war==================="
rm /developer/apache-tomcat-8.0.52/webapps/ROOT.war


echo "======拷贝编译出来的war包到tomcat下-ROOT.war======="
cp /developer/git-repository/mmall/target/jikego.war  /developer/apache-tomcat-8.0.52/webapps/ROOT.war


echo "============删除tomcat下旧的ROOT文件夹============="
rm -rf /developer/apache-tomcat-8.0.52/webapps/ROOT



echo "====================关闭tomcat====================="
/developer/apache-tomcat-8.0.52/bin/shutdown.sh


echo "================sleep 10s========================="
for i in {1..10}
do
	echo $i"s"
	sleep 1s
done


echo "====================启动tomcat====================="
/developer/apache-tomcat-8.0.52/bin/startup.sh

```