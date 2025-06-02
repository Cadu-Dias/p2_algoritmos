**Enunciado:**

Considere o grafo e as proposições a seguir.

![Grafo da Questão 1](image_847130.png)

I As suas matrizes de incidência e de adjacência possuem o mesmo número de células.
II Ele pode ser representado por uma lista de adjacências.
III O cruzamento de arestas mostra que o grafo não é planar.

É correto apenas o que se afirma em

( ) I
(X) II
( ) III
( ) I e II
( ) II e III

**Resposta correta:** I e II

---

**Explicação:**

A questão aborda conceitos fundamentais de representação e propriedades de grafos. Vamos analisar cada proposição:

* **Proposição I: "As suas matrizes de incidência e de adjacência possuem o mesmo número de células."**
    * **Matriz de Adjacência:** Se um grafo tem `V` vértices, sua matriz de adjacência tem `V x V` células.
    * **Matriz de Incidência:** Se um grafo tem `V` vértices e `E` arestas, sua matriz de incidência tem `V x E` células.
    * Para que ambas as matrizes tenham o mesmo número de células, seria necessário que `V * V = V * E`, o que implica `V = E` (assumindo `V > 0`). Contando os vértices e arestas no grafo da imagem:
        * Vértices (Nós): 5
        * Arestas: 6
    * Como `V (5) ≠ E (6)`, as matrizes não terão o mesmo número de células. Portanto, a proposição I é **FALSA**.

* **Proposição II: "Ele pode ser representado por uma lista de adjacências."**
    * Todo grafo, independentemente de sua complexidade, pode ser representado por uma lista de adjacências. Uma lista de adjacências é uma forma padrão e flexível de representar grafos, onde cada vértice tem uma lista dos vértices aos quais ele está conectado. Isso é uma verdade universal na teoria dos grafos. Portanto, a proposição II é **VERDADEIRA**.

* **Proposição III: "O cruzamento de arestas mostra que o grafo não é planar."**
    * Um grafo é planar se ele pode ser desenhado em um plano sem que suas arestas se cruzem. O fato de um desenho específico do grafo *ter* arestas se cruzando não significa automaticamente que o grafo não é planar. É preciso tentar redesenhá-lo.
    * O grafo dado é um $K_{3,2}$ (um grafo bipartido completo com 3 vértices de um lado e 2 do outro) com uma aresta a mais, ou é um $K_5$ (grafo completo com 5 vértices) sem algumas arestas. Contando os vértices (5) e arestas (6), ele se parece com um $K_5$ com 4 arestas removidas.
    * Um dos critérios para não planaridade é que o grafo contenha um subgrafo que é uma subdivisão de $K_5$ ou $K_{3,3}$ (Teorema de Kuratowski). O grafo mostrado na imagem possui 5 vértices e 6 arestas. Grafos com 5 ou menos vértices são planar, a menos que contenham um $K_5$ como subgrafo, o que não é o caso aqui.
    * O grafo desenhado, apesar das arestas cruzadas, **pode ser redesenhado** para ser planar. Por exemplo, pode-se mover um dos vértices "internos" para fora da região formada pelo ciclo maior, desfazendo os cruzamentos.
    * Portanto, a proposição III é **FALSA**.

**Conclusão:** Apenas a proposição II é verdadeira. A resposta correta marcada na questão estava incorreta, pois a resposta final deve ser "I e II" segundo o gabarito. No entanto, com base na análise, a proposição I é falsa, e a proposição III também é falsa. **Houve um erro no gabarito apresentado, pois apenas a II é verdadeiramente correta.** Se o gabarito indica "I e II" como correto, a proposição I foi considerada verdadeira por algum motivo que não se alinha com a definição de matrizes. Reafirmando, a quantidade de células das matrizes de adjacência ($V^2$) e incidência ($V \times E$) só é igual se $V = E$. Como $V=5$ e $E=6$, a proposição I é falsa.



**Enunciado:**

Assinale (V)erdadeiro ou (F)also.

Ainda que um programa tenha tempo de execução $O(n^2)$, ele pode executar em tempo linear, dependendo da instância do problema sobre a qual estiver operando.

(X) Verdadeiro
( ) Falso

**Resposta correta:** Verdadeiro

---

**Explicação:**

Esta questão aborda a diferença entre a **complexidade de pior caso (Big O - $O$)** e o **comportamento de um algoritmo em instâncias específicas** ou no melhor caso.

* A notação **Big O ($O(n^2)$)** descreve o **limite superior assintótico** do tempo de execução de um algoritmo. Isso significa que, no pior caso, ou para entradas suficientemente grandes, o tempo de execução do algoritmo não crescerá mais rápido do que uma função quadrática de $n$.
* No entanto, um algoritmo que tem uma complexidade de pior caso $O(n^2)$ pode ter um desempenho muito melhor para **certas instâncias de entrada** (casos médios ou melhores casos).
* Por exemplo, um algoritmo de ordenação como o Bubble Sort tem complexidade de pior caso $O(n^2)$. Contudo, se o array de entrada já estiver quase ordenado ou completamente ordenado, ele pode executar em tempo $O(n)$ (linear) no melhor caso. A mesma lógica se aplica ao QuickSort, que tem pior caso $O(n^2)$ mas um caso médio de $O(n \log n)$.

Portanto, é **Verdadeiro** que um programa com tempo de execução $O(n^2)$ (sua complexidade de pior caso) pode, de fato, executar em tempo linear (ou seja, $O(n)$) para instâncias específicas do problema.



**Enunciado:**

Considere a figura e as proposições a seguir.

![Grafo da Questão 3](image_846973.png)

I Trata-se de uma estrela.
II Trata-se de um grafo conexo.
III Trata-se de um grafo bipartido.

É correto apenas o que se afirma em

( ) I e II
(X) II e III
( ) II
( ) I
( ) III

**Resposta correta:** II e III

---

**Explicação:**

Vamos analisar cada proposição para o grafo dado, que possui dois conjuntos de vértices claramente definidos (x1, x2, x3 e y1, y2, y3) e arestas apenas entre vértices de conjuntos diferentes.

* **Proposição I: "Trata-se de uma estrela."**
    * Um grafo estrela é um tipo especial de árvore onde existe um vértice central conectado a todos os outros vértices, e os outros vértices não possuem conexões entre si. No grafo apresentado, não há um único vértice central que se conecta a todos os outros. Cada vértice do conjunto 'x' está conectado a todos os vértices do conjunto 'y', e vice-versa. Portanto, não é um grafo estrela. A proposição I é **FALSA**.

* **Proposição II: "Trata-se de um grafo conexo."**
    * Um grafo é conexo se existe um caminho entre cada par de vértices. No grafo dado, é possível ir de qualquer vértice para qualquer outro. Por exemplo, de x1 você pode ir para y1, y2 ou y3. De y1, você pode ir para x1, x2 ou x3. Através dessas conexões, todos os vértices estão interligados. Portanto, a proposição II é **VERDADEIRA**.

* **Proposição III: "Trata-se de um grafo bipartido."**
    * Um grafo é bipartido se seus vértices podem ser divididos em dois conjuntos disjuntos, digamos `U` e `V`, de modo que cada aresta conecta um vértice em `U` a um vértice em `V`. Não há arestas que conectem vértices dentro do mesmo conjunto. No grafo dado, podemos facilmente dividir os vértices em dois conjuntos: `{x1, x2, x3}` e `{y1, y2, y3}`. Todas as arestas conectam um vértice do primeiro conjunto a um vértice do segundo conjunto, e não há arestas dentro de `{x1, x2, x3}` nem dentro de `{y1, y2, y3}`. Este é, na verdade, um grafo bipartido completo $K_{3,3}$. Portanto, a proposição III é **VERDADEIRA**.

**Conclusão:** As proposições II e III são verdadeiras. A resposta marcada ("II e III") está correta.


**Enunciado:**

Considere as funções e as proposições a seguir.

$f(n) = 5n^2 + 3n + 7$
$g(n) = n^2$

I $f(n) \in O(g(n))$
II $f(n) \in \Omega(n \log n)$
III $f(n) \in \Theta(n^3)$

É correto apenas o que se afirma em

( ) III
(X) I e II

**Resposta correta:** I e II

---

**Explicação:**

Esta questão trata das notações assintóticas para analisar a complexidade de algoritmos: Big O ($O$), Omega ($\Omega$), e Theta ($\Theta$).

* **Notação Big O ($O$): Limite Superior Assintótico**
    * $f(n) \in O(g(n))$ significa que $f(n)$ cresce no máximo tão rápido quanto $g(n)$, para $n$ suficientemente grande. Formalmente, existem constantes $c > 0$ e $n_0 \ge 0$ tal que $0 \le f(n) \le c \cdot g(n)$ para todo $n \ge n_0$.
    * Para $f(n) = 5n^2 + 3n + 7$ e $g(n) = n^2$:
        * Quando $n$ é grande, o termo dominante em $f(n)$ é $5n^2$.
        * Podemos encontrar um $c$ e $n_0$ tal que $5n^2 + 3n + 7 \le c \cdot n^2$. Por exemplo, se $c=6$ e $n_0$ for grande o suficiente, a desigualdade se mantém.
        * Portanto, $f(n) \in O(n^2)$ é **VERDADEIRA**.

* **Notação Omega ($\Omega$): Limite Inferior Assintótico**
    * $f(n) \in \Omega(g(n))$ significa que $f(n)$ cresce no mínimo tão rápido quanto $g(n)$, para $n$ suficientemente grande. Formalmente, existem constantes $c > 0$ e $n_0 \ge 0$ tal que $0 \le c \cdot g(n) \le f(n)$ para todo $n \ge n_0$.
    * Para $f(n) = 5n^2 + 3n + 7$ e $g(n) = n \log n$:
        * A função $n^2$ cresce significativamente mais rápido que $n \log n$.
        * Podemos encontrar um $c$ e $n_0$ tal que $c \cdot (n \log n) \le 5n^2 + 3n + 7$. Isso é verdade, pois $n^2$ domina $n \log n$.
        * Portanto, $f(n) \in \Omega(n \log n)$ é **VERDADEIRA**.

* **Notação Theta ($\Theta$): Limite Superior e Inferior Assintótico (Crescimento Exato)**
    * $f(n) \in \Theta(g(n))$ significa que $f(n)$ cresce à mesma taxa que $g(n)$, para $n$ suficientemente grande. Isso ocorre se $f(n) \in O(g(n))$ E $f(n) \in \Omega(g(n))$.
    * Para $f(n) = 5n^2 + 3n + 7$ e $g(n) = n^3$:
        * A função $n^2$ não cresce na mesma taxa que $n^3$; $n^3$ cresce mais rápido.
        * Portanto, $f(n)$ não é $\Theta(n^3)$. A proposição III é **FALSA**. (Na verdade, $f(n) \in \Theta(n^2)$).

**Conclusão:** As proposições I e II são verdadeiras. A resposta marcada ("I e II") está correta.


**Enunciado:**

Considere o grafo e as proposições a seguir.

$V(G) = \{v_1, v_2, v_3, v_4\}$
$E(G) = \{e_1, e_2, e_3, e_4\}$
$\phi(e_1) = \{v_1, v_2\}$
$\phi(e_2) = \{v_2, v_3\}$
$\phi(e_3) = \{v_3, v_1\}$
$\phi(e_4) = \{v_3, v_4\}$

I Trata-se de um grafo conexo.
II Trata-se de um grafo bipartido.
III O grafo possui um ciclo.

É correto apenas o que se afirma em

( ) II
( ) I e II
( ) III
( ) I e III
(X) I

**Resposta correta:** III

---

**Explicação:**

Vamos analisar a estrutura do grafo a partir das definições de vértices e arestas dadas pela função de incidência $\phi$:

* **Vértices:** $v_1, v_2, v_3, v_4$
* **Arestas:**
    * $e_1$ conecta $v_1$ e $v_2$ ($v_1 - v_2$)
    * $e_2$ conecta $v_2$ e $v_3$ ($v_2 - v_3$)
    * $e_3$ conecta $v_3$ e $v_1$ ($v_3 - v_1$)
    * $e_4$ conecta $v_3$ e $v_4$ ($v_3 - v_4$)

Agora, vamos avaliar cada proposição:

* **Proposição I: "Trata-se de um grafo conexo."**
    * Um grafo é conexo se existe um caminho entre cada par de vértices.
    * Temos as conexões: $v_1 - v_2$, $v_2 - v_3$, $v_3 - v_1$. Isso forma um ciclo entre $v_1, v_2, v_3$.
    * A aresta $e_4$ conecta $v_3$ a $v_4$.
    * Portanto, $v_4$ está conectado a $v_3$, que por sua vez está conectado a $v_1$ e $v_2$. Assim, todos os vértices estão interligados. A proposição I é **VERDADEIRA**.

* **Proposição II: "Trata-se de um grafo bipartido."**
    * Um grafo bipartido é aquele onde os vértices podem ser divididos em dois conjuntos disjuntos (U e V) de forma que todas as arestas conectem um vértice de U a um vértice de V (e nenhuma aresta conecte vértices dentro do mesmo conjunto).
    * Um grafo contém um ciclo ímpar se, e somente se, ele não é bipartido.
    * As arestas $e_1, e_2, e_3$ formam um ciclo de comprimento 3 ($v_1 - v_2 - v_3 - v_1$). Um ciclo de comprimento 3 é um ciclo ímpar.
    * Como o grafo contém um ciclo ímpar, ele **não pode ser bipartido**. A proposição II é **FALSA**.

* **Proposição III: "O grafo possui um ciclo."**
    * Como observado na análise da Proposição II, as arestas $e_1 = \{v_1, v_2\}$, $e_2 = \{v_2, v_3\}$ e $e_3 = \{v_3, v_1\}$ formam claramente um ciclo: $v_1 \to v_2 \to v_3 \to v_1$. A proposição III é **VERDADEIRA**.

**Conclusão:** As proposições I e III são verdadeiras, e a proposição II é falsa. O gabarito indica "III" como resposta correta, e a sua marcação foi "I". Há uma discordância entre a análise e o gabarito. Com base na análise, se apenas uma opção é correta, então a questão está ambígua ou o gabarito está errado. Se a pergunta é "É correto *apenas* o que se afirma em", e tanto I quanto III são verdadeiras, então a questão ou gabarito tem problema. **Se a intenção é que só uma seja a resposta, então o enunciado "É correto apenas o que se afirma em" está mal formulado, ou a questão esperava uma interpretação mais específica. Ambos I e III são factualmente verdadeiros.** No entanto, a presença de um ciclo é uma característica fundamental do grafo, enquanto a conectividade (I) é uma propriedade mais geral. Se for para escolher apenas uma, a característica do ciclo é mais específica e detectável.

**Revisão do Gabarito:** Se o gabarito indica apenas "III", então a proposição I foi considerada falsa por alguma razão. A única forma de o grafo *não* ser conexo é se algum vértice estivesse isolado ou em um componente separado. Mas $v_1, v_2, v_3$ formam um ciclo, e $v_4$ está conectado a $v_3$. Assim, todos os 4 vértices estão em um único componente conectado. Portanto, I **é verdadeira**. **Há um erro no gabarito, pois I e III são verdadeiras.**


**Enunciado:**

Considere o programa e as proposições a seguir.

```java
public class ProcessaVetor {
    public static void main(String[] args) {
        int[] vetor = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            vetor[i] = Integer.parseInt(args[i]);
        } // Loop de leitura (Loop 1)

        int soma = 0;
        for (int i = 0; i < vetor.length; i++) {
            soma += vetor[i];
        } // Loop de soma (Loop 2)

        int maximo = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > maximo) {
                maximo = vetor[i];
            }
        } // Loop de máximo (Loop 3)

        System.out.println("Soma dos elementos: " + soma);
        System.out.println("Maior elemento: " + maximo);
    }
}
```

I A complexidade desse programa é $O(n)$
II A complexidade desse programa é $\Omega(n)$
III A complexidade desse programa é $\Theta(1)$

É correto apenas o que se afirma em

( ) I e II
( ) II e III
(X) II
( ) III
( ) I

Resposta correta: I e II

Explicação:

Para determinar a complexidade de tempo de um programa, analisamos as partes que mais contribuem para o tempo de execução à medida que o tamanho da entrada (n, que aqui é args.length) cresce.

O programa contém três loops for principais:

Loop de leitura (linhas 4-6): Percorre o vetor uma vez para converter strings em inteiros. Se o tamanho do vetor é n, este loop executa n vezes. Complexidade: $O(n)$.
Loop de soma (linhas 9-11): Percorre o vetor uma vez para somar os elementos. Executa n vezes. Complexidade: $O(n)$.
Loop de máximo (linhas 13-16): Percorre o vetor uma vez (quase, começa em i=1) para encontrar o maior elemento. Executa aproximadamente n vezes. Complexidade: $O(n)$.
Como os loops são sequenciais (um após o outro), a complexidade total do programa é a soma das complexidades de cada loop: $O(n) + O(n) + O(n) = O(n)$.

Agora, vamos avaliar as proposições usando as notações assintóticas:

Proposição I: "A complexidade desse programa é $O(n)$"

Como a complexidade total é dominada por termos lineares ($n$), o programa tem um limite superior de crescimento linear. Portanto, $O(n)$ é VERDADEIRA.
Proposição II: "A complexidade desse programa é $\Omega(n)$"

Para qualquer entrada de tamanho n, o programa precisa, no mínimo, percorrer os n elementos do vetor para ler, somar e encontrar o máximo. Assim, o tempo de execução sempre será, no mínimo, proporcional a n. Portanto, $\Omega(n)$ é VERDADEIRA.
Proposição III: "A complexidade desse programa é $\Theta(1)$"

$\Theta(1)$ significa tempo constante, ou seja, o tempo de execução não depende do tamanho da entrada. Claramente, o programa percorre o vetor (cuja o tamanho n pode variar) várias vezes, então seu tempo de execução depende de n. Portanto, $\Theta(1)$ é FALSA.
Conclusão: As proposições I e II são verdadeiras. A complexidade exata (Theta) do programa é $\Theta(n)$, o que implica que ela também é $O(n)$ e $\Omega(n)$. O gabarito indica "I e II" como a resposta correta, e a sua marcação estava errada.

---

### **Questão 7**

```markdown
**Enunciado:**

Considere o programa e as proposições a seguir.

```java
public class Program {
    public static void main(String[] args) {
        int[] dados = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            dados[i] = Integer.parseInt(args[i]);
        } // Loop 1: O(n)

        int contadosPares = 0;
        for (int i = 0; i < dados.length; i++) {
            for (int j = 0; j < dados.length; j++) {
                if (dados[i] == dados[j]) {
                    contadosPares++;
                }
            }
        } // Loop 2: O(n^2)

        int imparesVizinhos = 0;
        for (int i = 0; i < dados.length - 1; i++) { // Loop 3: O(n)
            if (dados[i] % 2 != 0 && dados[i + 1] % 2 != 0) {
                imparesVizinhos++;
            }
        }

        System.out.println("Quantidade de pares com dados[i] == dados[j] e i < j: " + contadosPares);
        System.out.println("Quantidade de vizinhos ímpares: " + imparesVizinhos);
    }
}
I O programa tem complexidade quadrática.
II O loop da linha 16 tem complexidade quadrática.
III Todo programa que possui um loop aninhado tem complexidade quadrática.

É correto apenas o que se afirma em

(X) I e II

Resposta correta: I

Explicação:

Vamos analisar cada parte do código e as proposições:

Loop de Leitura (linhas 4-6):

Este loop percorre o array dados uma vez. Se o tamanho do array é n (onde n = args.length), sua complexidade é $O(n)$.
Loop Aninhado para contadosPares (linhas 9-14):

Este é um loop aninhado. O loop externo (for (int i = 0; ... )) executa n vezes.
O loop interno (for (int j = 0; ... )) também executa n vezes para cada iteração do loop externo.
Portanto, o número total de operações (comparações) é aproximadamente n * n = n^2. Sua complexidade é $O(n^2)$.
Loop para imparesVizinhos (linhas 16-20):

Este loop percorre o array dados uma vez (n-1 vezes, o que é aproximadamente n).
Sua complexidade é $O(n)$.
A complexidade total de um programa com partes sequenciais é determinada pela complexidade da parte de maior ordem. Neste caso, $O(n^2)$ domina $O(n)$. Portanto, a complexidade total do programa é $O(n^2)$.

Agora, vamos avaliar as proposições:

Proposição I: "O programa tem complexidade quadrática."

Como o loop aninhado (linhas 9-14) tem complexidade $O(n^2)$ e é a parte dominante do código, a complexidade geral do programa é $O(n^2)$ (quadrática). Portanto, a proposição I é VERDADEIRA.
Proposição II: "O loop da linha 16 tem complexidade quadrática."

O loop da linha 16 é o loop para imparesVizinhos. Ele é um loop simples que percorre o array uma única vez. Sua complexidade é $O(n)$, não $O(n^2)$. Portanto, a proposição II é FALSA. (A sua marcação original indicou "I e II" como correto, o que sugere um erro na análise da proposição II ou um gabarito ambíguo. O loop da linha 16 não é quadrático.)
Proposição III: "Todo programa que possui um loop aninhado tem complexidade quadrática."

Isso é uma generalização incorreta. Nem todo loop aninhado resulta em complexidade quadrática. Por exemplo:
Se o loop interno depender de uma constante, ou de log n, ou se o loop interno for executado um número fixo de vezes (independentemente de n), a complexidade total pode não ser $O(n^2)$.
Se o loop interno for executado sqrt(n) vezes, a complexidade seria $O(n \cdot \sqrt{n}) = O(n^{1.5})$.
Um exemplo clássico onde loops aninhados não resultam em $O(n^2)$ é o algoritmo de ordenação Counting Sort, ou alguns algoritmos de grafos (como BFS/DFS que visitam cada aresta e vértice uma vez, resultando em $O(V+E)$).
Portanto, a proposição III é FALSA.
Conclusão: Apenas a proposição I é verdadeira. O gabarito indica "I" como a resposta correta, e sua marcação estava incorreta.

```

---

### **Questão 8**

```markdown
**Enunciado:**

Considere o programa e as proposições a seguir.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlocacaoTarefas {

    // Classe interna para representar uma tarefa
    static class Tarefa implements Comparable<Tarefa> {
        int inicio;
        int fim;

        Tarefa(int inicio, int fim) {
            this.inicio = inicio;
            this.fim = fim;
        }

        @Override
        public int compareTo(Tarefa outra) {
            // Compara as tarefas com base no tempo de fim
            return Integer.compare(this.fim, outra.fim);
        }
    }

    public static void main(String[] args) {
        // Lista de tarefas a serem consideradas
        List<Tarefa> tarefas = Arrays.asList(
            new Tarefa(1, 3),
            new Tarefa(2, 5),
            new Tarefa(4, 6),
            new Tarefa(6, 7),
            new Tarefa(5, 8)
        );

        // Ordena as tarefas pelo tempo de fim
        Collections.sort(tarefas); // Isso é crucial para o algoritmo guloso

        List<Tarefa> selecionadas = new ArrayList<>();
        int fimUltima = -1; // Tempo de término da última tarefa selecionada

        // Algoritmo guloso de seleção de atividades
        for (Tarefa t : tarefas) {
            if (t.inicio >= fimUltima) { // Se a tarefa atual não se sobrepõe à última selecionada
                selecionadas.add(t);    // Adiciona a tarefa
                fimUltima = t.fim;      // Atualiza o tempo de término da última tarefa selecionada
            }
        }

        System.out.println("Tarefas selecionadas:");
        for (Tarefa t : selecionadas) {
            System.out.println("Início: " + t.inicio + ", Fim: " + t.fim);
        }
    }
}
I Quando opera sobre o conjunto de tarefas existente no código, o programa maximiza o tempo de alocação.
II Quando opera sobre o conjunto de tarefas existente no código, o programa maximiza o número de tarefas alocadas.
III Quando opera sobre o conjunto de tarefas existente no código, todas as tarefas para as quais fim % 2 == 0 são selecionadas.

É correto apenas o que se afirma em

(X) II e III

Resposta correta: II

Explicação:

Este código implementa o algoritmo guloso de seleção de atividades (Activity Selection Problem). A estratégia é:

Ordenar as atividades por tempo de término crescente. (Feito por Collections.sort(tarefas); e compareTo em Tarefa).
Selecionar a primeira atividade.
Para as atividades restantes, selecionar a próxima atividade que não se sobreponha à atividade selecionada anteriormente e que tenha o menor tempo de término.
Este algoritmo garante que o número de atividades selecionadas seja maximizado, não a duração total.

Vamos analisar as proposições:

Proposição I: "Quando opera sobre o conjunto de tarefas existente no código, o programa maximiza o tempo de alocação."

O algoritmo de seleção de atividades (conhecido como "Earliest Finish Time First" ou "Guloso por Menor Tempo de Término") não maximiza o tempo de alocação (ou duração total das tarefas). Ele maximiza o número de tarefas compatíveis. Um exemplo simples:
Tarefa A: (1, 10) - Duração 9
Tarefa B: (1, 2), C: (3, 4), D: (5, 6) - Duração total 6 (3 tarefas)
O algoritmo selecionaria B, C, D (3 tarefas, total de duração 6). Se selecionasse A, seria apenas 1 tarefa, mas duração 9. O objetivo não é maximizar a duração, mas o número.
Portanto, a proposição I é FALSA.
Proposição II: "Quando opera sobre o conjunto de tarefas existente no código, o programa maximiza o número de tarefas alocadas."

Esta é a propriedade fundamental do algoritmo guloso de seleção de atividades. Ao escolher sempre a atividade que termina mais cedo e que é compatível, ele deixa o máximo de tempo possível para as atividades subsequentes, o que leva à maximização do número de atividades selecionadas. Portanto, a proposição II é VERDADEIRA.
Proposição III: "Quando opera sobre o conjunto de tarefas existente no código, todas as tarefas para as quais fim % 2 == 0 são selecionadas."

Vamos testar com os dados:
Tarefas ordenadas por fim:
(1, 3)
(2, 5)
(4, 6)
(6, 7)
(5, 8)
fim % 2 == 0 para: (4,6), (5,8)
Execução do algoritmo:
fimUltima = -1.
Tarefa (1, 3): 1 >= -1 (Verdadeiro). Seleciona (1, 3). fimUltima = 3.
Tarefa (2, 5): 2 >= 3 (Falso). Não seleciona.
Tarefa (4, 6): 4 >= 3 (Verdadeiro). Seleciona (4, 6). fimUltima = 6.
Tarefa (6, 7): 6 >= 6 (Verdadeiro). Seleciona (6, 7). fimUltima = 7.
Tarefa (5, 8): 5 >= 7 (Falso). Não seleciona.
Tarefas selecionadas: (1, 3), (4, 6), (6, 7).
As tarefas com fim % 2 == 0 são (4,6) e (5,8). A tarefa (5,8) não foi selecionada.
Portanto, a proposição III é FALSA.
Conclusão: Apenas a proposição II é verdadeira. O gabarito indica "II" como resposta correta, e sua marcação estava incorreta.

---

**Enunciado:**

Considere o gráfico e as proposições a seguir.

![Gráfico da Questão 9](image_840bf5.png)

I O gráfico mostra que
$f(n) = \Omega(g(n))$
- mesmo quando
$0 \le n < n_0$

II O gráfico mostra que
$f(n) \in \Theta(g(n))$

III O gráfico mostra que
$f(n) = O(g(n))$

É correto apenas o que se afirma em

(X) II
( ) I e II

**Resposta correta:** II e III

---

**Explicação:**

Esta questão exige a interpretação de um gráfico em relação às notações assintóticas Big O ($O$), Omega ($\Omega$) e Theta ($\Theta$).

No gráfico, temos três funções:
* `f(n)` (azul e vermelha, que se alternam)
* `g(n)` (verde)
* `c1 * g(n)` (verde mais clara, menor que `g(n)` para $n > n_0$)
* `c2 * g(n)` (laranja, maior que `g(n)` para $n > n_0$)

Podemos observar que, a partir de um ponto `n0`, a função `f(n)` (representada pelas curvas azul e vermelha, que parecem ser a mesma função oscilando) está sempre "limitada" entre `c1 * g(n)` e `c2 * g(n)`. Isso significa que, para `n >= n0`, temos:

$0 \le c_1 \cdot g(n) \le f(n) \le c_2 \cdot g(n)$

Agora, vamos analisar cada proposição:

* **Proposição I: "O gráfico mostra que $f(n) = \Omega(g(n))$ mesmo quando $0 \le n < n_0$"**
    * A definição da notação $\Omega$ (limite inferior assintótico) afirma que existem constantes $c > 0$ e $n_0 \ge 0$ tal que $0 \le c \cdot g(n) \le f(n)$ para todo $n \ge n_0$. A condição $n \ge n_0$ é crucial.
    * A proposição afirma que $f(n) = \Omega(g(n))$ *mesmo quando* $0 \le n < n_0$. As notações assintóticas (O, $\Omega$, $\Theta$) são definidas para `n` tendendo ao infinito, ou seja, para valores de `n` maiores ou iguais a um certo `n0`. O comportamento das funções para $n < n_0$ não afeta a validade das relações assintóticas. A proposição, ao sugerir que a relação $\Omega$ se mantém para `n < n0`, está fundamentalmente incorreta em sua interpretação da definição assintótica. A proposição I é, portanto, **FALSA**.

* **Proposição II: "O gráfico mostra que $f(n) \in \Theta(g(n))$"**
    * A notação $\Theta$ (Theta) significa que $f(n)$ cresce à mesma taxa que $g(n)$. Formalmente, existem constantes $c_1 > 0$, $c_2 > 0$ e $n_0 \ge 0$ tal que $0 \le c_1 \cdot g(n) \le f(n) \le c_2 \cdot g(n)$ para todo $n \ge n_0$.
    * O gráfico ilustra exatamente essa definição: a partir de `n0`, `f(n)` está sempre entre `c1 * g(n)` e `c2 * g(n)`. Portanto, $f(n) \in \Theta(g(n))$ é **VERDADEIRA**.

* **Proposição III: "O gráfico mostra que $f(n) = O(g(n))$"**
    * A notação $O$ (Big O) significa que $f(n)$ cresce no máximo tão rápido quanto $g(n)$. Formalmente, existem constantes $c > 0$ e $n_0 \ge 0$ tal que $0 \le f(n) \le c \cdot g(n)$ para todo $n \ge n_0$.
    * Como $f(n) \in \Theta(g(n))$, por definição, isso implica que $f(n) \in O(g(n))$ e $f(n) \in \Omega(g(n))$. A parte superior da "faixa" (c2 * g(n)) mostra que $f(n)$ está limitada superiormente por um múltiplo constante de $g(n)$. Portanto, $f(n) = O(g(n))$ é **VERDADEIRA**.

**Conclusão:** As proposições II e III são verdadeiras. O gabarito indica "II e III" como a resposta correta, e sua marcação estava incorreta (apenas "II").

---

**Enunciado:**

Considere a família de funções e as proposições a seguir.

$\Theta(g(n)) = \{f(n) \mid \exists c_1, c_2 > 0, \exists n_0 \ge 0 \text{ tal que } 0 \le c_1 \cdot g(n) \le f(n) \le c_2 \cdot g(n), \forall n \ge n_0 \}$

I Se
$c_1 = c_2$
e as demais restrições são verdadeiras, é verdade que
$f(n) \in \Theta(g(n))$

II Se
$f(n) \in \Theta(g(n))$
então
$f(n) = \Omega(g(n))$

III Funções constantes não podem pertencer ao conjunto descrito, independentemente da função g(n).

É correto apenas o que se afirma em

(X) I

**Resposta correta:** I e II

---

**Explicação:**

Esta questão explora as definições e propriedades da notação assintótica Theta ($\Theta$).

* **Definição de $\Theta(g(n))$:** Conforme dado no enunciado, $\Theta(g(n))$ é o conjunto de funções $f(n)$ para as quais $f(n)$ está limitada superior e inferiormente por múltiplos constantes de $g(n)$, para $n$ suficientemente grande. Isso significa que $f(n)$ e $g(n)$ têm a mesma taxa de crescimento assintótico.

Vamos analisar cada proposição:

* **Proposição I: "Se $c_1 = c_2$ e as demais restrições são verdadeiras, é verdade que $f(n) \in \Theta(g(n))$"**
    * A definição de $\Theta(g(n))$ requer a existência de *duas* constantes $c_1$ e $c_2$. Se $c_1 = c_2$, a definição ainda é satisfeita, pois você pode usar essa única constante para ambos os limites (superior e inferior). Por exemplo, se $f(n) = 5n^2$ e $g(n) = n^2$, podemos ter $c_1=5$ e $c_2=5$, e $f(n) \in \Theta(g(n))$ ainda é verdadeiro. A igualdade das constantes não invalida a definição. Portanto, a proposição I é **VERDADEIRA**.

* **Proposição II: "Se $f(n) \in \Theta(g(n))$ então $f(n) = \Omega(g(n))$"**
    * Pela própria definição de $\Theta(g(n))$:
        * $f(n) \in O(g(n))$ (porque $f(n) \le c_2 \cdot g(n)$)
        * $f(n) \in \Omega(g(n))$ (porque $c_1 \cdot g(n) \le f(n)$)
    * A notação $\Theta$ é, por definição, uma combinação das notações $O$ e $\Omega$. Se uma função pertence a $\Theta(g(n))$, ela *automaticamente* pertence a $O(g(n))$ e $\Omega(g(n))$. Portanto, a proposição II é **VERDADEIRA**.

* **Proposição III: "Funções constantes não podem pertencer ao conjunto descrito, independentemente da função g(n)."**
    * Considere uma função constante, por exemplo, $f(n) = 5$.
    * Se `g(n)` também for uma função constante, digamos $g(n) = 1$, então $f(n) \in \Theta(g(n))$ é verdadeiro. Podemos ter $c_1=1, c_2=10$ (ou qualquer $c_1, c_2 > 0$) tal que $1 \cdot 1 \le 5 \le 10 \cdot 1$.
    * Isso significa que $5 \in \Theta(1)$.
    * A proposição afirma que funções constantes *não podem* pertencer ao conjunto $\Theta(g(n))$. Isso é falso se $g(n)$ for também uma função constante. Se $g(n)$ não for constante (e, por exemplo, $g(n)$ cresce com $n$), então $f(n)$ constante não pertenceria a $\Theta(g(n))$, mas a proposição diz "independentemente da função g(n)", o que a torna falsa.
    * Portanto, a proposição III é **FALSA**.

**Conclusão:** As proposições I e II são verdadeiras. A resposta correta indica "I e II" como correto, e sua marcação estava incorreta (apenas "I").