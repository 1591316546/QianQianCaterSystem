package com.qiang.qianqiancater.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author QIANG
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        int width = 200;
        int height = 50;
        //创建一个图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //画笔
        Graphics g = image.getGraphics();
        //填充背景色
        g.setColor(Color.pink);
        g.fillRect(0, 0, width, height);
        //画边框
        g.setColor(Color.blue);
        g.drawRect(0, 0, width - 1, height - 1);

        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //画笔字体大小
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);//随机字符
            sb.append(ch);
            //写字符
            g.drawString(ch + "", width / 5 * (i+1), height / 2+ 15);
        }
        //将验证码存入session
        request.getSession().setAttribute("check_code_session",sb.toString());

        //画干扰线
        g.setColor(Color.green);
        for (int i = 0; i < 20; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);

            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);

            g.drawLine(x1,y1,x2,y2);
        }

        //输出图片到页面上
        ImageIO.write(image, "jpg", response.getOutputStream());

    }
}
