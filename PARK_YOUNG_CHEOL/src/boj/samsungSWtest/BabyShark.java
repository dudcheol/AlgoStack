package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1시간30분동안 못풂
 */
public class BabyShark {
    static int n;
    static int answer = Integer.MAX_VALUE;
    static int sharkY;
    static int sharkX;
    static int[] dy = {-1, 0, 0, 1}; // 위 왼 오 아
    static int[] dx = {0, -1, 1, 0};
    static boolean hasEatable = false;
    static boolean visited[][];

    static void dfs(int y, int x, int sharkSize, int eatFish, int time, int[][] map) {
        //기저
        // map에 아기 상어보다 작은 물고기가 있는지 확인
        hasEatable = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && map[i][j] < sharkSize) {
                    hasEatable = true;
                    break;
                }
            }
            if (hasEatable) i = n;
        }
        //더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
        if (!hasEatable) {
            answer = Math.min(answer, time);
            return;
        }

        // 상어 이동
        for (int i = 0; i < 4; i++) {
            // 네 방향 중 먹을 수 있는 애로 이동
            int moveY = y + dy[i];
            int moveX = x + dx[i];
            if (moveY < 0 || moveX < 0 || moveY >= n || moveX >= n) continue;

            if (map[moveY][moveX] <= sharkSize && !visited[moveY][moveX]) {
                // 움직일 위치에 물고기가 뭔지 확인
                int[][] tempMap = deepCopy(map);
                if (tempMap[moveY][moveX] != 0) {
                    if (tempMap[moveY][moveX] < sharkSize) { // 먹을 수 있음
                        tempMap[moveY][moveX] = 0;
                        eatFish++;
                        // 물고기를 먹으면 지나온 곳 초기화
//                        for (boolean[] v : visited) Arrays.fill(v, false);
                        if (eatFish == sharkSize) {
                            sharkSize++;
                            eatFish = 0;
                        }
                        dfs(moveY, moveX, sharkSize, eatFish, time + 1, tempMap);
                    }
                } else {
                    visited[y][x] = true;
                    dfs(moveY, moveX, sharkSize, eatFish, time + 1, tempMap);
                    visited[y][x] = false;
                }
            }
        }
//        // 먹을 수 있는 애가 없으면 이동할 수 있는 곳으로 이동
//        for (int i = 0; i < 4; i++) {
//            // 네 방향 중 먹을 수 있는 애로 이동
//            int moveY = y + dy[i];
//            int moveX = x + dx[i];
//            if (moveY < 0 || moveX < 0 || moveY >= n || moveX >= n) continue;
//            if (map[moveY][moveX] <= sharkSize && !visited[moveY][moveX]) {
//                visited[y][x] = true;
//                dfs(moveY, moveX, sharkSize, eatFish, time + 1, deepCopy(map));
//                visited[y][x] = false;
//            }
//        }
    }

    static int[][] deepCopy(int[][] origin) {
        int[][] res = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            res[i] = origin[i].clone();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    if (map[i][j] == 9) {
                        sharkY = i;
                        sharkX = j;
                        map[i][j] = 0;
                    }
                }
            }
        }

        dfs(sharkY, sharkX, 2, 0, 0, map);
        System.out.println(answer);
    }
}
