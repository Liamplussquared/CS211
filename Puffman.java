import java.util.*;
public class Puffman{

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

               //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
				Tree tree = new Tree(); //create new tree
				tree.root = new Node(); //set root of tree
				tree.root.letter = (char)i; //convert int to char
				tree.frequency = array[i]; //found earlier

				tree.ascii = (int)tree.root.letter; //ascii value of tree

				PQ.add(tree);
            }
        }


        while(PQ.size()>1){             //while there are two or more Trees left in the forest
            //IMPLEMENT THE HUFFMAN ALGORITHM
			Tree temp1 = PQ.poll(); //pop the PQ
			Tree temp2 = PQ.poll();

			Tree parent = new Tree(); //create new tree
			parent.root = new Node(); //give tree a root
			parent.frequency = temp1.frequency + temp2.frequency; //add the frequencies

			parent.root.rightChild = temp2.root; //more frequent
			parent.root.leftChild = temp1.root; //less frequent

			//ascii is the ASCII value of letter with the lowest ASCII value in the tree
			parent.ascii = Math.min(temp1.ascii, temp2.ascii);

			PQ.add(parent);
        }

        Tree HuffmanTree = PQ.poll();   //only one tree left to be popped

		for(int i =0; i < sentence.length(); i++) {
			System.out.print(HuffmanTree.getCode(sentence.charAt(i))); //print encoded sentence
		}
    }
}

class Tree implements Comparable<Tree>
   {
   public Node root;             // first node of tree
   public int frequency=0;
   public int ascii;

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
        }else{ //if same frequecy, sort by ASCII value
        	if(ascii > object.ascii)
        		return 1;
        	else if(ascii < object.ascii)
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