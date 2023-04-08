安装ELK
1、用docker的方式来安装es
```
docker pull elasticsearch:7.14.0
```
如果Mac M1之类的芯片安装失败的话，可以在后面加上--platform linux/x86_64

2、启动es
```
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -d elasticsearch:7.14.0
```
安装好了去检查curl http://localhost:9200  

3、修改配置，解决跨域访问问题
```
docker exec -it elasticsearch /bin/bash
cd /usr/share/elasticsearch/config/
vi elasticsearch.yml
```

在末尾加上
```
http.cors.enabled: true
http.cors.allow-origin: "*"
```
4、安装IK分词器
注意：要和es版本保持一致，不然在重启的时候会失败  
```
cd /usr/share/elasticsearch/plugins/
elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.14.0/elasticsearch-analysis-ik-7.14.0.zip
exit
docker restart elasticsearch
```

5、安装kibana
```
docker pull kibana:7.14.0
```
6、启动kibana
```
docker run --name kibana --link=elasticsearch:test  -p 5601:5601 -d kibana:7.14.0
docker start kibana
```
7、打开浏览器输入http://localhost:5601就可以打开kibana的界面了  

8、如果插入索引文档失败可能是没有显示警用安全选项导致的
```
network.host: 0.0.0.0
xpack.security.enabled: false
```
