package org.coding.expr;

public class Token {
	private TypeEnum type;
	private String value;
	public Token(TypeEnum type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	public int getIntValue() {
		return Integer.valueOf(this.value).intValue();
	}
	@Override
	public String toString() {
		return this.value;
	}

}
