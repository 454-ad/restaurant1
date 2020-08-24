package com.baidu.dao.impl;

import com.baidu.dao.FoodDao;
import com.baidu.po.Food;
import com.baidu.po.FoodType;
import com.baidu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.omg.PortableInterceptor.INACTIVE;

import java.sql.SQLException;
import java.util.List;

public class FoodDaoImpl implements FoodDao {

    @Override
    public List<Food> findList() {
        String sql="select * from food";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<Food> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<Food>(Food.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Food> findLimit(Integer i, Integer j) {
        String sql="select * from food limit ?,?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<Food> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<Food>(Food.class),i,j);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer count() {
        String sql="select count(*) from food";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        Integer count=null;
        try {
            count=((Long)qr.query(sql,new ScalarHandler<>())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    @Override
    public List<Food> findName(String name) {
        String sql="select * from food where foodName like ?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<Food> list=null;
        try {
            list = qr.query(sql, new BeanListHandler<Food>(Food.class),"%"+name+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Food findById(Integer id) {
        String sql="select * from food where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        Food food=null;
        try {
            food= qr.query(sql, new BeanHandler<>(Food.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public FoodType findByFood(Integer id) {
        String sql="select * from foodtype where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        FoodType foodType=null;
        try {
            foodType=qr.query(sql,new BeanHandler<>(FoodType.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodType;
    }

    @Override
    public List<Food> findType(Integer foodType_id) {
        String sql="select * from food where foodType_id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<Food> food=null;
        try {
            food=qr.query(sql,new BeanListHandler<Food>(Food.class),foodType_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food;
    }

    @Override
    public Integer countType(Integer foodType_id) {
        String sql="select count(*) from food where foodType_id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        Integer count=null;
        try {
            count=((Long) qr.query(sql,new ScalarHandler<>(),foodType_id)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void toAdd(Food food) {
        String sql="insert into food(foodName,foodType_id,price,mprice,remark,img) values (?,?,?,?,?,?)";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUp(Food food) {
        String sql="update food set foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg(),food.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noImgUp(Food food) {
        String sql="update food set foodName=?,foodType_id=?,price=?,mprice=?,remark=? where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toDel(Integer id) {
        String sql="delete from food where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
