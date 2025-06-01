package heap_sort;

import java.util.Arrays;

/**
 * Implementa uma Max-Heap (Heap de Máximo) com as operações
 * descritas no livro do Cormen.
 */
public class MaxHeap {
    private int[] A; // O array que representa o heap.
    private int heapSize; // O número de elementos atualmente no heap.
    private int capacity; // A capacidade total do array A.

    /**
     * Construtor para criar um MaxHeap a partir de um array existente.
     *
     * @param array O array inicial para construir o heap.
     *              Complexidade: O(n) para construir o heap (via buildMaxHeap, se
     *              implementado).
     */
    public MaxHeap(int[] array) {
        this.A = array;
        this.heapSize = array.length;
        this.capacity = array.length;
        // Se a intenção é construir um heap a partir de um array desordenado,
        // chamar buildMaxHeap(). Por simplicidade, assumimos que inicialmente
        // o array vazio ou um elemento será inserido.
    }

    /**
     * Construtor para criar um MaxHeap vazio com uma capacidade inicial.
     *
     * @param initialCapacity A capacidade inicial do heap.
     *                        Complexidade: O(initialCapacity).
     */
    public MaxHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que 0.");
        }
        this.A = new int[initialCapacity];
        this.heapSize = 0;
        this.capacity = initialCapacity;
    }

    /**
     * Retorna o índice do pai de um nó.
     * 
     * @param i Índice do nó.
     * @return Índice do pai.
     *         Complexidade: O(1).
     */
    private int PARENT(int i) {
        // No Cormen, os arrays são baseados em 1. Em Java, baseados em 0.
        // Para i (base 0), o pai é (i-1)/2.
        return (i - 1) / 2;
    }

    /**
     * Retorna o índice do filho à esquerda de um nó.
     * 
     * @param i Índice do nó.
     * @return Índice do filho à esquerda.
     *         Complexidade: O(1).
     */
    private int LEFT(int i) {
        // Em Java (base 0), o filho esquerdo de i é 2*i + 1.
        return 2 * i + 1;
    }

    /**
     * Retorna o índice do filho à direita de um nó.
     * 
     * @param i Índice do nó.
     * @return Índice do filho à direita.
     *         Complexidade: O(1).
     */
    private int RIGHT(int i) {
        // Em Java (base 0), o filho direito de i é 2*i + 2.
        return 2 * i + 2;
    }

    /**
     * Mantém a propriedade Max-Heap a partir de um nó 'i' para baixo.
     * (Max-Heapify em Cormen).
     * 
     * @param i O índice do nó a partir do qual a propriedade deve ser restaurada.
     *          Complexidade: O(log n), onde n é heapSize.
     */
    private void MAX_HEAPIFY(int i) { // Corresponde ao MAX-HEAPIFY(A, i) no Cormen.
        int l = LEFT(i); // Índice do filho esquerdo.
        int r = RIGHT(i); // Índice do filho direito.
        int largest = i; // Assume que o nó atual é o maior.

        // Se o filho esquerdo existe e é maior que o nó atual, ele é o maior.
        if (l < heapSize && A[l] > A[largest]) {
            largest = l;
        }

        // Se o filho direito existe e é maior que o maior até agora, ele é o maior.
        if (r < heapSize && A[r] > A[largest]) {
            largest = r;
        }

        // Se o maior não é o nó atual, troca e chama MAX_HEAPIFY recursivamente.
        if (largest != i) {
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            MAX_HEAPIFY(largest); // Chamada recursiva para manter a propriedade para baixo.
        }
    }

    /**
     * HEAP-MAXIMUM(A)
     * Retorna o maior elemento do heap (a raiz).
     * 
     * @return O valor do maior elemento.
     *         Complexidade: O(1).
     */
    public int HEAP_MAXIMUM() {
        if (heapSize == 0) {
            throw new IllegalStateException("Heap está vazio.");
        }
        return A[0]; // O maior elemento está sempre na raiz (índice 0).
    }

    /**
     * HEAP-EXTRACT-MAX(A)
     * Remove e retorna o maior elemento do heap.
     * 
     * @return O maior elemento removido.
     * @throws IllegalStateException Se o heap estiver vazio.
     *                               Complexidade: O(log n).
     */
    public int HEAP_EXTRACT_MAX() {
        if (heapSize < 1) { // if A.heap-size < 1
            throw new IllegalStateException("Heap underflow"); // error "heap underflow"
        }
        int max = A[0]; // max = A[1] (no Cormen, A[1] é a raiz)
        A[0] = A[heapSize - 1]; // A[1] = A[A.heap-size]
        heapSize = heapSize - 1; // A.heap-size = A.heap-size - 1
        MAX_HEAPIFY(0); // MAX-HEAPIFY(A, 1)
        return max; // return max
    }

    /**
     * HEAP-INCREASE-KEY(A, i, key)
     * Aumenta o valor de uma chave no índice 'i' para 'key'.
     * 
     * @param i   O índice do elemento a ser aumentado.
     * @param key O novo valor da chave.
     * @throws IllegalArgumentException  Se a nova chave for menor que a chave
     *                                   atual.
     * @throws IndexOutOfBoundsException Se o índice 'i' for inválido.
     *                                   Complexidade: O(log n).
     */
    public void HEAP_INCREASE_KEY(int i, int key) {
        if (i < 0 || i >= heapSize) {
            throw new IndexOutOfBoundsException("Índice fora dos limites do heap.");
        }
        if (key < A[i]) { // if key < A[i]
            throw new IllegalArgumentException("new key is smaller than current key"); // error "new key is smaller than
                                                                                       // current key"
        }
        A[i] = key; // A[i] = key
        // while i > 1 and A[PARENT(i)] < A[i] (no Cormen)
        while (i > 0 && A[PARENT(i)] < A[i]) {
            // exchange A[i] with A[PARENT(i)]
            int temp = A[i];
            A[i] = A[PARENT(i)];
            A[PARENT(i)] = temp;
            i = PARENT(i); // i = PARENT(i)
        }
    }

    /**
     * MAX-HEAP-INSERT(A, key)
     * Insere uma nova chave no heap.
     * 
     * @param key A chave a ser inserida.
     * @throws IllegalStateException Se o heap estiver cheio (capacidade esgotada).
     *                               Complexidade: O(log n).
     */
    public void MAX_HEAP_INSERT(int key) {
        if (heapSize == capacity) {
            // Pode-se implementar redimensionamento do array aqui se necessário.
            throw new IllegalStateException("Heap está cheio, não é possível inserir.");
        }
        heapSize = heapSize + 1; // A.heap-size = A.heap-size + 1
        A[heapSize - 1] = Integer.MIN_VALUE; // A[A.heap-size] = -infinito (no Cormen, para garantir que increase-key
                                             // funcione)
        HEAP_INCREASE_KEY(heapSize - 1, key); // HEAP-INCREASE-KEY(A, A.heap-size, key)
    }

    /**
     * Retorna o tamanho atual do heap.
     * 
     * @return O número de elementos no heap.
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Imprime o conteúdo do array que representa o heap.
     * Nota: A ordem dos elementos no array não reflete a ordem em um percurso em
     * nível,
     * mas sim a estrutura interna do heap.
     */
    public void printHeapArray() {
        System.out.println("Conteúdo do Heap (array): " + Arrays.toString(Arrays.copyOfRange(A, 0, heapSize)));
    }

    // Método main para testar a implementação do MaxHeap.
    public static void main(String[] args) {
        System.out.println("--- Testando MaxHeap ---");

        // Cria um heap vazio com capacidade 10.
        MaxHeap heap = new MaxHeap(10);
        System.out.println("Heap criado com capacidade 10, tamanho: " + heap.getHeapSize());

        // Testando inserções
        System.out.println("\nInserindo elementos:");
        heap.MAX_HEAP_INSERT(15);
        heap.MAX_HEAP_INSERT(13);
        heap.MAX_HEAP_INSERT(9);
        heap.MAX_HEAP_INSERT(5);
        heap.MAX_HEAP_INSERT(12);
        heap.MAX_HEAP_INSERT(8);
        heap.MAX_HEAP_INSERT(7);
        heap.MAX_HEAP_INSERT(4);
        heap.MAX_HEAP_INSERT(0);
        heap.printHeapArray();
        System.out.println("Tamanho atual do Heap: " + heap.getHeapSize());
        System.out.println("Máximo elemento: " + heap.HEAP_MAXIMUM());

        // Testando HEAP_INCREASE_KEY
        System.out.println("\nTestando HEAP_INCREASE_KEY:");
        // Aumentando a chave do elemento no índice 8 (que é 0) para 100.
        // O heap interno deve ser [15, 13, 12, 5, 8, 9, 7, 4, 0] antes da inserção
        // e depois de algumas inserções, o elemento 0 pode não estar no índice 8.
        // É melhor aumentar uma chave de um elemento conhecido, ou um índice baixo.
        // Vamos aumentar A[6] (que deve ser 7) para 16.
        // Supondo o array A: [15, 13, 9, 5, 12, 8, 7, 4, 0]
        // A[6] é 7.
        try {
            System.out.println("Aumentando A[6] (valor 7) para 16...");
            heap.HEAP_INCREASE_KEY(6, 16); // Índice 6 (que é o 7 no exemplo)
            heap.printHeapArray();
            System.out.println("Máximo elemento após increase: " + heap.HEAP_MAXIMUM());
        } catch (Exception e) {
            System.out.println("Erro ao aumentar chave: " + e.getMessage());
        }

        // Tentando diminuir uma chave (deve lançar erro)
        try {
            System.out.println("\nTentando diminuir A[0] (valor " + heap.A[0] + ") para 10...");
            heap.HEAP_INCREASE_KEY(0, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Testando HEAP_EXTRACT_MAX
        System.out.println("\nExtraindo o maior elemento (HEAP_EXTRACT_MAX):");
        int extractedMax = heap.HEAP_EXTRACT_MAX();
        System.out.println("Elemento extraído: " + extractedMax);
        heap.printHeapArray();
        System.out.println("Novo tamanho do Heap: " + heap.getHeapSize());
        System.out.println("Novo máximo elemento: " + heap.HEAP_MAXIMUM());

        extractedMax = heap.HEAP_EXTRACT_MAX();
        System.out.println("\nExtraindo o maior elemento novamente:");
        System.out.println("Elemento extraído: " + extractedMax);
        heap.printHeapArray();
        System.out.println("Novo tamanho do Heap: " + heap.getHeapSize());
        System.out.println("Novo máximo elemento: " + heap.HEAP_MAXIMUM());

        // Esvaziando o heap
        System.out.println("\nEsvaziando o heap:");
        while (heap.getHeapSize() > 0) {
            System.out.print("Extraindo: " + heap.HEAP_EXTRACT_MAX() + " ");
        }
        System.out.println("\nHeap vazio.");
        heap.printHeapArray();

        // Tentando extrair de um heap vazio
        try {
            System.out.println("\nTentando extrair de um heap vazio:");
            heap.HEAP_EXTRACT_MAX();
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }
    }
}