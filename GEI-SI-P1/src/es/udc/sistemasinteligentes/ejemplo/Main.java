package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.Nodo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial =
                new ProblemaAspiradora.EstadoAspiradora(
                        ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                        ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new Estrategia4();
        //System.out.println(buscador.reconstruye_sol(aspiradora));
        List<Nodo> auxList = new ArrayList<Nodo>(buscador.reconstruye_sol(aspiradora));
        System.out.println();
        for(Nodo n : auxList)
            System.out.println(n.toString());
    }
}
