import noskov.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Finder {
    private Finder() {}

    public static double calculateMistake(RealMatrix actual, RealMatrix expected) {
        RealMatrix mistake = expected.subtract(actual);
        return MatrixUtils.calculateNormal(mistake);
    }

    public static double calculateRelativeMistake(RealMatrix actual, RealMatrix expected) {
        double actualNormal = MatrixUtils.calculateNormal(expected);
        double mistake = calculateMistake(actual, expected);

        return mistake/ actualNormal;
    }

    public static double calculateDiscrepancy(RealMatrix a, RealMatrix actual, RealMatrix f) {
        RealMatrix discrepancy = a.multiply(actual).subtract(f);
        return MatrixUtils.calculateNormal(discrepancy);
    }

    public static double calculateRelativeDiscrepancy(RealMatrix a, RealMatrix actual, RealMatrix f) {
        double discrepancy = calculateDiscrepancy(a, actual, f);
        double fNormal = MatrixUtils.calculateNormal(f);
        return discrepancy/fNormal;
    }
    public static double calculateConditionNumber(RealMatrix straight, RealMatrix reverse) {
        double straightNormal = MatrixUtils.calculateNormal(straight);
        double reverseNormal = MatrixUtils.calculateNormal(reverse);

        return straightNormal * reverseNormal;
    }
}
