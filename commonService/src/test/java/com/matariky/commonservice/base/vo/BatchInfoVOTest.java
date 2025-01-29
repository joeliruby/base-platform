package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BatchInfoVOTest {

    @InjectMocks
    private BatchInfoVO batchinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGoodsId() {
        // Given
        Long expectedGoodsId = 123L;
        batchinfovo.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = batchinfovo.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testSetCreateTime() {
        // Given
        Long expectedCreateTime = System.currentTimeMillis();
        batchinfovo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = batchinfovo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testSetCreateTimeEnd() {
        // Given
        Long expectedCreateTimeEnd = System.currentTimeMillis() + 1000;
        batchinfovo.setCreateTimeEnd(expectedCreateTimeEnd);

        // When
        Long actualCreateTimeEnd = batchinfovo.getCreateTimeEnd();

        // Then
        assertThat(actualCreateTimeEnd).isEqualTo(expectedCreateTimeEnd);
    }

    @Test
    void testSetEpc() {
        // Given
        String expectedEpc = "EPC123456";
        batchinfovo.setEpc(expectedEpc);

        // When
        String actualEpc = batchinfovo.getEpc();

        // Then
        assertThat(actualEpc).isEqualTo(expectedEpc);
    }

    @Test
    void testSetTid() {
        // Given
        String expectedTid = "TID123456";
        batchinfovo.setTid(expectedTid);

        // When
        String actualTid = batchinfovo.getTid();

        // Then
        assertThat(actualTid).isEqualTo(expectedTid);
    }
}
