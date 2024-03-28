package classes;


import java.util.concurrent.RecursiveAction;  // Importar clase RecursiveAction

public class TransformacionArreglo extends RecursiveAction{

    // definicion de variables
    private int[] numList;
    private int num;
    private int begin;
    private int end;
    private int threshold = 100_000;

    // constructor
    public TransformacionArreglo(int[] numList, int num, int begin, int end) {
        this.numList = numList;
        this.num = num;
        this.begin = begin;
        this.end = end;
    }


    @Override
    // funcion compute() de la clase RecursiveAction
    // se encarga de ejecutar tareas y de dividirlas en otras mas pequeñas
    protected void compute() {
        
        if (end - begin < threshold){
            // si la resta entre el inicio y el final es menor que el limite se continua con la ejecucion
            multiplicarElementos();

        } else {
            // si no, se divide la el numro de elementos entre dos para dividir la tarea
            int mitad = (begin + end) / 2;
            
            // se crear dos tareas pequeñas para terminar la principal
            TransformacionArreglo cpu1 = new TransformacionArreglo(numList, num, begin, mitad);
            TransformacionArreglo cpu2 = new TransformacionArreglo(numList, num, mitad, end);
            
            // se invoca a ambas para ser ejecutadas
            invokeAll(cpu1, cpu2);
        }
    }
    // Metodo que se usa en el segunda tarea
    private void multiplicarElementos() {
        for (int i = begin; i < end; i++) {
            numList[i] *= num;
        }
    }
}
