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
    - `ocr(...)`：通用识别（自动版面分析）
    - `ocrDocument(...)`：**文档 / 代码截图专用**（PSM6 整块文本 + 放大 3 倍 + 保留缩进），多行文本识别率明显更高
  - `DdddOcrUtil` —— 基于 ddddocr（ONNX），**仅适合验证码等极短文本**
- 业务封装：
  - `VerifyCodeUtil` —— 验证码识别（内部调用上面的通用工具，附加单行模式与字符白名单）
    - **默认识别方案为 ddddocr**：`OCRCode(...)` 与 `ocrCode(...)` 均走 ddddocr
    - 如需 Tesseract 方案，请显式调用 `tesseractOcrCode(...)`

### 模型目录（models/）
 所有模型统一放在 `models/` 目录，已随仓库提交，**开箱即用**：

 - `eng.traineddata`（约 22MB）—— Tesseract 英文语言数据
 - `common_old.onnx`（约 13MB）—— ddddocr 默认 OCR 模型
 - `charset_old.txt` —— ddddocr 模型配套字符集（8209 个字符）

 路径均可用 JVM 参数覆盖：
 - Tesseract 数据目录：`-Dtesseract.datapath=models`
 - ddddocr 模型：`-Dddddocr.model.path=models/common_old.onnx`
 - ddddocr 字符集：`-Dddddocr.charset.path=models/charset_old.txt`

 > **关于 `common_old.onnx` 的输出 shape（已修正，勿与其他情况混淆）**
 >
 > 官方发布的 `common_old.onnx` 把输出节点声明为 `[1, -1]`（2 维），而模型真实输出是
 > `[T, 1, C]`（时间步 × batch × 类别数，3 维）。ONNX Runtime 会在**每次推理**时打印一条 W 级警告：
 >
 > ```
 > [W:onnxruntime:, execution_frame.cc:870 onnxruntime::ExecutionFrame::VerifyOutputSizes]
 > Expected shape from model of {1,-1} does not match actual shape of {20,1,8210} for output 387
 > ```
 >
 > 这只是模型的**描述性元数据写错了**，ORT 会以实际 shape 执行，**不影响识别结果**。
 >
 > 本仓库已把该模型的输出 shape 元信息修正为 `[T, N, 8210]`（**仅改元数据，未改动任何权重与计算逻辑**，
 > 修正后识别结果与修正前完全一致），因此正常运行**不会再出现这条警告**。
 > 若你重新从官方下载 `common_old.onnx`，该警告会再次出现 —— 属模型自身瑕疵，可忽略，或按下方脚本修正。
 >
 > 修正脚本（需要 `pip install onnx`）：
 > ```python
 > import onnx
 > from onnx import TensorProto, helper
 >
 > path = "models/common_old.onnx"
 > model = onnx.load(path)
 > fixed = [helper.make_tensor_value_info(o.name, TensorProto.FLOAT, ["T", "N", 8210])
 >          for o in model.graph.output]          # 原始误标为 [1, -1]
 > del model.graph.output[:]
 > model.graph.output.extend(fixed)
 > onnx.checker.check_model(model)
 > onnx.save(model, path)
 > ```

### 使用示例
 ```java
 // 通用文字识别
 TesseractOcrUtil.ocr("doc.png");                       // 默认 eng + 自动版面
 TesseractOcrUtil.ocr("cn.png", "chi_sim", 3, null);    // 指定语言

 // 文档 / 代码截图（多行文本，保留换行与缩进）
 TesseractOcrUtil.ocrDocument("img.png");               // img.png 为仓库内附带的示例代码截图

 // 验证码等极短文本
 DdddOcrUtil.ocr("captcha.png");                        // ddddocr 方案（仅适合短文本）

 // 验证码识别（默认方案为 ddddocr）
 VerifyCodeUtil.OCRCode("captcha.png");                 // 默认 ddddocr，失败返回 null（兼容旧调用）
 VerifyCodeUtil.ocrCode("captcha.png");                 // 默认 ddddocr，返回 Optional<String>
 VerifyCodeUtil.ddddOcrCode("captcha.png");             // 显式 ddddocr 方案
 VerifyCodeUtil.tesseractOcrCode("captcha.png");        // 显式 Tesseract 方案
 ```

  > `img.png` 是仓库内附带的一张 Java 代码截图，用于演示与测试文档文本识别效果
  > （对应测试见 `OcrCodeUtilTest#ocrDocumentImage`）。

 验证码识别方案对照：

 | 方法 | 使用方案 | 返回 |
 |---|---|---|
 | `VerifyCodeUtil.OCRCode(path)` | **ddddocr（默认）** | `String`，失败为 `null` |
 | `VerifyCodeUtil.ocrCode(path)` | **ddddocr（默认）** | `Optional<String>` |
 | `VerifyCodeUtil.ddddOcrCode(path)` | ddddocr | `Optional<String>` |
 | `VerifyCodeUtil.tesseractOcrCode(path)` | Tesseract（单行模式 + 字符白名单） | `Optional<String>` |

 > 默认识别方案从 Tesseract 切换为 ddddocr 后，旧的 `OCRCode(path)` 调用无需改动即可获得更高的验证码识别准确率。

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
