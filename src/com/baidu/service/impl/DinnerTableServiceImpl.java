package com.baidu.service.impl;

import com.baidu.dao.DinnerTableDao;
import com.baidu.dao.impl.DinnerTableDaoImpl;
import com.baidu.po.DinnerTable;
import com.baidu.service.DinnerTableService;

import java.util.List;

public class DinnerTableServiceImpl implements DinnerTableService {
    private DinnerTableDao dinnerTableDao=new DinnerTableDaoImpl();
    @Override
    public List<DinnerTable> findList() {
        return this.dinnerTableDao.findList();
    }

    @Override
    public List<DinnerTable> findName(String name) {
        return this.dinnerTableDao.findName(name);
    }

    @Override
    public void toDel(Integer id) {
        this.dinnerTableDao.toDel(id);
    }

    @Override
    public void toAdd(DinnerTable dinnerTable) {
        this.dinnerTableDao.toAdd(dinnerTable);
    }

    @Override
    public void toUp1(DinnerTable dinnerTable) {
        this.dinnerTableDao.toUp1(dinnerTable);
    }

    @Override
    public void toUp0(DinnerTable dinnerTable) {
        this.dinnerTableDao.toUp0(dinnerTable);
    }
}
