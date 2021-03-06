package LinearMath;

public class Transformation2D {
    public Matrix translate(double deltaX, double deltaY) {
            double[][] array = {{1, 0 , deltaX} , {0, 1, deltaY} , {0, 0 ,1} };
        Matrix matrix = new Matrix(array, array.length);
        return matrix;
    }
    public Matrix scale(double a, double b) {
        double[][] array = {{a, 0 , 0} , {0, b, 0} , {0, 0 ,1} };
        Matrix matrix = new Matrix(array, array.length);
        return matrix;
    }
    public Matrix rotate(double angle) {
        //radians??
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double[][] array = {{cos, sin , 0} , {-sin, cos, 0} , {0, 0 ,1} };
        Matrix matrix = new Matrix(array, array.length);
        return matrix;
    }
}
