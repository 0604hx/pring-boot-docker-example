"""
add on 2017-11-14 14:13:41
"""
import sys
import platform


def getOSInfo():
    """
    获取操作系统信息
    :return:
    """
    return {
        'system': platform.system(),
        'version': platform.version(),
        'machine': platform.machine(),
        'platform': sys.platform,
        '64Bit': sys.maxsize > 2 ** 32,
        'cpu': platform.processor()
    }


def getPythonInfo():
    """
    获取 python 信息，返回 version、 compiler 两个属性
    :return:
    """
    return {
        'version': platform.python_version(),
        'compiler': platform.python_compiler()
    }
