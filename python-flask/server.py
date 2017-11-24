"""
简易 web 程序

基于 Flask，端口 5000

add on 2017年11月24日15:49:47
"""

from flask import Flask, jsonify

import OSUtil

app = Flask(__name__)


@app.route("/heartbeat/<string:data>")
def heartbeat(data):
    """
    心跳测试，直接返回参数
    :param data:
    :return:
    """
    print("heartbeat testing : %s" % data)
    return data


@app.route("/info")
def sys_info():
    info = {
        'system': OSUtil.getOSInfo(),
        'python': OSUtil.getPythonInfo()
    }
    return jsonify(info)


app.run(
    host="0.0.0.0",
    port=5000,
    debug=True
)
