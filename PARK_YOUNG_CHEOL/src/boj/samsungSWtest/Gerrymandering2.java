package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gerrymandering2 {
    static int N;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int total;

    static int[] countMap(int x, int y, int d1, int d2) {
        int[] population = new int[5 + 1]; // 인덱스0은 사용하지 않음
        int[][] result = new int[N + 1][N + 1];
        // 5 표시하기 (가장자리)
        int i = y;
        int j = x;

        for (i = y; i >= y - d1; i--) {
            result[j++][i] = 5;
        }
        j = x;
        for (i = y; i <= y + d2; i++) {
            result[j++][i] = 5;
        }
        j = x + d1;
        for (i = y - d1; i <= y - d1 + d2; i++) {
            result[j++][i] = 5;
        }
        j = x + d2;
        for (i = y + d2; i >= y + d2 - d1; i--) {
            result[j++][i] = 5;
        }

        // 1 표시하기
        for (i = 1; i < x + d1; i++) {
            for (j = 1; j <= y; j++) {
                if (result[i][j] == 5) break;
//                result[i][j] = 1;
                population[1] += map[i][j];
            }
        }

        // 2 표시하기
        for (i = 1; i <= x + d2; i++) {
            for (j = N; j >= y + 1; j--) {
                if (result[i][j] == 5) break;
//                result[i][j] = 2;
                population[2] += map[i][j];
            }
        }

        // 3 표시하기
        for (i = x + d1; i <= N; i++) {
            for (j = 1; j < y - d1 + d2; j++) {
                if (result[i][j] == 5) break;
//                result[i][j] = 3;
                population[3] += map[i][j];
            }
        }

        // 4 표시하기
        for (i = x + d2 + 1; i <= N; i++) {
            for (j = N; j >= y - d1 + d2; j--) {
                if (result[i][j] == 5) break;
//                result[i][j] = 4;
                population[4] += map[i][j];
            }
        }

        // 5 안쪽 채우기
//        for (i = 1; i <= N; i++) {
//            for (j = 1; j <= N; j++) {
//                if (result[i][j] == 0) result[i][j] = 5;
//            }
//        }

        population[5] = total;
        for (i = 1; i <= 4; i++) {
            population[5] -= population[i];
        }

        return population;
    }

    static void dfs(int x, int y, int d1, int d2) {
        // 기저
        if (!(d1 >= 1 && d2 >= 1 && x >= 1 && x < x + d1 + d2 && x + d1 + d2 <= N && y - d1 >= 1 &&
                y - d1 < y && y < y + d2 && y + d2 <= N))
            return;

//        Arrays.fill(population, 0);
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                population[marked[i][j]] += map[i][j];
//            }
//        }

        // 인구의 최대값과 최솟값 차이 구하기
        int[] population = countMap(x, y, d1, d2);
        Arrays.sort(population);
        answer = Math.min(answer, population[5] - population[1]);

//        int[][] markMap = markMap(x, y, d1, d2);
//        if (answer < 20) {
//            System.out.println("x=" + x + ", y=" + y + ", d1=" + d1 + ", d2=" + d2);
//            printMap(markMap);
//            System.out.println("answer=" + answer);
//            System.out.println();
//        }

        dfs(x, y, d1 + 1, d2);
        dfs(x, y, d1, d2 + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 5 ≤ N ≤ 20
         * 1 ≤ A[r][c] ≤ 100
         */
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        for (int i = 2; i < N - 1; i++) {
            for (int j = 1; j <= N - 2; j++) {
                dfs(j, i, 1, 1);
            }
        }

        System.out.println(answer);
    }

    static void printMap(int[][] map) {
        for (int[] m : map) {
            for (int _m : m) {
                System.out.print(_m + " ");
            }
            System.out.println();
        }
    }
}
