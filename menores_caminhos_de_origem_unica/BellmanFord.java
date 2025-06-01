package menores_caminhos_de_origem_unica;

import java.util.*;

/**
 * Classe que representa um Grafo direcionado com arestas ponderadas.
 * Contém classes internas para Vertice e Aresta.
 */
class Grafo {

  private final Vertice[] vertices; // Array de objetos Vertice, indexados por seu 'indice'.
  private List<List<Aresta>> adjacencias; // Lista de listas de adjacência.
                                          // adjacencias.get(u) retorna a lista de arestas saindo de 'u'.

  /**
   * Construtor da classe Grafo.
   *
   * @param nomesVertices Um array de Strings contendo os nomes dos vértices.
   *                      A ordem desses nomes define o índice de cada vértice (0,
   *                      1, 2...).
   *                      Complexidade: O(V), onde V é o número de vértices, para
   *                      inicializar as estruturas.
   */
  Grafo(String[] nomesVertices) {
    this.adjacencias = new ArrayList<>(); // Inicializa a lista principal para adjacências.
    this.vertices = new Vertice[nomesVertices.length]; // Cria o array de objetos Vertice.

    // Para cada nome de vértice, cria um objeto Vertice e uma lista de adjacência
    // vazia.
    // Complexidade: O(V).
    for (int i = 0; i < nomesVertices.length; i++) {
      this.adjacencias.add(new ArrayList<>()); // Adiciona uma nova lista para as adjacências do vértice 'i'.
      this.vertices[i] = new Vertice(nomesVertices[i], i); // Cria e armazena o objeto Vertice.
    }
  }

  /**
   * Adiciona uma aresta direcionada ao grafo.
   *
   * @param origem  O índice do vértice de origem.
   * @param destino O índice do vértice de destino.
   * @param peso    O peso da aresta.
   *                Complexidade: O(1) para adicionar a aresta à lista de
   *                adjacência.
   */
  public void adicionarAresta(int origem, int destino, int peso) {
    adjacencias.get(origem).add(new Aresta(destino, peso));
  }

  /**
   * Retorna a lista de arestas que saem de um determinado vértice.
   *
   * @param u O índice do vértice.
   * @return Uma lista de objetos Aresta.
   *         Complexidade: O(1).
   */
  public List<Aresta> vizinhos(int u) {
    return adjacencias.get(u);
  }

  /**
   * Retorna o array de todos os objetos Vertice do grafo.
   *
   * @return O array de Vertices.
   *         Complexidade: O(1).
   */
  public Vertice[] getVertices() {
    return vertices;
  }

  /**
   * Retorna o número total de vértices no grafo.
   *
   * @return O número de vértices.
   *         Complexidade: O(1).
   */
  public int quantidadeVertices() {
    return vertices.length;
  }

  /**
   * Classe interna que representa um vértice no grafo.
   */
  static class Vertice {
    String nome; // Nome do vértice (e.g., "s", "t").
    int indice; // Índice numérico do vértice (e.g., 0, 1, 2).
    int distancia; // Distância estimada da fonte até este vértice.
                   // Inicializada com Integer.MAX_VALUE.
    Vertice predecessor; // O vértice predecessor no caminho mais curto da fonte.
                         // Usado para reconstruir o caminho.

    /**
     * Construtor da classe Vertice.
     *
     * @param nome   O nome do vértice.
     * @param indice O índice numérico do vértice.
     *               Complexidade: O(1).
     */
    Vertice(String nome, int indice) {
      this.nome = nome;
      this.indice = indice;
      distancia = Integer.MAX_VALUE; // Define distância inicial como "infinito".
      predecessor = null; // Sem predecessor inicial.
    }
  }

  /**
   * Classe interna que representa uma aresta no grafo.
   */
  static class Aresta {
    int destino; // Índice do vértice de destino da aresta.
    int peso; // Peso (custo) da aresta.

    /**
     * Construtor da classe Aresta.
     *
     * @param destino O índice do vértice de destino.
     * @param peso    O peso da aresta.
     *                Complexidade: O(1).
     */
    Aresta(int destino, int peso) {
      this.destino = destino;
      this.peso = peso;
    }
  }
}

/**
 * Classe que implementa o algoritmo de Bellman-Ford para encontrar os menores
 * caminhos
 * a partir de uma única fonte em um grafo com arestas de peso negativo.
 * Também inclui detecção e exibição de ciclos de peso negativo.
 */
public class BellmanFord {

  /**
   * Executa o algoritmo de Bellman-Ford.
   *
   * @param g O grafo a ser processado.
   * @param s O índice do vértice de origem (fonte).
   * @return true se não houver ciclos de peso negativo alcançáveis da fonte,
   *         false caso contrário.
   *         Complexidade: O(V * A), onde V é o número de vértices e A é o número
   *         de arestas.
   */
  public boolean executar(Grafo g, int s) {
    // Inicializa as distâncias e predecessores para todos os vértices.
    // Complexidade: O(V).
    inicializarFonteUnica(g, s);

    // Fase de relaxamento: Repete V-1 vezes.
    // Para cada iteração, percorre todas as arestas do grafo e tenta relaxá-las.
    // Complexidade: V * (V + A) no pior caso, pois itera V-1 vezes sobre todas as
    // arestas (A).
    // Na implementação, é V * Sum(grau_saida(u)) = V * A.
    for (int i = 0; i < g.quantidadeVertices() - 1; i++) {
      // Percorre todos os vértices 'u' no grafo.
      // Complexidade: O(V).
      for (var u : g.getVertices()) {
        // Se a distância de 'u' ainda é MAX_VALUE, significa que 'u' não é alcançável
        // da fonte,
        // então suas arestas não podem ser relaxadas (evita overflow em u.distancia +
        // a.peso).
        if (u.distancia == Integer.MAX_VALUE)
          continue; // Complexidade: O(1).

        // Percorre todas as arestas que saem de 'u'.
        // Complexidade: O(grau_saida(u)).
        for (var a : g.vizinhos(u.indice)) {
          var v = g.getVertices()[a.destino]; // Obtém o vértice de destino da aresta.
          relaxar(u, v, a.peso); // Tenta relaxar a aresta (u,v) com peso 'a.peso'.
          // Complexidade: O(1) para relaxar.
        }
      }
    }

    // Fase de detecção de ciclo negativo:
    // Percorre todas as arestas novamente. Se alguma aresta pode ser relaxada,
    // há um ciclo de peso negativo alcançável da fonte.
    // Complexidade: O(V + A).
    for (var u : g.getVertices()) {
      // Se u não é alcançável, não pode fazer parte de um ciclo negativo alcançável
      // da fonte.
      if (u.distancia == Integer.MAX_VALUE)
        continue; // Complexidade: O(1).

      for (var a : g.vizinhos(u.indice)) {
        var v = g.getVertices()[a.destino];
        // Se a distância de 'v' ainda pode ser diminuída, significa que existe um ciclo
        // negativo.
        if (v.distancia > u.distancia + a.peso) {
          // EXERCÍCIO 2: Ciclo de peso negativo detectado.
          System.out.printf("Caminho a partir da fonte %s contém um ciclo de peso negativo.\n",
              g.getVertices()[s].nome);
          exibirCicloNegativo(g, s, u, v); // Chama a função para exibir o ciclo.
          return false; // Retorna false indicando a presença de ciclo negativo.
        }
      }
    }
    return true; // Retorna true se nenhum ciclo negativo foi encontrado.
  }

  /**
   * Tenta relaxar uma aresta (u, v) com peso w.
   * Se um caminho mais curto para v é encontrado através de u, atualiza a
   * distância de v e seu predecessor.
   *
   * @param u O vértice de origem da aresta.
   * @param v O vértice de destino da aresta.
   * @param w O peso da aresta (u, v).
   *          Complexidade: O(1).
   */
  private void relaxar(Grafo.Vertice u, Grafo.Vertice v, int w) {
    // Evita overflow quando u.distancia é MAX_VALUE.
    // Se u.distancia é MAX_VALUE, u + w também seria muito grande, ou poderia
    // causar overflow
    // se w for negativo e MAX_VALUE - w for um número grande positivo.
    // A comparação v.distancia > u.distancia + w é problemática se u.distancia for
    // MAX_VALUE.
    // Uma forma segura é verificar se u.distancia não é MAX_VALUE antes de somar.
    if (u.distancia != Integer.MAX_VALUE && v.distancia > u.distancia + w) {
      v.distancia = u.distancia + w; // Atualiza a distância de v.
      v.predecessor = u; // Define u como predecessor de v.
    }
  }

  /**
   * Inicializa as distâncias e predecessores para a execução do Bellman-Ford a
   * partir de uma fonte.
   * A distância da fonte é 0, e as demais são "infinito" (MAX_VALUE).
   * Predecessores são nulos.
   *
   * @param g O grafo.
   * @param s O índice do vértice fonte.
   *          Complexidade: O(V).
   */
  private void inicializarFonteUnica(Grafo g, int s) {
    // Reseta distâncias e predecessores de todos os vértices.
    // Complexidade: O(V).
    for (Grafo.Vertice v : g.getVertices()) {
      v.distancia = Integer.MAX_VALUE;
      v.predecessor = null;
    }
    // A distância da fonte para ela mesma é 0.
    g.getVertices()[s].distancia = 0;
  }

  /**
   * **NOVO MÉTODO PARA EXERCÍCIO 2:**
   * Exibe um ciclo de peso negativo encontrado no grafo.
   * Começa a partir de um vértice 'v' que teve sua distância relaxada na V-ésima
   * iteração,
   * segue os predecessores até encontrar um vértice que se repete, indicando o
   * ciclo.
   *
   * @param g     O grafo.
   * @param fonte O índice do vértice fonte da execução atual do Bellman-Ford.
   * @param u     O vértice de origem da aresta que causou a detecção (u, v).
   * @param v     O vértice de destino da aresta que causou a detecção (u, v).
   *              Complexidade: O(V), no pior caso.
   */
  private void exibirCicloNegativo(Grafo g, int fonte, Grafo.Vertice u, Grafo.Vertice v) {
    // Para encontrar um vértice no ciclo: siga 'V' predecessores a partir de 'v'.
    // Isso garante que você aterrise em algum vértice que faz parte do ciclo.
    // Complexidade: O(V).
    Grafo.Vertice cicloVertice = v;
    for (int i = 0; i < g.quantidadeVertices(); i++) {
      if (cicloVertice.predecessor == null) { // Caso não haja predecessor (ciclo não alcançável da fonte)
        System.out.println("Não foi possível reconstruir o ciclo negativo a partir de " + fonte + ".");
        return;
      }
      cicloVertice = cicloVertice.predecessor;
    }

    // Agora, 'cicloVertice' está em algum lugar dentro do ciclo.
    // Percorra os predecessores novamente até encontrar o ponto de partida do
    // ciclo.
    // Complexidade: O(V).
    List<String> caminhoCiclo = new ArrayList<>();
    Set<Integer> visitadosNoCiclo = new HashSet<>();
    Grafo.Vertice current = cicloVertice;

    while (!visitadosNoCiclo.contains(current.indice)) {
      visitadosNoCiclo.add(current.indice);
      caminhoCiclo.add(current.nome);
      if (current.predecessor == null) {
        // Isso não deveria acontecer se o ciclo for detectado corretamente
        System.out.println("Erro ao reconstruir o ciclo negativo.");
        return;
      }
      current = current.predecessor;
    }

    // O ponto de partida do ciclo é 'current'.
    // Agora, remova os vértices que não fazem parte do ciclo a partir do início da
    // lista
    // (os que vieram antes do ponto de partida do ciclo).
    // Complexidade: O(V).
    int startIndex = caminhoCiclo.indexOf(current.nome);
    for (int i = 0; i < startIndex; i++) {
      caminhoCiclo.remove(0);
    }

    // Reverte a ordem para exibir do início ao fim do ciclo.
    Collections.reverse(caminhoCiclo);
    caminhoCiclo.add(caminhoCiclo.get(0)); // Adiciona o primeiro elemento novamente para fechar o ciclo.

    System.out.print("Ciclo de peso negativo: ");
    // Complexidade: O(V).
    for (int i = 0; i < caminhoCiclo.size(); i++) {
      System.out.print(caminhoCiclo.get(i));
      if (i < caminhoCiclo.size() - 1) {
        System.out.print(" -> ");
      }
    }
    System.out.println();
  }

  /**
   * Método principal para executar o programa.
   * **MODIFICADO PARA EXERCÍCIOS 1, 2 e 3.**
   *
   * @param args Argumentos da linha de comando (não usados diretamente para este
   *             problema,
   *             o grafo é hardcoded para o exercício 1).
   *             Complexidade total do main:
   *             Para cada fonte (V fontes):
   *             Execução do Bellman-Ford: O(V * A)
   *             Exibição dos resultados: O(V)
   *             Exibição do ciclo negativo: O(V)
   *             Total: O(V * (V * A)) = O(V^2 * A).
   */
  public static void main(String[] args) {
    // EXERCÍCIO 1: Montar o grafo da imagem
    String[] nomes = { "s", "t", "x", "y", "z" };
    Grafo g = new Grafo(nomes);

    // Adicionando as arestas conforme a imagem
    // (origem, destino, peso) -> (indiceOrigem, indiceDestino, peso)
    // s=0, t=1, x=2, y=3, z=4

    g.adicionarAresta(0, 1, 6); // s -> t (6)
    g.adicionarAresta(0, 3, 7); // s -> y (7)
    g.adicionarAresta(1, 2, 5); // t -> x (5)
    g.adicionarAresta(1, 4, -4); // t -> z (-4)
    g.adicionarAresta(1, 3, -8); // t -> y (8)
    g.adicionarAresta(2, 1, -2); // x -> t (-2)
    g.adicionarAresta(3, 2, 2); // y -> x (-3)
    g.adicionarAresta(3, 4, 9); // y -> z (9)
    g.adicionarAresta(4, 0, 2); // z -> s (2)
    g.adicionarAresta(4, 2, -4); // z -> x (-4)

    BellmanFord bellmanFord = new BellmanFord();

    // EXERCÍCIO 3: Exibir menores caminhos tendo como fonte cada um dos vértices.
    // Complexidade: O(V) para este loop.
    for (int i = 0; i < g.quantidadeVertices(); i++) {
      int fonteIndice = i;
      Grafo.Vertice fonteVertice = g.getVertices()[fonteIndice];

      System.out.printf("\n--- Executando Bellman-Ford a partir da fonte: %s ---\n", fonteVertice.nome);

      // Executa o Bellman-Ford.
      // Complexidade: O(V * A).
      boolean semCicloNegativo = bellmanFord.executar(g, fonteIndice);

      // Exibe os resultados (distâncias e predecessores).
      System.out.println("Resultados para a fonte " + fonteVertice.nome + ":");
      // Complexidade: O(V).
      for (Grafo.Vertice v : g.getVertices()) {
        if (v.distancia == Integer.MAX_VALUE) {
          System.out.printf("Distância de %s a %s: Infinito\n", fonteVertice.nome, v.nome);
          System.out.printf("Predecessor de %s: Não alcançável\n", v.nome);
        } else {
          System.out.printf("Distância de %s a %s: %d\n", fonteVertice.nome, v.nome, v.distancia);
          System.out.printf(
              "Predecessor de %s: %s\n", v.nome,
              v.predecessor != null ? v.predecessor.nome : "Não tem predecessor");
        }
      }

      if (!semCicloNegativo) {
        // Mensagem de ciclo negativo já é impressa dentro de 'executar' e
        // 'exibirCicloNegativo'.
      } else {
        System.out.println("Nenhum ciclo de peso negativo encontrado a partir desta fonte.");
      }
    }
  }
}