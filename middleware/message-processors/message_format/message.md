标签数据
{
    "deviceType":   "SRE401",  //设备类型
    "deviceCode":     "SRE801_1_gate1_1",  //设备编码
    "timestamp":"2023-06-28T07:24:00.277766184Z",  //消息发送时间
    "hostname":"impinj-14-2f-f4",  //设备名称
    "eventType":"tagInventory",  //事件类型
    "tagInventoryEvent":{
        "epc":"4sRRo6UBH6APNUaT",  
        "epcHex":"E2C451A3A5011FA00F354693",  //epc16进制编码
        "tid":"4sRRoyAAJpN5qv0A",  //时间戳
        "tidHex":"E2C451A32000269379AAFD00",  //tid 16进制编码
        "pc":"NAA=",  
        "antennaPort":2,  //天线端口
        "antennaName":"MQTT",  //天线名称
        "peakRssiCdbm":-5750,  //Rssi Cdbm峰值
        "frequency":921625,  //频率
        "transmitPowerCdbm":1000,  //发射功率
        "lastSeenTime":"2023-06-28T07:24:00.225324Z",  //读取时间
        "phaseAngle":208.82,  //相位角度
        "tagMemoryData":[
            {
                "memoryBank":"user", //读取区域
                "wordOffset":0,
                "tagDataHex":""
            }
        ],
        "tagAuthenticationResponse":{  //标签认证响应
            "messageHex":"054CB6BBC56A", //密文
            "responseHex":"EE69413D90DE7AA7", //随机数时间戳
            "tidHex":"51A3269379AAFD00"  //shortTid
        }
    },
    "originalMessage": ""//原消息体串
}

心跳
{
"deviceType":   "SRE401",  //设备类型
"deviceCode":     "SRE801_1_gate1_1",  //设备编码
"ip":   "192.168.1.136",   //设备IP
"mac":  "00142951ac04",    //设备mac地址
"isWork":       1,  //是否读取中
"systemTime":   "1690460004219",  //当前系统时间
"softVersion":  "S.R.MQTT.1.23",   //固件版本号
"originalMessage": ""//原消息体串
}

升级
{
"deviceType":   "SRE401",  //设备类型
"deviceCode":     "SRE801_1_gate1_1",  //设备编码
"status": 0,  //返回状态码 0: 升级成功 1：版本一致 2：文件下载失败 3：校验错误
"msg": "",   //错误码说明
"originalMessage": ""//原消息体串
}

时间同步
{
"deviceType":   "SRE401",  //设备类型
"deviceCode":     "SRE801_1_gate1_1",  //设备编码
"status":0,   //返回状态码 0: 同步成功  其它：失败
"msg": "",   //错误码说明
"originalMessage": ""//原消息体串
}

