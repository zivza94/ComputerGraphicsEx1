import LinearMath.Matrix;
import LinearMath.Vector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class MyCanvas extends Canvas implements MouseListener,  MouseMotionListener {
    private static final long serialVersionUID = 1L;
    Point pStart, pEnd;
    boolean bFlag = false;
    public MyCanvas() {
        setSize(600, 500);
        addMouseListener(this);
        addMouseMotionListener(this);
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

}