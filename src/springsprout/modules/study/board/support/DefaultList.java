package springsprout.modules.study.board.support;

import java.util.List;

/**
 * 리스트 정보를 구성하기 위한 default 클래스
 * @author June
 *
 */
public class DefaultList<T> {
	
	private int total;
	private int page;
	private int records;
	private List<T> rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public List<T> getRows() {
		return rows;
	}
	
	
}
