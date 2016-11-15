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

    private static PriorityQueue<Node> q;
    private static HashMap<Character, String> charToCode;
    private static HashMap<String, Character> codeToChar;


    public static void runAlgorithm(String text) throws FileNotFoundException {

        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            if (dict.containsKey(a))
                dict.put(a, dict.get(a) + 1);
            else
                dict.put(a, 1);
        }


        q = new PriorityQueue<>(100, new FrequencyComparator());
        int n = 0;
        for (Character c : dict.keySet()) {
            q.add(new Node(c, dict.get(c)));
            n++;
        }

        Node root = createTree(n);
        createAlgorithmTable(root);

    }

    private static Node createTree(int n) {
        Node x, y;
        for (int i = 1; i <= n - 1; i++) {
            Node z = new Node();
            z.left = x = q.poll();
            z.right = y = q.poll();
            z.freq = x.freq + y.freq;
            q.add(z);
        }
        return q.poll();
    }


    private static void createAlgorithmTable(Node root) {
        charToCode = new HashMap<>();
        codeToChar = new HashMap<>();
        postorder(root, "");
    }

    // This method is used to traverse from ROOT-to-LEAVES
    private static void postorder(Node n, String s) {
        if (n == null) {
            return;
        }
        postorder(n.left, s + "0");
        postorder(n.right, s + "1");


        if (!Character.toString(n.ch).equals("&#092;&#048;")) {
            //  System.out.println("{" + n.ch + ":" + s + "}");
            charToCode.put(n.ch, s);
            codeToChar.put(s, n.ch);
        }
    }


    public static String compress(String s) {
        String c = "";
        for (int i = 0; i < s.length(); i++)
            c = c + charToCode.get(s.charAt(i));
        return c;
    }


    public static String decompress(String s) {
        String temp = "";
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);
            Character c = codeToChar.get(temp);
            if (c != null && c != 0) {
                result = result + c;
                temp = "";
            }
        }
        return result;
    }

    public static void saveResult(String result, String fileName) throws IOException {

        PrintWriter oFile = new PrintWriter(fileName);

        oFile.println(result);
        oFile.close();
    }
}
