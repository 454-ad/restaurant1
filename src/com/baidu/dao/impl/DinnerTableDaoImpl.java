package com.baidu.dao.impl;

import com.baidu.dao.DinnerTableDao;
import com.baidu.po.DinnerTable;
import com.baidu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DinnerTableDaoImpl implements DinnerTableDao {
    @Override
    public List<DinnerTable> findList() {
        String sql="select * from dinnertable";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<DinnerTable> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DinnerTable> findName(String name) {
        String sql="select * from dinnertable where tableName like '%"+name+"%'";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        List<DinnerTable> list=null;
        try {
            list= qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void toDel(Integer id) {
        String sql="delete from dinnertable where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toAdd(DinnerTable dinnerTable) {
        String sql="insert into dinnertable set tableName=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,dinnerTable.getTableName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUp0(DinnerTable dinnerTable) {
        String sql="update dinnertable set tableStatus=0,orderDate=? where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,dinnerTable.getOrderDate(),dinnerTable.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toUp1(DinnerTable dinnerTable) {
        String sql="update dinnertable set tableStatus=1,orderDate=? where id=?";
        QueryRunner qr = JdbcUtils.getQuerrRunner();
        try {
            qr.update(sql,dinnerTable.getOrderDate(),dinnerTable.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
