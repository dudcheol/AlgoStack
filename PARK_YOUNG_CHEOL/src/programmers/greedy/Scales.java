package programmers.greedy;

import java.util.Arrays;

public class Scales {
    static int solution(int[] weight) {
        int answer = 0;

        Arrays.sort(weight);

        // 최소값이 1보다 크면 측정할 수 없는 양의 정수 무게 중 최솟값은 1이되므로 1 리턴
        if (weight[0] > 1){
            return 1;
        }

        int tmp = 1;
        for (int i = 0; i < weight.length; i++) {
            if (tmp < weight[i]) break;
            tmp += weight[i];
        }

        answer = tmp;
        return answer;
    }

    public static void main(String[] args) {
        int[] weight = {3, 1, 6, 2, 7, 30, 1};
        System.out.println(solution(weight));
    }
}
