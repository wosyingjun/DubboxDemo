>Dubbo是一个来自阿里巴巴的开源分布式服务框架，当当根据自身的需求，为Dubbo实现了一些新的功能，包括REST风格远程调用、Kryo/FST序列化等等，并将其命名为Dubbox。

Demo：[https://github.com/wosyingjun/DubboxDemo](https://github.com/wosyingjun/DubboxDemo)    
类似范例：[https://github.com/wosyingjun/beauty_ssm_dubbo](https://github.com/wosyingjun/beauty_ssm_dubbo)

##Dubbo架构
![](http://dubbo.io/dubbo-architecture.jpg-version=1&modificationDate=1330892870000.jpg)

* Provider: 暴露服务的服务提供方。
* Consumer: 调用远程服务的服务消费方。
* Registry: 服务注册与发现的注册中心。
* Monitor: 统计服务的调用次调和调用时间的监控中心。
* Container: 服务运行容器。

##为什么使用Dubbox而不是Dubbo
Dubbo是阿里开源的RPC服务调用框架，已经3年没有维护了,而当当网开源的Dubbox在保证Dubbo原有功能的基础上做了一系列优化。

##使用Dubbox的好处
* 支持REST风格远程调用（HTTP + JSON/XML)：基于非常成熟的JBoss [RestEasy](http://resteasy.jboss.org/)框架，在dubbo中实现了REST风格（HTTP + JSON/XML）的远程调用，以显著简化企业内部的跨语言交互，同时显著简化企业对外的Open API、无线API甚至AJAX服务端等等的开发。事实上，这个REST调用也使得Dubbo可以对当今特别流行的“微服务”架构提供基础性支持。 另外，REST调用也达到了比较高的性能，在基准测试下，HTTP + JSON与Dubbo 2.x默认的RPC协议（即TCP + Hessian2二进制序列化）之间只有1.5倍左右的差距。
* 支持基于Kryo和FST的Java高效序列化实现：基于当今比较知名的[Kryo](https://github.com/EsotericSoftware/kryo)和[FST](https://github.com/RuedigerMoeller/fast-serialization)高性能序列化库，为Dubbo默认的RPC协议添加新的序列化实现，并优化调整了其序列化体系，比较显著的提高了Dubbo RPC的性能。
* 支持基于Jackson的JSON序列化：基于业界应用最广泛的[Jackson](http://jackson.codehaus.org/)序列化库，为Dubbo默认的RPC协议添加新的JSON序列化实现。
* 支持基于嵌入式Tomcat的HTTP remoting体系：基于嵌入式tomcat实现dubbo的HTTP remoting体系（即dubbo-remoting-http），用以逐步取代Dubbo中旧版本的嵌入式Jetty，可以显著的提高REST等的远程调用性能，并将Servlet API的支持从2.5升级到3.1。（注：除了REST，dubbo中的WebServices、Hessian、HTTP Invoker等协议都基于这个HTTP remoting体系）。
* 升级Spring：将dubbo中Spring由2.x升级到目前最常用的3.x版本，减少版本冲突带来的麻烦。
* 升级ZooKeeper客户端：将dubbo中的zookeeper客户端升级到最新的版本，以修正老版本中包含的bug。
* 支持完全基于Java代码的Dubbo配置：基于Spring的Java Config，实现完全无XML的纯Java代码方式来配置dubbo
* 调整Demo应用：暂时将dubbo的demo应用调整并改写以主要演示REST功能、Dubbo协议的新序列化方式、基于Java代码的Spring配置等等。

**注：Dubbox和Dubbo 2.X是兼容的，没有改变Dubbo的任何已有的功能和配置方式（除了升级了Spring之类的版本）**

##DubboxDemo项目的安装
* Git Clone https://github.com/dangdangdotcom/dubbox
* Checkout出来的Dubbox通过Maven编译并安装到本地仓库。
* Zookeeper安装，作为dubbox的注册中心，见：[ZooKeeper 高可用集群的安装及配置](http://wosyingjun.iteye.com/blog/2312960) 
* 修改配置文件地址<dubbo:registry protocol="zookeeper" address="zookeeper://xxxxxxxxx">。
* 直接运行ProviderTest类启动服务发布者。
* 直接运行ConsumerTest类启动服务消费者。

#####补充：
* 如果dubbo protocol配置为rest的，可以直接通过浏览器访问http://xxx.xx.xx.xx:8080/user/getUserByPhone/1888888888
* 服务提供者最终打成jar时建议将启动类设置为com.alibaba.dubbo.container.Main，具体可见Provider的pom.xml

##Dubbox支持的远程调用协议
* dubbo：采用单一长连接和NIO异步通讯，基于TCP协议，默认基于netty框架进行传输，Hessian二进制序列化。
* RMI：短连接同步传输，基于TCP协议，Java标准二进制序列化。
* Hessian：短连接同步传输，基于HTTP协议，缺省内嵌Jetty作为服务器实现，Hessian二进制序列化。
* http：短连接同步传输，基于HTTP协议，采用Spring的HttpInvoker实现，表单序列化。
* webservice：短连接同步传输，基于HTTP协议，基于CXF实现，SOAP文本序列化。
* thrift：thrift rpc框架。
* memcached：采用KV存储的方式传输数据。
* redis：采用KV存储的方式传输数据。
* rest：dubbox提供，HTTP + JSON/XML，可内嵌tomcat实现http remoting体系。

##Dubbox支持的序列化方式比较
![](http://dangdangdotcom.github.io/dubbox/images/bytes.png)
![](http://dangdangdotcom.github.io/dubbox/images/rt.png)

##推荐配置
    <dubbo:protocol name="dubbo" serialization="kryo"  port="20990"  />
    <dubbo:protocol name="rest" port="8080"  server="tomcat" />
