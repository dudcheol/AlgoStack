package swea.ct;

import java.util.Arrays;

/**
 * 과제 3
 * 배열에 정수(음수 포함)들이 저장되어 있다. 연속인 구간들 중 그 합이 가장 큰 구간을 찾는 프로그램을 작성하라.
 */
public class _8_3 {
    static int[] cache;

    /* O(nlogn) 분할정복 */
    static int ans_divideConquer(int[] ary) {
        return 0;
    }

    /* O(n) 동적계획법 */
    static int ans_dp(int[] ary) {
        int answer;
        cache = new int[ary.length]; // 인덱스 i을 오른쪽 끝으로 갖는 구간의 최대합
        answer = cache[0] = ary[0];
        for (int i = 1; i < ary.length; i++) {
            answer = Math.max(answer, cache[i] = Math.max(0, cache[i-1]) + ary[i]);
        }
        return answer;
    }

    /* O(n^2) 완전탐색 */
    static int ans_for(int[] ary) {
        int res = ary[0];
        for (int i = 0; i < ary.length; i++) {
            int sum = 0;
            for (int j = i; j < ary.length; j++) {
                res = Math.max(res, sum += ary[j]);
            }
        }
        return res;
    }

    static int solution(int[] ary, int method) {
        switch (method) {
            case 1:
                // n^2 의 경우
                return ans_for(ary);
            case 2:
                // 분할정복
                return ans_divideConquer(ary);
            case 3:
                // 동적계획법
                return ans_dp(ary);
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        // 이참에 슬라이딩 윈도우(투포인터 알고리즘) 기법을 익혀보자!
        int method = 3;

        // 테스트 케이스
        int[][] test_case = {
                {2}, {-1}, {-2, 1, -2}, {1, 2, 3, 4, 5}, {-3, -4, -5, -2, -1, 0},
                {3, 2, -6, 4, 0}, {100, -100, 50, -50, 1000}, {-1000, -1000, -1000, -1000},
                {1, 2, -1, 15, 0, 5, 1000, -1000}, {-2, -3, 4, -1, -2, 1, 5, -3}
        };

        // 답안
        int[] answer = {2, -1, 1, 15, 0, 5, 1000, -1000, 1022, 7};

        for (int i = 0; i < test_case.length; i++) {
            int ans = solution(test_case[i], method);
            if (answer[i] == ans) {
                System.out.println("success! : " + answer[i] + " : " + ans);
            } else System.out.println("fail : " + answer[i] + " : " + ans);
        }
    }
}
