package com.hro.core.common.util;

import java.util.Map;

public class QueryParams {
	
	//第几页
	private Integer pageNo;
	//页大小
	private Integer pageSize;
	//起始行
	private Integer curRecordNo;
	//结束行
	private Integer endRecordNo;
	//记录datatables第几次刷新,返回的时候在这基础上加1
	private Integer draw;
	//排序的列
	private String orderColumn;
	//排序规则
	private String orderDir;
	
	private Map<String, Object> params;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getCurRecordNo() {
		return curRecordNo;
	}

	public void setCurRecordNo(Integer curRecordNo) {
		this.curRecordNo = curRecordNo;
	}

	public Integer getEndRecordNo() {
		return endRecordNo;
	}

	public void setEndRecordNo(Integer endRecordNo) {
		this.endRecordNo = endRecordNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderDir() {
		return orderDir;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
}
