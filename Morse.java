import javax.sound.sampled.*;
import java.util.*;

public class Morse {

    public static void main(String[] args) {

        HashMap<String, String> letters = new HashMap<String,String>();
        //0 is dot, 1 is dash
        //using international standard from Wikipedia
        letters.put("A","01");
        letters.put("B","1000");
        letters.put("C","1010");
        letters.put("D","100");
        letters.put("E","0");
        letters.put("F","0010");
        letters.put("G","110");
        letters.put("H","0000");
        letters.put("I","00");
        letters.put("J","0111");
        letters.put("K","101");
        letters.put("L","0100");
        letters.put("M","11");
        letters.put("N","10");
        letters.put("O","111");
        letters.put("P","0110");
        letters.put("Q","1101");
        letters.put("R","010");
        letters.put("S","000");
        letters.put("T","1");
        letters.put("U","001");
        letters.put("V","0001");
        letters.put("W","011");
        letters.put("X","1001");
        letters.put("Y","1011");
        letters.put("Z","1100");
        letters.put("0","11111");
        letters.put("1","01111");
        letters.put("2","00111");
        letters.put("3","00011");
        letters.put("4","00001");
        letters.put("5","00000");
        letters.put("6","10000");
        letters.put("7","11000");
        letters.put("8","11100");
        letters.put("9","11110");

        Scanner scan = new Scanner(System.in);
        String sentence = scan.nextLine();
        scan.close();
        //space between characters sepereated by 3 dot lengths, space between words sepereated by 7 dot lengths

        for(char c: sentence.toCharArray()) {
        	if (c==' ') { //account for pause between words
        		pause(5);
        		System.out.print("/ ");
        	}
        	else {
        		String letter = ""+c; //convert char to string
        		letter = letter.toUpperCase();
        		String temp = letters.get(letter);

        		for(char s: temp.toCharArray()) {
        			if (s=='0') {
        				//System.out.print(s + " ");
        				System.out.print(".");
        				shortbeep();
        			}
        			else {
        				//System.out.print(s + " ");
        				System.out.print("-");
        				longbeep();
        			}
        			pause(2);
        		}
        		System.out.print(" "); //pause between letters
        	}
        }

    }

    public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{
		float SAMPLE_RATE = 8000f;
		byte[] buf = new byte[1];
		AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i=0; i < msecs*8; i++) {
			double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
			buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
			sdl.write(buf,0,1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}

	//dash
	public static void longbeep() {
		try {
			tone(1000, 300, 12.5);
		} catch (LineUnavailableException e) {};
	}

	//dot
	public static void shortbeep() {
		try {
			tone(1000, 100, 12.5);
		} catch (LineUnavailableException e) {};
	}

	//n = 7 or 2
	public static void pause(int n) {
		try {
			tone(1000, n*100, 0.0);
		} catch (LineUnavailableException e) {};
	}
}
