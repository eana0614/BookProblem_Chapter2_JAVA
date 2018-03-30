package Greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Greedy Algorithm
 * ���� ���� ������ ��츦 �����ϴ� �˰���
 * 
 * Problem.
 * �޸� ������ �ִ��� ���� ž���ϰ� ���� ó�� ����� �� �� �ҿ�ð��� �󸶳� �Ǵ��� Ȯ��
 * ����� �� �����ϴ� �޸� ũ�� (W)�� ���� ��ü �޸� ũ��(F-E)�� �����ϸ鼭 ���� �ð�(P)�� �ּҷ� �ϴ� ��
 * ���� �� �ѹ��� ����Ǵ� ���� �ƴ϶� ������ ���� �� �� ����.
 * ���ÿ� ����Ǿ� �޸� ������ ������ �� �ִ�.
 * 
 * Input.
 * T : �׽�Ʈ ���̽�
 * E : �ü���� �⺻������ �����ϴ� �޸� ũ��
 * F : ������ ��ü �޸� ũ��
 * N : ���� �� ���� ����
 * P : �� ����� �ҿ�Ǵ� �ð�
 * W : ���� �����ϴ� �޸� ũ��
 * 
 * Output.
 * �� �׽�Ʈ�� �־��� �޸� ũ�⸦ ���� �۵��� ���� �� �� �ҿ�Ǵ� �ּ� �ð�
 * ����ϴ� ���� �޸� ũ�Ⱑ ���� ������ -1 ���
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
				System.out.println("�ּ� ���� �ð� : "+run_times[F-E]);
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
