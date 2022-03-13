package es.udc.sistemasinteligentes.g4_27;


import java.util.ArrayList;
import java.util.Arrays;

public class MainEj2A {
    public static int contadorDeCeros(int[][] matriz){
        int i, j, cont = 0;
        for(i = 0; i < matriz.length; i++){
            for(j = 0; j < matriz.length; j++){
                if(matriz[i][j] == 0)
                    cont++;
            }
        }
        System.out.println("cont = " + cont);
        return cont;
    }

    public static void main(String[] args) {
        int x;
        int y;
        ArrayList<ArrayList<Integer>> matriz =
                new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> fila1 = new ArrayList<Integer>();
        ArrayList<Integer> fila2 = new ArrayList<Integer>();
        ArrayList<Integer> fila3 = new ArrayList<Integer>();

        fila1.add(4);
        fila1.add(9);
        fila1.add(2);
        fila2.add(3);
        fila2.add(5);
        fila2.add(0);
        fila3.add(0);
        fila3.add(1);
        fila3.add(0);

        matriz.add(fila1);
        matriz.add(fila2);
        matriz.add(fila3);


        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
            new ProblemaCuadradoMagico.EstadoCuadradoMagico(matriz);

        estadoInicial.getAvailableNums().toString();

        /*
        //ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial =
        //        new ProblemaCuadradoMagico.EstadoCuadradoMagico(matriz, );
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda estrAnchura = new EstrategiaBusquedaAnchura();
        estrAnchura.soluciona(cuadrado);

        EstrategiaBusqueda estrProfundidad = new EstrategiaBusquedaProfundidad();*/
    }
}
