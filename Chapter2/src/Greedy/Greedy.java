package Greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Greedy Algorithm
 * 현재 가장 유리한 경우를 선택하는 알고리즘
 * 
 * Problem.
 * 메모리 공간에 최대한 앱을 탑재하고 앱이 처음 실행될 때 총 소요시간이 얼마나 되는지 확인
 * 실행될 때 차지하는 메모리 크기 (W)의 합이 전체 메모리 크기(F-E)를 우지하면서 실행 시간(P)는 최소로 하는 것
 * 앱은 단 한번만 실행되는 것이 아니라 여러번 실행 될 수 있음.
 * 동시에 실행되어 메모리 공간을 차지할 수 있다.
 * 
 * Input.
 * T : 테스트 케이스
 * E : 운영체제가 기본적으로 차지하는 메모리 크기
 * F : 제공된 전체 메모리 크기
 * N : 포팅 될 앱의 갯수
 * P : 앱 실행시 소요되는 시간
 * W : 앱이 차지하는 메모리 크기
 * 
 * Output.
 * 각 테스트에 주어진 메모리 크기를 갖는 앱들이 실행 될 때 소요되는 최소 시간
 * 사용하는 앱의 메모리 크기가 맞지 않으면 -1 출력
 */

public class Greedy {
	
	static int MAX = 10000;

	public static void main(String[] args) throws IOException {

		FileReader reader = new FileReader("input_greedy.txt");
		BufferedReader br = new BufferedReader(reader);

		String lines = br.readLine();
		int T = Integer.parseInt(lines);

		for (int i = 0; i < T; i++) {
			
			//Step1. Input Data
			lines = br.readLine();
			String[] temp = lines.split(" ");
			int E = Integer.parseInt(temp[0]);
			int F = Integer.parseInt(temp[1]);

			lines = br.readLine();
			int N = Integer.parseInt(lines);

			Apps[] apps = new Apps[N];

			for (int j = 0; j < N; j++) {

				lines = br.readLine();
				temp = lines.split(" ");

				int p = Integer.parseInt(temp[0]);
				int w = Integer.parseInt(temp[1]);

				apps[j] = new Apps(p, w);
			}
			
			
			//Step2. Solve
			int[] run_times = greedySolve(E, F, apps);
			
			//Step3. Output
			if(run_times[F-E] == MAX) {
				System.out.println("-1");
			}else {
				System.out.println("최소 실행 시간 : "+run_times[F-E]);
			}
			
		}

		br.close();
		reader.close();

	}

	private static int[] greedySolve(int e, int f, Apps[] apps) {
		
		int[] result = initArray(f-e);
		
		for(int i=0; i<apps.length; i++) {
			for(int j=0; j<=f-e-apps[i].W; j++) {
				if(result[j] + apps[i].P < result[j + apps[i].W]) {
					result[j + apps[i].W] = result[j] + apps[i].P;
				}
			}
		}
		
		return result;
	}

	private static int[] initArray(int x) {
		int[] temp = new int[x+1];
		
		temp[0] = 0;
		
		for(int i=1; i<= x; i++) {
			temp[i] = MAX;
		}
		
		return temp;
	}

	static class Apps {
		int P;
		int W;

		public Apps(int p, int w) {
			this.P = p;
			this.W = w;
		}
	}

}
