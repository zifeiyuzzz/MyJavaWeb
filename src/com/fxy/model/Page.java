package com.fxy.model;
/** 
* @author Memory
* @date 2020-11-21 9:38:16 
* 类说明 
*/
public class Page {
	private int page = 1;//当前第几页
	private int pageSize = 5;//每页多少条
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}

