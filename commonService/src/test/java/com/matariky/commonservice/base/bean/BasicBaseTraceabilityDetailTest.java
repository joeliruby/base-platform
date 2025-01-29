package com.matariky.commonservice.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseTraceabilityDetailTest {

    @InjectMocks
    private BasicBaseTraceabilityDetail basicbasetraceabilitydetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String nodeName = "Test Node";

        // When
        basicbasetraceabilitydetail.setId(id);
        basicbasetraceabilitydetail.setNodeName(nodeName);

        // Then
        assertEquals(id, basicbasetraceabilitydetail.getId());
        assertEquals(nodeName, basicbasetraceabilitydetail.getNodeName());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseTraceabilityDetail detail1 = new BasicBaseTraceabilityDetail();
        detail1.setId(1L);
        BasicBaseTraceabilityDetail detail2 = new BasicBaseTraceabilityDetail();
        detail2.setId(1L);

        // When & Then
        assertEquals(detail1, detail2);
        assertEquals(detail1.hashCode(), detail2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbasetraceabilitydetail.setId(1L);
        basicbasetraceabilitydetail.setNodeName("Test Node");

        // When
        String result = basicbasetraceabilitydetail.toString();

        // Then
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("nodeName=Test Node"));
    }

    // Add more test methods for other methods in BasicBaseTraceabilityDetail
}
