package Trabalhos_Praticos.Trabalho_Pratico_002.classes;

import java.util.List;

public interface Grafos {
    
    int numArestas();
    int numVertices();
    boolean ponderado();
    boolean ordenado();
    void addAresta(int verticeOrigem, int verticeDestino); // Construtor para grafos não ponderados
    void addAresta(int verticeOrigem, int verticeDestino, double peso); // Construtor para grafos ponderados

    List<Aresta> getAdjacencias(int vertice);// Retorna as adjacencias de um vertice

}
