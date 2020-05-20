package BOJ;
import java.util.Scanner;

public class StartAndLink {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] table;
    static boolean[] visited;
    static void divideTeam(int star,int idx){
        // check min value
        if(star == N/2) {
            int starScore = 0;
            int linkScore = 0;
            for(int m=0; m<N; m++){
                for(int n=m+1; n<N; n++){
                    if(visited[m] && visited[n]){
                        starScore += table[m][n];
                        starScore += table[n][m];
                    } else if(!visited[m] && !visited[n]){
                        linkScore += table[m][n];
                        linkScore += table[n][m];
                    }
                }
            }
            min = Math.min(min,Math.abs(starScore-linkScore));
        }
        for(int k=0; k<N; k++){
            if(visited[k]==false && k>idx){
                visited[k] = true;
                divideTeam(star+1, k);
                visited[k] = false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[N];
        // setting table
        table = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                table[i][j] = sc.nextInt();
            }
        }

        divideTeam(0,-1);

        System.out.println(min);
    }
}
