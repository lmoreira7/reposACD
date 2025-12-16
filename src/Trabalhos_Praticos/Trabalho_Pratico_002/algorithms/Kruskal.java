package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    static class DSU {
        int[] parent;
        DSU(int V) {
            parent = new int[V];
            for (int i = 0; i < V; i++) parent[i] = i;
        }
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) parent[rootI] = rootJ;
        }
    }

    private static List<Aresta> getAllArestasForKruskal(Grafos grafo) {
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

    public static double kruskalMST(Grafos grafo) {
        int V = grafo.numVertices();
        List<Aresta> edges = getAllArestasForKruskal(grafo);

        edges.sort(Comparator.comparingDouble(Aresta::getPeso));

        DSU dsu = new DSU(V);
        double totalWeight = 0.0;
        int countAresta = 0;

        for (Aresta edge : edges) {
            int u = edge.getVerticeOrigem();
            int v = edge.getVerticeDestino();

            if (dsu.find(u) != dsu.find(v)) {
                dsu.union(u, v);
                totalWeight += edge.getPeso();
                countAresta++;

                if (countAresta == V - 1) break;
            }
        }
        return totalWeight;
    }
}
