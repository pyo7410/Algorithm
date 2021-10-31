package D4;

import java.util.Scanner;

public class Ladder1 {
	static int MAX = 100;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		for(int tc = 1; tc <= T; tc++) {
			sc.nextInt(); 
			int[][] map = new int[MAX][MAX];
			for(int i = 0; i < MAX; i++) {
				for(int j = 0; j < MAX; j++) 
					map[i][j] = sc.nextInt();
			}
			// 사다리에서 1이 어디있는지 나온 순서대로 저장
			int[] lanes = new int[MAX];
			int idx = 0;
			int goalIdx = 0;
			for(int j = 0; j < MAX; j++) {
				if(map[MAX-1][j] != 0) {
					// 도착지를 찾으면 도착지 인덱스 저장
					// 2도 도착지일 뿐 1의 일종이므로 저장해야한다
					if(map[MAX-1][j] == 2)
						goalIdx = idx;
					
					// 1의 위치저장
					lanes[idx++] = j;
				}
			}
			// 사다리이므로 맨 밑이 1이라면 맨 위까지 1로 연결되 있음이 보장됨
			// 때문에 연결부만 체크할 수 있다
			// 현재 위치를 표시하는 idx
			int currentIdx = goalIdx;
			for(int i = MAX-1; i >= 0; i--) {
				// 만약 사다리에서 1이 있는 인덱스에서 1을 뻇을때 0보다 크고 왼쪽에 1이 있다면
				// 왼쪽에 있는 1의 인덱스가 저장된 lanes배열의 인덱스로 바로 이동
				if( lanes[currentIdx] - 1 >= 0 && map[i][lanes[currentIdx]-1] == 1 )
					currentIdx--;
				else if( lanes[currentIdx] + 1 < MAX && map[i][lanes[currentIdx]+1] == 1  )
					// 오른쪽이므로 1이 있는 인덱스 + 1이 100 보다 작고 오른쪽에 1이 있다면
					// 오른쪽의 1의 인덱스가 저장된 lanes배열의 인덱스로 바로 이동
					currentIdx++;
			}
			System.out.println("#" + tc + " " + lanes[currentIdx]);
		}
	}
}