package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 골드5 - 1사간 10분
public class RoboticVacuumCleaner {
    static int N;
    static int M;
    static int[][] map;
    static int rR;
    static int rC;
    static int rDir;
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};
    static int[] dirLeft = {3, 0, 1, 2}; // 왼쪽
    static int[] dirBack = {2, 3, 0, 1}; // 후진
    static int answer = 0;

    static void recursion(int x, int y, int dir, int end) {
        /* 1 */
        // 현재 위치 청소
        if (map[x][y] == 0) {
            map[x][y] = 2;
            answer++;
            // print();
        }

        /* 2 */
        int currentLeft = dirLeft[dir];
        int nx = x + dx[currentLeft];
        int ny = y + dy[currentLeft];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;
        if (map[nx][ny] == 0) {
            /* 2-1 */
            // 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
            // 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
            recursion(nx, ny, currentLeft, 0);
        } else {
            // 왼쪽 방향에 청소할 공간이 없다면,
            if (end != 4) {
                /* 2-2 */
                // 그 방향으로 회전하고 2번으로 돌아간다.
                recursion(x, y, currentLeft, end + 1);
            } else {
                // 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는,
                int back = dirBack[dir];
                int bx = x + dx[back];
                int by = y + dy[back];

                if (bx < 0 || by < 0 || bx >= N || by >= M) return;
                if (map[bx][by] != 1)
                    /* 2-3 */
                    // 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
                    recursion(bx, by, dir, 0);
                else
                    /* 2-4 */
                    // 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
                    return;
            }
        }
    }

    static void print() {
        System.out.println("--------------------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        rR = Integer.parseInt(st.nextToken());
        rC = Integer.parseInt(st.nextToken());
        rDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(rR, rC, rDir, 0);
        System.out.println(answer);
    }
}
