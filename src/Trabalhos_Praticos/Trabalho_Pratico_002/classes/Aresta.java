package Trabalhos_Praticos.Trabalho_Pratico_002.classes;

public class Aresta {
    
    private final int verticeOrigem;
    private final int verticeDestino;
    private double peso;

    public Aresta(int verticeOrigem, int verticeDestino, double peso) {
        
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        this.peso = peso;

    }

    public Aresta(int verticeOrigem, int verticeDestino) {
        
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        
    }

    public int getVerticeDestino() {
        return verticeDestino;
    }

    public int getVerticeOrigem() {
        return verticeOrigem;
    }

    public double getPeso() {
        return peso;
    }
}
