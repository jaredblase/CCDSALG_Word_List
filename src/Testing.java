public class Testing {
	public static void main(String[] args) {
		

		BST st = new BST();

		st.insert("banana");
		st.insert("banana");

		st.insert("apple");
		st.insert("apple");
		st.insert("apple");
		st.insert("apple");

		st.insert("peach");
		st.insert("peach");
		st.insert("peach");
		st.insert("peach");
		st.insert("peach");

		st.insert("sausage");
		st.insert("sausage");
		st.insert("sausage");

		st.insert("watermelon");
		st.insert("watermelon");
		st.insert("watermelon");
		st.insert("watermelon");

		st.insert("cheesecake");
		st.insert("Durian");


		st.inOrder();
		System.out.println();
		st.getSuccessor(new BST.Node("Peach")).printNode();

		st.destroy("banana");
		System.out.println();
		System.out.println();

		st.inOrder();
	}
}