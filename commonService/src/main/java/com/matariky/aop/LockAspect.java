package com.matariky.aop;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.redis.redisson.Lock;
import com.matariky.redis.redisson.LockUtil;
import com.matariky.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Order(1)
public class LockAspect {

    @Pointcut(value = "@annotation(com.matariky.annotation.Lock)")
    public void lockPointcut() {
    }

    @Around("lockPointcut() && @annotation(lockAnn)")
    public Object dataFilter(ProceedingJoinPoint joinPoint, com.matariky.annotation.Lock lockAnn) throws Throwable {
        List<Lock> lockList = new ArrayList<>();
        try {
            if (StringUtils.isNotEmpty(lockAnn.key())) {
                lockList.add(LockUtil.lock(lockAnn.key()));
            } else if (StringUtils.isNotEmpty(lockAnn.keyMethod())) {
                Object bean;
                String targetClazz = StringUtils.substringBeforeLast(lockAnn.keyMethod(), ".");
                if ("this".equals(targetClazz)) {
                    bean = joinPoint.getTarget();
                } else {
                    String beanName = StringUtils.lowerFirst(StringUtils.substringAfterLast(targetClazz, "."));

                    if (SpringContextUtils.containsBean(beanName)) {
                        bean = SpringContextUtils.getBean(beanName);
                    } else {
                        bean = Class.forName(targetClazz).getDeclaredConstructor().newInstance();
                    }
                }
                String methodName = StringUtils.substringAfterLast(lockAnn.keyMethod(), ".");
                Method lockKeyMethod = ReflectionUtils.findMethod(bean.getClass(), methodName, new Class[]{LockPoint.class});
                if (Objects.isNull(lockKeyMethod)) {
                    throw new NoSuchMethodException(lockAnn.keyMethod() + ",paramType=LockPoint.class");
                }
                Object keys = ReflectionUtils.invokeMethod(lockKeyMethod, bean, new LockPoint(joinPoint.getArgs(), ((MethodSignature) joinPoint.getSignature()).getMethod()));
                if (Objects.nonNull(keys)) {
                    if (keys instanceof Collection) {
                        ((Collection<?>) keys).stream().forEach(key -> lockList.add(LockUtil.lock(key.toString())));
                    } else {
                        lockList.add(LockUtil.lock(keys.toString()));
                    }
                }
            }
            return joinPoint.proceed();
        } finally {
            if (CollectionUtils.isNotEmpty(lockList)) {
                lockList.stream().forEach(Lock::unlock);
            }
        }
    }
}
