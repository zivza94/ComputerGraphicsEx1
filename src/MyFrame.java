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

        WindowAdapter Wa =new WindowAdapter(){
            public void windowClosing (WindowEvent e){
                System.exit(0);
            }
        };
        myFrame.setBounds(20,20, 460, 460);
        myFrame.addWindowListener(Wa);
        myFrame.pack();
        myFrame.setVisible(true);

    }
}
