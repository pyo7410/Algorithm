import java.util.Arrays;
import java.util.Scanner;

public class 성적매기기_1983 {
	
	public static float[] scores;
	public static String[] grades;
	public static String[] grade = {"D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-", "A0", "A+"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			scores = new float[n];
			grades = new String[n];
			
			int idx = 0;
			for (int i = 0; i < grade.length; ++i) {
				for (int j = 0; j < n / 10; ++j) {
					grades[idx++] = grade[i]; 
				}
			}
			
			for (int i = 0; i < n; ++i) {
				int midtermEx = sc.nextInt();
				int finalEx = sc.nextInt();
				int homework = sc.nextInt();
				
				scores[i] = (midtermEx * 0.35f) + (finalEx * 0.45f) + (homework * 0.2f);
			}
			
			float score = scores[k - 1];
			Arrays.sort(scores);
			
			idx = 0;
			for (int i = 0; i < n; ++i) {
				if (score == scores[i]) {
					idx = i;
				}
			}
			
			System.out.println("#" + tc + " " + grades[idx]);
		}
		
		sc.close();
	}
}
