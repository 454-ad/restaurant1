package com.baidu.service;

import com.baidu.po.FoodType;

import java.util.List;

public interface FoodTypeService {
    List<FoodType> findList();
    List<FoodType> findType(String name);
    void toAdd(FoodType foodType);
    void toDel(Integer id);
    void toUp(FoodType foodType);
    FoodType toShow(Integer id);
}
