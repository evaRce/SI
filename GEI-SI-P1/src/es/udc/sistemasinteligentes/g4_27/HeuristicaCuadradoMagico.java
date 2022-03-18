package es.udc.sistemasinteligentes.g4_27;

public class HeuristicaCuadradoMagico extends Heuristica {
    public HeuristicaCuadradoMagico(){}

    @Override
    public float evalua(Estado es){
        int cont = 0;
        ProblemaCuadradoMagico.EstadoCuadradoMagico estado = (ProblemaCuadradoMagico.EstadoCuadradoMagico) es;
        for(int i = 0; i < estado.getMatrizActual().size();i++){
            for(int j = 0; j < estado.getMatrizActual().size();j++){
                if(estado.getMatrizActual().get(i).get(j) == 0){
                    cont++;
                }
            }
        }
        return (float)cont;
    }

}
