package org.coding.expr;

import java.util.ArrayList;
import java.util.List;

import org.coding.one.Stack;

/**
 * 中序表达式转后序表达式
 */
public class InfixToPostfix {

	public static List<Token> convert(String expr) {
		TokenParse parse = new TokenParse();
		List<Token> tokens = parse.parse(expr);
		List<Token> result = new ArrayList<Token>();
		Stack operationStack = new Stack();
		//没有括号的情况下，数字的顺序是不变；
		for (Token token : tokens) {
			if (token.isOperation()) {
				//前一个操作符优先级高就直接放入结果集中。??
				while (!operationStack.isEmpty()
						&& ((Token)operationStack.peek()).hasHigherPriority(token) ) {
					result.add((Token)operationStack.pop());
				}
				operationStack.push(token);
			} else if (token.isNumber()) {
				result.add(token);
			}
		}
		while(!operationStack.isEmpty()) {
			result.add((Token) operationStack.pop());
		}
		return result;
	}

}
