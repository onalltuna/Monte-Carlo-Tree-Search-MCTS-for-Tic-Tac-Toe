import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing the game tree
 */
public class Tree {

    public Node root;
    private static double threshold = 0.4;
    public static Random rand = Main.rand;

    /**
     * Default constructor for the tree.
     * The root of the tree is set to the initial configuration of the tic-tac-toe game
     */
    public Tree() {
        this.root = new Node();
    }

    /**
     * Selects a leaf node or a node with non-empty list of possible moves.
     * - Starting from the tree root, gets the list of non-null children of the current node by using getChildrenIdxs() method.
     *      - If the current node is an unfinished game i.e. has unplayed moves (use node.getUnplayedMoves() method):
     *          - Generate a random number between 0 and 1 (1 excluded)
     *              - If the generated number is greater than the tree threshold return the current node
     *      - Select a child of the current node using node.selectChild() method (this should be done if the generated number is less than or equal to the threshold too.)
     *  - Repeat the above steps until you either arrive at a leaf node (number of children is 0) 
     or you have returned from the method because of the generated random number being greater than the threshold
     *                  
     * @return the selected node
     */
    public Node select() {
        /* TODO */
    
    	Node cur = this.root;
    	Node cur2;
    	
    	ArrayList<Integer> children = cur.getChildrenIdxs();
    	
    		while(children.size() > 0) {
    			
    			if(cur.getUnplayedMoves().isEmpty() != true) {
    				
            		double x = rand.nextDouble();
          
            		if(x > threshold) {
            			return cur;
            			
            		} else {
            			
            			children = cur.getChildrenIdxs();
            			cur2 = cur.selectChild(children);
            			
            			if(cur2 != null) {
            				
            				cur = cur2;
            				children = cur.getChildrenIdxs();
            				
            			} 
            		}
    				
    			} else {

    				cur2 = cur.selectChild(children);
    				
        			if(cur2 != null) {
        				cur = cur2;
        				children = cur.getChildrenIdxs();	
        			} 	
    			}
        	} 
    		
        return cur;
    }

    /**
     * Removes the worst performing node at the specified level in the tree if the level is not empty
     * @param level the level from which to remove the worst performing node
     */
    public void pruneTree(int level) {

        ArrayList<Node> parents = this.getNodesAtLevel(level - 1); 
        Node worstNodeParent = null;
        int worstScoreChildIndex = 0;
        double worstScore = Double.MAX_VALUE;
        double currentChildScore;

        for (Node parent : parents) {
            for (int i = 0; i < parent.getChildren().length; i++) {
                Node child = parent.getChildren()[i];
                if (child != null) {
                    currentChildScore = child.getScore();
                    if (currentChildScore < worstScore) {
                        worstScore = currentChildScore;
                        worstNodeParent = parent;
                        worstScoreChildIndex = i;
                    }
                }
            }
        }

        if (worstNodeParent == null)
            return;
        worstNodeParent.removeChild(worstScoreChildIndex);
    }

    /**
     * Retrieves the nodes at the specified level in the tree. Root is at level 0
     * @param level the level required
     * @return ArrayList of nodes in the specified level
     */
    public ArrayList<Node> getNodesAtLevel(int level) {
        /* TODO */
    	
    	ArrayList<Node> nodes = new ArrayList<Node>();
    	ArrayList<Node> child = new ArrayList<Node>();
    	ArrayList<Node> child3 = new ArrayList<Node>();
    	
    	child.add(this.root);
    	
    	Node[] child2 = child.get(0).getChildren();
    
    	int i = 0;
    	
    		if(this.root == null) {
    			return null;
    		}
    		
    		if(level == 0) {
    			nodes.add(this.root);
    		}
    		
    		while(i != level) {
    			for(int k = 0; k < child.size(); k++) {
    				
    					if(child.get(k) != null) {
    						 child2 = child.get(k).getChildren();
    						 
    						 if((i + 1) == level) {
    	    						for(int j = 0; j < child2.length; j++) {
    	    							if(child2[j] != null) {
    	    								nodes.add(child2[j]);
    	    							}
    	    						}
    	    						
    	    					} else {
    	    						
    	    						for(int m = 0; m < child2.length; m++) {
    	    							if(child2[m] != null) {
    	    								child3.add(child2[m]);
    	    							}			
    	    						}	
    	    					}
    					} 
    			}
    			
    			child.clear();
    			
    			for(int l = 0; l < child3.size(); l++) {
    				child.add(child3.get(l));
    			}
    			i++;
    			child3.clear();
    		}
    	
    	return nodes;
    
    }

    public String toString() {
        return root.toString();
    }
}
