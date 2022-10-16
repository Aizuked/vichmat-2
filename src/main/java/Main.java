import Jama.Matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static final double[][] mainMatrix = {
            {4.003, 0.207, 0.519, 0.281},
            {0.416, 3.273, 0.326, 0.375},
            {0.297, 0.351, 2.997, 0.429},
            {0.412, 0.194, 0.215, 3.628}};
    static final double[] results = {0.425, 0.021, 0.213, 0.946};

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

        Matrix A = new Matrix(mainMatrix);
        int i = 0;
        Matrix L = new Matrix(Arrays.stream(mainMatrix)
                .map(j -> {
                    i ==
                }));
    }
}
