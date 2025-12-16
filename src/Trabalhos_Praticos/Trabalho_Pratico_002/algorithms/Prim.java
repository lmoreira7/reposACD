package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim {

    public static double primMST(Grafos grafo) {
        int V = grafo.numVertices();
        double pesoTotal = 0.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        boolean[] inMST = new boolean[V];
        double[] key = new double[V];
        Arrays.fill(key, Double.MAX_VALUE);

        key[0] = 0.0;
        pq.offer(new double[]{0.0, 0});

        while (!pq.isEmpty()) {
            double[] atual = pq.poll();
            double pesoAtual = atual[0];
            int u = (int) atual[1];

            if (inMST[u]) continue;

            inMST[u] = true;
            pesoTotal += pesoAtual;

            for (Aresta aresta : grafo.getAdjacencias(u)) {
                int v = aresta.getVerticeDestino();
                double weight = aresta.getPeso();

                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.offer(new double[]{key[v], v});
                }
            }
        }
        return pesoTotal;
    }
}