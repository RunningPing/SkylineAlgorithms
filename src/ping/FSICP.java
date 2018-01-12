package ping;

import java.math.BigInteger;
import java.util.Random;

/**
 * Fast Secure Integer Comparison Protocol FINNELk is the result, 1 present a >
 * b and 2 present a < b;
 * 
 * @author ping
 *
 */
public class FSICP {

	public int FINNEL = 5;
	public int FINNEL1 = 0;
	public int FINNELk = 5;
	public int VA = 0;
	public int VB = 0;
	public int[][] VAA = null;
	public int[] IDEX = null;
	public BigInteger[][] KK = null;
	public BigInteger[] KKB = null;
	public BigInteger[] KKD = null;
	public BigInteger EQQ = null;
	public BigInteger[] FORKKD = null;
	public BigInteger[] FORREM = null;
	public BigInteger[] FORFINAL = null;
	public BigInteger TTTTT = null;
	public BigInteger KKKK = null;
	public BigInteger EQQK = null;
	public String[] VK = null;
	public BigInteger gama = null;
	public BigInteger modp = null;
	public BigInteger[] cc1 = null;
	public BigInteger[] cc2 = null;
	public BigInteger[] rr = null;
	public BigInteger[] K = null;
	public BigInteger[] CC = null;
	public BigInteger gamainv = null;
	public BigInteger alpha2 = null;
	public int[] KKBt = null;
	public BigInteger DEQQ = null;
	public BigInteger[] GDEQQ = null;
	public BigInteger[] TTT = null;
	public BigInteger REMD = null;
	public BigInteger[] GREMD = null;
	public BigInteger BBQ = null;
	public BigInteger FEQ = null;
	public BigInteger BBT = null;
	public BigInteger BFCE = null;
	public BigInteger rppp = null;
	public int BFCEF = 0;
	public BigInteger[] GREATER = null;
	public int idx = 0;
	public String[] TKTT = null;

	public FSICP(int _VA) {
		VA = _VA;
		gama = new BigInteger(160, 64, new Random());
		modp = new BigInteger(512, 64, new Random());
		gamainv = new BigInteger(20, 64, new Random());
	}

	public FSICP(int _VA, int _VB) {
		VA = _VA;
		VB = _VB;
	}

	public FSICP(int _VA, BigInteger _alpha, BigInteger _beta, BigInteger _tsec, BigInteger _alpha2) {
		VA = _VA;
		gama = _alpha;
		modp = _beta;
		gamainv = _tsec;
		alpha2 = _alpha2;
	}

	public BigInteger[][] NewStepOne(int length) {
		String lp = new String();
		String lkkk = new String();
		String lt = new String();
		int ccc = 0;

		lkkk = Integer.toBinaryString(VA);
		int cccsc = length - lkkk.length() - 2;

		if (cccsc > 0) {
			for (int i = 0; i < cccsc; i++)
				lkkk = '0' + lkkk;
		}

		lp = "11" + lkkk;
		VAA = new int[2][length];

		K = new BigInteger[length];
		KK = new BigInteger[2][length];

		cc1 = new BigInteger[length];
		cc2 = new BigInteger[length];
		rr = new BigInteger[length];
		CC = new BigInteger[length];
		Random rd1 = new Random();

		int kk = 0;
		for (int i = 0; i < lp.length(); i++) {
			kk = lp.length() - i - 1;
			cc1[kk] = new BigInteger(160, new Random());
			cc2[kk] = new BigInteger(160, new Random());

			BigInteger rb = (gama.multiply(cc1[kk])).mod(modp);
			BigInteger rc = gama.multiply(cc2[kk]);

			idx = Integer.parseInt(lp.substring(i, i + 1));

			BigInteger rt = (alpha2.add(rc)).mod(modp);

			KK[idx][kk] = rb;
			KK[1 - idx][kk] = rt;

		}

		for (int i = lp.length(); i < length; i++) {
			kk = i;
			cc1[kk] = new BigInteger(160, new Random());
			cc2[kk] = new BigInteger(160, new Random());

			BigInteger rb = (gama.multiply(cc1[kk])).mod(modp);
			BigInteger rc = gama.multiply(cc2[kk]);

			idx = Integer.parseInt(lp.substring(i, i + 1));
			BigInteger rt = (alpha2.add(rc)).mod(modp);

			KK[0][kk] = rb;
			KK[1][kk] = rt.add(rt);
		}

		return KK;

	}

	public void NewStepTwo(int _VB, int length) {
		VB = _VB;
		String lt = new String();
		String st = new String();
		String et = new String();

		et = Integer.toBinaryString(VB);
		KKD = new BigInteger[length];
		KKB = new BigInteger[length];
		TKTT = new String[length];
		IDEX = new int[length];
		KKBt = new int[length];

		FORKKD = new BigInteger[length];
		FORREM = new BigInteger[length];
		FORFINAL = new BigInteger[length];

		BBQ = BigInteger.ZERO;
		EQQ = BigInteger.ZERO;
		BBT = BigInteger.ZERO;
		FEQ = BigInteger.ZERO;
		EQQK = BigInteger.ZERO;

		int kkkk = length - et.length() - 2;

		if (kkkk > 0) {
			for (int i = 0; i < kkkk; i++) {
				et = '0' + et;

			}
		}

		lt = "11" + et;
		VK = new String[length];
		for (int i = 0; i < lt.length(); i++) {
			if (Integer.parseInt(lt.substring(i, i + 1)) == 0) {
				VK[i] = lt.substring(0, i) + '1';
				TKTT[i] = lt.substring(0, i) + '1';
				IDEX[i] = lt.length() - i - 1;

			}
		}

		for (int i = 0; i < length; i++) {
			if (VK[i] == null)
				continue;
			else {
				st = VK[i];
				BBQ = BigInteger.ZERO;
				BBT = BigInteger.ZERO;
				TTTTT = BigInteger.ZERO;
				KKKK = BigInteger.ZERO;

				for (int ii = 0; ii < st.length(); ii++) {
					int meid = 0;
					meid = Integer.parseInt(st.substring(ii, ii + 1));
					rppp = new BigInteger(160, new Random());
					BBQ = BBQ.add(rppp.multiply(KK[meid][lt.length() - ii - 1]));

				}

				KKB[i] = BBQ;

			}
		}
	}

	public void NewStepThree(int length, BigInteger[] kkb) {
		BFCE = BigInteger.ZERO;
		DEQQ = BigInteger.ZERO;
		REMD = BigInteger.ZERO;

		TTT = new BigInteger[length];
		GDEQQ = new BigInteger[length];
		GREMD = new BigInteger[length];
		GREATER = new BigInteger[length];
		FORKKD = new BigInteger[length];
		FORREM = new BigInteger[length];
		FORFINAL = new BigInteger[length];

		for (int i = 0; i < length; i++) {
			if (kkb[i] != null) {
				FORKKD[i] = (kkb[i].multiply(gamainv)).mod(modp);
				FORREM[i] = FORKKD[i].remainder(alpha2);
				FORFINAL[i] = (FORKKD[i].subtract(FORREM[i])).divide(alpha2);

				TTT[i] = (kkb[i].multiply(gamainv)).mod(modp);
				GREMD[i] = TTT[i].remainder(alpha2);
				GREATER[i] = (TTT[i].subtract(GREMD[i])).divide(alpha2);

				if (GREATER[i] == BFCE) {
					FINNEL1 = 1;
					break;
				}
			}
		}

		if (FINNEL1 == 1)
			FINNELk = 1;
		if (FINNEL1 == 0)
			FINNELk = 2;

	}

}
