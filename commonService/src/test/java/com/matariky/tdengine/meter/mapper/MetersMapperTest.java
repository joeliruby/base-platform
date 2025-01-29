package com.matariky.tdengine.meter.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.matariky.tdengine.meter.bean.Meters;
import com.matariky.tdengine.meter.vo.MetersVO;

@SpringBootTest
public class MetersMapperTest {

    @InjectMocks
    private MetersMapper metersmapper;

    @Mock
    private MetersMapper metersMapperMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertOne() {
        // Given
        Meters meters = new Meters();
        doNothing().when(metersMapperMock).insertOne(meters);

        // When
        metersmapper.insertOne(meters);

        // Then
        verify(metersMapperMock, times(1)).insertOne(meters);
    }

    @Test
    void testInsertBatch() {
        // Given
        String table = "test_table";
        List<Meters> metersList = Arrays.asList(new Meters(), new Meters());
        doNothing().when(metersMapperMock).insertBatch(table, metersList);

        // When
        metersmapper.insertBatch(table, metersList);

        // Then
        verify(metersMapperMock, times(1)).insertBatch(table, metersList);
    }

    @Test
    void testSelectByTime() {
        // Given
        String code = "test_code";
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        List<MetersVO> expectedList = Arrays.asList(new MetersVO(), new MetersVO());
        when(metersMapperMock.selectByTime(code, startTime, endTime)).thenReturn(expectedList);

        // When
        List<MetersVO> resultList = metersmapper.selectByTime(code, startTime, endTime);

        // Then
        assertEquals(expectedList, resultList);
    }

    @Test
    void testDeleteAll() {
        // Given
        String table = "test_table";
        doNothing().when(metersMapperMock).deleteAll(table);

        // When
        metersmapper.deleteAll(table);

        // Then
        verify(metersMapperMock, times(1)).deleteAll(table);
    }
}
