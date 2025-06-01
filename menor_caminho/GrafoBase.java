package menor_caminho;

import java.util.*;
public class GrafoBase {
  private int vertices;
  private LinkedList <Integer> [] adjacencias;

  @SuppressWarnings("unchecked")
  GrafoBase(int vertices){
    this.vertices = vertices;
    this.adjacencias = new LinkedList [this.vertices];
    for(int i = 0; i < this.vertices; i++){
      this.adjacencias[i] = new LinkedList<Integer>();
    }
  }

  void menorCaminho(int origem, int destino){
    int [] distancias = new int[vertices]; //já começa inicializado por zero
    boolean [] visitados = new boolean[vertices];//já começa inicializado por false
    Queue <Integer> fila = new LinkedList<>();
    fila.offer(origem);
    distancias[origem] = 0;
    visitados[origem] = true;
    while(!fila.isEmpty()){
      var atual = fila.poll();
      for (int vizinho : adjacencias[atual]){
        if(!visitados[vizinho]){
          fila.offer(vizinho);
          visitados[vizinho] = true;
          distancias[vizinho] = distancias[atual] + 1;
        }
      }
    }    
    System.out.printf(
      "Distância de %d até %d: %d\n",
      origem, destino, distancias[destino]
    );

  }

  void adicionarAresta(int a1, int a2){
    this.adjacencias[a1].add(a2);
    this.adjacencias[a2].add(a1);
  }

  //java Grafo 7 0 1 0 2 0 5 1 3 2 4 4 6 5 6 0 5
  public static void main(String[] args) {
    int vertices = Integer.parseInt(args[0]);
    GrafoBase grafo = new GrafoBase(vertices);
    for (int i = 1; i < args.length - 2; i++){
      grafo.adicionarAresta(
        Integer.parseInt(args[i]),
        Integer.parseInt(args[i + 1])
      );
    }
    grafo.menorCaminho(
      Integer.parseInt(args[args.length - 2]),
      Integer.parseInt(args[args.length - 1])
    );  
  }
}