import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {
//reference https://www.techiedelight.com/measure-elapsed-time-execution-time-java/

    public static long findMatrix(int[][] matrix){
        long startTime = System.nanoTime();
        int m = matrix.length;
        int n = matrix[0].length;
        if(m > 2){
            for(int i = 0; i < m; i++){
                for(int j = i + 1; j < n; j++){
                    if(matrix[i][j] == 1){
                        for(int k = j + 1; k < n; k++){
                            if(matrix[j][k] == 1 && matrix[i][k] == 1)
                                break;
                        }
                    }
                }
            }
        }
        long endTime = System.nanoTime();
//        System.out.println(startTime + " start and end " + endTime);
        long timeElapsed = endTime - startTime;
//        System.out.println("n " + n + "Time" + timeElapsed);
        return timeElapsed;
    }


    public static int[][] RandMatrix(int n){
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(rand.nextDouble() <= 0.5){
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }


    public static double[][] caller(int n){
        double[][] result = new double[2][n];
        for(int i = 0; i < n; i++){
            int pow = (int)Math.pow(2, (double)i);
            int[][] matrix = RandMatrix(pow);
            long time = (findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix)) / 5;
            result[0][i] = i;
            result[1][i] = Math.log10((double)time);
//            System.out.println("i " + i + "time" + result[1][i]);
        }
        return result;
    }



    public static void main(String args[]){
        double[][] result = caller(11);
        Plot2d.show(result);
    }

}
