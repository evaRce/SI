package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.Nodo;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial =
                new ProblemaAspiradora.EstadoAspiradora(
                        ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                        ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();
        Nodo arrNodo[] = buscador.soluciona(aspiradora);
        System.out.println("\nEJERCICIO 1A");
        for(int i = 0; i < arrNodo.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo[i] );
        }


        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        Nodo arrNodo2[] = buscador2.soluciona(aspiradora);
        System.out.println("\nEJERCICIO 1B");
        for(int i = 0; i < arrNodo2.length; i++){
            System.out.println("arrNodo[" + i + "] = " + arrNodo2[i] );
        }
        
    }
}
