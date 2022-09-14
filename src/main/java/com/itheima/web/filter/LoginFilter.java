package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest req = (HttpServletRequest) request;

        //判断路径是否和登录注册相关 css 跳转注册
        String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "/register.jsp", "/registerServlet", "/checkCodeServlet"};

        String url = req.getRequestURL().toString();

        for (String u : urls) {
            if (url.contains(u)) {
                chain.doFilter(request, response);

                return;
            }
        }

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        if (user != null) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_msg", "您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req, response);
        }





    }
}
