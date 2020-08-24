package com.baidu.controller;

import com.baidu.po.DinnerTable;
import com.baidu.po.Food;
import com.baidu.service.DinnerTableService;
import com.baidu.service.FoodService;
import com.baidu.service.FoodTypeService;
import com.baidu.service.impl.DinnerTableServiceImpl;
import com.baidu.service.impl.FoodServiceImpl;
import com.baidu.service.impl.FoodTypeServiceImpl;
import com.baidu.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexController extends HttpServlet {
    private DinnerTableService dinnerTableService=new DinnerTableServiceImpl();
    private FoodTypeService foodTypeService=new FoodTypeServiceImpl();
    private FoodService foodService=new FoodServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method.equals("toApp")){
            list(request,response);
        }else if (method.equals("getMenu")){
            getMenu(request,response);
        }
    }
    //菜品列表显示----分页
    private void getMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("table_id");
        if (null == object){
            String table_id = request.getParameter("table_id");
            session.setAttribute("table_id",Integer.parseInt(table_id));
        }
        //分页
        String currentPage = request.getParameter("currentPage");
        Integer count = foodService.count();
        PageUtils pageUtils=new PageUtils(currentPage,6,count);

        //food分页
        List<Food> list= foodService.findLimit((pageUtils.getCurrentPage()-1)*6,6);
        request.setAttribute("list",list);
        request.setAttribute("pageUtils",pageUtils);
        request.getRequestDispatcher("/app/detail/caidan.jsp").forward(request,response);

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DinnerTable> listDinnerTable = dinnerTableService.findList();
        request.setAttribute("listDinnerTable",listDinnerTable);
        request.getRequestDispatcher("/app/index.jsp").forward(request,response);
    }
}
