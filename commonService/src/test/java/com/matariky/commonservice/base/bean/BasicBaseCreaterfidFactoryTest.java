package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCreaterfidFactoryTest {

    @InjectMocks
    private BasicBaseCreaterfidFactory basicbasecreaterfidfactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String remark = "Test Remark";

        // When
        basicbasecreaterfidfactory.setId(id);
        basicbasecreaterfidfactory.setRemark(remark);

        // Then
        assertThat(basicbasecreaterfidfactory.getId()).isEqualTo(id);
        assertThat(basicbasecreaterfidfactory.getRemark()).isEqualTo(remark);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseCreaterfidFactory factory1 = new BasicBaseCreaterfidFactory();
        factory1.setId(1L);
        BasicBaseCreaterfidFactory factory2 = new BasicBaseCreaterfidFactory();
        factory2.setId(1L);

        // When & Then
        assertThat(factory1).isEqualTo(factory2);
        assertThat(factory1.hashCode()).isEqualTo(factory2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbasecreaterfidfactory.setId(1L);
        basicbasecreaterfidfactory.setRemark("Test Remark");

        // When
        String result = basicbasecreaterfidfactory.toString();

        // Then
        assertThat(result).contains("id=1", "remark=Test Remark");
    }

    // Add more test methods for other methods in BasicBaseCreaterfidFactory
}
