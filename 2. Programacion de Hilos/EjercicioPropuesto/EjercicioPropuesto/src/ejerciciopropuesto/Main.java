   package ejerciciopropuesto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        // PRIMERO VERIFICAR LA EXISTENCIA DE ARGUMENTOS , AL MENOS UNO
        if (args.length < 1) {
            System.err.println("\nSe necesita al menos la especificación de un fichero a procesar ");
            System.exit(-1);
        }
        
        //el hilo primario crea la lista y pasará su referencia a cada hilo lector que arranque
        //lanza tantos hilos lectores como ficheros reciba en los argumentos
        HiloLector hl = null;
        LinkedList<Persona> l = new LinkedList<>();
        for (String s : args) {
            hl = new HiloLector(s, l);
            hl.start();
            
        }//fin para cada argumento recibido
        
        //el hilo primario implementa un join() referenciado a cada hilo
        // de modo que,se detiene esperando a que todos sus hilos hijo referenciados finalicen. 
        
        for (String s : args) {
            try {
                  hl.join();
                } catch (InterruptedException e) {
                 }
        }
        
        // el hilo primario reanuda su ejecución, recorre lista y la vuelca en disco
        File f = new File("salida.txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(f));
            for (Persona p : l) {
                System.out.println(p);
                ps.println(p);
            }
            ps.close();
        } catch (FileNotFoundException e) {

        }
    }
}
