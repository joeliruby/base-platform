package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidBindingUpdateVOTest {

    @InjectMocks
    private BasicBaseRfidBindingUpdateVO basicBaseRfidBindingUpdateVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRfidId() {
        // Given
        Long expectedRfidId = 123L;
        basicBaseRfidBindingUpdateVO.setRfidId(expectedRfidId);

        // When
        Long actualRfidId = basicBaseRfidBindingUpdateVO.getRfidId();

        // Then
        assertThat(actualRfidId).isEqualTo(expectedRfidId);
    }

    @Test
    void testGoodsId() {
        // Given
        Long expectedGoodsId = 456L;
        basicBaseRfidBindingUpdateVO.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = basicBaseRfidBindingUpdateVO.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testDeviceId() {
        // Given
        Long expectedDeviceId = 789L;
        basicBaseRfidBindingUpdateVO.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basicBaseRfidBindingUpdateVO.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testEpc() {
        // Given
        String expectedEpc = "EPC123";
        basicBaseRfidBindingUpdateVO.setEpc(expectedEpc);

        // When
        String actualEpc = basicBaseRfidBindingUpdateVO.getEpc();

        // Then
        assertThat(actualEpc).isEqualTo(expectedEpc);
    }

    @Test
    void testTid() {
        // Given
        String expectedTid = "TID123";
        basicBaseRfidBindingUpdateVO.setTid(expectedTid);

        // When
        String actualTid = basicBaseRfidBindingUpdateVO.getTid();

        // Then
        assertThat(actualTid).isEqualTo(expectedTid);
    }

    @Test
    void testRemark() {
        // Given
        String expectedRemark = "This is a remark";
        basicBaseRfidBindingUpdateVO.setRemark(expectedRemark);

        // When
        String actualRemark = basicBaseRfidBindingUpdateVO.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testId() {
        // Given
        Long expectedId = 101L;
        basicBaseRfidBindingUpdateVO.setId(expectedId);

        // When
        Long actualId = basicBaseRfidBindingUpdateVO.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }
}
