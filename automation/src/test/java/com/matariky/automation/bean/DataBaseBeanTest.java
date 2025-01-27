package com.matariky.automation.bean;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class DataBaseBeanTest {

    private DataBaseBean dataBaseBean;

    @BeforeEach
    public void setUp() {
        dataBaseBean = new DataBaseBean();
    }

    @Test
    public void testGetAndSetTablename() {
        String tablename = "testTable";
        dataBaseBean.setTablename(tablename);
        assertThat(dataBaseBean.getTablename()).isEqualTo(tablename);
    }

    @Test
    public void testGetAndSetCtablename() {
        String ctablename = "testCTable";
        dataBaseBean.setCtablename(ctablename);
        assertThat(dataBaseBean.getCtablename()).isEqualTo(ctablename);
    }

    @Test
    public void testGetAndSetList() {
        List<DataBaseTableBean> list = new ArrayList<>();
        DataBaseTableBean tableBean = new DataBaseTableBean();
        list.add(tableBean);
        dataBaseBean.setList(list);
        assertThat(dataBaseBean.getList()).isEqualTo(list);
    }
}