package Trabalhos_Praticos.Trabalho_Pratico_003.classes;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int grau;
    private List<Integer> Id;
    private List<Items> dados;
    private List<Node> filhos;
    private boolean isFolha;

    public Node(int grau, boolean folha) {

        this.grau = grau;
        this.isFolha = folha;
        this.Id = new ArrayList<>();
        this.dados = new ArrayList<>();
        this.filhos = new ArrayList<>();

    }

    public void imprimirNode(int nivel) {

        String identacao = " ".repeat(nivel);
        StringBuilder infoNode = new StringBuilder();

        for(int i = 0; i < Id.size(); i++) {

            Items registros = dados.get(i);

            String formatString = registros.toString();

            if(formatString.length() > 40) {
                formatString = formatString.substring(0, 37) + "...";
            }

            infoNode.append(Id.get(i))
                    .append(" [")
                    .append(formatString)
                    .append("] ");
        }

        System.out.println(identacao + "NÍVEL " + nivel + " | " + infoNode.toString().trim());

        if(!isFolha) {
            for(Node filho : filhos) {
                filho.imprimirNode(nivel + 1);
            }
        }
    }

    public List<Integer> getId() {
        return Id;
    }

    public List<Items> getDados() {
        return dados;
    }

    public boolean getIsFolha() {
        return isFolha;
    }

    public List<Node> getFilhos() {
        return filhos;
    }

    public int getGrau() {
        return grau;
    }
}
