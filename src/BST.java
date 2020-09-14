import java.util.ArrayList;

/**
 * This class is a binary search tree class.
 * It contains an inner class Node.
 */
public class BST {
    /** The root node. */
    private Node root;

    /**
     * Handles the processing of the keywords.
     * @param word the key to be processed by the tree.
     */
    public void add(String word) {
        Node toFind = new Node(word.toLowerCase());
        Node node = search(word);

        if (node == null) {         // if not found
            insert(root, toFind);
        } else {
            node.addCount();        // increment count of instance
        }
    }

    /**
     * Calls search(Node) and passes the word wrapped in the Node class.
     * @param word the keyword to be searched in the tree.
     * @return the Node instance pointer if it is found, null otherwise.
     */
    public Node search(String word) {
        return search(new Node(word.toLowerCase()));
    }

    /**
     * Calls search(Node, Node) and passes the root as the parent node and the node instance
     * to be searched in the tree.
     * @param toFind the node instance to be searched in the tree.
     * @return the Node instance pointer in the tree if it is found, null otherwise.
     */
    public Node search(Node toFind) {
        return search(root, toFind);
    }

    /**
     * Searches the tree for an instance of a parentNode.
     * @param parentNode the parent node.
     * @param toFind the node to be searched for.
     * @return the node instance pointer if found, null otherwise.
     */
    private Node search(Node parentNode, Node toFind) {
        try {
            if (parentNode.equals(toFind)) {                            // Word Found in Child BST.Node
                return parentNode;
            } else if (parentNode.compareTo(toFind) > 0) {              // Search Left Sub Tree of Child BST.Node
                return search(parentNode.left, toFind);
            } else {                                                    // Search Right Sub Tree of Child BST.Node
                return search(parentNode.right, toFind);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Inserts a parentNode into the tree
     * @param parentNode the parent parentNode.
     * @param newNode the node to be inserted.
     */
    private void insert(Node parentNode, Node newNode) {
        try {
            if (parentNode.compareTo(newNode) > 0) {
                if (parentNode.left == null) {
                    parentNode.left = newNode;
                } else {
                    insert(parentNode.left, newNode);
                }
            } else {
                if(parentNode.right == null) {
                    parentNode.right = newNode;
                } else {
                    insert(parentNode.right, newNode);
                }
            }
        } catch (NullPointerException e) { // catches an exception when parentNode is null (only happens when root is null)
            root = newNode;
        }
    }

    /**
     * Returns the list of nodes in the tree in in-order fashion.
     * @return the list of nodes in the tree.
     */
    public ArrayList<Node> inOrder() {
        ArrayList<Node> nodeList = null;

        if (root == null) {
           System.out.println("> No Root BST.Node <");
        } else {
            nodeList = new ArrayList<>();
            inOrder(root, nodeList);
        }

        return nodeList;
    }

    /**
     * Adds the nodes to the ArrayList by in-order transversal.
     * @param node the parent node.
     * @param nodeList the ArrayList to contain all the nodes.
     */
    private void inOrder(Node node, ArrayList<Node> nodeList) {
        if(node.left != null) {         // Traverse Left of Sub Tree
            inOrder(node.left, nodeList);
        }

        nodeList.add(node);             // Add node

        if(node.right != null) {        // Traverse Right of Sub Tree
            inOrder(node.right, nodeList);
        }
    }

    /**
     * Calls max(Node) and passes the tree's root to look for the node
     * with the highest value.
     * @return the pointer to the Node instance with the greatest value.
     */
    public Node max() {
        return max(root);
    }

    /**
     * Searches the tree and returns the node with the highest value.
     * @param node the parent node.
     * @return the pointer to the Node instance with the greatest value.
     */
    public Node max(Node node) {
        return (node.right == null)? node : max(node.right);
    }

    /**
     * Removes the tree from memory.
     */
    public void destroy() {
        root = null;
        System.gc();
    }

    /**
     * The Node class is an inner class because it cannot exist outside of the Tree. The class contains
     * a left and a right Node, the key, and a count. Also implements the Comparable interface and compares
     * the keys of the node to the other Node's.
     */
    public static class Node implements Comparable<Node> {
        /** Pointer to the left child */
        private Node left;
        /** Pointer to the right child */
        private Node right;
        /** The key of the node */
        private final String WORD;
        /** The number of times this word was added to the tree */
        private int count;

        /**
         * This constructor sets the word attribute and initializes the count to 1.
         * @param word the word to set the key of the Node.
         */
        public Node(String word) {
            this.WORD = word;
            count = 1;
        }

        /**
         * Returns the key of the node.
         * @return the key of the node.
         */
        public String getWord() {
            return WORD;
        }

        /**
         * Returns the count of the node.
         * @return the count of the node.
         */
        public int getCount() {
            return count;
        }

        /**
         * Increments the count by 1.
         */
        public void addCount() {
             count++;
        }

        /**
         * Compares the keys and returns the result from the comparison.
         * @param node the node to compare with this node.
         * @return the comparison of the keys.
         */
        @Override
        public int compareTo(Node node) {
            return WORD.compareToIgnoreCase(node.WORD);
        }

        /**
         * Compares if the keys of both nodes are equal (the same string, case-insensitive).
         * @param obj the Object to be compared.
         * @return if the keys are equal.
         */
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Node && WORD.equalsIgnoreCase(((Node) obj).WORD);
        }

        /**
         * Return a formatted String.
         * @return formatted String containing the key and the count.
         */
        @Override
        public String toString() {
            return String.format("%-30s = %-5d\n", WORD, count);
        }
    }
}