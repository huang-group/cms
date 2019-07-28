package cn.cityworks.cms.exception;

import cn.cityworks.cms.base.Definition;
import cn.cityworks.cms.base.Result;
import cn.cityworks.cms.dao.ExceptionHandleDao;
import cn.cityworks.cms.domain.SysException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.*;
import java.sql.Timestamp;

/**
 * 业务异常处理类
 */
@RestControllerAdvice
@Log4j2
public class ExceptionHandleController {

    private ExceptionHandleDao exceptionHandleDao;
    @Autowired(required = false)
    public void setExceptionHandleDao(ExceptionHandleDao exceptionHandleDao){
        this.exceptionHandleDao = exceptionHandleDao;
    }

    /**
     * 异常捕获
     */
    @ExceptionHandler(value = Exception.class)
    public Result handleBusinessException(Exception ex){
        //异常名称，异常位置，异常文本,异常描述
        String exceptionName, exceptionLocation, exceptionText, exceptionDescription;
        SysException sysException = new SysException();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        StringWriter stringWriter = null;

        try{
            //获取异常名称
            exceptionName = ex.toString();
            //根据异常名称区别系统抛出异常
            if(exceptionName.equals("cn.cityworks.cms.exception.BizException")){
                BizException bx = (BizException)ex;
                exceptionDescription = bx.getDescription();

                sysException.setType(2);
            }else{
                exceptionDescription = exceptionName;
                exceptionName = exceptionName.split(":")[0];

                sysException.setType(1);
            }

            //定位异常所处类，若是不存在，则该异常不是由自定义代码产生
            String className;
            exceptionLocation = "";
            for(StackTraceElement ste : ex.getStackTrace()){
                className = ste.getClassName();
                if(className.contains("cn.cityworks.cms")){
                    exceptionLocation = className + ":" + ste.getLineNumber();
                    break;
                }
            }
            log.error(exceptionLocation);

            //获取异常的全部信息
            stringWriter = new StringWriter();
            stringWriter.flush();
            ex.printStackTrace(new PrintWriter(stringWriter , true));
            exceptionText = stringWriter.toString();

            //数据库存储信息
            sysException.setName(exceptionName);
            sysException.setLocation(exceptionLocation);
            sysException.setText(exceptionText);
            sysException.setDescription(exceptionDescription);
            sysException.setStatus(0);
            sysException.setRecord_status(0);
            sysException.setCreate_date(time);
            sysException.setUpdate_date(time);
            sysException.setProperties(null);

            log.error(exceptionText);

            exceptionHandleDao.addException(sysException);
        }catch (Exception e){
            exceptionDescription = "异常捕获类-出现异常!";
            e.printStackTrace();
        }finally {
            try{
                if(null != stringWriter){
                    stringWriter.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        return new Result(false, Definition.RESPONSE_STATUS_FAIL, exceptionDescription);
    }
}
