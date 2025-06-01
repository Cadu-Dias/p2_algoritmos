## O que é um grafo?
Um grafo é uma estrutura matemática usada para representar conexões ou relações entre objetos. Ele é formado por:
* **Vértices (ou nós)**: os objetos.
* **Arestas**: as conexões entre os vértices.

## O que é um grafo simples?
Um grafo simples tem duas restrições principais:
* **Sem laços**: uma aresta não pode ligar um vértice a ele mesmo.
* **Sem arestas múltiplas**: só pode existir uma aresta entre dois vértices.

Ou seja, entre dois vértices quaisquer, há no máximo uma conexão, e essa conexão não pode ser do tipo "vértice A ligado a ele mesmo".

## O que é um grafo não direcionado?
Um grafo não direcionado é aquele em que as arestas não têm direção. Ou seja, se existe uma aresta entre os vértices A e B, ela vale tanto de A para B quanto de B para A.

**Representação:**
* Aresta entre A e B: `{A, B}` ou `A—B`.
* Diferente de um grafo direcionado, onde a aresta tem direção (`A → B ≠ B → A`).

## A função de incidência diz: "Esta aresta liga quais vértices?"

### Exemplo:
Se tivermos um grafo com:
* **Vértices**: $V = \{A, B, C\}$
* **Arestas**: $E = \{e_1, e_2\}$

E:
* $\phi(e_1) = \{A, B\}$
* $\phi(e_2) = \{B, C\}$

Então:
* A aresta $e_1$ conecta os vértices A e B.
* A aresta $e_2$ conecta os vértices B e C.

### 🔹 Importância
A função de incidência é fundamental porque ela define a topologia do grafo — ou seja, como os vértices estão ligados.

Ela também é usada em:
* Representações matriciais (como a matriz de incidência)
* Provas e algoritmos em Teoria dos Grafos

## Exemplo 1: Amizades em uma rede social pequena
Imagine 4 pessoas: Ana (A), Bruno (B), Carla (C) e Daniel (D).
Vamos supor as seguintes amizades:
* Ana é amiga de Bruno
* Ana é amiga de Carla
* Bruno é amigo de Daniel

Essas relações são mútuas (não direcionadas) e não há amizade de alguém com si mesmo (sem laço), nem duplicidade de amizade.

### 📎 Representação como grafo:
* **Vértices**: A, B, C, D
* **Arestas**:
    * `{A, B}`
    * `{A, C}`
    * `{B, D}`

```
     A
    / \
    B  C
    |
    D
```