package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NewEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] people = new int[n][2];

            for (int k = 0; k < n; k++) {
                st = new StringTokenizer(br.readLine());
                people[k][0] = Integer.parseInt(st.nextToken());
                people[k][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(people, (o1, o2) -> {
                return Integer.compare(o1[0], o2[0]);
            });

            int answer = 0;
            int minGrade = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (people[i][1] < minGrade){
                    answer++;
                    minGrade = people[i][1];
                }
            }

            System.out.println(answer);
        }
    }
}
