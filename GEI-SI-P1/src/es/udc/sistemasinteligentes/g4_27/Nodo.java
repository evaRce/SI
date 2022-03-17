package es.udc.sistemasinteligentes.g4_27;

public class Nodo implements Comparable<Nodo>{
    //atributos
    Estado estado;
    Nodo padre;
    Accion accion;
    float coste; //coste del camino
    float funcionF;

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

    public Nodo(Estado estado, Nodo padre, Accion accion,
                float coste, float funcionF) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        this.coste = coste;
        this.funcionF = funcionF;
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

    public float getCosteNodo(){
        return this.coste;
    }

    public float getFuncionFNodo(){
        return this.funcionF;
    }

    @Override
    public String toString(){
        return "(" + this.estado + ", " + this.padre +
                ", " + this.accion + ", " + this.coste +
                ", " + this.funcionF +")";
    }

    @Override
    public int compareTo(Nodo nodo) {
        return 1;//this.getEstadoNodo().compareTo(nodo.getEstadoNodo());
    }
}
