import Jama.Matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
            {0.416, 0, 0.326, 0},
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

    public static void main(String[] args) {
        BufferedReader bfReader =
                new BufferedReader(new InputStreamReader(System.in));
        int accuracy = 0;
        try {
            accuracy = bfReader
                    .readLine()
                    .length() - 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Matrix A = new Matrix(a);
        Matrix D = new Matrix(d);
        Matrix L = new Matrix(l);
        Matrix R = new Matrix(r);
        Matrix B = D.inverse().uminus().times(L.plus(R));
        Matrix c = D.inverse().times(new Matrix(b, 1));

    }
}
