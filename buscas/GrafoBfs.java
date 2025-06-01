package buscas;

import java.util.LinkedList; // Importa a classe LinkedList para representar as listas de adjacência
import java.util.Queue;      // Importa a classe Queue para a implementação da BFS (fila)

/**
 * Classe que representa um Grafo e implementa o algoritmo de Busca em Largura (BFS)
 * para encontrar a distância mínima entre dois vértices.
 */
public class GrafoBfs {

    private int vertices; // Número de vértices no grafo. Complexidade de armazenamento: O(1).
    // Array de listas ligadas para representar as listas de adjacência.
    // Cada adjacencias[i] contém uma lista de vértices adjacentes ao vértice i.
    // Complexidade de armazenamento: O(V + A), onde V é o número de vértices e A é o número de arestas.
    private LinkedList<Integer> adjacencias[];

    /**
     * Construtor da classe Grafo.
     * Inicializa o grafo com um número específico de vértices.
     *
     * @param vertices O número total de vértices no grafo.
     * Complexidade: O(V), onde V é o número de vértices.
     */
    @SuppressWarnings("unchecked")
    public GrafoBfs(int vertices) {
        this.vertices = vertices; // Atribui o número de vértices. Complexidade: O(1).
        this.adjacencias = new LinkedList[vertices]; // Cria o array de LinkedLists. Complexidade: O(1).

        // Inicializa cada lista de adjacência.
        // Essencial para evitar NullPointerException ao adicionar arestas.
        // Complexidade: O(V), onde V é o número de vértices.
        for (int i = 0; i < vertices; i++) {
            adjacencias[i] = new LinkedList<>();
        }
    }

    /**
     * Adiciona uma aresta ao grafo.
     * Assume que o grafo é não-direcionado, então adiciona a aresta em ambas as direções (u -> v e v -> u).
     *
     * @param u O vértice de origem.
     * @param v O vértice de destino.
     * Complexidade: O(1) para adicionar a aresta ao final da LinkedList.
     */
    public void adicionarAresta(int u, int v) {
        adjacencias[u].add(v); // Adiciona v à lista de adjacência de u.
        adjacencias[v].add(u); // Adiciona u à lista de adjacência de v (para grafo não direcionado).
    }

    /**
     * Encontra a distância mínima (número de arestas) entre um vértice de origem e um vértice de destino
     * usando o algoritmo de Busca em Largura (BFS).
     *
     * @param origem  O vértice de onde a busca deve começar.
     * @param destino O vértice que se deseja alcançar.
     * @return A distância mínima entre origem e destino, ou -1 se o destino não for alcançável.
     * Complexidade: O(V + A), onde V é o número de vértices e A é o número de arestas.
     * Isso ocorre porque cada vértice e cada aresta são visitados no máximo uma vez.
     */
    public int bfs(int origem, int destino) {
        // Verifica se os vértices de origem e destino são válidos.
        // Complexidade: O(1).
        if (origem < 0 || origem >= vertices || destino < 0 || destino >= vertices) {
            System.out.println("Vértice de origem ou destino inválido.");
            return -1; // Retorna -1 para indicar erro ou impossibilidade.
        }

        // Array para manter o controle dos vértices visitados.
        // true se o vértice foi visitado, false caso contrário.
        // Inicialmente todos são false. Complexidade de inicialização: O(V).
        boolean[] visitado = new boolean[vertices];

        // Array para armazenar a distância de cada vértice em relação à origem.
        // Inicialmente todas as distâncias são 0 ou um valor que indique 'infinito'.
        // Complexidade de inicialização: O(V).
        int[] distancia = new int[vertices];

        // Fila para a BFS. Armazena os vértices a serem visitados.
        // Complexidade de operação (add, poll): O(1) em média.
        Queue<Integer> fila = new LinkedList<>();

        // Marca o vértice de origem como visitado e adiciona-o à fila.
        // Sua distância para ele mesmo é 0.
        // Complexidade: O(1).
        visitado[origem] = true;
        distancia[origem] = 0;
        fila.add(origem);

        // Loop principal da BFS. Continua enquanto a fila não estiver vazia.
        // Complexidade do loop: Cada vértice é adicionado e removido da fila uma única vez (O(V)).
        // Para cada vértice, suas arestas são percorridas (O(grau do vértice)).
        // Somando todos os graus, obtemos O(A).
        // Portanto, a complexidade total é O(V + A).
        while (!fila.isEmpty()) {
            // Remove o primeiro vértice da fila para processamento.
            int u = fila.poll();

            // Se o vértice atual for o destino, encontramos o caminho mais curto.
            // Complexidade: O(1).
            if (u == destino) {
                return distancia[u]; // Retorna a distância encontrada.
            }

            // Percorre todos os vizinhos do vértice atual 'u'.
            // Complexidade: O(grau(u)).
            for (int v : adjacencias[u]) {
                // Se o vizinho 'v' ainda não foi visitado,
                // marca-o como visitado, atualiza sua distância
                // e o adiciona à fila.
                // Complexidade: O(1) para cada vizinho.
                if (!visitado[v]) {
                    visitado[v] = true;
                    distancia[v] = distancia[u] + 1; // A distância de v é a distância de u + 1 aresta.
                    fila.add(v);
                }
            }
        }

        // Se o loop terminar e o destino não foi alcançado, significa que não há caminho.
        // Complexidade: O(1).
        return -1; // Retorna -1 para indicar que o destino não é alcançável a partir da origem.
    }

    /**
     * Método principal para executar o programa a partir da linha de comando.
     *
     * @param args Argumentos da linha de comando.
     * Exemplo: java Grafo 6 0 1 0 3 1 5 2 5 3 4 4 5 0 5
     * Complexidade total do main:
     * O(N) para parsing dos argumentos (onde N é o número total de argumentos).
     * O(V) para construção do grafo.
     * O(A) para adicionar as arestas.
     * O(V + A) para a execução da BFS.
     * No pior caso, dominado pela BFS: O(V + A).
     */
    public static void main(String[] args) {
        if (args.length < 3) { // Deve haver pelo menos (num_vertices, origem, destino)
            System.out.println("Uso: java Grafo <num_vertices> <aresta1_u> <aresta1_v> ... <origem> <destino>");
            return;
        }

        // O primeiro argumento é o número de vértices.
        // Complexidade: O(1).
        int numVertices = Integer.parseInt(args[0]);
        GrafoBfs grafo = new GrafoBfs(numVertices); // Cria uma instância do grafo. O(V)

        // Calcula quantos pares de arestas existem nos argumentos.
        // Os dois últimos argumentos são a origem e o destino.
        // Os argumentos do índice 1 até (args.length - 3) formam os pares de arestas.
        // Complexidade: O(1).
        int numArestasPares = (args.length - 1 - 2) / 2; // (total_args - (num_vertices + origem + destino)) / 2
                                                         // Simplificando: (args.length - 3) / 2

        // Adiciona as arestas ao grafo.
        // Itera sobre os argumentos para extrair os pares (u, v) e adicioná-los como arestas.
        // O(A) onde A é o número de arestas.
        for (int i = 0; i < numArestasPares; i++) {
            int u = Integer.parseInt(args[1 + i * 2]);     // Pega o primeiro vértice do par
            int v = Integer.parseInt(args[1 + i * 2 + 1]); // Pega o segundo vértice do par
            grafo.adicionarAresta(u, v);                   // Adiciona a aresta.
        }

        // Os dois últimos argumentos são o vértice de origem e o de destino.
        // Complexidade: O(1).
        int origem = Integer.parseInt(args[args.length - 2]);
        int destino = Integer.parseInt(args[args.length - 1]);

        // Executa a BFS para encontrar a distância mínima.
        // Complexidade: O(V + A).
        int distanciaMinima = grafo.bfs(origem, destino);

        // Exibe o resultado.
        // Complexidade: O(1).
        if (distanciaMinima != -1) {
            System.out.println("Distância mínima de " + origem + " até " + destino + " é " + distanciaMinima);
        } else {
            System.out.println("Não foi possível encontrar um caminho de " + origem + " até " + destino);
        }
    }
}