# Python Example
> 基于[Flask](http://flask.pocoo.org/)搭建的演示程序，建议使用 python 3+

## API

本程序只提供以下两个功能：

**heartbeat**

心跳包测试，参数： `time`，返回值与参数一致

示例： `heartbeat/130000000` 则返回 `130000000`

**info**

获取当前平台运行时信息（包括`操作系统`、`Python` 版本等）

返回格式如下：

```json
{
  "python": {
    "compiler": "MSC v.1900 64 bit (AMD64)", 
    "version": "3.6.2"
  }, 
  "system": {
    "64Bit": true, 
    "cpu": "Intel64 Family 6 Model 60 Stepping 3, GenuineIntel", 
    "machine": "AMD64", 
    "platform": "win32", 
    "system": "Windows", 
    "version": "10.0.15063"
  }
}
```


## 如何运行
> 强烈推荐试用 `virtualenv` 来安装依赖


```bash
pip install -r requirements.txt

python server.py
```

输出信息如下：
```text
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 859-405-227
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
```


**打包**

以下命令打包成当前平台下可运行的单文件(文件名为 `flask-demo`)，详见的打包请见：

```bash
pyinstaller -F -n flask-demo server.py
```

##