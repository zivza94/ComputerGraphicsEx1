import LinearMath.Vector;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Clipping {
    private List<Edge> boundaryEdgesList;
    private List<Vector> vertexList;

    public Clipping(List<Edge> boundaryEdgesList, List<Vector> vertexList) {
        this.boundaryEdgesList = boundaryEdgesList;
        this.vertexList = vertexList;
    }

    public void setVertexList(List<Vector> vertexList) {
        this.vertexList = vertexList;
    }

    public Vector findTheNormalVector(Vector vec) {

        double[] normal = new double[2];
        normal[0] = - vec.getVec()[1];
        normal[1] = vec.getVec()[0];
        Vector normalVec = new Vector(normal, 2);
        return normalVec;
    }

    public List<Vector> clip(List<Vector> line) {
        int edgesNum = boundaryEdgesList.size();
        int i;
        List<Vector> newLine = new ArrayList<>();
        List<Vector>  normals = new ArrayList<>();
        List<Vector> P0_PEi = new ArrayList<>();
        //Calculating the normals, the values of P0 - PEi for all edges
        for(i = 0; i < edgesNum; i++) {
            double[] edge = new double[2];
            double[] P0_PEiArray = new double[edgesNum];
            Vector ver0 = vertexList.get(boundaryEdgesList.get(i).getpointIndex0());
            Vector ver1 = vertexList.get(boundaryEdgesList.get(i).getpointIndex1());
            normals.add(i, findTheNormalVector(ver1.minus(ver0)));
            P0_PEiArray[0] = vertexList.get(i).getVec()[0] - line.get(0).getVec()[0];
            P0_PEiArray[1] = vertexList.get(i).getVec()[1] - line.get(0).getVec()[1];
            P0_PEi.add(i, new Vector(P0_PEiArray, 2));
        }
        // Calculating P1 - P0
        Vector delta = line.get(1).minus(line.get(0));
        double[] numerator = new double[edgesNum];
        double[] denominator = new double[edgesNum];
        for(i = 0; i < edgesNum; i++) {
            numerator[i] = normals.get(i).Multiply(P0_PEi.get(i));
            denominator[i] = normals.get(i).Multiply(delta);
        }
        // The enter point and leaving point
        double p;
        List<Double> pE = new ArrayList<>();
        List<Double> pL = new ArrayList<>();
        for(i = 0; i < edgesNum; i++) {
            p = numerator[i] / denominator[i];
            if (denominator[i] > 0) {
                pE.add(p);
            } else {
                pL.add(p);
            }
        }
        pE.add(0.0);
        pL.add(1.0);
        double minPL = findMin(pL);
        double maxPE = findMax(pE);
        //Completely outside
        if (maxPE > minPL) {
            double[] newPt0 = {-1,-1};
            double[] newPt1 = {-1,-1};
            newLine.add(new Vector(newPt0,2));
            newLine.add(new Vector(newPt1,2));
            return newLine;
        }
        double[] newPt0 = new double[2];
        double[] newPt1 = new double[2];
        newPt0[0] = line.get(0).getVec()[0] + delta.getVec()[0] * maxPE;
        newPt0[1] = line.get(0).getVec()[1] + delta.getVec()[1] * maxPE;
        newPt1[0] = line.get(0).getVec()[0] + delta.getVec()[0] * minPL;
        newPt1[1] = line.get(0).getVec()[1] + delta.getVec()[1] * minPL;
        newLine.add(new Vector(newPt0,2));
        newLine.add(new Vector(newPt1,2));
        System.out.println("p0x: " + newPt0[0] + " p0y: " + newPt0[1]
        + " p1x:" + newPt1[0] + " p1y:" + newPt1[0]);
        return newLine;

    }

    public double findMax(List<Double> list) {
        double max = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            if (list.get(i) > max)
                max = list.get(i);
        }
        return max;
    }

    public double findMin(List<Double> list) {
        double min = list.get(0);
        for(int i = 1; i < list.size(); i++) {
            if (list.get(i) < min)
                min = list.get(i);
        }
        return min;
    }
                                                   
}
