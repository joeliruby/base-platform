package com.matariky.constant;

public class RedisKey {

    /** Common serial number **/
    public static final String COMMON_NO_SEQ = "common_no_seq:%s:%s";

    /** Reader Executive Task ： Reader Code **/
    public static final String READER_EXECUTION_TASK = "reader_execution_task:";

    /** Read Task Label epc Information ： Task id **/
    public static final String READER_TASK_EPC = "reader_task_epc:";

    /** Read Task Label Information ： Task id **/
    public static final String READER_TASK_LABEL = "reader_task_label:";

    /** Task in stock Label epc Information ： Task id **/
    public static final String TASK_STOCK_EPC = "task_stock_epc:";

    /** cache Reader Code ： Task id **/
    public static final String TASK_READER_CODE = "task_reader_code:";

    /** appIn/Out Library Information **/
    public static final String APP_INOUT_INFO = "app_inout_info";

    // Cabinet ID
    public static final String LIBRARY_ID = "library_id:";

    // Enter the warehouse
    public static final String CHECK_IN = "check_in";

    // Out of the warehouse
    public static final String CHECK_OUT = "check_out";

    // EPC TID Mapping
    public static final String EPC_TID_MAP = "epc_tid_map:";

    // inventory
    public static final String INVENTORY_CHECK = "inventory_check";

    // Full amount of cabinets
    public static final String LIBRARY_BULK = "library_bulk";

    // menu Name

    public static final String MENU_NAMES = "qsl_menu_names:";

    // User Final active Time Prefix
    public static final String USER_LAST_ACTIVE_TIME_PREFIX = "LAST_ACTIVE_TIME";

    // User Active timeout Time
    public static final String APPLICATION_ACTIVITY_TIMEOUT_PREFIX = "USER_ACTIVITY_TIMEOUT";

    private RedisKey() {

    }
}
