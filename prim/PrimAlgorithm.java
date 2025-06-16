package prim;

import java.util.Arrays;

public class PrimAlgorithm {

    // Número de vértices no grafo
    private int numVertices;

    public PrimAlgorithm(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * Encontra e imprime a Árvore Geradora Mínima (AGM) de um grafo
     * usando o algoritmo de Prim.
     *
     * @param graph Matriz de adjacência representando o grafo.
     * graph[i][j] é o peso da aresta de i para j.
     * Um valor 0 (ou infinito) indica ausência de aresta.
     */
    public void primMST(int[][] graph) {
        // Array para armazenar o vértice pai de cada vértice na AGM
        int[] parent = new int[numVertices];

        // Array para armazenar o menor peso de aresta para conectar cada vértice
        // à AGM em construção. Inicializado com valores "infinitos".
        int[] key = new int[numVertices];

        // Array booleano para rastrear os vértices incluídos na AGM
        // included[i] é true se o vértice i está na AGM
        boolean[] included = new boolean[numVertices];

        // Inicializa todas as chaves como "infinito" e 'included' como false
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(included, false);

        // O primeiro vértice a ser incluído na AGM será o vértice 0.
        // A chave do vértice 0 é 0 para que ele seja escolhido primeiro.
        // Não tem pai, então parent[0] pode ser qualquer valor ou indicado como -1.
        key[0] = 0;
        parent[0] = -1; // -1 indica que é a raiz da AGM

        // A AGM terá numVertices arestas (V-1), mas aqui iteramos V vezes
        // para garantir que todos os V vértices sejam processados.
        for (int count = 0; count < numVertices - 1; count++) {
            // 1. Encontrar o vértice com a menor 'key' que ainda não está na AGM
            int u = findMinKeyVertex(key, included);

            // 2. Adicionar o vértice encontrado 'u' à AGM
            included[u] = true;

            // 3. Atualizar os valores 'key' e 'parent' dos vértices adjacentes a 'u'
            // que ainda não estão na AGM
            for (int v = 0; v < numVertices; v++) {
                // Se existe uma aresta entre u e v, v ainda não está na AGM,
                // e o peso da aresta (u,v) é menor que a chave atual de v
                if (graph[u][v] != 0 && !included[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Imprimir a Árvore Geradora Mínima construída
        printMST(parent, graph);
    }

    /**
     * Encontra o vértice com o menor valor 'key' que ainda não foi incluído na AGM.
     *
     * @param key Array de chaves (pesos mínimos para conexão).
     * @param included Array booleano indicando se o vértice já foi incluído.
     * @return O índice do vértice com a menor chave não incluída.
     */
    private int findMinKeyVertex(int[] key, boolean[] included) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!included[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    /**
     * Imprime as arestas da Árvore Geradora Mínima e seu custo total.
     *
     * @param parent Array de pais, indicando a estrutura da AGM.
     * @param graph Matriz de adjacência do grafo original.
     */
    private void printMST(int[] parent, int[][] graph) {
        System.out.println("Aresta \tPeso");
        int totalCost = 0;
        for (int i = 1; i < numVertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        System.out.println("Custo total da AGM: " + totalCost);
    }

    public static void main(String[] args) {
        // Exemplo de grafo (matriz de adjacência)
        // Vértices: 0, 1, 2, 3, 4
        // Arestas: (0,1)=2, (0,3)=6, (1,2)=3, (1,3)=8, (1,4)=5, (2,4)=7, (3,4)=9
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        PrimAlgorithm prim = new PrimAlgorithm(5); // Grafo com 5 vértices
        prim.primMST(graph);
    }
}