package com.xin.aop;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhenhr.common.ResultObj;
import com.zhenhr.common.TPErrorCodeGeneral;
import com.zhenhr.tools.JsonUtil;
import com.zhenhr.tools.ServletUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xin.user.service.TokenService;

/**
 * @description
 */

@Component
public class ControllerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;

    private final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * <p>
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */

    public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object handler) throws Exception {
        String url = req.getRequestURL().toString();

		/* url检测是否为开发性网址 */
        StringBuffer regexBuf = new StringBuffer();
        // 前置 例如：http://localhost:8080/projectName/
        regexBuf.append("(.*)/(");
        // 拦截网址
        regexBuf.append("|(user/smscode/send)");
        regexBuf.append("|(piccode/get)");
        regexBuf.append("|(user/register)");
        regexBuf.append("|(user/pwd/reset)");
        regexBuf.append("|(user/login)");

        regexBuf.append("|(index)");

        regexBuf.append("|(muser/login)");


        // 后置 例如：?name=admin&pwd=123456
        regexBuf.append(")(\\?(.)*)?");
        Boolean isClose = url.matches(regexBuf.toString());

        logger.info("receied request>>>>>>>>>>>>>>" + url + ",time=" + new Date().getTime());

        // 获取所有请求参数：
        Enumeration enu = req.getParameterNames();
        StringBuffer buf = new StringBuffer("{");
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            buf.append(paraName + ": " + req.getParameter(paraName) + ",");
        }
        buf.append("}");
        logger.info("request Param:" + buf.toString());

        // Enumeration headers = req.getHeaderNames();
        // StringBuffer header_buf = new StringBuffer("{");
        // while (headers.hasMoreElements()) {
        // String paraName = (String) headers.nextElement();
        // header_buf.append(paraName + ": " + req.getHeader(paraName) + ",");
        // }
        // header_buf.append("}");
        // logger.info("request header:" + header_buf.toString());

        // 判断是否关闭
        if (isClose) {
            return true;
        }

        // 检测用户token
        try {
            String token = req.getHeader("token");
            logger.info("token:" + token);

            if (token == null) {
                ResultObj obj = new ResultObj();
                obj.setCode(TPErrorCodeGeneral.Error_Param);
                obj.setMsg("token为空");
                logger.info("token is empty");
                ServletUtils.writeString(JsonUtil.toJson(obj), req, response);
                return false;
            }

            String userId = tokenService.getUserId(token);
            if (userId != null) {
                return true;
            }

            ResultObj resultObj = new ResultObj();
            resultObj.setCode(TPErrorCodeGeneral.Error_TokenError);
            resultObj.setMsg("用户身份已过期");
            ServletUtils.writeString(JsonUtil.toJson(resultObj), req, response);
            logger.info("token is valid");

            return false;

        } catch (Exception e) {
            ResultObj obj = new ResultObj();
            obj.setCode(TPErrorCodeGeneral.Error_Unknown);
            obj.setMsg("系统错误");
            ServletUtils.writeString(JsonUtil.toJson(obj), req, response);
            e.printStackTrace();
            return false;
        }

    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info(ex.getMessage());
    }

}