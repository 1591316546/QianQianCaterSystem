package com.qiang.qianqiancater.servlet;

import com.qiang.qianqiancater.bean.Cuisine;
import com.qiang.qianqiancater.bean.PageBean;
import com.qiang.qianqiancater.service.CuisineService;
import com.qiang.qianqiancater.service.impl.CuisineServiceImpl;
import com.qiang.qianqiancater.utils.FileUploadUtil;
import com.qiang.qianqiancater.bean.Msg;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QIANG
 */
@WebServlet("/cuisine/*")
public class CuisineServlet extends BaseServlet {

    CuisineService cuisineService = new CuisineServiceImpl();

    /**
     * 根据类别id获取商品列表
     * 通过json返回一个分页对象
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdStr = request.getParameter("categoryId");
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");

        int pageSize = Integer.parseInt(pageSizeStr);
        int categoryId = Integer.parseInt(categoryIdStr);
        int currentPage = Integer.parseInt(currentPageStr);
        //获取到封装好的数据
        PageBean<Cuisine> pageBean = cuisineService.getCuiSineByCategory(categoryId, pageSize, currentPage);
        //封装消息
        Msg msg = Msg.success().add("page", pageBean);
        responseMsg(msg, response);
    }

    /**
     * 根据ID查询菜品信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        Cuisine cuisine = cuisineService.getCuisineById(cid);
        Msg msg = Msg.success().add("cuisine", cuisine);
        responseMsg(msg, response);
    }

    /**
     * 根据菜品ID获取大图
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCuisineBigImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //返回大图的路径
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        String imgPath = cuisineService.getCuisineBigImg(cid);
        Msg msg = Msg.success().add("imgPath", imgPath);
        responseMsg(msg, response);
    }

    /**
     * 搜索菜品
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void searchCuisines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");

        int pageSize = Integer.parseInt(pageSizeStr);
        int currentPage = Integer.parseInt(currentPageStr);
        //获取到封装好的数据
        PageBean<Cuisine> pageBean = cuisineService.getCuiSineByName(cname, pageSize, currentPage);
        //封装消息
        Msg msg = Msg.success().add("page", pageBean);
        responseMsg(msg, response);
    }

    /**
     * 响应特色菜列表
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getSpecialCuisines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cuisine> cuisines = cuisineService.getSpecialtyCuisine();
        responseMsg(Msg.success().add("cuisines", cuisines), response);
    }

    /**
     * 获取所有菜列表
     */
    public void getAllCuisines(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");
        int pageSize = 1;
        try {
            pageSize = Integer.parseInt(pageSizeStr);
        } catch (NumberFormatException e) {
        }
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(currentPageStr);
        } catch (NumberFormatException e) {
        }
        //获取到封装好的数据
        PageBean<Cuisine> pageBean = cuisineService.getAllCuiSines(pageSize, currentPage);
        //封装消息
        Msg msg = Msg.success().add("page", pageBean);
        responseMsg(msg, response);
    }

    /**
     * 添加一个菜品
     */
    public void addCuisine(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //保存提交上来的菜品的参数
        Map<String, Object> cuisineMap = new HashMap<>();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
            responseMsg(Msg.fail().add("hint", "保存失败"), response);
            throw e;
        }

        for (FileItem fi : fileItems) {
            //获取name属性的值
            String key = fi.getFieldName();
            if (fi.isFormField()) {
                //表单
                cuisineMap.put(key, fi.getString("utf-8"));
            } else {
                //文件
                String fileName = fi.getName();
                //截取文件名
                String realName = FileUploadUtil.getRealName(fileName);
                //获取一个随机文件名
                String uuidName = FileUploadUtil.getUUIDName(realName);
                //存储的文件夹
                String dir = "/cuisine";

                //获取输入流
                InputStream inputStream = fi.getInputStream();

                //获取"/img的真实路径
                String realPath = getServletContext().getRealPath("/img");
                //创建目录
                File dirFile = new File(realPath, dir);
                if (!dirFile.exists()) {
                    //目录不存在则创建
                    dirFile.mkdirs();
                }
                //输出流
                FileOutputStream os = new FileOutputStream(new File(dirFile, uuidName));
                //System.out.println(new File(dirFile, uuidName));
                //对拷流
                IOUtils.copy(inputStream, os);
                //释放资源
                inputStream.close();
                os.close();
                //删除临时文件
                fi.delete();
                //保存文件路径
                cuisineMap.put(key, "img" + dir + "/" + uuidName);
            }
        }
        //设置上架状态
        cuisineMap.put("putaway", "Y");
        //设置入库时间
        cuisineMap.put("joindate", new Date());

        //自动封装工具封装
        Cuisine cuisine = new Cuisine();
        try {
            BeanUtils.populate(cuisine, cuisineMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMsg(Msg.fail().add("hint", "保存失败"), response);
            throw e;
        }
        //保存
        boolean b = cuisineService.saveCuisine(cuisine);
        if (b) {
            //成功
            responseMsg(Msg.success(), response);
        } else {
            //失败
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 批量上下架
     */
    public void changePutaway(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] cid = request.getParameterValues("cid");
        String putaway = request.getParameter("putaway");
        boolean b = cuisineService.updatePutaway(cid, putaway);
        if (b) {
            responseMsg(Msg.success(), response);

        } else {
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 删除菜品，不可以删除菜品 ，无用接口
     */
    public void deleteCuisine(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] cid = request.getParameterValues("cid");
        boolean b = cuisineService.delete(cid);
        if (b) {
            responseMsg(Msg.success(), response);
        } else {
            responseMsg(Msg.fail(), response);
        }
    }

    /**
     * 接收上传上来的菜品图片并存储
     */
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //文件域处理
            if (!fileItem.isFormField()) {
                //获取文件名
                String fileName = fileItem.getName();
                String realName = FileUploadUtil.getRealName(fileName);
                String uuidName = FileUploadUtil.getUUIDName(realName);

                //获取 /img 的真实路径
                String imgRealPath = getServletContext().getRealPath("/img");
                String dir = "/cuisine";//存储的文件夹

                //获取输入流
                InputStream is = fileItem.getInputStream();

                //输出流
                FileOutputStream os = new FileOutputStream(new File(imgRealPath + dir, uuidName));

                //流对拷
                IOUtils.copy(is, os);
                //释放资源
                is.close();
                os.close();
                //删除临时文件
                fileItem.delete();
                //保存路径并响应
                String imgPath = "img" + dir + "/" + uuidName;
                responseMsg(Msg.success().add("imagePath", imgPath), response);
                return;
            }
        }
        responseMsg(Msg.fail(), response);
    }

    /**
     * 修改菜品信息
     */
    public void alterCuisine(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Cuisine cuisine = new Cuisine();
        BeanUtils.populate(cuisine,parameterMap);
//        System.out.println(cuisine);
        //修改菜品信息
        boolean b = cuisineService.updateCuisine(cuisine);
        if (b){//成功吗
            responseMsg(Msg.success(),response);
        }else {
            responseMsg(Msg.fail(),response);
        }
    }


}