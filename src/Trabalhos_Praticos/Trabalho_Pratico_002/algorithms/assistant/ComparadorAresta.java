package Trabalhos_Praticos.Trabalho_Pratico_002.algorithms.assistant;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Aresta;

import java.util.Comparator;

public class ComparadorAresta implements Comparator<Aresta> {

    @Override
    public int compare(Aresta a1, Aresta a2) {

        double peso1 = a1.getPeso();
        double peso2 = a2.getPeso();

        if(peso1 < peso2) {
            return -1;
        }
        else if(peso1 > peso2) {
            return 1;
        } else {
            return 0;
        }
    }
}
