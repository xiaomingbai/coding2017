package org.coding.expr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class TokenParse {

	private static final char[] OPER_ARR = {'+', '-', '*', '/'};
	
	public List<Token> parse(String expr) {
		if (expr == null || expr.isEmpty()) {
			return null;
		}
		
		List<Token> tokens = new ArrayList<Token>();
		int length = expr.length();
		int index = 0;
		while (index < length) {
			char ch = expr.charAt(index);
			if (ArrayUtils.contains(OPER_ARR, ch)) {
				Token token = new Token(TypeEnum.OPERATION, String.valueOf(ch));
				tokens.add(token);
				index++;
			} else if (Character.isDigit(ch)) {
				StringBuilder builder = new StringBuilder();
				do {
					builder.append(ch);
					index++;
					if (index  == length ) {	//*** 最后一位处理
						break;
					}
					ch = expr.charAt(index);
				} while(Character.isDigit(ch));
				
				Token token = new Token(TypeEnum.NUMBER, builder.toString());
				tokens.add(token);
			} else {
				throw new RuntimeException("unkonw char: ");
			}
		}
		return tokens;
	}

}
