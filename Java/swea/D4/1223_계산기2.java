package D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 계산기2_1223 {
	public static char[] formula;
	public static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			String formula = post(input);
			
			sb.append("#").append(tc).append(" ").append(calc(formula));
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	public static char[] priority = new char[256];
	
	public static String post(String formula) {
		String postfix = "";
		Stack<Character> stk = new Stack<Character>();
		
		priority['*'] = 1;
		
		for (int i = 0; i < N; ++i)
	    {
	        if (formula.charAt(i) >= 48 && formula.charAt(i) <= 57) // 숫자일 경우
	        {
	            postfix += formula.charAt(i);
	        }
	        else // 연산자일 경우
	        {
	            if (!stk.empty())
	            {
	                while (priority[stk.peek()] >= priority[formula.charAt(i)])
	                {
	                    postfix += stk.pop();

	                    // stack이 비어버리면 while 조건문에서 검사를 진행할 수 없어 오류
	                    if (stk.empty())
	                    {
	                        break;
	                    }
	                }
	            }
	            stk.push(formula.charAt(i));
	        }
	    }

	    while (!stk.empty())
	    {
	        postfix += stk.pop();
	    }

	    return postfix;
	}
	
	public static int calc(String postfix)
	{
	    int result;
	    int len = postfix.length();

	    Stack<Integer> stk = new Stack<Integer>();
	    for (int i = 0; i < len; ++i)
	    {
	        if (postfix.charAt(i) >= 48 && postfix.charAt(i) <= 57)
	        {
	        	stk.push(postfix.charAt(i) - '0');
	        }
	        else
	        {
	            int y = stk.pop();
	            int x = stk.pop();

	            if (postfix.charAt(i) == '+')
	            {
	            	stk.push(x + y);
	            }
	            else if (postfix.charAt(i) == '*')
	            {
	            	stk.push(x * y);
	            }
	        }   
	    }

	    result = stk.pop();

	    return result;
	}
}
