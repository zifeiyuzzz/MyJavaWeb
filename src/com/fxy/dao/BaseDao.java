package com.fxy.dao;

import java.sql.*;

/** 
* @author Memory
* @date 2020-10-31 9:26:15 
* 数据库基类（公共类，用于操作数据库）
*/
public class BaseDao {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/bookdb";
	private String userno = "root";
	private String password = "";
	public Connection conn;				//存储数据库连接信息
	public PreparedStatement ps;		//执行sql命令
	public ResultSet rs;				//存储执行结果

	/**
	  * 获得数据库连接
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public Connection getConn() {
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.获得数据库连接
			this.conn = DriverManager.getConnection(url, userno, password);
			return conn;
		} catch (Exception e) {
			// 将异常信息写入到指定文件中（日志文件）
			e.printStackTrace();
		}
		return null;
	}
	/*
	public static void main(String[] args) {
		Connection conn = new BaseDao().getConn();
		System.out.println(conn);
	}
	*/
}

