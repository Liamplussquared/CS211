import java.util.*;
public class HuffmanComplete{

    public static void main(String[] args){
       Scanner in = new Scanner(System.in);

       //System.out.print("Enter your sentence: ");
       String sentence = in.nextLine();
       String binaryString="";      //this stores the string of binary code


       for(int i=0; i < sentence.length(); i++){        //go through the sentence
           int decimalValue = (int)sentence.charAt(i);      //convert to decimal
           String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
           for(int j=7;j>binaryValue.length();j--){
               binaryString+="0";           //this loop adds in those pesky leading zeroes
            }
           binaryString += binaryValue+" "; //add to the string of binary
       }
       //System.out.println(binaryString);    //print out the binary


       int[] array = new int[256];      //an array to store all the frequencies

       for(int i=0; i < sentence.length(); i++){    //go through the sentence
           array[(int)sentence.charAt(i)]++;    //increment the appropriate frequencies

       }


       PriorityQueue < Tree >  PQ = new PriorityQueue < Tree >() ;  //make a priority queue to hold the forest of trees


       for(int i=0; i<array.length; i++){ //go through frequency array
           if(array[i]>0){ //print out non-zero frequencies - cast to a char
                //System.out.println("'"+(char)i+"' appeared "+array[i]+((array[i] == 1) ? " time" : " times"));

               //FILL THIS IN:

               //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
				Tree tree = new Tree();
				tree.root = new Node();
				tree.root.letter = (char)i;
				tree.frequency = array[i];

				tree.aFrequency = (int)tree.root.letter;

				PQ.add(tree);
               //create a new Tree
               //set the cumulative frequency of that Tree
               //insert the letter as the root node
               //add the Tree into the PQ

               //System.out.println("frequency: " + tree.frequency);
               //System.out.println(tree.root.letter);

            }
        }


        while(PQ.size()>1){             //while there are two or more Trees left in the forest

            //PHIL THIS IN:

            //IMPLEMENT THE HUFFMAN ALGORITHM
			Tree temp1 = PQ.poll();
			Tree temp2 = PQ.poll();

			Tree master = new Tree();
			master.root = new Node();
			master.frequency = temp1.frequency + temp2.frequency;

			master.root.rightChild = temp2.root; //more frequent
			master.root.leftChild = temp1.root; //less frequent

			//aFrequency is the ASCII value of letter with the lowest ASCII value in the tree
			master.aFrequency = Math.min(temp1.aFrequency, temp2.aFrequency);

			PQ.add(master);
            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining

        }

        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes


        //PHIL THIS IN:
		for(int i =0; i < sentence.length(); i++) {
			System.out.print(HuffmanTree.getCode(sentence.charAt(i)));
		}
        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence

        //print out all the info

    }
}

class Tree implements Comparable<Tree>
   {
   public Node root;             // first node of tree
   public int frequency=0;
   public int aFrequency;

// -------------------------------------------------------------
   public Tree()                  // constructor
      {   root = null; }            // no nodes in tree yet
// -------------------------------------------------------------

//the PriorityQueue needs to be able to somehow rank the objects in it
//thus, the objects in the PriorityQueue must implement an interface called Comparable
//the interface requires you to write a compareTo() method so here it is:

   public int compareTo(Tree object){ //
       if(frequency-object.frequency>0){ //compare the cumulative frequencies of the tree
           return 1;
        }else if(frequency-object.frequency<0){
           return -1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        }else{
        	if(aFrequency > object.aFrequency)
        		return 1;
        	else if(aFrequency < object.aFrequency)
        		return -1;
        	else
            	return 0;   //return 0 if they're the same
        }
   }

// -------------------------------------------------------------

   String path="error";     //this variable will track the path to the letter we're looking for

   public String getCode(char c){  //we want the code for this letter

       //FILL THIS IN:
       //How do you get the code for the letter? Maybe try a traversal of the tree
       //Track the path along the way and store the current path when you arrive at the right letter
			traverse(root, c, "");
			return path;
   }

   private void traverse(Node root, char c, String code) { //traverse recursively
   		if(root!=null)
   			if(root.letter == c)
   				this.path = code;
   			else{
   				traverse(root.leftChild, c, code+"0");
   				traverse(root.rightChild, c, code+"1");
   			}
   			return;
   }

}  // end


class Node
   {

   public char letter='\0';            //stores letter

   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

}  // end class Node