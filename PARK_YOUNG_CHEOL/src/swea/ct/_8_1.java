package swea.ct;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 과제 1
 * 피보나치 수열을 계산하는 3가지 방법을 모두 작성해 보고 실행시간을 비교하라.
 * 결과 값이 빨리 커지는 것에 주의하라.
 */
public class _8_1 {
    static int[] cache;

    // 일반 재귀를 사용한 풀이
    static int fibo1(int k) {
        if (k == 0) return 0;
        if (k == 1) return 1;
        return fibo1(k - 1) + fibo1(k - 2);
    }

    // 메모이제이션을 활용한 풀이 (동적계획법)
    static int fibo2(int k) {
        // 기저
        if (k == 0) return cache[k] = 0;
        if (k == 1) return cache[k] = 1;

        // 메모이제이션
        if (cache[k] != -1) {
            return cache[k];
        } else return cache[k] = fibo2(k - 1) + fibo2(k - 2);
    }

    // 일반 반복문을 활용한 풀이
    static int fibo3(int k) {
        if (k < 2) return k;

        int a = 1, b = 1, res = 0;
        for (int i = 0; i < k - 2; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.print("fibo(k)를 구하기 위한 k값을 입력하시오 : ");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        System.out.println(fibo1(k));

        cache = new int[100];
        Arrays.fill(cache, -1);
        System.out.println(fibo2(k));

        System.out.println(fibo3(k));
    }
}
