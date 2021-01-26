package D3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 주차타워_9280 {
	public static int[] parkingPrices;
	public static int[] parkingAreas;
	public static int[] carWeights;
	public static Queue<Integer> waitCars;
	static int n;
	static int m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; ++tc) {
			n = sc.nextInt();
			m = sc.nextInt();
			
			parkingPrices = new int[n];
			parkingAreas = new int[n];
			carWeights = new int[m];
			waitCars = new LinkedList<>();
			
			for (int i = 0; i < n; ++i) {
				parkingPrices[i] = sc.nextInt();
			}
			

			for (int i = 0; i < m; ++i) {
				carWeights[i] = sc.nextInt();
			}
			
			int answer = 0;
			for (int i = 0; i < m * 2; ++i) {
				int car = sc.nextInt();
				
				// 주차장에서 나가기
				if (car < 0) {
					exitParking((-car));
					
					if (waitCars.isEmpty()) {
						continue;
					}
				}
				
				// 빈 공간 체크
				int pArea = checkArea();				
				// 빈 공간이 없을 시 대기 공간 입장
				if (pArea < 0) {
					waitCars.add(car);
					continue;
				}
				
				// 기다리는 차가 있다면
				if (!waitCars.isEmpty()) {
					
					// 출차가 아니라면 기다리게 하고
					if (car > 0) {
						waitCars.offer(car);
					}
								
					// 맨 앞에 있는 차를 선택
					car = waitCars.poll();
				}
				
				// 선택된 차를 주차 및 정산
				parkingAreas[pArea] = car;
				answer += (parkingPrices[pArea] * carWeights[car - 1]);
			}
			
			System.out.println("#" + tc + " " + answer);
		}
		
		sc.close();
	}
	
	static void exitParking(int car) {
		for (int i = 0; i < n; ++i) {
			if (parkingAreas[i] == car) {
				parkingAreas[i] = 0;
				break;
			}
		}
		
		return;
	}
	
	static int checkArea() {
		for (int i = 0; i < n; ++i) {
			if (parkingAreas[i] == 0) {
				return i;
			}
		}
		
		return -1;
	}
}
