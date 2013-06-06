package org.fferegrino.server.bluepointer;

/**
 *
 * @author Antonio
 */
public class BluePoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola, soy el servidor de Bluepointer");
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
    }
}
