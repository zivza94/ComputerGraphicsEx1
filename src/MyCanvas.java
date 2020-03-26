import LinearMath.Matrix;
import LinearMath.Vector;

import java.awt.*;
import java.awt.event.*;

class MyCanvas extends Canvas implements MouseListener,  MouseMotionListener, KeyListener {
    private Boolean cllipingFlag;
    private static final long serialVersionUID = 1L;
    Point pStart, pEnd;
    boolean bFlag = false;
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

    private double[][] UpdateVertex(double[][] vertex, Matrix VM){
        int dim = vertex.length;
        int size = vertex[0].length;
        double[][] retval = new double[dim][size];
        for(int i=0;i<size;i++){
            double[] point = new double[dim];
            for(int j=0; j<dim; j++){
                point[j] = vertex[j][i];
            }
            Vector pointVec = new Vector(point,dim);
            pointVec =pointVec.AddDimension();
            pointVec = VM.Multiply(pointVec);
            pointVec.DecreaseDimension();
            point = pointVec.getVec();
            for(int j=0; j<dim; j++){
                retval[j][i] = point[j];
            }
        }
        return retval;
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