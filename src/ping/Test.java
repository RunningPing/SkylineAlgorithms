package ping;

import java.math.BigInteger;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		System.out.println("正确性测试");
		test.correctTest();
		System.out.println("向量维度与时间测试");
		test.timeInDifferentDimensionTest();
	}
	
	/**
	 * 正确性测试函数
	 */
	public void correctTest(){
		int[] _VA = { 10, 11 };
		int[] _VB = { 8, 9 };
		int[][] VB = new int[1][];
		VB[0] = _VB;

		long clientTime, serverTime;

		long t1 = System.currentTimeMillis();
		Client client = new Client(_VA);
		long t2 = System.currentTimeMillis();
		Server server = new Server(VB);
		long t3 = System.currentTimeMillis();
		BigInteger[] CC = client.ClientFirstStep();
		long t4 = System.currentTimeMillis();
		BigInteger[] vdd = server.ServerFirstStep(client.getAlpha(), CC);
		long t5 = System.currentTimeMillis();
		int[] Length = client.ClientSecondStep(vdd);
		long t6 = System.currentTimeMillis();
		BigInteger[][][] KKK = client.ClientThirdStep();
		long t7 = System.currentTimeMillis();
		BigInteger[][][] result = server.ServerSecondStep(KKK, Length);
		String resultV = server.getLttp();
		long t8 = System.currentTimeMillis();
		int tempLength = result.length;
		String[] resultString = new String[tempLength];
		for (int i = 0; i < resultString.length; i++) {
			resultString[i] = client.ClientFourthStep(result[i]);

			int e = 0;
			int f = 0;
			binaryToDecimal cccc = new binaryToDecimal();
			e = cccc.ToDecimal(resultV);

			binaryToDecimal cccc1 = new binaryToDecimal();
			f = cccc1.ToDecimal(resultString[i]);

			if (e == f)
				System.out.println("Big");
			else
				System.out.println("Not Big");
		}
		long t9 = System.currentTimeMillis();

		clientTime = t2 - t1 + t4 - t3 + t5 - t6 + t7 - t6 + t9 - t8;
		serverTime = t3 - t2 + t5 - t4 + t8 - t7;
		System.out.println("clientTime is " + clientTime + "ms serverTime is " + serverTime + "ms");
	}
	
	/**
	 * 时间与向量维度关系的测试，VB数据集大小为10000，数据随机
	 */
	public void timeInDifferentDimensionTest(){
		Random rand = new Random(System.currentTimeMillis());
		for(int j = 1; j < 10; j++)
		{
			int[] _VA = new int[j];
			for(int jj = 0; jj < j ; jj++)
			{
				_VA[jj] = rand.nextInt(100);
			}
			
			int[][] _VB = new int[10000][j];
			for(int jj = 0; jj < 10000; jj++)
			{
				for(int jjj = 0; jjj < j ; jjj++)
				{
					_VB[jj][jjj] = rand.nextInt(100);
				}
			}
			
			long clientTime, serverTime;

			long t1 = System.currentTimeMillis();
			Client client = new Client(_VA);
			long t2 = System.currentTimeMillis();
			Server server = new Server(_VB);
			long t3 = System.currentTimeMillis();
			BigInteger[] CC = client.ClientFirstStep();
			long t4 = System.currentTimeMillis();
			BigInteger[] vdd = server.ServerFirstStep(client.getAlpha(), CC);
			long t5 = System.currentTimeMillis();
			int[] Length = client.ClientSecondStep(vdd);
			long t6 = System.currentTimeMillis();
			BigInteger[][][] KKK = client.ClientThirdStep();
			long t7 = System.currentTimeMillis();
			BigInteger[][][] result = server.ServerSecondStep(KKK, Length);
			String resultV = server.getLttp();
			long t8 = System.currentTimeMillis();
			int tempLength = result.length;
			String[] resultString = new String[tempLength];
			for (int i = 0; i < resultString.length; i++) {
				resultString[i] = client.ClientFourthStep(result[i]);

				int e = 0;
				int f = 0;
				binaryToDecimal cccc = new binaryToDecimal();
				e = cccc.ToDecimal(resultV);

				binaryToDecimal cccc1 = new binaryToDecimal();
				f = cccc1.ToDecimal(resultString[i]);

//				if (e == f)
//					System.out.print("Big ");
//				else
//					System.out.print("Not Big ");
			}
			
			long t9 = System.currentTimeMillis();
//			System.out.println();
			clientTime = t2 - t1 + t4 - t3 + t5 - t6 + t7 - t6 + t9 - t8;
			serverTime = t3 - t2 + t5 - t4 + t8 - t7;
			System.out.println("Dimension is " + j + " clientTime is " + clientTime + "ms serverTime is " + serverTime + "ms");
		}
	}
}
