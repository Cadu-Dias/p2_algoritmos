package heap_sort;

import java.util.Arrays;

public class HeapSortIgnorandoZero {

    // Métodos auxiliares para cálculo de índices (baseados em 1, como no seu código original)
    public static int parent(int i) {
        return i / 2;
    }

    public static int left(int i) {
        return 2 * i;
    }

    public static int right(int i) {
        return 2 * i + 1;
    }

    // Método auxiliar para troca de elementos
    // Nota: 'A' aqui é o array auxiliar 'tempA' que terá o índice 0 ignorado.
    private static void swap(int[] A, int i, int j) {
        var temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * Mantém a propriedade de Max-Heap para a subárvore enraizada em 'i'.
     * Assume que as subárvores dos filhos já são Max-Heaps.
     * @param A O array (auxiliar) que representa o heap.
     * @param i O índice da raiz da subárvore a ser heapificada (base 1).
     * @param heapSize O tamanho atual do heap (número de elementos válidos no array, excluindo o índice 0).
     */
    public static void maxHeapify(int[] A, int i, int heapSize) {
        var l = left(i);   // Índice do filho esquerdo
        var r = right(i);  // Índice do filho direito
        int largest;       // Armazena o índice do maior elemento entre pai e filhos

        // encontrar, entre pai, filho esquerdo e filho direito, quem é o maior
        // As condições de limite devem usar <= heapSize, pois heapSize representa o último índice válido.
        if (l <= heapSize && A[l] > A[i]) {
            largest = l;
        } else {
            largest = i;
        }

        if (r <= heapSize && A[r] > A[largest]) {
            largest = r;
        }

        // se for o caso, trocar pai por filho maior
        // aplicar o procedimento de maneira recursiva
        if (largest != i) {
            swap(A, i, largest);
            maxHeapify(A, largest, heapSize);
        }
    }

    /**
     * Constrói um Max-Heap a partir de um array desordenado.
     * @param A O array (auxiliar) a ser transformado em Max-Heap.
     * @param heapSize O número de elementos no array que devem fazer parte do heap.
     */
    public static void buildMaxHeap(int[] A, int heapSize) {
        // aplicar o heapify de baixo para cima, a partir do primeiro elemento que tem
        // filho
        // O loop começa do último nó não-folha e vai até 1 (a raiz).
        for (int i = heapSize / 2; i >= 1; i--) {
            maxHeapify(A, i, heapSize);
        }
    }

    /**
     * Implementa o algoritmo HeapSort para ordenar um array em ordem crescente.
     * Esta é a função principal que gerencia o array auxiliar.
     * @param A O array de inteiros a ser ordenado (passado pelo usuário).
     */
    public static void heapSort(int[] A) {
        if (A == null || A.length <= 1) {
            return; // Array vazio ou com um único elemento já está ordenado.
        }

        // 1. Crie um array auxiliar com tamanho A.length + 1 para ignorar o índice 0.
        int[] tempA = new int[A.length + 1];

        // 2. Copie os elementos do array original para o array auxiliar, começando do índice 1.
        for (int i = 0; i < A.length; i++) {
            tempA[i + 1] = A[i];
        }

        // 3. O tamanho do heap (número de elementos úteis) é o tamanho do array original.
        int n = A.length;

        // 4. Constrói um Max-Heap a partir do array auxiliar.
        buildMaxHeap(tempA, n);

        // 5. Extrai o maior elemento (raiz) e o coloca no final do heap, iterativamente.
        for (int i = n; i >= 2; i--) {
            // Troca o maior elemento (tempA[1]) com o último elemento do heap atual (tempA[i]).
            swap(tempA, 1, i);
            // Reduz o tamanho do heap (o elemento em tempA[i] não faz mais parte do heap).
            // Heapify na nova raiz (tempA[1]) para restaurar a propriedade de heap.
            maxHeapify(tempA, 1, i - 1); // Passa i-1 como novo tamanho do heap
        }

        // 6. Copie os elementos ordenados de volta para o array original.
        for (int i = 0; i < A.length; i++) {
            A[i] = tempA[i + 1];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        System.out.println("Array original 1: " + Arrays.toString(arr1));
        heapSort(arr1);
        System.out.println("Array ordenado 1: " + Arrays.toString(arr1));

        System.out.println();

        int[] arr2 = {1, 5, 2, 8, 3, 9, 4, 6, 7};
        System.out.println("Array original 2: " + Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println("Array ordenado 2: " + Arrays.toString(arr2));

        System.out.println();

        int[] arr3 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("Array original 3: " + Arrays.toString(arr3));
        heapSort(arr3);
        System.out.println("Array ordenado 3: " + Arrays.toString(arr3));

        System.out.println();

        int[] arr4 = {5};
        System.out.println("Array original 4: " + Arrays.toString(arr4));
        heapSort(arr4);
        System.out.println("Array ordenado 4: " + Arrays.toString(arr4));

        System.out.println();

        int[] arr5 = {};
        System.out.println("Array original 5: " + Arrays.toString(arr5));
        heapSort(arr5);
        System.out.println("Array ordenado 5: " + Arrays.toString(arr5));
    }
}