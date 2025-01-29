package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BeanUtilsTest {

    @InjectMocks
    private BeanUtils beanutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSetterMethods() {
        // Given
        TestBean testBean = new TestBean();

        // When
        List<Method> setterMethods = BeanUtils.getSetterMethods(testBean);

        // Then
        assertNotNull(setterMethods);
        assertFalse(setterMethods.isEmpty());
        assertEquals(1, setterMethods.size());
        assertEquals("setName", setterMethods.get(0).getName());
    }

    @Test
    void testGetGetterMethods() {
        // Given
        TestBean testBean = new TestBean();

        // When
        List<Method> getterMethods = BeanUtils.getGetterMethods(testBean);

        // Then
        assertNotNull(getterMethods);
        assertFalse(getterMethods.isEmpty());
        assertEquals(1, getterMethods.size());
        assertEquals("getName", getterMethods.get(0).getName());
    }

    @Test
    void testIsMethodPropEquals() {
        // Given
        String methodName1 = "getName";
        String methodName2 = "setName";

        // When
        boolean result = BeanUtils.isMethodPropEquals(methodName1, methodName2);

        // Then
        assertTrue(result);
    }

    @Test
    void testCopyProperties() {
        // Given
        TestBean source = new TestBean();
        source.setName("Test Name");

        // When
        TestBean target = BeanUtils.copyProperties(source, TestBean.class);

        // Then
        assertNotNull(target);
        assertEquals(source.getName(), target.getName());
    }

    @Test
    void testCopyPropertiesCollection() {
        // Given
        TestBean source1 = new TestBean();
        source1.setName("Test Name 1");
        TestBean source2 = new TestBean();
        source2.setName("Test Name 2");
        List<TestBean> sources = List.of(source1, source2);

        // When
        List<TestBean> targets = BeanUtils.copyProperties(sources, TestBean.class);

        // Then
        assertNotNull(targets);
        assertEquals(2, targets.size());
        assertEquals(source1.getName(), targets.get(0).getName());
        assertEquals(source2.getName(), targets.get(1).getName());
    }

    @Test
    void testBeanToMap() {
        // Given
        TestBean testBean = new TestBean();
        testBean.setName("Test Name");

        // When
        Map<String, String> beanMap = BeanUtils.beanToMap(testBean);

        // Then
        assertNotNull(beanMap);
        assertEquals("Test Name", beanMap.get("name"));
    }

    // Test bean class for testing purposes
    public static class TestBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
