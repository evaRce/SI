package es.udc.sistemasinteligentes;

import java.util.ArrayList;
import java.util.List;

public class Estrategia implements  EstrategiaBusqueda{
    public Estrategia(){

    }


    @Override
    public Nodo[] reconstruye_sol(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        List<Nodo> listaNodos = new ArrayList<Nodo>();
        Nodo nodo = new Nodo(estadoActual);
        listaNodos.add(nodo);
        Nodo nodoHijo1;
        Nodo arrNodo[];
        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;

            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    nodoHijo1 = new Nodo(estadoActual, nodo, acc);
                    nodo = new Nodo(estadoActual, nodo, acc);

                    listaNodos.add(nodoHijo1);

                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        arrNodo = castListToArray(listaNodos);
        return arrNodo;
    }

    public Nodo[] castListToArray(List<Nodo> listNodos){
        Nodo arrNodo[] = new Nodo[listNodos.size()];
        int i;
        for(i = 0; i < listNodos.size(); i++){
            arrNodo[i] = listNodos.get(i);
        }
        return arrNodo;
    }
}
