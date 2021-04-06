package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 키순서_5643 {
	public static int N, M, answer, outCnt;
	public static boolean[] visited;
	public static int[] totalCnt;
	public static List<Integer>[] students;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			students = new ArrayList[N + 1];
			totalCnt = new int[N + 1];
			
			for (int i = 1; i <= N; ++i) {
				students[i] = new ArrayList<Integer>();
			}
			
			for (int i = 1; i <= M; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				students[a].add(b);
			}
			
			int answer = 0;
			for (int i = 1; i <= N; ++i) {
				outCnt = 0;
				// 경로를 보는게 아니고 어떤 학생뒤에 설 수 있는지 보는 것이므로
				// 이미 방문한 학생에게는 다시 방문 X
				visited = new boolean[N + 1];
				visited[i] = true;
				go(i);
				totalCnt[i] += outCnt;
			}
			
//			Queue<Integer> q = new LinkedList<Integer>();
//			
//			for (int i = 1; i <= N; ++i) {
//				visited = new boolean[N + 1];
//				
//				q.offer(i);
//				visited[i] = true;
//				
//				while (!q.isEmpty()) {
//					int x = q.poll();
//					
//					totalCnt[i]++;
//					
//					for (int j = 0; j < students[x].size(); ++j) {
//						int y = students[x].get(j);
//						
//						if (visited[y]) {
//							continue;
//						}
//						
//						visited[y] = true;
//						totalCnt[y]++;
//						q.offer(y);
//					}
//				}
//			}
			
			answer = 0;
			for (int i = 1; i <= N; ++i) {
				if (totalCnt[i] == N) {
					answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void go(int n) {
		outCnt++;
		
		for (int i = 0; i < students[n].size(); ++i) {
			int student = students[n].get(i);
			if (visited[student]) {
				continue;
			}
			
			visited[student] = true;
			totalCnt[student]++;
			go(student);
		}
	}
}
