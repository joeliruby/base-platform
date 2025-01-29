package com.matariky.commonservice.log.bean;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicLogErrorTest {

    @InjectMocks
    private BasicLogError basiclogerror;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Add more test methods for other methods in BasicLogError
}
