package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;


import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    private static double[][] matrizCapacidade(Grafos grafo) {

        int numVertices = grafo.numVertices();
        double[][] capacidade = new double[numVertices][numVertices];

        for(int u = 0; u < numVertices; u++) {

            for(Aresta aresta : grafo.getAdjacencias(u)) {

                int verticeDestino = aresta.getVerticeDestino();

                capacidade[u][verticeDestino] = aresta.getPeso();

            }
        }
        return capacidade;
    }

    private static double dfs(int u, int t, int[] parent, double[][] capacidade) {
        return dfsRecursiva(u, t, parent, capacidade, Double.MAX_VALUE);
    }

    private static double dfsRecursiva(int u, int t, int[] parent, double[][] capacidade, double fluxo) {

        if(u == t) {
            return fluxo;
        }

        for(int v = 0; v < capacidade.length; v++) {

            if(parent[v] == -2 && capacidade[u][v] > 0) {

                parent[v] = u;
                double newFluxo = dfsRecursiva(v, t, parent, capacidade, Math.min(fluxo, capacidade[u][v]));

                if(newFluxo > 0) {
                    return newFluxo;
                }

            }

        }

        return 0;

    }

    public static double maxFluxo(Grafos grafo, int s, int t) {

        int numVertices = grafo.numVertices();
        double[][] capacidade = matrizCapacidade(grafo);

        int[] parent = new int[numVertices];

        double fluxoMax = 0.0;
        double fluxoCaminho;

        do {

            Arrays.fill(parent, -2);
            parent[s] = -1;

            fluxoCaminho = buscaCaminhoAumentador(s, t, parent, capacidade);

            if(fluxoCaminho > 0) {

                for(int v = t; v != s; v = parent[v]) {

                    int u = parent[v];
                    capacidade[u][v] -= fluxoCaminho;
                    capacidade[v][u] += fluxoCaminho;

                }

                fluxoMax += fluxoCaminho;

            }


        } while (fluxoCaminho > 0);

        return fluxoMax;
    }

    private static double buscaCaminhoAumentador(int s, int t, int[] parent, double[][] capacidade) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        parent[s] = s;

        double[] caminhoFluxo = new double[capacidade.length];
        caminhoFluxo[s] = Double.MAX_VALUE;

        while(!queue.isEmpty()) {

            int u = queue.poll();

            for(int v = 0; v < capacidade.length; v++) {

                if(parent[v] == -2 && capacidade[u][v] > 0) {
                    parent[v] = u;
                    caminhoFluxo[v] = Math.min(caminhoFluxo[u], capacidade[u][v]);

                    if(v == t) {
                        return caminhoFluxo[t];
                    }

                    queue.offer(v);
                }

            }

        }

        return 0.0;

    }

}
