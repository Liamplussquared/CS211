/*Problem Statement
The goal is to sort a long list of words by special order. The special
order is as follows. All the words are sorted by the 'greatest' character in
that word. What this means is that all words whose greatest character is
a 'a' (i.e. that feature only a's) should come first, followed by all words
whose greatest character is a 'b' (e.g. "baa"), followed by all those words
whose greatest character is an 'c' (e.g. "cab") and so forth. Here 'greatest'
means furthest along in the alphabet. The very last words in the list will 
be those whose greatest character is a 'z' (i.e. any words that have a 'z' in
them).
For words that have the same greatest character (e.g. "salt" and "table",
which both feature a 't' as their greatest character), these should be sorted
alphabetically (so 'salt' would come first) */
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
