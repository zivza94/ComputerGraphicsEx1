import LinearMath.Matrix;
import LinearMath.Vector;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

class MyCanvas extends Canvas implements MouseListener,  MouseMotionListener, KeyListener {
    private Boolean cllipingFlag;
    private static final long serialVersionUID = 1L;
    Point pStart, pEnd;
    boolean bFlag = false;
    Matrix VM;
    Matrix AT;
    Matrix CT;
    public MyCanvas() {
        setSize(600, 500);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        cllipingFlag = false;
    }

    public void paint(Graphics g) {
        g.drawRect(120, 70, 300, 300 );

    }

    public void draw(Graphics g, List<Edge> edges, List<Edge> edgesList) {


    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub


    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

        this.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

        this.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private List<Vector> UpdateVertex(List<Vector> vertex, Matrix VM){
        List<Vector> updatedVertex = new ArrayList<>();
        int i;
        int vertexNum = vertex.size();
        for (i = 0; i < vertexNum; i++) {
            updatedVertex.add(VM.Multiply(vertex.get(i)));
        }
        return updatedVertex;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
            cllipingFlag = !cllipingFlag;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}