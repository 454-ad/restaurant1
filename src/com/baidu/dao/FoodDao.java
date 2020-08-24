package com.baidu.dao;

import com.baidu.po.Food;
import com.baidu.po.FoodType;

import java.util.List;

public interface FoodDao {
//   显示美食
    List<Food> findList();//查询全部
    List<Food> findLimit(Integer i,Integer j); //分页设置
    Integer count();  //food总条数
//    查询
    List<Food> findName(String name);  //根据name查询
    Food findById(Integer id); //通过id查询
    FoodType findByFood(Integer id);  //修改菜名的所属菜系
    List<Food> findType(Integer foodType_id); //根据菜系id查询菜品
    Integer countType(Integer foodType_id);  //每个菜系的菜品数
//    添加
    void toAdd(Food food);
//    修改
    void toUp(Food food);
    void noImgUp(Food food);
//   删除
    void toDel(Integer id);
}
