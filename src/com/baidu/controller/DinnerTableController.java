package com.baidu.controller;

import com.baidu.po.DinnerTable;
import com.baidu.service.DinnerTableService;
import com.baidu.service.impl.DinnerTableServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DinnerTableController extends HttpServlet {
    private DinnerTableService dinnerTableService=new DinnerTableServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("list".equals(method)){
            list(request,response);
        }else if("select".equals(method)){
            select(request,response);
        }else if ("del".equals(method)){
            toDel(request,response);
        }else if ("add".equals(method)){
            toAdd(request,response);
        }else if ("up1".equals(method)){
            up1(request,response);
        }else if ("up0".equals(method)){
            up0(request,response);
        }
    }


    //预定和取消
    private void up0(HttpServletRequest request, HttpServletResponse response) {
        DinnerTable dinnerTable=new DinnerTable();
        try {
            BeanUtils.populate(dinnerTable,request.getParameterMap());
            this.dinnerTableService.toUp0(dinnerTable);
            response.sendRedirect(request.getContextPath()+"/table?method=list");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void up1(HttpServletRequest request, HttpServletResponse response) {
        DinnerTable dinnerTable=new DinnerTable();

        Date date = new Date(System.currentTimeMillis());
        dinnerTable.setOrderDate(date);
        try {
            BeanUtils.populate(dinnerTable,request.getParameterMap());
            this.dinnerTableService.toUp1(dinnerTable);
            response.sendRedirect(request.getContextPath()+"/table?method=list");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //添加
    private void toAdd(HttpServletRequest request, HttpServletResponse response) {
        DinnerTable dinnerTable=new DinnerTable();
        try {
            BeanUtils.populate(dinnerTable,request.getParameterMap());
            this.dinnerTableService.toAdd(dinnerTable);
            response.sendRedirect(request.getContextPath()+"/table?method=list");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //删除
    private void toDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)){
            dinnerTableService.toDel(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath()+"/table?method=list");
        }
    }

    //查询桌名
    private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<DinnerTable> list = this.dinnerTableService.findName(keyword);
        request.setAttribute("list",list);
        request.setAttribute("keyword",keyword);
        request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request,response);
    }
    //显示列表
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DinnerTable> list = this.dinnerTableService.findList();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/sys/board/boardList.jsp").forward(request,response);
    }
}
