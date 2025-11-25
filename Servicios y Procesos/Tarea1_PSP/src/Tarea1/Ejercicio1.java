package Tarea1;

import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) {

        try {

            FileOutputStream fos = new FileOutputStream("salida.txt", true);
            Jugador.setFileOutputStream(fos);

            Jugador cifras = new Jugador("cifras");
            Jugador letras = new Jugador("letras");

            cifras.start();
            letras.start();

            synchronized (Jugador.class) {
                try {
                    Jugador.class.wait();  // wait al ganador
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            fos.close();
            System.out.println("Juego finalizado. Revisa salida.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
