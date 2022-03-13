package es.udc.sistemasinteligentes.g4_27.ejemplo;

import es.udc.sistemasinteligentes.g4_27.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.g4_27.ProblemaBusqueda;
import es.udc.sistemasinteligentes.g4_27.Nodo;

public class MainEj1 {
    public static void main(String[] args) throws Exception {
        //inicializar estadoInicial
        ProblemaAspiradora.EstadoAspiradora estadoInicial =
                new ProblemaAspiradora.EstadoAspiradora(
                        ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                        ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        //Estrategia4
        EstrategiaBusqueda buscador = new Estrategia4();
        Nodo arrNodo[] = buscador.soluciona(aspiradora);
        System.out.println("\nEJERCICIO 1A");
        for(int i = 0; i < arrNodo.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo[i] );
        }

        //EstrategiaBusquedaGrafo
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        Nodo arrNodo2[] = buscador2.soluciona(aspiradora);
        System.out.println("\nEJERCICIO 1B");
        for(int i = 0; i < arrNodo2.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo2[i] );
        }

    }
}
