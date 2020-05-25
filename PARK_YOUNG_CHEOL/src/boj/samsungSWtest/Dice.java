package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dice {
        static int[][] map;
    static int[] dicePos = {0, 0};
    static int[] commands;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    //    static int[] diceC = {1, 5, 6, 2}; // 주사위 세로방향
//    static int[] diceR = {0, 3, -1, 4}; // 주사위 가로방향 => 0일 경우 diceC[diceCpos]로 바꿔야함 // -1일 경우 0의 반대
//    static int diceCPos = 0;
//    static int diceRPos = 0;
    static int diceAboveNum = 1;
    static int diceBelowNum = 6;
    static int[] diceNum = {0, 0, 0, 0, 0, 0, 0};
    static int[] num = {0, 1, 2, 3, 4, 5, 6}; // 0번째 원소는 무시
    static int[] diceOppositeSide = {0, 6, 5, 4, 3, 2, 1};

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static void dice() {

        /* 실수 :
        * 이동할때마다의 상태를 이용해야 한다
        * 주사위 때문에 2시간 넘게 걸림 */
        int[][] dice = { // 다이스를 이동했을때 윗면의 숫자를 의미
                // ex) 이동 전에 윗면의 숫자가 1(행)이었다면 4(남쪽)로 이동했을 때 윗면의 숫자
                {num[0], num[0], num[0], num[0], num[0]}, // 0 은 무시
                {num[0], num[4], num[3], num[5], num[2]}, //1
                {num[0], num[4], num[3], num[1], num[6]}, //2
                {num[0], num[1], num[6], num[5], num[2]}, //3
                {num[0], num[6], num[1], num[5], num[2]}, //4
                {num[0], num[4], num[3], num[6], num[1]}, //5
                {num[0], num[4], num[3], num[2], num[5]} // 6
        };

        for (int run = 0; run < commands.length; run++) {
            int moveDir = commands[run];

            // 주사위 지도 위치 이동
            int x = dicePos[0] + dx[moveDir - 1];
            int y = dicePos[1] + dy[moveDir - 1];

            if (x < 0 || y < 0 || x >= map.length || y >= map.length) {
                continue;
            }

            // 주사위 위치가 이동되면 주사위 위쪽면이 바뀜
            diceAboveNum = dice[diceAboveNum][commands[run]];
            diceBelowNum = diceOppositeSide[diceAboveNum];

            int mapNum = map[x][y];
            if (mapNum == 0) { // 이동한 칸에 써있는 수가 0이면 주사위 바닥면 수를 복사
                map[x][y] = diceBelowNum;
            } else { // 0이 아닌 경우에는, 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
                diceNum[diceBelowNum] = mapNum;
                map[x][y] = 0;
            }

            dicePos[0] = x;
            dicePos[1] = y;
            System.out.println(diceNum[diceAboveNum]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[stoi(st.nextToken())][stoi(st.nextToken())];
        dicePos = new int[]{stoi(st.nextToken()), stoi(st.nextToken())};
        int commandSize = stoi(st.nextToken());

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        commands = new int[commandSize];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < commandSize; i++) {
            commands[i] = stoi(st.nextToken());
        }

        dice();
    }
}
