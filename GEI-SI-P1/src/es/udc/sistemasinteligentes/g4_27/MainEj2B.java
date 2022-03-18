package es.udc.sistemasinteligentes.g4_27;

import java.util.ArrayList;

public class MainEj2B {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> fila1 = new ArrayList<Integer>();
        ArrayList<Integer> fila2 = new ArrayList<Integer>();
        ArrayList<Integer> fila3 = new ArrayList<Integer>();
        //Inicializar matriz
        fila1.add(2);
        fila1.add(0);
        fila1.add(0);
        fila2.add(0);
        fila2.add(0);
        fila2.add(0);
        fila3.add(0);
        fila3.add(0);
        fila3.add(0);

        matriz.add(fila1);
        matriz.add(fila2);
        matriz.add(fila3);

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
                new ProblemaCuadradoMagico.EstadoCuadradoMagico(matriz);
        ProblemaCuadradoMagico.HeuristicaCuadradoMagico heuristicaCuadrado =
                new ProblemaCuadradoMagico.HeuristicaCuadradoMagico();

        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        System.out.println("\nEJERCICIO 2B con EstrategiaBusquedaAestrella");
        EstrategiaBusquedaInformada estrAestr = new EstrategiaBusquedaAestrella();
        Nodo arrNodo[] = estrAestr.soluciona(cuadrado, heuristicaCuadrado);
        for(int i = 0; i < arrNodo.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo[i]);
        }
    }
}
