package com.matariky.commonservice.upload.constant;

/**
 * @description: message key class
 * @author: bo.chen
 * @create: 2023/9/6 18:06
 **/
public class MessageKey {
    /**
     *  Remark 长度不能超过255个字符
     **/
    public static final String COMMON_REMARK_LENGTH_LIMIT = "COMMON_REMARK_LENGTH_LIMIT";
    /**
     *  Status 为空
     **/
    public static final String COMMON_STATUS_IS_NULL = "COMMON_STATUS_IS_NULL";
    /**
     *  Status 非法
     **/
    public static final String COMMON_STATUS_ILLEGAL = "COMMON_STATUS_ILLEGAL";
    /**
     * 日期范围为空
     **/
    public static final String COMMON_DATE_RANGE_IS_NULL = "COMMON_DATE_RANGE_IS_NULL";
    /**
     * 日期范围月份差超过限制
     **/
    public static final String COMMON_DATE_RANGE_DIFF_MONTH_LIMIT = "COMMON_DATE_RANGE_DIFF_MONTH_LIMIT";
    /**
     * 图片大小不能超过%sM
     **/
    public static final String COMMON_IMAGE_SIZE_LIMIT = "COMMON_IMAGE_SIZE_LIMIT";
    /**
     * 图片格式错误
     **/
    public static final String COMMON_FILE_NOT_IMAGE = "COMMON_FILE_NOT_IMAGE";
    /**
     * 最多只能上传%s张图片
     **/
    public static final String COMMON_UPLOAD_IMAGE_LIMIT = "COMMON_UPLOAD_IMAGE_LIMIT";
    /**
     *  Data 过期
     **/
    public static final String COMMON_DATA_EXPIRE = "COMMON_DATA_EXPIRE";
    /**
     * 地点为空
     **/
    public static final String TAPE_LOCATION_IS_NULL = "TAPE_LOCATION_IS_NULL";
    /**
     * 机架不存在
     **/
    public static final String TAPE_LIBRARY_NOT_EXISTENT = "TAPE_LOCATION_LIBRARY_NOT_EXISTENT";
    /**
     * 机架正在盘点中
     **/
    public static final String TAPE_LIBRARY_INVENTORY_IN_PROGRESS = "TAPE_LIBRARY_INVENTORY_IN_PROGRESS";
    /**
     * 机架正在入库中
     **/
    public static final String TAPE_LIBRARY_STOCK_IN_PROGRESS = "TAPE_LIBRARY_STOCK_IN_PROGRESS";
    /**
     * 机架正在出库中
     **/
    public static final String TAPE_LIBRARY_STOCK_OUT_PROGRESS = "TAPE_LIBRARY_STOCK_OUT_PROGRESS";
    /**
     * 出入库Type 为空
     **/
    public static final String TAPE_INOUT_TYPE_IS_NULL = "TAPE_INOUT_TYPE_IS_NULL";
    /**
     * 出入库Type 非法
     **/
    public static final String TAPE_INOUT_TYPE_ILLEGAL = "TAPE_INOUT_TYPE_ILLEGAL";
    /**
     * 没有可出库的磁带
     **/
    public static final String TAPE_INOUT_OUT_EMPTY_STOCK = "TAPE_INOUT_OUT_EMPTY_STOCK";
    /**
     * 磁带出库Failed，请检查 Data 
     **/
    public static final String TAPE_INOUT_OUT_FAIL = "TAPE_INOUT_OUT_FAIL";
    /**
     * 磁带入库Failed，请检查 Data 
     **/
    public static final String TAPE_INOUT_IN_FAIL = "TAPE_INOUT_IN_FAIL";
    /**
     * 机架正在出入库中
     **/
    public static final String TAPE_LIBRARY_START_INOUT = "TAPE_LIBRARY_START_INOUT";
    /**
     * 机架未绑定读写器
     **/
    public static final String TAPE_LIBRARY_NOT_BIND_READER = "TAPE_LIBRARY_NOT_BIND_READER";
    /**
     * 机架"%s"未绑定读写器
     **/
    public static final String TAPE_LIBRARY_NOT_BIND_READER_SINGLE = "TAPE_LIBRARY_NOT_BIND_READER_SINGLE";
    /**
     * 记录不存在
     **/
    public static final String BIZ_RECORD_NON_EXISTENT = "BIZ_RECORD_NON_EXISTENT";
    /**
     *  Task 已取消
     **/
    public static final String BIZ_TASK_CANCELED = "BIZ_TASK_CANCELED";
    /**
     *  Task 已结束
     **/
    public static final String BIZ_TASK_ENDED = "BIZ_TASK_ENDED";
    /**
     * 未扫描到入库 Label 或者 Data 已过期
     **/
    public static final String TAPE_INOUT_IN_SCAN_LABEL_EMPTY_OR_EXPIRE = "TAPE_INOUT_IN_SCAN_LABEL_EMPTY_OR_EXPIRE";
    /**
     * 未扫描到出库 Label 或者 Data 已过期
     **/
    public static final String TAPE_INOUT_OUT_SCAN_LABEL_EMPTY_OR_EXPIRE = "TAPE_INOUT_OUT_SCAN_LABEL_EMPTY_OR_EXPIRE";
    /**
     * 出库Failed，库存为空
     **/
    public static final String TAPE_INOUT_OUT_STOCK_IS_NULL = "TAPE_INOUT_OUT_STOCK_IS_NULL";
    /**
     * 出库Failed，扫描的 Label 有误
     **/
    public static final String TAPE_INOUT_OUT_LABEL_ERROR = "TAPE_INOUT_OUT_LABEL_ERROR";
    /**
     * 入库Failed，扫描的 Label 有误
     **/
    public static final String TAPE_INOUT_IN_LABEL_ERROR = "TAPE_INOUT_IN_LABEL_ERROR";
    /**
     * 出库 Quantity大于库存 Quantity
     **/
    public static final String TAPE_OUTBOUND_QUANTITY_GT_STOCK_QUANTITY = "TAPE_OUTBOUND_QUANTITY_GT_STOCK_QUANTITY";
    /**
     *  Task  Name为空
     **/
    public static final String TAPE_INVENTORY_TASK_NAME_IS_NULL = "TAPE_INVENTORY_TASK_NAME_IS_NULL";
    /**
     *  Task  Name长度超过50个字符
     **/
    public static final String TAPE_INVENTORY_TASK_NAME_LENGTH_LIMIT = "TAPE_INVENTORY_TASK_NAME_LENGTH_LIMIT";
    /**
     *  Task Type 为空
     **/
    public static final String TAPE_INVENTORY_TASK_TYPE_IS_NULL = "TAPE_INVENTORY_TASK_TYPE_IS_NULL";
    /**
     *  Task Type 非法
     **/
    public static final String TAPE_INVENTORY_TASK_TYPE_ILLEGAL = "TAPE_INVENTORY_TASK_TYPE_ILLEGAL";
    /**
     *  Task 日期为空
     **/
    public static final String TAPE_INVENTORY_TASK_DATE_IS_NULL = "TAPE_INVENTORY_TASK_DATE_IS_NULL";
    /**
     *  Time 间隔为空
     **/
    public static final String TAPE_INVENTORY_TASK_TIME_INTERVAL_IS_NULL = "TAPE_INVENTORY_TASK_TIME_INTERVAL_IS_NULL";
    /**
     *  Time 间隔单位为空
     **/
    public static final String TAPE_INVENTORY_TASK_INTERVAL_UNIT_IS_NULL = "TAPE_INVENTORY_TASK_INTERVAL_UNIT_IS_NULL";
    /**
     *  Task  Name已存在
     **/
    public static final String TAPE_INVENTORY_TASK_TASK_NAME_EXIST = "TAPE_INVENTORY_TASK_TASK_NAME_EXIST";
    /**
     * 盘点 Task 不存在
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_EXIST = "TAPE_INVENTORY_TASK_NOT_EXIST";
    /**
     * 盘点 Task 已取消
     **/
    public static final String TAPE_INVENTORY_TASK_CANCELED = "TAPE_INVENTORY_TASK_CANCELED";
    /**
     * 盘点 Task 不在”扫描中“
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_SCANNING = "TAPE_INVENTORY_TASK_END_SCAN";
    /**
     * 取消 Task Failed
     **/
    public static final String TAPE_INVENTORY_TASK_CANCEL_TASK_FAIL = "TAPE_INVENTORY_TASK_CANCEL_TASK_FAIL";
    /**
     * 开始 Time 必须在当前 Time 5分钟之后
     **/
    public static final String TAPE_INVENTORY_TASK_START_TIME_ERROR = "TAPE_INVENTORY_TASK_START_TIME_ERROR";
    /**
     * 盘点结果不存在！
     **/
    public static final String TAPE_INVENTORY_TASK_RESULT_NOT_EXISTENT = "TAPE_INVENTORY_TASK_RESULT_NOT_EXISTENT";
    /**
     *  Task 正在盘点中！
     **/
    public static final String TAPE_INVENTORY_TASK_ING = "TAPE_INVENTORY_TASK_ING";
    /**
     *  Task 没有差异！
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_DIFF = "TAPE_INVENTORY_TASK_NOT_DIFF";
    /**
     * 盘点 Task 已Update！
     **/
    public static final String TAPE_INVENTORY_TASK_UPDATED = "TAPE_INVENTORY_TASK_UPDATED";
    /**
     * 盘点结果不存在差异
     **/
    public static final String TAPE_INVENTORY_TASK_RESULT_NOT_DIFF = "TAPE_INVENTORY_TASK_RESULT_NOT_DIFF";
    /**
     * 盘点结果处理Failed
     **/
    public static final String TAPE_INVENTORY_RESULT_HANDING_ERROR = "TAPE_INVENTORY_RESULT_HANDING_ERROR";
    /**
     * 原因长度不能超过255个字符
     **/
    public static final String TAPE_INVENTORY_RESULT_REASON_LENGTH_LIMIT = "TAPE_INVENTORY_RESULT_REASON_LENGTH_LIMIT";
    /**
     * 机架存在正在盘点的 Task ， Task  Name：
     **/
    public static final String TAPE_INVENTORY_TASK_RACK_ING = "TAPE_INOUT_INVENTORY_ING";
    /**
     * 机架存在未处理的盘点差异， Task  Name：
     **/
    public static final String TAPE_INOUT_INVENTORY_DISCREPANCY = "TAPE_INOUT_INVENTORY_DISCREPANCY";
    /**
     * 盘点 Task 机架正在做入库，单号:
     **/
    public static final String TAPE_INVENTORY_RACK_INBOUND_ING = "TAPE_INVENTORY_RACK_INBOUND_ING";
    /**
     * 盘点 Task 机架正在做出库，单号:
     **/
    public static final String TAPE_INVENTORY_RACK_OUTBOUND_ING = "TAPE_INVENTORY_RACK_OUTBOUND_ING";
    /**
     * 机架有新的盘点 Task 执行差异自动取消, Task  Name：
     **/
    public static final String TAPE_INVENTORY_DISCREPANCY_CONFLICT = "TAPE_INVENTORY_DISCREPANCY_CONFLICT";
    /**
     * 盘点 Task 已启用
     **/
    public static final String TAPE_INVENTORY_TASK_ENABLED = "TAPE_INVENTORY_TASK_ENABLED";
    /**
     * 盘点 Task 已停用
     **/
    public static final String TAPE_INVENTORY_TASK_DISABLED = "TAPE_INVENTORY_TASK_DISABLED";
    /**
     * 盘点 Task 已结束
     **/
    public static final String TAPE_INVENTORY_TASK_END = "TAPE_INVENTORY_TASK_END";
    /**
     *  Task 禁用取消
     **/
    public static final String TAPE_INVENTORY_DISABLE_CANCEL = "TAPE_INVENTORY_DISABLE_CANCEL";
    /**
     * 盘点结果取消
     **/
    public static final String TAPE_INVENTORY_RESULT_CANCEL = "TAPE_INVENTORY_RESULT_CANCEL";
    /**
     * 盘点 Task 禁用Failed
     **/
    public static final String TAPE_INVENTORY_TASK_DISABLE_FAIL = "TAPE_INVENTORY_TASK_DISABLE_FAIL";
    /**
     * 盘点 Task 启用Failed
     **/
    public static final String TAPE_INVENTORY_TASK_ENABLE_FAIL = "TAPE_INVENTORY_TASK_ENABLE_FAIL";
    /**
     * 周期盘点 Task ，开始 Time 不能大于结束 Time 
     **/
    public static final String TAPE_INVENTORY_PERIODIC_TIME_ERROR = "TAPE_INVENTORY_PERIODIC_TIME_ERROR";
    /**
     * 结束 Time 五分钟之内，无法启用 Task 
     **/
    public static final String TAPE_INVENTORY_PERIODIC_END_TIME_ERROR = "TAPE_INVENTORY_PERIODIC_END_TIME_ERROR";

    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";
    public static final String VERIFICATION_CODE_ERROR = "VERIFICATION_CODE_ERROR";
    public static final String USER_NOT_TENANTID_EXIST = "USER_NOT_TENANTID_EXIST";
    public static final String USER_NOT_REALNAME_EXIST = "USER_NOT_REALNAME_EXIST";
    public static final String USER_NOT_ORG_EXIST = "USER_NOT_ORG_EXIST";
    public static final String USER_NOT_LOGINNAME_EXIST = "USER_NOT_LOGINNAME_EXIST";
    public static final String USER_NOT_GENDER_EXIST = "USER_NOT_GENDER_EXIST";
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
    public static final String USER_NOT_CELLPHONE_EXIST = "USER_NOT_CELLPHONE_EXIST";
    public static final String UPDATE_SUCCESSFUL = "UPDATE_SUCCESSFUL";
    public static final String TOKEN_RENEWED = "TOKEN_RENEWED";
    public static final String THERE_ARE_SUBSETS_UNDER_RESOURCES = "THERE_ARE_SUBSETS_UNDER_RESOURCES";
    public static final String TENANT_RESOURCE_NO_DACLEVEL = "TENANT_RESOURCE_NO_DACLEVEL";
    public static final String TENANT_NOT_EXIST = "TENANT_NOT_EXIST";
    public static final String TENANT_NAME_ERR = "TENANT_NAME_ERR";
    public static final String TENANT_APPLICATION_UNRELATED = "TENANT_APPLICATION_UNRELATED";
    public static final String TARGET_ORGANIZATION_NOT_EXIST = "TARGET_ORGANIZATION_NOT_EXIST";
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
    public static final String SUB_AREA_NOT_EXIST = "SUB_AREA_NOT_EXIST";
    public static final String SET_SUCCESSFUL = "SET_SUCCESSFUL";
    public static final String SET_FAILED = "SET_FAILED";
    public static final String ROLE_NOT_EXIST = "ROLE_NOT_EXIST";
    public static final String ROLE_NAME_NULL = "ROLE_NAME_NULL";
    public static final String ROLE_NAME_ERR = "ROLE_NAME_ERR";
    public static final String RESOURCE_NOT_EXIST = "RESOURCE_NOT_EXIST";
    public static final String RESOURCE_BOUND_TO_TENANT_APPLICATION = "RESOURCE_BOUND_TO_TENANT_APPLICATION";
    public static final String RESOURCE_ASSIGNED_TO_USER = "RESOURCE_ASSIGNED_TO_USER";
    public static final String RESOURCE_ASSIGNED_TO_ROLE = "RESOURCE_ASSIGNED_TO_ROLE";
    public static final String RESOURCE_ASSIGNED_TO_GROUP = "RESOURCE_ASSIGNED_TO_GROUP";
    public static final String RECOVERY_SUCCESSFUL = "RECOVERY_SUCCESSFUL";
    public static final String PERMISSION_NOT_ASSIGNED = "PERMISSION_NOT_ASSIGNED";
    public static final String PAUSE_SUCCESSFUL = "PAUSE_SUCCESSFUL";
    public static final String PASSWORD_UPDATE = "PASSWORD_UPDATE";
    public static final String PASSWORD_RESET = "PASSWORD_RESET";
    public static final String PASSWORD_ERROR = "PASSWORD_ERROR";
    public static final String PARENTID_INVALID = "PARENTID_INVALID";
    public static final String PARENT_RESOURCE_NOT_MENU = "PARENT_RESOURCE_NOT_MENU";
    public static final String PARENT_RESOURCE_NOT_EXIST = "PARENT_RESOURCE_NOT_EXIST";
    public static final String ORIGINAL_ORGANIZATION_NOT_EXIST = "ORIGINAL_ORGANIZATION_NOT_EXIST";
    public static final String ORGANIZATION_USER_ERR = "ORGANIZATION_USER_ERR";
    public static final String ORGANIZATION_TYPE_ERR = "ORGANIZATION_TYPE_ERR";
    public static final String ORGANIZATION_NAME_NULL = "ORGANIZATION_NAME_NULL";
    public static final String ORGANIZATION_NAME_ERR = "ORGANIZATION_NAME_ERR";
    public static final String ORGANIZATION_CHILDREN_ERR = "ORGANIZATION_CHILDREN_ERR";
    public static final String OPERATION_SUCESSFUL = "OPERATION_SUCESSFUL";
    public static final String OPERATIN_SUCESSFUL = "OPERATIN_SUCESSFUL";
    public static final String NONCE_NOT_EXIST = "NONCE_NOT_EXIST";
    public static final String LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String LOGIN_FAIL = "LOGIN_FAIL";
    public static final String LOGIN_ACCOUNT_EXIST = "LOGIN_ACCOUNT_EXIST";
    public static final String JOB_EXIST = "JOB_EXIST";
    public static final String JOB_CLASS_NOT_EXIST = "JOB_CLASS_NOT_EXIST";
    public static final String INVALID_VERIFICATION_CODE = "INVALID_VERIFICATION_CODE";
    public static final String INVALID_USER_STATUS = "INVALID_USER_STATUS";
    public static final String INVALID_TENANT_ID = "INVALID_TENANT_ID";
    public static final String INVALID_ROLE_LIST = "INVALID_ROLE_LIST";
    public static final String INVALID_RESOURCE_URL = "INVALID_RESOURCE_URL";
    public static final String INVALID_RESOURCE_TYPE = "INVALID_RESOURCE_TYPE";
    public static final String INVALID_RESOURCE_STATUS = "INVALID_RESOURCE_STATUS";
    public static final String INVALID_RESOURCE_ORDER = "INVALID_RESOURCE_ORDER";
    public static final String INVALID_RESOURCE_ICON = "INVALID_RESOURCE_ICON";
    public static final String INVALID_RESOURCE_ATTRIBUTE = "INVALID_RESOURCE_ATTRIBUTE";
    public static final String INVALID_REAL_NAME = "INVALID_REAL_NAME";
    public static final String INVALID_PERMISSION_NAME = "INVALID_PERMISSION_NAME";
    public static final String INVALID_ORGANIZATION_CODE = "INVALID_ORGANIZATION_CODE";
    public static final String INVALID_LOGIN_NAME = "INVALID_LOGIN_NAME";
    public static final String INVALID_GROUP_LIST = "INVALID_GROUP_LIST";
    public static final String INVALID_GENDER = "INVALID_GENDER";
    public static final String INVALID_DA_VISITOR_TYPE = "INVALID_DA_VISITOR_TYPE";
    public static final String INVALID_DA_VISIBLE_SCOPE_TYPE = "INVALID_DA_VISIBLE_SCOPE_TYPE";
    public static final String INVALID_DA_NULL_VISITOR = "INVALID_DA_NULL_VISITOR";
    public static final String INVALID_DA_NULL_VISIBLE_SCOPE = "INVALID_DA_NULL_VISIBLE_SCOPE";
    public static final String INVALID_CREATOR = "INVALID_CREATOR";
    public static final String INVALID_COMMON_DATA_ACCESS_FLAG = "INVALID_COMMON_DATA_ACCESS_FLAG";
    public static final String INVALID_CELL_PHONE = "INVALID_CELL_PHONE";
    public static final String INVALID_ACCESS_TYPE = "INVALID_ACCESS_TYPE";
    public static final String GROUP_NOT_EXIST = "GROUP_NOT_EXIST";
    public static final String GROUP_NAME_NULL = "GROUP_NAME_NULL";
    public static final String GROUP_NAME_ERR = "GROUP_NAME_ERR";
    public static final String FILE_NOT_EXIST = "FILE_NOT_EXIST";
    public static final String ERROR_INSERTING_DATABASE = "ERROR_INSERTING_DATABASE";
    public static final String EMPTY_USER_ID = "EMPTY_USER_ID";
    public static final String EMPTY_TENANT_NAME = "EMPTY_TENANT_NAME";
    public static final String EMPTY_TENANT_LIASON_PHONE = "EMPTY_TENANT_LIASON_PHONE";
    public static final String EMPTY_TENANT_LIASON = "EMPTY_TENANT_LIASON";
    public static final String EMPTY_TENANT_ID = "EMPTY_TENANT_ID";
    public static final String EMPTY_REAL_NAME = "EMPTY_REAL_NAME";
    public static final String EMPTY_PHONE_NUMBER = "EMPTY_PHONE_NUMBER";
    public static final String EMPTY_PERMISSION_ID = "EMPTY_PERMISSION_ID";
    public static final String EMPTY_PARENT_TENANT_ID = "EMPTY_PARENT_TENANT_ID";
    public static final String MPTY_PARAMETER = "EMPTY_PARAMETER";
    public static final String EMPTY_GENDER = "EMPTY_GENDER";
    public static final String EMPTY_APPLICATION_ID = "EMPTY_APPLICATION_ID";
    public static final String DELETE_SUCCESSFUL = "DELETE_SUCCESSFUL";
    public static final String DATAPERMISSION_NOT_EXIST = "DATAPERMISSION_NOT_EXIST";
    public static final String COMMONDICTTYPE_NOT_EXIST = "COMMONDICTTYPE_NOT_EXIST";
    public static final String APPLICATION_SWITCHED = "APPLICATION_SWITCHED";
    public static final String APPLICATION_NOT_EXIST = "APPLICATION_NOT_EXIST";
    public static final String APPLICATION_EXISTS = "APPLICATION_EXISTS";
    public static final String USER_APPLICATION_NOT_UNDER_SAME_TENANT = "USER_APPLICATION_NOT_UNDER_SAME_TENANT";
    public static final String USER_DOES_NOT_HAVE_APPLICATION = "USER_DOES_NOT_HAVE_APPLICATION";


    //登录Failed,订单不存在,已过期或已终止,请确认
    public static final String USER_LOGIN_ORDER_IS_NOT_EXIST = "USER_LOGIN_ORDER_IS_NOT_EXIST";
    //程序错误
    public static final String TAPE_LABEL_PROGRAM_ERROR = "TAPE_LABEL_PROGRAM_ERROR";
    //上传文件错误
    public static final String TAPE_LABEL_UPLOAD_FILE_ERROR = "TAPE_LABEL_UPLOAD_FILE_ERROR";
    // Export  Data 错误
    public static final String TAPE_LABEL_EXPORT_DATA_ERROR = "TAPE_LABEL_EXPORT_DATA_ERROR";
    //Download错误download
    public static final String TAPE_LABEL_DOWNLOAD_FILE_ERROR = "TAPE_LABEL_DOWNLOAD_FILE_ERROR";
    // Data 不存在
    public static final String TAPE_LABEL_DATA_NOT_EXIST = "TAPE_LABEL_DATA_NOT_EXIST";
    //保存错误
    public static final String TAPE_LABEL_SAVE_ERROR = "TAPE_LABEL_SAVE_ERROR";

    //保存错误, Data 已存在
    public static final String TAPE_LABEL_SAVE_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_DATA_ALREADY_EXISTS";

    public static final String TAPE_LABEL_SAVE_LOCATION_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_LOCATION_DATA_ALREADY_EXISTS";
    public static final String TAPE_LABEL_SAVE_RANK_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_RANK_DATA_ALREADY_EXISTS";
    // Data 存在关联
    public static final String TAPE_LABEL_DATA_DISABLED_ALREADY = "TAPE_LABEL_DATA_DISABLED_ALREADY";

    // Data 已禁用
    public static final String TAPE_LABEL_DATA_DISABLED_EXISTS = "TAPE_LABEL_DATA_DISABLED_EXISTS";
    public static final String TAPE_LABEL_RACK_DATA_DISABLED_EXISTS = "TAPE_LABEL_RACK_DATA_DISABLED_EXISTS";
    // Data 存在关联
    public static final String TAPE_LABEL_DATA_ASSOCIATION_ALREADY = "TAPE_LABEL_DATA_ASSOCIATION_ALREADY";
    //已有天线的读写器不可删除
    public static final String TAPE_LABEL_DATA_ASSOCIATION_ALREADY_ISNOT_DELETE = "TAPE_LABEL_DATA_ASSOCIATION_ALREADY_ISNOT_DELETE";
    public static final String NETWORK_DISRUPTION = "NETWORK_DISRUPTION";
    public static final String DATA_BACKUP_EXCEPTION = "DATA_BACKUP_EXCEPTION";
    public static final String INVALID_DATA_ID = "INVALID_DATA_ID";
    public static final String USER_NOT_ACTIVE = "USER_NOT_ACTIVE";


    //人数结束 Quantity必须大于开始 Quantity For the user number the upper bound must be greater or equal to lower bound
    public static final String ORDER_USER_NUMBER_MUSTBE = "ORDER_USER_NUMBER_MUSTBE";
    //使用人数配置重叠，请调整 there are overlaps between time frames of user number configuration, please adjust.
    public static final String ORDER_USER_NUMBER_OVERLAP = "ORDER_USER_NUMBER_OVERLAP";

    //订单不存在 Order Not Exist
    public static final String ORDER_DOES_NOT_EXIST = "ORDER_DOES_NOT_EXIST";
    //选择到期日期必须大于订单到期日期 The selected end date must be greater or equal to the order end date
    public static final String ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE = "ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE";

    //无法删除,存在订单用户; Cannot delete user with order.
    public static final String ORDER_UNABLE_DELETE_PRESENT_ORDER = "ORDER_UNABLE_DELETE_PRESENT_ORDER";

    //开通账号数范围超出产品设置 Quantity Number of accounts exceeded limit
    public static final String ORDER_ACCOUNTS_NUMBER_RANGE_OUT = "ORDER_ACCOUNTS_NUMBER_RANGE_OUT";

    //使用人数配置,开始人数不能小于结束人数 User Number Configuration, upper bound must be greater or equal to lower bound.
    public static final String ORDER_USING_NUMBER_CONFIG_THAN_ENDING_NUMBER = "ORDER_USING_NUMBER_CONFIG_THAN_ENDING_NUMBER";

    //使用人数配置重叠，请调整 There are overlaps between the time frames of user number configuration.
    public static final String ORDER_USING_NUMBER_CONFIG_OVERLAPS = "ORDER_USING_NUMBER_CONFIG_OVERLAPS";

    //到期日期重叠，请调整 There are overlaps between the time frames of expiration data configuration.
    public static final String ORDER_EXPIRATION_DATA_OVERLAPS = "ORDER_EXPIRATION_DATA_OVERLAPS";
    //套餐使用中，无法编辑；The suite is in use, cannot be changed
    public static final String ORDER_SUITE_USED_NOT_EDIT = "ORDER_SUITE_USED_NOT_EDIT";

    //合同编号已存在 Contract Code is Exist
    public static final String ORDER_CONTRACT_IS_EXIST = "ORDER_CONTRACT_IS_EXIST";

    //套餐 Name已使用；The suite name is in use;
    public static final String ORDER_SUITE_NAME_IS_USED = "ORDER_SUITE_NAME_IS_USED";


    // Tenant 用户 Quantity超出订单范围；The number of users has exceeded the limit per license.
    public static final String TENANT_USE_NUMBER_RANGE_OUT = "TENANT_USE_NUMBER_RANGE_OUT";

    //有效期必须大于当前 Time  The validity period must be greater than the current time
    public static final String ORDER_EXPIRATION_DATA_THAN_NOW = "ORDER_EXPIRATION_DATA_THAN_NOW";


    //开通账号数必须大于同 Tenant 开通账户数总和 The number of allowed account must be than less suite user number
    public static final String ORDER_ACCOUNTS_NUMBER_THAN_SAME_ACCOUNTS_NUMBER = "ORDER_ACCOUNTS_NUMBER_THAN_SAME_ACCOUNTS_NUMBER";

    //使用人数必须大于同 Tenant 开通账户数总和 The user number  must be greater less number of allowed account
    public static final String ORDER_USER_NUMBER_THAN_SAME_ACCOUNTS_NUMBER = "ORDER_USER_NUMBER_THAN_SAME_ACCOUNTS_NUMBER";

    //货物 Name不能重复
    public static final String GOODS_NAME_NOT_REPEAT = "GOODS_NAME_NOT_REPEAT";
    //货物编码不能重复
    public static final String GOODS_CODE_NOT_REPEAT = "GOODS_CODE_NOT_REPEAT";

    // Rule  Name不能重复
    public static final String RULES_NAME_NOT_REPEAT = "RULES_NAME_NOT_REPEAT";
    // Device 型号不能重复
    public static final String DEVICE_MODEL_NOT_REPEAT = "DEVICE_MODEL_NOT_REPEAT";
    //  Device Type 不存在
    public static final String DEVICE_TYPE_NOT_EXIST = "DEVICE_TYPE_NOT_EXIST";
    //  Device 编码不能重复
    public static final String DEVICE_CODE_NOT_REPEAT = "DEVICE_CODE_NOT_REPEAT";
    //  Device Type - Data 字典Type -不存在
    public static final String DEVICE_TYPE_CODE_DICT_TYPE_NOT_EXIST = "DEVICE_TYPE_CODE_DICT_TYPE_NOT_EXIST";
    //  Device Type - Data 字典-不存在
    public static final String DEVICE_TYPE_CODE_DICT_NOT_EXIST = "DEVICE_TYPE_CODE_DICT_NOT_EXIST";
    // app客户端Type - Data 字典Type -不存在
    public static final String APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST = "APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST";
    // app客户端Type - Data 字典-不存在
    public static final String APP_UPGRADE_TYPE_DICT_NOT_EXIST = "APP_UPGRADE_TYPE_DICT_NOT_EXIST";
    //  Device 编码长度不能超过200字符
    public static final String DEVICE_CODE_LENGTH_CANNOT_EXCEED = "DEVICE_CODE_LENGTH_CANNOT_EXCEED: 200";
    //  Device 功率长度不能超过100字符
    public static final String DEVICE_DBM_LENGTH_CANNOT_EXCEED = "DEVICE_DBM_LENGTH_CANNOT_EXCEED: 100";
    //  Device IP长度不能超过50
    public static final String DEVICE_IP_LENGTH_CANNOT_EXCEED = "DEVICE_IP_LENGTH_CANNOT_EXCEED: 50";
    //  Device max地址不能超过200
    public static final String DEVICE_MAX_LENGTH_CANNOT_EXCEED = "DEVICE_MAX_LENGTH_CANNOT_EXCEED: 200";
    //  Device 经度长度不能超过100
    public static final String DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED = "DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED: 100";
    //  Device 纬度长度不能超过 100
    public static final String DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED = "DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED: 100";
    //  Device 安装地址长度不能超过200
    public static final String DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED = "DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED: 200";
    //  Label ID不能重复
    public static final String BASE_RFID_NOT_REPEAT = "BASE_RFID_NOT_REPEAT";
    //货物ID不能重复
    public static final String BASE_GOODSID_NOT_REPEAT = "BASE_GOODSID_NOT_REPEAT";
    //EPC不能重复
    public static final String BASE_EPC_NOT_REPEAT = "BASE_EPC_NOT_REPEAT";
    // TID 不能重复
    public static final String BASE_TID_NOT_REPEAT = "BASE_TID_NOT_REPEAT";
    //  Label 不存在
    public static final String BASE_RFID_INFO_NOT_EXIST = "BASE_RFID_INFO_NOT_EXIST";
    //  Device 升级包不存在
    public static final String DEVICE_PACKAGE_NOT_EXIST = "DEVICE_PACKAGE_NOT_EXIST";
    // Device  Rule 配置不能重复
    public static final String DEVICE_RULE_NOT_REPEAT = "DEVICE_RULE_NOT_REPEAT";
    public static final String KEYCLOAK_TOKEN_NOT_EXIST = "KEYCLOAK_TOKEN_NOT_EXIST";
    public static final String IOT_INVOCATION_FAILED = "IOT_INVOCATION_FAILED";
    // Device Type 已经被使用，不能删除
    public static final String BASE_DEVICE_TYPE_USED = "BASE_DEVICE_TYPE_USED";
    // Device  Rule  Detail Data 不存在
    public static final String DEVICE_RULE_DETAIL_NOT_EXIST = "DEVICE_RULE_DETAIL_NOT_EXIST";
    // xlsx 文件格式不正确
    public static final String XLSX_FILE_INCORRECT_FORMAT = "XLSX_FILE_INCORRECT_FORMAT";
    // 编码 Rule 已经被使用，不能删除
    public static final String BASE_CODING_RULES_USED = "BASE_CODING_RULES_USED";
    // Device 功率值不正确
    public static final String BASE_DEVICE_DBM_VALUE_INCORRECT = "BASE_DEVICE_DBM_VALUE_INCORRECT";
    // Device ip地址不能重复
    public static final String BASE_DEVICE_IP_REPEAT = "BASE_DEVICE_IP_REPEAT";
    // Device mac地址不能重复
    public static final String BASE_DEVICE_MAC_REPEAT = "BASE_DEVICE_MAC_REPEAT";
    //登录超时退出
    public static final String LOGIN_TIMED_OUT = "LOGIN_TIMED_OUT";
    //这个资源存在子项
    public static final String THIS_RESOURCE_HAS_SUB_SETS = "THIS_RESOURCE_HAS_SUB_SETS";
    //角色已经被引用，不能删除
    public static final String USER_ROLE_USED = "USER_ROLE_USED";
    //编辑用户Failed
    public static final String EDIT_USER_FAILD = "EDIT_USER_FAILD";
    //用户已经存在
    public static final String USER_EXISTED = "USER_EXISTED";
    //token超时
    public static final String TOKEN_TIMEOUT = "TOKEN_TIMEOUT";
    // Device 已经被使用，不能删除
    public static final String BASE_DEVICE_USED = "BASE_DEVICE_USED";
    // Device 不存在
    public static final String BASE_DEVICE_NOT_EXIST = "BASE_DEVICE_NOT_EXIST";
    //同一机构下，不能存在相同小组
    public static final String USER_GROUP_NOT_REPEAT = "USER_GROUP_NOT_REPEAT";
    //组名不能重复
    public static final String USER_GROUP_NAME_NOT_REPEAT = "USER_GROUP_NAME_NOT_REPEAT";
    //组织机构已经被用户绑定，不能删除
    public static final String ORG_CODE_USED_BY_USER = "ORG_CODE_USED_BY_USER";
    //存在相同产品
    public static final String SAME_PRODUCT_IS_EXIST = "SAME_PRODUCT_IS_EXIST";

    public static final String PRODUCT_EXPIRING_DAY_NOT_EXIST = "PRODUCT_EXPIRING_DAY_NOT_EXIST";
    //开通账号数不在产品设置范围内
    public static final String ACCOUNT_NUMBER_NOT_RIGHT = "ACCOUNT_NUMBER_NOT_RIGHT";
    // 小组已经归属其它组织机构
    public static final String USER_GROUP_ORGANIZATION_IS_EXIST = "USER_GROUP_ORGANIZATION_IS_EXIST";
    // Tenant 没有购买订单
    public static final String TENANT_HAVA_NOT_ORDER = "TENANT_NOT_HAVA_ORDER";
    // Tenant 订单已经终止或者过期，不能登录
    public static final String USER_ORDER_END_NOT_LOGIN = "USER_ORDER_END_NOT_LOGIN";
    // Item 已经被使用
    public static final String GOODS_USED = "GOODS_USED";
    //字段 Name不能重复
    public static final String FIELD_NAME_NOT_REPEAT = "FIELD_NAME_NOT_REPEAT";
    // Item Batch Number不能重复
    public static final String GOODS_BATCHCODE_NOT_REPEAT = "GOODS_BATCHCODE_NOT_REPEAT";
    // Rule 已经被使用，不能编辑
    public static final String RULE_USED_CANT_NOT_EDIT = "RULE_USED_CANT_NOT_EDIT";
    // Device 升级Failed
    public static final String DEVICE_UPGRAD_FAIL = "DEVICE_UPGRAD_FAIL";
    //非管理员不能分配 编码 Rule 菜单权限
    public static final String CODINGRULES_NOT_ALLOCATION = "CODINGRULES_NOT_ALLOCATION";
    // Export  Data 超过五万
    public static final String EXPORT_DATA_LENG_OUT_SIZE = "EXPORT_DATA_LENG_OUT_SIZE";
    //tid不能与epc相等
    public static final String TID_CANNOT_BE_EQUAL_TO_EPC = "TID_CANNOT_BE_EQUAL_TO_EPC";
}
