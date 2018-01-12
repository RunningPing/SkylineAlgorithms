package ping;

import java.math.BigInteger;
import java.util.Random;

/**
 * 客户端所进行的操作
 * @author ping
 *
 */
public class Client {

	public int[] VA = null;
	public int[] VB = null;
	public int[] VP = null;
	// public int[] VR = null;
	public int[] VK = null;
	public int[] VBP = null;
	public int[] VDD = null;
	public int[] VDA = null;
	public int[] VAD = null;
	public int[] VCR = null;
	public int FCR = 6;
	public int FCT = 8;
	public int[] VAP = null;
	public int[] VAK1 = null;
	public int[] VAK2 = null;
	public int[] VAKU = null;

	public int kkkp = 0;
	public int kkk = 0;

	public BigInteger alpha = null;
	public BigInteger beta = null;
	public BigInteger tsec = null;
	public BigInteger gama = null;
	public BigInteger modp = null;
	public BigInteger gamainv = null;
	public BigInteger alpha2 = null;

	public BigInteger[] cc = null;

	public BigInteger D = BigInteger.ZERO;
	public int[] _VA;

	FSPP lw;
	int[] Length;
	FSICP[] fsicp;
	BigInteger[][][] KKK;

	
	public Client(int[] _VA, BigInteger _alpha, BigInteger _beta, BigInteger _tsec, BigInteger _gama, BigInteger _modp,
			BigInteger _gamainv, BigInteger _alpha2) {
		VA = _VA;
		alpha = _alpha;
		beta = _beta;
		tsec = _tsec;
		gama = _gama;
		modp = _modp;
		gamainv = _gamainv;
		alpha2 = _alpha2;
	}

	/**
	 * 接收一个向量后，进行客户端的初始化
	 * @param _VA
	 */
	public Client(int[] _VA) {
		this._VA = _VA;
		int[] _VAA = new int[_VA.length * 2];
		for (int i = 0; i < _VA.length; i++) {
			_VAA[i] = 2 * _VA[i] + 1;
		}

		int MODK = 1100;
		for (int i = _VA.length; i < _VA.length * 2; i++) {
			_VAA[i] = MODK - _VAA[i - _VA.length];
		}

		this.VA = _VAA;

		alpha = new BigInteger(160, 64, new Random());
		beta = new BigInteger(512, 64, new Random());
		tsec = new BigInteger(20, 64, new Random());
		alpha2 = new BigInteger(400, 64, new Random());
		for (;;) {

			gama = new BigInteger(700, new Random());
			modp = new BigInteger(768, new Random());

			if (gama.gcd(modp).equals(BigInteger.ONE)) {
				gamainv = gama.modInverse(modp);
				break;
			}
		}
	}

	/**
	 * 客户端进行第一步，计算FSPP协议LSP第一步
	 * @return 返回传给CSP的值
	 */
	public BigInteger[] ClientFirstStep() {
		VDD = new int[VA.length];
		VCR = new int[VA.length];
		VBP = new int[VA.length];
		VAP = new int[VA.length];
		VAD = new int[VA.length];
		VAK1 = new int[VA.length];
		VAK2 = new int[VA.length];
		VAKU = new int[VA.length];

		lw = new FSPP(VA, alpha, beta);
		BigInteger[] CC = lw.NewStepOne();
		return CC;
	}

	/**
	 * 进行FSPP协议的第三部分，得到π(x + R)
	 * @param vdd
	 * @return
	 */
	public int[] ClientSecondStep(BigInteger[] vdd) {
		lw.NewStepThree(vdd);
		VDA = lw.VK;
		String[] ltt = new String[VDA.length];
		Length = new int[VDA.length];

		for (int jjj = 0; jjj < VA.length; jjj++) {
			ltt[jjj] = Integer.toBinaryString(VDA[jjj]);
			Length[jjj] = ltt[jjj].length() + 4;
		}

		return Length;

	}

	/**
	 * 进行FISCP协议的第一部分
	 * @return CSP需要接收的值
	 */
	public BigInteger[][][] ClientThirdStep() {
		fsicp = new FSICP[VA.length];
		KKK = new BigInteger[VA.length][][];
		for (int jjj = 0; jjj < VA.length; jjj++) {
			int a = VDA[jjj];
			fsicp[jjj] = new FSICP(a, gama, modp, gamainv, alpha2);
			KKK[jjj] = fsicp[jjj].NewStepOne(Length[jjj]);
		}
		return KKK;
	}

	/**
	 * ESVC最后一步
	 * @param kkb
	 * @return
	 */
	public String ClientFourthStep(BigInteger[][] kkb) {
		StringBuilder temp = new StringBuilder();
		for (int jjj = 0; jjj < VA.length; jjj++) {
			fsicp[jjj].NewStepThree(Length[jjj], kkb[jjj]);
			if (fsicp[jjj].FINNELk == 1)
				VAKU[jjj] = 1;
			else
				VAKU[jjj] = 0;
			temp.append(Integer.toString(VAKU[jjj]));
		}

		String lttp2 = temp.toString();

		return lttp2;
	}

	/**
	 * 传送alpha值
	 * @return
	 */
	public BigInteger getAlpha() {
		return this.alpha;
	}

	/*
	 * public void changeVA(){ int[] _VAA = new int[_VA.length * 2]; for(int i =
	 * 0; i < _VA.length; i++) { _VAA[i] = 2 * _VA[i]; }
	 * 
	 * int MODK = 1100; for(int i = _VA.length; i < _VA.length * 2; i++) {
	 * _VAA[i] = MODK - _VAA[i - _VA.length]; }
	 * 
	 * this.VA = _VAA; }
	 */

}
