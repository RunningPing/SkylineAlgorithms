package ping;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
 * Fast Secure Permutation Protocol
 * VK is the result F;
 * @author Administrator
 *
 */
public class FSPP {

	public int[] VA = null;
	public int[] VB = null;
	public int[] VC = null;
	public int[] VD = null;
	public int[] VK = null;
	public int kkk = 0;

	public BigInteger alpha = null;
	public BigInteger beta = null;

	public BigInteger[] cc = null;
	public BigInteger[] rr = null;
	public BigInteger[] K = null;
	public BigInteger[] CC = null;
	public BigInteger D = BigInteger.ZERO;
	public BigInteger RRR = BigInteger.ZERO;
	public BigInteger KKK = BigInteger.ZERO;
	public BigInteger kkkp = BigInteger.ZERO;
	public BigInteger kkkt = BigInteger.ZERO;
	public BigInteger M = BigInteger.ZERO;
	public BigInteger A = BigInteger.ZERO;
	public BigInteger B = BigInteger.ZERO;
	public BigInteger[] PP = null;
	public BigInteger[] VDD = null;
	public BigInteger[] TT = null;
	public BigInteger[] VTT = null;
	public BigInteger[] FF = null;
	public BigInteger[] REMD = null;
	   
	public FSPP(int[] _VA, int[] _VB) {
		VA = _VA;
		VB = _VB;

		String sVA = Arrays.toString(VA);
		String sVB = Arrays.toString(VB);

		System.out.println("VA = " + sVA);
		System.out.println("VB = " + sVB);
	}
	
	public FSPP(int[] _VA) {
		VA = _VA;

	}
	
	public FSPP(int[] _VA, BigInteger _alpha, BigInteger _beta) {
		VA = _VA;
		alpha = _alpha;
		beta = _beta;

	}
	
	public FSPP() {
		int length = 50;

		VA = new int[length];
		VB = new int[length];

		Random rd1 = new Random();
		Random rd2 = new Random();

		for (int i = 0; i < length; i++) {
			VA[i] = rd1.nextInt(25600);
			VB[i] = rd2.nextInt(25600);
		}
		String sVA = Arrays.toString(VA);
		String sVB = Arrays.toString(VB);

		System.out.println("VA = " + sVA);
		System.out.println("VB = " + sVB);

	}
	
	public BigInteger[] NewStepOne() {
		K = new BigInteger[VA.length];
		A = BigInteger.ZERO;

		cc = new BigInteger[VA.length];
		rr = new BigInteger[VA.length];
		CC = new BigInteger[VA.length];
		BigInteger[] CCC = new BigInteger[2 * VA.length];
		
		for (int i = 0; i < VA.length; i++) {
			cc[i] = new BigInteger(20, new Random());

			rr[i] = new BigInteger(508, new Random());

			BigInteger rb = beta.multiply(rr[i]);

			K[i] = rb.subtract(cc[i]);

			if (VA[i] == 0) {
				CC[i] = rb.add(cc[i]);

			} else {
				BigInteger ra = alpha.multiply(new BigInteger(Integer.toString(VA[i])));
				CC[i] = rb.add(cc[i]).add(ra);
			}
			
			CCC[i] = K[i];
			CCC[i + VA.length] = CC[i];

		}
		
		return CCC;
		

	}
	
	public void NewStepTwo(int[] _VB, int[] _VC) {
		D = BigInteger.ZERO;
		B = BigInteger.ZERO;
		KKK = BigInteger.ZERO;
		PP = new BigInteger[VA.length];
		TT = new BigInteger[VA.length];
		VDD = new BigInteger[VA.length];
		VTT = new BigInteger[VA.length];
		RRR = new BigInteger(20, 64, new Random());

		VB = _VB;
		VC = _VC;

		VD = VB.clone();
		for (int i = 0; i < VC.length; i++) {
			KKK = alpha.multiply(new BigInteger(Integer.toString(VB[i])));
			PP[i] = CC[i].add(KKK).add(RRR);
			TT[i] = K[i].add(RRR);
		}

		for (int i = 0; i < VC.length; i++) {
			kkkp = PP[VC[i]];
			VDD[i] = kkkp;
			kkkt = TT[VC[i]];
			VTT[i] = kkkt;
			VDD[i] = VDD[i].add(VTT[i]);
		}

	}
	
	public void NewStepThree(BigInteger[] vdd) {
		FF = new BigInteger[VA.length];
		VK = new int[VA.length];
		REMD = new BigInteger[VA.length];
		for (int i = 0; i < VA.length; i++) {			//VC
//			FF[i] = (VDD[i].add(VTT[i])).remainder(beta);
			FF[i] = vdd[i].remainder(beta);
			REMD[i] = FF[i].remainder(alpha);
			FF[i] = (FF[i].subtract(REMD[i])).divide(alpha);
			VK[i] = FF[i].intValue();
		}

	}
	
	
	  
}
