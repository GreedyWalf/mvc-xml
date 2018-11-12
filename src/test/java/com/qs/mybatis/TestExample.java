package com.qs.mybatis;


import com.qs.dao.BillDao;
import com.qs.entity.BillInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/applicationContext.xml"})
public class TestExample {

    @Autowired
    private BillDao billDao;


    @Test
    public void testFindByExample(){
        BillInfo billInfo = new BillInfo();
        billInfo.setBillType("1");
        billDao.findBillInfo(billInfo);
    }
}
