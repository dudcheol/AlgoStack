package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1시간 30분
 */
public class Dust {
    static int r;
    static int c;
    static int t;
    static int[][] map;
    static int[] airCleanerUpSide;
    static int[] airCleanerDownSide;
    static int answer = 0;
    static int[] dx = {-1, 0, 1, 0}; // 위, 오른, 아래, 위 방향
    static int[] dy = {0, 1, 0, -1}; // 위, 오른, 아래, 위 방향
    static int[] upSideDir = {1, 0, 3, 2};
    static int[] downSideDir = {1, 2, 3, 0};

    static void simulate() {
        int time = 0;
        while (time != t) {
            int[][] temp = new int[r][c]; // 이 시간대에 퍼질 미세먼지 구하기

            // 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] == 0) continue;
                    // 현재 i,j에서 4방향 확산기키기
                    int currentPosDust = map[i][j];
                    int effectDust = currentPosDust / 5;

                    // 확산될 방향 찾아서 값 넣고 갯수 세기
                    int effectArea = 0; // 확산된 방향 수
                    for (int d = 0; d <= 3; d++) {
                        int moveX = i + dx[d];
                        int moveY = j + dy[d];
                        if (!(moveX < 0 || moveY < 0 || moveX >= r || moveY >= c || map[moveX][moveY] == -1)) {
                            temp[moveX][moveY] += effectDust;
                            effectArea++;
                        }
                    }
                    // 남은 미세먼지양 적용
                    temp[i][j] += /*실수*/ currentPosDust - ((currentPosDust / 5) * effectArea);
                }
            }
            // 확산된 결과 temp로 map 업데이트
            temp[airCleanerUpSide[0]][airCleanerUpSide[1]] = -1;
            temp[airCleanerDownSide[0]][airCleanerDownSide[1]] = -1;
            map = temp;

            // 공기청정기가 작동한다.
            int upSideAirPosX = airCleanerUpSide[0];
            int upSideAirPosY = airCleanerUpSide[1];
            int downSideAirPosX = airCleanerDownSide[0];
            int downSideAirPosY = airCleanerDownSide[1];
            int[][] airTemp = new int[r][c];

            int preUpDust = 0;
            int preDownDust = 0;
            for (int i = 0; i <= 3; i++) { // 공기청정기 4가지 방향으로 이동함
                // 위쪽 공기청정기 i번째 방향으로 계속 이동
                while (true) {
                    upSideAirPosX += dx[upSideDir[i]];
                    upSideAirPosY += dy[upSideDir[i]];
                    if (upSideAirPosX < 0 || upSideAirPosY < 0 || upSideAirPosX >= r || upSideAirPosY >= c || map[upSideAirPosX][upSideAirPosY] == -1) {
                        upSideAirPosX -= dx[upSideDir[i]]; // 이전위치로 돌아가기
                        upSideAirPosY -= dy[upSideDir[i]];
                        break;
                    }
                    airTemp[upSideAirPosX][upSideAirPosY] = preUpDust;
                    preUpDust = map[upSideAirPosX][upSideAirPosY];
                }

                // 아래쪽 공기청정기 i번째 방향으로 계속 이동
                /* 위에서 이미 구현한 것과 비슷하다고 밑에 복붙할거면
                * 바꿔야할 값들 신중하게 보고 바꾸자... 여기서 실수로 시간 많이 뺏어먹음 */
                while (true) {
                    downSideAirPosX += dx[downSideDir[i]];
                    downSideAirPosY += dy[downSideDir[i]];
                    if (downSideAirPosX < 0 || downSideAirPosY < 0 || downSideAirPosX >= r || downSideAirPosY >= c || map[downSideAirPosX][downSideAirPosY] == -1) {
                        downSideAirPosX -= dx[downSideDir[i]]; // 이전위치로 돌아가기
                        downSideAirPosY -= dy[downSideDir[i]];
                        break;
                    }
                    airTemp[downSideAirPosX][downSideAirPosY] = preDownDust;
                    preDownDust = map[downSideAirPosX][downSideAirPosY];
                }
            }

            // 바람으로 바뀐 모양으로 map 업데이트
            for (int i = 0; i < r; i++) {
                if (i == 0 || i == r - 1 || i == airCleanerUpSide[0] || i == airCleanerDownSide[0]) continue;
                for (int j = 0; j < c; j++) {
                    if (j == 0 || j == c - 1 ) continue;
                    airTemp[i][j] = map[i][j];
                }
            }
            airTemp[airCleanerUpSide[0]][airCleanerUpSide[1]] = -1;
            airTemp[airCleanerDownSide[0]][airCleanerDownSide[1]] = -1;
            map = airTemp;

            time++;
        }

        for (int[] m : map) {
            for (int _m : m) {
                if (_m == -1) continue;
                answer += _m;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1)
                    if (airCleanerUpSide == null) airCleanerUpSide = new int[]{i, j};
                    else airCleanerDownSide = new int[]{i, j};
            }
        }

        simulate();
        System.out.println(answer);
    }
}
