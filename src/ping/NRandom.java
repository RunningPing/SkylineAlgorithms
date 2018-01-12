package ping;

import java.util.Random;

/**
 * 随机混淆向量
 * 
 * @author Administrator
 *
 */
public class NRandom {

	public int no = 0;

	public NRandom(int _VA) {
		no = _VA;
	}

	public static int[] getSequence(int no) {
		int[] sequence = new int[no];
		for (int i = 0; i < no; i++) {
			sequence[i] = i;
		}
		Random random = new Random();
		for (int i = 0; i < no; i++) {
			int p = random.nextInt(no);
			int tmp = sequence[i];
			sequence[i] = sequence[p];
			sequence[p] = tmp;
		}
		random = null;
		return sequence;
	}

}
