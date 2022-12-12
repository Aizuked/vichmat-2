import Jama.Matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static final double[][] a = {
            {4.003, 0.207, 0.519, 0.281},
            {0.416, 3.273, 0.326, 0.375},
            {0.297, 0.351, 2.997, 0.429},
            {0.412, 0.194, 0.215, 3.628}
    };

    static final double[][] d = {
            {4.003, 0, 0, 0},
            {0, 3.273, 0, 0},
            {0, 0, 2.997, 0},
            {0, 0, 0, 3.628}
    };
    static final double[][] l = {
            {0, 0, 0, 0},
            {0.416, 0, 0, 0},
            {0.297, 0.351, 0, 0},
            {0.412, 0.194, 0.215, 0}
    };
    static final double[][] r = {
            {0, 0.207, 0.519, 0.281},
            {0, 0, 0.326, 0.375},
            {0, 0, 0, 0.429},
            {0, 0, 0, 0}
    };
    static final double[] b = {0.425, 0.021, 0.213, 0.946};
    private static int rounder = 0;

    public static void main(String[] args) {
        BufferedReader bfReader =
                new BufferedReader(new InputStreamReader(System.in));
        double accuracy = 0;
        try {
            accuracy = Double.parseDouble(bfReader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        double acc = accuracy;
        while ((int) accuracy * 10 == 0) {
            accuracy *= 10;
            rounder++;
        }
        accuracy = acc;


        Matrix D = new Matrix(d);
        Matrix L = new Matrix(l);
        Matrix R = new Matrix(r);
        Matrix B = D.inverse().uminus().times(L.plus(R));
        Matrix c = new Matrix(b, 1).times(D.inverse());

        LinkedList<Matrix> cache = new LinkedList<>();
        LinkedList<Double> deltaX = new LinkedList<>();

        cache.addFirst(new Matrix(new double[]{0, 0, 0, 0}, 1));
        deltaX.addFirst(0d);
        cache.add((c));
        deltaX.add(cache.getLast().minus(cache.get(cache.indexOf(cache.getLast()) - 1)).norm1());

        while (cache.getLast().minus(cache.get(cache.indexOf(cache.getLast()) - 1)).norm1() >= (1 - B.norm1()) / B.norm1() * accuracy) {
            cache.addLast((cache.getLast().times(B).plus(c)));
            deltaX.add(cache.getLast().minus(cache.get(cache.indexOf(cache.getLast()) - 1)).norm1());
        }

        cache.stream().forEachOrdered(i -> {
            System.out.println(Arrays.deepToString(matrixToRoundUp(i).getArray()) + " " + roundUp(deltaX.get(cache.indexOf(i))));
        });

        int checkCtr = 0;
        for (int i = 0; i < 4; i++) {
            double diag = d[i][i];
            double temp = 0;
            for (int j = 0; j < 4; j++) {
                if (a[i][j] != diag) {
                    temp += a[i][j];
                }
            }
            if (diag > temp) checkCtr++;
            temp = 0;
        }

        System.out.println(checkCtr == 4 ? "Сходится" : "Не сходится");
    }


    public static Matrix matrixToRoundUp(Matrix matrixToRound) {
        double[][] tempMatrix = matrixToRound.getArray();

        for (int i = 0; i < tempMatrix.length; i++) {
            for (int j = 0; j < tempMatrix[i].length ; j++) {
                tempMatrix[i][j] = roundUp(tempMatrix[i][j]);
            }
        }

        return new Matrix(tempMatrix);
    }

    public static double roundUp(double toRound) {
        return Math.round(Math.pow(10, rounder) * toRound) / Math.pow(10, rounder);
    }
}
