package es.udc.sistemasinteligentes.g4_27;


import java.util.ArrayList;
import java.util.Arrays;

public class MainEj2A {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Integer>> matriz =
                new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> fila1 = new ArrayList<Integer>();
        ArrayList<Integer> fila2 = new ArrayList<Integer>();
        ArrayList<Integer> fila3 = new ArrayList<Integer>();
        //Inicializar matriz
        fila1.add(4);
        fila1.add(9);
        fila1.add(2);
        fila2.add(3);
        fila2.add(5);
        fila2.add(0);
        fila3.add(0);
        fila3.add(1);
        fila3.add(0);

        /*fila1.add(2);
        fila1.add(0);
        fila1.add(0);
        fila2.add(0);
        fila2.add(0);
        fila2.add(0);
        fila3.add(0);
        fila3.add(0);
        fila3.add(0);*/

        matriz.add(fila1);
        matriz.add(fila2);
        matriz.add(fila3);


        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
            new ProblemaCuadradoMagico.EstadoCuadradoMagico(matriz);

        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        //EstrategiaBusquedaAnchura
        EstrategiaBusqueda estrAnchura = new EstrategiaBusquedaAnchura();
        Nodo arrNodo[] = estrAnchura.soluciona(cuadrado);
        System.out.println("\nEJERCICIO 2A con EstrategiaBusquedaAnchura");
        for(int i = 0; i < arrNodo.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo[i]);
        }

        //EstrategiaBusquedaProfundidad
        EstrategiaBusqueda estrProfundidad = new EstrategiaBusquedaProfundidad();
        Nodo arrNodo2[] = estrProfundidad.soluciona(cuadrado);
        System.out.println("\nEJERCICIO 2A con EstrategiaBusquedaProfundidad");
        for(int i = 0; i < arrNodo2.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo2[i]);
        }
    }
}
