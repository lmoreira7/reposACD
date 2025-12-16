package Trabalhos_Praticos.Trabalho_Pratico_002.utils;

import Trabalhos_Praticos.Trabalho_Pratico_002.classes.Grafos;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.ListaAdj;
import Trabalhos_Praticos.Trabalho_Pratico_002.classes.MatrizAdj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GrafoReader {

    public static Grafos lerGrafo(String caminhoArquivo, String tipoImplementacao, boolean ponderado, boolean ordenado) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;
            int V = 0;
            int E = 0;

            while((linha = br.readLine()) != null) {

                linha = linha.trim();

                if(linha.isEmpty() || linha.startsWith("c")) {
                    continue;
                }

                if(linha.startsWith("p")) {

                    String[] partes = linha.split("\\s+");
                    V = Integer.parseInt(partes[2]);
                    E = Integer.parseInt(partes[3]);
                    break;

                }

            }

            if(V == 0) {
                throw new IOException("Formato nao encontrado");
            }

            Grafos grafo = (tipoImplementacao.equalsIgnoreCase("LA"))
                    ? new ListaAdj(V, ordenado, ponderado)
                    : new MatrizAdj(V, ponderado, ordenado);

            while ((linha = br.readLine()) != null) {

                linha = linha.trim();

                if(linha.isEmpty() || linha.startsWith("c")) {
                    continue;
                }

                if(linha.startsWith("a")) {
                    String[] partes = linha.split("\\s+");

                    if(partes.length < 4) {
                        continue;
                    }

                    int u = Integer.parseInt(partes[1]) - 1;
                    int v = Integer.parseInt(partes[2]) - 1;
                    double w = ponderado ? Double.parseDouble(partes[3]) : 1.0;

                    if(ponderado) {
                        grafo.addAresta(u, v, w);
                    } else {
                        grafo.addAresta(u, v);
                    }
                }

            }

            return grafo;

        } catch (IOException error) {
            System.err.println("[ERRO]: Não foi possivel ler o arquivo: " + error.getMessage());
            throw error;
        }

    }

}
