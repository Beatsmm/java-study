# 签名
接口签名是一种用于标识接口的方法,其目的是确保接口调用的正确性和安全性,接口签名是通过对接口的参数、返回值、函数名称和其他相关信息进行哈希运算  
得到的一串字符串,这个字符串唯一标识了接口.

## 作用
1、防止篡改：接口签名可以防止中间人攻击、数据篡改和重放攻击等安全问题  
2、身份验证：接口签名可以验证接口调用方的身份,确保只有授权的用户才能访问接口  
3、数据完整性：接口签名可以确保接口传输的数据没有被篡改过

## 例子  
很有第三方API接口为了防止别人篡改数据,通常会增加数字签名sign的验证  
sign = md5(多个参数拼接+密钥)  
在刚开始对接第三方平台接口时,会遇到参数错误,签名错误等问题,其中参数错误比较好解决,重点是签名错误这个问题  
比如：将参数名和参数值用冒号拼接,如果有多个参数,则按首字母排序,然后再将多个参数一起拼接,然后加盐(即-密钥)  
在通过md5,生成一个签名,如果有多个参数,你是按首字母倒叙的,则最后生成的签名出现问题,如果你是开发环境的密钥,  
用成生产环境的,也可能会产生签名出现问题,如果第三方平台要求最后3次md5生成签名,你只md5了一次,也会出现签名错误

## 流程 
首先我们需要分配给客户端一个私钥用于url签名加密,一般的签名算法如下：  
1、首先对请求参数按照key进行字母排序放入有序地集合中  
2、对排序完地数组键值对用&进行链接形成用于加密的参数字符串  
3、对加密的参数字符串前面后者后面加上私钥,然后用加密算法进行加密,得到sign，然后随着请求接口一起传给服务器  
例：https://api.xxxx.com/token?key=value&timetamp=xxxx&sign=xxxx-xxx-xxx-xxxx  
服务器端接受到请求以后,用同样的算法获得服务器的sign,对比客户端的sign是否一致,如果一致请求有效,如果不一致  
返回指定的错误信息  
补充：对什么进行签名  
1、请求参数,最主要的部分,签名的目的就是防止参数被篡改,就要对可能被篡改的参数签名  
2、同时考虑到请求参数的来源可能是请求路径path中,请求header中,请求body中  
3、如果对客户端分配了Appkey&AppSecret,那么Appkey也要参与签名,这样可以防止Appkey被泄露,防止恶意请求  
4、考虑到其他幂等,token失效等,也会将涉及的参数一并加入签名,比如timestamp,流水号  

## 签名和加密是不是一回事？  
不是一回事  
1、签名是通过对参数按照指定的算法、规则计算出sign,最后前后端通过同样的算法计算出sign是否一致来防止参数篡改,  
所以可以看到参数是明文的,只是多加了一个计算出的sign  
2、加密是对请求的参数加密,后端进行解密,同时有些情况下,也会对返回的response进行加密,前端进行解密,这里存在 
加密和解密的过程,所以思路上必然是对称加密的形式+时间戳接口时效性的验证,这里的参数是密文的,所以加密和签名不是一回事  

## 签名放在哪？  
签名可以放在请求参数中(path后者body),更为优雅的可以放在header中,这样可以避免参数被篡改,同时也可以避免参数过多