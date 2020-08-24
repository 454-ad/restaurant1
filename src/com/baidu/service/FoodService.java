package com.baidu.service;

import com.baidu.po.Food;
import com.baidu.po.FoodType;

import java.util.List;

public interface FoodService {
    List<Food> findList();
    List<Food> findLimit(Integer i,Integer j);
    Food findById(Integer id); //通过id查询
    FoodType findByFood(Integer id);  //修改菜名的所属菜系
    List<Food> findType(Integer foodType_id); //根据菜系id查询菜品
    Integer countType(Integer foodType_id);  //每个菜系的菜品数
    Integer count();
    void toAdd(Food food);
    List<Food> findName(String name);
    void toUp(Food food);
    void noImgUp(Food food);
    void toDel(Integer id);
}
