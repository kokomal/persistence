#HTTPS改造
构建https的密钥，采用jdk自带的keytool工具进行构建，命令为：  

##keytool -genkey -alias tomcat  -storetype PKCS12 -keyalg RSA -keysize 2048  -keystore keystore.p12 -validity 3650

含义为采用PKCS12为存储类型，2048位加密，文件名为keystore.p12，有效期10年 
检查密钥命令为：

##keytool -list -v -keystore keystore.p12

备注：
原先旧的RSA的命令为：
##keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA
这样可能会被jdk提示：
JKS 密钥库使用专用格式。
建议使用 "keytool -importkeystore -srckeystore mykeys.jks -destkeystore mykeys.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
按照提示进行操作即可