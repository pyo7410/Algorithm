import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 일곱난쟁이_2309 {
	public static int[] dwarf = new int[9];
	public static boolean[] isSelected = new boolean[9];
	public static StringBuilder sb = new StringBuilder("");
	
 	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; ++i) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(dwarf);
		powerset(0);
	}
 	
 	public static void powerset(int idx) {
 		if (idx == 9) {
 			int sum = 0; 			
 			int selectedCnt = 0;
 			
 			for (int i = 0; i < 9; ++i) {
 				if (isSelected[i]) {
 					sum += dwarf[i];
 					sb.append(dwarf[i]).append("\n");
 					selectedCnt++;
 				}
 			}
 			
 			if (selectedCnt != 7 || sum != 100) {
 				sb.setLength(0);
 				return;
 			}
 			
 			System.out.println(sb);
			System.exit(0);
 		}
 		
 		isSelected[idx] = true;
 		powerset(idx + 1);
 		isSelected[idx] = false;
 		powerset(idx + 1);
 	}
}

/*
import java.util.Arrays;
import java.util.Scanner;

public class 난쟁이v2 {
	static int[] arr = new int[9];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 9; ++i) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		dwarf(0, 0, 0);
	}
	
	// 고른 난쟁이를 저장하는 배열
	static int[] result = new int[7];
	// idx : arr의 인덱스 위치, cnt : result에 저장할 인덱스 위치, sum : 난쟁이들 키의 누적합
	// cnt와 idx가 따로 움직이므로 결국 9명의 난쟁이 중 7개를 뽑는 모든 경우를 볼 수 있다.
	public static void dwarf(int idx, int cnt, int sum) {
		if (cnt == 7) {
			if (sum == 100) {
				for (int i = 0; i < 7; ++i) {
					System.out.println(result[i]);
				}
				System.exit(0);				
			}

			return;
		}
		
		if (idx == 9) {
			return;
		}
		
		// idx번째 난쟁이를 고를거면 cnt도 증가하고 idx번째 난쟁이의 키를 누적합
		result[cnt] = arr[idx];
		dwarf(idx + 1, cnt + 1, sum + arr[idx]);
		// 안고를거면 cnt도 sum도 그대로 가자
		dwarf(idx + 1, cnt, sum);
	}
}
*/
