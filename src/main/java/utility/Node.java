package utility;

class Node {

    private boolean[] a = new boolean[26];
    private Node[] c = new Node[26];
    boolean isPresent;

    int words;
    Node() {
        for(int i=0;i<26;i++) {
            a[i] = false;
            c[i] = null;
            words = 0;
        }
    }

    boolean edgeExists(char x) {
        return a[x-97];
    }

    Node getNode(char x) {
        return c[x-97];
    }

    void addChar(char x) {
        a[x-97] = true;
        c[x-97] = new Node();
    }


}
