package com.matarikyr.processor.constants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.constants.ProcessorConstants;

@SpringBootTest
public class ProcessorConstantsTest {

    @InjectMocks
    private ProcessorConstants processorconstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFileNamesConstant() {
        assertThat(ProcessorConstants.GET_FILE_NAMES).isEqualTo("getFileNames");
    }

    @Test
    void testUpdateFileContentConstant() {
        assertThat(ProcessorConstants.UPDATE_FILE_CONTENT).isEqualTo("updateFileByName");
    }

    @Test
    void testGetFileContentConstant() {
        assertThat(ProcessorConstants.GET_FILE_CONTENT).isEqualTo("getFileContent");
    }

    @Test
    void testFileNameConstant() {
        assertThat(ProcessorConstants.FILE_NAME).isEqualTo("fileName");
    }

    @Test
    void testEnvConstant() {
        assertThat(ProcessorConstants.ENV).isEqualTo("env");
    }

    @Test
    void testFileDirConstant() {
        assertThat(ProcessorConstants.FILE_DIR).isEqualTo("fileDir");
    }

    @Test
    void testFileContentConstant() {
        assertThat(ProcessorConstants.FILE_CONTENT).isEqualTo("fileContent");
    }

    @Test
    void testOperationConstant() {
        assertThat(ProcessorConstants.OPERATION).isEqualTo("operation");
    }
}
