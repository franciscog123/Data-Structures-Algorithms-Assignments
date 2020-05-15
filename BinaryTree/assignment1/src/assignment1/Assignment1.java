package assignment1;

import java.util.Stack;
import java.io.*;

/**
 *A tree implementation that uses letters instead of numbers
 * @author Francisco Guerrero
 */

    class Node 
    {
        public char data;
        public Node leftChild;
        public Node rightChild;
    
        public void displayNode()
        {
            System.out.print("  "+data);
        }
    }
    
    class LetterTree 
    {
        private Node root;

        //default constructor, creates empty tree
        public LetterTree()
        {
            root=null;
        }

        //creates tree out of a string
        public LetterTree(String letters)
        {
            //array to store nodes
            Node [] treeArray=new Node[letters.length()];

            //add each letter in string as an individual node to array of nodes
            for(int index=0; index<letters.length();index++)
            {
                treeArray[index]=new Node();
                treeArray[index].data=letters.charAt(index);
            }

            //create subtree out of first two letters in array
            Node nonLetterNode=new Node();
            nonLetterNode.data='+';
            nonLetterNode.leftChild=treeArray[0];
            nonLetterNode.rightChild=treeArray[1];

            //adds the rest of the letters in the string to the tree
            for(int index=2; index<letters.length();index++)
            {
                //creates subtree with next value in array and adds it on top of previous subtree
                Node newNode=new Node();
                newNode.data='+';
                newNode.leftChild=nonLetterNode;
                newNode.rightChild=treeArray[index];
                nonLetterNode= newNode;
            }

            //sets the new '+' node as the root
            root=nonLetterNode;
        }

        //adds another character to the tree
        public void insert(char letter)
        {
                Node newNode=new Node();
                newNode.data=letter;
                Node nonLetterNode=new Node();
                nonLetterNode.data='+';
                nonLetterNode.leftChild=root;
                nonLetterNode.rightChild=newNode;

                root=nonLetterNode;
        }

        //calls traversal methods based on user input
        public void traverse(int traverseType)
          {
          switch(traverseType)
             {
             case 1: System.out.print("\nPreorder traversal: ");
                     preOrder(root);
                     break;
             case 2: System.out.print("\nInorder traversal:  ");
                     inOrder(root);
                     break;
             case 3: System.out.print("\nPostorder traversal: ");
                     postOrder(root);
                     break;
             }
          System.out.println();
          }
    // -------------------------------------------------------------
       //prefix expression
        private void preOrder(Node localRoot)
          {
          if(localRoot != null)
             {
             System.out.print(localRoot.data + " ");
             preOrder(localRoot.leftChild);
             preOrder(localRoot.rightChild);
             }
          }
    // -------------------------------------------------------------
        //infix expression
        private void inOrder(Node localRoot)
          {
          if(localRoot != null)
             {
             inOrder(localRoot.leftChild);
             System.out.print(localRoot.data + " ");
             inOrder(localRoot.rightChild);
             }
          }
    // -------------------------------------------------------------
        //postfix expression
        private void postOrder(Node localRoot)
          {
          if(localRoot != null)
             {
             postOrder(localRoot.leftChild);
             postOrder(localRoot.rightChild);
             System.out.print(localRoot.data + " ");
             }
          }
    // -------------------------------------------------------------
      //displays tree
       public void displayTree()
          {
          Stack globalStack = new Stack();
          globalStack.push(root);
          int nBlanks = 64;
          boolean isRowEmpty = false;
          System.out.println(
          "......................................................");
          while(isRowEmpty==false)
             {
             Stack localStack = new Stack();
             isRowEmpty = true;

             for(int j=0; j<nBlanks; j++)
                System.out.print(' ');

             while(globalStack.isEmpty()==false)
                {
                Node temp = (Node)globalStack.pop();
                if(temp != null)
                   {
                   System.out.print(temp.data);
                   localStack.push(temp.leftChild);
                   localStack.push(temp.rightChild);

                   if(temp.leftChild != null ||
                                       temp.rightChild != null)
                      isRowEmpty = false;
                   }
                else
                   {
                   System.out.print("--");
                   localStack.push(null);
                   localStack.push(null);
                   }
                for(int j=0; j<nBlanks*2-2; j++)
                   System.out.print(' ');
                }  // end while globalStack not empty
             System.out.println();
             nBlanks /= 2;
             while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
             }  // end while isRowEmpty is false
          System.out.println(
          "......................................................");
          } 

    }

    public class Assignment1
    {
        public static void main(String[] args) throws IOException 
        {
            int value;
            char letter;
            String letters="ABCD";

            //creates a tree with ABCD
            LetterTree tree =new LetterTree(letters);

            //gives user option to display tree, insert a value, traverse the tree
            //or exit the program
            while(true)
             {
             System.out.print("Enter first letter of show, ");
             System.out.print("insert, or traverse. Type e to exit program: ");
             int choice = getChar();
             switch(choice)
                {
                case 's':
                   tree.displayTree();
                   break;
                case 'i':
                   System.out.print("Enter value to insert: ");
                   letter = getChar();
                   tree.insert(letter);
                   break;
                case 't':
                   System.out.print("Enter type 1, 2 or 3: ");
                   value = getInt();
                   tree.traverse(value);
                   break;
                case 'e':
                    System.out.println("Goodbye");
                    return;
                default:
                   System.out.print("Invalid entry\n");
                }  // end switch
             }  //
        }

        //reads in a user input string
        public static String getString() throws IOException
          {
          InputStreamReader isr = new InputStreamReader(System.in);
          BufferedReader br = new BufferedReader(isr);
          String s = br.readLine();
          return s;
          }
    // -------------------------------------------------------------
       //reads in a user input string and returns first character
        public static char getChar() throws IOException
          {
          String s = getString();
          return s.charAt(0);
          }
    //-------------------------------------------------------------
       //reads in a user input string and returns first char as an int
        public static int getInt() throws IOException
          {
          String s = getString();
          return Integer.parseInt(s);
          }

    }





