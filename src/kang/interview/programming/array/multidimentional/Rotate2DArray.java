package kang.interview.programming.array.multidimentional;

import kang.interview.programming.util.DataPrinter;

/**
 * You are given an n x n 2D matrix that represents an image. Rotate the image
 * by 90 degrees (clockwise).
 * 
 * Try to solve this in-place - in a real interview, you will only be allowed to
 * use O(1) additional memory.
 * 
 * Example
 * 
 * For
 * 
 * a = [[1, 2, 3], 
 * 		[4, 5, 6], 
 * 		[7, 8, 9]] 
 * 
 * the output should be
 * 
 * rotateImage(a) = [[7, 4, 1], 
 * 					 [8, 5, 2], 
 * 					 [9, 6, 3]] 
 * 
 * Input/Output
 * 
 * [time limit] 3000ms (java) 
 * [input] array.array.integer a
 * 
 * Guaranteed constraints: 
 * 1 <= a.length <= 100, 
 * a[i].length = a.length, 
 * 1 <= a[i][j] <= 104.
 * 
 * [output] array.array.integer
 * 
 * @author YK044346
 *
 */
public class Rotate2DArray {

	public void rotate(int[][] array) {
		rotate(array, 0);
	}

	/**
	 * 
	 * @param array
	 * @param indent
	 */
	private void rotate(int[][] array, int indent) {

		int length = array.length - 2 * indent;
		if (length <= 0)
			return;

		int lastRows = array.length - indent - 1; // last row index
		int lastCols = array[0].length - indent - 1; // last col index

		int[] backup = new int[length];
		for (int i = 0; i < length; i++) {
			backup[i] = array[indent][i + indent];
		}

		for (int i = lastCols, j = indent; i >= indent && j <= lastRows; i--, j++) {
			array[indent][i] = array[j][indent];
		}

		for (int i = indent, j = indent; i <= lastRows && j <= lastCols; i++, j++) {
			array[i][indent] = array[lastRows][j];
		}

		for (int i = indent, j = lastRows; i <= lastCols && j >= indent; i++, j--) {
			array[lastRows][i] = array[j][lastCols];
		}

		for (int i = indent, j = 0; i <= lastRows && j < length; i++, j++) {
			array[i][lastCols] = backup[j];
		}
		rotate(array, indent + 1);
	}

	public void rotate2(int[][] array) {
		rotate2(array, 0);
	}

	/**
	 * 
	 * @param array
	 * @param offset
	 */
	private void rotate2(int[][] array, int offset) {

		int length = array.length - 2 * offset;
		if (length <= 0)
			return;

		int firstRowIndex = offset;
		int firstColIndex = offset;
		int lastRowIndex = array.length - 1 - offset;
		int lastColIndex = array[0].length - 1 - offset;

		for (int i = firstColIndex; i <= lastColIndex - 1; i++) {
			int temp = array[firstRowIndex][i];
			array[firstRowIndex][i] = array[array.length - 1 - i][firstColIndex];
			array[array.length - 1 - i][firstColIndex] = array[lastRowIndex][array[0].length - 1 - i];
			array[lastRowIndex][array[0].length - 1 - i] = array[i][lastColIndex];
			array[i][lastColIndex] = temp;
		}

		rotate2(array, offset + 1);
	}

	public static void main(String[] args) {
		Rotate2DArray a = new Rotate2DArray();
		int[][] array1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		a.rotate2(array1);
		DataPrinter.print2DArray(array1);

		System.out.println();
		int[][] array2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		a.rotate2(array2);
		DataPrinter.print2DArray(array2);
	}
}
