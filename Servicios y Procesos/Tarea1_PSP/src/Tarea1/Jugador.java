package Tarea1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Jugador extends Thread {

    private static boolean terminado = false;
    private static FileOutputStream fosSalida;

    private final String tipo;
    private final Random random = new Random();

    private static final char[] arrayLetras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public Jugador(String tipo) {
        this.tipo = tipo;
    }

    public static void setFileOutputStream(FileOutputStream fos) {
        fosSalida = fos;
    }

    private char letraRandom() {
        return arrayLetras[random.nextInt(arrayLetras.length)];
    }

    private char cifraRandom() {
        return (char) (random.nextInt(10) + '0');
    }

    @Override
    public void run() {

        for (int i = 0; i < 20; i++) {

            synchronized (Jugador.class) {   // <-- monitor único

                if (terminado) return;

                try {
                    char c = (tipo.equals("cifras")) ? cifraRandom() : letraRandom();
                    fosSalida.write(c);

                    Thread.sleep(random.nextInt(1000));

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == 19 && !terminado) {
                    terminado = true;
                    System.out.println("GANADOR: " + tipo.toUpperCase());
                    Jugador.class.notifyAll();
                }
            }
        }
    }
}
