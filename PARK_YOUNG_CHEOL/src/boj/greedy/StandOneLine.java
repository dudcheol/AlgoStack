package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 실버2 - 정답참고
 * 2시간 넘게 걸려서도 못품...ㄷㄷ
 */
public class StandOneLine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] answer = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            int order = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= n; j++) {
                if (cnt == order && answer[j] == 0) {
                    answer[j] = i;
                    break;
                }
                if (answer[j] == 0) cnt++;
            }
        }

        for (int a : answer) {
            if (a==0) continue;
            System.out.print(a + " ");
        }
    }
}
