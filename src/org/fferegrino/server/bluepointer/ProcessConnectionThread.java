package org.fferegrino.server.bluepointer;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable {

    private StreamConnection mConnection;
    // Constant that indicate command from devices
    private static final int EXIT_CMD = -1;
    // Constants that indicate command from device
    private static final int NEXT_SLIDE = 1;
    private static final int PREVIOUS_SLIDE = 2;
    private static final int STRING = 5;

    public ProcessConnectionThread(StreamConnection connection) {
        mConnection = connection;
    }

    @Override
    public void run() {
        try {
            
            InputStream inputStream = mConnection.openInputStream();

            System.out.println("Conexión establecida");

            while (true) {
                int command = inputStream.read();

                if (command == EXIT_CMD) {
                    System.out.println("Se terminó la conexión");
                    break;
                }
                if(command==STRING){
                    int lenght = inputStream.read();
                    byte[]message = new byte[lenght];
                    inputStream.read(message);
                    String msg = new String(message);
                    System.out.println("BluePointer para Android dice: " + msg);
                }
                processCommand(command);
            }
        } catch (Exception e) {
            
        }
    }

    /**
     * Process the command from client
     *
     * @param command the command code
     */
    private void processCommand(int command) {
        try {
            Robot robot = new Robot();
            switch (command) {
                case NEXT_SLIDE:
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    //System.out.println("Next slide");
                    break;
                case PREVIOUS_SLIDE:
                    robot.keyPress(KeyEvent.VK_LEFT);
                    //System.out.println("Previous slide");
                    break;
            }
        } catch (Exception e) {
        }
    }
}