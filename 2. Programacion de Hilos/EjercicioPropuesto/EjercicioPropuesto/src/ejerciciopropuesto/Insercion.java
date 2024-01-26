 package ejerciciopropuesto;

import java.util.Comparator;
import java.util.LinkedList;

public class Insercion {
//definimos aquí 
//un método sincronizado,es una sección crítica, de modo que, 
//    en cada instante sólo un hilo HiloLector podrá
// acceder a la lista para insertar un nuevo elemento.
//el código definido como sincronizado será un bloque atómico..
// se garantiza la exclusividad y unicidad, todo el bloque se ejecuta enteramente por el hilo    
    public synchronized static void insercionOrd(LinkedList<Persona> lista, Persona p) {

        
            lista.add(p);
            lista.sort(new Comparator<Persona>() {
                @Override
                public int compare(Persona o1, Persona o2) {
                    return o1.getNombre().compareToIgnoreCase(o2.getNombre());
                }
            });
        }


    }


