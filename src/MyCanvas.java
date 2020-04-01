import LinearMath.Matrix;
import LinearMath.Transformation2D;
import LinearMath.Vector;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

class MyCanvas extends Canvas implements MouseListener,  MouseMotionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private int viewHeight;
    private int viewWidth;
    private Boolean cllipingFlag;
    private Scene scene;
    private View view;
    private Transformation2D transformation;
    private String transType;
    private Vector pressedPoint;
    private Matrix VM;
    private Matrix AT;
    private Matrix CT;
    private Matrix TT;
    private boolean firstPaint;
    public MyCanvas(int width, int height) throws Exception{
        this.scene = new Scene();
        this.view = new View();
        this.transformation = new Transformation2D();
        this.viewHeight = height;
        this.viewWidth = width;
        this.AT = new Matrix(3);
        AT.toIdentityMatrix();
        this.CT = new Matrix(3);
        CT.toIdentityMatrix();
        this.TT = new Matrix(3);
        firstPaint = true;
        TT.toIdentityMatrix();
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        cllipingFlag = false;

    }

    public void paint(Graphics g) {
        setSize(this.viewWidth, this.viewHeight);
        worldView();
        if(firstPaint) {
            this.draw(this.scene.getEdgesList(), this.UpdateVertex(this.scene.getVertexList(), this.VM));
            firstPaint = false;
        } else {
            this.draw(this.scene.getEdgesList(), this.UpdateVertex(this.scene.getVertexList(), this.TT));
        }

    }

    public void worldView() {
        Matrix t1 = transformation.translate(-this.view.getOrigin().getVec()[0], -this.view.getOrigin().getVec()[1]);
        Matrix rotate = transformation.rotate(-this.view.getDirection());
        Matrix scale = transformation.scale(this.viewWidth/view.getSize()[0], -this.viewHeight/view.getSize()[1]);
        Matrix t2 = transformation.translate((double) this.viewWidth / 2 + 20,
                (double) this.viewHeight / 2 + 20);
        this.VM = t2.Multiply(scale).Multiply(rotate).Multiply(t1);
        this.TT = CT.Multiply(AT.Multiply(VM));
    }

    public void draw(List<Edge> edges, List<Vector> vertex) {
        int edgesNum = edges.size();
        int i;
        for (i = 0; i < edgesNum; i++) {
            Vector ver0 = vertex.get(edges.get(i).getpointIndex0());
            Vector ver1 = vertex.get(edges.get(i).getpointIndex1());
            if (cllipingFlag) {
                //clliping
            }
            getGraphics().drawLine((int) ver0.getVec()[0], (int) ver0.getVec()[1],
                    (int) ver1.getVec()[0], (int) ver1.getVec()[1]);
        }

    }

    private List<Vector> UpdateVertex(List<Vector> vertex, Matrix TT){
        List<Vector> updatedVertex = new ArrayList<>();
        int i;
        int vertexNum = vertex.size();
        for (i = 0; i < vertexNum; i++) {
            updatedVertex.add(TT.Multiply(vertex.get(i)));
        }
        return updatedVertex;
    }

    public void transformationType(int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();
        Boolean yPosition = (y <= height /3 || y >= (2 * height) / 3);
        if (x < width /3) {
            if (yPosition) {
                this.transType = "Rotate";
            } else {
                this.transType = "Scale";
            }

        } else if (x > (2 * width) / 3) {
            if (yPosition) {
                this.transType = "Rotate";
            } else {
                this.transType = "Scale";
            }
        } else {
            if (yPosition) {
                this.transType = "Scale";
            } else {
                this.transType = "Translate";
            }

        }

    }

    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        this.transformationType(e.getX(),e.getY());
        this.pressedPoint = new Vector(new double[]{e.getX(),e.getY(), 1}, 3);
    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        AT = CT.Multiply(AT);
        CT.toIdentityMatrix();
        this.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        if (transType.equals("Translate")) {
            CT = transformation.translate(e.getX() - pressedPoint.getVec()[0],
                    e.getY() - pressedPoint.getVec()[1]);


        } else {
            double originX = view.getOrigin().getVec()[0];
            double originY = view.getOrigin().getVec()[1];
            double arr[] = {viewWidth/2,viewHeight/2,1};
            Vector center = new Vector(arr, 3);
            Matrix transCenter = transformation.translate(viewWidth/2, viewHeight/2);
            Matrix transBack = transformation.translate(-viewWidth/2, -viewHeight/2);
            Vector destination = new Vector(new double[]{e.getX(),e.getY(), 1}, 3);

            if (transType.equals(("Scale"))) {

                double SF = destination.minus(center).GetLength() /
                        pressedPoint.minus(center).GetLength();
                Matrix scale = transformation.scale(SF, SF);
                CT = transCenter.Multiply(scale).Multiply(transBack);
            } else {
                double angle1 = destination.minus(center).GetAngle();
                double angle2 = pressedPoint.minus(center).GetAngle();
                Matrix rotate = transformation.rotate(-(angle1-angle2));
                CT = transCenter.Multiply(rotate).Multiply(transBack);
            }
        }
        TT = CT.Multiply(AT).Multiply(VM);

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