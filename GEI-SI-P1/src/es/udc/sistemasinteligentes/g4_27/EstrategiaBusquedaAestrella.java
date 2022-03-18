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
        boolean modificado = false;
        int createdNodos = 1, expandNodos = 0;

        float costeAcc = 0f; //

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
                    costeAcc = n.getAccionNodo().getCoste();
                    n.setCosteNodo(nodoActual.getCosteNodo() + costeAcc);
                    n.setFuncionFNodo(n.getCosteNodo() + h.evalua(state2));
                    if(!containsEstado(explorados, state2)){
                        if(containsEstado(frontera, state2)){
                            Nodo duplicatedNodo = getDuplicateState(frontera, state2);
                            if(n.getFuncionFNodo() < duplicatedNodo.getFuncionFNodo()){
                                eliminarElemento(frontera, duplicatedNodo);
                            }
                        }
                        frontera.add(n);
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
        //float nodo1Coste = 0f;
        for (Accion acc: accionesDisponibles) {
            Estado res = p.result(nodoPadre.getEstadoNodo(), acc);
            Nodo nodo1 = new Nodo(res, nodoPadre, acc, 0f, 0f);
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
            if(n.getEstadoNodo().equals(estado))
                return true;
        }
        return false;
    }

    //pop del elemento con menor funcion de evaluacion
    private Nodo pop(List<Nodo> list){
        List<Float> listFunF = new ArrayList<Float>();
        Nodo n = new Nodo();
        float min;
        int i, j;
        for(i = 0; i < list.size(); i++){
            listFunF.add(Float.valueOf(list.get(i).getFuncionFNodo()));
        }
        Collections.sort(listFunF);
        min = listFunF.get(0);
        for(j = 0; j < list.size(); j++){
            if(list.get(i).getFuncionFNodo() == min) {
                n = list.get(i);
                list.remove(i);
                return n;
            }
        }
        return n;
    }

    private Nodo getDuplicateState(List<Nodo> frontera, Estado estado){
        Nodo duplicatedNodo = new Nodo();
        for(Nodo n : frontera){
            if(n.getEstadoNodo().equals(estado))
                duplicatedNodo = duplicatedNodo;
        }
        return duplicatedNodo;
    }

    private List<Nodo> eliminarElemento(List<Nodo> list, Nodo n){
        int i;
        for(i = 0; i < list.size(); i++){
            if(list.get(i).equals(n))
                list.remove(i);
        }
        return list;
    }
}
