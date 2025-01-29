package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCodingRulesUpdateVOTest {

    @InjectMocks
    private BasicBaseCodingRulesUpdateVO basicbasecodingrulesupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        basicbasecodingrulesupdatevo = new BasicBaseCodingRulesUpdateVO();
    }

    @Test
    void testRulesName() {
        // Given
        String rulesName = "Test Rule Name";

        // When
        basicbasecodingrulesupdatevo.setRulesName(rulesName);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getRulesName()).isEqualTo(rulesName);
    }

    @Test
    void testCodingLength() {
        // Given
        Integer codingLength = 10;

        // When
        basicbasecodingrulesupdatevo.setCodingLength(codingLength);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getCodingLength()).isEqualTo(codingLength);
    }

    @Test
    void testRemark() {
        // Given
        String remark = "Test Remark";

        // When
        basicbasecodingrulesupdatevo.setRemark(remark);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getRemark()).isEqualTo(remark);
    }

    @Test
    void testId() {
        // Given
        Long id = 1L;

        // When
        basicbasecodingrulesupdatevo.setId(id);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getId()).isEqualTo(id);
    }

    @Test
    void testRulesType() {
        // Given
        Integer rulesType = 2;

        // When
        basicbasecodingrulesupdatevo.setRulesType(rulesType);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getRulesType()).isEqualTo(rulesType);
    }

    @Test
    void testIsGenerateBySort() {
        // Given
        Boolean isGenerateBySort = true;

        // When
        basicbasecodingrulesupdatevo.setIsGenerateBySort(isGenerateBySort);

        // Then
        assertThat(basicbasecodingrulesupdatevo.getIsGenerateBySort()).isEqualTo(isGenerateBySort);
    }
}
