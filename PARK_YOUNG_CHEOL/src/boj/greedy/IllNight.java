package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 실버5 ...
 * 이런류 그리디 문제에 정말 약한듯
 * 경우를 나눠서 생각하면 쉽게풀 수 있는 문제다
 */
public class IllNight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int answer = 0;

        if (h == 1 || w == 1) answer = 1;
        else if (h == 2) {
            if (w < 3) answer = 1;
            else answer = Math.min(w / 2 + w % 2, 4);
        } else if (h >= 3) {
            if (w <= 3) answer = w;
            else {
                if (w - 2 < 4) {
                    answer = 4;
                } else answer = w - 2;
            }
        }

        System.out.println(answer);
    }
}
