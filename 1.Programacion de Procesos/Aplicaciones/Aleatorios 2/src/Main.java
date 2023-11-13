import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        String entrada;

        try {
            // Arranca un proceso hijo aleatorioshijo.exe
            ProcessBuilder procesohijo = new ProcessBuilder("aleatorioshijo.exe").start();

            // Lee la entrada del usuario
            BufferedReader flujoentrada1 = new BufferedReader(new InputStreamReader(procesohijo.start().getInputStream()));
            PrintStream flujosalida1 = new PrintStream(procesohijo.getOutputStream());
            BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));

            do{
                System.out.print("Introduce una cadena ('fin' para terminar): ");
                entrada = lee.readLine();

                // Verifica si el usuario desea salir
                if (entrada.equals("fin")) {
                    flujosalida1.println("dame un numero");
                    flujosalida1.flush();
                    String salida = flujoentrada1.readLine();

                    int veces = Integer.parseInt(salida);

                    Process procesohijo2 = new ProcessBuilder("mayusculashijo.exe").start();
                    BufferedReader flujoentrada2 = new BufferedReader(new InputStreamReader(procesohijo2.));
                    PrintStream flujosalida2 = new PrintStream(procesohijo2.getOutputStream());
                    System.out.println("Numero de cadenas a a procesar:" + veces);

                    for (int i = 0; i < veces; i++){
                        String entrada2 = lee.readLine();
                        flujosalida2.println(entrada2);
                        flujosalida2.flush();
                        System.out.println(flujoentrada2.readLine());
                    }

                    procesohijo2.destroy();
                }
            }
            while (!entrada.equals("fin"));

            procesohijo.destroy();
        }
        catch (IOException e) {
            System.out.println("Error" + e);
        }
    }
}
