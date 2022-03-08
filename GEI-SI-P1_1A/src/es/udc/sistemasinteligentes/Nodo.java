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

}
