import Jama.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Sa {

    private static final double[][] matrixA = {
            {0, -0.052, -0.13, -0.07},
            {-0.127, 0, -0.1, -0.115},
            {-0.099, -0.117, 0, -0.143},
            {-0.114, -0.053, -0.059, 0}
    };
/*    private static final double[][] matrixA = {
        {0, -0.0552, -0.1605, -0.0067},
        {-0.2682, 0, -0.2764, -0.0783},
        {-0.0803, -0.0468, 0, -0.1144},
        {-0.0162, -0.1045, -0.0385, 0}
    };
    private static final double[] matrixB = {
            0.1401, 0.0999, 0.0692, 0.0527
    };*/

    private static final double[] matrixB = {
            0.106, 0.006, 0.071, 0.261
    };

    public static void main(String[] args) {
        Matrix a = new Matrix(matrixA);
        Matrix b = new Matrix(matrixB, 1);

        ArrayList<Matrix> matrices = new ArrayList<>();
        matrices.add(b);

        for (int i = 0; i < 4; i++) {
            Matrix temp = matrices.get(i).times(a);
            temp = temp.plus(b);
            matrices.add(matrixRoundUp(temp));
        }

        matrices.stream().forEachOrdered(matrix -> {
            System.out.println(Arrays.deepToString(matrix.getArray()));
        });
    }

    public static Matrix matrixRoundUp(Matrix m) {
        double[] tempFinishedArr;

        tempFinishedArr = Arrays.stream(m.getArray()[0])
                .map(i -> (double) Math.round(i * 1000.0) / 1000.0)
                .toArray();

        return new Matrix(tempFinishedArr, 1);
    }
}
