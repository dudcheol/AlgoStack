package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 골드5 - 55분
public class Laboratory {
    static int N;
    static int M;
    static int[][] map;
    static int answer = Integer.MIN_VALUE;
    static ArrayList<int[]> virusPos;
    static int[][] tempMap;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int wall, int x) {
        if (wall == 3) {
            // 바이러스가 퍼져나가는 것 시뮬레이션 하기
//            int[][] tempMap = deepCopy(map);
            copyMap();
            for (int[] virus : virusPos) {
                spreadVirus(virus[0], virus[1]);
            }

            // 바이러스 퍼진 후의 안전 영역 크기 구하기
            int safeArea = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tempMap[i][j] == 0) safeArea += 1;
                }
            }

            answer = Math.max(answer, safeArea);
            return;
        }

//        for (int i = x; i < N; i++) {
//            for (int j = 0; j < M; j++) { /* 실수 */
//                if (map[i][j] != 0) continue; // 빈 칸이 아니면 컨티뉴
//                map[i][j] = 1;
//                dfs(wall + 1, i);
//                map[i][j] = 0;
//            }
//        }
        /* 이 방법을 사용하면 배열의 인덱스를 좀 더 효율적으로 접근할 수 있음
        * 위 주석의 '이중for문'을 사용하면, 지나쳐도될 인덱스까지 검사하게 됨 */
        for (int i = x; i < N * M; i++) {
            int _x = i / M;
            int _y = i % M;

            if (map[_x][_y] == 0) {
                map[_x][_y] = 1;
                dfs(wall + 1, i + 1);
                map[_x][_y] = 0;
            }
        }
    }

    static void spreadVirus(int x, int y) {
        // 2인 구역을 중심으로 바이러스 전염 시뮬레이션
        for (int dir = 0; dir < 4; dir++) {
            int moveX = x + dx[dir];
            int moveY = y + dy[dir];
            if (moveX < 0 || moveY < 0 || moveX >= N || moveY >= M) continue;
            if (tempMap[moveX][moveY] == 0) {
                tempMap[moveX][moveY] = 2;
                spreadVirus(moveX, moveY);
            }
        }
    }

//    static int[][] deepCopy(int[][] origin) {
//        int[][] result = new int[N][M];
//        for (int i = 0; i < origin.length; i++) {
//            result[i] = origin[i].clone();
//        }
//        return result;
//    }

    static void copyMap() {
        for (int i = 0; i < map.length; i++) {
            tempMap[i] = map[i].clone();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tempMap = new int[N][M];
        virusPos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusPos.add(new int[]{i, j});
                }
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }
}
