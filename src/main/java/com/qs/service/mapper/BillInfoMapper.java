package com.qs.service.mapper;

import com.qs.entity.BillInfo;
import com.qs.entity.BillInfoExample;

public interface BillInfoMapper {

    BillInfo selectByExample(BillInfoExample example);
}
