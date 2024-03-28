
import java.util.concurrent.ForkJoinPool;  // Importar framewor ForkJoinPool

import classes.TransformacionArreglo;  // Importar la clase TrasformacionArreglo


// Clase computo
// Ejecuta la funcion "main" que se encargad de ejecutar todo el codigo
public class computo{

    static final int tamaño = (10000000);  // Se define un tamaño maximo para la lista
    public static void main(String[] args) {

        int[] numList = cargarArreglo(); // Genera valores para la lista usando el metodo de cargar arreglo
        int cant = 100;    //  Se establece un limite de elementos a mostrar de la lista

        System.out.println("Primeros " + cant + " elementos del arreglo");
        imprimirArreglo(numList, cant);

        TransformacionArreglo tareaPrincipal = new TransformacionArreglo(numList, 10, 0, tamaño);    // Se instancia la clase "transforacionArreglo" y se crea un nuevo objeto

        ForkJoinPool pool = new ForkJoinPool();  // Se instancia ForkJoinPool para definir una ejecucion de computo paralelo

        pool.invoke(tareaPrincipal);  // Invoca la tarea para ejecutarla por hilos (threads)

        pool.close(); // Se cierra el pool

        System.out.println();
        
        System.out.println("Contenido del arreglo después de la transformación:");
        imprimirArreglo(numList, cant); 
    }



    // Método que carga al arreglo con valores
    private static int[] cargarArreglo() {
        int[] numList = new int[tamaño]; // tamaño del arreglo definido por la variable "tamaño"
        int contador = 1;
        
        for (int i = 0; i < tamaño; i++) {
            numList[i] = contador++; // Asigna números al arreglo
        }
        return numList;
    }
    // Imprime solamente la cantidad de elementos del arreglo indicada con "cant"
    private static void imprimirArreglo(int[] numList, int cant) {  
        for (int i = 0; i < cant; i++) {
            System.out.println(numList[i] + " ");
        }
    }


}