import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점심식사시간_2383 {
	// 계단의 위치와 높이
	public static class StairInfo {
		int y, x, height;

		public StairInfo(int y, int x, int height) {
			super();
			this.y = y;
			this.x = x;
			this.height = height;
		}
	}
	
	// 각 사람들의 위치 정보
	public static class Info {
		int y, x;
		
		public Info(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	public static int N, answer;
	
	// 각 사람들이 어느 계단에 배정될지 저장
	public static int[] stairType;
	// 지도정보 저장
	public static int[][] map;
	
	public static List<StairInfo> stairList;
	public static List<Info> infoList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
	
			stairList = new ArrayList<StairInfo>();
			infoList = new ArrayList<Info>();
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if (map[i][j] > 1) {
						// 만약 계단이라면 계단 정보 리스트에 저장
						stairList.add(new StairInfo(i, j, map[i][j]));
					}
					else if (map[i][j] == 1) {
						// 만약 사람이라면 사람 정보 리스트에 저장
						infoList.add(new Info(i, j));
					}
				}
			}
			
			// 각 사람마다 어느 계단에 매칭될지 지정 
			stairType = new int[infoList.size()];
			
			answer = Integer.MAX_VALUE;
			powerset(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// 모든 계단의 경우를 다 살핀다.
	public static void powerset(int cnt) {
		if (cnt == infoList.size()) {
			// 시간상 가장 가까운 사람이 먼저 계단에 들어가야하므로 우선순위큐 사용
			PriorityQueue<Integer>[] pq = new PriorityQueue[2];
			
			// 0번 계단
			pq[0] = new PriorityQueue<Integer>();
			// 1번 계단
			pq[1] = new PriorityQueue<Integer>();
			
			// 각 사람마다 배정된 계단에 거리 즉, 문제에서 요구하는 시간을 구해 각 계단에 맞는 우선순위큐에 저장
			for (int i = 0; i < infoList.size(); ++i) {
				Info info = infoList.get(i);
				StairInfo stairInfo = stairList.get(stairType[i]);
				
				int time = Math.abs(info.y - stairInfo.y) + Math.abs(info.x - stairInfo.x);
				
				pq[stairType[i]].offer(time);
			}
			
			int max = 0;
			// 각 계단의 우선순위큐를 살핌
			for (int i = 0; i < 2; ++i) {
				// 해당하는 계단의 높이 저장
				int height = stairList.get(i).height;
				// 계단에 최대 3명만 내려갈 수 있으므로 이를 표현하기위해 큐를 사용
				Queue<Integer> stair = new LinkedList<Integer>();
				
				int time = 0;
				// 해당하는 계단의 우선순위큐를 통해 배정된 모든 사람들이 내려가는데 걸리는 시간을 계산
				while (!pq[i].isEmpty()) {
					// 현재 사람이 들어온 시간
					time = pq[i].poll();
					
					if (stair.size() < 3) {
						// 만약 계단에 3명의 사람이 없다면
						// +1의 시간부터 내려갈 수 있음을 주의하고 계단의 맨 아래 도착시간을 큐에 저장
						time += height + 1;
						stair.offer(time);
					}
					else {
						// 만약 계단에 3명의 사람이 다 있다면
						while (!stair.isEmpty() && stair.peek() <= time) {
							// 현재 사람이 들어온 시간보다 같거나 작은 시간을 전부 큐에서 제거
							stair.poll();
						}
						
						if (stair.size() >= 3) {
							// 제거했는데도 큐의 사이즈가 3이라면 큐의 맨 앞의 시간이 가장 빨리 도착하는 시간이므로
							// 현재 도착한 사람의 시간을 갱신
							// 이때, 현재 도착한 사람은 기다렸다가므로 +1분을 안해주고 바로 출발이 가능
							time = stair.poll();
						}
						else {
							// 제거를 통해 계단에 자리가 생겼다면 해당 사람은 바로 계단을 내려갈 수 있으므로
							// 문제의 조건에 따라 +1
							time += 1;
						}
						
						// 도착시간을 구하고 큐에 추가
						time += height;
						stair.offer(time);
					}					
				}
				
				// 시간이 0이될 수 있음
				if (time != 0) {
					max = (max < time) ? time : max;
				}
			}
			
			answer = (answer > max) ? max : answer;
			
			return;
		}
		
		// 0번 계단에 가는 경우
		stairType[cnt] = 0;
		powerset(cnt + 1);
		
		// 1번 계단에 가는 경우
		stairType[cnt] = 1;
		powerset(cnt + 1);
	}
}
