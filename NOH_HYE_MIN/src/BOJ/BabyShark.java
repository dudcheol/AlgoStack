package BOJ;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class BabyShark {
    static int n;
    static int[][] map;
    static Shark baby;
    static int time = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j]==9){
                    baby = new Shark(i, j);
                }
            }
        }

        bfs();
        System.out.println(time);
    }
    public static void bfs(){
        int[][] check = new int[n][n];
        Queue<Point> q = new LinkedList<Point>();

    }
    public static boolean isIn(int x, int y){
        return(0<=x && x<n && 0<=y && y<n);
    }

}
class Shark{
    int x;
    int y;
    int size;
    Shark(int x, int y){
        this.x = x;
        this.y = y;
        this.size = 2;
    }
}
