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
 * 用户控制器
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		
		String action = request.getParameter("action");//取得操作行为
		String userid = request.getParameter("userid");//取得用户id
		UserService userService = new UserService();
		if(action != null && action.equals("delete")) {//删除操作
			boolean result = userService.deleteUser(userid);
			if(result) {
				//删除成功
				List<User> list = userService.findUserList();
				//跳转到首页
				//request.setAttribute("user", user);
				request.setAttribute("list", list);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else if(action != null && action.equals("toUpdate")){//跳转到修改界面
			User user = userService.findUserById(userid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("update.jsp").forward(request, response);	
		}else if(action != null && action.equals("update")) {
			String userno = request.getParameter("userno");//取得账号
			String password = request.getParameter("password");//取得密码
			String age = request.getParameter("age")!=null?request.getParameter("age"):"0";//取得年龄
			
			String name = request.getParameter("name");//取得姓名
			
			User user = new User();
			user.setUserid(Integer.parseInt(userid));
			user.setAge(Integer.parseInt(age));
			user.setName(name);
			user.setUserno(userno);
			user.setPassword(password);
			
			boolean result = userService.updateUser(user);
			if(result) {
				//修改成功
				//获取用户列表
				List<User> list = userService.findUserList();
				//跳转到首页
				//request.setAttribute("user", user);
				request.setAttribute("list", list);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else if(action != null && action.equals("toAdd")){//跳转到新增界面
			request.getRequestDispatcher("add.jsp").forward(request, response);
		}else if(action != null && action.equals("add")) {//新增用户
			String userno = request.getParameter("userno");//取得账号
			String password = request.getParameter("password");//取得密码
			String age = request.getParameter("age")!=null?request.getParameter("age"):"0";//取得年龄
			String name = request.getParameter("name");//取得姓名
			
			User user = new User();
			user.setAge(Integer.parseInt(age));
			user.setName(name);
			user.setUserno(userno);
			user.setPassword(password);
			
			boolean result = userService.addUser(user);
			if(result) {
				//添加成功
				//获取用户列表
				List<User> list = userService.findUserList();
				//跳转到首页
				//request.setAttribute("user", user);
				request.setAttribute("list", list);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else if(action != null && action.equals("findPage")) {//分页查询
			String page = request.getParameter("page") == null ? "1" : request.getParameter("page");//当前第几页
			Page p = new Page();
			p.setPage(Integer.parseInt(page));
			
			List<User> list = userService.findUserListByPage(p);
			//查询总页数
			int count = userService.findUserCount();//数据总数
			Page page2 = new Page();
			int total = 0;//总页数
			if(count % page2.getPageSize() > 0) {
				total = count/page2.getPageSize() + 1;
			}else {
				total = count/page2.getPageSize();
			}
			//跳转到首页
			request.setAttribute("list", list);
			request.setAttribute("page", page);//记录当前页数，便于页面取值
			request.setAttribute("total", total);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {//搜索
			String userno = request.getParameter("userno");//取得账号
			
			List<User> list = userService.findUserListByUserNo(userno);
			//跳转到首页
			//request.setAttribute("user", user);
			request.setAttribute("list", list);
			request.setAttribute("userno", userno);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
