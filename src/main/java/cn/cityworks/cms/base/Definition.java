package cn.cityworks.cms.base;

/**
 * 程序固定属性
 */
public class Definition {

    /** Result类使用，success值*/
    public static final int SUCCESS = 0;

    /** 数据状态码-正常 */
    public static final int DATA_STATUS_NORMAL = 0;
    /** 数据状态码-逻辑删除（垃圾桶） */
    public static final int DATA_STATUS_LOGIC_DELETE = -1;
    /** 数据状态码-物理删除（完全删除） */
    public static final int DATA_STATUS_PHYSICS_DELETE = -2;

    /** 返回状态码-成功返回 */
    public static final int RESPONSE_STATUS_SUCCESS = 200;
    /** 返回状态码-异常返回 */
    public static final int RESPONSE_STATUS_FAIL = 500;

    /** 异常编码-运行异常 */
    public static final int EXCEPTION_OF_RUNTIME = 1;
    /** 异常编码-业务异常异常 */
    public static final int EXCEPTION_OF_BIZ = 2;
}
