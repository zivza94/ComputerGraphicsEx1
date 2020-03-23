import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame {
    Frame myFrame;
    public MyFrame(String frameName) {
        this.myFrame = new Frame(frameName);
    }
    public void createWindow() {
        Canvas myCanvas = new MyCanvas();
        myFrame.add(myCanvas);
        myFrame.setSize(600, 500);
        myFrame.setVisible(true);
        WindowAdapter Wa =new WindowAdapter(){
            public void windowClosing (WindowEvent e){
                myFrame.dispose();
                System.exit(0);
            }
        };
        myFrame.addWindowListener(Wa);
    }
}
