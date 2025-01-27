package com.matariky.commonservice.upload.constant;

public class MessageKey {
    /**
     * Remark length Can't exceed 255 characters
     **/
    public static final String COMMON_REMARK_LENGTH_LIMIT = "COMMON_REMARK_LENGTH_LIMIT";
    /**
     * Status Be empty
     **/
    public static final String COMMON_STATUS_IS_NULL = "COMMON_STATUS_IS_NULL";
    /**
     * Status illegal
     **/
    public static final String COMMON_STATUS_ILLEGAL = "COMMON_STATUS_ILLEGAL";
    /**
     * Date range is empty
     **/
    public static final String COMMON_DATE_RANGE_IS_NULL = "COMMON_DATE_RANGE_IS_NULL";
    /**
     * Date range differences exceeding restrictions
     **/
    public static final String COMMON_DATE_RANGE_DIFF_MONTH_LIMIT = "COMMON_DATE_RANGE_DIFF_MONTH_LIMIT";
    /**
     * ImageThe size cannot exceed%sM
     **/
    public static final String COMMON_IMAGE_SIZE_LIMIT = "COMMON_IMAGE_SIZE_LIMIT";
    /**
     * Image Format Error
     **/
    public static final String COMMON_FILE_NOT_IMAGE = "COMMON_FILE_NOT_IMAGE";
    /**
     * At most, you can only upload%s Image
     **/
    public static final String COMMON_UPLOAD_IMAGE_LIMIT = "COMMON_UPLOAD_IMAGE_LIMIT";
    /**
     * Data Expired
     **/
    public static final String COMMON_DATA_EXPIRE = "COMMON_DATA_EXPIRE";
    /**
     * The location is empty
     **/
    public static final String TAPE_LOCATION_IS_NULL = "TAPE_LOCATION_IS_NULL";
    /**
     * Library No existence
     **/
    public static final String TAPE_LIBRARY_NOT_EXISTENT = "TAPE_LOCATION_LIBRARY_NOT_EXISTENT";
    /**
     * Library In the inventory
     **/
    public static final String TAPE_LIBRARY_INVENTORY_IN_PROGRESS = "TAPE_LIBRARY_INVENTORY_IN_PROGRESS";
    /**
     * Library In the warehouse
     **/
    public static final String TAPE_LIBRARY_STOCK_IN_PROGRESS = "TAPE_LIBRARY_STOCK_IN_PROGRESS";
    /**
     * Library In the warehouse
     **/
    public static final String TAPE_LIBRARY_STOCK_OUT_PROGRESS = "TAPE_LIBRARY_STOCK_OUT_PROGRESS";
    /**
     * In/Out Library Type Be empty
     **/
    public static final String TAPE_INOUT_TYPE_IS_NULL = "TAPE_INOUT_TYPE_IS_NULL";
    /**
     * In/Out Library Type illegal
     **/
    public static final String TAPE_INOUT_TYPE_ILLEGAL = "TAPE_INOUT_TYPE_ILLEGAL";
    /**
     * There is no tape that can be out of the warehouse
     **/
    public static final String TAPE_INOUT_OUT_EMPTY_STOCK = "TAPE_INOUT_OUT_EMPTY_STOCK";
    /**
     * Tape out of the warehouse Failed ,Check, please Data
     **/
    public static final String TAPE_INOUT_OUT_FAIL = "TAPE_INOUT_OUT_FAIL";
    /**
     * Tape into warehouse Failed ,Check, please Data
     **/
    public static final String TAPE_INOUT_IN_FAIL = "TAPE_INOUT_IN_FAIL";
    /**
     * Library In/Out In progress
     **/
    public static final String TAPE_LIBRARY_START_INOUT = "TAPE_LIBRARY_START_INOUT";
    /**
     * Library unbinding Reader
     **/
    public static final String TAPE_LIBRARY_NOT_BIND_READER = "TAPE_LIBRARY_NOT_BIND_READER";
    /**
     * Library "%s"Unbinding Reader
     **/
    public static final String TAPE_LIBRARY_NOT_BIND_READER_SINGLE = "TAPE_LIBRARY_NOT_BIND_READER_SINGLE";
    /**
     * Record No existence
     **/
    public static final String BIZ_RECORD_NON_EXISTENT = "BIZ_RECORD_NON_EXISTENT";
    /**
     * Task Canceled
     **/
    public static final String BIZ_TASK_CANCELED = "BIZ_TASK_CANCELED";
    /**
     * Task Ended
     **/
    public static final String BIZ_TASK_ENDED = "BIZ_TASK_ENDED";
    /**
     * No Scan Label Found or Data Expired
     **/
    public static final String TAPE_INOUT_IN_SCAN_LABEL_EMPTY_OR_EXPIRE = "TAPE_INOUT_IN_SCAN_LABEL_EMPTY_OR_EXPIRE";
    /**
     * No Outbound Label Found or Data Expired
     **/
    public static final String TAPE_INOUT_OUT_SCAN_LABEL_EMPTY_OR_EXPIRE = "TAPE_INOUT_OUT_SCAN_LABEL_EMPTY_OR_EXPIRE";
    /**
     * Outbound Failed, Stock Is Empty
     **/
    public static final String TAPE_INOUT_OUT_STOCK_IS_NULL = "TAPE_INOUT_OUT_STOCK_IS_NULL";
    /**
     * Outbound Failed, Label Error
     **/
    public static final String TAPE_INOUT_OUT_LABEL_ERROR = "TAPE_INOUT_OUT_LABEL_ERROR";
    /**
     * Inbound Failed, Label Error
     **/
    public static final String TAPE_INOUT_IN_LABEL_ERROR = "TAPE_INOUT_IN_LABEL_ERROR";
    /**
     * Outbound Quantity Greater Than Stock Quantity
     **/
    public static final String TAPE_OUTBOUND_QUANTITY_GT_STOCK_QUANTITY = "TAPE_OUTBOUND_QUANTITY_GT_STOCK_QUANTITY";
    /**
     * Task Name Is Empty
     **/
    public static final String TAPE_INVENTORY_TASK_NAME_IS_NULL = "TAPE_INVENTORY_TASK_NAME_IS_NULL";
    /**
     * Task Name Length Exceeds 50 Characters
     **/
    public static final String TAPE_INVENTORY_TASK_NAME_LENGTH_LIMIT = "TAPE_INVENTORY_TASK_NAME_LENGTH_LIMIT";
    /**
     * Task Type Is Empty
     **/
    public static final String TAPE_INVENTORY_TASK_TYPE_IS_NULL = "TAPE_INVENTORY_TASK_TYPE_IS_NULL";
    /**
     * Task Type Is Illegal
     **/
    public static final String TAPE_INVENTORY_TASK_TYPE_ILLEGAL = "TAPE_INVENTORY_TASK_TYPE_ILLEGAL";
    /**
     * Task Date Is Empty
     **/
    public static final String TAPE_INVENTORY_TASK_DATE_IS_NULL = "TAPE_INVENTORY_TASK_DATE_IS_NULL";
    /**
     * Time Interval Is Empty
     **/
    public static final String TAPE_INVENTORY_TASK_TIME_INTERVAL_IS_NULL = "TAPE_INVENTORY_TASK_TIME_INTERVAL_IS_NULL";
    /**
     * Time Interval Unit Is Empty
     **/
    public static final String TAPE_INVENTORY_TASK_INTERVAL_UNIT_IS_NULL = "TAPE_INVENTORY_TASK_INTERVAL_UNIT_IS_NULL";
    /**
     * Task Name Already Exists
     **/
    public static final String TAPE_INVENTORY_TASK_TASK_NAME_EXIST = "TAPE_INVENTORY_TASK_TASK_NAME_EXIST";
    /**
     * Inventory Task Does Not Exist
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_EXIST = "TAPE_INVENTORY_TASK_NOT_EXIST";
    /**
     * Inventory Task Canceled
     **/
    public static final String TAPE_INVENTORY_TASK_CANCELED = "TAPE_INVENTORY_TASK_CANCELED";
    /**
     * Inventory Task Not Scanning
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_SCANNING = "TAPE_INVENTORY_TASK_END_SCAN";
    /**
     * Cancel Task Failed
     **/
    public static final String TAPE_INVENTORY_TASK_CANCEL_TASK_FAIL = "TAPE_INVENTORY_TASK_CANCEL_TASK_FAIL";
    /**
     * Start Time Must Be 5 Minutes After Current Time
     **/
    public static final String TAPE_INVENTORY_TASK_START_TIME_ERROR = "TAPE_INVENTORY_TASK_START_TIME_ERROR";
    /**
     * Inventory Result Does Not Exist!
     **/
    public static final String TAPE_INVENTORY_TASK_RESULT_NOT_EXISTENT = "TAPE_INVENTORY_TASK_RESULT_NOT_EXISTENT";
    /**
     * Task Is In Progress!
     **/
    public static final String TAPE_INVENTORY_TASK_ING = "TAPE_INVENTORY_TASK_ING";
    /**
     * Task Has No Differences!
     **/
    public static final String TAPE_INVENTORY_TASK_NOT_DIFF = "TAPE_INVENTORY_TASK_NOT_DIFF";
    /**
     * Inventory Task Updated!
     **/
    public static final String TAPE_INVENTORY_TASK_UPDATED = "TAPE_INVENTORY_TASK_UPDATED";
    /**
     * Inventory Result Has No Differences
     **/
    public static final String TAPE_INVENTORY_TASK_RESULT_NOT_DIFF = "TAPE_INVENTORY_TASK_RESULT_NOT_DIFF";
    /**
     * Inventory Result Handling Failed
     **/
    public static final String TAPE_INVENTORY_RESULT_HANDING_ERROR = "TAPE_INVENTORY_RESULT_HANDING_ERROR";
    /**
     * Reason Length Cannot Exceed 255 Characters
     **/
    public static final String TAPE_INVENTORY_RESULT_REASON_LENGTH_LIMIT = "TAPE_INVENTORY_RESULT_REASON_LENGTH_LIMIT";
    /**
     * Library Has Ongoing Inventory Task, Task Name:
     **/
    public static final String TAPE_INVENTORY_TASK_RACK_ING = "TAPE_INOUT_INVENTORY_ING";
    /**
     * Library Has Unresolved Inventory Discrepancies, Task Name:
     **/
    public static final String TAPE_INOUT_INVENTORY_DISCREPANCY = "TAPE_INOUT_INVENTORY_DISCREPANCY";
    /**
     * Inventory Task Library Has Inbound In Progress, Order Number:
     **/
    public static final String TAPE_INVENTORY_RACK_INBOUND_ING = "TAPE_INVENTORY_RACK_INBOUND_ING";
    /**
     * Inventory Task Library Has Outbound In Progress, Order Number:
     **/
    public static final String TAPE_INVENTORY_RACK_OUTBOUND_ING = "TAPE_INVENTORY_RACK_OUTBOUND_ING";
    /**
     * New Inventory Task Execution Conflict, Task Name:
     **/
    public static final String TAPE_INVENTORY_DISCREPANCY_CONFLICT = "TAPE_INVENTORY_DISCREPANCY_CONFLICT";
    /**
     * Inventory Task Activated
     **/
    public static final String TAPE_INVENTORY_TASK_ENABLED = "TAPE_INVENTORY_TASK_ENABLED";
    /**
     * Inventory Task Deactivated
     **/
    public static final String TAPE_INVENTORY_TASK_DISABLED = "TAPE_INVENTORY_TASK_DISABLED";
    /**
     * Inventory Task Ended
     **/
    public static final String TAPE_INVENTORY_TASK_END = "TAPE_INVENTORY_TASK_END";
    /**
     * Task Disable Cancel
     **/
    public static final String TAPE_INVENTORY_DISABLE_CANCEL = "TAPE_INVENTORY_DISABLE_CANCEL";
    /**
     * Inventory Result Cancel
     **/
    public static final String TAPE_INVENTORY_RESULT_CANCEL = "TAPE_INVENTORY_RESULT_CANCEL";
    /**
     * Inventory Task Disable Failed
     **/
    public static final String TAPE_INVENTORY_TASK_DISABLE_FAIL = "TAPE_INVENTORY_TASK_DISABLE_FAIL";
    /**
     * Inventory Task Activation Failed
     **/
    public static final String TAPE_INVENTORY_TASK_ENABLE_FAIL = "TAPE_INVENTORY_TASK_ENABLE_FAIL";
    /**
     * Periodic Inventory Task, Start Time Cannot Be Greater Than End Time
     **/
    public static final String TAPE_INVENTORY_PERIODIC_TIME_ERROR = "TAPE_INVENTORY_PERIODIC_TIME_ERROR";
    /**
     * End Time Within Five Minutes, Cannot Activate Task
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

    // Login Failed, order does not exist, has expired, or has been terminated,
    // please confirm
    public static final String USER_LOGIN_ORDER_IS_NOT_EXIST = "USER_LOGIN_ORDER_IS_NOT_EXIST";
    // Program Error
    public static final String TAPE_LABEL_PROGRAM_ERROR = "TAPE_LABEL_PROGRAM_ERROR";
    // Upload file Error
    public static final String TAPE_LABEL_UPLOAD_FILE_ERROR = "TAPE_LABEL_UPLOAD_FILE_ERROR";
    // Export Data Error
    public static final String TAPE_LABEL_EXPORT_DATA_ERROR = "TAPE_LABEL_EXPORT_DATA_ERROR";
    // Download Error
    public static final String TAPE_LABEL_DOWNLOAD_FILE_ERROR = "TAPE_LABEL_DOWNLOAD_FILE_ERROR";
    // Data does not exist
    public static final String TAPE_LABEL_DATA_NOT_EXIST = "TAPE_LABEL_DATA_NOT_EXIST";
    // Save Error
    public static final String TAPE_LABEL_SAVE_ERROR = "TAPE_LABEL_SAVE_ERROR";
    // Save Error, Data already exists
    public static final String TAPE_LABEL_SAVE_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_DATA_ALREADY_EXISTS";

    public static final String TAPE_LABEL_SAVE_LOCATION_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_LOCATION_DATA_ALREADY_EXISTS";
    public static final String TAPE_LABEL_SAVE_RANK_DATA_ALREADY_EXISTS = "TAPE_LABEL_SAVE_RANK_DATA_ALREADY_EXISTS";
    // Data has associations
    public static final String TAPE_LABEL_DATA_DISABLED_ALREADY = "TAPE_LABEL_DATA_DISABLED_ALREADY";
    // Data has been disabled
    public static final String TAPE_LABEL_DATA_DISABLED_EXISTS = "TAPE_LABEL_DATA_DISABLED_EXISTS";
    public static final String TAPE_LABEL_RACK_DATA_DISABLED_EXISTS = "TAPE_LABEL_RACK_DATA_DISABLED_EXISTS";
    // Data has associations
    public static final String TAPE_LABEL_DATA_ASSOCIATION_ALREADY = "TAPE_LABEL_DATA_ASSOCIATION_ALREADY";
    // Reader with antenna cannot be deleted
    public static final String TAPE_LABEL_DATA_ASSOCIATION_ALREADY_ISNOT_DELETE = "TAPE_LABEL_DATA_ASSOCIATION_ALREADY_ISNOT_DELETE";

    // Number of users end quantity must be greater than start quantity
    public static final String ORDER_USER_NUMBER_MUSTBE = "ORDER_USER_NUMBER_MUSTBE";
    // User number configuration overlaps, please adjust
    public static final String ORDER_USER_NUMBER_OVERLAP = "ORDER_USER_NUMBER_OVERLAP";

    // Order does not exist
    public static final String ORDER_DOES_NOT_EXIST = "ORDER_DOES_NOT_EXIST";
    // Selected expiration date must be greater than or equal to order expiration
    // date
    public static final String ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE = "ORDER_SELECTION_DATE_THAN_EXPIRATION_DATE";

    // Cannot delete, order exists
    public static final String ORDER_UNABLE_DELETE_PRESENT_ORDER = "ORDER_UNABLE_DELETE_PRESENT_ORDER";

    // Number of allowed accounts exceeds the configuration range
    public static final String ORDER_ACCOUNTS_NUMBER_RANGE_OUT = "ORDER_ACCOUNTS_NUMBER_RANGE_OUT";

    // User number configuration, start number must not be less than end number
    public static final String ORDER_USING_NUMBER_CONFIG_THAN_ENDING_NUMBER = "ORDER_USING_NUMBER_CONFIG_THAN_ENDING_NUMBER";

    // User number configuration overlaps, please adjust
    public static final String ORDER_USING_NUMBER_CONFIG_OVERLAPS = "ORDER_USING_NUMBER_CONFIG_OVERLAPS";

    // Expiration date overlaps, please adjust
    public static final String ORDER_EXPIRATION_DATA_OVERLAPS = "ORDER_EXPIRATION_DATA_OVERLAPS";
    // Suite in use, cannot update
    public static final String ORDER_SUITE_USED_NOT_EDIT = "ORDER_SUITE_USED_NOT_EDIT";

    // Contract Code already exists
    public static final String ORDER_CONTRACT_IS_EXIST = "ORDER_CONTRACT_IS_EXIST";

    // Suite name already in use
    public static final String ORDER_SUITE_NAME_IS_USED = "ORDER_SUITE_NAME_IS_USED";

    // Tenant user quantity exceeds the order limit
    public static final String TENANT_USE_NUMBER_RANGE_OUT = "TENANT_USE_NUMBER_RANGE_OUT";

    // Validity period must be greater than the current time
    public static final String ORDER_EXPIRATION_DATA_THAN_NOW = "ORDER_EXPIRATION_DATA_THAN_NOW";

    // Allowed accounts must be greater than or equal to the total of existing
    // tenant accounts
    public static final String ORDER_ACCOUNTS_NUMBER_THAN_SAME_ACCOUNTS_NUMBER = "ORDER_ACCOUNTS_NUMBER_THAN_SAME_ACCOUNTS_NUMBER";

    // User number must be greater than or equal to the total number of existing
    // tenant accounts
    public static final String ORDER_USER_NUMBER_THAN_SAME_ACCOUNTS_NUMBER = "ORDER_USER_NUMBER_THAN_SAME_ACCOUNTS_NUMBER";

    // Goods Name cannot be repeated
    public static final String GOODS_NAME_NOT_REPEAT = "GOODS_NAME_NOT_REPEAT";
    // Goods Code cannot be repeated
    public static final String GOODS_CODE_NOT_REPEAT = "GOODS_CODE_NOT_REPEAT";

    // Rule Name cannot be repeated
    public static final String RULES_NAME_NOT_REPEAT = "RULES_NAME_NOT_REPEAT";
    // Device Type cannot be repeated
    public static final String DEVICE_MODEL_NOT_REPEAT = "DEVICE_MODEL_NOT_REPEAT";
    // Device Type does not exist
    public static final String DEVICE_TYPE_NOT_EXIST = "DEVICE_TYPE_NOT_EXIST";
    // Device Code cannot be repeated
    public static final String DEVICE_CODE_NOT_REPEAT = "DEVICE_CODE_NOT_REPEAT";
    // Device Type - Data Dictionary Type does not exist
    public static final String DEVICE_TYPE_CODE_DICT_TYPE_NOT_EXIST = "DEVICE_TYPE_CODE_DICT_TYPE_NOT_EXIST";
    // Device Type - Data Dictionary does not exist
    public static final String DEVICE_TYPE_CODE_DICT_NOT_EXIST = "DEVICE_TYPE_CODE_DICT_NOT_EXIST";
    // App Client Type - Data Dictionary Type does not exist
    public static final String APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST = "APP_UPGRADE_TYPE_DICT_TYPE_NOT_EXIST";
    // App Client Type - Data Dictionary does not exist
    public static final String APP_UPGRADE_TYPE_DICT_NOT_EXIST = "APP_UPGRADE_TYPE_DICT_NOT_EXIST";

    // Device Code length cannot exceed 200 characters
    public static final String DEVICE_CODE_LENGTH_CANNOT_EXCEED = "DEVICE_CODE_LENGTH_CANNOT_EXCEED: 200";
    // Device Power length cannot exceed 100 characters
    public static final String DEVICE_DBM_LENGTH_CANNOT_EXCEED = "DEVICE_DBM_LENGTH_CANNOT_EXCEED: 100";
    // Device IP length cannot exceed 50 characters
    public static final String DEVICE_IP_LENGTH_CANNOT_EXCEED = "DEVICE_IP_LENGTH_CANNOT_EXCEED: 50";

    // The same organization cannot have identical groups
    public static final String USER_GROUP_NOT_REPEAT = "USER_GROUP_NOT_REPEAT";
    // Group Name cannot be repeated
    public static final String USER_GROUP_NAME_NOT_REPEAT = "USER_GROUP_NAME_NOT_REPEAT";

    // Organization already bound by a user, cannot delete
    public static final String ORG_CODE_USED_BY_USER = "ORG_CODE_USED_BY_USER";
    // Same product already exists
    public static final String SAME_PRODUCT_IS_EXIST = "SAME_PRODUCT_IS_EXIST";
    
    public static final String PRODUCT_EXPIRING_DAY_NOT_EXIST = "PRODUCT_EXPIRING_DAY_NOT_EXIST";
    
	 // Account number is not within the product settings range
	 public static final String ACCOUNT_NUMBER_NOT_RIGHT = "ACCOUNT_NUMBER_NOT_RIGHT";
	 // The group already belongs to another organization
	 public static final String USER_GROUP_ORGANIZATION_IS_EXIST = "USER_GROUP_ORGANIZATION_IS_EXIST";
	 // Tenant has no purchase orders
	 public static final String TENANT_HAVA_NOT_ORDER = "TENANT_NOT_HAVA_ORDER";
	 // Tenant order has been terminated or expired, cannot log in
	 public static final String USER_ORDER_END_NOT_LOGIN = "USER_ORDER_END_NOT_LOGIN";
	 // Item has been used
	 public static final String GOODS_USED = "GOODS_USED";
	 // Field name cannot be duplicated
	 public static final String FIELD_NAME_NOT_REPEAT = "FIELD_NAME_NOT_REPEAT";
	 // Item batch number cannot be duplicated
	 public static final String GOODS_BATCHCODE_NOT_REPEAT = "GOODS_BATCHCODE_NOT_REPEAT";
	 // Rule has been used, cannot edit
	 public static final String RULE_USED_CANT_NOT_EDIT = "RULE_USED_CANT_NOT_EDIT";
	 // Device upgrade failed
	 public static final String DEVICE_UPGRAD_FAIL = "DEVICE_UPGRAD_FAIL";
	 // Non-admin cannot allocate coding rule menu permissions
	 public static final String CODINGRULES_NOT_ALLOCATION = "CODINGRULES_NOT_ALLOCATION";
	 // Export data exceeds 50,000
	 public static final String EXPORT_DATA_LENG_OUT_SIZE = "EXPORT_DATA_LENG_OUT_SIZE";
	 // TID cannot be equal to EPC
	 public static final String TID_CANNOT_BE_EQUAL_TO_EPC = "TID_CANNOT_BE_EQUAL_TO_EPC";
	 
	 // Coding Rule in use cannot be deleted
	 public static final String BASE_CODING_RULES_USED = "BASE_CODING_RULES_USED";
	 
	 // Device  Rule  Detail Data not exist
	 public static final String DEVICE_RULE_DETAIL_NOT_EXIST = "DEVICE_RULE_DETAIL_NOT_EXIST";
	 
	 // Device  Rule configuration cannot repeat
	 public static final String DEVICE_RULE_NOT_REPEAT = "DEVICE_RULE_NOT_REPEAT";
	 
	 //  Device upgrade package not exist
	 public static final String DEVICE_PACKAGE_NOT_EXIST = "DEVICE_PACKAGE_NOT_EXIST";
	 
	 // Device ip address cannot repeat
	 public static final String BASE_DEVICE_IP_REPEAT = "BASE_DEVICE_IP_REPEAT";
	 // Device mac address cannot repeat
	 public static final String BASE_DEVICE_MAC_REPEAT = "BASE_DEVICE_MAC_REPEAT";
	 
	 // Device Type in use, cannot be deleted
	 public static final String BASE_DEVICE_TYPE_USED = "BASE_DEVICE_TYPE_USED";
	 // xlsx format incorrect
	 public static final String XLSX_FILE_INCORRECT_FORMAT = "XLSX_FILE_INCORRECT_FORMAT";
	 // Device power value incorrect
	 public static final String BASE_DEVICE_DBM_VALUE_INCORRECT = "BASE_DEVICE_DBM_VALUE_INCORRECT";
	 
	// Device in use cannot be deleted
	public static final String BASE_DEVICE_USED = "BASE_DEVICE_USED";
	// Device not exist
	public static final String BASE_DEVICE_NOT_EXIST = "BASE_DEVICE_NOT_EXIST";
	
	//EPC cannot repeat
    public static final String BASE_EPC_NOT_REPEAT = "BASE_EPC_NOT_REPEAT";
    // TID cannot repeat
    public static final String BASE_TID_NOT_REPEAT = "BASE_TID_NOT_REPEAT";
    //  Label cannot repeat
    public static final String BASE_RFID_INFO_NOT_EXIST = "BASE_RFID_INFO_NOT_EXIST";
    
    //  Label ID cannot repeat
    public static final String BASE_RFID_NOT_REPEAT = "BASE_RFID_NOT_REPEAT";
    
    //item ID cannot repeat
    public static final String BASE_GOODSID_NOT_REPEAT = "BASE_GOODSID_NOT_REPEAT";
    
    //  Device max length cannot exceed 200
    public static final String DEVICE_MAX_LENGTH_CANNOT_EXCEED = "DEVICE_MAX_LENGTH_CANNOT_EXCEED: 200";
    
    //  Device longitude length cannot exceed 100
    public static final String DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED = "DEVICE_LONGITUDE_LENGTH_CANNOT_EXCEED: 100";
    
    //  Device latitude length cannot exceed 100
    public static final String DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED = "DEVICE_LATITUDE_LENGTH_CANNOT_EXCEED: 100";

    //  Device install address length cannot exceed 200
    public static final String DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED = "DEVICE_INSTALL_ADDRESS_LENGTH_CANNOT_EXCEED: 200";
    
    //Login time out 
    public static final String LOGIN_TIMED_OUT = "LOGIN_TIMED_OUT";
    
    //Resource has sub set
    public static final String THIS_RESOURCE_HAS_SUB_SETS = "THIS_RESOURCE_HAS_SUB_SETS";
    
    //User existed
    public static final String USER_EXISTED = "USER_EXISTED";
    
    //User role in use cannot be deleted
    public static final String USER_ROLE_USED = "USER_ROLE_USED";
    
	 public static final String NETWORK_DISRUPTION = "NETWORK_DISRUPTION";
	 public static final String DATA_BACKUP_EXCEPTION = "DATA_BACKUP_EXCEPTION";
	 public static final String INVALID_DATA_ID = "INVALID_DATA_ID";
	 public static final String USER_NOT_ACTIVE = "USER_NOT_ACTIVE";
	 public static final String KEYCLOAK_TOKEN_NOT_EXIST = "KEYCLOAK_TOKEN_NOT_EXIST";
	 public static final String IOT_INVOCATION_FAILED = "IOT_INVOCATION_FAILED";

}
