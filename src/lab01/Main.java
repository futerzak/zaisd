package lab01;

import java.io.IOException;
import java.util.LinkedList;
import lab02.WarshalFloydAlgorithm;
import lab3.FordFulkerson;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		String path = "src/grafWarshalaFloyda.txt";
		GraphInterface g = null;
		GraphInterface g2 = null;
		try {
			g = new MatrixGraph(path, 1000, 10000);
			g2 = new ListGraph(path, 1000, 10000);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		warshalFloyd(g, g2);
		
		path = "src/duzy_graf.txt";
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
	
	
	
	public static void warshalFloyd(GraphInterface g, GraphInterface g2) {
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

	public static void fordFulkerson(GraphInterface g, int s, int t) {
		FordFulkerson ff = new FordFulkerson();
		g.printGraph();
		System.out.println("Maxymalny przepływ: " + ff.resolve(g, s, t));
	}
}