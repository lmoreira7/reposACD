package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    
    public static List<Integer> BFS (Grafos grafo, int verticeOrigem) {

        boolean[] visitados = new boolean[grafo.numVertices()];

        Queue<Integer> filaVertices = new LinkedList<>();
        List<Integer> ordemVisita = new ArrayList<>();

        visitados[verticeOrigem] = true;
        filaVertices.add(verticeOrigem);

        while (!filaVertices.isEmpty()) {

            int verticeRemovido = filaVertices.poll();
            ordemVisita.add(verticeRemovido);

            for(Aresta aresta : grafo.getAdjacencias(verticeRemovido)) {

                int v = aresta.getVerticeDestino();

                if(!visitados[v]) {
                    visitados[v] = true;
                    filaVertices.add(v);
                }
            }
        }

        return ordemVisita;
    }
}
