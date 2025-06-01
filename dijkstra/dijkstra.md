# Algoritmo de Dijkstra e sua Aplicação em Problemas de Menor Caminho

## 1. O que é o Algoritmo de Dijkstra?

O Algoritmo de Dijkstra é um algoritmo de busca de caminho mais curto em grafos, concebido pelo cientista da computação holandês Edsger W. Dijkstra em 1956 e publicado em 1959. Ele é amplamente utilizado para encontrar os caminhos mais curtos entre um nó inicial (fonte) e todos os outros nós em um grafo com arestas de pesos não negativos.

### **Características Principais:**
* **Fonte Única:** Calcula os menores caminhos a partir de um único vértice de origem para todos os outros vértices acessíveis.
* **Pesos Não Negativos:** Funciona apenas em grafos onde os pesos das arestas (custos, distâncias, tempo, etc.) são maiores ou iguais a zero. Se houver pesos negativos, outros algoritmos como Bellman-Ford devem ser usados.
* **Algoritmo Guloso:** Em cada passo, ele seleciona o vértice ainda não visitado com a menor distância conhecida a partir da fonte, garantindo a otimalidade local que leva à otimalidade global.

### **Como Funciona (Passo a Passo com Exemplo):**

Vamos usar o seguinte grafo como exemplo, onde o objetivo é encontrar o caminho mais curto do vértice 'A' para todos os outros vértices:

```
       (2)
    A ----- B
    | \     |
(1) |  (4)  | (3)
    |    \  |
    C ----- D
       (5)
```

* **Vértices:** A, B, C, D
* **Arestas e Pesos:** A-B (2), A-C (1), A-D (4), B-D (3), C-D (5)
* **Origem:** A

---

1.  **Inicialização:**
    * Para cada vértice `v` no grafo, define-se uma distância estimada da fonte para `v`, geralmente infinito (`∞`).
    * A distância da fonte para ela mesma é definida como zero.
    * Define-se também um predecessor para cada vértice, inicialmente nulo.
    * Cria-se um conjunto `S` de vértices já visitados (inicialmente vazio) e uma fila de prioridade `Q` contendo todos os vértices do grafo.

    **Estado Inicial:**
    * `Distâncias`: A: 0, B: ∞, C: ∞, D: ∞
    * `Predecessores`: A: null, B: null, C: null, D: null
    * `Q`: {A, B, C, D}
    * `S`: {}

2.  **Iteração Principal:**
    * Enquanto a fila de prioridade `Q` não estiver vazia:
        * Extrai-se o vértice `u` de `Q` que possui a menor distância estimada.
        * Adiciona-se `u` ao conjunto `S` de vértices visitados.
        * Para cada vizinho `v` de `u`:
            * **Relaxamento:** Se a distância de `u` mais o peso da aresta `(u, v)` for menor do que a distância atual de `v`, então a distância de `v` é atualizada para esse novo valor, e `u` é definido como o predecessor de `v`. Esta etapa é crucial para encontrar caminhos mais curtos.

    ---

    **Iteração 1:**
    * Extrai `u = A` (menor distância: 0). Adiciona A a `S`.
    * Vizinhos de A: B, C, D
        * Para `v = B`: `dist(A) + peso(A,B) = 0 + 2 = 2`. Como `2 < dist(B)` (∞), `dist(B) = 2`, `pred(B) = A`.
        * Para `v = C`: `dist(A) + peso(A,C) = 0 + 1 = 1`. Como `1 < dist(C)` (∞), `dist(C) = 1`, `pred(C) = A`.
        * Para `v = D`: `dist(A) + peso(A,D) = 0 + 4 = 4`. Como `4 < dist(D)` (∞), `dist(D) = 4`, `pred(D) = A`.

    **Estado após Iteração 1:**
    * `Distâncias`: A: 0, B: 2, C: 1, D: 4
    * `Predecessores`: A: null, B: A, C: A, D: A
    * `Q`: {B, C, D} (com distâncias atualizadas)
    * `S`: {A}

    ---

    **Iteração 2:**
    * Extrai `u = C` (menor distância: 1). Adiciona C a `S`.
    * Vizinhos de C: D
        * Para `v = D`: `dist(C) + peso(C,D) = 1 + 5 = 6`. Como `6` **NÃO É MENOR** que `dist(D)` (que é 4), a distância de D não é atualizada.

    **Estado após Iteração 2:**
    * `Distâncias`: A: 0, B: 2, C: 1, D: 4
    * `Predecessores`: A: null, B: A, C: A, D: A
    * `Q`: {B, D}
    * `S`: {A, C}

    ---

    **Iteração 3:**
    * Extrai `u = B` (menor distância: 2). Adiciona B a `S`.
    * Vizinhos de B: D
        * Para `v = D`: `dist(B) + peso(B,D) = 2 + 3 = 5`. Como `5` **NÃO É MENOR** que `dist(D)` (que é 4), a distância de D não é atualizada.

    **Estado após Iteração 3:**
    * `Distâncias`: A: 0, B: 2, C: 1, D: 4
    * `Predecessores`: A: null, B: A, C: A, D: A
    * `Q`: {D}
    * `S`: {A, C, B}

    ---

    **Iteração 4:**
    * Extrai `u = D` (menor distância: 4). Adiciona D a `S`.
    * Vizinhos de D: Nenhum (para este grafo direcionado/arestas já consideradas).

    **Estado após Iteração 4:**
    * `Distâncias`: A: 0, B: 2, C: 1, D: 4
    * `Predecessores`: A: null, B: A, C: A, D: A
    * `Q`: {} (Vazia, o algoritmo termina)
    * `S`: {A, C, B, D}

---

3.  **Resultado:**
    * Ao final, a distância de cada vértice no grafo para a fonte estará calculada, e o caminho pode ser reconstruído seguindo os predecessores de volta daquele vértice até a fonte.

    **Caminhos Mínimos a partir de A:**
    * **A para A:** Distância 0
    * **A para B:** Distância 2. Caminho: A -> B
    * **A para C:** Distância 1. Caminho: A -> C
    * **A para D:** Distância 4. Caminho: A -> D (Note que o caminho via C ou B seria maior: A-C-D = 1+5=6; A-B-D = 2+3=5. O algoritmo manteve o 4 de A-D direto.)

## 2. Aplicação no Exercício Proposto

O exercício pede para encontrar o caminho de menor custo entre duas regiões de uma cidade, que é representada como um grafo direcionado com arestas de pesos positivos. Este é um cenário ideal para a aplicação do Algoritmo de Dijkstra.

### **Detalhes do Problema e Mapeamento para Dijkstra:**

* **Regiões da Cidade:** Correspondem aos **vértices (ou nós)** do grafo.
* **Rotas/Vias:** Correspondem às **arestas** do grafo. Como o grafo é direcionado e as rotas são previamente mapeadas, as arestas têm uma direção (origem -> destino).
* **Custo de Deslocamento:** Corresponde ao **peso** de cada aresta. O problema especifica "pesos positivos", o que valida o uso de Dijkstra.
* **Caminho de Menor Custo:** É exatamente o que o Algoritmo de Dijkstra se propõe a encontrar.

### **Etapas da Solução no Contexto do Exercício:**

1.  **Leitura da Entrada:**
    * Primeiro, lemos o número de vértices (regiões) e construímos a estrutura básica do grafo com seus vértices.
    * Em seguida, para cada via (aresta), lemos a região de origem, a região de destino e o custo, e adicionamos essa aresta ao grafo.
    * Finalmente, lemos a região específica de origem e a região de destino para o qual queremos encontrar o caminho mais curto.

2.  **Inicialização:**
    * Todos os vértices (regiões) têm sua `distancia` definida como infinito (`Integer.MAX_VALUE`) e seu `predecessor` como nulo.
    * A região de origem escolhida tem sua `distancia` definida como `0`.
    * Todos os vértices são adicionados a uma fila de prioridade (ou min-heap), que irá organizar os vértices pela sua distância atual.

3.  **Execução do Dijkstra:**
    * A cada passo, extraímos o vértice `u` (região) com a menor distância da fila de prioridade.
    * Marcamos `u` como visitado.
    * Para cada vizinho `v` (região adjacente) de `u`:
        * Verificamos se `v` já foi visitado. Se não, e se um caminho mais curto para `v` for encontrado passando por `u` (ou seja, `u.distancia + peso(u,v) < v.distancia`), atualizamos a distância de `v` e definimos `u` como seu predecessor.
        * O vértice `v` com a nova distância é então atualizado ou reinserido na fila de prioridade para garantir que a fila sempre contenha as distâncias mais atuais.

4.  **Reconstrução e Exibição do Caminho:**
    * Após o algoritmo terminar, a distância total da origem até a região de destino estará armazenada na propriedade `distancia` do vértice de destino.
    * Para exibir o caminho, começamos do vértice de destino e seguimos seus predecessores (`predecessor`) de volta até a região de origem.
    * O caminho é então invertido para ser exibido na ordem correta (origem para destino), mostrando as regiões e os custos associados a cada trecho.

### **Implementação da Fila de Prioridade (Heap):**

O exercício pede duas abordagens para a fila de prioridade:

1.  **Heap Manual (Conforme Aprendido em Aula):**
    * Isso envolve a implementação de uma estrutura de dados de Min-Heap, onde os vértices são armazenados e organizados com base em suas `distancia`.
    * As operações `insert` (para adicionar vértices) e `extractMin` (para remover o vértice com menor distância) são implementadas usando as propriedades do heap (heapifyUp e heapifyDown).
    * A "atualização" (decrease-key) para um vértice cuja distância foi relaxada é geralmente simulada adicionando o vértice novamente na fila de prioridade com a nova (menor) distância. O algoritmo simplesmente ignorará as entradas "obsoletas" do vértice quando forem extraídas mais tarde, desde que a lógica de "visitado" seja aplicada corretamente.

2.  **`PriorityQueue` da API do Java:**
    * Esta é a abordagem mais simples e eficiente em Java, pois a classe `java.util.PriorityQueue` já implementa um min-heap binário.
    * Basta garantir que a classe `Grafo.Vertice` implemente a **interface** `Comparable<Grafo.Vertice>` e defina o método `compareTo` para comparar os vértices com base em suas distâncias.
    * A `PriorityQueue` cuidará automaticamente da organização e das operações de inserção (`add`) e extração (`poll`) do elemento com menor prioridade (neste caso, a menor distância).
    * A "atualização" (decrease-key) também é tratada implicitamente: quando a distância de um vértice é relaxada, ele é simplesmente adicionado novamente à fila. A `PriorityQueue` permite duplicatas, e o algoritmo de Dijkstra, juntamente com o controle de vértices `visitado`, garantirá que apenas a entrada com a menor distância para um vértice seja processada.

Ao seguir esses passos e utilizando as estruturas de dados adequadas para o grafo e a fila de prioridade, o algoritmo de Dijkstra encontrará eficientemente o caminho de menor custo entre as regiões de origem e destino, resolvendo o problema de logística proposto.