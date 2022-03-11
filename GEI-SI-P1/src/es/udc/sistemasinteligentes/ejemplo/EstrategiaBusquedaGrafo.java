package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> frontera = new ArrayList<Nodo>();
        ArrayList<Nodo> explorados = new ArrayList<Nodo>();
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo();
        Estado s;
        List<Nodo> listH;

        Nodo nodoPadre = new Nodo(estadoActual, null, null);
        int i = 1, j = 0;

        frontera.add(nodoPadre);

        while (!frontera.isEmpty()) {
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            //System.out.println("La frontera no esta vacia ");
            nodoActual = pop(frontera);
            s = nodoActual.getEstadoNodo();
            System.out.println("nodoActual = " + nodoActual.toString());
            if(p.esMeta(s)){
                break;
            } else {
                explorados.add(nodoActual);
                listH = sucesores(nodoActual, p);
                for(Nodo n: listH){
                    if(!containsEstado(frontera, n.getEstadoNodo()) && !containsEstado(explorados, n.getEstadoNodo())){
                        frontera.add(n);
                    }
                }
            }
        }
        Nodo[] arrNodo = castListToArray(reconstruye_sol(nodoActual));
        return arrNodo;
    }

    /*metodo al cual se le pasa un nodo conteniendo el estadoMeta ,
     * que se encargara de reconstruir el camino desde el estadoMeta hasta el estadoInicial */
    private List<Nodo> reconstruye_sol(Nodo nodo) {
        List<Nodo> newList = new ArrayList<Nodo>();
        Nodo actualNodo = new Nodo(nodo.getEstadoNodo(), nodo.getPadreNodo(), nodo.getAccionNodo());
        while (actualNodo != null) {
            newList.add(actualNodo);
            actualNodo = actualNodo.getPadreNodo();
        }
        Collections.reverse(newList);
        return newList;
    }

    private List<Nodo> sucesores(Nodo nodoPadre, ProblemaBusqueda p){
        List<Nodo> sucesores = new ArrayList<Nodo>();
        Accion[] accionesDisponibles = p.acciones(nodoPadre.getEstadoNodo());
        for (Accion acc: accionesDisponibles) {
            Estado res = p.result(nodoPadre.getEstadoNodo(), acc);
            Nodo nodo1 = new Nodo(res, nodoPadre, acc);
            sucesores.add(nodo1);
        }
        return sucesores;
    }

    //funcion que pasa los elementos de List<Nodo> a el array Nodo
    private Nodo[] castListToArray(List<Nodo> listNodos) {
        Nodo[] arrNodo = new Nodo[listNodos.size()];
        int i;
        for (i = 0; i < listNodos.size(); i++) {
            arrNodo[i] = listNodos.get(i);
        }
        return arrNodo;
    }

    private boolean containsEstado(List<Nodo> listNodo, Estado estado){
        for(Nodo n: listNodo){
            if(n.getEstadoNodo() == estado){
                return true;
            }
        }
        return false;
    }

    private Nodo pop(List<Nodo> list){
        Nodo n = list.get(0);
        list.remove(0);
        return n;
    }
}
