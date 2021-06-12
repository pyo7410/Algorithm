import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 캐슬디펜스_17135 {
	// 적군의 배열을 만들어서, 적군의 위치정보를 배열에 모아서 관리하면 조금 더 쉽다.
	public static int N, M, D;
	
	public static class Enemy {
		int r, c;

		public Enemy(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static List<Enemy> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					list.add(new Enemy(i, j));
				}
			}
		}
		
		int answer = 0;
		// 조합의 수가 작을때에는 배열을 돌리는게 재귀를 돌리는것보다 더 빠름
		for (int i = 0; i < M; ++i) {
			for (int j = i; j < M; ++j) {
				for (int k = j; k < M; ++k) 
				{	
					// 매번 리스트가 필요하므로 복사
					List<Enemy> list2 = new ArrayList<>();
//					list2.addAll(list);
					for (Enemy e : list) {
						list2.add(new Enemy(e.r, e.c));						
					}
					int cnt = game(list2, new int[] {i, j, k});
					answer = Math.max(cnt, answer);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public static int game(List<Enemy> list, int[] pos) {
		int cnt = 0;
		
		// 적군이 다 죽을때까지
		while (list.size() != 0) {
			List<Enemy> tmp = new ArrayList<Enemy>();
			
			// 각 궁수별로
			for (int p : pos) {
				// 자신과 가장 가까운 적군을 찾는다.
				int target = findNear(list, p);
				// 찾은 적군을 임시 리스트에 담아놓는다.
				if (target != -1) {
					tmp.add(list.get(target));
				}
			}
			
			// 임시 배열을 돌면서
			for (Enemy e : tmp) {
				// 임시 배열에 포함된 적군을 적군리스트에서 지운다.
				// list에서 remove는 지워졌으면 true 아니면 false를 반환
				if (list.remove(e)) {
					cnt++;
				}
			}
			
			// 적군 한칸씩 내려온다.
			for (int i = 0; i < list.size(); ++i) {
				Enemy e = list.get(i);
				e.r++;
				
				// 밖으로 나간 적군은 죽는다.
				if (e.r == N) {
					list.remove(i);
					i--;
				}
			}
		}
		
		return cnt;
	}
	
	// p열에 위치한 궁수로부터 가장 가까운 적군을 찾는다.
	public static int findNear(List<Enemy> list, int p) {
		// 가장 가까운 적군의 나와의 거리
		int dist = 987654321;
		// 가장 가까운 적군의 열 위치
		int minW = 987654321;
		// 찾아낸 적군의 인덱스
		int res = -1;
		
		for (int i = 0; i < list.size(); ++i) {
			Enemy e = list.get(i);
			// N은 어쨋든 가장 큰 수 이므로 abs 불필요
			int d = N - e.r + Math.abs(p - e.c);
			
			// 거리안에 들어오는지
			if (d > D) {
				continue;
			}
			
			// 알려진 최소거리보다 작다면
			if (d < dist) {
				dist = d;
				minW = e.c;
				res = i;
			}
			else if (d == dist) {
				// 알고있던 최소값과 같은 적군이 나왔으면
				if (minW > e.c) {
					minW = e.c;
					res = i;
				}
			}
		}
		
		// 죽일 적군의 위치를 반환
		return res;
	}
}
