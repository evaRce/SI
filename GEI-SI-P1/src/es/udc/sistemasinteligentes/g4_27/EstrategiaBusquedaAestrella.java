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
        Estado state1, state2;
        Nodo nodoActual = new Nodo();
        Nodo nodoPadre = new Nodo(estadoActual, null, null, 0f, 0f);
        int createdNodos = 1, expandNodos = 0;
        boolean modificado = false;
        float costeAcc; //

        frontera.add(nodoPadre);

        while (!frontera.isEmpty()) {
            Collections.sort(frontera);
            nodoActual = pop(frontera);  //mirar la estructura del pop()
            state1 = nodoActual.getEstadoNodo();
            if(p.esMeta(state1)){
                modificado = true;
                break ;
            } else {
                explorados.add(nodoActual);
                expandNodos++;
                listH = sucesores(nodoActual, p);
                for(Nodo n: listH){
                    createdNodos++;
                    state2 = n.getEstadoNodo();
                    costeAcc = n.getAccionNodo().getCoste();
                    n.setCosteNodo(nodoActual.getCosteNodo() + costeAcc);
                    n.setFuncionFNodo(n.getCosteNodo() + h.evalua(state2));
                    if(!containsEstado(explorados, state2)){
                        if(containsEstado(frontera, state2)){
                            Nodo duplicatedNodo = getDuplicateState(frontera, state2);
                            if(n.getFuncionFNodo() < duplicatedNodo.getFuncionFNodo()){
                                eliminarElemento(frontera, duplicatedNodo);
                                frontera.add(n);
                            }
                        } else {
                            frontera.add(n);
                        }
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

    /*se le pasa un nodo conteniendo el estadoMeta ,que se encargara de reconstruir
      el camino desde el estadoMeta hasta el estadoInicial */
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
            Nodo nodo1 = new Nodo(res, nodoPadre, acc, 0f, 0f);
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
            if(n.getEstadoNodo().equals(estado))
                return true;
        }
        return false;
    }

    //pop del elemento con menor funcion de evaluacion-
    private Nodo pop(List<Nodo> list){
        Nodo n;
        n = list.get(0);
        list.remove(0);
        return n;
    }

    //dado un estado comprueba si hay otro nodo en frontera con el mismo estado
    //si lo hay devuelve el nodo
    private Nodo getDuplicateState(List<Nodo> frontera, Estado estado){
        Nodo duplicatedNodo = new Nodo();
        for(Nodo n : frontera) {
            if (n.getEstadoNodo().equals(estado)){
                duplicatedNodo = n;
                break;
            }
        }
        return duplicatedNodo;
    }

    //elimina un nodo(n) de la lista de nodos(list)
    private void eliminarElemento(List<Nodo> list, Nodo n){
        int i;
        for(Nodo nodo : list){
            if(nodo.equals(n)) {
                i = list.indexOf(nodo);
                list.remove(i);
                break;
            }
        }
    }
}
