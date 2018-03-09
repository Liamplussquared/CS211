import java.util.Scanner;
public class QuicksortLexi {
	public static void main(String args []) {
		Scanner scan = new Scanner(System.in);
		//fill and make array
		String words[] = new String[Integer.parseInt(scan.nextLine())];

		for(int i = 0; i < words.length; i++) {
			words[i] = scan.nextLine();
			//System.out.print(words[i] + " ");
		}

		quickSort(words, 0, words.length-1);

		for(String s: words) {
			System.out.println(s);
		}
	}

	public static void quickSort(String[] words, int low, int high) {
		String pivot = words[low + (high-low)/2]; //middle element
		int i = low;
		int j = high;

		while(i<=j) { //haven't crossed
			while(compare(words[i], pivot)) //left scan, while words[i] < pivot
				i++;

			while(compare(pivot, words[j])) //right scan, while words[i] > pivot
				j--;

			if(i<=j) {
				swap(i,j,words);
				i++;
				j--;
			}
		}
		if (low < j)
			quickSort(words, low, j);
		if (high > i)
			quickSort(words, i, high);

	}

	public static boolean compare(String s1, String s2) {
		char c1 = greatestChar(s1);
		char c2 = greatestChar(s2);

		if(c1 == c2) { //same greatest character
			if (s2.compareTo(s1) > 0) //s2 higher priority
				return true;

			return false;
		}
		else
			return c1 <= c2; //true if c1 less priority
	}

	public static char greatestChar(String word) {
		char c[] = word.toCharArray();
		int greatestVal = 0;
		for (char s: c) {
			greatestVal = Math.max(greatestVal, (int) s);
		}
		return (char)greatestVal;
	}

	public static void swap(int i, int j, String words[]) {
		String temp = words[i];
		words[i] = words[j];
		words[j] = temp;
	}
}