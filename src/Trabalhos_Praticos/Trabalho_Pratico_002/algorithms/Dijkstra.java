package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.algorithms.assistant.VerticeDistancia;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    public static double[] menorCaminho(Grafos grafo, int verticeInicial) {

        double[] distancias = new double[grafo.numVertices()];
        int[] predecessores = new int[grafo.numVertices()];
        final double infinito = Double.MAX_VALUE;

        Arrays.fill(distancias, infinito);
        Arrays.fill(predecessores, -1);

        distancias[verticeInicial] = 0;

        PriorityQueue<VerticeDistancia> queue = new PriorityQueue<>();
        queue.add(new VerticeDistancia(verticeInicial, 0.0));

        while(!queue.isEmpty()) {

            VerticeDistancia uDist = queue.poll();
            int u = uDist.getVerticeID();
            double distanciaU = uDist.getDistancia();

            if(distanciaU > distancias[u]) {
                continue;
            }

            for(Aresta aresta : grafo.getAdjacencias(u)) {

                int v = aresta.getVerticeDestino();
                double peso = aresta.getPeso();

                double novaDistancia = distancias[u] + peso;

                if(novaDistancia < distancias[v]) {

                    distancias[v] = novaDistancia;
                    predecessores[v] = u;

                    queue.add(new VerticeDistancia(v, novaDistancia));

                }
            }
        }

        return distancias;
    }
}
