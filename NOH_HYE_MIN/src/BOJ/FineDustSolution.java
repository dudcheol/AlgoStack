package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class FineDustSolution {

    public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    public static int[][] map;
    public static int[][] copyMap;
    public static int R, C, T;
    public static Point[] air;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        copyMap = new int[R][C];
        air = new Point[2];

        int cnt = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    air[cnt] = new Point(i, 0);
                    copyMap[i][j] = -1;
                    cnt++;
                }
            }
        }

        while (T > 0) {
            // 미세먼지 확산
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        dust(i, j);
                    }
                }
            }

            // copyMap 내용 복사, 초기화
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] = copyMap[i][j];
                    if (copyMap[i][j] == -1) {
                        continue;
                    }
                    copyMap[i][j] = 0;
                }
            }
            leftAir(air[0].x, air[0].y);
            rightAir(air[1].x, air[1].y);
            T--;
        }

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        System.out.println(sum);

    }

    // 미세먼지 네방향으로 확산
    public static void dust(int r, int c) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int newR = r + dir[i][0];
            int newC = c + dir[i][1];

            if (isIn(newR, newC) && map[newR][newC] != -1) {
                cnt++;
                copyMap[newR][newC] += map[r][c] / 5;
            }
        }
        copyMap[r][c] += map[r][c] - (map[r][c] / 5) * cnt;
    }

    // 공기청정기 작동
    public static void leftAir(int x, int y) {
        // left rotation
        x--; // 위로 이동해서 시작
        // 하
        for (int i = 0; i < air[0].x - 1; i++) {
            map[x][y] = map[x - 1][y];
            x--;
        }
        // 좌
        for (int i = 0; i < C - 1; i++) {
            map[x][y] = map[x][y + 1];
            y++;
        }
        // 상
        for (int i = 0; i < air[0].x; i++) {
            map[x][y] = map[x + 1][y];
            x++;
        }
        // 우
        for (int i = 0; i < C - 2; i++) {
            map[x][y] = map[x][y - 1];
            y--;
        }

        map[x][y] = 0;

    }

    public static void rightAir(int x, int y) {
        // right rotation
        x++; // 아래로 이동해서 시작
        // 상
        for (int i = 0; i < R - air[1].x - 2; i++) {
            map[x][y] = map[x + 1][y];
            x++;
        }
        // 좌
        for (int i = 0; i < C - 1; i++) {
            map[x][y] = map[x][y + 1];
            y++;
        }
        // 하
        for (int i = 0; i < R - air[1].x - 1; i++) {
            map[x][y] = map[x - 1][y];
            x--;
        }
        // 우
        for (int i = 0; i < C - 2; i++) {
            map[x][y] = map[x][y - 1];
            y--;
        }

        map[x][y] = 0;
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}

