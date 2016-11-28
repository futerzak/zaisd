package labs;

import labs.lab01.*;
import labs.lab02.WarshalFloydAlgorithm;
import labs.lab03.Edge;
import labs.lab03.FordFulkerson;
import labs.lab03.Vertex;
import labs.lab04.HuffmanAlgorithm;
import labs.lab04.SaveFileHelper;
import labs.lab05.Matrix;
import labs.lab05.ParallelMatricesMultiplicationAlgorithm;
import labs.lab05.ReadFileHelper;
import labs.lab05.SequentialMatricesMultiplicationAlgorithm;
import labs.lab06.ComputePi;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;


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
        System.out.println("5 - Obliczanie PI");
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
                try {
                    matrixMultiplicationRun();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /* end Matrix Multiplication */
                break;
            case "5":
                /* PI Computing */
                try {
                    computingPiRun();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /* end PI Computing */
                break;


            default:
                System.out.println("Dokonano złego wyboru!");
                chooseAlgorithm();
                break;
        }
    }

    private static void computingPiRun() throws Exception {

        int[] n = new int[4];
        for(int i=0;i<4;i++) {
            n[i] = (int) Math.pow(10,i+5);
        }

        for(int i=0;i<n.length;i++) {
            computePi(n[i], true);
            computePi(n[i], false);
            System.out.println();
        }
        System.out.println("------------------------");


    }

    private static void computePi(int n, boolean parallel) throws Exception {

        Double result = 0.0;
        Long startTime, endTime;

        startTime = System.currentTimeMillis();

        if(parallel) {

            int procesorsNumber = Runtime.getRuntime().availableProcessors();

            ExecutorService pool = Executors.newFixedThreadPool(procesorsNumber);
            List<Future<Double>> list = new ArrayList<>();

            int range = (int) Math.floor(n/procesorsNumber);

            for(int i=0;i<n;i+=range) {
                Future<Double> future = pool.submit(new ComputePi(i,i+range, n));
                list.add(future);
            }

            for(Future<Double> future: list) {
                result += future.get();
            }

        } else {
            for(int i=0;i<=n;i++){
                result += 4.0 / (1.0 + Math.pow( ( (2.0*i) + 1.0) / (2.0 * n), 2));
            }
        }

        endTime = System.currentTimeMillis();

        String type = parallel?"równoległego":"sekwencyjnego";
        System.out.println("Czas obliczenia " + type + " dla n="+ n +" wynosi: " + (endTime-startTime) + "ms");
        System.out.println("Wynik: " + result.toString());

    }

    private static void matrixMultiplicationRun() throws ExecutionException, InterruptedException {
        System.out.println("Trwa implementacja");

        System.out.println("Podaj liczbę macierzy do załadowania");
        Scanner in = new Scanner(System.in);
        int number =  in.nextInt();
        in.close();

        List<Matrix> matrices = ReadFileHelper.readMatrices("src/sample-matrices.txt", number);

        long startSexuence = System.currentTimeMillis();

        SequentialMatricesMultiplicationAlgorithm sequentialMatricesMultiplicationAlgorithm = new SequentialMatricesMultiplicationAlgorithm(matrices);
        sequentialMatricesMultiplicationAlgorithm.run().show();

        long endSequence = System.currentTimeMillis();
        long resultSequence = endSequence - startSexuence;
        System.out.println("Czas wykonywania sekwencyjnego: " + resultSequence + "ms");


        int matricesNumber = matrices.size();
        int processorsNumber = Runtime.getRuntime().availableProcessors();
        int threadNumber = (int) Math.floor(matricesNumber/processorsNumber);


        ExecutorService pool = Executors.newFixedThreadPool(processorsNumber);

        List<Future<Matrix>> resultMatrixList = new ArrayList<>();
        List<Matrix> matricesStack = new Stack<>();

        long startParralel = System.currentTimeMillis();

        for(int i=0;i<matricesNumber;i+=threadNumber){
            if(i+threadNumber >= matricesNumber && matricesNumber%processorsNumber != 0) {
                threadNumber = matricesNumber%processorsNumber;
            }
            Future<Matrix> resolvedMatrix = pool.submit(new ParallelMatricesMultiplicationAlgorithm(matrices, i,i+threadNumber));

            resolvedMatrix.get();
            resultMatrixList.add(resolvedMatrix);

            for(Future<Matrix> resultMatrix : resultMatrixList) {
                matricesStack.add(resultMatrix.get());
            }

            while (matricesStack.size() !=1) {
                Matrix temp = matricesStack.remove(0).times(matricesStack.remove(0));
                matricesStack.add(temp);
            }
        }


        long endParallel = System.currentTimeMillis();
        long resultParallel = endParallel - startParralel;

        System.out.println("Czas wykonywania równoległego: " + resultParallel + "ms");
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

    private static void fordFulkersonRun(String path) {

        GraphfInterface  matrixGraph = null, listGraph = null;

        matrixGraph = new MatrixfGraph();

        File graphFile = new File("src/duzy_graf.txt");

        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(graphFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        int i = 1;
        try {
            for (line = buffer.readLine(); line != null; line = buffer.readLine()) {
                String[] t = line.split(";");

                Vertex initialVertex = new Vertex().setVertexId(Integer.parseInt(t[0].trim()));
                Vertex finalVertex = new Vertex().setVertexId(Integer.parseInt(t[1].trim()));
                Edge edge = new Edge().setEdgeId(i)
                        .setEdgeWeight(Integer.parseInt(t[2].trim()));

                matrixGraph.addVertex(initialVertex);
                matrixGraph.addVertex(finalVertex);
                matrixGraph.addEdge(initialVertex, finalVertex, edge);
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FordFulkerson fordFulkerson = new FordFulkerson(matrixGraph.getVertexCount());
        int maxFlow = fordFulkerson.fordFulkerson(matrixGraph, 109, 609);
        System.out.println("Max flow 109 -> 609: " + maxFlow);
    }
}