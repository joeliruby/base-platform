package com.matariky.aop;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
import com.matariky.commonservice.sqlog.mapper.CommonSqlLogMapper;
import com.matariky.utils.SqlUtils;
import com.matariky.utils.TokenUtils;

//commented temporarily to reduce verboseness of logs
//@Aspect
//@Component
public class SqlLogAspect {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Pointcut("execution(public * com.matariky..*.mapper.*.*(..))")
    public void sqlLog() {
    }

    @Autowired
    CommonSqlLogMapper commonSqlLogMapper;

    @Around("sqlLog() ")
    public Object logAroundMappers(ProceedingJoinPoint pjp) throws Throwable {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (!Objects.isNull(sra) && !Objects.isNull(sra.getRequest())) {
            request = sra.getRequest();
        }
        Long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        Long endTime = System.currentTimeMillis();

        String sql = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);

        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            CommonSqlLog csl = new CommonSqlLog();
            csl.setCreateTime(System.currentTimeMillis());
            csl.setExecutionTime(endTime - startTime);
            csl.setSqlStatemant(sql);
            String userId = null;
            if (proceed != null && Objects.nonNull(request) && request.getRequestURL().toString().endsWith("/login")
                    && "com.matariky.userservice.bean.User".equals(proceed.getClass().getName())) {// 如果是 Login
                Method getId = proceed.getClass().getMethod("getId", new Class[] {});
                Object obj = getId.invoke(proceed, (Object[]) null);
                userId = obj.toString();
            } else if (Objects.nonNull(request) && !request.getRequestURL().toString().endsWith("/login"))
                userId = TokenUtils.extractUserIdFromHttpReqeust(request);
            if (userId == null)
                csl.setCreatedBy(-1L);
            else {
                csl.setCreatedBy(Long.parseLong(userId));
            }
            csl.setId(UUID.randomUUID().toString());
            commonSqlLogMapper.createCommonSqlLog(csl);
        }
        return proceed;

    }

}