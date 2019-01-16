# JamJavaUtil

## 数据库连接类
增加了mysql和sqlite 支持，修改配置文件可以切换数据源
数据库连接
查询转JSON对象或者JAVA对象
list插入数据库

## 验证码识别
 可以使用VerifyCodeUtil类识别验证码，使用tesseract进行验证
 


## 一些易混淆的 测试 例子。

例如

output is : "value is  = 9.0" , ps: 10.9d is a double var,so 9i is convert to 9.0d

` System.out.println("value is  = " + ( (a < 5 )? 10.9 : 9 )); `


## 一些常用的工具的Java代码实现。

現有的工具包括

- 工具类
> 加密：MD5 hash256

> 文件操作类： 文件和文件夹的操作，创建，删除，移动，复制和解压缩 

> EXCEL操作类： 表格的基本操作 

> 排序：常用的排序，如快排，冒泡，归并等 

> 文本类: 文件的读取，写入等操作

> 正则表达式：常用的规则判断，如邮箱，电话，身份证，汉字，整数等

- 集合类
> LinkedList 实现的 队列

> LinkedList 实现的 栈
