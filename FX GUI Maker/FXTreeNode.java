/**
 * The <code>FXTreeNode</code> class is used to create and manipulate
 * nodes for the tree
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    final int maxChildren = 10;

    /**
     * Constructor for a node
     * @param text Text of the node
     * @param type Type of component
     * @param parent Parent node
     * @param children Array of this node's child nodes.
     */
    public FXTreeNode(String text, ComponentType type, FXTreeNode parent,
                      FXTreeNode[] children){
        this.text = text;
        this.type = type;
        this.parent = parent;
        this.children = new FXTreeNode[maxChildren];
    }


    /**
     * Getter for the text of the node
     * @return String of text in the node
     */
    public String getText(){
        return this.text;
    }

    /**
     * Getter for the component type of the node
     * @return The type of component of the node
     */
    public ComponentType getType(){
        return this.type;
    }

    /**
     * Getter for the parent of the node
     * @return The parent node
     */
    public FXTreeNode getParent(){
        return this.parent;
    }

    /**
     * Getter for the children of the node
     * @return An array of the children of the node
     */
    public FXTreeNode[] getChildren(){
        return this.children;
    }

    /**
     * Setter for the text of the node
     * @param text Text to be set as the text of the node
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Setter for the component type of the node
     * @param type Desired component type to change the node to
     */
    public void setType(ComponentType type){
        this.type = type;
    }

    /**
     * Setter for the parent of the node
     * @param parent Parent node to set as the parent of the node
     */
    public void setParent(FXTreeNode parent){
        this.parent = parent;
    }

    /**
     * Setter for the children of the node
     * @param children Array of children to be assigned as the children of the
     *                 node
     */
    public void setChildren(FXTreeNode[] children){
        this.children = children;
    }

    /**
     * Checks if the node has reached its maximum number of children
     * @return Whether the child array is full or not
     */
    public boolean fullChildren(){
        int count = 0;
        for(int i=0;i<this.maxChildren;i++){
            if(children[i] != null){
                count++;
            }
        }

        return(count == maxChildren);
    }

}
