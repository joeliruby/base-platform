package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCodingRulesAddVOTest {

    @InjectMocks
    private BasicBaseCodingRulesAddVO basicbasecodingrulesaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetRulesName() {
        // Given
        String rulesName = "Test Rule Name";

        // When
        basicbasecodingrulesaddvo.setRulesName(rulesName);

        // Then
        assertThat(basicbasecodingrulesaddvo.getRulesName()).isEqualTo(rulesName);
    }

    @Test
    void testSetAndGetCodingLength() {
        // Given
        Integer codingLength = 10;

        // When
        basicbasecodingrulesaddvo.setCodingLength(codingLength);

        // Then
        assertThat(basicbasecodingrulesaddvo.getCodingLength()).isEqualTo(codingLength);
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String remark = "Test Remark";

        // When
        basicbasecodingrulesaddvo.setRemark(remark);

        // Then
        assertThat(basicbasecodingrulesaddvo.getRemark()).isEqualTo(remark);
    }

    @Test
    void testSetAndGetRulesType() {
        // Given
        Integer rulesType = 1;

        // When
        basicbasecodingrulesaddvo.setRulesType(rulesType);

        // Then
        assertThat(basicbasecodingrulesaddvo.getRulesType()).isEqualTo(rulesType);
    }

    @Test
    void testSetAndGetRulesCode() {
        // Given
        String rulesCode = "TestCode";

        // When
        basicbasecodingrulesaddvo.setRulesCode(rulesCode);

        // Then
        assertThat(basicbasecodingrulesaddvo.getRulesCode()).isEqualTo(rulesCode);
    }
}
