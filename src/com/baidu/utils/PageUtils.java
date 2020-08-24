package com.baidu.utils;
/**
 */
public class PageUtils {

	
	private int currentPage=0;
	private int prevPage=0;
	private int nextPage=0;
	private int lastPage=0;
	private int pageSize=0;
	private int count=0;
	
	
	public PageUtils(String page,int pageSize,int count){
		
		currentPage=Integer.parseInt(page==null?"1":page);
		
		
		this.pageSize=pageSize;
		this.count=count;
		
		
		initLastPage();
		initPrevPage();
		initnextPage();
		
		
	}

	private void initnextPage() {
		nextPage=currentPage==lastPage?lastPage:currentPage+1;
	}

	private void initPrevPage() {
		prevPage=currentPage==1?currentPage:currentPage-1;
	}

	private void initLastPage() {
		lastPage=count/pageSize;
		if(count%pageSize!=0){
			lastPage+=1;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	

	
	
	
}