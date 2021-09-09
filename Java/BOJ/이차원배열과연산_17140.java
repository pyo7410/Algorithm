import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 이차원배열과연산_17140 {
	public static class Info implements Comparable<Info> {
		int num, cnt;

		public Info(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Info o) {
			// 문제의 조건에서 나온 횟수가 많은 순으로 정렬을 시킨다
			int diff = Integer.compare(this.cnt, o.cnt);
			
			// 만약 나온 횟수가 같다면 숫자가 큰 순으로 정렬을 시킨다.
			return (diff == 0) ? Integer.compare(this.num, o.num) : diff;
		}
	}
	public static int r, c, k, maxR, maxC, answer;
	public static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[100][100];
		
		for (int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < 3; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음 시작때 최대 행과 열은 3
		maxR = 3;
		maxC = 3;
		
		answer = getTime();
		
		System.out.println(answer);
	}
	
	public static int getTime() {
		int time = 0;
		
		// 배열의 (r, c) 위치의 값이 k가 아니고 time이 100을 넘지 않을때까지 반복
		while (arr[r][c] != k && time <= 100) {
			// 문제의 조건에 따라 현재 배열의 R이 길면 R 함수로
			// 현재 배열의 C가 길면 C 함수로 보낸다.
			if (maxR >= maxC) {
				R();
			}
			else {
				C();
			}
			
			// 처리를 했으므로 시간 +1
			time++;
		}
		
		// 만약 time이 100이넘는다면 -1을 아니라면 시간을 그대로 리턴
		return (time > 100) ? -1 : time;
	}
	
	public static void R() {
		// 가장 긴 길이를 저장
		int maxCol = maxC;
		
		for (int i = 0; i < maxR; ++i) {
			// 맵을 사용하여 숫자의 나온 횟수를 구한다.
			Map<Integer, Integer> map = new HashMap<>();
			
			for (int j = 0; j < maxC; ++j) {
				// 0이라면 숫자의 개수를 셀 필요가 없으므로 다음 숫자가 0이 아닌 수일 수 있기에 계속 탐색
				if (arr[i][j] == 0) {
					continue;
				}
				
				// 만약 맵에 포함된 숫자라면 기존 cnt에 +1을 아니라면 1을 맵에 넣는다
				if (map.containsKey(arr[i][j])) {
					map.put(arr[i][j], map.get(arr[i][j]) + 1);
				}
				else {
					map.put(arr[i][j], 1);
				}
			}
			
			// 정렬을 우선순위 큐를 사용해 수행
			PriorityQueue<Info> pq = new PriorityQueue<>();
			
			// map에 든 값들을 pq에 삽입
			for (int num : map.keySet()) {
				pq.add(new Info(num, map.get(num)));
			}
			
			int col = 0;
			// 문제의 조건에 의해 배열의 길이는 100이 넘어가면 안되므로 100까지만 반복
			while (!pq.isEmpty() && col < 100) {
				Info info = pq.poll();
				
				arr[i][col++] = info.num;
				arr[i][col++] = info.cnt;
			}
			
			// 가장 긴 길이를 저장한다.
			if (maxCol < col) {
				maxCol = col;
			}
			else {
				// 가장 긴 길이가 아니라면 오히려 기존의 길이보다 짧아졌을 수 도 있기 때문에
				// 짧아진 길이만큼 그 공간을 0으로 만들어 준다.
				for (int j = col; j < maxC; ++j) {
					arr[i][j] = 0;
				}
			}
		}
		
		// 다음 R함수나 C함수를 위해 현재까지 구한 가장 긴 길이를 저장한다.
		maxC = maxCol;
	}
	
	public static void C() {
		// 가장 긴 길이를 저장
		int maxRow = maxR;
		
		for (int i = 0; i < maxC; ++i) {
			// 맵을 사용하여 숫자의 나온 횟수를 구한다.
			Map<Integer, Integer> map = new HashMap<>();
			
			for (int j = 0; j < maxR; ++j) {
				// 0이라면 숫자의 개수를 셀 필요가 없으므로 다음 숫자가 0이 아닌 수일 수 있기에 계속 탐색
				if (arr[j][i] == 0) {
					continue;
				}
				
				// 만약 맵에 포함된 숫자라면 기존 cnt에 +1을 아니라면 1을 맵에 넣는다
				if (map.containsKey(arr[j][i])) {
					map.put(arr[j][i], map.get(arr[j][i]) + 1);
				}
				else {
					map.put(arr[j][i], 1);
				}
			}
			
			// 정렬을 우선순위 큐를 사용해 수행
			PriorityQueue<Info> pq = new PriorityQueue<>();
			
			// map에 든 값들을 pq에 삽입
			for (int num : map.keySet()) {
				pq.add(new Info(num, map.get(num)));
			}
			
			int row = 0;
			// 문제의 조건에 의해 배열의 길이는 100이 넘어가면 안되므로 100까지만 반복
			while (!pq.isEmpty() && row < 100) {
				Info info = pq.poll();
				
				arr[row++][i] = info.num;
				arr[row++][i] = info.cnt;
			}
			
			if (maxRow < row) {
				maxRow = row;
			}
			else {
				// 가장 긴 길이가 아니라면 오히려 기존의 길이보다 짧아졌을 수 도 있기 때문에
				// 짧아진 길이만큼 그 공간을 0으로 만들어 준다.
				for (int j = row; j < maxR; ++j) {
					arr[j][i] = 0;
				}
			}
		}
		
		// 다음 R함수나 C함수를 위해 현재까지 구한 가장 긴 길이를 저장한다.
		maxR = maxRow;
	}
}
