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
    static int diceAboveNum = 0;
    static int diceBelowNum = 0;
    static int[] dice = {0,0,0,0,0,0};

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static void dice() {

        /* 실수 :
        * 이동할때마다의 상태를 이용해야 한다
        * 주사위 때문에 2시간 넘게 걸림 */
//        int[][] dice = { // 다이스를 이동했을때 윗면의 숫자를 의미
//                // ex) 이동 전에 윗면의 숫자가 1(행)이었다면 4(남쪽)로 이동했을 때 윗면의 숫자
//                {num[0], num[0], num[0], num[0], num[0]}, // 0 은 무시
//                {num[0], num[4], num[3], num[5], num[2]}, //1
//                {num[0], num[4], num[3], num[1], num[6]}, //2
//                {num[0], num[1], num[6], num[5], num[2]}, //3
//                {num[0], num[6], num[1], num[5], num[2]}, //4
//                {num[0], num[4], num[3], num[6], num[1]}, //5
//                {num[0], num[4], num[3], num[2], num[5]} // 6
//        };

        for (int run = 0; run < commands.length; run++) {
            int moveDir = commands[run];

            // 주사위 지도 위치 이동
            int x = dicePos[0] + dx[moveDir - 1];
            int y = dicePos[1] + dy[moveDir - 1];

            if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) { /* map의 가로 세로는 다르다 */
                continue;
            }

            // 주사위 위치가 이동되면 주사위 위쪽면이 바뀜
            int[] tempDice = dice.clone(); // 이차원배열이 아닌 일차원배열은 클론가능!
            /* 주사위 굴러가는 것과 비슷한 것은 이런식으로 구현할 수 있다!! */
            switch (moveDir){
                case 1: // 동 = 오
                    dice[0] = tempDice[2];
                    dice[2] = tempDice[5];
                    dice[5] = tempDice[3];
                    dice[3] = tempDice[0];
                    break;
                case 2: // 서 = 왼
                    dice[0] = tempDice[3];
                    dice[3] = tempDice[5];
                    dice[5] = tempDice[2];
                    dice[2] = tempDice[0];
                    break;
                case 3: // 북 = 위
                    dice[0] = tempDice[4];
                    dice[4] = tempDice[5];
                    dice[5] = tempDice[1];
                    dice[1] = tempDice[0];
                    break;
                case 4: // 남 = 아래
                    dice[0] = tempDice[1];
                    dice[1] = tempDice[5];
                    dice[5] = tempDice[4];
                    dice[4] = tempDice[0];
                    break;
            }

            diceAboveNum = dice[0];
            diceBelowNum = dice[5];

            int mapNum = map[x][y];
            if (mapNum == 0) { // 이동한 칸에 써있는 수가 0이면 주사위 바닥면 수를 복사
                map[x][y] = diceBelowNum;
            } else { // 0이 아닌 경우에는, 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
                dice[5] = mapNum;
                map[x][y] = 0;
            }

            dicePos[0] = x;
            dicePos[1] = y;
            System.out.println(dice[0]);
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
