package Trabalhos_Praticos.Trabalho_Pratico_003.classes;

import java.util.ArrayList;
import java.util.List;

public class BTree {

    private Node raiz;
    private int grau;

    public BTree(int grau) {

        this.grau = grau;
        this.raiz = new Node(grau, true);

    }

    public Items buscar(int id) {
        ResultSearch result = localizarId(this.raiz, id);

        if(result != null) {
            return result.node.getDados().get(result.id);
        }

        return null;
    }

    private Items buscar(Node node, int id) {

        int i = busca_binaria(node, id);

        if(i < node.getId().size() && node.getId().get(i) == id) {
            return node.getDados().get(i);
        }

        if(node.getIsFolha()) {
            return null;
        }

        return buscar(node.getFilhos().get(i), id);
    }

    private int busca_binaria(Node node, int id) {

        int inicio = 0;
        int fim = node.getId().size() - 1;

        while(inicio <= fim) {

            int meio = (inicio + fim)/2;
            int id_meio = node.getId().get(meio);

            if(id_meio == id) {
                return meio;
            }
            else if(id_meio < id) {

                inicio = meio + 1;

            } else {

                fim = meio - 1;

            }
        }

        return inicio;

    }

    public void insert(int id, Items dados) {

        Node root = this.raiz;

        if(root.getId().size() == (2*grau-1)) {

            Node newRoot = new Node(grau, false);

            newRoot.getFilhos().add(root);
            split(newRoot, 0, root);
            insertFree(newRoot, id, dados);
            this.raiz = newRoot;

        } else {

            insertFree(root, id, dados);

        }
    }

    private void insertFree(Node node, int id, Items dados) {

        int i = busca_binaria(node, id);

        if(node.getIsFolha()) {

            node.getId().add(i, id);
            node.getDados().add(i, dados);

        } else {

            if(node.getFilhos().get(i).getId().size() == (2*grau-1)) {

                split(node, i, node.getFilhos().get(i));

                if(id > node.getId().get(i)) {
                    i++;
                }

            }

            insertFree(node.getFilhos().get(i), id, dados);

        }
    }

    private void split(Node nodePai, int idFilho, Node filhoFulll) {

        Node newFilho = new Node(grau, filhoFulll.getIsFolha());

        for(int i = 0; i < grau - 1; i++) {

            newFilho.getId().add(filhoFulll.getId().remove(grau));
            newFilho.getDados().add(filhoFulll.getDados().remove(grau));

        }

        if(!filhoFulll.getIsFolha()) {

            for(int i = 0; i < grau; i++) {

                newFilho.getFilhos().add(filhoFulll.getFilhos().remove(grau));

            }
        }

        int id_mediano = filhoFulll.getId().remove(grau - 1);
        Items infoDado = filhoFulll.getDados().remove(grau - 1);

        nodePai.getId().add(idFilho, id_mediano);
        nodePai.getDados().add(idFilho, infoDado);
        nodePai.getFilhos().add(idFilho + 1, newFilho);

    }

    private ResultSearch localizarId(Node node, int id) {

        int i = busca_binaria(node, id);

        if(i < node.getId().size() && node.getId().get(i) == id) {
            return new ResultSearch(node, i);
        }

        if(node.getIsFolha()) {
            return null;
        }

        return localizarId(node.getFilhos().get(i), id);

    }

    public boolean atualizar(int id, Items newData) {

        ResultSearch result = localizarId(this.raiz, id);

        if(result != null) {
            result.node.getDados().set(result.id, newData);
            return true;
        }

        return false;

    }

    public void imprimirArvore() {
        raiz.imprimirNode(0);
    }

    public boolean remove(int id) {

        Node root = raiz;

        ResultSearch result = localizarId(root, id);

        if(result == null) {
            return false;
        }

        removeSubTree(root, id);

        if(raiz.getId().isEmpty() && !raiz.getIsFolha()) {

            raiz = raiz.getFilhos().get(0);

        }

        return true;
    }

    private void removeSubTree(Node node, int id) {

        int i = busca_binaria(node, id);

        if(i < node.getId().size() && node.getId().get(i) == id) {

            if(node.getIsFolha()) {

                node.getId().remove(i);
                node.getDados().remove(i);
            } else {

                if(node.getFilhos().get(i + 1).getId().size() >= grau) {

                    int sucessor = getSucessor(node, i);
                    node.getId().set(i, sucessor);
                    removeSubTree(node.getFilhos().get(i + 1), sucessor);

                }
                else if (node.getFilhos().get(i).getId().size() >= grau) {

                    int antecessor = getAntecessor(node, i);
                    node.getId().set(i, antecessor);
                    removeSubTree(node.getFilhos().get(i), antecessor);

                } else {

                    mergeFilhos(node, i, node.getFilhos().get(i), node.getFilhos().get(i + 1));
                    removeSubTree(node.getFilhos().get(i), id);

                }
            }

            return;
        }

        if(node.getIsFolha()) {
            return;
        }

        if(node.getFilhos().get(i).getId().size() < grau) {

            chavesMinimas(node, i);
        }

        removeSubTree(node.getFilhos().get(i), id);
    }

    private int getAntecessor(Node node, int i) {

        Node atual = node.getFilhos().get(i);

        while(!atual.getIsFolha()) {

            atual = atual.getFilhos().get(atual.getFilhos().size() - 1);

        }

        return atual.getId().get(atual.getId().size() - 1);

    }

    private int getSucessor(Node node, int i) {

        Node atual = node.getFilhos().get(i + 1);

        while (!atual.getIsFolha()) {

            atual = atual.getFilhos().get(0);

        }

        return atual.getId().get(0);
    }

    private void chavesMinimas(Node nodePai, int i) {

        Node filho = nodePai.getFilhos().get(i);

        if(i > 0 && nodePai.getFilhos().get(i - 1).getId().size() >= grau) {

            redistribuiEsquerda(nodePai, i);
            return;
        }

        if(i < nodePai.getFilhos().size() - 1 && nodePai.getFilhos().get(i + 1).getId().size() >= grau) {

            redistribuirDireita(nodePai, i);
            return;

        }

        if(i > 0) {

            mergeFilhos(nodePai, i-1, nodePai.getFilhos().get(i - 1), nodePai.getFilhos().get(i));

        } else {

            mergeFilhos(nodePai, i, nodePai.getFilhos().get(i), nodePai.getFilhos().get(i + 1));

        }
    }

    private void mergeFilhos(Node nodePai, int chaveDescidaID, Node esquerdo, Node direito) {

        int idDescida = nodePai.getId().remove(chaveDescidaID);
        Items dadoDescida = nodePai.getDados().remove(chaveDescidaID);

        esquerdo.getId().add(idDescida);
        esquerdo.getDados().add(dadoDescida);

        esquerdo.getId().addAll(direito.getId());
        esquerdo.getDados().addAll(direito.getDados());

        if(!esquerdo.getIsFolha()) {
            esquerdo.getFilhos().addAll(direito.getFilhos());
        }

        nodePai.getFilhos().remove(chaveDescidaID + 1);

    }

    private void redistribuiEsquerda(Node nodePai, int i) {
        Node filho = nodePai.getFilhos().get(i);
        Node irmaoEsquerdo = nodePai.getFilhos().get(i-1);

        int idDescida = nodePai.getId().remove(i - 1);
        Items dadoDescida = nodePai.getDados().remove(i - 1);

        filho.getId().add(0, idDescida);
        filho.getDados().add(0, dadoDescida);

        int lastId = irmaoEsquerdo.getId().remove(irmaoEsquerdo.getId().size() - 1);
        Items lastData = irmaoEsquerdo.getDados().remove(irmaoEsquerdo.getDados().size() - 1);

        nodePai.getId().add(i-1, lastId);
        nodePai.getDados().add(i-1, lastData);

        if(!filho.getIsFolha()) {

            Node lastChild = irmaoEsquerdo.getFilhos().remove(irmaoEsquerdo.getFilhos().size() - 1);
            filho.getFilhos().add(0, lastChild);

        }

    }

    private void redistribuirDireita(Node nodePai, int i) {
        Node filho = nodePai.getFilhos().get(i);
        Node irmaoDireito = nodePai.getFilhos().get(i+1);

        int idDescida = nodePai.getId().remove(i);
        Items dadoDescida = nodePai.getDados().remove(i);

        filho.getId().add(idDescida);
        filho.getDados().add(dadoDescida);

        int firstId = irmaoDireito.getId().remove(0);
        Items firstData = irmaoDireito.getDados().remove(0);

        nodePai.getId().add(i, firstId);
        nodePai.getDados().add(i, firstData);

        if(!filho.getIsFolha()) {

            Node firstChild = irmaoDireito.getFilhos().remove(0);
            filho.getFilhos().add(0, firstChild);

        }
    }

    public List<String> obterRegistros() {

        List<String> registros = new ArrayList<>();
        inOrder(raiz, registros);
        return registros;
    }

    private void inOrder(Node node, List<String> registros) {

        int numIDs = node.getId().size();

        for(int i = 0; i < numIDs; i++) {

            if(!node.getIsFolha()) {
                inOrder((node.getFilhos().get(i)), registros);
            }

            int id = node.getId().get(i);
            Items item = node.getDados().get(i);

            String linha = id + ";" + item.toFileFormat();
            registros.add(linha);

        }

        if(!node.getIsFolha()) {
            inOrder(node.getFilhos().get(numIDs), registros);
        }
    }

}
