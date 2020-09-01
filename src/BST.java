// package-private class and methods
class Node {
    private Node left;
    private Node right;
    private final String word;
    private int count;

    Node(String word) {
        this.word = word;
        count = 1;
    }

    int getCount() {
        return count;
    }

    String getWord() {
        return word;
    }

    void addCount() {
        count++;
    }
}

public class BST {
    private Node root;

    public BST() {

    }

    public boolean search(String word) {
        return false;
    }

    public void insert(String word) {

    }

    public void inOrder() {
        Node node = new Node("Hello");      // placeholder

        // traverse left

        System.out.printf("%30s\t%s", node.getWord(), node.getCount());

        // traverse right
    }

    public void destroy() {

    }
}
