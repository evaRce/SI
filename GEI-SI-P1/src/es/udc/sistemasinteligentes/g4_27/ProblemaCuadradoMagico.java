package es.udc.sistemasinteligentes.g4_27;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaCuadradoMagico extends ProblemaBusqueda{
    public static class EstadoCuadradoMagico extends Estado implements Cloneable{
        private ArrayList<ArrayList<Integer>> matrizActual;
        private ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados;
        private int[] actualPos;

        public EstadoCuadradoMagico(ArrayList<ArrayList<Integer>> matrizActual){
            this.matrizActual = matrizActual;
            this.matrizUsados = new ArrayList<ArrayList<ArrayList<Integer>>>();
            initUsados(this.matrizActual, this.matrizUsados);
            this.actualPos = new int[]{0, 0};
        }

        public EstadoCuadradoMagico(ArrayList<ArrayList<Integer>> matrizActual,
                                    ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados,
                                    int[] actualPos){
            ArrayList<ArrayList<Integer>> copiaMatrizActual = new ArrayList<ArrayList<Integer>>(matrizActual);
            ArrayList<ArrayList<ArrayList<Integer>>> copiaMatrizUsados = new ArrayList<ArrayList<ArrayList<Integer>>>(matrizUsados);
            this.matrizActual = copyMatActual(copiaMatrizActual);
            this.matrizUsados = copyMatUsados(copiaMatrizUsados);
            this.actualPos = new int[]{actualPos[0], actualPos[1]};
        }

        /*matrizActual sera una matriz parcialmente rellena
        * matrizUsados en la posicion donde haya un numero:
        *   -igual a 0, tendra una lista vacia con los numeros que queden por introducir en matrizActual
        *   -distinto de 0, sera null  */
        public void initUsados(
                ArrayList<ArrayList<Integer>> matrizActual,
                ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados){
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

        public ArrayList<ArrayList<ArrayList<Integer>>> copyMatUsados(ArrayList<ArrayList<ArrayList<Integer>>> matrizUsados){
            ArrayList<ArrayList<ArrayList<Integer>>> newMatUsados = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(ArrayList<ArrayList<Integer>> fila : matrizUsados){
                ArrayList<ArrayList<Integer>> newFila = new ArrayList<ArrayList<Integer>>();
                for(ArrayList<Integer> col : fila){
                    if(col == null){
                        newFila.add(null);
                    } else {
                        ArrayList<Integer> newCol = new ArrayList<Integer>(col);
                        newFila.add(newCol);
                    }
                }
                newMatUsados.add(newFila);
            }
            return newMatUsados;
        }

        public ArrayList<ArrayList<Integer>> copyMatActual(ArrayList<ArrayList<Integer>> matrizActual){
            ArrayList<ArrayList<Integer>> newMatActual = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> fila : matrizActual){
                ArrayList<Integer> newFila = new ArrayList<Integer>();
                for(Integer col : fila){
                    Integer newCol = Integer.valueOf(col);
                    newFila.add(newCol);
                }
                newMatActual.add(newFila);
            }
            return newMatActual;
        }

        public ArrayList<ArrayList<Integer>> getMatrizActual(){
            return this.matrizActual;
        }

        private ArrayList<ArrayList<ArrayList<Integer>>> getMatrizUsados(){
            return this.matrizUsados;
        }
        public int[] getActualPos(){
            return this.actualPos;
        }

        /*Consigue los numeros que no están declarados en matrizActual */
        public ArrayList<Integer> getAvailableNums(){
            ArrayList<Integer> availables = new ArrayList<Integer>();
            int n, colPos;
            int pow = this.matrizActual.size() * this.matrizActual.size();
            for(n = 1; n <= pow; n++){
                availables.add(n); //lista con todos los numeros que pueden haber en la matriz
            }
            for(ArrayList<Integer> fila : this.matrizActual){
                for(Integer col : fila){
                    if(col != 0){
                        colPos = availables.indexOf(col);
                        availables.remove(colPos);
                    }
                }
            }
            return availables;
        }

        /*Indica si es posible establecer un numero en cierta posicion */
        public boolean isValid(int num){
            int i = this.actualPos[0];
            int j = this.actualPos[1];
            int size = this.matrizActual.size();
            if (this.matrizUsados.get(i).get(j) == null)
                return this.matrizActual.get(i).get(j) == num;

            ArrayList<Integer> availables = this.getAvailableNums();
            return availables.contains(num) && !this.matrizUsados.get(i).get(j).contains(num);
        }

        public void update(int num){
            int i = this.actualPos[0];
            int j = this.actualPos[1];
            if(this.matrizActual.get(i).get(j) == 0 && this.matrizUsados.get(i).get(j) != null){
                this.matrizActual.get(i).set(j, num);
                this.matrizUsados.get(i).get(j).add(num);
            }
            int size = this.matrizActual.size();
            if(i != (size-1) || j != (size-1)) {
                this.actualPos = getNextPos();
            }
        }

        private int[] getNextPos(){
            int i = this.actualPos[0];
            int j = this.actualPos[1];
            int size = this.matrizActual.size();
            return new int[]{i + ((j + 1) / size), (j + 1) % size};
        }

        public void putOnNextUsed(int num) {
            int[] next = getNextPos();
            int i = next[0];
            int j = next[1];
            int size = this.matrizActual.size();
            if(i < size && j < size && this.matrizUsados.get(i).get(j) != null)
                this.matrizUsados.get(i).get(j).add(Integer.valueOf(num));
        }

        @Override
        public EstadoCuadradoMagico clone(){
             return new EstadoCuadradoMagico(this.matrizActual, this.matrizUsados, this.actualPos);
        }

        @Override
        public String toString() {
            int i, j;
            int n = this.matrizActual.size();
            String m = "";
            for(i = 0; i < n; i++){
                for(j = 0; j < n; j++){
                    m += (this.matrizActual.get(i).get(j) + "  ");
                }
                if(i != (n-1)){
                    m += "|";
                }
            }
            return m ;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            EstadoCuadradoMagico e = (EstadoCuadradoMagico) obj;
            if(this.matrizActual != e.getMatrizActual() ||
                this.matrizUsados != e.getMatrizUsados() ||
                this.actualPos != e.getActualPos()){
                return false;
            }
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
            return "[" + Integer.toString(this.num) + "]";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico)es;
            return (esCuadrado.isValid(this.num));
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadradoMagico esCuadrado = (EstadoCuadradoMagico) es;
            EstadoCuadradoMagico newEsCuadrado = esCuadrado.clone();
            esCuadrado.putOnNextUsed(this.num);
            newEsCuadrado.update(this.num);

            return newEsCuadrado;
        }
    }

    public class HeuristicaCuadradoMagico extends Heuristica {
        @Override
        public float evalua(Estado es){
            int cont = 0;
            EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
            for(int i = 0; i < estado.matrizActual.size();i++){
                for(int j = 0; j < estado.matrizActual.size();j++){
                    if(estado.matrizActual.get(i).get(j) == 0){
                        cont++;
                    }
                }
            }
            return (float)cont;
        }

    }

    private Accion[] listaAcciones;

    //constructor
    public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        int n = estadoInicial.matrizActual.size() * estadoInicial.matrizActual.size();
        int num;
        listaAcciones = new Accion[n];
        for (num = 0; num < n; num++)
            listaAcciones[num] = new AccionCuadradoMagico(num+1);
    }

    @Override
    public Accion[] acciones(Estado es) {
        ArrayList<Accion> newArrAcciones = new ArrayList<>();
        for (int i = 0; i < listaAcciones.length; i++) {
            if (listaAcciones[i].esAplicable(es)) {
                newArrAcciones.add(listaAcciones[i]);
            }
        }
        Accion[] acc = new Accion[newArrAcciones.size()];
        for (int j = 0; j < newArrAcciones.size(); j++) {
            acc[j] = newArrAcciones.get(j);
        }
        return acc;
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadradoMagico estadoInicial = (EstadoCuadradoMagico)es;
        if(!sumFilas(estadoInicial) || !sumCols(estadoInicial) || !sumDiag(estadoInicial)){
            return false;
        }
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

