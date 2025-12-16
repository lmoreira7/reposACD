import Projects.Emprestimo_Livros.Biblioteca;
import Projects.Emprestimo_Livros.Emprestimo;
import Projects.Emprestimo_Livros.Livro;
import Projects.Emprestimo_Livros.Pessoa;

import java.util.List;
import java.util.Scanner;

public class Main {

    // Instancia o repositório e o scanner globalmente para acesso nos métodos
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Dados de teste iniciais (Opcional, apenas para facilitar o uso)
        biblioteca.adicionarLivro(new Livro(1, "Dom Quixote", "Miguel de Cervantes"));
        biblioteca.adicionarLivro(new Livro(2, "O Senhor dos Anéis", "J.R.R. Tolkien"));
        biblioteca.adicionarLivro(new Livro(3, "Java: Como Programar", "Deitel"));
        biblioteca.adicionarPessoa(new Pessoa(1, "Maria Silva", "123.456.789-00"));
        biblioteca.adicionarPessoa(new Pessoa(2, "João Santos", "987.654.321-11"));

        int opcao = 0;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            processarOpcao(opcao);

        } while (opcao != 8);
    }

    private static void exibirMenu() {
        System.out.println("\n========== SISTEMA DE BIBLIOTECA ==========");
        System.out.println("1. Novo Empréstimo");
        System.out.println("2. Buscar Livro (Consulta)");
        System.out.println("3. Buscar Pessoa (Consulta)");
        System.out.println("4. Imprimir/Buscar um Empréstimo Específico");
        System.out.println("5. Novo Livro (Cadastro)");
        System.out.println("6. Nova Pessoa (Cadastro)");
        System.out.println("7. Visualizar todos os empréstimos");
        System.out.println("8. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                realizarNovoEmprestimo();
                break;
            case 2:
                buscarLivroInterativo();
                break;
            case 3:
                buscarPessoaInterativo();
                break;
            case 4:
                imprimirEmprestimoEspecifico();
                break;
            case 5:
                cadastrarNovoLivro();
                break;
            case 6:
                cadastrarNovaPessoa();
                break;
            case 7:
                listarTodosEmprestimos();
                break;
            case 8:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    // --- LÓGICA DO MENU 1: NOVO EMPRÉSTIMO ---
    private static void realizarNovoEmprestimo() {
        System.out.println("\n--- NOVO EMPRÉSTIMO ---");

        // 1. Vincular Pessoa
        System.out.print("Digite o ID do usuário: ");
        int idPessoa = Integer.parseInt(scanner.nextLine());
        Pessoa p = biblioteca.buscarPessoaPorId(idPessoa);

        if (p == null) {
            System.out.println("Usuário não encontrado. Cadastre-o primeiro.");
            return;
        }
        System.out.println("Usuário selecionado: " + p.getNome());

        // Cria o objeto empréstimo
        Emprestimo emprestimo = new Emprestimo(p);

        // 2. Vincular Livros (Loop)
        boolean adicionarMais = true;
        while (adicionarMais) {
            System.out.print("Digite o ID do livro a ser emprestado: ");
            int idLivro = Integer.parseInt(scanner.nextLine());
            Livro l = biblioteca.buscarLivroPorId(idLivro);

            if (l != null) {
                if (l.isDisponivel()) {
                    emprestimo.adicionarLivro(l);
                    System.out.println("Livro adicionado ao empréstimo.");
                } else {
                    System.out.println("Este livro já está emprestado no momento.");
                }
            } else {
                System.out.println("Livro não encontrado no acervo.");
            }

            System.out.print("Deseja adicionar outro livro a este empréstimo? (S/N): ");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("N")) {
                adicionarMais = false;
            }
        }

        // Só salva se houver livros
        if (!emprestimo.getLivros().isEmpty()) {
            biblioteca.adicionarEmprestimo(emprestimo);
            System.out.println("Empréstimo finalizado com sucesso! ID do Empréstimo: " + emprestimo.getId());
        } else {
            System.out.println("Empréstimo cancelado (nenhum livro adicionado).");
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private static void cadastrarNovoLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        System.out.print("ID do Livro: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        biblioteca.adicionarLivro(new Livro(id, titulo, autor));
        System.out.println("Livro cadastrado!");
    }

    private static void cadastrarNovaPessoa() {
        System.out.println("\n--- CADASTRO DE PESSOA ---");
        System.out.print("ID da Pessoa: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        biblioteca.adicionarPessoa(new Pessoa(id, nome, cpf));
        System.out.println("Pessoa cadastrada!");
    }

    private static void buscarLivroInterativo() {
        System.out.print("Digite o ID do livro para buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Livro l = biblioteca.buscarLivroPorId(id);
        if (l != null) System.out.println(l);
        else System.out.println("Livro não encontrado.");
    }

    private static void buscarPessoaInterativo() {
        System.out.print("Digite o ID da pessoa para buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Pessoa p = biblioteca.buscarPessoaPorId(id);
        if (p != null) System.out.println(p);
        else System.out.println("Pessoa não encontrada.");
    }

    private static void imprimirEmprestimoEspecifico() {
        System.out.print("Digite o ID do Empréstimo: ");
        int id = Integer.parseInt(scanner.nextLine());
        Emprestimo e = biblioteca.buscarEmprestimoPorId(id);
        if (e != null) System.out.println(e);
        else System.out.println("Empréstimo não encontrado.");
    }

    private static void listarTodosEmprestimos() {
        System.out.println("\n--- LISTA DE TODOS OS EMPRÉSTIMOS ---");
        List<Emprestimo> lista = biblioteca.getTodosEmprestimos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum empréstimo realizado.");
        } else {
            for (Emprestimo e : lista) {
                System.out.println(e); // Chama o toString() do Emprestimo
                System.out.println("-------------------------");
            }
        }
    }
}