import noskov.Gen;
import noskov.RotationMethod;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void findReverse100x100() throws Exception {
        int n = 100;
        double alpha = 1.e+9;
        double beta  = 1.;

        double[][] a = new double[n][];
        for (int i = 0; i < n; i++)	a[i] = new double[n];

        double[][] expected = new double[n][];
        for (int i = 0; i < n; i++)	expected[i] = new double[n];

        Gen g = new Gen();

     //   g.mygen( a, expected, n, alpha, beta, 1, 2, 1, 1 );
        g.mygen ( a, expected, n, alpha, beta, 0, 0, 2, 1 );//жорданова клетка


        //   g.print_matr(a,n);
      //  g.print_matr(expected,n);

        test(a, expected);
    }


    @Test
    public void findReverse3x3() throws Exception {
        int n = 3;
        double alpha = 1.e-3;
        double beta  = 1.;

        double[][] a = new double[n][];
        for (int i = 0; i < n; i++)	a[i] = new double[n];

        double[][] expected = new double[n][];
        for (int i = 0; i < n; i++)	expected[i] = new double[n];

        Gen g = new Gen();

        g.mygen ( a, expected, n, alpha, beta, 1, 2, 1, 1 );

        g.print_matr(a,n);
        g.print_matr(expected,n);

        test(a, expected);
    }

    @Test
    public void findReverseJordan3x3() throws Exception {
        int n = 3;
        double alpha = 1.e-3;
        double beta  = 1.;

        double[][] a = new double[n][];
        for (int i = 0; i < n; i++)	a[i] = new double[n];

        double[][] expected = new double[n][];
        for (int i = 0; i < n; i++)	expected[i] = new double[n];

        Gen g = new Gen();

        g.mygen ( a, expected, n, alpha, beta, 0, 0, 2, 1 );//жорданова клетка

        g.print_matr(a,n);
        g.print_matr(expected,n);

        test(a, expected);
    }

    private void test(double[][] aArray, double[][] expectedArray) throws Exception {
        RealMatrix a = MatrixUtils.createRealMatrix(aArray);
        double[][] copyMatrix = aArray.clone();
        RotationMethod.upperTriangularMatrix(copyMatrix);
        RotationMethod.inversionUpperTriangularMatrix(copyMatrix);
        RotationMethod.backwash(copyMatrix);
        RealMatrix actual = MatrixUtils.createRealMatrix(aArray);
        RealMatrix expected = MatrixUtils.createRealMatrix(expectedArray);
        test(a, actual, expected);
    }

    private void testActualAndExpected(RealMatrix actual, RealMatrix expected) {
        int squareLength = actual.getRowDimension();

        for(int i = 0; i < squareLength; i++) {
            Assert.assertArrayEquals(expected.getRow(i),actual.getRow(i),0.001);
        }
    }

    private void test(RealMatrix a, RealMatrix actual, RealMatrix expected) throws Exception {

        double aNormal = noskov.MatrixUtils.calculateNormal(a);
        System.out.println("a matrix normal = " + aNormal);
        double reverseNormal = noskov.MatrixUtils.calculateNormal(actual);
        System.out.println("reverse matrix noraml = " + reverseNormal);
        double conditionNumber = Finder.calculateConditionNumber(a,expected);
        System.out.println("condition number = " + conditionNumber);

        double mistake = Finder.calculateMistake(actual, expected);
        System.out.println("mistake = " + mistake);

        double relativeMistake = Finder.calculateRelativeMistake(actual, expected);
        System.out.println("relative mistake = " + relativeMistake);

        int squareLength = a.getRowDimension();
        RealMatrix unitMatrix = noskov.MatrixUtils.getSquareUnitMatrix(squareLength);

        double discr = Finder.calculateDiscrepancy(a, actual,unitMatrix);
        System.out.println("discrepancy = " + discr);

        double relativeDiscr = Finder.calculateRelativeDiscrepancy(
                                                                    a, actual, unitMatrix);
        System.out.println("relative discrepancy = " + relativeDiscr);


       // System.out.println(discr/relativeMistake);
    }

}
