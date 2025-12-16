package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.*;

public class BellmanFord {

    private static List<Aresta> getAllArestas(Grafos grafo) {
        List<Aresta> allArestas = new ArrayList<>();
        int V = grafo.numVertices();
        for (int i = 0; i < V; i++) {
            for (Aresta aresta : grafo.getAdjacencias(i)) {
                if (grafo.ordenado() || i <= aresta.getVerticeDestino()) {
                    allArestas.add(aresta);
                }
            }
        }
        return allArestas;
    }

    public static double[] bellmanFord(Grafos grafo, int verticeOrigem) {
        int V = grafo.numVertices();
        List<Aresta> edges = getAllArestas(grafo);

        double[] dist = new double[V];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[verticeOrigem] = 0.0;

        for (int i = 1; i < V; ++i) {
            for (Aresta edge : edges) {
                int u = edge.getVerticeOrigem();
                int v = edge.getVerticeDestino();
                double weight = edge.getPeso();

                if (dist[u] != Double.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        for (Aresta aresta : edges) {
            int u = aresta.getVerticeOrigem();
            int v = aresta.getVerticeDestino();
            double weight = aresta.getPeso();

            if (dist[u] != Double.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("O Grafo contém ciclo de peso negativo!");
                return null; // Indica falha
            }
        }

        return dist;
    }
}

