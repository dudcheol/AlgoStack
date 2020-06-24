package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 골드5 - 55분
public class Laboratory {
    static int N;
    static int M;
    static int[][] map;
    static int answer = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int wall, int x) {
        if (wall == 3) {
            // 바이러스가 퍼져나가는 것 시뮬레이션 하기
            int[][] tempMap = deepCopy(map);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        spreadVirus(tempMap, i, j);
                    }
                }
            }

//            /* 로그 */
//            System.out.println("--------------------");
//            for (int i=0;i<N;i++){
//                for (int j=0;j<M;j++){
//                    System.out.print(tempMap[i][j]+" ");
//                }
//                System.out.println();
//            }

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

        for (int i = x; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) continue; // 빈 칸이 아니면 컨티뉴
                map[i][j] = 3;
                dfs(wall + 1, i);
                map[i][j] = 0;
            }
        }
    }

    static void spreadVirus(int[][] simulMap, int x, int y) {
        // 2인 구역을 중심으로 바이러스 전염 시뮬레이션
        for (int dir = 0; dir < 4; dir++) {
            int moveX = x + dx[dir];
            int moveY = y + dy[dir];
            if (moveX < 0 || moveY < 0 || moveX >= N || moveY >= M) continue;
            if (simulMap[moveX][moveY] == 0) {
                simulMap[moveX][moveY] = 2;
                spreadVirus(simulMap, moveX, moveY);
            }
        }
    }

    static int[][] deepCopy(int[][] origin) {
        int[][] result = new int[N][M];
        for (int i = 0; i < origin.length; i++) {
            result[i] = origin[i].clone();
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(answer);
    }
}
