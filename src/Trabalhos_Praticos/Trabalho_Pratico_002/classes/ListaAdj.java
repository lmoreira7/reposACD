package Trabalhos_Praticos.Trabalho_Pratico_002.classes;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class ListaAdj implements Grafos {

    private final int vertices;
    private final boolean ordenado;
    private final boolean ponderado;
    private int quantidadeArestas;
    private final List<List<Aresta>> adjacencias;

    public ListaAdj(int vertices, boolean ordenado, boolean ponderado) {

        this.vertices = vertices;
        this.ordenado = ordenado;
        this.ponderado = ponderado;

        adjacencias = new ArrayList<>(); // Cria lista de vertices

        for(int i = 0; i < vertices; i++) {

            adjacencias.add(new ArrayList<>()); // Cria lista de adjacencias para cada vertice
        }        

    }

    public void addAresta(int verticeOrigem, int verticeDestino, double peso) {

        adjacencias.get(verticeOrigem).add(new Aresta(verticeOrigem, verticeDestino, peso));
        quantidadeArestas++;

        if(!ordenado) {
            adjacencias.get(verticeDestino).add(new Aresta(verticeDestino, verticeOrigem, peso));
        }

    }

    public void addAresta(int verticeOrigem, int verticeDestino) {

        adjacencias.get(verticeOrigem).add(new Aresta(verticeOrigem, verticeDestino));
        quantidadeArestas++;

        if(!ordenado) {
            adjacencias.get(verticeDestino).add(new Aresta(verticeDestino, verticeOrigem));
        }

    }

    public List<Aresta> getAdjacencias(int vertice) {
        return adjacencias.get(vertice);
    }

    public int numVertices() {
        return vertices;
    }

    public int numArestas() {
        return quantidadeArestas;
    }

    public boolean ordenado() {
        return ordenado;
    }

    public boolean ponderado() {
        return ponderado;
    }
}
