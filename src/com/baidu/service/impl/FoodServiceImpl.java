package com.baidu.service.impl;

import com.baidu.dao.impl.FoodDaoImpl;
import com.baidu.po.Food;
import com.baidu.po.FoodType;
import com.baidu.service.FoodService;
import com.baidu.dao.FoodDao;

import java.util.List;

public class FoodServiceImpl implements FoodService {
    FoodDao foodDao=new FoodDaoImpl();
    @Override
    public List<Food> findList() {
        return this.foodDao.findList();
    }

    @Override
    public List<Food> findLimit(Integer i, Integer j) {
        return this.foodDao.findLimit(i,j);
    }

    @Override
    public Food findById(Integer id) {
        return foodDao.findById(id);
    }

    @Override
    public FoodType findByFood(Integer id) {
        return foodDao.findByFood(id);
    }

    @Override
    public List<Food> findType(Integer foodType_id) {
        return foodDao.findType(foodType_id);
    }

    @Override
    public Integer countType(Integer foodType_id) {
        return foodDao.countType(foodType_id);
    }

    @Override
    public Integer count() {
        return this.foodDao.count();
    }

    @Override
    public void toAdd(Food food) {
        this.foodDao.toAdd(food);
    }


    @Override
    public List<Food> findName(String name) {
        return this.foodDao.findName(name);
    }

    @Override
    public void toUp(Food food) {
        this.foodDao.toUp(food);
    }

    @Override
    public void noImgUp(Food food) {
        this.foodDao.noImgUp(food);
    }

    @Override
    public void toDel(Integer id) {
        this.foodDao.toDel(id);
    }
}
