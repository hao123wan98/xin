package com.xin.common;

import java.util.List;

public class ListPageVO {
	private List<Object> list;
	private ListPage page;

	public List<Object> getList() {
		return list;
	}

	public <T> void setList(List<T> list) {
		this.list = (List<Object>) list;
	}

	public ListPage getPage() {
		return page;
	}

	public void setPage(ListPage page) {
		this.page = page;
	}

	public void setPage(int pageNo, int size, int count) {
		this.page = new ListPage(pageNo, size, count);
	}
}
