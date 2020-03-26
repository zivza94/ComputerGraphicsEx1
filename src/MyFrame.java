import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame {
    Frame myFrame;
    public MyFrame(String frameName) {
        this.myFrame = new Frame(frameName);
    }
    public void createWindow() throws Exception {
        Canvas myCanvas = new MyCanvas(500, 500);
        myFrame.add(myCanvas);
        myFrame.setSize(540, 540);

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
