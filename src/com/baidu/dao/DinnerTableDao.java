package com.baidu.dao;

import com.baidu.po.DinnerTable;

import java.util.List;

public interface DinnerTableDao {
//    显示全部
    List<DinnerTable> findList();
//    查询桌名
    List<DinnerTable> findName(String name);
//    删除桌
    void toDel(Integer id);
//    添加桌
    void toAdd(DinnerTable dinnerTable);
//    预定与取消
    void toUp0(DinnerTable dinnerTable);
    void toUp1(DinnerTable dinnerTable);
}
