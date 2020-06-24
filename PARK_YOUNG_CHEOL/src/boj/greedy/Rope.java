package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

// 실버4 - 50분
public class Rope {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] ropes = new Integer[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes, Collections.reverseOrder());

        int answer = Integer.MIN_VALUE;
        int cnt = 1;
        int maxWeight = 0;
        for (int i = 0; i < ropes.length; i++) {
            maxWeight = ropes[i] * cnt;
            answer = Math.max(answer, maxWeight);
            cnt++;
        }
        System.out.println(answer);
    }
}