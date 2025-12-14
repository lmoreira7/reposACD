package Trabalhos_Praticos.Trabalho_Pratico_003.classes;

public class Items {

    private String descricao;
    private int quantidade;

    public Items(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public Items(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {

        return String.format("Desc: \"%s\", Qtd: %d", descricao != null ? descricao : "N/D", quantidade);

    }

    public String toFileFormat() {
        return String.format("%s|%d", descricao, quantidade);
    }

    public static Items fromFileFormat(String linhaDados) {

        String[] partes = linhaDados.split("\\|");

        if(partes.length == 2) {

            String descricao = partes[0].trim();
            int quantidade = Integer.parseInt(partes[1].trim());
            return new Items(descricao, quantidade);
        }

        throw new IllegalArgumentException("Formato de linha de dados inválido para items");
    }
}
