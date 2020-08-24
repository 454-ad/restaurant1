package com.baidu.dao;

import com.baidu.po.FoodType;

import java.util.List;

public interface FoodTypeDao {
//    显示菜品
    List<FoodType> findList();
//    查询菜品
    List<FoodType> findType(String name);
//    添加
    void toAdd(FoodType foodType);
//     删除
    void toDel(Integer id);
//    更新
    void toUp(FoodType foodType);
    FoodType toShow(Integer id);
}
