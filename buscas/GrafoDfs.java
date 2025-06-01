/*
Exercício:

Implemente uma classe Java chamada Grafo que realiza uma busca em profundidade (DFS) a partir do vértice 0.
O grafo é não direcionado e deve ser representado por lista de adjacências usando um vetor de LinkedList<Integer>.
A entrada será fornecida via linha de comando (args), da seguinte forma:

• O primeiro valor representa o número de vértices do grafo.
• Os valores seguintes devem ser interpretados como pares de vértices conectados por uma aresta.

Exemplo de execução:
// java Grafo 6 0 1 0 2 1 3 2 4 4 5

Explicação:
• 6 → número de vértices (vértices de 0 a 5)
• 0 1, 0 2, 1 3, 2 4, 4 5 → arestas

Saída esperada:
DFS a partir do vértice 0:
0 1 3 2 4 5

Dica:
Utilize um vetor boolean[] visitado para controlar quais vértices já foram visitados.
*/

package buscas;

import java.util.*;

public class GrafoDfs {
    private int vertices;                       // Número total de vértices do grafo
    private List<Integer>[] adjacencias;       // Lista de adjacência: vetor de listas

    @SuppressWarnings("unchecked")
    public GrafoDfs(int vertices) {
        this.vertices = vertices;
        this.adjacencias = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencias[i] = new LinkedList<>();  // Inicializa lista de adjacência para cada vértice
        }
    }

    // Adiciona uma aresta não direcionada entre origem e destino
    public void adicionarAresta(int origem, int destino) {
        adjacencias[origem].add(destino);
        adjacencias[destino].add(origem);  // Como é grafo não direcionado, adiciona nos dois sentidos
    }

    // Busca em profundidade a partir de um vértice dado
    public void dfs(int inicio) {
        boolean[] visitado = new boolean[vertices];  // Marca os vértices já visitados
        System.out.println("DFS a partir do vértice " + inicio + ":");
        dfsRecursivo(inicio, visitado);              // Chamada recursiva
        System.out.println();
    }

    // Função recursiva de DFS
    private void dfsRecursivo(int atual, boolean[] visitado) {
        visitado[atual] = true;              // Marca vértice como visitado
        System.out.print(atual + " ");       // Processa o vértice (exibe)

        for (int vizinho : adjacencias[atual]) {
            if (!visitado[vizinho]) {
                dfsRecursivo(vizinho, visitado);  // Visita recursivamente vizinhos não visitados
            }
        }
    }

    public static void main(String[] args) {
        int vertices = Integer.parseInt(args[0]);    // Número de vértices do grafo
        GrafoDfs grafo = new GrafoDfs(vertices);          // Cria o grafo

        // Lê os pares de vértices da entrada (arestas)
        for (int i = 1; i < args.length; i += 2) {
            int origem = Integer.parseInt(args[i]);
            int destino = Integer.parseInt(args[i + 1]);
            grafo.adicionarAresta(origem, destino);
        }

        grafo.dfs(0);  // Executa DFS a partir do vértice 0 (como exigido no enunciado)
    }
}

/*
Complexidade:
- Construção da lista de adjacência: O(m), onde m é o número de arestas
- DFS (visita todos os vértices e arestas): O(n + m), onde n é o número de vértices
*/
