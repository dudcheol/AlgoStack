package BOJ;
import java.util.Scanner;

public class RollTheDice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int x = sc.nextInt(); // 행
        int y = sc.nextInt(); // 열
        int k = sc.nextInt();

        int[] dice = new int[7]; // 주사위

        // 지도 초기
        int[][] map = new int[N][M];
        for(int row=0; row<N; row++){
            for(int col=0; col<M; col++){
                map[row][col] = sc.nextInt();
            }
        }

        // 명령
        int dir;
        for(int i=0; i<k; i++){
            dir = sc.nextInt();
            int top = dice[1];
            boolean flag = false;
            switch (dir){
                case 1: // 동
                    if(y+1<M) {
                        y += 1;
                        dice[1] = dice[4];
                        dice[4] = dice[6];
                        dice[6] = dice[3];
                        dice[3] = top;
                        flag = true;
                    }
                    break;
                case 2: // 서
                    if(y-1>=0){
                        y -= 1;
                        dice[1] = dice[3];
                        dice[3] = dice[6];
                        dice[6] = dice[4];
                        dice[4] = top;
                        flag = true;
                    }
                    break;
                case 3: // 북
                    if(x-1>=0){
                        x -= 1;
                        dice[1] = dice[5];
                        dice[5] = dice[6];
                        dice[6] = dice[2];
                        dice[2] = top;
                        flag = true;
                    }
                    break;
                case 4: // 남
                    if(x+1<N){
                        x += 1;
                        dice[1] = dice[2];
                        dice[2] = dice[6];
                        dice[6] = dice[5];
                        dice[5] = top;
                        flag = true;
                    }
                    break;
            }
            // 출력할 때, 지도범위 밖으로 넘어가는 명령에 대해서는 출력안
            if(flag){
                // 지도와 주사위가 닿는 부분 체크
                if(map[x][y] == 0){
                    map[x][y] = dice[6];
                } else {
                    dice[6] = map[x][y];
                    map[x][y] = 0;
                }
                System.out.println(dice[1]);
            }
        }
    }
}
