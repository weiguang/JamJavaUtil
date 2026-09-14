# JamJavaUtil

## 数据库连接类
增加了mysql和sqlite 支持，修改配置文件可以切换数据源
数据库连接
查询转JSON对象或者JAVA对象
list插入数据库

## 验证码识别
 可以使用VerifyCodeUtil类识别验证码，使用tesseract进行验证

### OCR 语言数据（tessdata）
 识别功能依赖 `tessdata/` 目录下的训练数据文件，当前仓库只保留了英文模型：

 - `eng.traineddata`（约 23MB）—— 英文，`VerifyCodeUtil` 默认使用（`setLanguage("eng")`）
 - `chi_sim.traineddata`（约 44MB）—— 简体中文，本仓库未使用，已从仓库移除以减小体积

 如需识别其他语言（如中文），请自行下载对应文件放入 `tessdata/` 目录即可：

 - 官方数据仓库：https://github.com/tesseract-ocr/tessdata
 - 简体中文：https://github.com/tesseract-ocr/tessdata/raw/main/chi_sim.traineddata
 - 英文：https://github.com/tesseract-ocr/tessdata/raw/main/eng.traineddata
 - 全部语言打包下载：https://codeload.github.com/tesseract-ocr/tessdata/zip/4.0.0

 > 下载后按 `tessdata/文件名.traineddata` 放置，例如中文为 `tessdata/chi_sim.traineddata`。
 > 注意：`tessdata/` 下的 `*.traineddata` 属大体积第三方数据，不建议提交到 git。
 


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


## 一些易混淆的 测试 例子。

例如

output is : "value is  = 9.0" , ps: 10.9d is a double var,so 9i is convert to 9.0d

` System.out.println("value is  = " + ( (a < 5 )? 10.9 : 9 )); `
