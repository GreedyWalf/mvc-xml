package com.qs.dao.impl;

import com.qs.dao.BillDao;
import com.qs.entity.BillInfo;
import com.qs.entity.BillInfoExample;
import com.qs.service.mapper.BillInfoMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BillDaoImpl implements BillDao {

    @Autowired
    private SqlSessionFactory sessionFactory;


    public BillInfo findBillInfo(BillInfo billInfo) {
        BillInfoExample example = new BillInfoExample();
        BillInfoExample.Criteria criteria = example.createCriteria();
        criteria.andBillTypeEqualTo(billInfo.getBillType());
        example.setOrderByClause("create_time desc");
        SqlSession session = sessionFactory.openSession();
        BillInfoMapper mapper = session.getMapper(BillInfoMapper.class);
        BillInfo billInfoFromDB = mapper.selectByExample(example);
        System.out.println(billInfoFromDB);
        return billInfoFromDB;
    }
}
