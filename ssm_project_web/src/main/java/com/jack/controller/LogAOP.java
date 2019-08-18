package com.jack.controller;

import com.jack.domain.SysLog;
import com.jack.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//这里切面的使用时注解的方式，xml文件的配置方式请参考spring day02的配置文件
//需要在springMVC配置文件上设置aop注解支持--否则不能生效
@Component
@Aspect
public class LogAOP {

 /*   @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    //访问日期
    private Date visitTime;
    //访问的类
    private Class executionClass;
    //访问的方法
    private Method executionMethod;

    // 主要获取访问时间、访问的类、访问的方法
    @Before("execution(* com.jack.controller.*.*(..))")
    public void doBefore (JoinPoint jp) throws Exception {
        //访问日期
        visitTime = new Date ();
        //访问的类
        executionClass = jp.getTarget ().getClass ();
        //访问的方法的名称
        String methodName = jp.getSignature ().getName ();

        Object[] args = jp.getArgs ();//获取访问的参数的方法

        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod ( methodName );// 只能获取无参数方法
        } else {
            Class[] classArgs = new Class[args.length];

            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass ();
            }
            executionMethod = executionClass.getMethod ( methodName, classArgs );// 获取有参数方法
        }
    }

    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.jack.controller.*.*(..))")
    public void doAfter ( JoinPoint jp) throws Exception {

        //获取访问时长
        Long executionTime = new Date ().getTime () - visitTime.getTime ();

        //获取访问的id地址
        String ip = request.getRemoteAddr ();

        //获取访问的对象用户名
        SecurityContext context = SecurityContextHolder.getContext ();
        User user = (User) context.getAuthentication ().getPrincipal ();
        String username = user.getUsername ();

        //获取访问的URL
        if (executionClass != null && executionMethod != null && executionClass != LogAOP.class) {
            //获取controller类上的requestMapping路径
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation ( RequestMapping.class );

            if (classAnnotation != null) {
                String[] value = classAnnotation.value ();
                String url1 = value[0];
            }

            //获取controller方法上的requestMapping路径
            RequestMapping methodAnnotation = executionMethod.getAnnotation ( RequestMapping.class );
            String url2 = null;
            if (methodAnnotation != null) {
                String[] value1 = methodAnnotation.value ();
                url2 = value1[0];
            }

            String url = "";
            url = url + url2;

            //把参数放进实体日志类
            SysLog sysLog = new SysLog ();
            sysLog.setUrl ( url );
            sysLog.setExecutionTime ( executionTime );
            sysLog.setIp ( ip );
            sysLog.setUsername ( username );
            sysLog.setVisitTime ( visitTime );
            sysLog.setMethod ( "[类名] " + executionClass.getName () + "[方法名] " +
                    executionMethod.getName () );

            // 调用Service，调用dao将sysLog insert数据库
            sysLogService.save ( sysLog );
        }


    }
*/

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法

    // 主要获取访问时间、访问的类、访问的方法
    @Before("execution(* com.jack.controller.*.*(..))")
    public void doBefore (JoinPoint jp) throws NoSuchMethodException, SecurityException {
        startTime = new Date (); // 访问时间
        // 获取访问的类
        executionClass = jp.getTarget ().getClass ();
        // 获取访问的方法
        String methodName = jp.getSignature ().getName ();// 获取访问的方法的名称
        Object[] args = jp.getArgs ();// 获取访问的方法的参数
        if (args == null || args.length == 0) {// 无参数
            executionMethod = executionClass.getMethod ( methodName ); // 只能获取无参数方法
        } else {
        // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];

            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass ();
            }
            executionMethod = executionClass.getMethod ( methodName, classArgs );// 获取有参数方法
        }
    }

    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.jack.controller.*.*(..))")
    public void doAfter (JoinPoint jp) throws Exception {
        // 获取类上的@RequestMapping对象
        if (executionClass != null && executionMethod != null && executionClass != LogAOP.class && executionClass != SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping)
                    executionClass.getAnnotation ( RequestMapping.class );
            if (classAnnotation != null) {
        // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation =
                        executionMethod.getAnnotation ( RequestMapping.class );
                if (methodAnnotation != null) {
                    String url = ""; // 它的值应该是类上的@RequestMapping的value+方法上的@RequestMapping的value
                    url = classAnnotation.value ()[0] + methodAnnotation.value ()[0];
                    SysLog sysLog = new SysLog ();
                    // 获取访问时长
                    Long executionTime = new Date ().getTime () - startTime.getTime ();
                    // 将sysLog对象属性封装
                    sysLog.setExecutionTime ( executionTime );
                    sysLog.setUrl ( url );
                    // 获取ip
                    String ip = request.getRemoteAddr ();
                    sysLog.setIp ( ip );
                        // 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext (); //
                 // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
                    String username = ((User)
                            (context.getAuthentication ().getPrincipal ())).getUsername ();
                    sysLog.setUsername ( username );
                    sysLog.setMethod ( "[类名]" + executionClass.getName () + "[方法名]" +
                            executionMethod.getName () );
                    sysLog.setVisitTime ( startTime );
                // 调用Service，调用dao将sysLog insert数据库
                    sysLogService.save ( sysLog );
                }
            }

        }
    }
}



