package com.baidu.controller;

import com.baidu.po.Food;
import com.baidu.po.FoodType;
import com.baidu.service.FoodService;
import com.baidu.service.FoodTypeService;
import com.baidu.service.impl.FoodServiceImpl;
import com.baidu.service.impl.FoodTypeServiceImpl;
import com.baidu.utils.PageUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FoodController extends HttpServlet {
    private FoodService foodService=new FoodServiceImpl();
    private FoodTypeService foodTypeService=new FoodTypeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("list".equals(method)){
            list(request,response);
        }else if("search".equals(method)){
            toSearch(request,response);
        }else if("toSaveFood".equals(method)){
            toSave(request,response);
        }else if ("add".equals(method)){
            toAdd(request,response);
        }else if ("del".equals(method)){
            toDel(request,response);
        }else if("show".equals(method)){
            toShow(request,response);
        }
    }

    private void toShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Food> food = foodService.findList();
        List<FoodType> type = foodTypeService.findList();
        request.setAttribute("food",food);
        request.setAttribute("type",type);
        request.getRequestDispatcher("/sys/food/updateFood.jsp").forward(request,response);
    }
//    删除
    private void toDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        foodService.toDel(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath()+"/food?method=list");
    }
    //添加
    private void toAdd(HttpServletRequest request, HttpServletResponse response) {
       try {
           FileItemFactory factory=new DiskFileItemFactory();
           //文件上传工厂
           ServletFileUpload upload=new ServletFileUpload(factory);
           upload.setFileSizeMax(10*1024*1024);//单个文件大小限制
           upload.setFileSizeMax(50*1024*1024);//最大文件大小限制
           upload.setHeaderEncoding("utf-8");//对中文字符进行汇编
           //判断文件上传
           if(upload.isMultipartContent(request)){
               Food food=new Food();
               //对当前request内容进行解析
               List<FileItem> list = upload.parseRequest(request);
               for (FileItem fileItem:list){
                   //判断当前fileItem是否为普通字段
                   if (fileItem.isFormField()){
                       String fieldName = fileItem.getFieldName();
                       String value = fileItem.getString();
                       value=new String(value.getBytes("iso8859-1"),"utf-8");
                       BeanUtils.setProperty(food,fieldName,value);
                   }else {
                       //文件上传
                       String fieldName = fileItem.getFieldName();
                       String name = fileItem.getName();
                       //获取当前部署服务器路径
                       String path = request.getServletContext().getRealPath("/upload");
                       File file=new File(path);
                       if (!file.exists()){
                           file.mkdir();
                       }
                       File file1=new File(path,name);
                       if (!file1.isDirectory()){
                           fileItem.write(file1);
                       }
                       BeanUtils.setProperty(food,fieldName,"upload/"+name);
                       fileItem.delete();
                   }
               }
               //保存到数据库
               foodService.toAdd(food);
               list(request,response);
           }
       }catch (Exception e){
           e.getStackTrace();
       }
    }
    //更新准备
    private void toSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<FoodType> foodTypes = this.foodTypeService.findList();
        request.setAttribute("foodtypes",foodTypes);
        request.getRequestDispatcher("/sys/food/saveFood.jsp").forward(request,response);
    }
    //查询
    private void toSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Food> list = this.foodService.findName(keyword);
        request.setAttribute("list",list);
        response.sendRedirect(request.getContextPath()+"/food?method=list");
    }
    //列表显示
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        Integer pageSize=5;
        Integer count=foodService.count();
        PageUtils pageUtils=new PageUtils(page,pageSize,count);
//        List<Food> list = this.foodService.findLimit((pageUtils.getCurrentPage()-1)*pageSize,pageSize);
        List<FoodType> types = this.foodTypeService.findList();
        List<Food> list = this.foodService.findList();
        request.setAttribute("list",list);
        request.setAttribute("types",types);
        request.getRequestDispatcher("/sys/food/foodList.jsp").forward(request,response);

    }
}
