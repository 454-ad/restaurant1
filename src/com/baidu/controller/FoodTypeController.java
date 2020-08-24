package com.baidu.controller;

import com.baidu.po.FoodType;
import com.baidu.service.FoodTypeService;
import com.baidu.service.impl.FoodTypeServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class FoodTypeController extends HttpServlet {
    FoodTypeService foodTypeService=new FoodTypeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("list".equals(method)){
            list(request,response);
        }else if ("search".equals(method)){
            find(request,response);
        }else if ("delete".equals(method)){
            toDel(request,response);
        }else if("add".equals(method)){
            toAdd(request,response);
        }else if ("update".equals(method)){
            toUp(request,response);
        }else if ("show".equals(method)){
            toShow(request,response);
        }
    }

    private void toShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        FoodType foodType = this.foodTypeService.toShow(Integer.parseInt(id));
        request.setAttribute("foodType",foodType);
        request.getRequestDispatcher("/sys/foodtype/updateCuisine.jsp").forward(request,response);
    }

    private void toUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FoodType foodType=new FoodType();
        String typeName = request.getParameter("typeName");
//        Integer id = Integer.parseInt(request.getParameter("id"));
        foodType.setTypeName(typeName);
//        foodType.setId(id);
        this.foodTypeService.toUp(foodType);
        response.sendRedirect(request.getContextPath()+"/foodType?method=list");
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeName = request.getParameter("typeName");
        FoodType foodType=new FoodType();
        foodType.setTypeName(typeName);
        this.foodTypeService.toAdd(foodType);
        response.sendRedirect(request.getContextPath()+"/foodType?method=list");
    }

    private void toDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)){
            this.foodTypeService.toDel(Integer.parseInt(id));
            response.sendRedirect(request.getContextPath()+"/foodType?method=list");
        }else {
            response.getWriter().write("error");
        }
    }

    //查询
    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<FoodType> list = this.foodTypeService.findType(keyword);
        request.setAttribute("list",list);
        request.setAttribute("keyword",keyword);
        request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp").forward(request,response);
    }
    //显示菜品
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodType> list = this.foodTypeService.findList();
        request.setAttribute("list",list);
        request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp").forward(request,response);
    }
}
