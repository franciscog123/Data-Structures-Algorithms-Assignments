package lettertreeapp;

/**
 *node implementation with character as data value
 * @author Francisco Guerrero
 */
public class Node 
{
    public char data;
    public Node leftChild;
    public Node rightChild;
    
    public void displayNode()
    {
        System.out.print("  "+data);
    }
}
