public class Node {
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
}