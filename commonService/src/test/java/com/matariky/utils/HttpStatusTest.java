package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class HttpStatusTest {

    @InjectMocks
    private HttpStatus httpstatus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessStatus() {
        assertThat(HttpStatus.SUCCESS).isEqualTo(200);
    }

    @Test
    void testCreatedStatus() {
        assertThat(HttpStatus.CREATED).isEqualTo(201);
    }

    @Test
    void testAcceptedStatus() {
        assertThat(HttpStatus.ACCEPTED).isEqualTo(202);
    }

    @Test
    void testNoContentStatus() {
        assertThat(HttpStatus.NO_CONTENT).isEqualTo(204);
    }

    @Test
    void testMovedPermStatus() {
        assertThat(HttpStatus.MOVED_PERM).isEqualTo(301);
    }

    @Test
    void testSeeOtherStatus() {
        assertThat(HttpStatus.SEE_OTHER).isEqualTo(303);
    }

    @Test
    void testNotModifiedStatus() {
        assertThat(HttpStatus.NOT_MODIFIED).isEqualTo(304);
    }

    @Test
    void testBadRequestStatus() {
        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(400);
    }

    @Test
    void testUnauthorizedStatus() {
        assertThat(HttpStatus.UNAUTHORIZED).isEqualTo(401);
    }

    @Test
    void testForbiddenStatus() {
        assertThat(HttpStatus.FORBIDDEN).isEqualTo(403);
    }

    @Test
    void testNotFoundStatus() {
        assertThat(HttpStatus.NOT_FOUND).isEqualTo(404);
    }

    @Test
    void testBadMethodStatus() {
        assertThat(HttpStatus.BAD_METHOD).isEqualTo(405);
    }

    @Test
    void testConflictStatus() {
        assertThat(HttpStatus.CONFLICT).isEqualTo(409);
    }

    @Test
    void testUnsupportedTypeStatus() {
        assertThat(HttpStatus.UNSUPPORTED_TYPE).isEqualTo(415);
    }

    @Test
    void testErrorStatus() {
        assertThat(HttpStatus.ERROR).isEqualTo(500);
    }

    @Test
    void testNotImplementedStatus() {
        assertThat(HttpStatus.NOT_IMPLEMENTED).isEqualTo(501);
    }
}
