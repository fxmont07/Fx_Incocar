package thread;

import userInterface.WindowMain;

import javax.swing.*;
import java.awt.*;

public class ThreadMove extends Thread {
    private WindowMain windowMain;

    // Constructors
    public ThreadMove(WindowMain windowMain) {
        this.windowMain = windowMain;
    }

    @Override
    public void run() {
        int i = 1;
        while(true) {
            try {
                Thread.sleep(1300);
                if(i == 0) windowMain.getContainer().remove(windowMain.getWelcomeMessages()[windowMain.getWelcomeMessages().length -1]);
                else windowMain.getContainer().remove(windowMain.getWelcomeMessages()[i-1]);
                windowMain.getWelcomeMessages()[i].setHorizontalAlignment(SwingConstants.CENTER);
                windowMain.getContainer().add(windowMain.getWelcomeMessages()[i], BorderLayout.NORTH);
                windowMain.getContainer().repaint();
                windowMain.getContainer().revalidate();

                if (i == windowMain.getWelcomeMessages().length -1) i = 0;
                else i++;

            } catch (InterruptedException exception) {
                JOptionPane.showMessageDialog(null, "Erreur animation", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
