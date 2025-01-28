package com.matariky.orderservice.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderSuiteConfigMapperTest {

    @InjectMocks
    private OrderSuiteConfigMapper ordersuiteconfigmapper;

    @Mock
    private OrderSuiteConfigMapper mockOrderSuiteConfigMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuiteConfigAll() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(mockOrderSuiteConfigMapper.getOrderSuiteConfigAll()).thenReturn(Collections.singletonList(config));

        List<OrderSuiteConfig> result = ordersuiteconfigmapper.getOrderSuiteConfigAll();

        assertThat(result).isNotEmpty();
        verify(mockOrderSuiteConfigMapper, times(1)).getOrderSuiteConfigAll();
    }

    @Test
    void testCreateOrderSuiteConfig() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(mockOrderSuiteConfigMapper.createOrderSuiteConfig(config)).thenReturn(1);

        int result = ordersuiteconfigmapper.createOrderSuiteConfig(config);

        assertThat(result).isEqualTo(1);
        verify(mockOrderSuiteConfigMapper, times(1)).createOrderSuiteConfig(config);
    }

    @Test
    void testUpdateOrderSuiteConfig() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(mockOrderSuiteConfigMapper.updateOrderSuiteConfig(config)).thenReturn(1);

        int result = ordersuiteconfigmapper.updateOrderSuiteConfig(config);

        assertThat(result).isEqualTo(1);
        verify(mockOrderSuiteConfigMapper, times(1)).updateOrderSuiteConfig(config);
    }

    @Test
    void testDelOrderSuiteConfigById() {
        int id = 1;
        when(mockOrderSuiteConfigMapper.delOrderSuiteConfigById(id)).thenReturn(1);

        int result = ordersuiteconfigmapper.delOrderSuiteConfigById(id);

        assertThat(result).isEqualTo(1);
        verify(mockOrderSuiteConfigMapper, times(1)).delOrderSuiteConfigById(id);
    }

    @Test
    void testGetOrderSuiteConfigById() {
        int id = 1;
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(mockOrderSuiteConfigMapper.getOrderSuiteConfigById(id)).thenReturn(config);

        OrderSuiteConfig result = ordersuiteconfigmapper.getOrderSuiteConfigById(id);

        assertThat(result).isNotNull();
        verify(mockOrderSuiteConfigMapper, times(1)).getOrderSuiteConfigById(id);
    }

    // Add more test methods for other methods in OrderSuiteConfigMapper
}
