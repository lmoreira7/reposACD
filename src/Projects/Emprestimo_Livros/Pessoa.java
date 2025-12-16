package Projects.Emprestimo_Livros;

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;

    public Pessoa(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | CPF: " + cpf;
    }
}
