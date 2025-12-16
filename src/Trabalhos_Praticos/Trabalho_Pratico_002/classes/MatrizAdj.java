package Trabalhos_Praticos.Trabalho_Pratico_002.classes;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;

import java.util.ArrayList;
import java.util.List;

public class MatrizAdj implements Grafos {
    
    private final int vertices;
    private final boolean ordenado;
    private final boolean ponderado;
    private int quantidadeArestas;
    private final double[][] matrizAdjacencias;

    public MatrizAdj(int vertices, boolean ponderado, boolean ordenado) {
        
        this.vertices = vertices;
        this.ponderado = ponderado;
        this.ordenado = ordenado;

        matrizAdjacencias = new double[vertices][vertices];

        for(int i = 0; i < vertices; i++) {

            for(int j = 0; j < vertices; j++) {

                matrizAdjacencias[i][j] = 0;
            }
        }

    }

    public void addAresta(int verticeOrigem, int verticeDestino, double peso) {
        
        matrizAdjacencias[verticeOrigem][verticeDestino] = peso;
        quantidadeArestas++;

        if(!ordenado) {
            matrizAdjacencias[verticeDestino][verticeOrigem] = peso;
        }

    }

    public void addAresta(int verticeOrigem, int verticeDestino) {
        
        matrizAdjacencias[verticeOrigem][verticeDestino] = 1;
        quantidadeArestas++;

        if(!ordenado) {
            matrizAdjacencias[verticeDestino][verticeOrigem] = 1;
        }

    }

    public List<Aresta> getAdjacencias(int vertice) {

        List<Aresta> adj = new ArrayList<>();

        for(int i = 0; i < vertices; i++) {

            if(matrizAdjacencias[vertice][i] != 0) {

                if(ponderado) {

                    adj.add(new Aresta(vertice, i, matrizAdjacencias[vertice][i]));

                } else {

                    adj.add(new Aresta(vertice, i, 1));

                }
            }
        }

        return adj;
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
