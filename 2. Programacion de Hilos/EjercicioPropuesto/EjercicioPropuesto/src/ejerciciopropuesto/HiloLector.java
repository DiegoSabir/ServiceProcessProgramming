package ejerciciopropuesto;

import java.io.*;
import java.util.LinkedList;

public class HiloLector extends Thread {

    //con hilos de esta clase leeremos los ficheros 
    //compartiremos una lista encadenada
    //cada hilo de esta clase HiloLector, recibe un  nombre de fichero a leer
    // y el recurso que comparten todos ellos, que es una lista encadenada de objetos de la clase
    //Persona, EN ELLA LOS ELEMENTOS SE INSERTAN ORDENADAMENTE
    String nfich;
    LinkedList<Persona> lista;
   
    // constructor de la clase, el hilo espera recibir, el fichero a procesar y la lista
    // todos los hilos van a recibir la misma lista 
    // no modificamos la prioridad, por tanto heredan la misma prioridad del hilo padre que los crea 
    public HiloLector(String nfich, LinkedList<Persona> lista) {
        this.nfich = nfich;
        this.lista = lista;
    }

    public String getNfich() {
        return nfich;
    }

    public void setNfich(String nfich) {
        this.nfich = nfich;
    }

    public LinkedList<Persona> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Persona> lista) {
        this.lista = lista;
    }

    // tarea que realiza cada hilo de esta clase 
    @Override
    public void run() {
        File f = new File(nfich);
        try {
            BufferedReader read = new BufferedReader(new FileReader(f));
            String nom;
            // ciclo secuencial de lectura de las cadenas en el fichero
            nom = read.readLine(); //intento leer primera cadena del fichero
            while (nom != null) {
               System.out.println(getName()+" "+nom);
                    int longitud = nom.length();
                    String[] sep = nom.split(" ");
                    StringBuffer ini = new StringBuffer();
                    for (String s : sep) {
                        ini.append(s.charAt(0));
                    }
                    String iniciales = ini.toString();
                    //nuevo objeto en memoria con información obtenida por el hilo
                    Persona p = new Persona(nom, longitud, iniciales);
                    // AQUÍ A CONTINUACIÓN CADA HILO INTENTA ADQUIRIR EL MONITOR DEL OBJETO lista
                    // POR LO TANTO,SINO NO LO OBTIENE EL HILO SE  BLOQUEA--->PASA A ESTADO ESPERANDO POR EL MONITOR DEL OBJETO
                    // SI LO OBTIENE EL HILO EJECUTA EL MÉTODO Insercion.insercionOrd 
                    // CUANDO EL ÚNICO HILO QUE ESTÁ EJECUTANDO LA SECCIÓN CRÍTICA (TIENE EL MONITOR DEL OBJETO) TERMINE
                    // DESPERTARÁ A TODOS LOS HILOS ESPERANDO
                    
                    Insercion.insercionOrd(lista, p);
                    
                
                nom = read.readLine(); //sigo leyendo en el fichero que estoy procesando,.. siguiente cadena
            } 
            // fin ciclo de lectura, he llegado secuencialmente al final del fichero, no hay más cadenas enel fichero
            read.close();
        } catch (IOException e) {

        }
    }  // fin tarea del objeto hilo de esta clase
}
