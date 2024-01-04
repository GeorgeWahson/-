package com.itheima.web;

import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    BrandService service = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Brand> brands = service.selectAll();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("user", user);

        request.setAttribute("brands", brands);

        request.getRequestDispatcher("/brand.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
