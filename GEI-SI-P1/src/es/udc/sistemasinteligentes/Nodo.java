package es.udc.sistemasinteligentes;

import java.util.List;

public class Nodo {
    //atributos
    Estado estado;
    Nodo padre;
    Accion accion;

    //contructores
    public Nodo(){}

    public Nodo (Estado estado){
        this.estado = estado;
    }

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }

    public Estado getEstadoNodo(){
        return this.estado;
    }

    public Nodo getPadreNodo(){
        return this.padre;
    }

    public Accion getAccionNodo(){
        return this.accion;
    }

    @Override
    public String toString(){
        return "(" + this.estado + ", " + this.padre + ", " + this.accion + ")";
    }

    public Nodo copyNodo(Nodo nodoForCopy){
        Nodo auxNodo = new Nodo(nodoForCopy.getEstadoNodo(), nodoForCopy, nodoForCopy.getAccionNodo());
        /*System.out.println("COPIA 1: ");
        System.out.println(auxNodo.getEstadoNodo());
        System.out.println(auxNodo.getPadreNodo());
        System.out.println(auxNodo.getAccionNodo());*/
        return auxNodo;
    }

}
