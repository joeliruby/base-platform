package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EPCAndTIDVOTest {

    @InjectMocks
    private EPCAndTIDVO epcandtidvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEpcSetterGetter() {
        // Given
        String epc = "testEPC";

        // When
        epcandtidvo.setEpc(epc);

        // Then
        assertEquals(epc, epcandtidvo.getEpc());
    }

    @Test
    void testTidSetterGetter() {
        // Given
        String tid = "testTID";

        // When
        epcandtidvo.setTid(tid);

        // Then
        assertEquals(tid, epcandtidvo.getTid());
    }

    @Test
    void testEpcNotBlank() {
        // Given
        String epc = "";

        // When
        epcandtidvo.setEpc(epc);

        // Then
        assertTrue(epcandtidvo.getEpc().isBlank());
    }

    @Test
    void testTidSize() {
        // Given
        String tid = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

        // When
        epcandtidvo.setTid(tid);

        // Then
        assertTrue(epcandtidvo.getTid().length() <= 100);
    }
}
