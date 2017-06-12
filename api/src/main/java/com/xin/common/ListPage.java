package com.xin.common;

public class ListPage {
	private int pageCount;
	private int recordsCount;
	private int size;
	private int pageNo;

	public ListPage(int pageNo, int size, int count) {
		this.setPageNo(pageNo);

		this.setSize(size);
		this.setRecordsCount(count);
		int pageCount = count / size;
		int tmp = count % size;
		if (tmp != 0) {
			pageCount++;
		}
		this.setPageCount(pageCount);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}
