package dijkstra;

import java.util.*;

// =================================================================================================
// Classes do Grafo (Reutilizadas e Adaptadas para Dijkstra com PriorityQueue)
// =================================================================================================

// As classes Grafo, Vertice e Aresta são idênticas às usadas na Resolução 1.
// Apenas repetidas aqui para que o pacote seja autocontido.
class Grafo {

    private final Vertice[] vertices;
    private List<List<Aresta>> adjacencias;
    private Map<String, Integer> nomeParaIndice;

    Grafo(String[] nomesVertices) {
        this.adjacencias = new ArrayList<>();
        this.vertices = new Vertice[nomesVertices.length];
        this.nomeParaIndice = new HashMap<>();

        for (int i = 0; i < nomesVertices.length; i++) {
            this.adjacencias.add(new ArrayList<>());
            this.vertices[i] = new Vertice(nomesVertices[i], i);
            this.nomeParaIndice.put(nomesVertices[i], i);
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
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

    static class Vertice implements Comparable<Vertice> {
        String nome;
        int indice;
        int distancia;
        Vertice predecessor;

        Vertice(String nome, int indice) {
            this.nome = nome;
            this.indice = indice;
            this.distancia = Integer.MAX_VALUE;
            this.predecessor = null;
        }

        @Override
        public int compareTo(Vertice other) {
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
// Algoritmo de Dijkstra (com java.util.PriorityQueue)
// =================================================================================================
public class DijkstraJavaAPI {

    public void executar(Grafo g, int origemIndice, int destinoIndice) {
        inicializarFonteUnica(g, origemIndice);

        // Usando java.util.PriorityQueue
        // A PriorityQueue ordena automaticamente com base no compareTo do objeto Vertice.
        PriorityQueue<Grafo.Vertice> pq = new PriorityQueue<>();

        // Adiciona todos os vértices à fila de prioridade.
        // Inicialmente, apenas a origem terá distancia 0.
        // Os outros terão Integer.MAX_VALUE, mas serão relaxados e readicionados/atualizados.
        for (Grafo.Vertice v : g.getVertices()) {
            pq.add(v); // Adiciona todos os vértices
        }

        // Para evitar processar o mesmo vértice com distâncias maiores
        // (A PriorityQueue da API não tem decreaseKey nativo, então re-inserimos.
        // Usar um conjunto de visitados garante que processamos cada vértice apenas uma vez
        // pela sua menor distância.)
        boolean[] visitado = new boolean[g.quantidadeVertices()];

        while (!pq.isEmpty()) {
            Grafo.Vertice u = pq.poll(); // Extrai o vértice com a menor distância atual
            
            // Se já processamos este vértice com uma distância menor, ignoramos esta entrada
            if (visitado[u.indice]) {
                continue;
            }
            visitado[u.indice] = true;

            // Se chegamos ao destino, podemos parar
            if (u.indice == destinoIndice) {
                break;
            }

            for (Grafo.Aresta aresta : g.vizinhos(u.indice)) {
                Grafo.Vertice v = g.getVertices()[aresta.destino];
                // Verifica se 'u.distancia' não é infinito para evitar overflow
                if (!visitado[v.indice] && u.distancia != Integer.MAX_VALUE && v.distancia > u.distancia + aresta.peso) {
                    v.distancia = u.distancia + aresta.peso;
                    v.predecessor = u;
                    // A "atualização" na PriorityQueue é feita inserindo uma nova cópia do vértice
                    // com a distância atualizada. A `visitado` array se encarrega de ignorar as cópias "obsoletas".
                    pq.add(v);
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
        Collections.reverse(caminho);

        System.out.print("Caminho encontrado: ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caminho.size(); i++) {
            sb.append(caminho.get(i));
            if (i < caminho.size() - 1) {
                Grafo.Vertice u = g.getVertices()[g.getIndice(caminho.get(i))];
                Grafo.Vertice v = g.getVertices()[g.getIndice(caminho.get(i + 1))];
                int pesoAresta = -1;
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
        if (args.length < 5) {
            System.out.println("Uso: java DijkstraJavaAPI <num_vertices> [<origem_aresta> <destino_aresta> <peso_aresta> ...] <nome_origem> <nome_destino>");
            // Exemplo: java DijkstraJavaAPI 5 0 1 10 0 3 5 1 2 1 1 3 2 2 4 4 3 1 3 3 2 9 3 4 2 4 2 6 s z
            return;
        }

        int numVertices = Integer.parseInt(args[0]);
        String[] nomesVertices = new String[numVertices];
        for (int i = 0; i < numVertices; i++) {
            nomesVertices[i] = String.valueOf(i);
        }

        Grafo g = new Grafo(nomesVertices);

        int argIndex = 1;
        while (argIndex < args.length - 2) {
            try {
                int u = Integer.parseInt(args[argIndex++]);
                int v = Integer.parseInt(args[argIndex++]);
                int peso = Integer.parseInt(args[argIndex++]);
                g.adicionarAresta(u, v, peso);
            } catch (NumberFormatException e) {
                argIndex--;
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

        DijkstraJavaAPI dijkstra = new DijkstraJavaAPI();
        dijkstra.executar(g, origemIndice, destinoIndice);
    }
}