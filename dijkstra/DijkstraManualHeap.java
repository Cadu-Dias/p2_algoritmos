package dijkstra; // Novo pacote para organizar

import java.util.*;

// =================================================================================================
// Classes do Grafo (Adaptadas para Dijkstra com Heap Manual)
// =================================================================================================

class Grafo {

    private final Vertice[] vertices;
    private List<List<Aresta>> adjacencias;
    private Map<String, Integer> nomeParaIndice; // Mapeia nomes de região para índices

    Grafo(String[] nomesVertices) {
        this.adjacencias = new ArrayList<>();
        this.vertices = new Vertice[nomesVertices.length];
        this.nomeParaIndice = new HashMap<>();

        for (int i = 0; i < nomesVertices.length; i++) {
            this.adjacencias.add(new ArrayList<>());
            this.vertices[i] = new Vertice(nomesVertices[i], i);
            this.nomeParaIndice.put(nomesVertices[i], i); // Adiciona ao mapa
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        // Grafos direcionados para Dijkstra, então apenas de origem para destino
        adjacencias.get(origem).add(new Aresta(destino, peso));
    }

    public List<Aresta> vizinhos(int u) {
        return adjacencias.get(u);
    }

    public Vertice[] getVertices() {
        return vertices;
    }

    public int quantidadeVertices() {
        return vertices.length;
    }

    public Integer getIndice(String nome) {
        return nomeParaIndice.get(nome);
    }

    static class Vertice implements Comparable<Vertice> { // Vertice agora é Comparable
        String nome;
        int indice;
        int distancia;
        Vertice predecessor;

        Vertice(String nome, int indice) {
            this.nome = nome;
            this.indice = indice;
            this.distancia = Integer.MAX_VALUE; // Representa infinito
            this.predecessor = null;
        }

        @Override
        public int compareTo(Vertice other) {
            // Compara vértices pela distância para a fila de prioridade
            return Integer.compare(this.distancia, other.distancia);
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    static class Aresta {
        int destino;
        int peso;

        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
}

// =================================================================================================
// Implementação da Fila de Prioridade (Min-Heap Manual)
// =================================================================================================
class MinPriorityQueue {
    private List<Grafo.Vertice> heap;
    // Opcional: Para otimizar decreaseKey em um heap manual,
    // precisaríamos de um mapeamento de Vertice para seu índice no heap.
    // map<Integer, Integer> verticeIndiceNoHeap;
    // Para este problema e simplificação, vamos usar a abordagem de re-inserir.

    public MinPriorityQueue() {
        this.heap = new ArrayList<>();
        // this.verticeIndiceNoHeap = new HashMap<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(Grafo.Vertice v) {
        // Se o vértice já estiver no heap (para o caso de decreaseKey),
        // uma forma simples é removê-lo e reinserí-lo.
        // Ou, para a implementação mais fiel ao Cormen, simplesmente adicionar e
        // deixar o extractMin lidar com as duplicatas (pegará a menor distância).
        // Para Dijkstra, geralmente, a inserção é feita quando a distância é "relaxada".
        // A versão Cormen do Min-Priority Queue para Dijkstra tem uma operação DECREASE-KEY.
        // Sem um mapeamento de índice, é mais complexo.
        // A abordagem mais comum para simplificar é permitir duplicatas e
        // verificar se o vértice já foi extraído ao retirá-lo.
        heap.add(v);
        heapifyUp(heap.size() - 1);
        // verticeIndiceNoHeap.put(v.indice, heap.size() - 1);
    }

    public Grafo.Vertice extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Fila de prioridade vazia.");
        }
        Grafo.Vertice min = heap.get(0);
        Grafo.Vertice last = heap.remove(heap.size() - 1); // Remove o último
        if (!isEmpty()) {
            heap.set(0, last); // Coloca o último na raiz
            heapifyDown(0);
        }
        // verticeIndiceNoHeap.remove(min.indice);
        return min;
    }

    // heapifyUp para manter a propriedade de Min-Heap após uma inserção
    private void heapifyUp(int i) {
        while (i > 0 && heap.get(i).compareTo(heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // heapifyDown para manter a propriedade de Min-Heap após um extractMin
    private void heapifyDown(int i) {
        int leftChild = left(i);
        int rightChild = right(i);
        int smallest = i;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // Métodos auxiliares para cálculo de índices (base 0)
    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Grafo.Vertice temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        // Atualizar mapeamento de índice se verticeIndiceNoHeap fosse usado
        // verticeIndiceNoHeap.put(heap.get(i).indice, i);
        // verticeIndiceNoHeap.put(heap.get(j).indice, j);
    }
}

// =================================================================================================
// Algoritmo de Dijkstra (com Min-Heap Manual)
// =================================================================================================
public class DijkstraManualHeap {

    public void executar(Grafo g, int origemIndice, int destinoIndice) {
        inicializarFonteUnica(g, origemIndice);

        // Usando a fila de prioridade manual
        MinPriorityQueue pq = new MinPriorityQueue();
        for (Grafo.Vertice v : g.getVertices()) {
            pq.insert(v); // Insere todos os vértices na fila
        }

        // Para evitar processar o mesmo vértice várias vezes com distâncias maiores
        boolean[] visitado = new boolean[g.quantidadeVertices()];

        while (!pq.isEmpty()) {
            Grafo.Vertice u = pq.extractMin(); // Extrai o vértice com a menor distância
            
            // Se já processamos este vértice com uma distância menor, ignoramos esta entrada
            if (visitado[u.indice]) {
                continue;
            }
            visitado[u.indice] = true;

            // Se chegamos ao destino, podemos parar (para caminho de única origem-destino)
            if (u.indice == destinoIndice) {
                break;
            }

            for (Grafo.Aresta aresta : g.vizinhos(u.indice)) {
                Grafo.Vertice v = g.getVertices()[aresta.destino];
                // Se 'v' ainda não foi visitado e podemos relaxar a aresta
                if (!visitado[v.indice] && u.distancia != Integer.MAX_VALUE && v.distancia > u.distancia + aresta.peso) {
                    v.distancia = u.distancia + aresta.peso;
                    v.predecessor = u;
                    pq.insert(v); // Re-insere o vértice com a nova distância (simula decrease-key)
                }
            }
        }

        exibirCaminhoMinimo(g, origemIndice, destinoIndice);
    }

    private void inicializarFonteUnica(Grafo g, int s) {
        for (Grafo.Vertice v : g.getVertices()) {
            v.distancia = Integer.MAX_VALUE;
            v.predecessor = null;
        }
        g.getVertices()[s].distancia = 0;
    }

    private void exibirCaminhoMinimo(Grafo g, int origemIndice, int destinoIndice) {
        Grafo.Vertice destino = g.getVertices()[destinoIndice];
        if (destino.distancia == Integer.MAX_VALUE) {
            System.out.printf("Não há caminho de %s até %s.\n", g.getVertices()[origemIndice].nome, destino.nome);
            return;
        }

        List<String> caminho = new ArrayList<>();
        Grafo.Vertice atual = destino;
        while (atual != null) {
            caminho.add(atual.nome);
            atual = atual.predecessor;
        }
        Collections.reverse(caminho); // Inverte para ter origem -> destino

        System.out.print("Caminho encontrado: ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caminho.size(); i++) {
            sb.append(caminho.get(i));
            if (i < caminho.size() - 1) {
                // Adiciona o peso da aresta seguinte
                Grafo.Vertice u = g.getVertices()[g.getIndice(caminho.get(i))];
                Grafo.Vertice v = g.getVertices()[g.getIndice(caminho.get(i + 1))];
                int pesoAresta = -1; // Para encontrar o peso da aresta
                for(Grafo.Aresta a : g.vizinhos(u.indice)){
                    if(a.destino == v.indice){
                        pesoAresta = a.peso;
                        break;
                    }
                }
                sb.append(" ->(").append(pesoAresta).append(") ");
            }
        }
        System.out.println(sb.toString());
        System.out.printf("Custo total do caminho: %d\n", destino.distancia);
    }

    public static void main(String[] args) {
        // Entrada esperada: num_arestas, origem_regiao, destino_regiao, custo... origem_final destino_final
        // Exemplo: 6 0 1 10 0 3 5 1 2 1 1 3 2 2 4 4 3 1 3 3 2 9 3 4 2 4 2 6 s z
        // Note que o problema pede "num_arestas", mas os exemplos do Dijkstra
        // geralmente leem vértices primeiro. Para seguir a entrada solicitada,
        // vou assumir que o primeiro número é o número de *arestas* e não o número de vértices.
        // Isso implica que precisamos descobrir o número de vértices a partir das arestas,
        // ou que os nomes dos vértices sejam fornecidos de alguma forma.
        // O exemplo "java Grafo 6 0 1 0 3 1 5 2 5 3 4 4 5 0 5" do BFS sugere
        // que o primeiro número é o número de vértices. Vamos manter essa convenção:
        // args[0] = número de vértices (máx_vertice_idx + 1)
        // args[1..n-3] = arestas (u, v, peso)
        // args[n-2] = nome_origem
        // args[n-1] = nome_destino

        if (args.length < 5) { // min: num_vertices, u1, v1, origem, destino
            System.out.println("Uso: java DijkstraManualHeap <num_vertices> [<origem_aresta> <destino_aresta> <peso_aresta> ...] <nome_origem> <nome_destino>");
            // Exemplo: java DijkstraManualHeap 5 0 1 10 0 3 5 1 2 1 1 3 2 2 4 4 3 1 3 3 2 9 3 4 2 4 2 6 s z
            return;
        }

        int numVertices = Integer.parseInt(args[0]);
        // Gerar nomes de vértices genéricos se eles não forem fornecidos explicitamente
        // (ex: "0", "1", "2", ... "N-1")
        String[] nomesVertices = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            nomesVertices[i] = String.valueOf(i);
        }

        Grafo g = new Grafo(nomesVertices);

        int argIndex = 1;
        // Percorrer as arestas. Cada aresta tem 3 partes: u, v, peso.
        // O loop vai até os dois últimos argumentos, que são origem e destino.
        while (argIndex < args.length - 2) {
            try {
                int u = Integer.parseInt(args[argIndex++]);
                int v = Integer.parseInt(args[argIndex++]);
                int peso = Integer.parseInt(args[argIndex++]);
                g.adicionarAresta(u, v, peso);
            } catch (NumberFormatException e) {
                // Se encontrar um nome de vértice (não número), assumimos que chegamos à origem/destino
                argIndex--; // Volta o índice para o início do nome da origem
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro: Formato de aresta inválido. Esperado <origem> <destino> <peso>.");
                return;
            }
        }

        String nomeOrigem = args[argIndex++];
        String nomeDestino = args[argIndex];

        Integer origemIndice = g.getIndice(nomeOrigem);
        Integer destinoIndice = g.getIndice(nomeDestino);

        if (origemIndice == null || destinoIndice == null) {
            System.out.println("Erro: Região de origem ou destino não encontrada no grafo.");
            return;
        }

        DijkstraManualHeap dijkstra = new DijkstraManualHeap();
        dijkstra.executar(g, origemIndice, destinoIndice);
    }
}