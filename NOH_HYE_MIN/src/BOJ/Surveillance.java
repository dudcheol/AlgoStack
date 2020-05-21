package BOJ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Surveillance {
    static int N, M, min = Integer.MAX_VALUE;
    static int[][] room;
    static int camCnt;
    static ArrayList<Camera> camlist =  new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        room = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int v = sc.nextInt();
                if(v!=0 && v!=6){
                    camlist.add(new Camera(i, j, v));
                }
                room[i][j] = v;
            }
        }
        camCnt = camlist.size();

        allCamera(0, room);
        System.out.println(min);
    }
    static void allCamera(int idx, int[][] room){
        int[][] tmpRoom = new int[N][M];
        if(idx==camCnt){
            int zeroCnt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(room[i][j]==0){
                        zeroCnt++;
                    }
                }
            }
            if(zeroCnt<min){
                min = zeroCnt;
            }
        } else {
            Camera cam = camlist.get(idx);
            // 카메라 90도씩 돌리며 체크
            switch (cam.num) {  // 카메라 타입에 따라 회전 횟수 다르게 가능
                case 1:
                    for (int dir = 0; dir < 4; dir++) {
                        for (int i = 0; i < N; i++) {
                            tmpRoom[i] = Arrays.copyOf(room[i], M);
                        }
                        check(tmpRoom, cam.posX, cam.posY, dir);
                        allCamera(idx + 1, tmpRoom);
                    }
                    break;
                case 2:
                    for (int dir = 0; dir < 2; dir++) {
                        for (int i = 0; i < N; i++) {
                            tmpRoom[i] = Arrays.copyOf(room[i], M);
                        }
                        check(tmpRoom, cam.posX, cam.posY, dir);
                        check(tmpRoom, cam.posX, cam.posY, (dir + 2) % 4);
                        allCamera(idx + 1, tmpRoom);
                    }
                    break;
                case 3:
                    for (int dir = 0; dir < 4; dir++) {
                        for (int i = 0; i < N; i++) {
                            tmpRoom[i] = Arrays.copyOf(room[i], M);
                        }
                        check(tmpRoom, cam.posX, cam.posY, dir);
                        check(tmpRoom, cam.posX, cam.posY, (dir + 1) % 4);
                        allCamera(idx + 1, tmpRoom);
                    }
                    break;
                case 4:
                    for (int dir = 0; dir < 4; dir++) {
                        for (int i = 0; i < N; i++) {
                            tmpRoom[i] = Arrays.copyOf(room[i], M);
                        }
                        check(tmpRoom, cam.posX, cam.posY, dir);
                        check(tmpRoom, cam.posX, cam.posY, (dir + 1) % 4);
                        check(tmpRoom, cam.posX, cam.posY, (dir + 2) % 4);
                        allCamera(idx + 1, tmpRoom);
                    }
                    break;
                case 5:
                    for (int i = 0; i < N; i++) {
                        tmpRoom[i] = Arrays.copyOf(room[i], M);
                    }
                    check(tmpRoom, cam.posX, cam.posY, 0);
                    check(tmpRoom, cam.posX, cam.posY, 1);
                    check(tmpRoom, cam.posX, cam.posY, 2);
                    check(tmpRoom, cam.posX, cam.posY, 3);
                    allCamera(idx + 1, tmpRoom);
                    break;
            }
        }
    }
    static void check(int[][] tmpRoom, int i, int j, int dir){
        switch (dir){
            case 0:
                for (int k = j; k >= 0; k--) {
                    if (room[i][k] == 6) {
                        break;
                    }
                    tmpRoom[i][k] = 7;
                }
                break;
            case 1:
                for (int k = i; k >= 0; k--) {
                    if (room[k][j] == 6) {
                        break;
                    }
                    tmpRoom[k][j] = 7;
                }
                break;
            case 2:
                for (int k = j; k < M; k++) {
                    if (room[i][k] == 6) {
                        break;
                    }
                    tmpRoom[i][k] = 7;
                }
                break;
            case 3:
                for (int k = i; k < N; k++) {
                    if (room[k][j] == 6) {
                        break;
                    }
                    tmpRoom[k][j] = 7;
                }
                break;
        }
    }
}

class Camera{
    int posX;
    int posY;
    int num;

    Camera(int posX, int posY, int num){
        this.posX = posX;
        this.posY = posY;
        this.num = num;
    }
}