package kruskal;

import java.util.*;

// =================================================================================================
// Classes base (Aresta) - Reutilizada do seu código
// =================================================================================================
class Aresta implements Comparable<Aresta> {
    int u, v, peso; // u e v são os vértices que a aresta conecta, peso é o custo.

    Aresta(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta o) {
        // Compara arestas com base em seus pesos para ordenação.
        // Complexidade: O(1).
        return Integer.compare(this.peso, o.peso);
    }
}

// =================================================================================================
// Exercício 1: UnionFind aprimorado com Path Compression e Union By Rank
// =================================================================================================
class UnionFind {
    int[] representantes; // Armazena o pai de cada elemento.
    int[] rank;           // Armazena o rank (altura aproximada) de cada raiz.

    /**
     * Construtor do UnionFind.
     *
     * @param n O número total de elementos (vértices).
     * Complexidade: O(n) para inicializar os arrays.
     */
    public UnionFind(int n) {
        representantes = new int[n];
        rank = new int[n];
        // Cada elemento é inicialmente seu próprio representante e tem rank 0.
        for (int i = 0; i < n; i++) {
            representantes[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Encontra o representante do conjunto ao qual 'x' pertence.
     * Implementa a otimização de Path Compression.
     *
     * @param x O elemento cujo representante se deseja encontrar.
     * @return O representante (raiz) do conjunto de 'x'.
     * Complexidade: Quase constante (amortizada O(α(N)), onde α é o inverso da função de Ackermann),
     * na prática, é extremamente rápida, quase O(1).
     */
    public int find(int x) {
        // Se 'x' não é seu próprio representante, recursivamente encontra o representante
        // e, no retorno da recursão, faz 'x' apontar diretamente para o representante (Path Compression).
        if (representantes[x] != x) {
            representantes[x] = find(representantes[x]);
        }
        return representantes[x];
    }

    /**
     * Une os conjuntos que contêm 'x' e 'y'.
     * Implementa a otimização Union By Rank.
     *
     * @param x Um elemento do primeiro conjunto.
     * @param y Um elemento do segundo conjunto.
     * Complexidade: Quase constante (amortizada O(α(N))).
     */
    public void union(int x, int y) {
        int rX = find(x); // Encontra o representante de 'x'.
        int rY = find(y); // Encontra o representante de 'y'.

        // Se já estão no mesmo conjunto, não há nada a fazer.
        if (rX != rY) {
            // Anexa a árvore com menor rank à árvore com maior rank.
            // Isso ajuda a manter as árvores mais rasas.
            if (rank[rX] < rank[rY]) {
                representantes[rX] = rY;
            } else if (rank[rY] < rank[rX]) {
                representantes[rY] = rX;
            } else {
                // Se os ranks são iguais, anexa um ao outro e incrementa o rank da nova raiz.
                representantes[rY] = rX;
                rank[rX]++;
            }
        }
    }
}

// =================================================================================================
// Algoritmo de Kruskal (usando o UnionFind aprimorado)
// =================================================================================================
public class Kruskal { // Renomeado de KruskalSimples para Kruskal
    /**
     * Implementa o algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (AGM).
     * Utiliza o Union-Find aprimorado com Path Compression e Union By Rank.
     *
     * @param n      O número de vértices no grafo.
     * @param arestas Uma lista de todas as arestas no grafo.
     * @return Uma lista de arestas que compõem a AGM.
     * Complexidade: O(A log A) devido à ordenação das arestas, ou O(A log V) se V é o número
     * de vértices e A o número de arestas, devido às operações amortizadas do Union-Find.
     * Geralmente, A log A domina se o grafo for denso (A é próximo de V^2), ou A log V se for esparso.
     */
    public List<Aresta> kruskal(int n, List<Aresta> arestas) {
        // 1. Ordena todas as arestas por peso em ordem crescente.
        // Complexidade: O(A log A), onde A é o número de arestas.
        Collections.sort(arestas);

        // 2. Inicializa a estrutura Union-Find.
        // Complexidade: O(V).
        var uf = new UnionFind(n);

        // 3. Inicializa a lista para armazenar as arestas da AGM.
        var arvoreResultante = new ArrayList<Aresta>();

        // 4. Itera sobre as arestas ordenadas.
        // Complexidade: O(A) iterações. Em cada iteração, Union-Find operações são O(α(V)) amortizado.
        // Total: O(A * α(V)).
        for (var a : arestas) {
            // Se os vértices da aresta (u, v) não estão no mesmo conjunto (não formam um ciclo),
            if (uf.find(a.u) != uf.find(a.v)) {
                uf.union(a.u, a.v);           // Une os conjuntos.
                arvoreResultante.add(a);      // Adiciona a aresta à AGM.
                // Se a AGM já tem V-1 arestas, encontramos todas as arestas necessárias.
                if (arvoreResultante.size() == n - 1) {
                    break;
                }
            }
        }
        return arvoreResultante;
    }

    // =================================================================================================
    // Exercício 2: Implementação do Algoritmo de Prim
    // =================================================================================================

    /**
     * Implementa o algoritmo de Prim para encontrar a Árvore Geradora Mínima (AGM).
     *
     * @param n O número de vértices no grafo.
     * @param adjacencias Lista de adjacências que representa o grafo.
     * Cada adjacencias.get(u) é uma lista de Aresta (destino, peso) saindo de u.
     * @param origem O vértice de início para o algoritmo de Prim (pode ser qualquer vértice).
     * @return Uma lista de arestas que compõem a AGM.
     * Complexidade: O(A log V) ou O(A + V log V) dependendo da implementação da PriorityQueue.
     * Usando PriorityQueue padrão de Java (heap binário), é O(A log V).
     */
    public List<Aresta> prim(int n, List<List<Aresta>> adjacencias, int origem) {
        // Array para armazenar o peso da aresta de menor custo que conecta 'i' à MST.
        // Inicializa com infinito (MAX_VALUE).
        // Complexidade: O(V).
        int[] key = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);

        // Array para armazenar o predecessor de cada vértice na MST.
        // Usado para reconstruir a MST.
        // Complexidade: O(V).
        int[] parent = new int[n];
        Arrays.fill(parent, -1); // -1 indica nenhum pai.

        // Array para verificar se um vértice já está incluído na MST.
        // Complexidade: O(V).
        boolean[] inMST = new boolean[n];

        // Fila de prioridade para armazenar objetos Pair (peso, vértice).
        // Pair é uma classe auxiliar para armazenar (key[v], v).
        // Prioriza pelo peso (key).
        // Complexidade: O(log V) para inserção e remoção.
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // Começa com o vértice de origem. Sua 'key' é 0.
        key[origem] = 0;
        pq.add(new Pair(0, origem)); // Adiciona (peso, vértice) à fila de prioridade.

        // Lista para armazenar as arestas da AGM.
        List<Aresta> arvoreResultante = new ArrayList<>();
        int arestasAdicionadas = 0; // Contador de arestas adicionadas para o Prim.

        // Loop principal: Continua enquanto a fila de prioridade não estiver vazia
        // e ainda não adicionamos V-1 arestas (que é o tamanho de uma AGM).
        // Complexidade: O(V) extrações de min.
        // Cada extração de min é O(log V). Total V log V.
        while (!pq.isEmpty() && arestasAdicionadas < n - 1) {
            Pair minNode = pq.poll(); // Extrai o vértice com a menor 'key' (peso) da fila.
            int u = minNode.vertice;  // Vértice atual.

            // Se 'u' já está na MST, pula. (Pode acontecer se um vértice foi adicionado à PQ múltiplas vezes
            // com diferentes 'keys' e uma chave menor foi processada primeiro).
            if (inMST[u]) {
                continue;
            }

            inMST[u] = true; // Marca 'u' como incluído na MST.

            // Se 'u' não é a origem e tem um pai, adiciona a aresta (parent[u], u) à AGM.
            // Complexidade: O(1).
            if (parent[u] != -1) {
                arvoreResultante.add(new Aresta(parent[u], u, key[u]));
                arestasAdicionadas++;
            }

            // Para cada vizinho 'v' de 'u'.
            // Complexidade: Sum(grau(u)) para todos os u = O(A) total.
            // Cada adição à PQ é O(log V). Total A log V.
            for (Aresta aresta : adjacencias.get(u)) {
                int v = aresta.v;
                int peso = aresta.peso;

                // Se 'v' ainda não está na MST e o peso da aresta (u,v) é menor
                // que a 'key' atual de 'v'.
                if (!inMST[v] && peso < key[v]) {
                    key[v] = peso;          // Atualiza a 'key' de 'v'.
                    parent[v] = u;          // Define 'u' como pai de 'v'.
                    pq.add(new Pair(key[v], v)); // Adiciona/atualiza 'v' na fila de prioridade.
                }
            }
        }
        // Verifica se a AGM foi completamente formada (se o grafo é conectado).
        // Se arestasAdicionadas < n - 1, o grafo não é conectado e uma AGM não pode ser formada.
        if (arestasAdicionadas < n - 1 && n > 1) { // n > 1 para grafos com 1 vértice.
             System.out.println("Aviso: O grafo não é conectado. A AGM não foi totalmente formada.");
        }

        return arvoreResultante;
    }

    /**
     * Classe auxiliar para a PriorityQueue no algoritmo de Prim.
     * Armazena um par (peso, vértice) e permite comparação baseada no peso.
     */
    private static class Pair implements Comparable<Pair> {
        int peso;
        int vertice;

        Pair(int peso, int vertice) {
            this.peso = peso;
            this.vertice = vertice;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.peso, other.peso);
        }
    }

    // =================================================================================================
    // Main Method para testar Kruskal aprimorado e Prim
    // =================================================================================================
    public static void main(String[] args) {
        int n = 4; // Número de vértices

        // Exemplo de arestas para testar Kruskal (igual ao seu código base)
        // Grafo: 0--10--1, 0--6--2, 0--5--3, 1--15--3, 2--4--3
        List<Aresta> arestasKruskal = Arrays.asList(
                new Aresta(0, 1, 10),
                new Aresta(0, 2, 6),
                new Aresta(0, 3, 5),
                new Aresta(1, 3, 15),
                new Aresta(2, 3, 4)
        );

        System.out.println("--- Testando Kruskal aprimorado ---");
        Kruskal kruskalAlgo = new Kruskal();
        List<Aresta> arvoreKruskal = kruskalAlgo.kruskal(n, arestasKruskal);

        int totalPesoKruskal = 0;
        System.out.print("Arestas da AGM (Kruskal): ");
        for (var a : arvoreKruskal) {
            System.out.printf("(%d, %d, %d) ", a.u, a.v, a.peso);
            totalPesoKruskal += a.peso;
        }
        System.out.println("\nPeso total da AGM (Kruskal): " + totalPesoKruskal);

        System.out.println("\n--- Testando Algoritmo de Prim ---");

        // Para o Prim, precisamos de uma representação de lista de adjacência.
        // Vamos construir essa representação a partir das arestasKruskal.
        // A lista de adjacências para Prim precisa ser direcionada no sentido de "para quem" a aresta vai
        // e, para um grafo não direcionado, cada aresta (u,v,peso) se torna (u,v,peso) e (v,u,peso).
        List<List<Aresta>> adjacenciasPrim = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacenciasPrim.add(new ArrayList<>());
        }
        for (Aresta a : arestasKruskal) {
            adjacenciasPrim.get(a.u).add(new Aresta(a.u, a.v, a.peso)); // Aresta u -> v
            adjacenciasPrim.get(a.v).add(new Aresta(a.v, a.u, a.peso)); // Aresta v -> u
        }

        // Executa Prim a partir do vértice 0
        List<Aresta> arvorePrim = kruskalAlgo.prim(n, adjacenciasPrim, 0);

        int totalPesoPrim = 0;
        System.out.print("Arestas da AGM (Prim): ");
        for (var a : arvorePrim) {
            // As arestas de Prim são armazenadas como (parent[u], u, key[u])
            // Para imprimir de forma consistente como (u, v, peso), podemos normalizar.
            // Aqui, o 'u' na aresta retornada é o predecessor, e 'v' é o vértice atual.
            // Para fins de exibição, garantimos a ordem numérica menor -> maior vértice para arestas.
            int displayU = Math.min(a.u, a.v);
            int displayV = Math.max(a.u, a.v);
            System.out.printf("(%d, %d, %d) ", displayU, displayV, a.peso);
            totalPesoPrim += a.peso;
        }
        System.out.println("\nPeso total da AGM (Prim): " + totalPesoPrim);

        // Teste de grafo não conectado para Prim (apenas para demonstrar o aviso)
        System.out.println("\n--- Testando Prim em grafo não conectado ---");
        int n2 = 3;
        List<List<Aresta>> adjacenciasNaoConectado = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            adjacenciasNaoConectado.add(new ArrayList<>());
        }
        adjacenciasNaoConectado.get(0).add(new Aresta(0, 1, 5)); // Apenas 0 e 1 estão conectados
        adjacenciasNaoConectado.get(1).add(new Aresta(1, 0, 5));

        List<Aresta> arvorePrimNaoConectado = kruskalAlgo.prim(n2, adjacenciasNaoConectado, 0);
        System.out.print("Arestas da AGM (Prim - Não Conectado): ");
        if (arvorePrimNaoConectado.isEmpty() && n2 > 1) { // Para evitar "Peso total: 0" para grafos de 1 vertice
             System.out.println("Nenhuma aresta na AGM (grafo desconectado ou apenas 1 vértice).");
        } else {
            int totalPesoPrimNaoConectado = 0;
            for (var a : arvorePrimNaoConectado) {
                int displayU = Math.min(a.u, a.v);
                int displayV = Math.max(a.u, a.v);
                System.out.printf("(%d, %d, %d) ", displayU, displayV, a.peso);
                totalPesoPrimNaoConectado += a.peso;
            }
            System.out.println("\nPeso total da AGM (Prim - Não Conectado): " + totalPesoPrimNaoConectado);
        }
    }
}
