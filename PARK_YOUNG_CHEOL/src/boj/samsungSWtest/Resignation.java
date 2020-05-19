package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그냥 엄청 오래품.. 한 3시간 붙잡은듯..... 풀이를 봤다
 */
public class Resignation {
    static int n;
    static int[] t;
    static int[] p;
    static int max_money = Integer.MIN_VALUE;

    static void answer(int k, int money) {
        // 기저
        if (k > n) return;
        if (k == n) {
            max_money = Math.max(max_money, money);
            return;
        }

        answer(k + t[k], money + p[k]);
        answer(k + 1, money); /* 이게 포인트!! */
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        t = new int[n];
        p = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        answer(0, 0);

        System.out.println(max_money);
    }
}
