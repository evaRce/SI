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

    public void setCosteNodo(float coste){
        this.coste = coste;
    }

    public void setFuncionFNodo(float funcionF){
        this.funcionF = funcionF;
    }

    @Override
    public String toString(){
        return "(" + this.estado + ", " + this.padre +
                ", " + this.accion + ", " + this.coste +
                ", " + this.funcionF +")";
    }

    @Override
    public int compareTo(Nodo nodo) {
        return Float.compare(this.funcionF, nodo.getFuncionFNodo());
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Nodo n = (Nodo)o;
        if(this.getEstadoNodo() != n.getEstadoNodo() ||
            this.getPadreNodo() != n.getPadreNodo() ||
            this.getAccionNodo() != n.getAccionNodo() ||
            this.getCosteNodo() != n.getCosteNodo() ||
            this.getFuncionFNodo() != n.getFuncionFNodo()){
            return false;
        }
        return true;
    }

    /*public int hashOneNode(Nodo n){
        int result1 = 1, valor = 0;
        if(n != null){
            valor = hashOneNode(n);

        result1 = 31 * result1 + this.estado.hashCode();
        result1 = 31 * result1 + valor;
        result1 = 31 * result1 + Integer.parseInt(this.accion.toString());
        return result1;
    }*/

    @Override
    public int hashCode(){
        int result = 1;
        result = 31 * result + this.estado.hashCode();
        result = 31 * result + this.padre.hashCode();
        result = 31 * result + Integer.parseInt(this.accion.toString());
        result = 31 * result + Float.floatToIntBits(this.coste);
        result = 31 * result + Float.floatToIntBits(this.funcionF);
        return  result;
    }
}
