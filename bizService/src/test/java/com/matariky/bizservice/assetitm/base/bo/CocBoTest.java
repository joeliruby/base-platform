package com.matariky.bizservice.assetitm.base.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CocBoTest {

    @InjectMocks
    private CocBo cocbo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Integer seqNo = 1;
        String fromTo = "A-B";
        String location = "Location1";
        Integer status = 0;
        String readerCode = "Reader1";
        String ip = "192.168.1.1";
        String operator = "Operator1";
        String remark = "Remark1";
        Long time = System.currentTimeMillis();

        // When
        cocbo.setSeqNo(seqNo);
        cocbo.setFromTo(fromTo);
        cocbo.setLocation(location);
        cocbo.setStatus(status);
        cocbo.setReaderCode(readerCode);
        cocbo.setIp(ip);
        cocbo.setOperator(operator);
        cocbo.setRemark(remark);
        cocbo.setTime(time);

        // Then
        assertEquals(seqNo, cocbo.getSeqNo());
        assertEquals(fromTo, cocbo.getFromTo());
        assertEquals(location, cocbo.getLocation());
        assertEquals(status, cocbo.getStatus());
        assertEquals(readerCode, cocbo.getReaderCode());
        assertEquals(ip, cocbo.getIp());
        assertEquals(operator, cocbo.getOperator());
        assertEquals(remark, cocbo.getRemark());
        assertEquals(time, cocbo.getTime());
    }

    @Test
    void testToString() {
        // Given
        cocbo.setSeqNo(1);
        cocbo.setFromTo("A-B");
        cocbo.setLocation("Location1");
        cocbo.setStatus(0);
        cocbo.setReaderCode("Reader1");
        cocbo.setIp("192.168.1.1");
        cocbo.setOperator("Operator1");
        cocbo.setRemark("Remark1");
        cocbo.setTime(System.currentTimeMillis());

        // When
        String toString = cocbo.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("seqNo=1"));
        assertTrue(toString.contains("fromTo=A-B"));
        assertTrue(toString.contains("location=Location1"));
        assertTrue(toString.contains("status=0"));
        assertTrue(toString.contains("readerCode=Reader1"));
        assertTrue(toString.contains("ip=192.168.1.1"));
        assertTrue(toString.contains("operator=Operator1"));
        assertTrue(toString.contains("remark=Remark1"));
    }

    // Add more test methods for other methods in CocBo if needed
}
