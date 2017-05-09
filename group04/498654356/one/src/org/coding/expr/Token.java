package org.coding.expr;

import java.util.HashMap;
import java.util.Map;

public class Token {
	
	private static Map<String, Integer> priorities = new HashMap<String, Integer>();
	static {
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("*", 2);
		priorities.put("/", 2);
	}
	
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
	public boolean isOperation() {
		return TypeEnum.OPERATION.equals(this.type);
	}
	public boolean isNumber() {
		return TypeEnum.NUMBER.equals(this.type);
	}
	
	/**
	 * 比指定的 token 优先级高,返回  true; 反之亦然. (*** 同级返回 true)
	 * @return
	 */
	public boolean hasHigherPriority(Token token) {
		if(!this.isOperation() || !token.isOperation()) {
			throw new RuntimeException("illegality compare !!");
		}
		return priorities.get(this.value).compareTo(priorities.get(token.toString())) >= 0;
	}

}
