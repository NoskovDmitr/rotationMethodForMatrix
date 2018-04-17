import noskov.RotationMethod;
import org.junit.Test;

public class RotationMethodTest {

    @Test
    public void handMadeTest() {
        double[][] matrix = new double[3][3];

//        matrix[0][0] = 2;
//        matrix[0][1] = 5;
//        matrix[0][2] = 7;
//
//        matrix[1][0] = 6;
//        matrix[1][1] = 3;
//        matrix[1][2] = 4;
//
//        matrix[2][0] = 5;
//        matrix[2][1] = -2;
//        matrix[2][2] = -3   ;


        matrix[0][0] = 3.;
        matrix[0][1] = 2.;
        matrix[0][2] = 1.;

        matrix[1][0] = 2.;
        matrix[1][1] = 2.;
        matrix[1][2] = 1.;

        matrix[2][0] = 1.;
        matrix[2][1] = 1.;
        matrix[2][2] = 1.;


//        matrix[0][0] = 1.;
//        matrix[0][1] = 0.;
//        matrix[0][2] = -1.;
//
//        matrix[1][0] = 0.;
//        matrix[1][1] = 1.;
//        matrix[1][2] = 0.;
//
//        matrix[2][0] = 0.;
//        matrix[2][1] = 0.;
//        matrix[2][2] = 1.;


//        matrix[0][0] = 1.;
//        matrix[0][1] = 0.;
//        matrix[0][2] = 0.;
//
//        matrix[1][0] = 0.;
//        matrix[1][1] = 1.;
//        matrix[1][2] = 0.;
//
//        matrix[2][0] = -1.;
//        matrix[2][1] = 0.;
//        matrix[2][2] = 1.;

        double[][] copyMatrix = matrix.clone();

        RotationMethod.upperTriangularMatrix(copyMatrix);

        System.out.println("Upper");
        for(int i=0; i<copyMatrix.length; i++) {
            for(int j=0; j<copyMatrix.length; j++) {
                System.out.print(copyMatrix[i][j]+"   ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("inversionUpper");

        RotationMethod.inversionUpperTriangularMatrix(copyMatrix);

        for(int i=0; i<copyMatrix.length; i++) {
            for(int j=0; j<copyMatrix.length; j++) {
                System.out.print(copyMatrix[i][j]+"   ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("backwash");

        RotationMethod.backwash(copyMatrix);

        for(int i=0; i<copyMatrix.length; i++) {
            for(int j=0; j<copyMatrix.length; j++) {
                System.out.print(copyMatrix[i][j]+"   ");
            }
            System.out.println();
        }
    }

    @Test
    public void testingInversionUpperTriangularMatrix() {
        double[][] matrix = new double[3][3];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        matrix[1][0] = 0;
        matrix[1][1] = 2;
        matrix[1][2] = 2;

        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 1;

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j]+"   ");
            }
            System.out.println();
        }
        System.out.println();


        RotationMethod.inversionUpperTriangularMatrix(matrix);

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j]+"   ");
            }
            System.out.println();
        }

    }
}