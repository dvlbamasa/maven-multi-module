package app;

public class Element {
	
	private String key;
	private String value;
	private int row;
	private int column;

	public Element(String key, String value, int row, int column) {
		this.key = key;
		this.value = value;
		this.row = row;
		this.column = column;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRow() {
		return row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

}