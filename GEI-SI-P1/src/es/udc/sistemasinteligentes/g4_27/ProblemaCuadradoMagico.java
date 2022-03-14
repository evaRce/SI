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

        public int[] getActualPos() {
            return actualPos;
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
        private int num;

        public AccionCuadradoMagico(int num){
            this.num = num;
        }

        @Override
        public String toString() {
            return "(" + Integer.toString(this.num) + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico)es;
            int[] pos = esCuadrado.getActualPos();
            return (esCuadrado.isValid(this.num, pos[0], pos[1]));
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico) es;
            int[] pos = esCuadrado.getActualPos();
            int i = pos[0];
            int j = pos[1];
            esCuadrado.setPosition(i, j, this.num);

            return esCuadrado;
        }
    }

    private Accion[] listaAcciones;

    //constructor
    public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        int n = estadoInicial.matrizActual.size();
        int num;
        listaAcciones = new Accion[]{};
        for (num = 1; num <= n; num++)
            listaAcciones[num-1] = new AccionCuadradoMagico(num);
    }

    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        return sumFilas(estadoInicial) &&
                sumCols(estadoInicial) &&
                sumDiag(estadoInicial);
    }

    private int sumTotal(Estado es){
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        int n = estadoInicial.matrizActual.size();
        return (n*((n*n)+1))/2;
    }

    private boolean sumFilas(Estado es){
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        boolean correcto = true;
        int n = estadoInicial.matrizActual.size();
        int i = 0, j;
        int sumFila = 0;
        int sumTotal = sumTotal(estadoInicial);
        while(i < n && correcto){
            for(j = 0; j < n; j++)
                sumFila += estadoInicial.matrizActual.get(i).get(j);
            if(sumFila != sumTotal)
                correcto = false;
            i++;
            sumFila = 0;
        }
        return correcto;
    }

    private boolean sumCols(Estado es){
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        boolean correcto = true;
        int n = estadoInicial.matrizActual.size();
        int i, j = 0;
        int sumCols = 0;
        int sumTotal = sumTotal(estadoInicial);
        while(j < n && correcto){
            for(i = 0; j < n; j++)
                sumCols += estadoInicial.matrizActual.get(j).get(i);
            if(sumCols != sumTotal)
                correcto = false;
            i++;
            sumCols = 0;
        }
        return correcto;
    }

    private boolean sumDiag(Estado es){
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        boolean correcto = true;
        int n = estadoInicial.matrizActual.size();
        int i = 0, j = 0;
        int sumDiag1 = 0, sumDiag2;
        int sumTotal = sumTotal(estadoInicial);
        while(i < n && j < n) {
            sumDiag1 += estadoInicial.matrizActual.get(i).get(j);
            i++;
            j++;
        }
        if(sumDiag1 != sumTotal)
            correcto = false;
        else {
            sumDiag2 = 0;
            i = 0;
            j = n - 1;
            while(i < n && j >= 0){
                sumDiag2 += estadoInicial.matrizActual.get(i).get(j);
                i++;
                j--;
            }
            if(sumDiag2 != sumTotal)
                correcto = false;
        }
        return correcto;
    }
}

