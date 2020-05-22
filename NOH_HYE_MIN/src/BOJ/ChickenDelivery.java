package BOJ;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ChickenDelivery {
    static int n, m;
    static int minDist = Integer.MAX_VALUE;
    static ArrayList<Node> homeList = new ArrayList<>();
    static ArrayList<Node> chickenList = new ArrayList<>();
    static Stack<Node> selected = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int v = sc.nextInt();
                if(v==1){  // home
                    homeList.add(new Node(i, j));
                } else if(v==2){  // chicken
                    chickenList.add(new Node(i, j));
                }
            }
        }
        selectChicken(0, 0);
        System.out.println(minDist);
    }
    static void selectChicken(int idx, int cnt){
        if (cnt == m){
            int sumDist = 0;
            for(Node home:homeList){
                int dist = Integer.MAX_VALUE;
                for(Node chicken:selected){
                    int tmp = Math.abs(chicken.x-home.x)+Math.abs(chicken.y-home.y);
                    dist = Math.min(dist,tmp);
                }
                sumDist += dist;
            }
            minDist = Math.min(minDist,sumDist);
        } else {
            for (int i = idx; i < chickenList.size(); i++) {
                selected.push(chickenList.get(i));
                selectChicken(i + 1, cnt + 1);
                selected.pop();
            }
        }
    }
}
class Node{
    int x;
    int y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
