/*
Exercício:

Implemente uma classe em Java que represente um grafo simples e não direcionado
utilizando uma lista de adjacências com a estrutura LinkedList<Integer> para
armazenar os vizinhos de cada vértice. O programa deve ler o grafo da entrada
padrão e, para cada vértice v, produzir:

    • O conjunto de vizinhos N(v)
    • O grau de v

Entrada:
    • Um número inteiro n representando o número de vértices (numerados de 0 a n - 1).
    • Um número inteiro m representando o número de arestas.
    • Em seguida, m linhas com dois inteiros u e v, indicando que existe uma aresta entre os vértices u e v.

Saída:
    Para cada vértice v, o programa deve imprimir os vizinhos N(v) e o grau do vértice, no formato:

    N(v) = [...], grau = k

Exemplo:
Entrada:
4
4
0 1
0 2
1 2
2 3

Saída esperada:
N(0) = [1, 2], grau = 2
N(1) = [0, 2], grau = 2
N(2) = [0, 1, 3], grau = 3
N(3) = [2], grau = 1
*/

import java.util.*;

// Classe que representa um grafo simples e não direcionado
public class Grafo {

    // Lista de adjacência onde cada vértice possui uma lista de vizinhos
    private List<LinkedList<Integer>> adjacencia;

    // Construtor: inicializa a lista de adjacência com n vértices
    public Grafo(int n) {
        adjacencia = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencia.add(new LinkedList<>());
        }
        // Complexidade: O(n), pois adicionamos n listas vazias
    }

    // Adiciona uma aresta não direcionada entre os vértices u e v
    public void adicionarAresta(int u, int v) {
        adjacencia.get(u).add(v); // adiciona v à lista de vizinhos de u
        adjacencia.get(v).add(u); // adiciona u à lista de vizinhos de v
        // Complexidade: O(1) para cada chamada, pois a inserção em LinkedList é constante
    }

    // Imprime, para cada vértice, sua lista de vizinhos e seu grau
    public void imprimirGrafo() {
        for (int v = 0; v < adjacencia.size(); v++) {
            LinkedList<Integer> vizinhos = adjacencia.get(v);
            System.out.println("N(" + v + ") = " + vizinhos + ", grau = " + vizinhos.size());
            // Para cada vértice, acessamos a lista de vizinhos (tempo proporcional ao grau do vértice)
        }
        // Complexidade total desse método: O(n + m)
        // - O(n) para iterar sobre os vértices
        // - O(m) porque a soma dos tamanhos das listas de adjacência é 2m (cada aresta aparece duas vezes)
    }

    // Função principal: lê o grafo da entrada e imprime os vizinhos e graus de cada vértice
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // número de vértices
        int m = sc.nextInt(); // número de arestas

        Grafo grafo = new Grafo(n); // inicializa o grafo

        // Lê m pares (u, v) e adiciona as arestas ao grafo
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            grafo.adicionarAresta(u, v);
        }
        // Complexidade: O(m), pois cada aresta é lida e inserida uma vez (duas chamadas O(1))

        // Imprime o resultado conforme o formato esperado
        grafo.imprimirGrafo();

        sc.close();
    }
}
