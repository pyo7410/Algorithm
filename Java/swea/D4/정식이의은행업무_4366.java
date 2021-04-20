package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 정식이의은행업무_4366 {
	public static String binary, ternary;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			binary = br.readLine();
			ternary = br.readLine();
			
			// 이진수의 모든 경우를 십진수로 바꾸어 저장
			List<Integer> list = new ArrayList<Integer>();
			
			list.add(Integer.parseInt(binary, 2));
			for (int i = 0; i < binary.length(); ++i) {
				char[] changeBinary = binary.toCharArray();
				
				if (changeBinary[i] == '1') {
					changeBinary[i] = '0';
				}
				else {
					changeBinary[i] = '1';
				}
				
				list.add(Integer.parseInt(String.valueOf(changeBinary), 2));
			}
			
			int answer = 0;
			
			// 삼진수를 처리하면서 같은 십진수의 수가 list에  있는지 검사
			out: for (int i = 0; i < ternary.length(); ++i) {
				char[] changeTernary = ternary.toCharArray();
				
				for (int j = 1; j <= 2; ++j) {
					char originalBit = changeTernary[i];
					int changeBit = ((changeTernary[i] - '0') + j) % 3;
					
					changeTernary[i] = (char) (changeBit + '0');
					answer = Integer.parseInt(String.valueOf(changeTernary), 3);
					if (list.contains(answer)) {
						break out;
					}
					
					changeTernary[i] = originalBit;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
