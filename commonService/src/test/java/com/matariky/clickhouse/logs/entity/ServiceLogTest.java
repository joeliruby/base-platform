package com.matariky.clickhouse.logs.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceLogTest {

    @InjectMocks
    private ServiceLog servicelog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        String timestamp = "2023-10-01T12:00:00Z";
        String errorMessage = "Error message";
        String serviceName = "ServiceName";
        String name = "OperationCommand";
        Integer kind = 1;
        BigDecimal durationNano = new BigDecimal("1234567890");
        String dbSystem = "mysql";
        String dbName = "testDB";
        String dbOperation = "SELECT";
        String httpMethod = "GET";
        String httpCode = "200";
        String httpRoute = "/api/test";
        String os = "Linux";
        String host = "localhost";
        String dbtype = "SQL";
        String dbop = "READ";
        String telemetryLanugage = "Java";
        String peerDomain = "example.com";
        String peerPort = "8080";
        String dbStatements = "SELECT * FROM table";
        String otlSdk = "OpenTelemetry";
        String connectionString = "jdbc:mysql://localhost:3306/testDB";
        String clientIp = "127.0.0.1";
        String dbTable = "table";
        Boolean hasError = true;
        String timeStart = "2023-10-01T12:00:00Z";
        String timeEnd = "2023-10-01T12:05:00Z";
        Long timeStartLong = 1696152000000L;
        Long timeEndLong = 1696152300000L;
        Integer perPage = 10;
        Integer offSet = 0;

        // When
        servicelog.setTimestamp(timestamp);
        servicelog.setErrorMessage(errorMessage);
        servicelog.setServiceName(serviceName);
        servicelog.setName(name);
        servicelog.setKind(kind);
        servicelog.setDurationNano(durationNano);
        servicelog.setDbSystem(dbSystem);
        servicelog.setDbName(dbName);
        servicelog.setDbOperation(dbOperation);
        servicelog.setHttpMethod(httpMethod);
        servicelog.setHttpCode(httpCode);
        servicelog.setHttpRoute(httpRoute);
        servicelog.setOs(os);
        servicelog.setHost(host);
        servicelog.setDbtype(dbtype);
        servicelog.setDbop(dbop);
        servicelog.setTelemetryLanugage(telemetryLanugage);
        servicelog.setPeerDomain(peerDomain);
        servicelog.setPeerPort(peerPort);
        servicelog.setDbStatements(dbStatements);
        servicelog.setOtlSdk(otlSdk);
        servicelog.setConnectionString(connectionString);
        servicelog.setClientIp(clientIp);
        servicelog.setDbTable(dbTable);
        servicelog.setHasError(hasError);
        servicelog.setTimeStart(timeStart);
        servicelog.setTimeEnd(timeEnd);
        servicelog.setTimeStartLong(timeStartLong);
        servicelog.setTimeEndLong(timeEndLong);
        servicelog.setPerPage(perPage);
        servicelog.setOffSet(offSet);

        // Then
        assertEquals(timestamp, servicelog.getTimestamp());
        assertEquals(errorMessage, servicelog.getErrorMessage());
        assertEquals(serviceName, servicelog.getServiceName());
        assertEquals(name, servicelog.getName());
        assertEquals(kind, servicelog.getKind());
        assertEquals(durationNano, servicelog.getDurationNano());
        assertEquals(dbSystem, servicelog.getDbSystem());
        assertEquals(dbName, servicelog.getDbName());
        assertEquals(dbOperation, servicelog.getDbOperation());
        assertEquals(httpMethod, servicelog.getHttpMethod());
        assertEquals(httpCode, servicelog.getHttpCode());
        assertEquals(httpRoute, servicelog.getHttpRoute());
        assertEquals(os, servicelog.getOs());
        assertEquals(host, servicelog.getHost());
        assertEquals(dbtype, servicelog.getDbtype());
        assertEquals(dbop, servicelog.getDbop());
        assertEquals(telemetryLanugage, servicelog.getTelemetryLanugage());
        assertEquals(peerDomain, servicelog.getPeerDomain());
        assertEquals(peerPort, servicelog.getPeerPort());
        assertEquals(dbStatements, servicelog.getDbStatements());
        assertEquals(otlSdk, servicelog.getOtlSdk());
        assertEquals(connectionString, servicelog.getConnectionString());
        assertEquals(clientIp, servicelog.getClientIp());
        assertEquals(dbTable, servicelog.getDbTable());
        assertEquals(hasError, servicelog.getHasError());
        assertEquals(timeStart, servicelog.getTimeStart());
        assertEquals(timeEnd, servicelog.getTimeEnd());
        assertEquals(timeStartLong, servicelog.getTimeStartLong());
        assertEquals(timeEndLong, servicelog.getTimeEndLong());
        assertEquals(perPage, servicelog.getPerPage());
        assertEquals(offSet, servicelog.getOffSet());
    }

    // Add more test methods for other methods in ServiceLog
}
