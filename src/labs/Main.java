package labs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import labs.lab01.GraphInterface;
import labs.lab01.ListGraph;
import labs.lab01.MatrixGraph;
import labs.lab02.WarshalFloydAlgorithm;
import labs.lab03.FordFulkerson;
import labs.lab04.HuffmanAlgorithm;
import labs.lab04.SaveFileHelper;
import labs.lab05.MatricesMultiplicationAlgorithm;
import labs.lab05.Matrix;
import labs.lab05.ReadFileHelper;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{

    chooseAlgorithm();
	}

    private static void chooseAlgorithm() {

        System.out.println("Wybierz jedną z opcji: ");
        System.out.println("1 - Algorytm Warshala-Floyda");
        System.out.println("2 - Algorytm Forda-Fulkersona");
        System.out.println("3 - Algorytm Huffmana");
        System.out.println("4 - Mnożenie macierzy");
        System.out.print("Wybór: ");

        Scanner in = new Scanner(System.in);
        String choose =  in.nextLine();

        System.out.println("Czekaj ...");
        switch (choose) {
            case "1":
                /* Warshal-Floyd */
                warshalFloydRun("src/grafWarshalaFloyda.txt");
		        /* end Warshal-Floyd */
                break;
            case "2":
                /* Ford-Fulkerson */
                fordFulkersonRun("src/duzy_graf.txt");
		        /* end Ford-Fulkerson */
                break;
            case "3":
                /* Huffman */

                String text = "";
                try {
                    text = new Scanner(new File("src/seneca.txt")).useDelimiter("\\A").next();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                huffman(text, "src/compressed.dat", "src/decompressed.txt");
		        /* end Huffman */
                break;
            case "4":
                /* Matrix Multiplication */
                matrixMultiplicationRun();
                /* end Matrix Multiplication */
                break;


            default:
                System.out.println("Dokonano złego wyboru!");
                chooseAlgorithm();
                break;
        }
    }

    private static void matrixMultiplicationRun() {
        System.out.println("Trwa implementacja");

        System.out.println("Podaj liczbę macierzy od załadowania");
        Scanner in = new Scanner(System.in);
        int number =  in.nextInt();

        ArrayList<Matrix> matrices = ReadFileHelper.readMatrices("src/sample-matrices.txt", number);

        MatricesMultiplicationAlgorithm matricesMultiplicationAlgorithm = new MatricesMultiplicationAlgorithm(matrices);




    }

    private static void huffman(String sourceTextContent, String compressionFile, String decompressionFile) {

        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm(sourceTextContent);

        try {
            huffmanAlgorithm.compress();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String compressedString = huffmanAlgorithm.getCompressedString();
		System.out.println("Kompresja:\n" + compressedString);
		try {
            SaveFileHelper.save(compressionFile, huffmanAlgorithm.getCompressedString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		int l_origin = sourceTextContent.length()*8;
		int l_compressed = compressedString.length();
		System.out.print("Stopień kompresji: " + (float)(l_origin - l_compressed) / l_origin);

		huffmanAlgorithm.decompress();

        try {
            SaveFileHelper.save(decompressionFile, huffmanAlgorithm.getDecompressedString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static void warshalFloyd(GraphInterface g, GraphInterface g2) {
		int startNode = 1;
		int endNode = 20;
		
		WarshalFloydAlgorithm fW = new WarshalFloydAlgorithm();
		WarshalFloydAlgorithm fW2 = new WarshalFloydAlgorithm();
		
		
		long startTimeMatrix = System.currentTimeMillis();
		int[][] D = fW.warshalFloyd(g);
		LinkedList<Integer> p = fW.getPath(startNode, endNode);
		long endTimeMatrix = System.currentTimeMillis();
		
		
		long startTimeList = System.currentTimeMillis();
		int[][] D2 = fW2.warshalFloyd(g2);
		LinkedList<Integer> p2 = fW2.getPath(startNode, endNode);
		long endTimeList = System.currentTimeMillis();
		
		long timeMatrix = endTimeMatrix - startTimeMatrix;
		long timeList = endTimeList - startTimeList;
		
		g.printGraph();
		
		System.out.println("---MACIERZ---");
		System.out.println("czas: " + timeMatrix + "ms");
		System.out.println("odległość: " + D[startNode][endNode]);
		System.out.println("ścieżka: " + p);
		
		System.out.println("----LISTA----");
		System.out.println("czas: " + timeList + "ms");
		System.out.println("odległość: " + D2[startNode][endNode]);
		System.out.println("ścieżka: " + p2);
		
		System.out.println("R = Tlista / Tmacierz = " + (float) timeList/timeMatrix);
	}

	private static void warshalFloydRun(String path) {

        GraphInterface g = null;
        GraphInterface g2 = null;
        try {
            g = new MatrixGraph(path, 1000, 10000);
            g2 = new ListGraph(path, 1000, 10000);

        } catch (IOException e) {
            e.printStackTrace();
        }

        warshalFloyd(g, g2);
    }

	private static void fordFulkerson(GraphInterface g, int s, int t) {
		FordFulkerson ff = new FordFulkerson();
		g.printGraph();
		System.out.println("Maxymalny przepływ: " + ff.resolve(g, s, t));
	}

	private static void fordFulkersonRun(String path) {

        GraphInterface flow = null;

        try {
            flow = new MatrixGraph(path, 1000, 10000);
        }catch (IOException e) {
            e.printStackTrace();
        }

        int s = 109;
        int t = 609;

        fordFulkerson(flow, s, t);
    }
}