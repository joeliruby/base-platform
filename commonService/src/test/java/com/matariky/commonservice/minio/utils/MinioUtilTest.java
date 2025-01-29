package com.matariky.commonservice.minio.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.mockito.Mock;
import com.matariky.commonservice.minio.domain.Fileinfo;

@SpringBootTest
public class MinioUtilTest {

    @InjectMocks
    private MinioUtil minioUtil;

    @Mock
    private MinioClient minioClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBucket() throws Exception {
        // Given
        String bucketName = "test-bucket";
        when(minioClient.bucketExists(any(BucketExistsArgs.class))).thenReturn(false);

        // When
        minioUtil.createBucket(bucketName);

        // Then
        verify(minioClient, times(1)).makeBucket(any(MakeBucketArgs.class));
    }

    @Test
    void testUploadFile() throws Exception {
        // Given
        InputStream stream = mock(InputStream.class);
        String bucketName = "test-bucket";
        String objectName = "test-object";
        ObjectWriteResponse response = mock(ObjectWriteResponse.class);
        when(minioClient.putObject(any(PutObjectArgs.class))).thenReturn(response);

        // When
        ObjectWriteResponse result = minioUtil.uploadFile(stream, bucketName, objectName);

        // Then
        assertEquals(response, result);
    }

    @Test
    void testListBuckets() throws Exception {
        // Given
        Bucket bucket1 = mock(Bucket.class);
        Bucket bucket2 = mock(Bucket.class);
        when(bucket1.name()).thenReturn("bucket1");
        when(bucket2.name()).thenReturn("bucket2");
        when(minioClient.listBuckets()).thenReturn(Arrays.asList(bucket1, bucket2));

        // When
        List<String> result = minioUtil.listBuckets();

        // Then
        assertEquals(Arrays.asList("bucket1", "bucket2"), result);
    }

    @Test
    void testListFiles() throws Exception {
        // Given
        String bucketName = "test-bucket";
        Item item1 = mock(Item.class);
        Item item2 = mock(Item.class);
        when(item1.objectName()).thenReturn("file1");
        when(item2.objectName()).thenReturn("file2");
        when(item1.isDir()).thenReturn(false);
        when(item2.isDir()).thenReturn(false);
        Result<Item> result1 = mock(Result.class);
        Result<Item> result2 = mock(Result.class);
        when(result1.get()).thenReturn(item1);
        when(result2.get()).thenReturn(item2);
        when(minioClient.listObjects(any(ListObjectsArgs.class))).thenReturn(Arrays.asList(result1, result2));

        // When
        List<Fileinfo> result = minioUtil.listFiles(bucketName);

        // Then
        assertEquals(2, result.size());
        assertEquals("file1", result.get(0).getFilename());
        assertEquals("file2", result.get(1).getFilename());
    }

    @Test
    void testDownload() throws Exception {
        // Given
        String bucketName = "test-bucket";
        String objectName = "test-object";
        InputStream stream = mock(InputStream.class);
        GetObjectResponse getObjectResponse = mock(GetObjectResponse.class);
        when(getObjectResponse.readAllBytes()).thenReturn(stream.readAllBytes());
        when(minioClient.getObject(any(GetObjectArgs.class))).thenReturn(getObjectResponse);

        // When
        InputStream result = minioUtil.download(bucketName, objectName);

        // Then
        assertEquals(stream, result);
    }

    @Test
    void testDeleteBucket() throws Exception {
        // Given
        String bucketName = "test-bucket";

        // When
        minioUtil.deleteBucket(bucketName);

        // Then
        verify(minioClient, times(1)).removeBucket(any(RemoveBucketArgs.class));
    }

    @Test
    void testDeleteObject() throws Exception {
        // Given
        String bucketName = "test-bucket";
        String objectName = "test-object";

        // When
        minioUtil.deleteObject(bucketName, objectName);

        // Then
        verify(minioClient, times(1)).removeObject(any(RemoveObjectArgs.class));
    }
}
