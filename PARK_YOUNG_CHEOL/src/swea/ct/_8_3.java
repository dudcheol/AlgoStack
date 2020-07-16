package swea.ct;

import java.util.Arrays;

/**
 * 과제 3
 * 배열에 정수(음수 포함)들이 저장되어 있다. 연속인 구간들 중 그 합이 가장 큰 구간을 찾는 프로그램을 작성하라.
 */
public class _8_3 {
    static int[] cache;

    /* O(nlogn) 분할정복 */
    static int ans_divideConquer(int[] ary, int low, int high) {
        // 기저
        if (low == high) {
            // 계속해서 반으로 나누다가 결국에 만나는 지점
            return ary[low];
        }

        int mid = (low + high) / 2;

        // left 부분에서 최대합 구하기
        int sum = 0;
        int left = Integer.MIN_VALUE;
        // mid부터 low까지 1씩 '감소'하는 이유?
        // 왼쪽부터 시작해서 연속하는 구간합을 구하는 것이므로, 왼쪽부분 기준에서 가장 오른쪽에 있는 원소가 가장 큰 값이 되버리면
        // 왼쪽부분 기준에서 가장 왼쪽에 있는 원소부터 더해나갔을 때, 왼쪽부분의 최대합이 이상해질 수 있다.
        // ex) 왼쪽부분이 4 -2 -5 5 인 경우, for문이 low(=0)부터 시작한다면 반복문이 끝났을 때 left = 4 가 된다.
        // 하지만 for문이 mid부터 시작한다면 반복문이 끝났을 때 left = 5 가 된다. (left에 5를 먼저 넣으므로)
        for (int i = mid; i >= low; i--) {
            sum += ary[i];
            left = Math.max(left, sum);
        }

        int right = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += ary[i];
            right = Math.max(right, sum);
        }

        // 최대 구간이 나눠진 두 구간 중 하나만 속해있는 경우
        int single = Math.max(ans_divideConquer(ary, low, mid), ans_divideConquer(ary, mid + 1, high));
        // left + right => 최대 구간이 나눠진 두 구간 사이에 존재하는 경우
        int between = left + right;
        // 두 구간 중 하나만 속해있는 경우와 나눠진 두 구간 사이에 있는 경우 중 큰 값을 리턴한다.
        return Math.max(single, between);
    }

    /* O(n) 동적계획법 */
    static int ans_dp(int[] ary) {
        int answer;
        cache = new int[ary.length]; // 인덱스 i을 오른쪽 끝으로 갖는 구간의 최대합
        answer = cache[0] = ary[0];
        for (int i = 1; i < ary.length; i++) {
            answer = Math.max(answer, cache[i] = Math.max(0, cache[i - 1]) + ary[i]);
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
                return ans_divideConquer(ary, 0, ary.length - 1);
            case 3:
                // 동적계획법
                return ans_dp(ary);
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        // 이참에 슬라이딩 윈도우(투포인터 알고리즘) 기법을 익혀보자!
        int method = 2;

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
