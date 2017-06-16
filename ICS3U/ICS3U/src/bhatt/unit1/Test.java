package bhatt.unit1;
/*
 * Test.java
 * Program Description
 * 4 26, 2017
 *
 * @author Rishab Bhatt
 */

/**
 * @author Alyssa April 24, 2017
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double[][] things = {{1.2, 9.0}, {9.2, 0.5, 0.0, 33, 23, 32}, {7.3, 7.9, 3.9, 2.5}};

		// System.out.println(things.length);
		// System.out.println(things[1].length);

		int[] list1 = {1, 2, 3};
		int[] list2 = {1, 2, 3};
		list2 = list1;

//		list1[0] = 0;
//		list1[1] = 1;
//		list2[2] = 2;

		for (int i = 0; i < list1.length; i++) {
			System.out.println(list1[i]);
		}

		for (int i = 0; i < list2.length; i++) {
			System.out.println(list2[i] + " ");
		}

		// int[] a1 = { 2, 4, 5, 7, 10, 15, 18 };
		//
		// System.out.println("Every other");
		//
		// // every other number
		// for (int i = 0; i <= a1.length; i += 2) {
		// System.out.println(a1[i]);
		// }
		//
		// System.out.println("Reverse");
		//
		// // reverse order
		// for (int i = a1.length - 1; i > 0; i--) {
		// System.out.println(a1[i]);
		// }
		//
		// System.out.println("Sum");
		//
		// // sum of all numbers
		// int sum = 0;
		// for (int i = 0; i < a1.length; i++) {
		// sum += a1[i];
		// }
		// System.out.println(sum);

	}

}