package es.udc.sistemasinteligentes.g4_27;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return La lista de nodos que representan los estados recorridos desde el estado inicial hasta la meta
     */
    public abstract Nodo[] soluciona(ProblemaBusqueda p) throws Exception;
}
