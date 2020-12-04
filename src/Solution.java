import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
//reference https://www.techiedelight.com/measure-elapsed-time-execution-time-java/
    static Lock lock = new ReentrantLock();
    static int maxSize;
    public static long findMatrix(int[][] matrix){
        lock.lock();
//        long startTime = System.nanoTime();
        maxSize = 0;
        int n = matrix.length;
        boolean[] visited = new boolean[n];
        List<Integer> result = new LinkedList<>();
        helper(1, matrix, result);
//        System.out.println(maxSize);
//        long endTime = System.nanoTime();
//        System.out.println(startTime + " start and end " + endTime);
//        long timeElapsed = endTime - startTime;
        lock.unlock();
//        System.out.println("n " + n + "Time" + timeElapsed);
        return maxSize;
    }

    public static void helper(int start, int[][] matrix, List<Integer> result){
        for(int i = start; i <= matrix.length; i++){
            if(validIndependentSet(i - 1, matrix, result)){
                result.add(i - 1);
                helper(i + 1, matrix, result);
                result.remove(result.size() - 1);
            }
        }
//        System.out.println(result);
        maxSize = Math.max(result.size(), maxSize);
    }

    public static boolean validIndependentSet(int curr, int[][] matrix, List<Integer> result){
        for(int r : result){
            if(matrix[curr][r] != matrix[r][curr])
                System.out.println("Error input");
            if(matrix[curr][r] == 1)
                return false;
        }
        return true;
    }




    public static int[][] RandMatrix(int n){
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(rand.nextDouble() <= 0.75){
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
            }
            matrix[i][i] = 0;
        }
        return matrix;
    }




    public static double[][] caller(int n){
        double[][] result = new double[2][n];
        for(int i = 0; i < n; i++){
            int pow = (int)Math.pow(2, (double)i);
            int[][] matrix = RandMatrix(pow);
//            int[][] matrix = BipartiteMatrix(pow);
            long time = (findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix) + findMatrix(matrix)) / 5;
            result[0][i] = i;
            result[1][i] = time;
            System.out.println("log vertices number: " + i + " || MIS:" + result[1][i] );
//            System.out.println("maximum independent vertices size: "  +  maxSize);
        }
        return result;
    }



    public static void main(String args[]){
//        int[][] matrix = {{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0 , 1}, {1, 1, 1, 0}};
//        findMatrix(matrix);
        double[][] result = caller(11);
        Plot2d.show(result);
    }






//    public static int[][] BipartiteMatrix(int n){
//        int[][] matrix = new int[n][n];
//        Random rand = new Random();
////        int left = rand.nextInt(n);
////        int right = n - left;
//        for(int i = 0; i < n/2; i++){
//            for(int j = n/2; j < n; j++){
//                if(rand.nextDouble() <= 0.5)
//                    matrix[i][j] = 1;
//            }
//        }
//        return matrix;
//    }



    //        boolean flag = true;
//        if(m > 2){
//            for(int i = 0; i < m; i++){
//                if(!flag)
//                    break;
//                for(int j = i + 1; j < n; j++){
//                    if(!flag)
//                        break;
//                    if(matrix[i][j] == 1){
//                        for(int k = j + 1; k < n; k++){
//                            if(!flag)
//                                break;
//                            if(matrix[j][k] == 1 && matrix[i][k] == 1){
//                                flag = false;
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        }
}
