package com.matariky.redis.redisson;

public class LockKey {

    /** Library tape lock key **/
    public final static String TAPE_LIBRARY_LOCK_KEY = "tape_library_lock:";

    /** Inventory Task lock **/
    public final static String TAPE_INVENTORY_TASK_LOCK_KEY = "tape_inventory_task_lock:";

    /** Inventory Task Immediate Execution Lock **/
    public final static String TAPE_INVENTORY_TASK_IMMEDIATE_LOCK_KEY = "tape_inventory_task_immediate_lock:";

    /** Label Generation Immediate Execution Lock **/
    public final static String TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY = "tape_rfidcreate_task_immediate_lock:";

    private LockKey() {

    }
}
