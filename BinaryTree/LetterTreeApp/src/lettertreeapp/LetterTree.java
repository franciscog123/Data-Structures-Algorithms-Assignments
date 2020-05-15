package lettertreeapp;

import java.util.Stack;

/**A tree implementation that uses letters instead of numbers
 *
 * @author Francisco Guerrero
 */

public class LetterTree 
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
