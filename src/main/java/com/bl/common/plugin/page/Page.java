package com.bl.common.plugin.page;
import java.util.ArrayList;
import java.util.List;

/*
	The MIT License (MIT)

	Copyright (c) 2014 abel533@gmail.com

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
*/
import org.apache.ibatis.session.RowBounds;

/**
 * Mybatis - 分页对象
 * @version 3.2.0
 * @author liuzh/abel533/isea533
 * @url http://git.oschina.net/free/Mybatis_PageHelper
 */
public class Page<E> extends ArrayList<E> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**不进行count查询*/
    public static final int NO_SQL_COUNT = -1;
    public static final int SQL_COUNT = 0;
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;

    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, SQL_COUNT);
    }

    public Page(int pageNum, int pageSize, int total) {
        super(pageSize);
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    public Page(RowBounds rowBounds){
        super(rowBounds.getLimit());
        this.pageSize = rowBounds.getLimit();
        this.startRow = rowBounds.getOffset();
        //RowBounds方式默认不求count总数，如果想求count,可以修改这里为SQL_COUNT
        this.total = NO_SQL_COUNT;
        this.endRow = this.startRow+this.pageSize;
    }

    public List<E> getResult() {
        return this;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    
    public PageInfo<E> toPageInfo(){
    	 PageInfo<E> pageInfo = new PageInfo<E>();
    	 pageInfo.setTotal(this.total);
    	 pageInfo.setEndRow(this.endRow);
    	 pageInfo.setPageNum(this.pageNum);
    	 pageInfo.setPages(this.pages);
    	 pageInfo.setPageSize(this.pageSize);
    	 pageInfo.setResult(this.getResult());
    	 pageInfo.setStartRow(startRow);
    	return pageInfo;
    }
    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }
    
    
}
