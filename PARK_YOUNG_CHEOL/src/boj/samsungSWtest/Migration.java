package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1시간 12분
 */
public class Migration {
    static int n;
    static int L;
    static int R;
    static int[][] map;
    static int[][] visited;
    // 상 좌 우 하
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int check = 1;
    static int answer;

    static void dfs(int y, int x) {
        // 기저 : 범위를 벗어나면 종료
        if (y < 0 || x < 0 || y >= n || x >= n) return;

        // 현재 좌표에서 국경선 열 수 있는 곳 확인
        for (int move = 0; move <= 3; move++) {
            int moveY = y + dy[move];
            int moveX = x + dx[move];

            if (moveY < 0 || moveX < 0 || moveY >= n || moveX >= n) continue;

            int diff = Math.abs(map[y][x] - map[moveY][moveX]);
            if (diff >= L && diff <= R) {
                // 국경선 열 수 있는 국가 찾음
                visited[y][x] = check;
                if (visited[moveY][moveX] != 0) continue;
                dfs(moveY, moveX);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean breakFlag = true;
            int zeroCnt = 0;
            check = 1;
            visited = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] != 0) continue;
                    dfs(i, j);
                    check++;
                    breakFlag = false;
                    zeroCnt++;
                }
            }
            if (breakFlag) break;
            // dfs 수행했음에도 visited가 모두 0이면 인구이동이 없던 것이므로 반복문 종료
            if (zeroCnt == n * n) break;
            answer++;

            // 같은 연합끼리 인구 이동 시작
            int[] unions = new int[check];
            int[] unionsCnt = new int[check];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) continue;
                    unions[visited[i][j]] += map[i][j];
                    unionsCnt[visited[i][j]]++;
                }
            }

            // 인구 이동 결과로 map 업데이트
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) continue;
                    map[i][j] = unions[visited[i][j]] / unionsCnt[visited[i][j]];
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
}
