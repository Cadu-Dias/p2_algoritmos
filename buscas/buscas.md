## 🔎 Busca em Profundidade (DFS - Depth-First Search)

### Como funciona?
1.  Começa em um vértice inicial.
2.  Vai explorando o mais fundo possível antes de voltar (backtracking).
3.  Ou seja, visita um vizinho, depois o vizinho desse vizinho, e assim por diante, até não conseguir ir mais longe.
4.  Quando não tem mais vértices para visitar na profundidade, volta para o último vértice que tinha vizinhos não visitados e continua.

### Características:
* Usa uma pilha (explícita ou pela recursão).
* Explora primeiro os caminhos mais profundos.
* Ideal para tarefas como:
    * Encontrar caminhos
    * Detectar ciclos
    * Explorar componentes conectados

### Visual simplificado:
Se o grafo fosse um labirinto, DFS iria "mergulhar" num caminho até o fim antes de tentar outro.

## 🔎 Busca em Largura (BFS - Breadth-First Search)

### Como funciona?
1.  Começa em um vértice inicial.
2.  Visita todos os vizinhos diretos primeiro (camada 1).
3.  Depois visita os vizinhos dos vizinhos (camada 2).
4.  E assim sucessivamente, camada por camada.

### Características:
* Usa uma fila para armazenar os vértices a serem visitados.
* Explora os vértices em ordem crescente de distância (número mínimo de arestas).
* Ideal para:
    * Encontrar o caminho mais curto em grafos não ponderados
    * Verificar níveis/distâncias dos vértices

### Visual simplificado:
No labirinto, BFS exploraria todas as salas que ficam a um passo de distância antes de ir para as que estão a dois passos, etc.

## Comparação com um exemplo simples

Considere o seguinte grafo, onde cada letra é um vértice e as linhas são arestas:

```
  A
 / \
B   C
|   |
D   E
```

### DFS começando em A:
1.  Visita A
2.  Vai para B
3.  De B, vai para D
4.  D não tem novos vizinhos, volta para B
5.  B não tem mais vizinhos, volta para A
6.  Vai para C
7.  De C, vai para E

### BFS começando em A:
1.  Visita A
2.  Visita todos os vizinhos de A: B e C
3.  Visita todos os vizinhos de B e C que ainda não foram visitados: D (vizinho de B) e E (vizinho de C)