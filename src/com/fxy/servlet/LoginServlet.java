package com.fxy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.model.Page;
import com.fxy.model.User;
import com.fxy.service.UserService;

/**
 * 用户登录
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * 接收get请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 接收post请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userno = request.getParameter("userno");//取得账号
		String password = request.getParameter("password");//取得密码
		
		UserService userService = new UserService();
		//调用业务层方法
		User user = userService.login(userno, password);
		if(user != null) {
			//查询用户
			List<User> list = userService.findUserList();
			//查询总页数
			int count = userService.findUserCount();//数据总数
			Page page = new Page();
			int total = 0;//总页数
			if(count % page.getPageSize() > 0) {
				total = count/page.getPageSize() + 1;
			}else {
				total = count/page.getPageSize();
			}
			
			//跳转到首页
			//request中的数据针对的是某一次请求，请求结束数据失效
			//request.setAttribute("user", user);
			//session对象针对的是同一个浏览器的不同窗口直接共享数据
			request.getSession().setAttribute("user", user);
			request.setAttribute("list", list);
			request.setAttribute("page", new Page().getPage());//当前第几页
			request.setAttribute("total", total);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			//登陆失败
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
