package noskov;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

public class MatrixUtils {
    private MatrixUtils() {}

    public static void print(RealMatrix matrix, int accuracy) {
        String sAccuracy = "%" + String.format("%df ", accuracy);
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (double v : matrix.getRow(i)) {
                System.out.printf(sAccuracy, v);
            }
            System.out.println();
        }
    }

    public static double calculateNormal(RealMatrix matrix) {
        return calculateNormal(matrix.getData());
    }

    public static double calculateNormal(double[][] matrix) {
        double max = sumVectorByModul(matrix[0]);

        for (int i = 1; i < matrix.length; i++) {
            final double sum = sumVectorByModul(matrix[i]);
            if(max < sum)
                max = sum;
        }
        return max;
    }

    public static double sumVectorByModul(double[] vector) {
        double sum = 0.;
        for (double v : vector) {
            sum += Math.abs(v);
        }
        return sum;
    }

    public static RealMatrix getSquareUnitMatrix(int count) {
        double[] diagonal = new double[count];
        Arrays.fill(diagonal, 1);
        return org.apache.commons.math3.linear.MatrixUtils.createRealDiagonalMatrix(diagonal);
    }
}
