# Heap: Uma Estrutura de Dados Baseada em Árvore

Um **Heap** é uma estrutura de dados baseada em árvore que satisfaz a propriedade do heap. Embora seja geralmente implementado como um array, ele pode ser visualizado como uma árvore binária completa. A característica principal de um Heap é que o valor de cada nó é comparado com os valores de seus filhos de uma forma específica, mantendo uma ordem parcial.

Existem dois tipos principais de Heap:

1.  **Max-Heap (Heap Máximo):** Em um Max-Heap, para qualquer nó 'n', o valor de 'n' é maior ou igual aos valores de seus filhos. Isso significa que o elemento de maior valor está sempre na raiz do Heap.
2.  **Min-Heap (Heap Mínimo):** Em um Min-Heap, para qualquer nó 'n', o valor de 'n' é menor ou igual aos valores de seus filhos. Isso significa que o elemento de menor valor está sempre na raiz do Heap.

---

## Propriedades Fundamentais de um Heap

Um Heap possui duas propriedades principais:

1.  **Propriedade da Forma (Shape Property):** Um Heap é uma **árvore binária completa**. Isso significa que todos os níveis da árvore, exceto possivelmente o último, estão completamente preenchidos, e todos os nós no último nível estão o mais à esquerda possível. Essa propriedade é crucial porque permite que o Heap seja eficientemente armazenado em um array, sem deixar "buracos".

2.  **Propriedade da Ordem (Heap Property):**
    * **Max-Heap:** Para cada nó `i` que não é a raiz, `Parent(i) >= i`. (O valor do pai é maior ou igual ao valor do filho).
    * **Min-Heap:** Para cada nó `i` que não é a raiz, `Parent(i) <= i`. (O valor do pai é menor ou igual ao valor do filho).

---

## Representação de um Heap em Array

A propriedade da forma de uma árvore binária completa permite uma representação muito eficiente de um Heap usando um array (ou `ArrayList` em Java, `list` em Python).

Se o nó em um array está no índice `i` (considerando índices baseados em 0):

* **Pai de `i`:** `(i - 1) / 2` (divisão inteira)
* **Filho Esquerdo de `i`:** `2 * i + 1`
* **Filho Direito de `i`:** `2 * i + 2`

Essa representação economiza memória, pois não são necessários ponteiros explícitos para os filhos.

---

## Operações Típicas em um Heap

As operações básicas em um Heap são:

1.  **`Insert (Inserir)`:** Adicionar um novo elemento ao Heap.
    * O novo elemento é adicionado no final do array (na próxima posição disponível, mantendo a propriedade da forma).
    * Em seguida, a propriedade da ordem é restaurada "subindo" o elemento (chamado **heapify-up** ou **percolate-up**). O elemento é trocado com seu pai se estiver fora de ordem, e esse processo continua até que a propriedade da ordem seja satisfeita ou o elemento chegue à raiz.
    * Complexidade: $O(\log N)$, onde $N$ é o número de elementos no Heap.

2.  **`Delete (Remover)` (especificamente `extract-max` ou `extract-min`):** Remover o elemento de maior (Max-Heap) ou menor (Min-Heap) prioridade, que é sempre o elemento na raiz.
    * O elemento da raiz é removido e armazenado para retorno.
    * O último elemento do array é movido para a raiz para manter a propriedade da forma.
    * Em seguida, a propriedade da ordem é restaurada "descendo" o elemento (chamado **heapify-down** ou **percolate-down**). O elemento é trocado com o maior (Max-Heap) ou menor (Min-Heap) de seus filhos, e esse processo continua até que a propriedade da ordem seja satisfeita ou o elemento chegue a uma folha.
    * Complexidade: $O(\log N)$.

3.  **`Peek (Espiar)` (ou `get-max`/`get-min`):** Obter o elemento de maior (Max-Heap) ou menor (Min-Heap) prioridade sem removê-lo.
    * Simplesmente retorna o elemento na raiz do Heap.
    * Complexidade: $O(1)$.

4.  **`Build Heap (Construir Heap)`:** Converter um array arbitrário em um Heap.
    * Isso é feito aplicando a operação `heapify-down` (ou `percolate-down`) de baixo para cima na árvore, começando pelo último nó que não é uma folha (`(N/2) - 1` para índices baseados em 0).
    * Complexidade: $O(N)$ (embora uma análise ingênua possa sugerir $O(N \log N)$, uma análise mais rigorosa mostra que é $O(N)$).

---

## Aplicações do Heap

Heaps são estruturas de dados extremamente úteis e versáteis, com muitas aplicações em ciência da computação:

* **Filas de Prioridade (Priority Queues):** Esta é a aplicação mais comum e direta. Um Heap é a base para implementar filas de prioridade eficientes, onde elementos com maior (ou menor) prioridade são sempre processados primeiro.
* **Heapsort (Ordenação por Heap):** Um algoritmo de ordenação eficiente que tem complexidade de tempo de $O(N \log N)$ no pior caso.
* **Algoritmos de Grafo:**
    * **Algoritmo de Dijkstra:** Usado para encontrar os caminhos mais curtos em um grafo. Filas de prioridade baseadas em Heap são essenciais para otimizar a seleção do próximo vértice a ser visitado.
    * **Algoritmo de Prim:** Usado para encontrar a Árvore Geradora Mínima (AGM). Filas de prioridade baseadas em Heap otimizam a busca pela próxima aresta de menor peso.
* **Sistema Operacional:** Gerenciamento de tarefas (tarefas com maior prioridade são executadas primeiro).
* **Simulações de Eventos:** Para gerenciar eventos em ordem cronológica.
* **Estatísticas:** Encontrar o k-ésimo maior/menor elemento em um conjunto de dados de forma eficiente.

---

## Comparação com Outras Estruturas de Dados

* **Com Árvores de Busca Binária (BSTs):**
    * BSTs mantêm uma ordem total (elementos à esquerda são menores, à direita são maiores). Heaps mantêm apenas uma ordem parcial (pai vs. filhos).
    * BSTs são boas para busca e inserção/deleção de qualquer elemento. Heaps são otimizados para encontrar e remover o elemento de maior/menor prioridade.
    * BSTs podem se degenerar (pior caso $O(N)$ para operações) sem balanceamento. Heaps são naturalmente balanceados (árvore completa) e garantem $O(\log N)$ para as operações principais.

* **Com Listas Encadeadas/Arrays Simples:**
    * Inserção/remoção em O(1) no final/início de listas simples, mas encontrar o máximo/mínimo é O(N). Heaps oferecem $O(1)$ para peek e $O(\log N)$ para inserção/remoção do extremo, sendo muito mais eficientes para filas de prioridade.

Em resumo, um Heap é uma estrutura de dados fundamental para cenários onde a recuperação eficiente do elemento de maior ou menor prioridade é uma necessidade crucial. Sua representação compacta em array e suas garantias de desempenho em tempo logarítmico a tornam uma ferramenta poderosa em algoritmos e sistemas.