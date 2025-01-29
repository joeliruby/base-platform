package com.matariky.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StrUtilTest {

    @InjectMocks
    private StrUtil strutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsBlankChar() {
        assertThat(StrUtil.isBlankChar(' ')).isTrue();
        assertThat(StrUtil.isBlankChar('\n')).isTrue();
        assertThat(StrUtil.isBlankChar('a')).isFalse();
    }

    @Test
    void testIsBlank() {
        assertThat(StrUtil.isBlank(null)).isTrue();
        assertThat(StrUtil.isBlank("")).isTrue();
        assertThat(StrUtil.isBlank(" ")).isTrue();
        assertThat(StrUtil.isBlank("abc")).isFalse();
    }

    @Test
    void testIsEmpty() {
        assertThat(StrUtil.isEmpty(null)).isTrue();
        assertThat(StrUtil.isEmpty("")).isTrue();
        assertThat(StrUtil.isEmpty(" ")).isFalse();
        assertThat(StrUtil.isEmpty("abc")).isFalse();
    }

    @Test
    void testBytes() {
        assertThat(StrUtil.bytes("test", Charset.defaultCharset()))
                .isEqualTo("test".getBytes(Charset.defaultCharset()));
        assertThat(StrUtil.bytes(null, Charset.defaultCharset())).isNull();
    }

    @Test
    void testStr() {
        assertThat(StrUtil.str("test")).isEqualTo("test");
        assertThat(StrUtil.str(null)).isNull();
    }

    @Test
    void testStrFromBytes() {
        assertThat(StrUtil.str("test".getBytes(), Charset.defaultCharset())).isEqualTo("test");
        assertThat(StrUtil.str(null, Charset.defaultCharset())).isNull();
    }

    @Test
    void testSub() {
        assertThat(StrUtil.sub("abcdefgh", 2, 5)).isEqualTo("cde");
        assertThat(StrUtil.sub("abcdefgh", -3, -1)).isEqualTo("fg");
        assertThat(StrUtil.sub("abcdefgh", 5, 2)).isEqualTo("cde");
    }

    @Test
    void testSubPre() {
        assertThat(StrUtil.subPre("abcdefgh", 3)).isEqualTo("abc");
    }

    @Test
    void testSubSuf() {
        assertThat(StrUtil.subSuf("abcdefgh", 3)).isEqualTo("defgh");
    }

    @Test
    void testIndexOf() {
        assertThat(StrUtil.indexOf("abcdefgh", 'c')).isEqualTo(2);
        assertThat(StrUtil.indexOf("abcdefgh", 'z')).isEqualTo(-1);
    }

    @Test
    void testNullToDefault() {
        assertThat(StrUtil.nullToDefault(null, "default")).isEqualTo("default");
        assertThat(StrUtil.nullToDefault("test", "default")).isEqualTo("test");
    }

    @Test
    void testNullToEmpty() {
        assertThat(StrUtil.nullToEmpty(null)).isEqualTo("");
        assertThat(StrUtil.nullToEmpty("test")).isEqualTo("test");
    }
}
