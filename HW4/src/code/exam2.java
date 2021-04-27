package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntPredicate;

import sun.jvm.hotspot.utilities.RBColor;





public class exam2 {
	public static ArrayList<String> markLengthN(ArrayList<String> list, int n) {
		ArrayList<String> result = list;
		String s = new String(new char[n]).replace('\0', '*');

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).length() == 4) {
				list.add(i + 1, s);
				list.add(i, s);
				i = i + 2;
			}
		}
		
		return result;
	}
	
	public static int[][] toSpiral(int[] a, int[][] b, int row, int col){
		
			int[][] result = b;
		
		int conti = - 1;
		int contj = 1;
		int flip = -1;
		int control = 1;
		int i = 0;
		int j = 0;
		
		for (int k = 0; k < 35; k++) {
			if (!(row < 0 && col < 0 && row < b.length && col < b[1].length)) {
				b[row][col] = a[k];
			}
			if (i < control) {
				i++;
				row = row + conti;
			} else if (j < control) {
				j++;
				col = col + contj;
			} else {
				
				control++;
				conti = conti * flip;
				contj = contj * flip;
				row = row + conti;
				
				i = 1;
				j = 0;
				
			}
			
		}
		return result;
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList("this", "Java", "is", "lots", "of", "fun", "for", "every", "Java", "programmer"));
		System.out.println(markLengthN(list, 4));
		System.out.println(" n ");
		
		int[][] b = new int[20][20];
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};
		
		toSpiral(a, b, 4, 4);
		System.out.println(Arrays.deepToString(b).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
}
