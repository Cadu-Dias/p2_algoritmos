## **Enunciado**

**I** - A cada iteração, o algoritmo de Kruskal faz uma escolha gulosa

**II** - O grafo induzido pelo conjunto de arestas escolhidas pelo algoritmo de Prim é conexo no final de cada iteração

**III** - A estrutura UNION-FIND somente pode ser utilizada para representar grafos conexos

É correto apenas o que se afirma em:

### Explicação

-   **Proposição I:
"A cada iteração, o algoritmo de Kruskal faz uma escolha gulosa."**

    -   ✅ Verdadeira.
  
    -   O algoritmo de Kruskal é um algoritmo guloso. Ele ordena as arestas pelo peso e, a cada iteração, escolhe a menor aresta que não forma ciclo com as já escolhidas, seguindo a ideia de "melhor escolha local".

- **Proposição II:
"O grafo induzido pelo conjunto de arestas escolhidas pelo algoritmo de Prim é conexo no final de cada iteração."**

    - ✅ Verdadeira.
  
    - O algoritmo de Prim constrói uma árvore geradora mínima a partir de um único vértice inicial, adicionando sempre uma aresta que conecta um novo vértice ao conjunto de vértices já conectados. Em cada iteração, o subconjunto de vértices e arestas já escolhidas forma um subgrafo conexo.
  

- **Proposição III:
"A estrutura UNION-FIND somente pode ser utilizada para manipular grafos conexos."**

   - ❌ Falsa.
    
    - A estrutura Union-Find (ou Disjoint Set Union) é utilizada para manter o controle de componentes conexos, principalmente em algoritmos como Kruskal. Porém, ela funciona também em grafos não conexos, tratando cada componente separadamente. Assim, não é exclusiva para grafos conexos.