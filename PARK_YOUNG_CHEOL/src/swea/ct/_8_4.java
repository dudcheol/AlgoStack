package swea.ct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 과제 4
 * (어려움) 배열에 정수(음수 포함)들이 저장되어 있다.
 * 배열의 일부 값들을 골라서 배열에 있는 순서대로 보면 증가하는 순서가 될 수 있다.
 * 이러한 것들 중 가장 긴 것을 찾는 프로그램을 작성하라.
 */
public class _8_4 {
    static int n;
    static int[] ary;
    static int[] cache;

    static int lis(int start) {
        if (cache[start] != -1) {
            return cache[start];
        }

        cache[start] = 1;
        for (int i = start + 1; i < n; i++) {
            if (ary[start] < ary[i]) {
                cache[start] = Math.max(cache[start], lis(i) + 1);
            }
        }
        return cache[start];
    }

    public static void main(String[] args) throws Exception {
        // input 배열의 크기, 배열에 들어있는 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ary = new int[n];
        cache = new int[n];
        Arrays.fill(cache, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(lis(i), answer);
        }
        System.out.println(answer);
    }
}
