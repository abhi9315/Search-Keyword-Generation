package utility;


public class Trie {

    private Node root;
    public Trie() {
        root = new Node();
    }

    public void add(String word) {
        int len = word.length();
        Node temp = root;
        for(int i = 0; i < len; i++) {
            char x = word.charAt(i);
            if(!temp.edgeExists(x)) {
                temp.addChar(x);
                temp.words++;
            }
            temp = temp.getNode(x);
        }
        temp.isPresent = true;
    }

    public boolean contains(String word) {
        int len = word.length();
        Node temp = root;
        for (int i=0;i<len;i++) {
            char x = word.charAt(i);
            if(!temp.edgeExists(x))
                return false;
            temp = temp.getNode(x);
        }
        return temp.isPresent;
    }



    public void display() {
        traverse(root);
    }

    private void traverse(Node node) {
        for (int i=0;i<26;i++) {
            if (node.edgeExists((char)(97+i))) {
                System.out.print((char) (97 + i));
                traverse(node.getNode((char)(97+i)));
            }
        }
    }

}
