标签数据
{
"deviceType": "SRE401", //设备类型
"deviceCode": "SRE801_1_gate1_1", //设备 Code
"timestamp":"2023-06-28T07:24:00.277766184Z", // Message 发送时间
"hostname":"impinj-14-2f-f4", //设备 Name 称
"eventType":"tagInventory", // Event 类型
"tagInventoryEvent":{
"epc":"4sRRo6UBH6APNUaT",  
 "epcHex":"E2C451A3A5011FA00F354693", //epc16 进制 Code
"tid":"4sRRoyAAJpN5qv0A", //时间戳
"tidHex":"E2C451A32000269379AAFD00", //tid 16 进制 Code
"pc":"NAA=",  
 "antennaPort":2, //天线端口
"antennaName":"MQTT", //天线 Name 称
"peakRssiCdbm":-5750, //Rssi Cdbm 峰值
"frequency":921625, //频率
"transmitPowerCdbm":1000, //发射 Power
"lastSeenTime":"2023-06-28T07:24:00.225324Z", // Read 时间
"phaseAngle":208.82, //相位角度
"tagMemoryData":[
{
"memoryBank":"user", // Read 区域
"wordOffset":0,
"tagDataHex":""
}
],
"tagAuthenticationResponse":{ //标签认证响应
"messageHex":"054CB6BBC56A", //密文
"responseHex":"EE69413D90DE7AA7", //随机数时间戳
"tidHex":"51A3269379AAFD00" //shortTid
}
},
"originalMessage": ""//原 Message 体串
}

心跳
{
"deviceType": "SRE401", //设备类型
"deviceCode": "SRE801_1_gate1_1", //设备 Code
"ip": "192.168.1.136", //设备 IP
"mac": "00142951ac04", //设备 mac Address
"isWork": 1, //是否 Read 中
"systemTime": "1690460004219", // Current System 时间
"softVersion": "S.R.MQTT.1.23", // Firmware Version 号
"originalMessage": ""//原 Message 体串
}

Upgrade
{
"deviceType": "SRE401", //设备类型
"deviceCode": "SRE801_1_gate1_1", //设备 Code
"status": 0, //返回状态码 0: Upgrade 成功 1： Version 一致 2： File 下载失败 3：校验 Error
"msg": "", // Error 码说明
"originalMessage": ""//原 Message 体串
}

时间同步
{
"deviceType": "SRE401", //设备类型
"deviceCode": "SRE801_1_gate1_1", //设备 Code
"status":0, //返回状态码 0: 同步成功 其它：失败
"msg": "", // Error 码说明
"originalMessage": ""//原 Message 体串
}
