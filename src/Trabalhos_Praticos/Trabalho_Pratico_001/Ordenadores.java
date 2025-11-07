package Trabalhos_Praticos.Trabalho_Pratico_001;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ordenadores {

    public static <T extends Comparable<T>> void bubbleSort(T[] vet, int n) {

        T auxVar;

        for (int i = 0; i < n; i++) {

            boolean trocou = false;

            for (int j = 1; j < n-i; j++) {

                if (vet[j].compareTo(vet[j - 1]) < 0) {    // Compara os pares
                    auxVar = vet[j - 1];
                    vet[j - 1] = vet[j];
                    vet[j] = auxVar;
                    trocou = true;
                }
            }

            if (!trocou) {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] vet, int n) {

        T auxVar;

        for (int i = 0; i < n - 1; i++) {

            int valMin = i;

            for (int j = i + 1; j < n; j++) {

                if (vet[j].compareTo(vet[valMin]) < 0) {
                    valMin = j;
                }
            }

            if (valMin != i) {
                auxVar = vet[i];
                vet[i] = vet[valMin];
                vet[valMin] = auxVar;
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] vet, int n) {

        for (int i = 1; i < n; i++) {

            T auxVar = vet[i];
            int j = i - 1;

            while (j >= 0 && vet[j].compareTo(auxVar) > 0) {
                vet[j + 1] = vet[j];
                j--;
            }
            vet[j + 1] = auxVar;
        }
    }

    public static <T extends Comparable<T>> void shellSort(T[] vet, int n) {

        int h = 1;

        while(h < n / 3) {
            h = 3 * h + 1;
        }

        while(h >= 1) {

            for (int i = h; i < n; i++) {

                T auxVal = vet[i];
                int j = i;

                while (j >= h && vet[j - h].compareTo(auxVal) > 0) {

                    vet[j] = vet[j - h];
                    j = j - h;
                }

                vet[j] = auxVal;
            }

            h /= 3;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] vet, int init, int n) {

        if (init >= n) {
            return;
        }

        int pointMed = (init + n) / 2;

        // Realiza a divisão do vetor
        mergeSort(vet, init, pointMed);
        mergeSort(vet, pointMed + 1, n);

        merge(vet, init, pointMed, n);
    }

    public static <T extends Comparable<T>> void heapSort(T[] vet, int n) {

        for (int i = (n / 2-1); i >= 0; i--) {
            maxHeap(vet, n, i);
        }

        for (int i = n-1; i > 0; i--) {

            swap(vet, 0, i);
            maxHeap(vet, i, 0);

        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] vet, int inicio, int fim) {

        if (inicio < fim) {

            int indicePivo = particionar(vet, inicio, fim);

            quickSort(vet, inicio, indicePivo - 1);
            quickSort(vet, indicePivo + 1, fim);
        }
    }

    public static <T extends Number & Comparable<T>> void countingSort(T[] vet, int n, int k) {

        @SuppressWarnings("unchecked")
        T[] B = (T[]) java.lang.reflect.Array.newInstance(vet.getClass().getComponentType(), n);
        int[] C = new int[k + 1];

        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        for (int j = 0; j < n; j++) {
            int value = vet[j].intValue();
            C[value]++;
        }

        for (int i = 1; i <= k; i++) {
            C[i] = C[i] + C[i - 1];
        }

        for (int j = n - 1; j >= 0; j--) {
            int value = vet[j].intValue();
            B[C[value]-1] = vet[j];
            C[value]--;
        }

        for (int i = 0; i < n; i++) {
            vet[i] = B[i];
        }

    }

    public static void radixSort(Integer[] vet, int n) {

        int maxValue = getMaxValue(vet);

        for(int exp = 1; maxValue / exp > 0; exp *= 10) {
            countingSortByDigit(vet, n, exp);
        }
    }

    public static <T extends Comparable<T>> void bucketSort(T[] vet) {

        if (vet == null || vet.length <= 1) {
            return;
        }

        T minValue = vet[0];
        T maxValue = vet[0];

        for (T auxVal : vet) {

            if (auxVal.compareTo(minValue) < 0) {
                minValue = auxVal;
            }
            if (auxVal.compareTo(maxValue) > 0) {
                maxValue = auxVal;
            }
        }

        int numBuckets = vet.length;
        List<List<T>> buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        for (T auxVal : vet) {
            int bucketIndex = getBucketIndex(auxVal, minValue, maxValue, numBuckets);
            buckets.get(bucketIndex).add(auxVal);
        }

        int index = 0;

        for (List<T> bucket : buckets) {
            Collections.sort(bucket);

            for (T auxVal : bucket) {
                vet[index++] = auxVal;
            }
        }
    }

    // METODOS AUXILARES //

    private static <T extends Comparable<T>> void merge(T[] vet, int init, int pointMed, int n) {

        int tamFirstPart = (pointMed - init) + 1;        // Tamanho da primeira metade do vetor
        int tamLastPart = (n - pointMed);           // Tamanho da segunda metade do vetor

        @SuppressWarnings("unchecked")
        T[] firstAuxVet = (T[]) new Comparable[tamFirstPart];
        @SuppressWarnings("unchecked")
        T[] lastAuxVet = (T[]) new Comparable[tamLastPart];


        for (int i = 0; i < tamFirstPart; i++) {

            firstAuxVet[i] = vet[init + i];     // Copia elementos para o vetor auxiliar
        }

        for (int j = 0; j < tamLastPart; j++) {

            lastAuxVet[j] = vet[pointMed + 1 + j];   // Copia elementos para o vetor auxiliar
        }

        int i = 0, j = 0, markOriginal = init;

        while (i < tamFirstPart && j < tamLastPart) {

            // COMPARA OS PARES PARA ORDENADOR O VETOR ORIGINAL //

            if (firstAuxVet[i].compareTo(lastAuxVet[j]) <= 0) {
                vet[markOriginal] = firstAuxVet[i];
                i++;
            } else {
                vet[markOriginal] = lastAuxVet[j];
                j++;
            }

            markOriginal++;
        }

        while (i < tamFirstPart) {
            vet[markOriginal] = firstAuxVet[i];
            markOriginal++;
            i++;
        }

        while (j < tamLastPart) {
            vet[markOriginal] = lastAuxVet[j];
            markOriginal++;
            j++;
        }
    }

    private static <T extends Comparable<T>> void maxHeap(T[] vet, int n, int i) {

        int root = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && vet[left].compareTo(vet[root]) > 0) {
            root = left;
        }

        if (right < n && vet[right].compareTo(vet[root]) > 0) {
            root = right;
        }

        if (root != i) {
            swap(vet, i, root);
            maxHeap(vet, n, root);
        }
    }

    private static <T> void swap(T[] vet, int i, int j) {
        T auxTemp = vet[i];
        vet[i] = vet[j];
        vet[j] = auxTemp;
    }

    private static <T extends Comparable<T>> int particionar(T[] vet, int inicio, int fim) {
        T pivo = vet[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (vet[j].compareTo(pivo) <= 0) {
                i++;
                swap(vet, i, j);
            }
        }

        swap(vet, i + 1, fim);

        return i + 1;
    }

    private static <T extends Comparable<T>> int getBucketIndex(T auxVal, T minValue, T maxValue, int numBuckets) {

        if (minValue.equals(maxValue)) {
            return 0;
        }

        double normalized = (toDouble(auxVal) - toDouble(minValue)) / (toDouble(maxValue) - toDouble(minValue));
        return Math.min((int) (normalized * (numBuckets - 1)), numBuckets - 1);
    }

    private static <T> double toDouble(T value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        else {
            throw new IllegalArgumentException("BucketSort suporta apenas tipos numéricos.");
        }
    }

    private static int getMaxValue(Integer[] vet) {

        int maxValue = vet[0];

        for(int auxVal : vet) {
            if(auxVal > maxValue) {
                maxValue = auxVal;
            }
        }

        return maxValue;
    }

    private static void countingSortByDigit(Integer[] vet, int n, int exp) {

        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        for (int j = 0; j < n; j++) {
            int digit = (vet[j] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }

        for (int j = n - 1; j >= 0; j--) {
            int digit = (vet[j] / exp) % 10;
            output[count[digit] - 1] = vet[j];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            vet[i] = output[i];
        }
    }
}

