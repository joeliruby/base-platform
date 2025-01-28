package com.matariky.jobs.jobsService.assetitm.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseRfidfactoryCNExeclReqVoTest {

    @InjectMocks
    private BasicBaseRfidfactoryCNExeclReqVo basicbaserfidfactorycnexeclreqvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        String epc = "1234567890";
        String password = "password";
        String goodsName = "ItemName";
        String goodsCode = "ItemCode";
        String odContent = "BarcodeContent";
        String qrContent = "QRCodeContent";

        // When
        basicbaserfidfactorycnexeclreqvo.setEPC(epc);
        basicbaserfidfactorycnexeclreqvo.setPassword(password);
        basicbaserfidfactorycnexeclreqvo.setGoodsName(goodsName);
        basicbaserfidfactorycnexeclreqvo.setGoodsCode(goodsCode);
        basicbaserfidfactorycnexeclreqvo.setOdContent(odContent);
        basicbaserfidfactorycnexeclreqvo.setQrContent(qrContent);

        // Then
        assertThat(basicbaserfidfactorycnexeclreqvo.getEPC()).isEqualTo(epc);
        assertThat(basicbaserfidfactorycnexeclreqvo.getPassword()).isEqualTo(password);
        assertThat(basicbaserfidfactorycnexeclreqvo.getGoodsName()).isEqualTo(goodsName);
        assertThat(basicbaserfidfactorycnexeclreqvo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(basicbaserfidfactorycnexeclreqvo.getOdContent()).isEqualTo(odContent);
        assertThat(basicbaserfidfactorycnexeclreqvo.getQrContent()).isEqualTo(qrContent);
    }

    @Test
    void testNoArgsConstructor() {
        // Given
        BasicBaseRfidfactoryCNExeclReqVo vo = new BasicBaseRfidfactoryCNExeclReqVo();

        // Then
        assertThat(vo).isNotNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        String epc = "1234567890";
        String password = "password";
        String goodsName = "ItemName";
        String goodsCode = "ItemCode";
        String odContent = "BarcodeContent";
        String qrContent = "QRCodeContent";

        // When
        BasicBaseRfidfactoryCNExeclReqVo vo = new BasicBaseRfidfactoryCNExeclReqVo(epc, password, goodsName, goodsCode,
                odContent, qrContent);

        // Then
        assertThat(vo.getEPC()).isEqualTo(epc);
        assertThat(vo.getPassword()).isEqualTo(password);
        assertThat(vo.getGoodsName()).isEqualTo(goodsName);
        assertThat(vo.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(vo.getOdContent()).isEqualTo(odContent);
        assertThat(vo.getQrContent()).isEqualTo(qrContent);
    }
}
