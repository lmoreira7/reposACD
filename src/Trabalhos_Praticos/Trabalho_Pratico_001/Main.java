package Trabalhos_Praticos.Trabalho_Pratico_001;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer[] vetOriginal = null;

        System.out.println("Informe o tamanho do vetor: ");
        int tamanho = scanner.nextInt();
        int maxValue = tamanho;

        System.out.println("========= ALGORITMOS DE ORDENAÇÃO =============\n\n");
        System.out.println("1. Vetor aleatório\n2. Vetor Crescente\n3. Vetor Decrescente\n\n");
        System.out.println("Escolha uma opção: ");
        int op = scanner.nextInt();

        switch(op) {
            case 1:
                vetOriginal = arrayCreate(tamanho, maxValue);
                break;
            case 2:
                vetOriginal = arrayCreateCres(tamanho);
                break;
            case 3:
                vetOriginal = arrayCreateDec(tamanho);
                break;
            default:
                System.out.println("Opcao inválida, será gerado um vetor aleatório");
                vetOriginal = arrayCreate(tamanho, maxValue);
        }

        Integer[] vetCopy; // Vetor usado como cópia do vetor original
        long tempo;

        //  Bubble Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetBubble = vetCopy;
        tempo = calcTemp(() -> Ordenadores.bubbleSort(vetBubble, vetBubble.length));
        System.out.printf("Bubble Sort:    %.3f ms%n", tempo / 1_000_000.0);

        // Selection Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetSelection = vetCopy;
        tempo = calcTemp(() -> Ordenadores.selectionSort(vetSelection, vetSelection.length));
        System.out.printf("Selection Sort: %.3f ms%n", tempo / 1_000_000.0);

        // Insertion Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetInsertion = vetCopy;
        tempo = calcTemp(() -> Ordenadores.insertionSort(vetInsertion, vetInsertion.length));
        System.out.printf("Insertion Sort: %.3f ms%n", tempo / 1_000_000.0);

        // Shell Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetShell = vetCopy;
        tempo = calcTemp(() -> Ordenadores.shellSort(vetShell, vetShell.length));
        System.out.printf("Shell Sort:     %.3f ms%n", tempo / 1_000_000.0);

        // Merge Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetMerge = vetCopy;
        tempo = calcTemp(() -> Ordenadores.mergeSort(vetMerge, 0, vetMerge.length - 1));
        System.out.printf("Merge Sort:     %.3f ms%n", tempo / 1_000_000.0);

        // Heap Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetHeap = vetCopy;
        tempo = calcTemp(() -> Ordenadores.heapSort(vetHeap, vetHeap.length));
        System.out.printf("Heap Sort:      %.3f ms%n", tempo / 1_000_000.0);

        // Quick Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetQuick = vetCopy;
        tempo = calcTemp(() -> Ordenadores.quickSort(vetQuick, 0, vetQuick.length - 1));
        System.out.printf("Quick Sort:     %.3f ms%n", tempo / 1_000_000.0);

        // Counting Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetCount = vetCopy;
        tempo = calcTemp(() -> Ordenadores.countingSort(vetCount, vetCount.length, maxValue));
        System.out.printf("Counting Sort:  %.3f ms%n", tempo / 1_000_000.0);

        // Radix Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetRadix = vetCopy;
        tempo = calcTemp(() -> Ordenadores.radixSort(vetRadix, vetRadix.length));
        System.out.printf("Radix Sort:     %.3f ms%n", tempo / 1_000_000.0);

        // Bucket Sort //
        vetCopy = Arrays.copyOf(vetOriginal, vetOriginal.length);
        Integer[] vetBucket = vetCopy;
        tempo = calcTemp(() -> Ordenadores.bucketSort(vetBucket));
        System.out.printf("Bucket Sort:    %.3f ms%n", tempo / 1_000_000.0);

        System.out.println("-------------------------------------------");
        System.out.println("Testes concluídos!");

    }

    public static long calcTemp(Runnable algortimo) {
        long inicio = System.nanoTime();
        algortimo.run();
        long fim = System.nanoTime();

        return fim-inicio;
    }

    public static Integer[] arrayCreate(int tamanho, int max) {

        Random random = new Random();
        Integer[] vet = new Integer[tamanho];

        for(int i = 0; i < tamanho; i++) {
            vet[i] = random.nextInt(max);
        }

        return vet;
    }

    public static Integer[] arrayCreateDec(int tamanho) {

        Integer[] vet = new Integer[tamanho];

        for(int i = 0; i < tamanho; i++) {
            vet[i] = tamanho - i;
        }

        return vet;
    }

    public static Integer[] arrayCreateCres(int tamanho) {

        Integer[] vet = new Integer[tamanho];

        for(int i = 0; i < tamanho; i++) {
            vet[i] = i;
        }

        return vet;
    }
}
