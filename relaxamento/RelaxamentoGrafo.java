package relaxamento;

import java.util.HashMap;
import java.util.Map;

public class RelaxamentoGrafo {

    // Classe para representar uma aresta no grafo
    static class Aresta {
        String origem;
        String destino;
        int peso;

        Aresta(String origem, String destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        @Override
        public String toString() {
            return origem + " --(" + peso + ")--> " + destino;
        }
    }

    /**
     * Demonstra a operação de relaxamento para uma aresta específica.
     *
     * @param distancias Um mapa que armazena a menor distância conhecida da origem para cada vértice.
     * @param predecessores Um mapa que armazena o predecessor de cada vértice no caminho mais curto.
     * @param aresta A aresta a ser relaxada.
     */
    public static void relaxarAresta(Map<String, Integer> distancias, Map<String, String> predecessores, Aresta aresta) {
        String u = aresta.origem;
        String v = aresta.destino;
        int pesoUV = aresta.peso;

        System.out.println("\n--- Tentando relaxar a aresta: " + aresta + " ---");
        System.out.println("  Distância atual para " + u + " (dist(u)): " + distancias.get(u));
        System.out.println("  Peso da aresta (u,v): " + pesoUV);
        System.out.println("  Distância atual para " + v + " (dist(v)): " + distancias.get(v));

        // Calcula a distância tentativa para v passando por u
        int distanciaTentativa = distancias.get(u) + pesoUV;
        System.out.println("  Distância tentativa para " + v + " (dist(u) + peso(u,v)): " + distanciaTentativa);


        // Verifica se a distância tentativa é menor que a distância atual de v
        if (distanciaTentativa < distancias.get(v)) {
            System.out.println("  CONDICAO: " + distanciaTentativa + " < " + distancias.get(v) + " é VERDADEIRA!");
            // Se sim, relaxa a aresta
            distancias.put(v, distanciaTentativa);
            predecessores.put(v, u);
            System.out.println("  >>> Relaxamento realizado! <<<");
            System.out.println("    Nova distância para " + v + ": " + distancias.get(v));
            System.out.println("    Novo predecessor de " + v + ": " + predecessores.get(v));
        } else {
            System.out.println("  CONDICAO: " + distanciaTentativa + " < " + distancias.get(v) + " é FALSA.");
            System.out.println("  Não há relaxamento a ser feito para esta aresta.");
        }
    }

    public static void main(String[] args) {
        // --- GRAFO DE EXEMPLO ---
        //
        //        (Peso 2)
        //  U ------------> V
        //  ^                ^
        //  |                |
        //(5)              (1)
        //  |                |
        //  S ------------> X
        //       (Peso 8)
        //

        // Vértices do grafo
        String[] vertices = {"S", "U", "V", "X"};

        // Mapa para armazenar as distâncias da origem 'S' para cada vértice
        Map<String, Integer> distancias = new HashMap<>();
        // Mapa para armazenar o predecessor de cada vértice no caminho mais curto
        Map<String, String> predecessores = new HashMap<>();

        // Inicialização: S=0, outros=Infinito, predecessores=null
        int INFINITO = Integer.MAX_VALUE; // Usamos um valor grande para representar infinito

        for (String vertice : vertices) {
            distancias.put(vertice, INFINITO);
            predecessores.put(vertice, null);
        }
        distancias.put("S", 0); // Distância da origem para ela mesma é 0

        System.out.println("--- Estado Inicial ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);

        // Definindo as arestas do grafo e os pesos
        Aresta arestaSU = new Aresta("S", "U", 5);
        Aresta arestaSX = new Aresta("S", "X", 8);
        Aresta arestaUV = new Aresta("U", "V", 2);
        Aresta arestaXV = new Aresta("X", "V", 1);


        // --- Demonstração do Relaxamento ---

        // 1. Relaxar Aresta (S, U)
        relaxarAresta(distancias, predecessores, arestaSU);
        System.out.println("\n--- Estado Após Relaxamento (S, U) ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);

        // 2. Relaxar Aresta (S, X)
        relaxarAresta(distancias, predecessores, arestaSX);
        System.out.println("\n--- Estado Após Relaxamento (S, X) ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);

        // 3. Relaxar Aresta (U, V)
        // Neste ponto, dist(U) é 5.
        relaxarAresta(distancias, predecessores, arestaUV);
        System.out.println("\n--- Estado Após Relaxamento (U, V) ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);

        // 4. Relaxar Aresta (X, V)
        // Neste ponto, dist(X) é 8 e dist(V) é 7.
        // A aresta (X,V) tem peso 1. dist(X) + 1 = 9.
        // 9 não é menor que 7, então não deve haver relaxamento.
        relaxarAresta(distancias, predecessores, arestaXV);
        System.out.println("\n--- Estado Após Relaxamento (X, V) ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);

        // 5. Tentando relaxar (U,V) novamente (não deve mudar)
        // dist(U) é 5, dist(V) é 7. 5+2=7. 7 < 7 é falso.
        relaxarAresta(distancias, predecessores, arestaUV);
        System.out.println("\n--- Estado Após Relaxamento (U, V) Novamente ---");
        System.out.println("Distâncias: " + distancias);
        System.out.println("Predecessores: " + predecessores);
    }
}