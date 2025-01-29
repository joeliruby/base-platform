package com.matariky.aop;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.mockito.Mock;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.matariky.commonservice.sqlog.mapper.CommonSqlLogMapper;
import com.matariky.utils.SqlUtils;

@SpringBootTest
public class SqlLogAspectTest {

    @InjectMocks
    private SqlLogAspect sqlLogAspect;

    @Mock
    private SqlSessionFactory sqlSessionFactory;

    @Mock
    private CommonSqlLogMapper commonSqlLogMapper;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ServletRequestAttributes servletRequestAttributes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogAroundMappers_SelectQuery() throws Throwable {
        // Given
        when(proceedingJoinPoint.proceed()).thenReturn(new Object());
        when(SqlUtils.getMybatisSql(proceedingJoinPoint, sqlSessionFactory)).thenReturn("SELECT * FROM table");
        when(RequestContextHolder.getRequestAttributes()).thenReturn(servletRequestAttributes);
        when(servletRequestAttributes.getRequest()).thenReturn(request);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost/test"));

        // When
        Object result = sqlLogAspect.logAroundMappers(proceedingJoinPoint);

        // Then
        assertThat(result).isNotNull();
        verify(commonSqlLogMapper, times(1)).createCommonSqlLog(any());
    }

    @Test
    void testLogAroundMappers_NonSelectQuery() throws Throwable {
        // Given
        when(proceedingJoinPoint.proceed()).thenReturn(new Object());
        when(SqlUtils.getMybatisSql(proceedingJoinPoint, sqlSessionFactory))
                .thenReturn("UPDATE table SET column = value");
        when(RequestContextHolder.getRequestAttributes()).thenReturn(servletRequestAttributes);
        when(servletRequestAttributes.getRequest()).thenReturn(request);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost/test"));

        // When
        Object result = sqlLogAspect.logAroundMappers(proceedingJoinPoint);

        // Then
        assertThat(result).isNotNull();
        verify(commonSqlLogMapper, times(0)).createCommonSqlLog(any());
    }

    @Test
    void testLogAroundMappers_LoginRequest() throws Throwable {
        // Given
        Object user = mock(Object.class);
        when(proceedingJoinPoint.proceed()).thenReturn(user);
        when(SqlUtils.getMybatisSql(proceedingJoinPoint, sqlSessionFactory)).thenReturn("SELECT * FROM table");
        when(RequestContextHolder.getRequestAttributes()).thenReturn(servletRequestAttributes);
        when(servletRequestAttributes.getRequest()).thenReturn(request);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost/login"));
        when(user.getClass().getName()).thenReturn("com.matariky.userservice.bean.User");
        when(user.getClass().getMethod("getId")).thenReturn(Object.class.getMethod("toString"));
        when(user.getClass().getMethod("getId").invoke(user)).thenReturn("123");

        // When
        Object result = sqlLogAspect.logAroundMappers(proceedingJoinPoint);

        // Then
        assertThat(result).isNotNull();
        verify(commonSqlLogMapper, times(1)).createCommonSqlLog(any());
    }

    // Add more test methods for other scenarios in SqlLogAspect
}
