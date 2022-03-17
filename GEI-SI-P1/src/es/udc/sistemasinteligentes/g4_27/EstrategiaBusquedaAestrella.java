package es.udc.sistemasinteligentes.g4_27;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaBusquedaAestrella implements EstrategiaBusquedaInformada{

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        ArrayList<Nodo> frontera = new ArrayList<Nodo>();
        ArrayList<Nodo> explorados = new ArrayList<Nodo>();
        List<Nodo> listH; //lista de sucesores
        Estado estadoActual = p.getEstadoInicial();
        Estado state1, state2, state3;
        Nodo nodoActual = new Nodo();
        Nodo nodoPadre = new Nodo(estadoActual, null, null);
        boolean modificado = false;
        int createdNodos = 1, expandNodos = 0;

        float c = 0f; //q es?

        frontera.add(nodoPadre);

        while (!frontera.isEmpty()) {
            nodoActual = pop(frontera);  //mirar la estructura del pop()
            state1 = nodoActual.getEstadoNodo();
            if(p.esMeta(state1)){
                break ;
            } else {
                modificado = true;
                explorados.add(nodoActual);
                expandNodos++;
                listH = sucesores(nodoActual, p);
                for(Nodo n: listH){
                    modificado = true;
                    createdNodos++;
                    state2 = n.getEstadoNodo();
                    if(containsEstado(explorados, state2)){
                        n.setCosteNodo(nodoActual.getCosteNodo() + c);
                        n.setFuncionFNodo(h.evalua(state2));
                    } else {
                        if(!containsEstado(frontera, state2) ){
                            frontera.add(n);
                        } else {
                            float nF = 0f;
                            if(n.getFuncionFNodo() < nF){

                            }
                        }
                    }
                    state3 = n.getEstadoNodo();
                    if(p.esMeta(state3)){
                        nodoActual = n;
                        break;
                    } else {

                    }
                }
            }
            if (!modificado)
                throw new Exception("No se ha podido encontrar una solución");
        }

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
        Nodo n = list.get(0);
        list.remove(0);
        return n;
    }
}
