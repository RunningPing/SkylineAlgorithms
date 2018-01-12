package ping;

import java.math.BigInteger;
import java.util.Random;

/**
 * 服务器端所进行操作
 * @author ping
 *
 */
public class Server {

	public int[][] VB;
	public int[][] VBP;
	public int[][] VBD;
	public int[] VR;
	public int[] VP;
	public int[] Length;
	int length;
	int[][] _VB;

	/**
	 * 根据数据集初始化
	 * @param _VB
	 */
	public Server(int[][] _VB) {
		this._VB = _VB;
		VB = new int[_VB.length][];
		for (int j = 0; j < _VB.length; j++) {
			int[] _VAA = new int[_VB[j].length * 2];
			for (int i = 0; i < _VB[j].length; i++) {
				_VAA[i] = 2 * _VB[j][i] + 1;
			}

			int MODK = 1100;
			for (int i = _VB[j].length; i < _VB[j].length * 2; i++) {
				_VAA[i] = MODK - _VAA[i - _VB[j].length];
			}
			VB[j] = _VAA;
		}

		Random rd2 = new Random();
		length = VB[0].length;
		VR = new int[length];

		for (int i = 0; i < length; i++) {
			VR[i] = rd2.nextInt(25600);
		}

		VP = NRandom.getSequence(length);

		VBP = new int[VB.length][length];
		VBD = new int[VB.length][length];

		for (int i = 0; i < VB.length; i++) {
			for (int j = 0; j < length; j++) {
				VBP[i][j] = VB[i][j] + VR[j];
			}
		}

		for (int i = 0; i < VB.length; i++) {
			for (int j = 0; j < length; j++) {
				VBD[i][j] = VBP[i][VP[j]];
			}
		}

	}
	/*
	 * public void changeVB(){ VB = new int[_VB.length][]; for(int j = 0; j <
	 * _VB.length; j++) { int[] _VAA = new int[_VB[j].length * 2]; for(int i =
	 * 0; i < _VB[j].length; i++) { _VAA[i] = 2 * _VB[j][i]; }
	 * 
	 * int MODK = 1100; for(int i = _VB[j].length; i < _VB[j].length * 2; i++) {
	 * _VAA[i] = MODK - _VAA[i - _VB[j].length]; } VB[j] = _VAA; }
	 * 
	 * VBP = new int[VB.length][length]; VBD = new int[VB.length][length];
	 * 
	 * for(int i = 0; i < VB.length; i++) { for(int j = 0; j < length; j++) {
	 * VBP[i][j] = VB[i][j] + VR[j]; } }
	 * 
	 * for(int i = 0; i < VB.length; i++) { for(int j = 0; j < length; j++) {
	 * VBD[i][j] = VBP[i][VP[j]] ; } } }
	 */

	
	/**
	 * 服务器执行的FSPP第二部分
	 * @param alpha
	 * @param CC
	 * @return
	 */
	public BigInteger[] ServerFirstStep(BigInteger alpha, BigInteger[] CC) {
		BigInteger[] vdd = FSPP_CSP_Second_Step.NewStepTwo(VR, VP, alpha, CC);
		return vdd;
	}

	/**
	 * 服务器执行的FSCIP第二步
	 * @param CC
	 * @param lengthK
	 * @return
	 */
	public BigInteger[][][] ServerSecondStep(BigInteger[][][] CC, int[] lengthK) {
		BigInteger[][][] result = new BigInteger[VB.length][length][];
		for (int i = 0; i < VB.length; i++) {
			for (int j = 0; j < length; j++) {
				result[i][j] = FSICP_CSP_Second_Step.NewStepTwo(VBD[i][j], lengthK[j], CC[j]);
			}

		}
		return result;
	}

	/**
	 * 服务器提供置换后的v向量
	 * @return
	 */
	public String getLttp() {
		String lttp;
		int CCC = length / 2;
		int[] VAK1 = new int[length];
		int[] VAK2 = new int[length];
		for (int i = 0; i < CCC; i++) {
			VAK1[i] = 1;
			VAK1[i + CCC] = 0;
		}
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < length; i++) {
			VAK2[i] = VAK1[VP[i]];
			temp.append(Integer.toString(VAK2[i]));
		}
		lttp = temp.toString();
		return lttp;
	}
}
