package com.xin.aop;

import com.xin.tools.JsonUtil;
import com.xin.tools.ServletUtils;
import com.xin.tools.common.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class ControllerAop {
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 声明一个切入点，可以拦截具体到某个方法， 即在执行此方法之前、之后、最终、异常……时可以执行的其他业务方法（通知advice）；
     * 括号内的意思是：拦截某个方法，返回值是所有类型（第一个*）， com.aoptest.service包及其子包下的所有类（..*），
     * 类下所有的方法（第三个.*），返回值任意（内部嵌套括号(..)）
     */
    //
    @Pointcut("execution(* com.xin.*.controller.*.*(..))")
    public void anyMethod() {
        logger.info("anyMethod");
    }

    // 声明前置通知
//    @Before(value = "anyMethod()&&args(req,res,..)")
//    public void doBefore(JoinPoint jp) {
//        logger.info("前置通知");
//    }

//    // 声明后置通知
//    @AfterReturning(pointcut = "anyMethod()")
//    public void doAfterReturning() {
//        logger.info("后置通知");
//    }


    @Around("anyMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

    @Around("anyMethod()&&args(params)")
    public Object doAround(ProceedingJoinPoint pjp, Object params) throws Throwable {
        logger.info("Logger2");
        return pjp.proceed();
    }

    // 声明环绕通知
    @Around("anyMethod()&&args(req,res,..)")
    public Object doAround(ProceedingJoinPoint pjp, HttpServletRequest req, HttpServletResponse res) {
//        String url = req.getRequestURL().toString();
        Object o;
        try {
            o = pjp.proceed();
            return o;
        } catch (ParameterException e) {
            BaseResultObj obj = new BaseResultObj(e.errorCode, e.getMessage());
            ServletUtils.writeString(JsonUtil.toJson(obj), req, res);
            logger.info("param error");
            return null;
        } catch (ServiceException e) {
            BaseResultObj obj = new BaseResultObj(e.errorCode, e.getMessage());
            ServletUtils.writeString(JsonUtil.toJson(obj), req, res);
            logger.info("service error" + e.getLogInfo());

            return null;
        } catch (MemcachedException e) {
            BaseResultObj obj = new BaseResultObj(e.errorCode, e.getMessage());
            ServletUtils.writeString(JsonUtil.toJson(obj), req, res);
            logger.info("cache error" + e.getLogInfo());
            return null;
        } catch (ToUserException e) {
            BaseResultObj obj = new BaseResultObj(e.errorCode, e.errorField, e.getMessage());
            ServletUtils.writeString(JsonUtil.toJson(obj), req, res);

            logger.info("to user error:" + e.getMessage());
            return null;
        } catch (Throwable e) {
            BaseResultObj obj = new BaseResultObj();
            obj.setCode(TPErrorCodeGeneral.Error_Unknown);
            obj.setMsg("系统错误");
            logger.error("system error", e);

            ServletUtils.writeString(JsonUtil.toJson(obj), req, res);
            return null;
        }
    }


}
