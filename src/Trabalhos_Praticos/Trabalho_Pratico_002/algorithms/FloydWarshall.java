package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;


import java.util.Arrays;

public class FloydWarshall {

    private static final double INF = Double.MAX_VALUE / 2;

    private static double[][] inicializaMatrizDist(Grafos grafo) {
        int numVertices = grafo.numVertices();
        double dist[][] = new double[numVertices][numVertices];

        for(int i = 0; i < numVertices; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0.0;
        }

        for(int u = 0; u < numVertices; u++) {

            for(Aresta aresta : grafo.getAdjacencias(u)) {

                int v = aresta.getVerticeDestino();
                dist[u][v] = aresta.getPeso();

            }
        }

        return dist;
    }

    public static double[][] floydWarshall(Grafos grafo) {

        int numVertices = grafo.numVertices();
        double[][] dist = inicializaMatrizDist(grafo);

        for(int k = 0; k < numVertices; k++) {
            for(int i = 0; i < numVertices; i++) {
                for(int j = 0; j < numVertices; j++) {

                    if(dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for(int i = 0; i < numVertices; i++) {

            if(dist[i][i] < 0) {
                System.out.println("Grafo contém ciclo de peso negativo!");
            }
        }

        return dist;
    }
}
