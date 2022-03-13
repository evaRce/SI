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
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo();
        Estado state;
        List<Nodo> listH;

        Nodo nodoPadre = new Nodo(estadoActual, null, null);

        if(p.esMeta(nodoPadre.getEstadoNodo()))
            return castListToArray(reconstruye_sol(nodoPadre));

        while(!p.esMeta(estadoActual)){
            frontera.add(nodoPadre);
            while (!frontera.isEmpty()) {
                //boolean modificado = false;
                nodoActual = pop(frontera);
                //state = nodoActual.getEstadoNodo();
                explorados.add(nodoActual);
                listH = sucesores(nodoActual, p);

                externo:
                for(Nodo n: listH){
                    if(p.esMeta(n.getEstadoNodo())){
                        break externo;
                    } else {
                        if(!containsEstado(frontera, n.getEstadoNodo()) && !containsEstado(explorados, n.getEstadoNodo())){
                            frontera.add(n);
                        }
                    }
                }
            }
        }

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

    //pop de Estructura FIFO
    private Nodo pop(List<Nodo> list){
        int index = list.size()-1;
        Nodo n = list.get(index);
        list.remove(index);
        return n;
    }
}
