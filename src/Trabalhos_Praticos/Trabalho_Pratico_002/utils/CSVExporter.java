package Trabalhos_Praticos.Trabalho_Pratico_002.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVExporter {

    public static final String CSV_FILE = "dados_experimento.csv";
    private static final String CSV_HEADER = "Tempo_ms,Algoritmo,Fator_A_Representacao,Fator_B_Tipo_Grafo,V,E,Iteracao";

    private final List<String> dataLines = new ArrayList<>();
    private final boolean firstRun; // Flag para controlar se é a primeira vez que o arquivo será escrito

    public CSVExporter(boolean firstRun) {
        this.firstRun = firstRun;
        // O cabeçalho só é adicionado na primeira execução
        if (firstRun) {
            dataLines.add(CSV_HEADER);
        }
    }

    // ... (Método addData permanece o mesmo) ...
    public void addData(double timeMs, String algoritmo, String representacao, String tipoGrafo) {
        // ... (código addData) ...
        String line = String.format(Locale.US, "%.6f,%s,%s,%s",
                timeMs, algoritmo, representacao, tipoGrafo);
        dataLines.add(line);
    }

    /**
     * Salva todos os dados coletados no arquivo CSV, ANEXANDO se já existir.
     */
    public void export() {
        // O parâmetro 'true' no FileWriter ativa o modo APPEND (Anexar)
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            // Se for a primeira execução, escreve o cabeçalho primeiro
            if (firstRun) {
                writer.println(CSV_HEADER);
            }
            // Escreve as linhas de dados (sem o cabeçalho, que já foi escrito ou está na lista)
            for (int i = firstRun ? 1 : 0; i < dataLines.size(); i++) {
                writer.println(dataLines.get(i));
            }

            System.out.println("\n[SUCESSO] Dados do experimento ANEXADOS ao arquivo: " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("ERRO ao salvar o arquivo CSV: " + e.getMessage());
        }
    }
}
