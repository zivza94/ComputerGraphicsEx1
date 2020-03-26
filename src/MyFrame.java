import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame {
    Frame myFrame;
    public MyFrame(String frameName) {
        this.myFrame = new Frame(frameName);
    }
    public void createWindow() throws Exception {
        Canvas myCanvas = new MyCanvas(800, 800);
        myFrame.add(myCanvas);
        myFrame.setSize(840, 840);

        WindowAdapter Wa =new WindowAdapter(){
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        };
        myFrame.addWindowListener(Wa);
        //myFrame.pack();
        myFrame.setVisible(true);
        myFrame.repaint();

    }
}
