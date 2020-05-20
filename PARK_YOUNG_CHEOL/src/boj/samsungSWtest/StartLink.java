package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 40분 - 시간초과
 * 1시간30분 ... 도저히 모르겠음 ㅠㅠ
 * 해법확인함
 */
public class StartLink {
    static int n;
    static int[][] s;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static void answer(int idx, int k) {
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
                            //System.out.println("start -> " + p + ", " + q);
                            start += (s[p][q] + s[q][p]);
                        }
                    }
                } else {
                    for (int q = p + 1; q < n; q++) {
                        if (p == q) continue;
                        if (!visited[q]) {
                            //System.out.println(" link -> " + p + ", " + q);
                            link += (s[p][q] + s[q][p]);
                        }
                    }
                }
            }
            //System.out.println("==============================");
            answer = Math.min(answer, Math.abs(start - link));
            return;
        }

        // 0으로 시작하면 1,2 -> 1,3 -> 1,4 -> 2,1 이 되버림 2,1과 1,2는 동일하므로 생략해줘야함
        for (int j = idx /* 0으로 시작하면 중복되는 경우가 많아짐 */; j < n; j++) {
            /* 조합풀때 이거때문에 애 많이 먹었으면서.. 또 같은 실수를 했구나... */
            if (visited[j]) continue;
            visited[j] = true;
            answer(j, k + 1);
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
//        startTeam = new int[n / 2];
        answer(0, 0);

        System.out.println(answer);
    }
}
