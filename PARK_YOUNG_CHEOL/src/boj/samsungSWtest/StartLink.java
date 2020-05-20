package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartLink {
    static int n;
    static int[][] s;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static void answer(int k) {
        // 기저
        if (k == n / 2) {
            int start = 0;
            int link = 0;
            // visited가 true인 것들이 start팀, false인 것들은 link팀 이라고 한다
            for (int p = 0; p < n; p++) {
                if (visited[p]) {
                    for (int q = p + 1; q < n; q++) {
                        if (p == q) continue;
                        if (visited[q]) {
                            System.out.println("start -> " + p + ", " + q);
                            start += (s[p][q] + s[q][p]);
                        }
                    }
                } else {
                    for (int q = p + 1; q < n; q++) {
                        if (p == q) continue;
                        if (!visited[q]) {
                            System.out.println(" link -> " + p + ", " + q);
                            link += (s[p][q] + s[q][p]);
                        }
                    }
                }
            }
            System.out.println("==============================");
            answer = Math.min(answer, Math.abs(start - link));
            return;
        }

        // 1,2가 팀일때 3,4가 팀인 경우도 같이 구해지므로 추가작업은 하지 않아도 된다
        for (int j = 0; j < n; j++) {
            if (visited[j]) continue;
            visited[j] = true;
            answer(k + 1);
            visited[j] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = stoi(br.readLine());

        s = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = stoi(st.nextToken());
            }
        }

        visited = new boolean[n];
        answer(0);

        System.out.println(answer);
    }
}
