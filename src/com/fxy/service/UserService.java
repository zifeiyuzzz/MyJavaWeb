package com.fxy.service;

import java.util.List;

import com.fxy.dao.UserDao;
import com.fxy.model.Page;
import com.fxy.model.User;

/** 
* @author Memory 
* @date 2020-10-24 15:14:37 
* 用户业务类(承载用户数据)
*/
public class UserService {
	UserDao userDao = new UserDao();
	
	/**
	 * 用户登录
	 * @param userno 账号
	 * @param password 密码
	 * @return 用户对象，如果对象为空，则登陆失败，否则登陆成功。
	 */
	public User login(String userno, String password) {
		//处理密码加密业务。
		User user = userDao.login(userno, password);
		return user;
	}
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> findUserList() {
		return userDao.findUserList();
	}
	/**
	 * 搜索用户列表
	 * @return
	 */
	public List<User> findUserListByUserNo(String userno) {
		return userDao.findUserListByUserNo(userno);
	}
	/**
	 * 删除用户
	 * @param userid
	 * @return
	 */
	public boolean deleteUser(String userid) {
		
		return userDao.deleteUser(userid);
	}
	/**
	 * 根据id查询用户信息
	 * @param userid
	 * @return
	 */
	public User findUserById(String userid) {
		
		return userDao.findUserById(userid);
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	/**
	 * 分页查询
	 * @param p
	 * @return
	 */
	public List<User> findUserListByPage(Page p) {
		// TODO Auto-generated method stub
		return userDao.findUserListByPage(p);
	}
	/**
	 * 查询用户数据总数
	 * @return
	 */
	public int findUserCount() {
		// TODO Auto-generated method stub
		return userDao.findUserCount();
	}
}

