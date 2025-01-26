package com.matariky.constant;

/**
 * @description: Redis key constant class
 * @author: bo.chen
 * @create: 2023/9/5 16:25
 **/
public class RedisKey {

    /** Common serial number **/
    public static final String COMMON_NO_SEQ = "common_no_seq:%s:%s";

    /** 读写器执行的 Task ：读写器编码 **/
    public static final String READER_EXECUTION_TASK = "reader_execution_task:";

    /** 读取 Task  Label epc Information ： Task id **/
    public static final String READER_TASK_EPC = "reader_task_epc:";

    /** 读取 Task  Label  Information ： Task id **/
    public static final String READER_TASK_LABEL = "reader_task_label:";

    /**  Task 库存 Label epc Information ： Task id **/
    public static final String TASK_STOCK_EPC = "task_stock_epc:";

    /** 缓存读写器编码： Task id **/
    public static final String TASK_READER_CODE = "task_reader_code:";

    /** app出入库 Information  **/
    public static final String APP_INOUT_INFO  = "app_inout_info";
    
    //机柜ID
    public static final String LIBRARY_ID="library_id:";
    
//    入库
    public static final String CHECK_IN="check_in";
    
//    出库
    public static final String CHECK_OUT="check_out";
    
//  EPC TID 映射
    public static final String EPC_TID_MAP="epc_tid_map:";
    
//    盘点
    public static final String INVENTORY_CHECK="inventory_check";
    
//   机柜全量
    public static final String LIBRARY_BULK ="library_bulk";
    
//   菜单名
    
    public static final String MENU_NAMES="qsl_menu_names:";
    
//  用户最后活跃 Time 前缀
    public static final String USER_LAST_ACTIVE_TIME_PREFIX="LAST_ACTIVE_TIME";
    
//  用户活跃超时 Time     
    public static final String APPLICATION_ACTIVITY_TIMEOUT_PREFIX="USER_ACTIVITY_TIMEOUT";

    private RedisKey () {
    	
    }
}
