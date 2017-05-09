package org.coding.expr;

import java.util.List;

import org.coding.one.Stack;

/**
 * 中序表达式求值
 */
public class InfixExpr {
	private String expr;
	public InfixExpr(String expr) {
		super();
		this.expr = expr;
	}
	
	public float evaluate() {
		TokenParse parse = new TokenParse();
		List<Token> tokens = parse.parse(this.expr);
		
		Stack operationStack = new Stack();
		Stack numberStack = new Stack();
		for (Token token : tokens) {
			if(token.isOperation()) {
				//一直向前找，直到比前一个优先级高为止.
				while (!operationStack.isEmpty() 
						&& ((Token) operationStack.peek()).hasHigherPriority(token)) {
					float result = doEvalute(numberStack, (Token) operationStack.pop());
					numberStack.push(result);
				}
				operationStack.push(token);
			}else if (token.isNumber()) {
				numberStack.push(Float.valueOf(token.getIntValue()));
			}
		}
		
		while(!operationStack.isEmpty()) {
			float result = doEvalute(numberStack, (Token) operationStack.pop());
			numberStack.push(result);
		}
		return (float) numberStack.pop();
	}

	private float doEvalute(Stack numberStack, Token token) {
		float num2 = (float) numberStack.pop();
		float num1 = (float) numberStack.pop();
		float result = 0;
		if("+".equals(token.toString())) {
			result = num1 + num2;
		} else if ("-".equals(token.toString())) {
			result = num1 - num2;
		} else if ("*".equals(token.toString())) {
			result = num1 * num2;
		} else if ("/".equals(token.toString())) {
			result = num1 / num2;
		} 
		return result;
	}


}
