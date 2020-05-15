package lettertreeapp;

import java.io.*;

/**
 *
 * @author Francisco Guerrero
 */
public class LetterTreeApp 
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
