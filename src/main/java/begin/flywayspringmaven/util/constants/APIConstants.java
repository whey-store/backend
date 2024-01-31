package begin.flywayspringmaven.util.constants;

public class APIConstants {
    /**
     * Common
     */
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 400;
    public static final int ERROR_BAD_REQUEST_CODE = 400;
    public static final String ERROR_MSG = "Error";
    public static final String ERROR_BAD_VALUE_MSG = "The value %s is invalid";

    public static final int ERROR_NOT_FOUND_CODE = 404;
    public static final String ERROR_NOT_FOUND_MSG = "%s not found";
    public static final String ERROR_CONFLICT_MSG = "Duplicate %s value";
    public static final String ERROR_EXISTED_MSG = "%s is existed";
    public static final String ERROR_CREATE_FAILED_MSG = "Create %s failed";
    public static final String ERROR_UPDATE_FAILED_MSG = "Update %s failed";
    public static final String ERROR_DELETE_FAILED_MSG = "Delete %s failed";
    public static final String ERROR_UNKNOW_MSG = "Unknown error";

    //	public static final String ERROR_EMAIL_EXISTED_MSG = "Email has been used";
//
    public static final String USER_EMAIL_INVALID_FORMAT_MSG = "Incorrect email format";
    //
    public static final String USER_ONLY_ONE_SHOP_MSG = "Each user has only one shop";

    /**
     * For Authentication
     */
    public static final String USER_INACTIVE_MSG = "User has not been activated";
    public static final String USER_BLOCKED_MSG = "User has been blocked";
    public static final String USER_DELETED_MSG = "User has been deleted";
    public static final String TOKEN_INVALID_MSG = "Invalid token";

    public static final int ERROR_CONFLICT_CODE = 409;
    public static final String ERROR_OLDPASSWORD_INVALID_MSG = "Invalid old password";


    /**
     * For user
     */

    public static final String ERROR_ACCOUNT_IS_BLOCKED = "Account is blocked";
    public static final String ERROR_ACCOUNT_IS_DELETED = "Account is deleted";

    public static final String ERROR_SHOP_IS_REJECTED = "Shop is rejected";
}
