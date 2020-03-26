import LinearMath.Matrix;
import LinearMath.Transformation2D;
import LinearMath.Vector;
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
    Matrix VM;
    Matrix AT;
    Matrix CT;
    public MyCanvas(int width, int height) throws Exception{
        this.scene = new Scene();
        this.view = new View();
        this.transformation = new Transformation2D();
        this.viewHeight = height;
        this.viewWidth = width;
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        cllipingFlag = false;
    }

    public void paint(Graphics g) {
        setSize(this.viewWidth, this.viewHeight);
        this.worldView();
        this.draw(this.scene.getEdgesList(), this.UpdateVertex(this.scene.getVertexList(), this.VM));


    }

    public void worldView() {
        Matrix t1 = transformation.translate(-this.view.getOrigin().getVec()[0], -this.view.getOrigin().getVec()[1]);
        Matrix rotate = transformation.rotate(-this.view.getDirection());
        Matrix scale = transformation.scale(this.viewWidth/view.getSize()[0], -this.viewHeight/view.getSize()[1]);
        Matrix t2 = transformation.translate((double) this.viewWidth / 2 + 20,
                (double) this.viewHeight / 2 + 20);
        this.VM = t2.Multiply(scale).Multiply(rotate).Multiply(t1);

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

    private List<Vector> UpdateVertex(List<Vector> vertex, Matrix VM){
        List<Vector> updatedVertex = new ArrayList<>();
        int i;
        int vertexNum = vertex.size();
        for (i = 0; i < vertexNum; i++) {
            updatedVertex.add(VM.Multiply(vertex.get(i)));
        }
        return updatedVertex;
    }

    public String transformationType(int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();
        Boolean yPosition = (y <= height /3 || y >= (2 * height) / 3);
        if (x < width /3) {
            if (yPosition) {
                return "Rotate";
            } else {
                return "Scale";
            }

        } else if (x > (2 * width) / 3) {
            if (yPosition) {
                return "Rotate";
            } else {
                return "Scale";
            }
        } else {
            if (yPosition) {
                return "Scale";
            } else {
                return "Translate";
            }

        }

    }
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        String transformationType = this.transformationType(e.getX(),e.getY());
        if (transformationType.equals("Rotate")) {


        } else if (transformationType.equals(("Scale"))) {

        } else {

        }


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