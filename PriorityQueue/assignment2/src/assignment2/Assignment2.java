package assignment2;

import java.io.*;

/**
 *implementing the priorityQueue class using a heap instead of array.
 * @author Francisco Guerrero
 */

class Node
   {
   private int iData;             // data item (key)
// -------------------------------------------------------------
   public Node(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   public void setKey(int id)
      { iData = id; }
// -------------------------------------------------------------
   }  // end class Node
////////////////////////////////////////////////////////////////
class Heap
   {
   private Node[] heapArray;
   private int maxSize;           // size of array
   private int currentSize;       // number of nodes in array
// -------------------------------------------------------------
   public Heap(int mx)            // constructor
      {
      maxSize = mx;
      currentSize = 0;
      heapArray = new Node[maxSize];  // create array
      }
// -------------------------------------------------------------
   public boolean isEmpty()
      { return currentSize==0; }
// -------------------------------------------------------------
   public boolean insert(int key)
      {
      if(currentSize==maxSize)
         return false;
      Node newNode = new Node(key);
      heapArray[currentSize] = newNode;
      trickleUp(currentSize++);
      return true;
      }  // end insert()
// -------------------------------------------------------------
   public void trickleUp(int index)
      {
      int parent = (index-1) / 2;
      Node bottom = heapArray[index];

      while( index > 0 &&
             heapArray[parent].getKey() < bottom.getKey() )
         {
         heapArray[index] = heapArray[parent];  // move it down
         index = parent;
         parent = (parent-1) / 2;
         }  // end while
      heapArray[index] = bottom;
      }  // end trickleUp()
// -------------------------------------------------------------
   public Node remove()           // delete item with max key
      {                           // (assumes non-empty list)
      Node root = heapArray[0];
      heapArray[0] = heapArray[--currentSize];
      trickleDown(0);
      return root;
      }  // end remove()
// -------------------------------------------------------------
   public void trickleDown(int index)
      {
      int largerChild;
      Node top = heapArray[index];       // save root
      while(index < currentSize/2)       // while node has at
         {                               //    least one child,
         int leftChild = 2*index+1;
         int rightChild = leftChild+1;
                                         // find larger child
         if(rightChild < currentSize &&  // (rightChild exists?)
                             heapArray[leftChild].getKey() <
                             heapArray[rightChild].getKey())
            largerChild = rightChild;
         else
            largerChild = leftChild;
                                         // top >= largerChild?
         if( top.getKey() >= heapArray[largerChild].getKey() )
            break;
                                         // shift child up
         heapArray[index] = heapArray[largerChild];
         index = largerChild;            // go down
         }  // end while
      heapArray[index] = top;            // root to index
      }  // end trickleDown()
// -------------------------------------------------------------
   public boolean change(int index, int newValue)
      {
      if(index<0 || index>=currentSize)
         return false;
      int oldValue = heapArray[index].getKey(); // remember old
      heapArray[index].setKey(newValue);  // change to new

      if(oldValue < newValue)             // if raised,
         trickleUp(index);                // trickle it up
      else                                // if lowered,
         trickleDown(index);              // trickle it down
      return true;
      }  // end change()
// -------------------------------------------------------------
   public void displayHeap()
      {
      System.out.print("heapArray: ");    // array format
      for(int m=0; m<currentSize; m++)
         if(heapArray[m] != null)
            System.out.print( heapArray[m].getKey() + " ");
         else
            System.out.print( "-- ");
      System.out.println();
                                          // heap format
      int nBlanks = 32;
      int itemsPerRow = 1;
      int column = 0;
      int j = 0;                          // current item
      String dots = "...............................";
      System.out.println(dots+dots);      // dotted top line

      while(currentSize > 0)              // for each heap item
         {
         if(column == 0)                  // first item in row?
            for(int k=0; k<nBlanks; k++)  // preceding blanks
               System.out.print(' ');
                                          // display item
         System.out.print(heapArray[j].getKey());

         if(++j == currentSize)           // done?
            break;

         if(++column==itemsPerRow)        // end of row?
            {
            nBlanks /= 2;                 // half the blanks
            itemsPerRow *= 2;             // twice the items
            column = 0;                   // start over on
            System.out.println();         //    new row
            }
         else                             // next item on row
            for(int k=0; k<nBlanks*2-2; k++)
               System.out.print(' ');     // interim blanks
         }  // end for
      System.out.println("\n"+dots+dots); // dotted bottom line
      }  // end displayHeap()
// -------------------------------------------------------------
   }  // end class Heap
////////////////////////////////////////////////////////////////


//priority queue based on heap class
class priorityQueue
{
    private Heap theHeap;
    
    public priorityQueue(int size)
    {
        theHeap=new Heap(size);
    }
    
    public boolean insert(int value)
    {
        return theHeap.insert(value);
    }
    
    public int remove()
    {
        return theHeap.remove().getKey();
    }
    
    public boolean isEmpty()
    {
        return theHeap.isEmpty();
    }
    
    public void displayQueue()
    {
        theHeap.displayHeap();
    }
}

public class Assignment2 
{

    public static void main(String[] args) throws IOException
    {
      int value;
      priorityQueue theQueue = new priorityQueue(31);  // make a priority queue based on a heap; max size 31
      boolean success;

      theQueue.insert(70);           // insert 10 items
      theQueue.insert(40);
      theQueue.insert(50);
      theQueue.insert(20);
      theQueue.insert(60);
      theQueue.insert(100);
      theQueue.insert(80);
      theQueue.insert(30);
      theQueue.insert(10);
      theQueue.insert(90);

      while(true)                   // until [Ctrl]-[C]
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, remove, or exit: ");
         int choice = getChar();
         switch(choice)
            {
            case 's':                        // show
               theQueue.displayQueue();
               break;
            case 'i':                        // insert
               System.out.print("Enter value to insert: ");
               value = getInt();
               success = theQueue.insert(value);
               if( !success )
                  System.out.println("Can't insert; heap full");
               break;
            case 'r':                        // remove
               if( !theQueue.isEmpty() )
                  theQueue.remove();
               else
                  System.out.println("Can't remove; heap empty");
               break;
            case 'e':                       //exit app
                return;
            default:
               System.out.println("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
//-------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
//-------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
//-------------------------------------------------------------
}
    

