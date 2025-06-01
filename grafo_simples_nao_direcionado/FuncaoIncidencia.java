/*
Exercício:

Implemente uma classe em Java para representar um grafo simples e não direcionado,
em que cada aresta possui um identificador textual. Utilize um objeto do tipo
Map<String, Set<Integer>> para armazenar a função de incidência φ, cuja finalidade
é associar cada aresta ao conjunto dos dois vértices que ela conecta.

O programa deve ler as arestas da entrada padrão e, ao final, exibir a função
de incidência φ, considerando que φ(e) = {u, v} representa que a aresta e liga os vértices u e v.

Entrada:
    • Um número inteiro m representando a quantidade de arestas.
    • Em seguida, m linhas contendo uma string e (identificador da aresta) e dois inteiros u e v,
      indicando que a aresta e conecta os vértices u e v.

Saída:
    Para cada aresta, exiba a função de incidência no formato:
    phi(e) = {u, v}

Exemplo:
Entrada:
3
e1 0 1
e2 0 2
e3 1 3

Saída esperada:
phi(e1) = {0, 1}
phi(e2) = {0, 2}
phi(e3) = {1, 3}
*/

import java.util.*;

public class FuncaoIncidencia {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); // número de arestas

        // Mapa para armazenar a função de incidência: aresta -> conjunto de vértices
        Map<String, Set<Integer>> phi = new LinkedHashMap<>();

        // Leitura das m arestas
        for (int i = 0; i < m; i++) {
            String aresta = sc.next();  // identificador textual da aresta
            int u = sc.nextInt();       // vértice u
            int v = sc.nextInt();       // vértice v

            // Cria o conjunto com os dois vértices e associa à aresta
            Set<Integer> vertices = new HashSet<>();
            vertices.add(u);
            vertices.add(v);
            phi.put(aresta, vertices);
            // Complexidade: O(1) para cada inserção (HashSet e LinkedHashMap)
        }

        // Impressão da função de incidência
        for (Map.Entry<String, Set<Integer>> entrada : phi.entrySet()) {
            System.out.println("phi(" + entrada.getKey() + ") = " + entrada.getValue());
            // A ordem de inserção é preservada por usar LinkedHashMap
        }

        // Complexidade total:
        // - Leitura das arestas: O(m)
        // - Inserções no mapa e conjuntos: O(1) por aresta => O(m)
        // - Impressão: O(m)
        // ⇒ Complexidade total: O(m)

        sc.close();
    }
}
