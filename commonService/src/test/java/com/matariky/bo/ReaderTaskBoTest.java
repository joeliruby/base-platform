package com.matariky.bo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ReaderTaskBoTest {

    @InjectMocks
    private ReaderTaskBo readertaskbo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long taskId = 1L;
        Integer type = 2;
        Integer expirationTime = 30;
        String brandModel = "BrandModel";
        Integer application = 3;

        // When
        readertaskbo.setTaskId(taskId);
        readertaskbo.setType(type);
        readertaskbo.setExpirationTime(expirationTime);
        readertaskbo.setBrandModel(brandModel);
        readertaskbo.setApplication(application);

        // Then
        assertThat(readertaskbo.getTaskId()).isEqualTo(taskId);
        assertThat(readertaskbo.getType()).isEqualTo(type);
        assertThat(readertaskbo.getExpirationTime()).isEqualTo(expirationTime);
        assertThat(readertaskbo.getBrandModel()).isEqualTo(brandModel);
        assertThat(readertaskbo.getApplication()).isEqualTo(application);
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        ReaderTaskBo newReaderTaskBo = new ReaderTaskBo();

        // Then
        assertThat(newReaderTaskBo.getTaskId()).isNull();
        assertThat(newReaderTaskBo.getType()).isNull();
        assertThat(newReaderTaskBo.getExpirationTime()).isNull();
        assertThat(newReaderTaskBo.getBrandModel()).isNull();
        assertThat(newReaderTaskBo.getApplication()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Long taskId = 1L;
        Integer type = 2;
        Integer expirationTime = 30;
        String brandModel = "BrandModel";
        Integer application = 3;

        // When
        ReaderTaskBo newReaderTaskBo = new ReaderTaskBo(taskId, type, expirationTime, brandModel, application);

        // Then
        assertThat(newReaderTaskBo.getTaskId()).isEqualTo(taskId);
        assertThat(newReaderTaskBo.getType()).isEqualTo(type);
        assertThat(newReaderTaskBo.getExpirationTime()).isEqualTo(expirationTime);
        assertThat(newReaderTaskBo.getBrandModel()).isEqualTo(brandModel);
        assertThat(newReaderTaskBo.getApplication()).isEqualTo(application);
    }

    // Add more test methods for other methods in ReaderTaskBo if needed
}
