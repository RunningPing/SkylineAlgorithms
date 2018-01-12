package ping;

import java.util.ArrayList;
import java.util.Random;

/**
 * 由主机计算的Skyline值
 * @author Administrator
 *
 */
public class SkylineLocalAlgorithms {

	/**
	 * 比较两个向量的值
	 * @param r1
	 * @param r2
	 * @return 0--两个向量没有关系，3--r1大于r2，2--r1小于r2
	 */
	public static int compareRow(int[] r1, int[] r2) {
		int N = (r1.length);
		int flag = 0;
		int mid = 0;

		for (int i = 0; i < N; i++) {

			if (r1[i] < r2[i]) {
				flag = 2;
				mid = i + 1;
				break;
			}
			if (r1[i] > r2[i]) {
				flag = 1;
				mid = i + 1;
				break;
			}

			if (i == N - 1) {
				if ((r1[i] == r2[i]) && (flag == 0))
					return 1;
				if ((r1[i] < r2[i]) && (flag == 0))
					return 2;
				if ((r1[i] > r2[i]) && (flag == 0))
					return 3;
			}

		}

		for (int i = mid; i < N; i++) {

			if ((r1[i] > r2[i]) && (flag == 2))
				return 0;
			if ((r1[i] < r2[i]) && (flag == 1))
				return 0;

			if (i == N - 1) {
				if ((r1[i] <= r2[i]) && (flag == 2))
					return 2;
				if ((r1[i] >= r2[i]) && (flag == 1))
					return 3;
			}
		}

		if (flag == 1)
			return 3;
		if (flag == 2)
			return 2;

		return -1;
	}
	
	/**
	 * 计算一个数据集的Skyline点值
	 * @param vectors
	 * @param choose
	 * @return
	 */
	public static int[][] getSkyline(int[][] vectors, boolean choose){
		int STR_ARR_ROY = vectors.length;
		int[] IDB = new int[vectors.length]; 
		for(int k = 0; k < STR_ARR_ROY; k++)
		{
			IDB[k] = 1;
		}
		int temp = 0;
		if(choose == true)
			temp = 2;
		else
			temp = 3;
		
		for (int k = 0; k < STR_ARR_ROY; k++) {

			if (IDB[k] == 0)
				continue;
			int[] VB_1 = vectors[k];
			for (int jjj = 0; jjj < STR_ARR_ROY; jjj++) {
				if ((k == jjj) || (IDB[jjj] == 0))
					continue;
				int[] VB_2 = vectors[jjj];
				if (compareRow(VB_1, VB_2) == temp) {
					IDB[k] = 0;
					break;
				}
				else
				{
					IDB[jjj] = 0;
				}

			}

		}
		ArrayList<int[]> tempList = new ArrayList<>();
		for(int k = 0; k < STR_ARR_ROY; k++)
		{
			if(IDB[k] == 1)
			{
				tempList.add(vectors[k]);
			}
		}
		
		int[][] result = new int[tempList.size()][vectors[0].length];
		
		for(int i = 0; i < result.length; i++)
		{
			result[0] = tempList.get(i);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[][] temp = {
				{2, 3, 4, 5, 6, 7},
				{10, 11, 12, 13, 14, 15},
				{6, 7, 8, 9, 10, 11},
				{8, 9, 10, 11, 12, 13},
				{1, 2, 3, 4, 5, 6}
		};
		int[][] result = SkylineLocalAlgorithms.getSkyline(temp, false);
		
		for(int i = 0; i < result.length; i++)
		{
			for(int j = 0; j < result[i].length; j++)
			{
				System.out.print("r[" + i + "][" + j + "]=" + result[i][j] + " ");
			}
			System.out.println();
		}
	}
}
