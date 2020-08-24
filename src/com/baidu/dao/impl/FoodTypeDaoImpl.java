package com.baidu.dao.impl;

import com.baidu.dao.FoodTypeDao;
import com.baidu.po.FoodType;
import com.baidu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class FoodTypeDaoImpl implements FoodTypeDao {
    @Override
    public List<FoodType> findList() {
        String sql="select * from foodtype";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<FoodType> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<FoodType> findType(String name) {
        String sql="select * from foodtype where typeName like '%"+name+"%'";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<FoodType> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void toAdd(FoodType foodType) {
        String sql="insert into foodtype (typeName) value (?)";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,foodType.getTypeName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toDel(Integer id) {
        String sql="delete from foodtype where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUp(FoodType foodType) {
        String sql="update foodtype set typeName=? where id=?";

        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,foodType.getTypeName(),foodType.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FoodType toShow(Integer id) {
        String sql="select typeName from foodtype where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        FoodType foodType=null;
        try {
            foodType = qr.query(sql, new BeanHandler<FoodType>(FoodType.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodType;
    }
}
