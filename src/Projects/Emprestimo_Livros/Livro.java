package Projects.Emprestimo_Livros;

public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private boolean disponivel; // Flag para saber se já está emprestado

    public Livro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true; // Por padrão, o livro nasce disponível
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "ID: " + id + " | Título: " + titulo + " | Autor: " + autor + " | Disp: " + (disponivel ? "Sim" : "Não");
    }
}
