package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms.assistant;

public class VerticeDistancia implements Comparable<VerticeDistancia> {

    private int verticeID;
    private double distancia;

    public VerticeDistancia(int verticeID, double distancia) {

        this.verticeID = verticeID;
        this.distancia = distancia;

    }

    @Override
    public int compareTo(VerticeDistancia other) {
        return Double.compare(this.distancia, other.distancia);
    }

    public int getVerticeID() {
        return verticeID;
    }

    public double getDistancia() {
        return distancia;
    }
}
