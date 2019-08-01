package cn.cityworks.cms.config;

import cn.cityworks.cms.utils.HttpTools;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限控制拦截器
 */
@Aspect
@Component
@Log4j2
public class AuthorityInterceptor {

    /**
     * 拦截规则
     * 拦截所有包下含有@PostMapping注解的方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut(){}

    /**
     * 请求前进行处理
     */
    @Before("postMappingPointcut()")
    public void doBefore(){

        //从RequestContextHolder中获取HttpServletRequest
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null == servletRequestAttributes){
            return;
        }
        //获取请求
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String token = request.getHeader("access_token");

        //根据token获取用户信息
        List<Map<String, String>> parms = new ArrayList<>();
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("paramKey", "access_token");
        paramsMap.put("paramValue", token);
        parms.add(paramsMap);
        String userInfo = HttpTools.httpGetRequest("http://11.23.254.120/auth-resource/user", parms);

        log.warn("这边要计算权限!");

        request.setAttribute("user_roles", userInfo);
    }

}
