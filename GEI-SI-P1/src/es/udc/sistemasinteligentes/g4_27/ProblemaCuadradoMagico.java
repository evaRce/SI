package es.udc.sistemasinteligentes.g4_27;

import org.w3c.dom.ranges.Range;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{
    public static class EstadoCuadradoMagico extends Estado{
        private ArrayList<ArrayList<Integer>> matrizActual;
        private ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados;
        private int[] actualPos;

        public EstadoCuadradoMagico(ArrayList<ArrayList<Integer>> matrizActual){
            this.matrizActual = matrizActual;
            this.matrizUsados = new ArrayList<ArrayList<ArrayList<Integer>>>();
            initUsados(this.matrizActual, this.matrizUsados);
            this.actualPos = new int[]{0, 0};
        }

        public void initUsados(
                ArrayList<ArrayList<Integer>> matrizActual,
                ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados
        ){
            int i, j;
            ArrayList<ArrayList<Integer>> fila;
            for(i = 0; i < matrizActual.size(); i++){
                matrizUsados.add(new ArrayList<ArrayList<Integer>>());
                fila = matrizUsados.get(i);
                for(j = 0; j < matrizActual.size(); j++){
                    if(matrizActual.get(i).get(j) == 0) {
                        fila.add(new ArrayList<Integer>());
                    } else {
                        fila.add(null);
                    }
                }
                matrizUsados.set(i, fila);
            }
        }

        public ArrayList<Integer> getAvailableNums(){
            ArrayList<Integer> availables = new ArrayList<Integer>();
            int n;
            int pow = this.matrizActual.size() * this.matrizActual.size();
            for(n = 1; n <= pow; n++){
                availables.add(n);
            }
            for(ArrayList<Integer> fila : this.matrizActual){
                for(Integer col : fila){
                    if(col != 0){
                        availables.remove(col);
                    }
                }
            }
            return availables;
        }

        public boolean isValid(int num, int i, int j){
            int[] validPos = getNextPos();
            if (validPos[0] != i || validPos[1] != j)
                return false;

            if (this.matrizUsados.get(i).get(j) == null)
                return this.matrizActual.get(i).get(j) == num;
            else {
                ArrayList<Integer> availables = this.getAvailableNums();
                return availables.contains(num) && !this.matrizUsados.get(i).get(j).contains(num);
            }
        }

        public void setPosition(int i, int j, int num){
            this.matrizActual.get(i).set(j, num);
            this.matrizUsados.get(i).get(j).add(num);
            this.actualPos[0] = i;
            this.actualPos[1] = j;
        }

        private int[] getNextPos(){
            int i = this.actualPos[0];
            int j = this.actualPos[1];
            int size = this.matrizActual.size();
            return new int[]{i + (j + 1 / size), j + 1 % size};
        }

        @Override
        public String toString() {
            return "";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            EstadoCuadradoMagico e = (EstadoCuadradoMagico) obj;
            if(matrizActual != e.matrizActual)
                return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1, i;
            for(i = 0; i < matrizActual.size(); i++)
                result = 31 * result + Arrays.hashCode(new ArrayList[]{matrizActual.get(i)});
            return result;
        }
    }

    public static class AccionCuadradoMagico extends Accion{
        private int[] posicion;
        private int num;


        public AccionCuadradoMagico(int[] posicion, int num){
            this.posicion = posicion;
            this.num = num;
        }

        @Override
        public String toString() {
            return "(" + this.posicion.toString() + ", " + Integer.toString(this.num) + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            int i = this.posicion[0];
            int j = this.posicion[1];
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico)es;
            return (esCuadrado.isValid(this.num, i, j));
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico) es;
            int i = this.posicion[0];
            int j = this.posicion[1];
            esCuadrado.setPosition(i, j, this.num);

            return esCuadrado;
        }
    }
    private Accion[] listaAcciones;

    //constructor
    public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        int n = estadoInicial.matrizActual.size();
        int i, j, a;
        for (i=0; i<n; i++)
            for (j=0; j<n; j++)
                for (a=1; a<=n; a++)
                    listaAcciones
    }
    /*public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        listaAcciones = new Accion[]{};
        }
    }*/

    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }

    @Override
    public boolean esMeta(Estado es) {
        return true;
    }

    /*public int sumaTotal(){
            return (n*((n*n)+1))/2;
        }*/
}

