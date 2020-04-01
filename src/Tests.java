import LinearMath.Matrix;
import LinearMath.Transformation2D;
import LinearMath.Vector;

public class Tests {
    public static void main(String[] args) throws Exception{
        double[] vec = {0,3,1};
        double[] vecX = {3,3,0};
        Vector v = new Vector(vec,3);
        Vector x = new Vector(vecX,3);
        double angle1 = v.GetAngle();
        double angle2 = v.GetAngle(x);
        System.out.println("1 vec :" + angle1 + "   2 vec :" +angle2);


    }
    public static double[][] UpdateVertex(double[][] vertex, Matrix VM){
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
    public static void TestVertexTransform() throws Exception{
        double[][] ver = {{2,4,2,4},{4,4,2,2}};
        Transformation2D tr = new Transformation2D();
        Matrix translate = tr.translate(4,2);

        ver = UpdateVertex(ver,translate);
        for (double[] val :ver) {
            for (double num:val) {
                System.out.print(num +" ");
            }
            System.out.println();
        }
    }
    public static void TestVecIncAndDec(){
        double[] vec = {5,4};
        Vector v1 = new Vector(vec,2);
        Vector v2 = v1.AddDimension();
        v2.Print();
        v2 = v2.DecreaseDimension();
        v2.Print();

    }
}
