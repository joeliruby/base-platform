package com.matarikyr.processor.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson2.JSONObject;
import com.matariky.processor.config.HttpProcessor;
import com.matariky.processor.constants.ProcessorConstants;
import com.matariky.processor.utils.ProcessorUtils;

@SpringBootTest
public class HttpProcessorTest {

    @InjectMocks
    private HttpProcessor httpprocessor;

    @Mock
    private Exchange exchange;

    @Mock
    private Message message;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(exchange.getIn()).thenReturn(message);
    }

    @Test
    void testGetFileNames() throws Exception {
        // Given
        String inJson = "{\"operation\":\"GET_FILE_NAMES\",\"env\":\"testEnv\"}";
        when(message.getBody(String.class)).thenReturn(inJson);
        when(ProcessorUtils.getProperty("testEnv", ProcessorConstants.FILE_DIR)).thenReturn("testDir");
        File dir = mock(File.class);
        when(dir.list()).thenReturn(new String[] { "file1.xml", "file2.xml" });
        // Mock the static method call to new File()
        // You can use PowerMockito or other libraries to mock static methods

        // When
        httpprocessor.process(exchange);

        // Then
        verify(message).setBody(any(JSONObject.class));
    }

    @Test
    void testUpdateFileContent() throws Exception {
        // Given
        String inJson = "{\"operation\":\"UPDATE_FILE_CONTENT\",\"env\":\"testEnv\",\"fileName\":\"testFile.xml\",\"fileContent\":\"new content\"}";
        when(message.getBody(String.class)).thenReturn(inJson);
        when(ProcessorUtils.getProperty("testEnv", ProcessorConstants.FILE_DIR)).thenReturn("testDir");

        // When
        httpprocessor.process(exchange);

        // Then
        verify(message).setBody(any(JSONObject.class));
        assertTrue(Files.exists(Paths.get("testDir/testFile.xml")));
    }

    @Test
    void testGetFileContent() throws Exception {
        // Given
        String inJson = "{\"operation\":\"GET_FILE_CONTENT\",\"env\":\"testEnv\",\"fileName\":\"testFile.xml\"}";
        when(message.getBody(String.class)).thenReturn(inJson);
        when(ProcessorUtils.getProperty("testEnv", ProcessorConstants.FILE_DIR)).thenReturn("testDir");
        Files.write(Paths.get("testDir/testFile.xml"), "file content".getBytes());

        // When
        httpprocessor.process(exchange);

        // Then
        verify(message).setBody(any(JSONObject.class));
    }

    // Add more test methods for other scenarios in HttpProcessor
}
