# JamJavaUtil

## 数据库连接类
增加了mysql和sqlite 支持，修改配置文件可以切换数据源
数据库连接
查询转JSON对象或者JAVA对象
list插入数据库

## OCR 文字识别

### 分层结构
- 通用能力（识别任意图片文字，与业务无关）：
  - `TesseractOcrUtil` —— 基于 Tesseract（tess4j）
  - `DdddOcrUtil` —— 基于 ddddocr（ONNX），对验证码等短文本准确率更高
- 业务封装：
  - `VerifyCodeUtil` —— 验证码识别（内部调用上面的通用工具，附加单行模式与字符白名单）

### 模型目录（models/）
 所有模型统一放在 `models/` 目录，已随仓库提交，**开箱即用**：

 - `eng.traineddata`（约 22MB）—— Tesseract 英文语言数据
 - `common_old.onnx`（约 13MB）—— ddddocr 默认 OCR 模型
 - `charset_old.txt` —— ddddocr 模型配套字符集（8209 个字符）

 路径均可用 JVM 参数覆盖：
 - Tesseract 数据目录：`-Dtesseract.datapath=models`
 - ddddocr 模型：`-Dddddocr.model.path=models/common_old.onnx`
 - ddddocr 字符集：`-Dddddocr.charset.path=models/charset_old.txt`

### 使用示例
 ```java
 // 通用文字识别
 TesseractOcrUtil.ocr("doc.png");                       // 默认 eng + 自动版面
 TesseractOcrUtil.ocr("cn.png", "chi_sim", 3, null);    // 指定语言
 DdddOcrUtil.ocr("text.png");                           // ddddocr 方案

 // 验证码识别
 VerifyCodeUtil.ocrCode("captcha.png");                 // Tesseract 方案
 VerifyCodeUtil.ddddOcrCode("captcha.png");             // ddddocr 方案
 ```

### 更换 / 新增自己的模型
 **Tesseract（更换语言或模型）**
 - 从 https://github.com/tesseract-ocr/tessdata 下载 `*.traineddata`（如简体中文 `chi_sim.traineddata`），放入 `models/`
 - 调用时指定语言即可：`TesseractOcrUtil.ocr(image, "chi_sim", 3, null)`
 - 若模型放在其他目录，用 `-Dtesseract.datapath=你的目录` 指定

 **ddddocr（更换为自己的 ONNX 模型）**
 - 把模型与配套 charset 放入 `models/`，用 JVM 参数指定：
   `-Dddddocr.model.path=models/my_model.onnx -Dddddocr.charset.path=models/my_charset.txt`
 - 要求：输入为**高度 64 的单通道灰度图**，输出为 CTC 字符序列（`[T,1,C]` 或 `[1,T,C]`），charset 索引 0 为 blank
 - 字符集导出方式：
   `python -c "import ddddocr.charsets as c; open('charset_old.txt','w',encoding='utf-8').write(''.join(c.CHARSET_OLD[1:]))"`
 - 输入尺寸不同的模型，需同步调整 `DdddOcrUtil.INPUT_HEIGHT` 与预处理逻辑

 


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
