import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The <code>FXComponentTree</code> class is used to create and manipulate
 * a tree containing FXTreeNodes
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;

    /**
     * Resets the cursor to the root of the tree
     */
    public void cursorToRoot(){
        this.cursor = this.root;
    }

    /**
     * Deletes the child of the node at the cursor at a specified index
     * @param index Index of the child to be deleted
     */
    public void deleteChild(int index){
        index--;
        FXTreeNode[] children = cursor.getChildren();
        for(int i=index;i<children.length-1;i++){
            children[i] = children[i+1];
        }
        children[children.length-1] = null;
        cursor.setChildren(children);
    }

    /**
     * Adds a child node at a specified index of the cursor node
     * @param index Index of the child array to add the node to
     * @param node Node to become a child of the cursor node
     */

    public void addChild(int index, FXTreeNode node){
        try {
            FXTreeNode[] children = cursor.getChildren();
            if(index > children.length || index < 1){
                throw new IndexOutOfBoundsException();
            }

            index--;

            for (int i = children.length-1; i >= index+1; i--) {
                children[i] = children[i - 1];
            }
            children[index] = node;
            cursor.setChildren(children);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Error, index is out of bounds");
        }
    }

    /**
     * Sets text of the cursor node
     * @param text Text to replace the current text of the cursor node
     */
    public void setTextAtCursor(String text){
        cursor.setText(text);
    }

    /**
     * Moves the cursor to a specified child of itself.
     * @param index The index of the child node to set the cursor as
     */
    public void cursorToChild(int index){
        FXTreeNode[] children = cursor.getChildren();
        index--;
        cursor = children[index];
        System.out.println("Cursor moved to " + cursor.getType().toString() +
                ": " + cursor.getText());
    }

    /**
     * Gets the root of the tree
     * @return The root node of the tree
     */
    public FXTreeNode getRoot(){
        return this.root;
    }

    /**
     * Sets the root of the tree
     * @param node Node to be set as the root of the tree
     */
    public void setRoot(FXTreeNode node){
        this.root = node;
    }

    /**
     * Gets the current cursor node
     * @return The node the cursor is currently at
     */
    public FXTreeNode getCursor(){
        return this.cursor;
    }


    /**
     * Moves the cursor to its parent
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
    }

    /**
     * Reads a tree from a text file
     * @param filename Name of the text file that is being read from
     * @return The tree constructed from the text file
     */
    public static FXComponentTree readFromFile(String filename){
        try {
            File inputFile = new File(filename);
            Scanner sc = new Scanner(inputFile);
            FXComponentTree inputTree = new FXComponentTree();
            String firstLine = sc.nextLine();
            String[] firstLineArray = firstLine.split(" ");
            String firstComponent = firstLineArray[1];
            FXTreeNode root = new FXTreeNode("",ComponentType.
                    valueOf(firstComponent),null,
                    new FXTreeNode[10]);
            inputTree.root = root;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] lineArray = line.split(" ",3);
                String position = lineArray[0];
                String componentType = lineArray[1];
                String name = "";
                if(lineArray.length > 2) {
                    name = lineArray[2];
                }
                FXTreeNode temp = root;
                FXTreeNode parent = root;
                int index = 0;
                for(int i=2;i<position.length();i+=2){
                    index = position.charAt(i) - '0';
                    parent = temp;
                    temp = temp.getChildren()[index];
                }

                temp = new FXTreeNode(name,ComponentType.
                        valueOf(componentType),parent,new FXTreeNode[10]);
                parent.getChildren()[index] = temp;



                }

            System.out.println(filename + " loaded");
            return inputTree;
        }catch(FileNotFoundException e){
            System.out.println("Error, " + filename + " not found");
        }




        return null;
    }


    /**
     * Writes the current tree in a text file and saves it
     * @param tree Tree to be saved
     * @param filename Name of the text file to save it in
     */
    public static void writeToFile(FXComponentTree tree,String filename){
        try {
            FileWriter myFile = new FileWriter(filename);
            String fileString = getFileString(tree,tree.root,"0");
            myFile.write(fileString);
            System.out.println("File saved");
            finalString = "";
            myFile.close();

        } catch(IOException e){
            System.out.println("Error cannot write to this file");
        }

    }


    private static String finalString = "";

    /**
     * Converts the tree to a string representation in the same format it
     * reads in
     * @param tree Tree to be written as a string
     * @param root Root of the tree
     * @param position Position of a given node
     * @return A string representing the tree
     */
    public static String getFileString(FXComponentTree tree,FXTreeNode root,
                                     String position){

        finalString += position + " " + root.getType() + " " +
                root.getText() + "\n";


        for(int i=0;i<root.maxChildren;i++){
            if(root.getChildren()[i] != null){
                position += "-" + String.valueOf(i);
                getFileString(tree,root.getChildren()[i],position);
                position = position.substring(0,position.length()-2);
            }
        }

        return finalString;
    }



}
