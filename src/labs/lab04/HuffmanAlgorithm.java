package labs.lab04;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char a, int f) {
        ch = a;
        freq = f;
    }
    Node() {
    }
    public String toString() {
        return ch + " " + freq;
    }
}
class FrequencyComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        int freqA = a.freq;
        int freqB = b.freq;
        return freqA-freqB;
    }
}

public class HuffmanAlgorithm {

    private PriorityQueue<Node> q;
    private HashMap<Character, String> charToCode;
    private HashMap<String, Character> codeToChar;


    private String sourceText;
    private String compressedString;
    private String decompressedString;

    public HuffmanAlgorithm(String sourceText) {
        this.sourceText = sourceText;
    }

    private void runAlgorithm() throws FileNotFoundException {

        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < this.sourceText.length(); i++) {
            char a = this.sourceText.charAt(i);
            if (dict.containsKey(a))
                dict.put(a, dict.get(a) + 1);
            else
                dict.put(a, 1);
        }


        this.q = new PriorityQueue<>(100, new FrequencyComparator());
        int n = 0;
        for (Character c : dict.keySet()) {
            this.q.add(new Node(c, dict.get(c)));
            n++;
        }

        Node root = createTree(n);
        createAlgorithmTable(root);

    }

    private Node createTree(int n) {
        Node x, y;
        for (int i = 1; i <= n - 1; i++) {
            Node z = new Node();
            z.left = x = this.q.poll();
            z.right = y = this.q.poll();
            z.freq = x.freq + y.freq;
            this.q.add(z);
        }
        return this.q.poll();
    }


    private void createAlgorithmTable(Node root) {
        this.charToCode = new HashMap<>();
        this.codeToChar = new HashMap<>();
        postorder(root, "");
    }

    // This method is used to traverse from ROOT-to-LEAVES
    private void postorder(Node n, String s) {
        if (n == null) {
            return;
        }
        postorder(n.left, s + "0");
        postorder(n.right, s + "1");


        if (!Character.toString(n.ch).equals("&#092;&#048;")) {
            //  System.out.println("{" + n.ch + ":" + s + "}");
            this.charToCode.put(n.ch, s);
            this.codeToChar.put(s, n.ch);
        }
    }


    public HuffmanAlgorithm compress() throws FileNotFoundException {

        if(this.sourceText == null) {
            System.out.println("Brak danych do kompresji");
            return this;
        }
        this.runAlgorithm();

        String c = "";
        for (int i = 0; i < this.sourceText.length(); i++) {
            c += charToCode.get(this.sourceText.charAt(i));
        }
        this.compressedString = c;
        return this;
    }


    public HuffmanAlgorithm decompress() {
        String temp = "";
        String result = "";
        if(this.compressedString == null) {
            System.out.println("Brak danych do dekompresji");
            return this;
        }
        for (int i = 0; i < this.compressedString.length(); i++) {
            temp = temp + this.compressedString.charAt(i);
            Character c = codeToChar.get(temp);
            if (c != null && c != 0) {
                result = result + c;
                temp = "";
            }
        }
        this.decompressedString = result;
        return this;
    }

    public String getCompressedString() {
        return compressedString;
    }

    public HuffmanAlgorithm setCompressedString(String compressedString) {
        this.compressedString = compressedString;
        return this;
    }

    public String getDecompressedString() {
        return decompressedString;
    }

    public HuffmanAlgorithm setDecompressedString(String decompressedString) {
        this.decompressedString = decompressedString;
        return this;
    }

    public HuffmanAlgorithm setSourceText(String sourceText) {
        this.sourceText = sourceText;
        return this;
    }
}
