package Trabalhos_Praticos.Trabalho_Pratico_002;

import Trabalhos_Praticos.Trabalho_Pratico_002.algorithms.*;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.ListaAdj;
import Trabalhos_Praticos.Trabalho_Pratico_002.utils.CSVExporter;
import Trabalhos_Praticos.Trabalho_Pratico_002.utils.GrafoReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Caminho do arquivo de exemplo para demonstração
    private static final String caminhoArquivo = "Grafos/sample500-99800.gr";
    // Defina o número de execuções para o experimento (mínimo 10)
    private static final int NUM_EXECUTIONS = 10;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int op;

        do {

            System.out.println("\n=====================================");
            System.out.println("    ALGORITMOS DE GRAFOS    \n");
            System.out.println("1. Executar todos algoritmos");
            System.out.println("2. Executar experimento (Dijkstra vs. Bellman-Ford");
            System.out.println("0. Sair");
            System.out.println("=======================================");
            System.out.println("Selecione uma opcao: ");

            try {

                op = scanner.nextInt();

                switch (op) {

                    case 1:
                        runAll(caminhoArquivo);
                        break;
                    case 2:
                        runExperimento();
                        break;
                    case 0:
                        System.out.println("Aplicação encerrada!");
                        break;
                    default:
                        System.out.println("Opção inválida, selecione novamente!");

                }

            } catch (InputMismatchException error) {
                System.out.println("Entrada inválida.");
                scanner.nextLine();
                op = -1;
            } catch (Exception error) {
                System.err.println("[ERRO]: " + error.getMessage());
                op = -1;
            }

        } while (op != 0);

        scanner.close();

    }

    private static void runAll(String caminhoArquivo) throws IOException {

        System.out.println("[LOG] Carregando Grafo");
        Grafos grafo = GrafoReader.lerGrafo(caminhoArquivo, "LA", true, true);
        imprimeGrafo(grafo, caminhoArquivo);

        int verticeOrigem = 0;
        int fonteFluxoMax = 0;
        int sumidouro = grafo.numVertices() - 1;

        // 1. BFS
        executaAlgoritmo("Busca em Largura (BFS)", () -> {
            Bfs.BFS(grafo, verticeOrigem);});

        // 2. DFS
        executaAlgoritmo("Busca em Profundidade (DFS)", () -> {
            Dfs.DFS(grafo, verticeOrigem);});

        // 3. Dijkstra
        executaAlgoritmo("Dijkistra (Caminho Mínimo)", () -> {
            double[] dist = Dijkstra.menorCaminho(grafo, verticeOrigem);
            System.out.println("Distâncias a partir de " + verticeOrigem + ": " + Arrays.toString(dist));

        });

        // 4. Bellman-Ford
        executaAlgoritmo("Bellman-Ford (Caminho Mínimo)", () -> {
            double[] dist = BellmanFord.bellmanFord(grafo, verticeOrigem);
            if(dist != null) {
                System.out.println("Distâncias a partir de " + verticeOrigem + ": " + Arrays.toString(dist));
            }
        });

        // 5. Prim
        executaAlgoritmo("Busca em Largura (BFS)", () -> {
            Grafos grafoNaoOrdenado = GrafoReader.lerGrafo(caminhoArquivo, "LA", true, false);
            double mstPeso = Prim.primMST(grafoNaoOrdenado);
            System.out.println("Peso Total da MST (Prim): " + mstPeso);
        });

            // 6. Kruskal
        executaAlgoritmo("Busca em Largura (BFS)", () -> {
            Grafos grafoNaoOrdenado = GrafoReader.lerGrafo(caminhoArquivo, "LA", true, false);
            double mstPeso = Kruskal.kruskalMST(grafoNaoOrdenado);
            System.out.println("Peso Total da MST (Kruskal): " + mstPeso);
        });

            // 7. Floyd-Warshall
        executaAlgoritmo("Busca em Largura (BFS)", () -> {
            double[][] distMatriz = FloydWarshall.floydWarshall(grafo);
            System.out.println("Distância Mínima entre Todos os Pares (Dist[0][n]): " + distMatriz[verticeOrigem][grafo.numVertices() - 1]);
        });
        // 8. Ford-Fulkerson
        executaAlgoritmo("Busca em Largura (BFS)", () -> {
            double fluxoMax = FordFulkerson.maxFluxo(grafo, fonteFluxoMax, sumidouro);
            System.out.println("Fluxo Máximo de " + fonteFluxoMax + " para " + sumidouro + ": " + fluxoMax);
        });
    }

    private static void executaAlgoritmo(String nome, RunAlgoritmo run) throws IOException {

        System.out.println("\n-> Executando Algoritmo " + nome);
        long tempoInicio = System.nanoTime();
        run.execute();
        long tempoFim = System.nanoTime();

        double tempoExec = (tempoFim - tempoInicio) / 1_000_000.0;

        System.out.printf("Tempo de Execução (%s): %.4f ms\n", nome, tempoExec);

    }

    private static void runExperimento() {
        System.out.println("\n--- EXPERIMENTO: Bellman-Ford vs. Dijkstra ---");
        System.out.println("Rodando " + NUM_EXECUTIONS + " execuções para cada combinação...");

        boolean fileExists = new java.io.File(CSVExporter.CSV_FILE).exists();

        CSVExporter exporter = new CSVExporter(!fileExists);

        String[][] configuracoesGrafo = {
                {"Grafos/sample100-1980.gr", "100", "1980", "Ɛ"},
                {"Grafos/sample100-3960.gr", "100", "3960", "Ɛ"},
                {"Grafos/sample100-5940.gr", "100", "5940", "Ɛ"},
                {"Grafos/sample100-7920.gr", "100", "7920", "Ɗ"},
                {"Grafos/sample100-9900.gr", "100", "9900", "Ɗ"},
                {"Grafos/sample200-7960.gr", "200", "7960", "Ɛ"},
                {"Grafos/sample200-15920.gr", "200", "15920", "Ɛ"},
                {"Grafos/sample200-23880.gr", "200", "23880", "Ɛ"},
                {"Grafos/sample200-31840.gr", "200", "31840", "Ɗ"},
                {"Grafos/sample200-39800.gr", "200", "39800", "Ɗ"},
                {"Grafos/sample500-49900.gr", "500", "49900", "Ɛ"},
                {"Grafos/sample500-99800.gr", "500", "99800", "Ɛ"},
                {"Grafos/sample500-149700.gr", "500", "149700", "Ɛ"},
                {"Grafos/sample500-199600.gr", "500", "199600", "Ɗ"},
                {"Grafos/sample500-249500.gr", "500", "249500", "Ɗ"},

        };

        for (String[] config : configuracoesGrafo) {
            String filePath = config[0];
            String type = config[3];

            performaceExperimento(filePath, "LA", type, exporter);
            performaceExperimento(filePath, "MA", type, exporter);
        }

        exporter.export();
        System.out.println("\nExperimento concluído");
    }

    private static void performaceExperimento(String caminhoArquivo, String tipoImplementacao, String tipoGrafo, CSVExporter exporter) {

        try {
            Grafos grafo = GrafoReader.lerGrafo(caminhoArquivo, tipoImplementacao, true, true);
            int verticeOrigem = 0;

            System.out.println("\nConfiguração: Grafo " + tipoGrafo + ", Implementação: " + tipoImplementacao);

            long totalTimeBF = 0;
            for (int i = 0; i < NUM_EXECUTIONS; i++) {
                totalTimeBF += measureAlgorithmTime(() -> BellmanFord.bellmanFord(grafo, verticeOrigem));
            }
            double avgTimeBF = (double) totalTimeBF / NUM_EXECUTIONS / 1_000_000.0;
            exporter.addData(avgTimeBF, "Bellman-Ford", tipoImplementacao, tipoGrafo);
            System.out.printf("  Bellman-Ford (V=%d, E=%d): %.4f ms (Média de %d execuções)\n",
                    grafo.numVertices(), grafo.numArestas(), avgTimeBF, NUM_EXECUTIONS);

            long totalTimeDijkstra = 0;
            for (int i = 0; i < NUM_EXECUTIONS; i++) {
                totalTimeDijkstra += measureAlgorithmTime(() -> Dijkstra.menorCaminho(grafo, verticeOrigem));
            }
            double avgTimeDijkstra = (double) totalTimeDijkstra / NUM_EXECUTIONS / 1_000_000.0;
            exporter.addData(avgTimeDijkstra, "Dijkstra", tipoImplementacao, tipoGrafo);
            System.out.printf("  Dijkstra (V=%d, E=%d): %.4f ms (Média de %d execuções)\n",
                    grafo.numVertices(), grafo.numArestas(), avgTimeDijkstra, NUM_EXECUTIONS);

        } catch (IOException error) {
            System.err.println("Falha ao carregar grafo para experimento: " + caminhoArquivo + ". Erro: " + error.getMessage());
        }
    }

    private static void imprimeGrafo(Grafos grafo, String caminhoArquivo) {
        System.out.println("\n----------------------------------------------");
        System.out.println("  VISUALIZAÇÃO DO GRAFO DE ENTRADA");
        System.out.println("----------------------------------------------");
        System.out.println("Arquivo de Entrada: " + caminhoArquivo);
        System.out.println("Implementação: " + (grafo instanceof ListaAdj ? "Lista de Adjacência" : "Matriz de Adjacência"));
        System.out.println("Vértices (V): " + grafo.numVertices());
        System.out.println("Arestas (E): " + grafo.numArestas());
        System.out.println("Tipo: " + (grafo.ordenado() ? "Dirigido (Dígrafo)" : "Não-Dirigido"));
        System.out.println("Ponderado: " + (grafo.ponderado() ? "Sim" : "Não"));

        int maxPrint = Math.min(5, grafo.numVertices());
        for (int i = 0; i < maxPrint; i++) {
            System.out.print("Vértice " + i + " -> ");
            grafo.getAdjacencias(i).forEach(aresta ->
                    System.out.print("(" + aresta.getVerticeDestino() + ", P=" + aresta.getPeso() + ") "));
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

    private static <T> long measureAlgorithmTime(AlgorithmSupplier<T> supplier) {
        long startTime = System.nanoTime();
        supplier.execute();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @FunctionalInterface
    interface RunAlgoritmo {
        void execute() throws IOException;
    }

    @FunctionalInterface
    interface AlgorithmSupplier<T> {
        T execute();
    }
}
