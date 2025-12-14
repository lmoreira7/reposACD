package Trabalhos_Praticos.Trabalho_Pratico_003;

import Trabalhos_Praticos.Trabalho_Pratico_003.classes.BTree;
import Trabalhos_Praticos.Trabalho_Pratico_003.classes.Items;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int op = -1;
        int id;
        BTree ArvoreB = new BTree(3);
        Scanner scanner = new Scanner(System.in);

        final String DATA_BASE = "dados.txt";

        carregarDados(DATA_BASE, ArvoreB);

        while(op != 5) {

            System.out.println("     ### GERENCIAMENTO DE ESTOQUE ###   \n\n");
            System.out.println("1. Criar novo registro\n2. Atualizar estoque\n3. Excluir registro\n4. Imprimir estoque\n5. Sair\n");
            System.out.println("Selecione uma opcao: ");

            try {

                op = scanner.nextInt();

                switch (op) {

                    case 1:

                        System.out.println("Informe o ID do item: ");
                        id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Informe a descrição do item: ");
                        String descricao = scanner.nextLine();
                        System.out.println("Informe a quantidade: ");
                        int quantidade = scanner.nextInt();

                        Items dado = new Items(descricao, quantidade);

                        ArvoreB.insert(id, dado);

                        break;
                    case 2:

                        System.out.println("Informe o ID que deseja atualizar: ");
                        id = scanner.nextInt();

                        System.out.println("Informe a nova descrição do item: ");
                        String newDescricao = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("Informe nova quantidade: ");
                        int newQuantidade = scanner.nextInt();

                        Items newData = new Items(newDescricao, newQuantidade);

                        boolean resultAtt = ArvoreB.atualizar(id, newData);

                        if(resultAtt) {
                            System.out.println("Atualização realizada com sucesso");
                        } else {
                            System.out.println("Não foi possivel atualizar o registro. ID não encontrado!\n");
                        }

                        break;
                    case 3:

                        System.out.println("Informe o ID que deseja remover: ");
                        int idRemove = scanner.nextInt();

                        boolean resultDelete = ArvoreB.remove(idRemove);

                        if(resultDelete) {
                            System.out.println("Item removido com sucesso!\n");
                        } else {
                            System.out.println("Não foi possivel remover o registro, ID não encontrado!");
                        }

                        break;
                    case 4:
                        ArvoreB.imprimirArvore();
                        break;
                    case 5:
                        salvarDados(DATA_BASE, ArvoreB);
                        System.out.println("Aplicação encerrada com sucesso!!\n");
                        break;
                    default:
                        System.out.println("Opcao invalida, selecione novamente!");

                }
            } catch (NumberFormatException error) {

                System.out.println("Opção invalida, selecione novamente!");

            }

        }

    }

    public static void carregarDados(String caminhoArquivo, BTree ArvoreB) {

        System.out.println("[LOG] Iniciando carga de dados do arquivo: " + caminhoArquivo);

        int contaLinhas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;

            while((linha = br.readLine()) != null) {

                if(linha.trim().isEmpty()) continue;

                String[] partes = linha.split(";", 2);

                if(partes.length == 2) {

                    try {
                         int id = Integer.parseInt(partes[0].trim());
                         String dadosItem = partes[1].trim();

                         Items item = Items.fromFileFormat(dadosItem);

                         ArvoreB.insert(id, item);
                         contaLinhas++;
                    } catch (NumberFormatException error) {
                            System.err.println("[ERRO] dado mal formatado\n");
                    } catch (IllegalArgumentException error) {
                            System.err.println("[ERRO] dado mal formatado\n");
                    }

                }
            }

        } catch (FileNotFoundException error) {
            System.out.println("[ERRO] Arquivo não encontrado!\n");
        } catch (IOException error) {
            System.err.println("[ERRO] Falha ao ler o arquivo: " + error.getMessage());
        }

        System.out.println("[LOG] Carga finalizada!\n");
    }

    public static void salvarDados(String caminhoArquivo, BTree ArvoreB) {

        System.out.println("\n[LOG] Salvando alterações... Não encerre a aplicação");

        List<String> registroSave = ArvoreB.obterRegistros();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo))) {

            for(String linhaRegistro : registroSave) {

                bw.write(linhaRegistro);
                bw.newLine();
            }

            System.out.println("[LOG] Banco de dados salvo com sucesso!");
        } catch (IOException error) {
            System.err.println("[ERRO] Falha ao escrever no arquivo!");
        }
    }

}
