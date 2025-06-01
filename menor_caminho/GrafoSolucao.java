package menor_caminho;

import java.util.*;

/**
 * Classe que representa um Grafo e implementa o algoritmo de Busca em Largura (BFS)
 * para encontrar e exibir os menores caminhos.
 *
 * Baseado na solução original fornecida e adaptado para os exercícios propostos.
 */
public class GrafoSolucao {
    private int vertices; // Número de vértices no grafo.
    // Array de listas ligadas para representar as listas de adjacência.
    // Cada adjacencias[i] contém uma lista de vértices adjacentes ao vértice i.
    private LinkedList<Integer>[] adjacencias;

    /**
     * Construtor da classe Grafo.
     * Inicializa o grafo com um número específico de vértices.
     *
     * @param vertices O número total de vértices no grafo.
     * Complexidade: O(V), onde V é o número de vértices.
     */
    @SuppressWarnings("unchecked") // Suprime o aviso de tipo para a criação do array de LinkedLists.
    GrafoSolucao(int vertices) {
        this.vertices = vertices; // Atribui o número de vértices.
        this.adjacencias = new LinkedList[this.vertices]; // Cria o array de LinkedLists.

        // Inicializa cada lista de adjacência para evitar NullPointerException.
        // Complexidade: O(V).
        for (int i = 0; i < this.vertices; i++) {
            this.adjacencias[i] = new LinkedList<>();
        }
    }

    /**
     * Adiciona uma aresta ao grafo.
     * Assume que o grafo é não-direcionado, adicionando a aresta em ambas as direções.
     *
     * @param a1 O primeiro vértice da aresta.
     * @param a2 O segundo vértice da aresta.
     * Complexidade: O(1).
     */
    void adicionarAresta(int a1, int a2) {
        this.adjacencias[a1].add(a2); // Adiciona a2 à lista de adjacência de a1.
        this.adjacencias[a2].add(a1); // Adiciona a1 à lista de adjacência de a2.
    }

    /**
     * **MODIFICADO PARA EXERCÍCIO 1:**
     * Encontra e exibe o menor caminho (baseado no número de arestas) de uma origem a um destino
     * usando BFS, e mostra o caminho no formato especificado.
     *
     * @param origem  O vértice de onde a busca deve começar.
     * @param destino O vértice que se deseja alcançar.
     * Complexidade: O(V + A), onde V é o número de vértices e A é o número de arestas,
     * para a BFS. A reconstrução do caminho é O(V) no pior caso.
     */
    void menorCaminho(int origem, int destino) {
        // Arrays para a BFS:
        int[] distancias = new int[vertices]; // distâncias da origem para cada vértice. Inicializa com 0.
        boolean[] visitados = new boolean[vertices]; // se o vértice foi visitado. Inicializa com false.
        int[] predecessores = new int[vertices]; // **NOVO:** Para armazenar o predecessor de cada vértice no caminho.
                                                 // Usado para reconstruir o caminho.
                                                 // Inicializa com 0.

        // Fila para a BFS.
        Queue<Integer> fila = new LinkedList<>();

        // Inicializa predecessores com -1 para indicar que nenhum predecessor foi encontrado ainda.
        // Complexidade: O(V).
        for (int i = 0; i < vertices; i++) {
            predecessores[i] = -1;
        }

        // Inicia a BFS a partir da origem.
        fila.offer(origem);
        distancias[origem] = 0;
        visitados[origem] = true;

        // Loop principal da BFS.
        // Complexidade: O(V + A).
        while (!fila.isEmpty()) {
            int atual = fila.poll(); // Remove o vértice atual da fila.

            // Se o vértice atual é o destino, o caminho mais curto foi encontrado.
            // Complexidade: O(1).
            if (atual == destino) {
                // EXERCÍCIO 1: Imprime o caminho no formato especificado.
                // Complexidade: O(V) no pior caso para reconstruir e imprimir o caminho.
                System.out.print("Caminho de " + origem + " até " + destino + ": ");
                imprimirCaminho(origem, destino, predecessores);
                System.out.printf(" (Distância: %d)\n", distancias[destino]);
                return; // Caminho encontrado, termina a função.
            }

            // Percorre os vizinhos do vértice atual.
            // Complexidade: O(grau do vértice atual).
            for (int vizinho : adjacencias[atual]) {
                if (!visitados[vizinho]) {
                    fila.offer(vizinho); // Adiciona o vizinho à fila.
                    visitados[vizinho] = true; // Marca como visitado.
                    distancias[vizinho] = distancias[atual] + 1; // Atualiza a distância.
                    predecessores[vizinho] = atual; // **NOVO:** Armazena o vértice atual como predecessor do vizinho.
                }
            }
        }

        // Se a fila esvaziou e o destino não foi alcançado, não há caminho.
        // Complexidade: O(1).
        System.out.printf("Não há caminho de %d até %d\n", origem, destino);
    }

    /**
     * **NOVO MÉTODO PARA EXERCÍCIO 1:**
     * Imprime o caminho do destino à origem usando o array de predecessores.
     * O caminho é reconstruído de trás para frente e depois impresso na ordem correta.
     *
     * @param origem       O vértice de origem do caminho.
     * @param destino      O vértice de destino do caminho.
     * @param predecessores Array contendo o predecessor de cada vértice no caminho BFS.
     * Complexidade: O(V), onde V é o número de vértices, no pior caso (para um caminho longo).
     */
    private void imprimirCaminho(int origem, int destino, int[] predecessores) {
        // Lista para armazenar o caminho na ordem inversa.
        LinkedList<Integer> caminho = new LinkedList<>();
        int atual = destino;

        // Reconstrói o caminho do destino até a origem usando os predecessores.
        // Complexidade: O(V) no pior caso.
        while (atual != -1) {
            caminho.addFirst(atual); // Adiciona no início para manter a ordem correta.
            atual = predecessores[atual];
        }

        // Se o caminho não começa na origem, significa que o destino não é alcançável
        // (este caso já foi tratado no bfs, mas é uma verificação de segurança).
        if (caminho.getFirst() != origem) {
            System.out.print("Caminho não encontrado ou inválido.");
            return;
        }

        // Imprime o caminho no formato "a -> (p1) b -> (p2) -> c"
        // O(V) no pior caso.
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i));
            if (i < caminho.size() - 1) {
                // Aqui o "peso" da aresta é sempre 1 no BFS.
                System.out.print(" -> (1) ");
            }
        }
    }

    /**
     * Método principal para executar o programa a partir da linha de comando.
     * **MODIFICADO PARA EXERCÍCIO 2:**
     * Agora itera sobre todos os vértices para calcular e mostrar os menores caminhos
     * de cada vértice para todos os outros.
     *
     * @param args Argumentos da linha de comando.
     * Exemplo: java Grafo 7 0 1 0 2 0 5 1 3 2 4 4 6 5 6
     * (Note que os últimos dois argumentos de origem/destino foram removidos para o Ex2)
     * Complexidade total do main:
     * O(V) para parsing do número de vértices.
     * O(V) para construção do grafo.
     * O(A) para adicionar as arestas.
     * O(V * (V + A)) para o loop duplo que chama a BFS para todos os pares origem-destino.
     * No pior caso, a complexidade é dominada pelo loop de BFS: O(V * (V + A)).
     */
    public static void main(String[] args) {
        if (args.length < 1) { // Pelo menos o número de vértices deve ser fornecido.
            System.out.println("Uso: java Grafo <num_vertices> [<aresta1_u> <aresta1_v> ...]");
            return;
        }

        int vertices = Integer.parseInt(args[0]); // Pega o número de vértices.
        GrafoBase grafo = new GrafoBase(vertices); // Cria uma instância do grafo.

        // Adiciona as arestas ao grafo.
        // Agora, os argumentos de aresta vão do índice 1 até o final (args.length).
        // Deve haver um número par de argumentos restantes para as arestas.
        if ((args.length - 1) % 2 != 0) {
            System.out.println("Erro: Número ímpar de argumentos para arestas.");
            return;
        }
        // Complexidade: O(A) onde A é o número de arestas.
        for (int i = 1; i < args.length - 1; i += 2) {
            int u = Integer.parseInt(args[i]);
            int v = Integer.parseInt(args[i + 1]);
            grafo.adicionarAresta(u, v);
        }

        System.out.println("Calculando e exibindo os menores caminhos para todos os pares de vértices:");

        // **EXERCÍCIO 2:** Itera sobre cada vértice como origem.
        // Complexidade: O(V).
        for (int origem = 0; origem < vertices; origem++) {
            // Itera sobre cada vértice como destino.
            // Complexidade: O(V).
            for (int destino = 0; destino < vertices; destino++) {
                if (origem == destino) {
                    System.out.printf("Caminho de %d até %d: %d (Distância: 0)\n", origem, destino, origem);
                    continue; // Distância de um vértice para ele mesmo é 0.
                }
                // Chama o método menorCaminho para cada par (origem, destino).
                // Complexidade da chamada: O(V + A).
                grafo.menorCaminho(origem, destino);
            }
            System.out.println("--------------------"); // Separador para clareza
        }
    }
}