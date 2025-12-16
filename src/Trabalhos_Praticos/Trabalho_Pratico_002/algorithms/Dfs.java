package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class Dfs {

    private static List<Integer> ordemVisita;
    private static boolean[] visitados;

    public static List<Integer> DFS(Grafos grafo, int verticeOrigem) {

        visitados = new boolean[grafo.numVertices()];
        ordemVisita = new ArrayList<>();

        dfsRecursiva(grafo, verticeOrigem);

        return ordemVisita;
    }

    private static void dfsRecursiva (Grafos grafo, int u) {

        visitados[u] = true;
        ordemVisita.add(u);

        for(Aresta aresta : grafo.getAdjacencias(u)) {

            int v = aresta.getVerticeDestino();

            if(!visitados[v]) {
                dfsRecursiva(grafo, v);
            }
        }
    }
}
