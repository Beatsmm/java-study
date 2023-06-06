# Nginx

## Nginx使用docker安装
1、docker search nginx 搜索nginx镜像
2、docker pull nginx 拉取nginx最新镜像
3、docker images 查看本地是否安装了镜像
4、docker run -itd --name nginx-test -p 8080:80 nginx 启动nginx容器

## Nginx简介
Nginx是一个高性能的HTTP和反向代理服务器，同时也提供了IMAP/POP3/SMTP服务。Nginx的并发能力强,  
支持高达50,000个并发连接数。

## 正向代理
正向代理是指代理服务器代理客户端向服务器发送请求，服务器响应后再将响应返回给客户端。它最大的特点是客户端非常明确要访问的服务器端的地址,它代理客户端,替客户端发出请求  
假设客户端想要访问Google,它明确知道待访问的服务器地址是www.google.com,但是由于某些原因,客户端不能直接访问Google,这时候就需要一个代理服务器,客户端将请求发送给代理服务器,  
这就是一次正向代理的过程,这个过程里面服务器并不知道真正发出请求的是谁,只知道请求是从代理服务器发出的,这样就保证了客户端的隐私,同时也保证了服务器的安全。  

## 反向代理
反向代理是指代理服务器向客户端发送请求，客户端响应后再将响应返回给服务器。它最大的特点是客户端并不知道真正提供服务的服务器地址,它代理服务器,替服务器发出请求,  
换句话说反向代理的过程中,客户端并不知道是哪台服务器处理了自己的请求,也不知道服务器的地址,只知道请求是从代理服务器发出的。在这过程中反向代理需要考虑的问题就是  
如何进行均匀分工,控制流量,防止服务器过载,以及如何处理服务器宕机的情况。

## 负载均衡
单个服务器解决不了,我们增加服务器的数量,然后将请求分发到各个服务器上,将原来请求集中到单个服务器上的情况改为请求分发到多个服务器上,这就是负载均衡。  
1、轮询法  
轮询为负载均衡中最简单的一种方式,它的原理是将请求按顺序分配到不同的服务器上,每个服务器轮流处理请求。相当于循环遍历  
2、加权轮询法  
为了避免某些服务器处理能力较弱,而导致请求积压,我们可以为每个服务器设置一个权重,权重越大的服务器,每次分配到的请求就越多。  
3、IPHash法
IPHash法是根据客户端的IP地址来决定将请求分配到哪个服务器上,这样同一个客户端的请求总是分配到同一个服务器上,这样可以保证客户端的会话不会被打断。  
4、最少连接法  
最少连接法是根据服务器的连接数来决定将请求分配到哪个服务器上,连接数最少的服务器优先处理请求。  
5、URL Hash法  
URL Hash法是根据请求的URL来决定将请求分配到哪个服务器上,这样同一个URL的请求总是分配到同一个服务器上,这样可以保证同一个URL的请求总是访问到同一个服务器上的同一个文件。  
6、响应时间法  
响应时间法是根据服务器的响应时间来决定将请求分配到哪个服务器上,响应时间最短的服务器优先处理请求。  
7、随机法  
随机法是随机选择一个服务器来处理请求,这种方式的优点是简单,缺点是无法保证请求的分配是均匀的。

## 动静分离
动静分离是指将动态资源和静态资源分离到不同的服务器上,动态资源由应用服务器处理,静态资源由Nginx处理。加快解析速度,降低原来单个服务器的压力