package Projects.Emprestimo_Livros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {

    private static int contadorId = 1; // Para gerar IDs automáticos
    private int id;
    private LocalDate dataEmprestimo;
    private Pessoa pessoa;
    private List<Livro> livros;

    public Emprestimo(Pessoa pessoa) {
        this.id = contadorId++;
        this.dataEmprestimo = LocalDate.now(); // Pega a data atual do sistema
        this.pessoa = pessoa;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            this.livros.add(livro);
            livro.setDisponivel(false); // Marca o livro como indisponível
        } else {
            System.out.println("O livro '" + livro.getTitulo() + "' já está emprestado!");
        }
    }

    public int getId() { return id; }
    public Pessoa getPessoa() { return pessoa; }
    public List<Livro> getLivros() { return livros; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Empréstimo ID: ").append(id).append(" ---\n");
        sb.append("Data: ").append(dataEmprestimo).append("\n");
        sb.append("Cliente: ").append(pessoa.getNome()).append("\n");
        sb.append("Livros:\n");
        for (Livro l : livros) {
            sb.append("  - ").append(l.getTitulo()).append("\n");
        }
        return sb.toString();
    }
}
