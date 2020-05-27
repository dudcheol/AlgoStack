package BOJ;

import java.util.ArrayList;
import java.util.Scanner;

public class FineDust {
    static int r, c, t;
    static int[][] map;
    static ArrayList<Integer> cleaner = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        t = sc.nextInt();

        map = new int[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j]==-1){
                    cleaner.add(i);
                }
            }
        }

        for(int k=0; k<t; k++){
            diffusion(getDustList(map));
            clean();
        }

        int answer = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(map[i][j]);
                if(map[i][j]!=0 && map[i][j]!=-1){
                    answer += map[i][j];
                }
            }
            System.out.println();
        }
        System.out.println(answer);
    }
    static void diffusion(ArrayList<Dust> dusts){
        int[][] newMap = new int[r][c];
        for(Dust dust:dusts){
            // 먼지 위치의 먼지
            int nr = dust.r;
            int nc = dust.c;
            newMap[nr][nc] += dust.n;
            int newDust = dust.n/5;
            if(nr-1>0 && map[nr-1][nc]!=-1){ // up
                newMap[nr-1][nc] += newDust;
                newMap[nr][nc] -= newDust;
            }
            if(nc-1>0 && map[nr][nc-1]!=-1){ // left
                newMap[nr][nc-1] += newDust;
                newMap[nr][nc] -= newDust;
            }
            if(nr+1<r && map[nr+1][nc]!=-1){  // down
                newMap[nr+1][nc] += newDust;
                newMap[nr][nc] -= newDust;

            }
            if(nc+1<c && map[nr][nc+1]!=-1){
                newMap[nr][nc+1] += newDust;
                newMap[nr][nc] -= newDust;
            }
        }
        newMap[cleaner.get(0)][0] = -1;
        newMap[cleaner.get(1)][0] = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = newMap[i][j];
            }
        }
    }
    static void clean(){
        // cleaner는 항상 1열이므로 c idx정보만 얻을 수 있
        int upIdx = cleaner.get(0);
        int downIdx = cleaner.get(1);

        for(int i=upIdx-1; i>=0; i--) {
            if(i!=0) {
                map[i][0] = map[i-1][0];
            }
        }

        for(int i=0; i<c-1; i++) {
            map[0][i] = map[0][i+1];
        }

        for(int i=0; i<upIdx; i++) {
            map[i][c-1] = map[i+1][c-1];
        }

        for(int i=c-1; i>=1; i--) {
            map[upIdx][i] = map[upIdx][i-1];

            if(i==1) {
                map[upIdx][i] = 0;
            }
        }
        for(int i=downIdx+1; i<r; i++) {
            if(i!=r-1) {
                map[i][0] = map[i+1][0];
            }
        }

        for(int i=0; i<c-1; i++) {
            map[r-1][i] = map[r-1][i+1];
        }

        for(int i=r-1; i>downIdx; i--) {
            map[i][c-1] = map[i-1][c-1];
        }

        for(int i=c-1; i>=1; i--) {
            map[downIdx][i] = map[downIdx][i-1];

            if(i==1) {
                map[downIdx][i] = 0;
            }
        }
    }
    static ArrayList<Dust> getDustList(int[][] map){
        ArrayList <Dust> dusts = new ArrayList<>();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j]!=-1 && map[i][j]!=0){
                    dusts.add(new Dust(i, j, map[i][j]));
                }
            }
        }
        return dusts;
    }
}
class Dust{
    int r;
    int c;
    int n;

    Dust(int r, int c, int n){
        this.r = r;
        this.c = c;
        this.n = n;
    }
}
