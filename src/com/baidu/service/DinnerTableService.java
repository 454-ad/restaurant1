package com.baidu.service;

import com.baidu.po.DinnerTable;

import java.util.List;

public interface DinnerTableService {
    List<DinnerTable> findList();
    List<DinnerTable> findName(String name);
    void toDel(Integer id);
    void toAdd(DinnerTable dinnerTable);
    void toUp1(DinnerTable dinnerTable);
    void toUp0(DinnerTable dinnerTable);
}
