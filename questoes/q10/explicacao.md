## **Enunciado**

Considere a figura e as proposições a seguir
```
       (2)
    C ----- D
    |       |
(1) |       | (1)
    |       |
    A ----- B
       (2)
```

**I** - O grafo possui uma única árvore geradora de custo mínimo

**II** - O grafo possui uma árvore geradora de custo mínimo que, por sua vez, possui dois vértices

**III** - Todas as árvores geradoras de custo mínimo deste grafo possuem três arestas

### Explicação

- **I. O grafo possui uma única árvore geradora de custo mínimo.**
  - ❌ Falsa.

  - O grafo possui mais de uma árvore geradora de custo mínimo.

    - Vamos analisar:

      - As árvores geradoras precisam conectar todos os 4 vértices com 3 arestas (n-1 = 4-1 = 3).

      - As possíveis combinações com menor custo são:

        - A—C (1), B—D (1), C—D (2) → custo 4

        - A—C (1), B—D (1), A—B (2) → custo 4

    - Duas árvores geradoras diferentes com o mesmo custo mínimo → não é única.

- **II. O grafo possui uma árvore geradora de custo mínimo que, por sua vez, possui dois vértices.**
  
  - ❌ Falsa.

  - Por definição, árvore geradora conecta todos os vértices do grafo, ou seja, ela deve ter 4 vértices. Uma árvore com apenas 2 vértices não pode ser geradora para um grafo com 4 vértices.

- **III. Todas as árvores geradoras de custo mínimo deste grafo possuem três arestas.**
  
  - ✅ Verdadeira.

  - Isso é verdade para qualquer grafo conexo com 𝑛 vértices: toda árvore geradora terá sempre 𝑛 − 1 arestas. Como esse grafo tem 4 vértices, todas as árvores geradoras terão 3 arestas.