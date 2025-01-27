package com.matariky.redis;

public class LuaScript {

    /** Save Reader Task **/
    public static final String SAVE_READER_TASK = "if ARGV[2] ~= '0' then local array = {} for v in string.gmatch(ARGV[2], '%w+') do redis.call('SADD', KEYS[1], '\"'..v..'\"') end redis.call('expire', KEYS[1], ARGV[1]) end for i = 2, #(KEYS) do redis.call('SETEX', KEYS[i], ARGV[1], ARGV[i + 1]) end return 1";
    /** Save Read Result（In/Out repository） **/
    public static final String SAVE_READER_RESULT_1 = "redis.call('SADD', KEYS[1], ARGV[1]) redis.call('expire', KEYS[1], ARGV[3]) for v in string.gmatch(ARGV[1], '%w+') do redis.call('HSET', KEYS[2], v, ARGV[2]) end  redis.call('expire', KEYS[2], ARGV[3]) redis.call('expire', KEYS[3], ARGV[3]) return 1";
    /** Save Read Result（Inventory） **/
    public static final String SAVE_READER_RESULT_2 = "for v in string.gmatch(ARGV[1], '%w+') do redis.call('HSET', KEYS[1], v, ARGV[2]) end redis.call('expire', KEYS[1], ARGV[3]) return 1";
}
