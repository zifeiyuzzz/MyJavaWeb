package com.fxy.dao;

import java.util.ArrayList;
import java.util.List;

import com.fxy.model.Page;
import com.fxy.model.User;

/** 
* @author Memory
* @date 2020-10-24 16:07:47 
* 类说明 
*/
public class UserDao extends BaseDao{
	/**
	 * 用户登录
	 * @param userno 账号
	 * @param password 密码
	 * @return 用户对象，如果对象为空，则登陆失败，否则登陆成功。
	 */
	public User login(String userno, String password) {
		//执行sql查询
		String sql = "select * from t_user where userno='"+userno+"' and password='"+password+"'";

		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			while(rs.next()) {//如果有下一条
				String name = rs.getString("name");
				int userid = rs.getInt("user_id");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setUserno(userno);
				user.setUserid(userid);
				
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return null;
		
		/*
		 * 测试代码
		//返回结果
		User user = new User();
		user.setAddress("山东青岛");
		user.setAge(20);
		user.setEmail("125@qq.com");
		user.setName("张三");
		user.setPassword(password);
		user.setUserno(userno);
		return user;
		*/
	}
	
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> findUserList() {
		//执行sql查询
		String sql = "select * from t_user";
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			List<User> list = new ArrayList<User>();
			while(rs.next()) {//如果有下一条
				String name = rs.getString("name");
				int userid = rs.getInt("user_id");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String userno = rs.getString("userno");
				String password = rs.getString("password");
		
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setUserno(userno);
				user.setUserid(userid);
				
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	/**
	 * 
	 *搜索用户列表
	 * @return
	 */
	public List<User> findUserListByUserNo(String userno1) {
		//执行sql查询
		String sql = "select * from t_user where userno like '%" + userno1 + "%'";
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			List<User> list = new ArrayList<User>();
			while(rs.next()) {//如果有下一条
				String name = rs.getString("name");
				int userid = rs.getInt("user_id");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String userno = rs.getString("userno");
				String password = rs.getString("password");
		
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setUserno(userno);
				user.setUserid(userid);
				
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	public boolean deleteUser(String userid) {
		String sql = "delete from t_user where user_id="+userid;
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行删除、修改、新增命令
			//result:数据库受影响的行数
			int result = ps.executeUpdate();//成功或失败，true后false
			if(result>0) {
				//成功
				return true;
			}else {
				//失败
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}

	/**
	 * 根据id查询用户信息
	 * @param userid
	 * @return
	 */
	public User findUserById(String userid) {
		String sql = "select * from t_user where user_id=" + userid;
		try {
			//1、获得连接(连接数据库)
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			User user = null;
			if(rs.next()) {//如果有下一条
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String userno = rs.getString("userno");
				String password = rs.getString("password");
		
				user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setUserno(userno);
				user.setUserid(Integer.parseUnsignedInt(userid));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		String sql = "update t_user set userno=?,`password`=?,`name`=?,age=? where user_id=?";
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUserno());//第一个问号
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getAge());
			ps.setInt(5, user.getUserid());
			
			
			//3、执行删除、修改、新增命令
			//result:数据库受影响的行数
			int result = ps.executeUpdate();//成功或失败，true后false
			if(result>0) {
				//成功
				return true;
			}else {
				//失败
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		String sql = "insert into t_user(userno,`password`,`name`,age) values(?,?,?,?)";
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUserno());//第一个问号
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getAge());
//			ps.setString(5, user.getEmail());
//			ps.setString(6, user.getAddress());
			
			//3、执行删除、修改、新增命令
			//result:数据库受影响的行数
			int result = ps.executeUpdate();//成功或失败，true后false
			if(result>0) {
				//成功
				return true;
			}else {
				//失败
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}
	/**
	 * 分页查询
	 * @param p
	 * @return
	 */
	public List<User> findUserListByPage(Page p) {
		//执行sql查询
		String sql = "select * from t_user limit ?,?";
		
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			
			ps.setInt(1, (p.getPage() - 1) * p.getPageSize());//第一个问号
			ps.setInt(2, p.getPageSize());
			
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			List<User> list = new ArrayList<User>();
			while(rs.next()) {//如果有下一条
				String name = rs.getString("name");
				int userid = rs.getInt("user_id");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String userno = rs.getString("userno");
				String password = rs.getString("password");
		
				User user = new User();
				user.setAddress(address);
				user.setAge(age);
				user.setEmail(email);
				user.setName(name);
				user.setPassword(password);
				user.setUserno(userno);
				user.setUserid(userid);
				
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	/**
	 * 查询用户数据总数
	 * @return
	 */
	public int findUserCount() {
		//执行sql查询
		String sql = "select count(*) as count from t_user";
		int count =0;
		try {
			//1、获得连接
			this.conn = this.getConn();
			//2、获得执行命令对象
			this.ps = conn.prepareStatement(sql);
			//3、执行查询命令
			this.rs = ps.executeQuery();
			//4、取出执行结果
			while(rs.next()) {//如果有下一条
				count = rs.getInt("count");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return count;
	}
	
	
	
}

