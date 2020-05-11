package programmers.summerWinterCoding2018;

import java.util.Arrays;

/**
 * 25분
 *
 * 재정의/추상화/계획세우기 모두 잘했다
 * 정렬하고 시작하는 부분과 어떻게하면 효율성을 높힐지 고민하는 부분 칭찬해~
 */
public class NumberGame {
    static int solution(int[] A, int[] B) {
        int answer = 0;
        boolean[] visited = new boolean[A.length];

        Arrays.sort(A);
        Arrays.sort(B);

        int jump = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = jump; j < B.length; j++) {
                if (visited[j]) continue;
                if (A[i] < B[j]) {
                    visited[j] = true;
                    answer++;
                    jump = j;
                    j = B.length;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};

//        int[] A = {2, 2, 2, 2};
//        int[] B = {1, 1, 1, 1};

        System.out.println(solution(A, B));
    }
}
