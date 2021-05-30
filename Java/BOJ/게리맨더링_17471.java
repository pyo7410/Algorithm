import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 게리맨더링_17471 {
	public static int N, minPop;
	public static int[] populations, adjCnt;
	public static boolean[] isConnected;
	public static List<Integer>[] adjMatrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		populations = new int[N + 1];
		adjCnt = new int[N + 1];
		adjMatrix = new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			adjMatrix[i] = new ArrayList<Integer>();
			populations[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			adjCnt[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < adjCnt[i]; ++j) {
				adjMatrix[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		minPop = Integer.MAX_VALUE;
		go("", 0);
		
		if (minPop == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minPop);			
		}
	}
	
	public static void go(String s, int cnt) {
		if (s.length() == N) {
			int aPopulation = 0;
			int bPopulation = 0;
			
			for (int i = 1; i <= N; ++i) {
				int section = s.charAt(i - 1) - '0';
				
				if (section == 1) {
					aPopulation += populations[i];
				}
				else if (section == 2) {
					bPopulation += populations[i];
				}
			}
			
			// 각 선거구는 적어도 하나의 구역을 포함해야한다.
			if (aPopulation == 0 || bPopulation == 0) {
				return;
			}
			
			isConnected = new boolean[N + 1];
			
			// 문제의 조건에 따르자면 각 구역의 어느점을 선택해도 모두 연결이되어야한다.
			// 때문에 연결이 안된지점이 생겼다는것은 불가능한 방법이 된다.
			
			// 제일 처음 1이 나오는 위치
			for (int i = 1; i <= N; ++i) {
				int start = s.charAt(i - 1) - '0';
				
				if (start == 1) {
					isConnected[i] = true;
					connect(i, s);
					break;
				}
			}

			// 제일 처음 2가 나오는 위치
			for (int i = 1; i <= N; ++i) {
				int start = s.charAt(i - 1) - '0';
				
				if (start == 2) {
					isConnected[i] = true;
					connect(i, s);
					break;
				}
			}
			
			// 연결되지않은 점이 하나라도 존재하면 불가능한 방법
			// 즉, 같은 선거구이지만 연결되지않는 구역이 존재
			for (int i = 1; i <= N; ++i) {
				if (!isConnected[i]) {
					return;
				}	
			}
			
			int result = Math.abs(aPopulation - bPopulation);
			
			minPop = (minPop > result) ? result : minPop;
			
			return;
		}
		
		go(s + "1", cnt + 1);
		go(s + "2", cnt + 1);
	}
	
	public static void connect(int start, String s) {
		for (int i = 0; i < adjMatrix[start].size(); ++i) {
			int section = adjMatrix[start].get(i);
			
			if (!isConnected[section] && s.charAt(start - 1) == s.charAt(section - 1)) {
				isConnected[section] = true;
				connect(section, s);
			}
		}
	}
}
