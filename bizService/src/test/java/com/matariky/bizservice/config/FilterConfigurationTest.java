package com.matariky.bizservice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import com.matariky.bizservice.filter.ChinesePathFilter;

@SpringBootTest
public class FilterConfigurationTest {

    @InjectMocks
    private FilterConfiguration filterConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetChinesePathFilter() {
        // Given

        // When
        FilterRegistrationBean<Filter> registrationBean = filterConfiguration.getChinesePathFilter();

        // Then
        assertThat(registrationBean.getFilter()).isInstanceOf(ChinesePathFilter.class);
    }

    // Add more test methods for other methods in FilterConfiguration
}
