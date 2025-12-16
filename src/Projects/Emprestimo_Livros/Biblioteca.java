package Projects.Emprestimo_Livros;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervoLivros;
    private List<Pessoa> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.acervoLivros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro l) {
        acervoLivros.add(l);
    }
    public void adicionarPessoa(Pessoa p) {
        usuarios.add(p);
    }
    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public Livro buscarLivroPorId(int id) {
        for (Livro l : acervoLivros) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    public Pessoa buscarPessoaPorId(int id) {
        for (Pessoa p : usuarios) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Emprestimo buscarEmprestimoPorId(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Livro> getTodosLivros() {
        return acervoLivros;
    }

    public List<Pessoa> getTodasPessoas() {
        return usuarios;
    }

    public List<Emprestimo> getTodosEmprestimos() {
        return emprestimos;
    }

}
