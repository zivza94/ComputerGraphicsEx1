package LinearMath;

public class Vector {
    double[] vec;
    int size;
    public Vector(double[]vec,int size){
        this.vec = vec;
        this.size = size;
    }
    public Vector Add(Vector v){
        Vector retval;
        double[] vecArr = new double[size];
        for(int i=0; i<size; i++){
            vecArr[i] = this.vec[i] + v.vec[i];
        }
        retval = new Vector(vecArr,size);
        return retval;
    }
    public double Multiply(Vector v){
        double retval = 0;
        for(int i=0; i<size;i++){
            retval += this.vec[i] * v.vec[i];
        }
        return retval;
    }
    public double GetLength(){
        double length = 0;
        length = Math.sqrt(this.Multiply(this));
        return length;
    }
    public double GetAngle(Vector v){
        double CrossPro = this.Multiply(v);
        double cosAngle = CrossPro/(this.GetLength()*v.GetLength());
        return Math.toDegrees(Math.acos(cosAngle));
    }

    public void Print(){
        for(int i=0; i<size;i++){
            System.out.print(this.vec[i] +", ");
        }
        System.out.println();
    }
}
