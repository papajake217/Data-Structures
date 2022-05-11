
import java.util.Scanner;

/**
 * The <code>FXGuiMaker</code> class is used to drive the program, creating
 * a tree or loading one from a text file. This tree can be edited and
 * saved in a text file.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class FXGuiMaker {


    /**
     * Main loop of the program. Allows the user to manipulate the tree
     * and perform various actions.
     * @param args Default main parameters
     */

    public static void main(String[] args){
        FXComponentTree tree = new FXComponentTree();
        Scanner sc = new Scanner(System.in);

        FXTreeNode first = new FXTreeNode("",
                ComponentType.AnchorPane,null,new FXTreeNode[10]);

        tree.setRoot(first);
        tree.cursorToRoot();
        System.out.println("Welcome to counterfeit SceneBuilder");

        printMenu();

        while (true){
            System.out.println("\nEnter an option: ");
            String input = sc.nextLine();
            input = input.toUpperCase();
            switch(input){
                case "L":
                    System.out.println("Enter a filename: ");
                    String filename = sc.nextLine();
                    tree = FXComponentTree.readFromFile(filename);
                    if(tree != null) {
                        tree.cursorToRoot();
                    } else{
                        tree = new FXComponentTree();
                        tree.setRoot(first);
                        tree.cursorToRoot();
                    }
                    break;

                case "P":
                    printTree(tree,tree.getRoot(),0);
                    System.out.println();
                    break;

                case "C":
                    try {
                        int index;
                        System.out.println("Enter number of child" +
                                " (Starting at 1): ");
                        index = sc.nextInt();
                        sc.nextLine();
                        if (tree.getCursor().getChildren()[index - 1] != null) {
                            tree.cursorToChild(index);
                        } else {
                            System.out.println("Error, invalid child index");
                        }
                    } catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Error index is out of bounds");
                    }

                    break;

                case "R":
                    if(tree.getRoot() != null){
                        tree.cursorToRoot();
                        System.out.println("Cursor moved to root");
                    } else{
                        System.out.println("Error, tree is empty");
                    }
                    break;

                case "A":
                    if(tree.getCursor().getType() == ComponentType.HBox
                            || tree.getCursor().getType() == ComponentType.VBox
                            || (tree.getCursor().getType() ==
                            ComponentType.AnchorPane && tree.getCursor().
                            getChildren()[0] == null)) {
                        if(!tree.getCursor().fullChildren()) {
                            System.out.println("Select component type " +
                                    "(H - HBox, V - VBox, T - TextArea, B - " +
                                    "Button, L - Label): ");
                            String componentType = sc.nextLine().toUpperCase();
                            ComponentType userComponent = null;
                            boolean valid = true;
                            switch (componentType) {
                                case "H":
                                    userComponent = ComponentType.HBox;
                                    break;
                                case "V":
                                    userComponent = ComponentType.VBox;
                                    break;
                                case "T":
                                    userComponent = ComponentType.TextArea;
                                    break;
                                case "B":
                                    userComponent = ComponentType.Button;
                                    break;
                                case "L":
                                    userComponent = ComponentType.Label;
                                    break;
                                default:
                                    System.out.println("Error, " +
                                            "invalid component type");
                                    valid = false;
                                    break;
                            }


                            if (valid) {
                                String userText = "";
                                if (userComponent == ComponentType.Button ||
                                        userComponent == ComponentType.Label ||
                                        userComponent == ComponentType.
                                                TextArea) {
                                    System.out.println("Enter text: ");
                                    userText = sc.nextLine();
                                }

                                System.out.println("Enter an index " +
                                        "(Starting at 1): ");
                                int childIndex = sc.nextInt();
                                sc.nextLine();
                                int maxIndex = 0;
                                for (int i = 0; i < tree.getCursor().
                                        getChildren().length; i++) {
                                    if (tree.getCursor().
                                            getChildren()[i] != null) {
                                        maxIndex++;
                                    }
                                }


                                if (childIndex <= maxIndex + 1) {
                                    if (childIndex >= 1 && childIndex <= 10) {
                                        FXTreeNode userNode =
                                                new FXTreeNode(userText,
                                                        userComponent,
                                                        tree.getCursor(),
                                                        new FXTreeNode[10]);
                                        tree.addChild(childIndex, userNode);
                                        tree.cursorToChild(childIndex);
                                    } else {
                                        System.out.println("Error, invalid " +
                                                "index."
                                                + "Tree" +
                                                " is unchanged");
                                    }
                                } else {
                                    System.out.println("Error, invalid " +
                                            "index, " +
                                            "resulting in a hole. Tree is " +
                                            "unchanged");
                                }
                            }
                        } else{
                            System.out.println("Error, node already has " +
                                    "maximum amount of children");
                        }
                    }
                    else{
                        System.out.println("Error, cannot add " +
                                "children to component because it is " +
                                "either the AnchorPane and has one child," +
                                " or it is a type that "+
                                "is not a container");
                    }


                    break;

                case "U":
                    if(tree.getCursor() == tree.getRoot()){
                        System.out.println("Cursor is already at root, " +
                                "no change has been made");

                    } else{
                        tree.cursorToParent();
                        System.out.println("Cursor moved to " +
                                tree.getCursor().getType() + ": " +
                                tree.getCursor().getText());
                    }

                    break;

                case "E":
                    if(tree.getCursor().getType() != ComponentType.Button &&
                            tree.getCursor().getType() != ComponentType.Label &&
                            tree.getCursor().getType() !=
                                    ComponentType.TextArea){
                        System.out.println("Error, selected component" +
                                " cannot have text");
                    } else {
                        System.out.println("Enter new text: ");
                        String newText = sc.nextLine();
                        tree.getCursor().setText(newText);
                        System.out.println("Text edited");
                    }
                    break;

                case "D":
                    System.out.println("Enter number of child " +
                            "(Starting with 1): ");
                    int chosenIndex = sc.nextInt();
                    sc.nextLine();
                    if(chosenIndex <= 10 && chosenIndex >= 1) {
                        if(tree.getCursor().getChildren()[chosenIndex-1]
                                == null){
                            System.out.println("Error, no child at " +
                                    "this index");
                        } else {
                            FXTreeNode removedChild = tree.getCursor().
                                    getChildren()[chosenIndex-1];
                            tree.deleteChild(chosenIndex);
                            String returnMsg = "" + removedChild.getType();
                            if(!(removedChild.getText().equals(""))){
                                returnMsg += ": " + removedChild.getText();
                            }
                            returnMsg += " removed";
                            System.out.println(returnMsg);
                        }
                    } else{
                        System.out.println("Error, invalid index");
                    }



                    break;


                case "S":
                    if(tree.getRoot() == null){
                        System.out.println("Tree is empty, cannot be saved");
                    } else {
                        System.out.println("Please enter a filename");
                        String file = sc.nextLine();
                        FXComponentTree.writeToFile(tree, file);
                    }
                    break;

                case "Q":
                    System.out.println("Make like a tree and leave");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }

        }


    }

    /**
     * Prints all the options available to the user
     */
    public static void printMenu(){
        System.out.println("Menu:" +
                "\n" +
                "    L) Load from file" +
                "\n" +
                "    P) Print tree" +
                "\n" +
                "    C) Move cursor to a child node" +
                "\n" +
                "    R) Move cursor to root" +
                "\n" +
                "    A) Add a child" +
                "\n" +
                "    U) Cursor up (to parent)" +
                "\n" +
                "    E) Edit text of cursor" +
                "\n" +
                "    D) Delete child" +
                "\n" +
                "    S) Save to file" +
                "\n" +
                "    X) Export FXML" +
                "\n" +
                "    Q) Quit");
    }


    /**
     * Prints the current tree
     * @param tree Tree to print
     * @param root Root of the tree
     * @param spaces Tracks the spaces needed while printing
     */
    public static void printTree(FXComponentTree tree,
                                 FXTreeNode root,int spaces){

        for(int i=0;i<spaces;i++){
            System.out.print("  ");
        }
        if(root == tree.getCursor()){
            System.out.print("==>");
        } else if(root != tree.getRoot()){
            System.out.print("+--");
        }
        System.out.print(root.getType().toString());
        if(!(root.getText().equals(""))){
            System.out.print(": " + root.getText());
        }
        System.out.print("\n");

        for(int i=0;i<root.getChildren().length;i++){
            if(root.getChildren()[i] != null) {
                printTree(tree,root.getChildren()[i],spaces+1);
            }
        }
    }
}
