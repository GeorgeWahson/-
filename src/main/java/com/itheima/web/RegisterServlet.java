package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        String checkCode = request.getParameter("checkCode");

        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");


        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            request.setAttribute("register_msg", "验证码错误");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }


        boolean flag = service.register(user);
        if (flag) {

            request.setAttribute("register_msg", "注册成功，请登录");
//            request.setAttribute("register_user", user);

            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else {
            request.setAttribute("register_msg", "用户名已存在");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
