public class BST {
     // Attritubtes ::::::::::::::::::::::::::::::::::::::::::::::::::
    private Node root;


     // Create / Constructor ::::::::::::::::::::::::::::::::::::::::::::::::::
     
    public BST() {                                                  
        // Create()
    }


    // Search ::::::::::::::::::::::::::::::::::::::::::::::::::
    
    // Root
    public boolean search(String word) {
        if(root == null) {                                          // No Root Node
            return false;

        } else if(word.equalsIgnoreCase(root.getWord())) {          // Word Found in Root Node
            return true;

        } else if(word.compareToIgnoreCase(root.getWord()) < 0){    // Search Left Sub Tree
            return search(root.left, word);

        } else {                                                    // Search Right Sub Tree
            return search(root.right, word);

        }
    }

    // Subtree
    private boolean search(Node tree, String word) {
        if(tree == null) {                                          // No Child Node
            return false;

        } else if(word.equalsIgnoreCase(tree.getWord())) {          // Word Found in Child Node
            return true;

        } else if(word.compareToIgnoreCase(tree.getWord()) < 0) {   // Search Left Sub Tree of Child Node
            return search(tree.left, word);

        } else {                                                    // Search Right Sub Tree of Child Node
            return search(tree.right, word);
            
        }
    }


    // Insert ::::::::::::::::::::::::::::::::::::::::::::::::::
    
    // Root
    public void insert(String word) {   
        if(root == null) {                                          // No Root Node then Create Root Node
            root = new Node(word);

        } else if(root.getWord().equalsIgnoreCase(word)) {          // Word Found in Root Node (Increment Count)
            root.addCount();

        } else if(word.compareToIgnoreCase(root.getWord()) < 0) {   // Word < Root Word
            root.left = insert(root.left, word);

        } else {                                                    // Word > Root Word
            root.right = insert(root.right,word);

        }
    }

    // Sub Trees
    private Node insert(Node tree, String word) {
        if(tree == null) {                                          // No Root Node then Create Root Node
            tree = new Node(word);

        } else if(tree.getWord().equalsIgnoreCase(word)) {          // Word Found in Child Node (Increment)
            tree.addCount();

        } else if(word.compareToIgnoreCase(tree.getWord()) < 0) {   // Word < Root Word
            tree.left = insert(tree.left, word);

        } else {                                                    // Word > Root Word
            tree.right = insert(tree.right,word);

        }

        return tree;
    }


    // inOrder :::::::::::::::::::::::::
    
    // Root
    public void inOrder() {
        // Column Heads
        System.out.format("%-30s - %-5s\n", "Word", "Count");
        // Traverse Left of Root
        try {
            inOrder(root.left);
        } catch (NullPointerException e) {
            // Ignore
        }
       
        // Print The Root Node's Word & Count
        try {
             System.out.format("%-30s = %-5d\n", root.getWord(), root.getCount());
        } catch (NullPointerException e) {
            System.out.println("> No Root Node <");
        }
       
        // Traverse Right of Root
        try {
            inOrder(root.right);
        } catch (NullPointerException e) {
            // Ignore
        }
    }

    // Sub Tree
    private void inOrder(Node tree) {
        // Traverse Left of Sub Tree
        try {
            inOrder(tree.left);
        } catch (NullPointerException e) {
            // Ignore
        }
          
        // Print This Node's Word & Count
        System.out.format("%-30s = %-5d\n", tree.getWord(), tree.getCount());

        // Traverse Right of Sub Tree
        try {
           
            inOrder(tree.right);
        } catch (NullPointerException e) {
            // Ignore
        }
    }


    // Destroy ::::::::::::::::::::::::::::::::::::::::::::::::::
    
    // Root
    public void destroy(String word) {
        if(root == null) {                                          // Empty Tree
            System.out.println("> Empty Tree <");

        } else if(word.equalsIgnoreCase(root.getWord())) {          // Word Found, Destroy Node

            if(root.left == null && root.right == null) {           // Case '1' Root Node has no Children
                root = null;

            } else if(root.left != null && root.right != null) {    // Case '2' Root Node has 2 Children

                Node temp = getHighestNode(root, word);             // Obtain the next highest node

                temp.left = root.left;                              // Set Leaf Node Left Tree to Root Node Left Tree
                temp.right = root.right;                            // Set Leaf Node Right Tree to Root Node Right Tree

                destroy(temp.getWord());                            // Delete the Old Highest Leaf Node (Which is Now the New Root Node of this Tree)
                root = temp;                                        // Change Root

                System.out.println("> Word is Removed <");

            } else {                                                // Case '3' Root Node has 1 Child
                if(root.left != null) {                             // Case '3.1' Left Sub Tree is the Only Child
                    root = root.left;

                } else {                                            // Case '3.2' Right Sub Tree is the Only Child
                    root = root.right;    

                }   
            }      
        } else if(word.compareToIgnoreCase(root.getWord()) < 0) {   // Search & Destroy Word in Left Sub Tree
            root.left = destroy(root.left, word);

        } else {                                                    // Search & Destroy Word in Right Sub Tree
            root.right = destroy(root.right, word);

        }
    }

    // Sub Tree
    private Node destroy(Node tree, String word) {
        if(tree == null) {                                          // Empty Tree
            System.out.println("> The Word (" + word + ") is Not in This Tree <");

        } else if(word.equalsIgnoreCase(tree.getWord())) {          // Word Found, Destroy Node

            if(tree.left == null && tree.right == null) {           // Case '1' Sub Root Node has no Children
                tree = null;

            } else if(tree.left != null && tree.right != null) {    // Case '2' Sub Root Node has 2 Children

                Node temp = getHighestNode(tree, word);             // Obtain the next highest node

                temp.left = tree.left;                              // Set Leaf Node Left Tree to Root Node Left Tree
                temp.right = tree.right;                            // Set Leaf Node Right Tree to Root Node Right Tree

                destroy(temp.getWord());                            // Delete the Old Highest Leaf Node (Which is Now the New Root Node of this Tree)

                tree = temp;                                        // Change Sub Root

            } else {                                                // Case '3' Root Node has 1 Child
                if(tree.left != null) {                             // Case '3.1' Left Sub Tree is the Only Child
                    tree = tree.left;                               
                } else {                                            // Case '3.2' Right Sub Tree is the Only Child
                    tree = tree.right;                              
                }   
            }      
        } else if(word.compareToIgnoreCase(tree.getWord()) < 0) {   // Search & Destroy Word in Left Sub Tree
            tree.left = destroy(tree.left, word);

        } else {                                                    // Search & Destroy Word in Right Sub Tree
            tree.right = destroy(tree.right, word);

        }

        return tree;
    }


    private Node getHighestNode(Node tree, String word) {

        if(tree.left == null && tree.right == null) {                                       // Leaf Node
            Node temp = new Node(tree.getWord());

            for(int i = 1; i < tree.getCount(); i++) {
                temp.addCount();
                System.out.println("sup");
            }

            return temp;

        } else if(tree.left != null && word.compareToIgnoreCase(tree.left.getWord()) < 0) { // Left Node is Bigger
            return getHighestNode(tree.left, word); 

        } else {
            return getHighestNode(tree.right, word);        

        } 
    }   
}