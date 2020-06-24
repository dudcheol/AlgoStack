package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실버3 - 8분
 */
public class ATM {
    static int N;
    static int[] persons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        persons = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            persons[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(persons);

        int answer = 0;
        int sum = 0;
        for (int person : persons){
            sum += person;
            answer += sum;
        }

        System.out.println(answer);
    }
}
