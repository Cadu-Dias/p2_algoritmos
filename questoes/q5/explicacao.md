## **Enunciado**

Considere o programa e as proposições a seguir:

```java
public static void maxHeapify(int[] A, int i, int n) {
    int l = left(i);
    int r = right(i);
    int largest;

    if (l <= n && A[l] > A[i])
        largest = l;
    else
        largest = i;

    if (r <= n && A[r] > A[largest])
        largest = r;

    if (largest != i) {
        swap(A, i, largest);
        maxHeapify(A, largest, n);
    }
}
```

**I** - Após uma execução de "maxHeapify", o vetor A se torna um heap, independentemente do valor da variável i.

**II** - Após uma execução de "maxHeapify", com i valendo 1, o vetor A terminado ordenado de maneira não decrescente

**III** - Para que a linha 13 execute, é necessário (mas não suficiente) que o elemento na posição i do vetor tenha um filho à direita

### Explicação

- **❌ Proposição I: "Após uma execução de maxHeapify, o vetor A se torna um heap, independentemente do valor da variável i."
Falsa.**

  - A função maxHeapify não garante que o vetor inteiro se torne um heap — ela atua apenas na subárvore com raiz em i.

  - Para transformar o vetor inteiro em heap, é necessário aplicar maxHeapify para vários nós, como feito em buildMaxHeap.

- **❌ Proposição II: "Após uma execução de maxHeapify, com i valendo 1, o vetor A termina ordenado de maneira não decrescente."
Falsa.**

  - maxHeapify não ordena o vetor — apenas mantém ou restaura a propriedade de heap máximo localmente.

  - A ordenação do vetor ocorre com heapSort, que envolve múltiplas chamadas de maxHeapify em conjunto com trocas.

- **✅ Proposição III: "Para que a linha 13 execute, é necessário (mas não suficiente) que o elemento na posição i do vetor tenha um filho à direita."
Verdadeira.**

  - A linha 13 executa o swap e a chamada recursiva apenas se largest != i.

  - Isso só acontece se algum filho for maior que o elemento em i.

  - Ter um filho à direita (índice r) é uma condição necessária, mas não suficiente, pois ainda é preciso que esse filho seja maior que o atual maior valor (A[largest]).