package com.matariky.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.Collection;

@SpringBootTest
public class QueryDataIsolationTest {

    @InjectMocks
    private QueryDataIsolation querydataisolation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetSharingSelfOrgCodes() {
        // Given
        Collection<String> sharingSelfOrgCodes = Arrays.asList("Org1", "Org2");

        // When
        querydataisolation.setSharingSelfOrgCodes(sharingSelfOrgCodes);

        // Then
        assertThat(querydataisolation.getSharingSelfOrgCodes()).isEqualTo(sharingSelfOrgCodes);
    }

    @Test
    void testSetAndGetSharingOrgCodes() {
        // Given
        Collection<String> sharingOrgCodes = Arrays.asList("Org3", "Org4");

        // When
        querydataisolation.setSharingOrgCodes(sharingOrgCodes);

        // Then
        assertThat(querydataisolation.getSharingOrgCodes()).isEqualTo(sharingOrgCodes);
    }

    @Test
    void testSetAndGetStrategyCode() {
        // Given
        String strategyCode = "Strategy1";

        // When
        querydataisolation.setStrategyCode(strategyCode);

        // Then
        assertThat(querydataisolation.getStrategyCode()).isEqualTo(strategyCode);
    }

    @Test
    void testSetAndGetOrgCode() {
        // Given
        String orgCode = "OrgCode1";

        // When
        querydataisolation.setOrgCode(orgCode);

        // Then
        assertThat(querydataisolation.getOrgCode()).isEqualTo(orgCode);
    }

    @Test
    void testSetAndGetSelfOrgCode() {
        // Given
        String selfOrgCode = "SelfOrgCode1";

        // When
        querydataisolation.setSelfOrgCode(selfOrgCode);

        // Then
        assertThat(querydataisolation.getSelfOrgCode()).isEqualTo(selfOrgCode);
    }

    @Test
    void testSetAndGetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "OperatorSelfOrgCode1";

        // When
        querydataisolation.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(querydataisolation.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }
}
