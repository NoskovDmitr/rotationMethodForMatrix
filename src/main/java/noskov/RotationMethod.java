package noskov;

public class RotationMethod {

    public static void upperTriangularMatrix(double[][] matrix) {
        int n = matrix.length;
        double cos;
        double sin;
        double mup, mdown;
        for(int k=0; k<n; k++) {         //бежим по столбцам матрицы
            for (int l=k+1; l<n; l++) {         //бежим по строкам этого столбца

                double denominator = Math.sqrt(matrix[k][k]*matrix[k][k] + matrix[l][k]*matrix[l][k]);

                if (denominator == 0.) {
                    throw new NullPointerException("Denominator = 0");
                }

                cos = matrix[k][k]/denominator;
                sin = -matrix[l][k]/denominator;

                for (int j=k; j<n; j++) {        //бежим по столбцам в фиксированноых строках
                    mup = matrix[k][j];
                    mdown = matrix[l][j];
                    matrix[k][j] = mup*cos - mdown*sin; //действуем на k-ю строку
                    matrix[l][j] = mup*sin + mdown*cos; //действуем на l-ю строку
                }

                if (sin > 0.) {
                    matrix[l][k] = cos + 1.;
                } else {
                    matrix[l][k] = cos - 1.;
                }


            }
        }
        if (matrix[n-1][n-1] == 0.) {
            throw new NullPointerException("Matrix[n][n] = 0");
        }
    }

    public static void backwash(double[][] matrix) {
        int n = matrix.length;
        double[] v = new double[n];
        for (int k = n - 2; k >= 0; k--) {         //бежим по столбцам матрицы справо налево
            for (int l = k+1; l < n; l++) {         //забираем вектор чисел ниже диагонали
                v[l] = matrix[l][k];
                matrix[l][k] = 0.;
            }

            for (int u = n-1; u>k; u--) {
                double cos;
                double sin;
                if (v[u] > 0.) { //cos in (0,2]
                    cos = v[u] - 1.;
                    sin = Math.sqrt(1.-cos*cos);
                } else {
                        cos = v[u] + 1.;
                        sin = -Math.sqrt(1.-cos*cos);
                }
                double ml, mr;
                for (int l=0; l<n; l++) { //бежим по строкам выбранных столбцов

//                    System.out.println();
//                    for(int i=0; i<matrix.length; i++) {
//                        for(int j=0; j<matrix.length; j++) {
//                            System.out.print(matrix[i][j]+"   ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println();
//                    System.out.println("k "+k);
//                    System.out.println("u "+u);
//                    System.out.println("l "+l);
//                    System.out.println("sin "+sin);
//                    System.out.println("cos "+cos);
//                    System.out.println();


                    ml = matrix[l][k];
                    mr = matrix[l][u];
                    matrix[l][k] = ml*cos + mr*sin; //действуем на k-й стоблец
                    matrix[l][u] = -ml*sin + mr*cos;
                }

            }
        }
    }

    public static void inversionUpperTriangularMatrix(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i<n; i++) { //строка
            matrix[i][i] = 1./matrix[i][i];
            for (int j = i+1; j<n; j++) { //столбец
                double sum = 0.;
                for (int k = i; k<j; k++) {
                    sum += matrix[i][k] * matrix[k][j];
                }
                matrix[i][j] = -sum/matrix[j][j];
            }
        }
    }

    public static void normalizeMatrixColumns(double[][] matrix, double[] k) {
        int n = matrix.length;
        for (int j = 0; j<n; j++) {
            for (int i = 0; i<n; i++) {
                matrix[i][j] = matrix[i][j]/k[j];
            }
        }
    }
}
