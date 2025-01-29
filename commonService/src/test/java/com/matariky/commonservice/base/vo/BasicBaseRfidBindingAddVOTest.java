package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidBindingAddVOTest {

    @InjectMocks
    private BasicBaseRfidBindingAddVO basicBaseRfidBindingAddVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGoodsId() {
        // Given
        Long expectedGoodsId = 123L;
        basicBaseRfidBindingAddVO.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = basicBaseRfidBindingAddVO.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testDeviceId() {
        // Given
        Long expectedDeviceId = 456L;
        basicBaseRfidBindingAddVO.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basicBaseRfidBindingAddVO.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testEpc() {
        // Given
        String expectedEpc = "EPC123";
        basicBaseRfidBindingAddVO.setEpc(expectedEpc);

        // When
        String actualEpc = basicBaseRfidBindingAddVO.getEpc();

        // Then
        assertThat(actualEpc).isEqualTo(expectedEpc);
    }

    @Test
    void testTid() {
        // Given
        String expectedTid = "TID123";
        basicBaseRfidBindingAddVO.setTid(expectedTid);

        // When
        String actualTid = basicBaseRfidBindingAddVO.getTid();

        // Then
        assertThat(actualTid).isEqualTo(expectedTid);
    }

    @Test
    void testTagCode() {
        // Given
        Long expectedTagCode = 789L;
        basicBaseRfidBindingAddVO.setTagCode(expectedTagCode);

        // When
        Long actualTagCode = basicBaseRfidBindingAddVO.getTagCode();

        // Then
        assertThat(actualTagCode).isEqualTo(expectedTagCode);
    }
}
