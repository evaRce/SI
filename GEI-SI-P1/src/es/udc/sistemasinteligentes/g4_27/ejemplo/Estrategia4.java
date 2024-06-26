package es.udc.sistemasinteligentes.g4_27.ejemplo;

import es.udc.sistemasinteligentes.g4_27.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);

        List<Nodo> listNodos = new ArrayList<Nodo>();
        Nodo nodoPadre = new Nodo(estadoActual);
        listNodos.add(nodoPadre);
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
                    Nodo nodoHijo1 = new Nodo(sc, nodoPadre, acc);
                    nodoPadre = new Nodo(sc, nodoPadre, acc); //copia de nodoHijo, el cual en la proxima iteracion será padre del nuevo nodoHijo
                    listNodos.add(nodoHijo1);

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

        return castListToArray(reconstruye_sol(listNodos.get(listNodos.size()-1)));
    }

    /*metodo al cual se le pasa un nodo conteniendo el estadoMeta ,
    * que se encargara de reconstruir el camino desde el estadoMeta hasta el estadoInicial */
    public List<Nodo> reconstruye_sol(Nodo nodo){
        List<Nodo> newList = new ArrayList<>();
        Nodo actualNodo = new Nodo(nodo.getEstadoNodo(), nodo.getPadreNodo(), nodo.getAccionNodo());
        while(actualNodo != null){
            newList.add(actualNodo);
            actualNodo = actualNodo.getPadreNodo();
        }
        Collections.reverse(newList);
        return newList;
    }

    //funcion que pasa los elementos de List<Nodo> a el array Nodo
    private Nodo[] castListToArray(List<Nodo> listNodos){
        Nodo[] arrNodo = new Nodo[listNodos.size()];
        int i;
        for(i = 0; i < listNodos.size(); i++){
            arrNodo[i] = listNodos.get(i);
        }
        return arrNodo;
    }

}
