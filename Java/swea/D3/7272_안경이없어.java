package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안경이없어_7272 {
	public static String text1, text2;
	
	public final static String NOHOLE = "CEFGHIJKLMNSTUVWXYZ";
	public final static String ONEHOLE = "ADOPQR";
	public final static String DOUBLEHOLE = "B";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			text1 = st.nextToken();
			text2 = st.nextToken();
			
//			text1 = transferText(text1);
//			text2 = transferText(text2);
			
			sb.append("#").append(tc).append(" ");
//			sb.append((text1.equals(text2) ? "SAME" : "DIFF"));
//			sb.append("\n");
			
			if (text1.length() != text2.length()) {
				sb.append("DIFF");
			}
			else {
				sb.append((checkText() ? "SAME" : "DIFF"));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static String transferText(String text) {
		text = text.replaceAll("C|E|F|G|H|I|J|K|L|M|N|S|T|U|V|W|X|Y|Z", "-");
		text = text.replaceAll("A|D|O|P|Q|R", "0");
		text = text.replace('B', '8');
		
		return text;
	}
	
	public static boolean checkText() {
		int len = text1.length();
		
		boolean flag = false;
		for (int i = 0; i < len; ++i) {
			if (NOHOLE.contains(String.valueOf(text1.charAt(i))) &&
					NOHOLE.contains(String.valueOf(text2.charAt(i)))) {
				flag = true;
			}
			
			if (ONEHOLE.contains(String.valueOf(text1.charAt(i))) &&
					ONEHOLE.contains(String.valueOf(text2.charAt(i)))) {
				flag = true;
			}
			
			if (DOUBLEHOLE.contains(String.valueOf(text1.charAt(i))) &&
					DOUBLEHOLE.contains(String.valueOf(text2.charAt(i)))) {
				flag = true;
			}
			
			if (!flag) {
				return false;
			}
			
			flag = false;
		}
		
		return true;
	}
}
