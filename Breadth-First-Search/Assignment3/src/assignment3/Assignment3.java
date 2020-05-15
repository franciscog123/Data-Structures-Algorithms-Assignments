package assignment3;

/**
 *
 * @author Francisco Guerrero
 */

    class Queue
   {
   private final int SIZE = 20;
   private int[] queArray;
   private int front;
   private int rear;
// -------------------------------------------------------------
   public Queue()            // constructor
      {
      queArray = new int[SIZE];
      front = 0;
      rear = -1;
      }
// -------------------------------------------------------------
   public void insert(int j) // put item at rear of queue
      {
      if(rear == SIZE-1)
         rear = -1;
      queArray[++rear] = j;
      }
// -------------------------------------------------------------
   public int remove()       // take item from front of queue
      {
      int temp = queArray[front++];
      if(front == SIZE)
         front = 0;
      return temp;
      }
// -------------------------------------------------------------
   public boolean isEmpty()  // true if queue is empty
      {
      return ( rear+1==front || (front+SIZE-1==rear) );
      }
// -------------------------------------------------------------
   }  // end class Queue
    
    class Vertex
   {
   public char label;        // label (e.g. 'A')
   public boolean wasVisited;
// -------------------------------------------------------------
   public Vertex(char lab)   // constructor
      {
      label = lab;
      wasVisited = false;
      }
// -------------------------------------------------------------
   }  // end class Vertex
    
    class Graph
   {
   private final int MAX_VERTS = 20;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private Queue theQueue;
// ------------------------------------------------------------
   public Graph()               // constructor
      {
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      for(int j=0; j<MAX_VERTS; j++)      // set adjacency
         for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
            adjMat[j][k] = 0;
      theQueue = new Queue();
      }  // end constructor
// -------------------------------------------------------------
   public void addVertex(char lab)
      {
      vertexList[nVerts++] = new Vertex(lab);
      }
// -------------------------------------------------------------
   public void addEdge(int start, int end)
      {
      adjMat[start][end] = 1;
      adjMat[end][start] = 1;
      }
// -------------------------------------------------------------
   public void displayVertex(int v)
      {
      System.out.print(vertexList[v].label);
      }
// -------------------------------------------------------------
   
   public void bfs()                   // breadth-first search
      {                                // begin at vertex 0
      vertexList[0].wasVisited = true; // mark it
      displayVertex(0);                // display it
      theQueue.insert(0);              // insert at tail
      int v2;

      while( !theQueue.isEmpty() )     // until queue empty,
         {
         int v1 = theQueue.remove();   // remove vertex at head
         // until it has no unvisited neighbors
         while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
            {                                  // get one,
            vertexList[v2].wasVisited = true;  // mark it
            displayVertex(v2);                 // display it
            theQueue.insert(v2);               // insert it
            }   // end while
         }  // end while(queue not empty)

      // queue is empty, so we're done
      for(int j=0; j<nVerts; j++)             // reset flags
         vertexList[j].wasVisited = false;
      }  // end bfs()
// -------------------------------------------------------------
   
   public void bfsMST()                   // breadth-first search
      {                                // begin at vertex 0
      //vertexList[0].wasVisited = true; // mark it
            vertexList[4].wasVisited = true; // mark it

      //displayVertex(0);                // display it
      //theQueue.insert(0);              // insert at tail
            theQueue.insert(4);              // insert at tail

      int v2;

      while( !theQueue.isEmpty() )     // until queue empty,
         {
         int v1 = theQueue.remove();   // remove vertex at head
         // until it has no unvisited neighbors
         while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
            {                                  // get one,
            vertexList[v2].wasVisited = true;  // mark it
            displayVertex(v1);                 // display it
            displayVertex(v2);                 // display it
            System.out.print(" ");
            theQueue.insert(v2);               // insert it
            }   // end while
         }  // end while(queue not empty)

      // queue is empty, so we're done
      for(int j=0; j<nVerts; j++)             // reset flags
         vertexList[j].wasVisited = false;
      }  // end bfs()
   // returns an unvisited vertex adj to v
   
   public int getAdjUnvisitedVertex(int v)
      {
      for(int j=0; j<nVerts; j++)
         if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
            return j;
      return -1;
      }  // end getAdjUnvisitedVertex()
// -------------------------------------------------------------
   }  // end class Graph
    
class Assignment3 
{
    public static void main(String[] args) 
    {
        Graph theGraph = new Graph();
        
        theGraph.addVertex('0');	// 0 (start for bfs)
        theGraph.addVertex('1');        //1
        theGraph.addVertex('2');        //2
        theGraph.addVertex('3');        //3
        theGraph.addVertex('4');        //4
        theGraph.addVertex('5');        //5
        
        theGraph.addEdge(0, 5);     
        theGraph.addEdge(0, 1);     
        theGraph.addEdge(1, 4);     
        theGraph.addEdge(0, 2);     
        theGraph.addEdge(1, 2);     
        theGraph.addEdge(1, 3);     
        theGraph.addEdge(2, 3);     
        theGraph.addEdge(3, 4);     
       // theGraph.addEdge(1, 6);     
       // theGraph.addEdge(3, 7);   
       // theGraph.addEdge(2, 5);     
       // theGraph.addEdge(4, 8);
        
        System.out.print("Visits: ");
        theGraph.bfs();             // breadth-first search
        System.out.println();
        
        System.out.print("Minimum spanning tree: ");
        theGraph.bfsMST();             // minimum spanning tree
        System.out.println();       
    }
}   

