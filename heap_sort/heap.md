# Algoritmo de Ordenação HeapSort

## 1. O que é o Algoritmo HeapSort?

O HeapSort é um algoritmo de ordenação por comparação que utiliza a estrutura de dados **Heap Binário** (especificamente, um Max-Heap para ordenação crescente ou um Min-Heap para ordenação decrescente). Ele é considerado um algoritmo eficiente, com complexidade de tempo de pior caso, médio e melhor caso de $O(n \log n)$.

### **Características Principais:**
* **Baseado em Heap:** A chave para o HeapSort é a propriedade da estrutura de Heap, onde o maior (ou menor) elemento está sempre na raiz.
* **In-place (quase):** Embora precise de um pequeno espaço auxiliar para algumas operações de swap, pode ser implementado de forma que a ordenação ocorra dentro do próprio array de entrada, sem a necessidade de um array auxiliar de tamanho `n`.
* **Não Estável:** A ordem relativa de elementos com chaves iguais não é preservada.
* **Eficiência:** Garante $O(n \log n)$ em todos os cenários, o que o torna competitivo com MergeSort e QuickSort (no pior caso).

### **Como Funciona (Passo a Passo com Exemplo):**

O HeapSort opera em duas fases principais:

1.  **Construção do Heap (Build Max-Heap):**
    * Transforma o array de entrada em um Max-Heap (ou Min-Heap, dependendo da ordem desejada).
    * Isso é feito aplicando a operação `maxHeapify` (ou `minHeapify`) de baixo para cima, começando do último nó não-folha até a raiz.

2.  **Ordenação (HeapSort Principal):**
    * Repetidamente extrai o maior elemento (a raiz do Max-Heap) e o coloca na sua posição final ordenada no final do array.
    * Após cada extração, o heap é reconstruído para manter a propriedade de Max-Heap.

Vamos usar o array `A = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]` para ordenar em ordem crescente, utilizando um Max-Heap.

### **Fase 1: Construção do Max-Heap**

Para um array baseado em 0, os filhos de um nó `i` são `2i+1` (esquerdo) e `2i+2` (direito), e o pai de `i` é `(i-1)/2`.
A operação `maxHeapify(A, i, n)` assume que as subárvores dos filhos de `i` já são Max-Heaps e corrige a posição de `i`.
Para construir o heap, aplicamos `maxHeapify` da metade do array (o primeiro nó com pelo menos um filho) até a raiz (índice 0).
`n` = tamanho do array - 1 (último índice).

**Array Inicial:** `A = [4, 1, 3, 2, 16, 9, 10, 14, 8, 7]`
**Tamanho (length):** 10. Último índice: 9.
**Primeiro nó não-folha:** `(length/2) - 1 = (10/2) - 1 = 5 - 1 = 4` (índice 4, que é o valor 16).

---

**Passo 1: `maxHeapify(A, 4, 9)` (Valor 16)**
* `A[4] = 16`. Filhos: `A[9] = 7` (esquerdo inexistente `2*4+1=9`, `2*4+2=10` inexistente)
* Na verdade, `(length/2) - 1` é o primeiro nó que **pode** ter filhos. O último nó não-folha é `(n-1)/2` se n é o tamanho válido do heap. Se estamos falando do array completo `A.length - 1` é o último índice, então `(A.length - 1 - 1)/2 = (8)/2 = 4`. Ok, o índice 4 (valor 16) é o primeiro.
* Filhos de `A[4]` (16): `2*4+1=9` (`A[9]=7`). `2*4+2=10` (fora do limite).
* `A[4]` (16) > `A[9]` (7). `16` já é o maior. Nada muda.
* Array: `[4, 1, 3, 2, 16, 9, 10, 14, 8, 7]`

---

**Passo 2: `maxHeapify(A, 3, 9)` (Valor 2)**
* `A[3] = 2`. Filhos: `A[7] = 14`, `A[8] = 8`.
* Maior entre `A[3]`, `A[7]`, `A[8]` é `A[7]` (14).
* Troca `A[3]` com `A[7]`. `A = [4, 1, 3, 14, 16, 9, 10, 2, 8, 7]`
* Recurse em `maxHeapify(A, 7, 9)`. (Nós folhas, nada muda).
* Array: `[4, 1, 3, 14, 16, 9, 10, 2, 8, 7]`

---

**Passo 3: `maxHeapify(A, 2, 9)` (Valor 3)**
* `A[2] = 3`. Filhos: `A[5] = 9`, `A[6] = 10`.
* Maior é `A[6]` (10).
* Troca `A[2]` com `A[6]`. `A = [4, 1, 10, 14, 16, 9, 3, 2, 8, 7]`
* Recurse em `maxHeapify(A, 6, 9)`. (Nó folha, nada muda).
* Array: `[4, 1, 10, 14, 16, 9, 3, 2, 8, 7]`

---

**Passo 4: `maxHeapify(A, 1, 9)` (Valor 1)**
* `A[1] = 1`. Filhos: `A[3] = 14`, `A[4] = 16`.
* Maior é `A[4]` (16).
* Troca `A[1]` com `A[4]`. `A = [4, 16, 10, 14, 1, 9, 3, 2, 8, 7]`
* Recurse em `maxHeapify(A, 4, 9)`.
    * `A[4] = 1`. Filhos: `A[9] = 7`.
    * `A[4]` (1) < `A[9]` (7). Maior é `A[9]` (7).
    * Troca `A[4]` com `A[9]`. `A = [4, 16, 10, 14, 7, 9, 3, 2, 8, 1]`
    * Recurse em `maxHeapify(A, 9, 9)`. (Nó folha).
* Array: `[4, 16, 10, 14, 7, 9, 3, 2, 8, 1]`

---

**Passo 5: `maxHeapify(A, 0, 9)` (Valor 4)**
* `A[0] = 4`. Filhos: `A[1] = 16`, `A[2] = 10`.
* Maior é `A[1]` (16).
* Troca `A[0]` com `A[1]`. `A = [16, 4, 10, 14, 7, 9, 3, 2, 8, 1]`
* Recurse em `maxHeapify(A, 1, 9)`.
    * `A[1] = 4`. Filhos: `A[3] = 14`, `A[4] = 7`.
    * Maior é `A[3]` (14).
    * Troca `A[1]` com `A[3]`. `A = [16, 14, 10, 4, 7, 9, 3, 2, 8, 1]`
    * Recurse em `maxHeapify(A, 3, 9)`.
        * `A[3] = 4`. Filhos: `A[7] = 2`, `A[8] = 8`.
        * Maior é `A[8]` (8).
        * Troca `A[3]` com `A[8]`. `A = [16, 14, 10, 8, 7, 9, 3, 2, 4, 1]`
        * Recurse em `maxHeapify(A, 8, 9)`. (Nó folha).

**Array após Construção do Max-Heap:** `A = [16, 14, 10, 8, 7, 9, 3, 2, 4, 1]`
(Note que a representação visual de um heap é uma árvore, mas ele é armazenado linearmente em um array).

### **Fase 2: Ordenação (Extração do Maior Elemento)**

Agora, o array está no formato de Max-Heap. O maior elemento (16) está na raiz (`A[0]`).
Vamos iterar de trás para frente, trocando a raiz com o último elemento não ordenado e reduzindo o tamanho do heap.

**Array atual (Max-Heap):** `A = [16, 14, 10, 8, 7, 9, 3, 2, 4, 1]`
**`n` (tamanho lógico do heap):** 9 (índice do último elemento válido no heap).

---

**Iteração 1: `i = 9` (último elemento)**
* Troca `A[0]` (16) com `A[9]` (1). `A = [1, 14, 10, 8, 7, 9, 3, 2, 4, 16]`
* Reduz `n` para 8. (O 16 está agora na sua posição final ordenada).
* `maxHeapify(A, 0, 8)`: Restaura a propriedade de heap para o heap de tamanho 9.
    * `A[0] = 1`. Filhos: `A[1]=14`, `A[2]=10`. Maior é 14.
    * Troca `A[0]` com `A[1]`. `A = [14, 1, 10, 8, 7, 9, 3, 2, 4, 16]`
    * Recurse `maxHeapify(A, 1, 8)`:
        * `A[1] = 1`. Filhos: `A[3]=8`, `A[4]=7`. Maior é 8.
        * Troca `A[1]` com `A[3]`. `A = [14, 8, 10, 1, 7, 9, 3, 2, 4, 16]`
        * Recurse `maxHeapify(A, 3, 8)`:
            * `A[3] = 1`. Filhos: `A[7]=2`, `A[8]=4`. Maior é 4.
            * Troca `A[3]` com `A[8]`. `A = [14, 8, 10, 4, 7, 9, 3, 2, 1, 16]`
            * Recurse `maxHeapify(A, 8, 8)`. (Nó folha).
* Array: `[14, 8, 10, 4, 7, 9, 3, 2, 1, 16]` (16 está ordenado)

---

**Iteração 2: `i = 8`**
* Troca `A[0]` (14) com `A[8]` (1). `A = [1, 8, 10, 4, 7, 9, 3, 2, 14, 16]`
* Reduz `n` para 7.
* `maxHeapify(A, 0, 7)`: Restaura heap.
    * `A[0] = 1`. Filhos: `A[1]=8`, `A[2]=10`. Maior é 10.
    * Troca `A[0]` com `A[2]`. `A = [10, 8, 1, 4, 7, 9, 3, 2, 14, 16]`
    * Recurse `maxHeapify(A, 2, 7)`:
        * `A[2] = 1`. Filhos: `A[5]=9`, `A[6]=3`. Maior é 9.
        * Troca `A[2]` com `A[5]`. `A = [10, 8, 9, 4, 7, 1, 3, 2, 14, 16]`
        * Recurse `maxHeapify(A, 5, 7)`:
            * `A[5] = 1`. Filhos: `A[7]=2`. Maior é 2.
            * Troca `A[5]` com `A[7]`. `A = [10, 8, 9, 4, 7, 2, 3, 1, 14, 16]`
            * Recurse `maxHeapify(A, 7, 7)`. (Nó folha).
* Array: `[10, 8, 9, 4, 7, 2, 3, 1, 14, 16]` (14, 16 estão ordenados)

---

... e assim por diante, o processo continua até que o tamanho do heap seja 1.

**Array após a ordenação completa (o estado final seria):**
`A = [1, 2, 3, 4, 7, 8, 9, 10, 14, 16]`

### **Observação sobre o Índice Zero:**

No problema anterior de HeapSort, foi feita uma pergunta sobre "ignorar o índice zero". Isso é uma convenção comum em alguns livros (como o CLRS - Cormen, Leiserson, Rivest, Stein) para simplificar as fórmulas de cálculo de índices de pais e filhos (`parent(i) = i/2`, `left(i) = 2i`, `right(i) = 2i+1`).

Quando implementamos o HeapSort **ignorando o índice 0**, o array é alocado com um tamanho `N+1` para `N` elementos, e os dados são armazenados a partir do índice 1. Isso torna a tradução direta do pseudocódigo do Cormen para o Java mais fácil, mas introduz um pequeno desperdício de memória no índice 0. As operações `parent`, `left` e `right` então usam a matemática do livro (divisão e multiplicação simples).

No exemplo prático acima, assumimos um array baseado em 0, que é a convenção padrão do Java, e as fórmulas para `parent`, `left`, `right` seriam:
* `parent(i) = (i - 1) / 2`
* `left(i) = 2 * i + 1`
* `right(i) = 2 * i + 2`

Ambas as abordagens (ignorar índice 0 ou usar índice 0) são válidas, e a escolha geralmente depende da preferência do implementador ou da base de conhecimento utilizada. A funcionalidade do HeapSort permanece a mesma.

## 2. Aplicação do HeapSort no Exercício Passado Anteriormente

O exercício anterior focado em Dijkstra e Bellman-Ford tratava de problemas de busca de **caminho mais curto em grafos**. O HeapSort, por outro lado, é um **algoritmo de ordenação**.

Isso significa que o HeapSort não se aplica diretamente ao problema de encontrar o "caminho de menor custo entre duas regiões" como Dijkstra. Eles resolvem classes de problemas completamente diferentes:

* **Problema anterior (Dijkstra/Bellman-Ford):** Qual é a sequência ótima de regiões para ir de A a B com o menor custo total? (Problema de roteamento/otimização de caminho).
* **Problema do HeapSort:** Como rearranjar um conjunto de elementos (números, strings, objetos comparáveis) em uma ordem específica (crescente ou decrescente)? (Problema de ordenação).

### **Como o HeapSort poderia ser *indiretamente* útil em um contexto de grafos/logística:**

Embora não seja para o problema central de caminho mais curto, o HeapSort poderia ser usado como uma **sub-rotina** ou em um **pré-processamento** para outros algoritmos ou análises em um sistema de logística:

1.  **Ordenação de Arestas por Peso (para Kruskal):**
    * No algoritmo de Kruskal (para MST), a primeira etapa é ordenar todas as arestas do grafo por seus pesos em ordem crescente. O HeapSort seria uma escolha eficiente para realizar essa ordenação, devido à sua complexidade de tempo $O(n \log n)$ em todos os casos.
    * **Exemplo:** Se você tem uma lista de milhares de possíveis rotas entre regiões, com seus respectivos custos, e precisa encontrar as rotas mais baratas para construir uma rede de distribuição (MST), você poderia usar HeapSort para ordenar essa lista de rotas antes de aplicar Kruskal.

2.  **Priorização de Tarefas/Entregas:**
    * Em um sistema de logística, pode haver uma lista de tarefas ou entregas que precisam ser priorizadas com base em urgência, custo ou algum outro critério numérico. O HeapSort poderia ser usado para ordenar essa lista de tarefas por sua prioridade.
    * **Exemplo:** Um centro de distribuição recebe pedidos com diferentes prazos de entrega. Você pode usar HeapSort para organizar os pedidos em ordem de prioridade (prazos mais apertados primeiro) para otimizar o planejamento de rotas de entrega (que ainda seria resolvido por algoritmos de grafos, mas a lista de entradas para eles seria pré-ordenada).

3.  **Análise de Dados de Desempenho:**
    * Se um sistema de logística coleta dados sobre o tempo médio de entrega para diferentes rotas, ou o custo de combustível por viagem, esses dados poderiam ser armazenados e depois ordenados com HeapSort para identificar rapidamente as rotas mais eficientes ou as que precisam de otimização.

Em resumo, o HeapSort é uma ferramenta poderosa para **organizar dados**, o que é uma tarefa fundamental em muitas aplicações de ciência da computação. Embora não seja um algoritmo de grafo por si só, ele pode ser um componente valioso dentro de sistemas maiores que lidam com grafos e problemas de logística.