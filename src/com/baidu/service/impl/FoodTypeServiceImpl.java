package com.baidu.service.impl;

import com.baidu.dao.FoodTypeDao;
import com.baidu.dao.impl.FoodTypeDaoImpl;
import com.baidu.po.FoodType;
import com.baidu.service.FoodTypeService;

import java.util.List;

public class FoodTypeServiceImpl implements FoodTypeService {
    FoodTypeDao foodTypeDao=new FoodTypeDaoImpl();
    @Override
    public List<FoodType> findList() {
        return this.foodTypeDao.findList();
    }

    @Override
    public List<FoodType> findType(String name) {
        return this.foodTypeDao.findType(name);
    }

    @Override
    public void toAdd(FoodType foodType) {
        this.foodTypeDao.toAdd(foodType);
    }


    @Override
    public void toDel(Integer id) {
        this.foodTypeDao.toDel(id);
    }

    @Override
    public void toUp(FoodType foodType) {
        this.foodTypeDao.toUp(foodType);
    }

    @Override
    public FoodType toShow(Integer id) {
        return this.foodTypeDao.toShow(id);
    }
}
