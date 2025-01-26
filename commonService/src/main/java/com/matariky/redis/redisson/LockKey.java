package com.matariky.redis.redisson;

/**
 * @description: 锁键
 * @author: bo.chen
 * @create: 2023/2/13 18:27
 **/
public class LockKey {

    /** 机架磁带锁key **/
    public final static String TAPE_LIBRARY_LOCK_KEY = "tape_library_lock:";

    /** 盘点 Task 锁 **/
    public final static String TAPE_INVENTORY_TASK_LOCK_KEY = "tape_inventory_task_lock:";

    /** 盘点 Task 立即执行锁 **/
    public final static String TAPE_INVENTORY_TASK_IMMEDIATE_LOCK_KEY = "tape_inventory_task_immediate_lock:";

    /**  Label  Generation 立即执行锁 **/
    public final static String TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY = "tape_rfidcreate_task_immediate_lock:";


    private LockKey() {

    }
}
