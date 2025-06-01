**Entrada para o `main`:** `java menor_caminho.Grafo 7 0 1 0 2 0 5 1 3 2 4 4 6 5 6`

**Interpretação:**

* `vertices = 7` (vértices de 0 a 6)

* Arestas: `(0,1), (0,2), (0,5), (1,3), (2,4), (4,6), (5,6)`



### Grafo Representado:



```

    0 ----- 1 ----- 3

    | \     |

    |  \    |

    |   \   |

    2 ----- 4 ----- 6

    \       /

     \     /

      \   /

        5

```



### Teste de Mesa do Método `main`:



| Linha | Variável | Valor | Observações |

| :---- | :------- | :---- | :---------- |

| `main` | `args` | `{"7", "0", "1", "0", "2", "0", "5", "1", "3", "2", "4", "4", "6", "5", "6"}` | Argumentos de linha de comando. |

| 104 | `args.length` | `15` | `15 >= 1` é verdadeiro. |

| 108 | `numVertices` | `7` | `Integer.parseInt("7")`. |

| 109 | `grafo` | `new Grafo(7)` | Cria o grafo com 7 vértices e listas de adjacência vazias. |

| 113 | `(args.length - 1) % 2` | `(15 - 1) % 2 = 14 % 2 = 0` | `0 != 0` é falso. |

| 119 | `i` | `1` | Início do loop para adicionar arestas. |

| 119 | `i < args.length - 1` | `1 < 14` é verdadeiro. |

| 120 | `u` | `0` | `Integer.parseInt(args[1])`. |

| 121 | `v` | `1` | `Integer.parseInt(args[2])`. |

| 122 | `grafo.adicionarAresta(0, 1)` | | `adjacencias[0].add(1)`, `adjacencias[1].add(0)`. |

| 119 | `i` | `3` | `i += 2`. |

| 119 | `i < args.length - 1` | `3 < 14` é verdadeiro. |

| 120 | `u` | `0` | `Integer.parseInt(args[3])`. |

| 121 | `v` | `2` | `Integer.parseInt(args[4])`. |

| 122 | `grafo.adicionarAresta(0, 2)` | | `adjacencias[0].add(2)`, `adjacencias[2].add(0)`. |

| ... | ... | ... | (Continua adicionando as arestas restantes: (0,5), (1,3), (2,4), (4,6), (5,6)) |

| 119 | `i` | `13` | `i += 2`. |

| 119 | `i < args.length - 1` | `13 < 14` é verdadeiro. |

| 120 | `u` | `5` | `Integer.parseInt(args[13])`. |

| 121 | `v` | `6` | `Integer.parseInt(args[14])`. |

| 122 | `grafo.adicionarAresta(5, 6)` | | `adjacencias[5].add(6)`, `adjacencias[6].add(5)`. |

| 119 | `i` | `15` | `i += 2`. |

| 119 | `i < args.length - 1` | `15 < 14` é falso. Loop encerra. |

| 126 | `System.out.println(...)` | | Imprime "Calculando e exibindo...". |

| 129 | `origem` | `0` | Início do loop `for` externo. |

| 132 | `destino` | `0` | Início do loop `for` interno. |

| 133 | `origem == destino` | `0 == 0` é verdadeiro. |

| 134 | `System.out.printf(...)` | | Imprime "Caminho de 0 até 0: 0 (Distância: 0)". |

| 135 | `continue` | | Pula para a próxima iteração de `destino`. |

| 132 | `destino` | `1` | |

| 133 | `origem == destino` | `0 == 1` é falso. |

| 138 | `grafo.menorCaminho(0, 1)` | | Chama o BFS. |



---



### Teste de Mesa do Método `menorCaminho(0, 1)`:



**Estado inicial dentro de `menorCaminho(0, 1)`:**

* `origem = 0`, `destino = 1`

* `distancias = [0, 0, 0, 0, 0, 0, 0]`

* `visitados = [F, F, F, F, F, F, F]`

* `predecessores = [-1, -1, -1, -1, -1, -1, -1]`

* `fila = []`



| Linha | Variável/Estado | Valor | Observações |

| :---- | :-------------- | :---- | :---------- |

| `menorCaminho` | `for (i=0..6)` | `predecessores` | `[-1, -1, -1, -1, -1, -1, -1]` (todos inicializados com -1) |

| 62 | `fila.offer(0)` | `fila = [0]` | Adiciona origem à fila. |

| 63 | `distancias[0]` | `0` | Distância da origem para si mesma. |

| 64 | `visitados[0]` | `T` | Marca origem como visitada. |

| 65 | `!fila.isEmpty()` | `T` | `fila` não está vazia. |

| 66 | `atual` | `0` | `fila.poll()`. `fila = []`. |

| 69 | `atual == destino` | `0 == 1` é falso. |

| 73 | `vizinho` | `1` | Primeiro vizinho de `0` (`adjacencias[0]` contém `[1, 2, 5]`). |

| 74 | `!visitados[1]` | `!F` é `T`. | `visitados[1]` é false. |

| 75 | `fila.offer(1)` | `fila = [1]` | Adiciona `1` à fila. |

| 76 | `visitados[1]` | `T` | Marca `1` como visitado. `visitados = [T, T, F, F, F, F, F]`. |

| 77 | `distancias[1]` | `1` | `distancias[0] + 1 = 0 + 1`. `distancias = [0, 1, 0, 0, 0, 0, 0]`. |

| 78 | `predecessores[1]` | `0` | `predecessores = [-1, 0, -1, -1, -1, -1, -1]`. |

| 73 | `vizinho` | `2` | Segundo vizinho de `0`. |

| 74 | `!visitados[2]` | `!F` é `T`. |

| 75 | `fila.offer(2)` | `fila = [1, 2]` | |

| 76 | `visitados[2]` | `T` | `visitados = [T, T, T, F, F, F, F]`. |

| 77 | `distancias[2]` | `1` | `distancias[0] + 1`. `distancias = [0, 1, 1, 0, 0, 0, 0]`. |

| 78 | `predecessores[2]` | `0` | `predecessores = [-1, 0, 0, -1, -1, -1, -1]`. |

| 73 | `vizinho` | `5` | Terceiro vizinho de `0`. |

| 74 | `!visitados[5]` | `!F` é `T`. |

| 75 | `fila.offer(5)` | `fila = [1, 2, 5]` | |

| 76 | `visitados[5]` | `T` | `visitados = [T, T, T, F, F, T, F]`. |

| 77 | `distancias[5]` | `1` | `distancias[0] + 1`. `distancias = [0, 1, 1, 0, 0, 1, 0]`. |

| 78 | `predecessores[5]` | `0` | `predecessores = [-1, 0, 0, -1, -1, 0, -1]`. |

| 65 | `!fila.isEmpty()` | `T` | `fila` não está vazia. |

| 66 | `atual` | `1` | `fila.poll()`. `fila = [2, 5]`. |

| 69 | `atual == destino` | `1 == 1` é `T`. | **Condição de destino alcançado!** |

| 70 | `System.out.print(...)` | | Imprime "Caminho de 0 até 1: ". |

| 71 | `imprimirCaminho(0, 1, predecessores)` | | Chama a função para imprimir o caminho. |



---



### Teste de Mesa do Método `imprimirCaminho(0, 1, predecessores)`:



**Estado inicial dentro de `imprimirCaminho(0, 1, [-1, 0, 0, -1, -1, 0, -1])`:**

* `origem = 0`, `destino = 1`

* `caminho = []`

* `atual = 1`



| Linha | Variável/Estado | Valor | Observações |

| :---- | :-------------- | :---- | :---------- |

| 89 | `atual` | `1` | Início do loop `while`. |

| 90 | `atual != -1` | `1 != -1` é `T`. | |

| 91 | `caminho.addFirst(1)` | `caminho = [1]` | Adiciona `1` ao início. |

| 92 | `atual` | `0` | `predecessores[1]` é `0`. |

| 90 | `atual != -1` | `0 != -1` é `T`. | |

| 91 | `caminho.addFirst(0)` | `caminho = [0, 1]` | Adiciona `0` ao início. |

| 92 | `atual` | `-1` | `predecessores[0]` é `-1`. |

| 90 | `atual != -1` | `-1 != -1` é `F`. | Loop encerra. |

| 96 | `caminho.getFirst()` | `0` | |

| 96 | `caminho.getFirst() != origem` | `0 != 0` é `F`. | Condição é falsa. |

| 100 | `i` | `0` | Início do loop `for` para imprimir. |

| 100 | `i < caminho.size()` | `0 < 2` é `T`. | |

| 101 | `System.out.print(caminho.get(0))` | | Imprime `0`. Saída atual: "Caminho de 0 até 1: 0" |

| 102 | `i < caminho.size() - 1` | `0 < 1` é `T`. | |

| 104 | `System.out.print(" -> (1) ")` | | Imprime " -> (1) ". Saída atual: "Caminho de 0 até 1: 0 -> (1) " |

| 100 | `i` | `1` | `i++`. |

| 100 | `i < caminho.size()` | `1 < 2` é `T`. | |

| 101 | `System.out.print(caminho.get(1))` | | Imprime `1`. Saída atual: "Caminho de 0 até 1: 0 -> (1) 1" |

| 102 | `i < caminho.size() - 1` | `1 < 1` é `F`. | |

| 100 | `i` | `2` | `i++`. |

| 100 | `i < caminho.size()` | `2 < 2` é `F`. | Loop encerra. |

| 71 (volta para `menorCaminho`) | `System.out.printf(...)` | | Imprime " (Distância: 1)\n". Saída final: "Caminho de 0 até 1: 0 -> (1) 1 (Distância: 1)" |

| 72 | `return` | | Termina a execução de `menorCaminho(0, 1)`. |



---



### Continuação do Teste de Mesa do Método `main`:



Depois de `menorCaminho(0, 1)` retornar:



| Linha | Variável | Valor | Observações |

| :---- | :------- | :---- | :---------- |

| 132 | `destino` | `2` | Próxima iteração do loop interno. |

| 133 | `origem == destino` | `0 == 2` é falso. | |

| 138 | `grafo.menorCaminho(0, 2)` | | Chama BFS. |



... (Este processo se repetirá para `(0,2)`, `(0,3)`, `(0,4)`, `(0,5)`, `(0,6)`, e depois para `(1,0)`, `(1,1)`, `(1,2)`, etc., até que todos os pares origem-destino tenham sido processados).



**Exemplo para `menorCaminho(0, 5)`:**



* Após a BFS, `distancias[5]` seria `1` e `predecessores[5]` seria `0`.

* A `imprimirCaminho(0, 5, predecessores)` reconstruiria `0 -> 5`.

* Saída: `Caminho de 0 até 5: 0 -> (1) 5 (Distância: 1)`



**Exemplo para `menorCaminho(0, 6)`:**



BFS para `(0,6)`:

* `origem = 0`, `destino = 6`

* `fila = [0]`

* `distancias = [0, inf, inf, inf, inf, inf, inf]`

* `predecessores = [-1, -1, -1, -1, -1, -1, -1]`



1.  **Poll 0**:

    * Add 1, 2, 5 to `fila`.

    * `distancias[1]=1`, `predecessores[1]=0`

    * `distancias[2]=1`, `predecessores[2]=0`

    * `distancias[5]=1`, `predecessores[5]=0`

    * `fila = [1, 2, 5]`



2.  **Poll 1**: (`atual == 1`, `destino == 6` é falso)

    * Vizinho de 1 é 3 (0 já visitado).

    * Add 3 to `fila`.

    * `distancias[3]=distancias[1]+1=2`, `predecessores[3]=1`

    * `fila = [2, 5, 3]`



3.  **Poll 2**: (`atual == 2`, `destino == 6` é falso)

    * Vizinho de 2 é 4 (0 já visitado).

    * Add 4 to `fila`.

    * `distancias[4]=distancias[2]+1=2`, `predecessores[4]=2`

    * `fila = [5, 3, 4]`



4.  **Poll 5**: (`atual == 5`, `destino == 6` é falso)

    * Vizinho de 5 é 6 (0 já visitado).

    * Add 6 to `fila`.

    * `distancias[6]=distancias[5]+1=2`, `predecessores[6]=5`

    * `fila = [3, 4, 6]`



5.  **Poll 3**: (`atual == 3`, `destino == 6` é falso)

    * Vizinhos de 3: 1 (já visitado). Nada a fazer.

    * `fila = [4, 6]`



6.  **Poll 4**: (`atual == 4`, `destino == 6` é falso)

    * Vizinhos de 4: 2 (já visitado), 6 (já visitado). Nada a fazer.

    * `fila = [6]`



7.  **Poll 6**: (`atual == 6`, `destino == 6` é **VERDADEIRO**)

    * **Imprime caminho:** `imprimirCaminho(0, 6, predecessores)`

        * `caminho = []`, `atual = 6`

        * Loop:

            * `caminho.addFirst(6)` -> `[6]`

            * `atual = predecessores[6] = 5`

            * `caminho.addFirst(5)` -> `[5, 6]`

            * `atual = predecessores[5] = 0`

            * `caminho.addFirst(0)` -> `[0, 5, 6]`

            * `atual = predecessores[0] = -1`

            * Loop termina.

        * Imprime: `0 -> (1) 5 -> (1) 6`

    * Distância: `distancias[6]` que é `2`.

    * Saída final para este par: `Caminho de 0 até 6: 0 -> (1) 5 -> (1) 6 (Distância: 2)`

    * `return`.



Este teste de mesa demonstra como o BFS encontra o caminho mais curto e como o array `predecessores` é usado para reconstruí-lo e imprimi-lo no formato desejado. O processo se repete para todos os pares de origem e destino, garantindo que o Exercício 2 seja cumprido.