package es.udc.sistemasinteligentes.g4_27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {

    public EstrategiaBusquedaAnchura() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> frontera = new ArrayList<Nodo>();
        ArrayList<Nodo> explorados = new ArrayList<Nodo>();
        List<Nodo> listH; //lista de sucesores
        Estado estadoActual = p.getEstadoInicial();
        Estado state;
        Nodo nodoActual = new Nodo();
        Nodo nodoPadre = new Nodo(estadoActual, null, null);
        boolean modificado = false;
        int createdNodos = 1, expandNodos = 0;

        if(p.esMeta(nodoPadre.getEstadoNodo())) {
            modificado = true;
            return castListToArray(reconstruye_sol(nodoPadre));
        }
        frontera.add(nodoPadre);

        externo:
        while (!frontera.isEmpty()) {
            nodoActual = pop(frontera);
            explorados.add(nodoActual);
            expandNodos++;
            listH = sucesores(nodoActual, p);

            for(Nodo n: listH){
                createdNodos++;
                state = n.getEstadoNodo();
                if(p.esMeta(state)){
                    modificado = true;
                    nodoActual = n;
                    break externo;
                } else {
                    if(!containsEstado(frontera, n.getEstadoNodo()) && !containsEstado(explorados, n.getEstadoNodo())){
                        frontera.add(n);
                    }
                }
            }
        }
        if (!modificado)
            throw new Exception("No se ha podido encontrar una solución");

        System.out.println("Numero de nodos expandidos = " + expandNodos);
        System.out.println("Numero de nodos creados = " + createdNodos);
        return castListToArray(reconstruye_sol(nodoActual));
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

    //a partir de una nodoPadre genera todos sus posibles sucesores
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

    //pasa los elementos de List<Nodo> a el array Nodo
    private Nodo[] castListToArray(List<Nodo> listNodos) {
        Nodo[] arrNodo = new Nodo[listNodos.size()];
        int i;
        for (i = 0; i < listNodos.size(); i++) {
            arrNodo[i] = listNodos.get(i);
        }
        return arrNodo;
    }

    //comprueba si dado un estado, este esta en listNodo
    private boolean containsEstado(List<Nodo> listNodo, Estado estado){
        for(Nodo n: listNodo){
            if(n.getEstadoNodo() == estado){
                return true;
            }
        }
        return false;
    }

    //pop de Estructura FIFO
    private Nodo pop(List<Nodo> list){
        Nodo n = list.get(0);
        list.remove(0);
        return n;
    }
}
