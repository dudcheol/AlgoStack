package boj.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 실버 3
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * <p>
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 * <p>
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 */
public class MakeOne {
    static int MAX = 1000001;
    static int[] cache = new int[1000001];

    static int op(int n) {
        if (n == 1) {
            /* 실수 */
            // 인자를 리턴하는 형태는 "부분문제"를 해결하는 것이 아니게 되므로 답을 찾을 수 없다.
            return 0;
        }

        if (cache[n] != -1) return cache[n];

        int a = MAX, b = MAX, c = MAX;
        if (n % 3 == 0)
            a = op(n / 3) + 1;
        if (n % 2 == 0)
            b = op(n / 2) + 1;
        c = op(n - 1) + 1;

        return cache[n] = Math.min(c, Math.min(a, b));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Arrays.fill(cache, -1);

        System.out.println(op(n));
    }
}
