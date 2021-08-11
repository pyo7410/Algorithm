import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 단어수학_1339 {
	public static int N, alphaCnt, answer;
	public static Set<Character> alphabetSet;
	public static String[] word;
	public static int[] number;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		alphabetSet = new HashSet<Character>();
		word = new String[N];
		
		for (int i = 0; i < N; ++i) {
			word[i] = br.readLine();
			
			for (int j = 0; j < word[i].length(); ++j) {
				alphabetSet.add(word[i].charAt(j));
			}
		}
		
		alphaCnt = alphabetSet.size();
		number = new int[alphaCnt];
		int idx = 0;
		for (int i = 9; i > 9 - alphaCnt; i--) {
			number[idx++] = i;
		}
		
		Arrays.sort(number);
		
		do {
			int result = calc();
			
			answer = (answer < result) ? result : answer;
		} while (next_permutation(number));
		
		System.out.println(answer);
	}
	
	public static int calc() {
		Map<Character, Integer> alphabetMap = new HashMap<Character, Integer>();
		
		int idx = 0;
		for (char ch : alphabetSet) {
			alphabetMap.put(ch, number[idx++]);
		}
		
		int result = 0;
		for (int i = 0; i < N; ++i) {
			int num = 0;
			
			for (int j = 0; j < word[i].length(); ++j) {
				num = (num * 10) + alphabetMap.get(word[i].charAt(j));
			}
			
			result += num;
		}
		
		return result;
	}
	
	public static boolean next_permutation(int[] arr) {
		int i = alphaCnt - 1;
		
		while (i > 0 && arr[i - 1] >= arr[i]) {
			--i;
		}
		
		// 맨 앞까지 조사했지만 순열이 없으므로 false를 반환
		if (i == 0) {
			return false;
		}
		
		int j = alphaCnt - 1;
		while (arr[i - 1] >= arr[j]) {
			--j;
		}
		
		swap(arr, i - 1, j);
		
		int k = alphaCnt - 1;
		while (i < k) {
			swap(arr, i++, k--);
		}
		
		return true;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
