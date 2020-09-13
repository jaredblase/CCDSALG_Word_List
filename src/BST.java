public class BST {
    private Node root;

    public Node search(String word) {
        word = word.toLowerCase();
        return search(root, word);
    }

    private Node search(Node node, String word) {
        try {
            if (word.equals(node.getWord())) {          // Word Found in Child BST.Node
                return node;
            } else if (word.compareTo(node.getWord()) < 0) {   // Search Left Sub Tree of Child BST.Node
                return node.left == null ? node : search(node.left, word);
            } else {                                                    // Search Right Sub Tree of Child BST.Node
                return node.right == null ? node : search(node.right, word);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void insert(String word) {
        word = word.toLowerCase();
        insert(search(word), word);
    }

    private void insert(Node node, String word) {
        if(node == null) {                                          // No Root BST.Node then Create Root BST.Node
            root = new Node(word);
        } else if(node.getWord().equals(word)) {          // Word Found in Child BST.Node (Increment)
            node.addCount();
        } else if(word.compareTo(node.getWord()) < 0) {   // Word < Root Word
            node.left = new Node(word);
        } else {                                                    // Word > Root Word
            node.right = new Node(word);
        }
    }

    public void inOrder() {
        // Column Heads
        System.out.format("%-30s - %-5s\n", "Word", "Count");
       
        if (root == null) {
           System.out.println("> No Root BST.Node <");
        } else {
           inOrder(root);
        }
    }

    private void inOrder(Node tree) {
        // Traverse Left of Sub Tree
        if(tree.left != null) {
            inOrder(tree.left);
        }
          
        // Print This BST.Node's Word & Count
        System.out.format("%-30s = %-5d\n", tree.getWord(), tree.getCount());

        // Traverse Right of Sub Tree
        if(tree.right != null) {
            inOrder(tree.right);
        }
    }

    public void destroy(String word) {
        Node toReplace;

        if(root.getWord().equals(word)) {
            toReplace = getSuccessor(root);

            if (toReplace != null) {
                Node temp = getParent(root, toReplace);
                if (toReplace.equals(temp.left)) {
                    temp.left = null;
                } else {
                    temp.right = null;
                }

                toReplace.left = root.left;
                toReplace.right = root.right;
            }

            root = toReplace;
            return;
        }

        Node toDestroy = new Node(word);
        Node parent = getParent(root, toDestroy);

        try {
            toDestroy = toDestroy.equals(parent.left)? parent.left : parent.right;
            toReplace = getSuccessor(toDestroy);

            if (toReplace != null) {
                Node temp = getParent(root, toReplace);
                if (toReplace.equals(temp.left)) {
                    temp.left = null;
                } else {
                    temp.right = null;
                }

                toReplace.left = toDestroy.left;
                toReplace.right = toDestroy.right;
            }

            if (toDestroy.equals(parent.left)) {
                parent.left = toReplace;
            } else {
                parent.right = toReplace;
            }
        } catch (Exception e) {
            System.out.println("Word does not exist!");
        }

    }

    public Node getParent(Node node, Node child) {
        try {
            if (child.equals(node.left) || child.equals(node.right)) {          // Parent found
                return node;

            } else if (node.compareTo(child) > 0) {   // Search Left Sub Tree of Child BST.Node
                return node.left == null ? node : getParent(node.left, child);

            } else {                                                    // Search Right Sub Tree of Child BST.Node
                return node.right == null ? node : getParent(node.right, child);

            }
        } catch (Exception e) {
            return null;
        }
    }

    private Node getHighestNode(Node node) {
        return node.right == null? node : getHighestNode(node.right);
    }

    public Node getSuccessor(Node node) {
        node = search(node.getWord());
        if (node.left == null) {
            return node.right;
        } else {
            return getHighestNode(node.left);
        }
    }

    /**
     * The Node class is an inner class because it cannot exist outside of the Tree.
     */
    public static class Node implements Comparable<Node> {
        public Node left;
        public Node right;
        private final String word;
        private int count;

        public Node(String word) {
            this.word = word;
            count = 1;
        }

        public int getCount() {
            return count;
        }

        public String getWord() {
            return word;
        }

        public void addCount() {
             count++;
        }

        @Override
        public int compareTo(Node node) {
            return word.compareTo(node.word);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Node && word.equals(((Node) obj).word);
        }

        public void printNode() {
            System.out.format("%-30s = %-5d\n", word, count);
        }
    }
}