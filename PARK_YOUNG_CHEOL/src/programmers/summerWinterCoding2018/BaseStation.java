package programmers.summerWinterCoding2018;

/**
 * 45분
 *
 * 재정의/추상화/계획세우기 모두 괜찮게 했음
 * 아쉬운점은, 계획세우기에서 세운 계획을 코드로 옮길 때
 * 세운 계획을 보면서 코딩하지않고 또 머리로 기억한 계획대로 코딩함 ㅠㅠ
 * 어이없는 실수를 할 수 있으므로 항상 계획 세운 것은 꼭 확인하면서 코딩할 것 (특히, 조건여부)
 */
public class BaseStation {
    static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int preStation = 0;
        int stationW = w * 2 + 1; // 하나의 기지국이 영향을 끼칠 수 있는 너비

        for (int i = 0; i < stations.length; i++) {
            int left = stations[i] - w; // stations[i]의 전파 범위의 왼쪽에 닿는 아파트
            int leftRange = left - preStation - 1; // stations[i]의 범위가 안닿는 곳과 이전 기지국의 오른쪽 전파 범위와 안닿는 곳의 거리

            if (leftRange > 0) {
                int mok = leftRange / stationW;
                int rest = leftRange % stationW;
                if (rest == 0) {
                    answer += mok;
                } else {
                    answer += mok + 1;
                }
            }
            preStation = stations[i] + w;
        }

        // stations의 마지막 기지국의 오른쪽에 세워야할 기지국 구하기
        int rightRange = n - (stations[stations.length - 1] + w);
        if (rightRange > 0) {
            if (rightRange % stationW == 0) {
                answer += (rightRange / stationW);
            } else {
                answer += (rightRange / stationW) + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
//        int n = 11;
//        int[] stations = {4, 11};
//        int w = 1;

        int n = 16;
        int[] stations = {9};
        int w = 2;


        System.out.println(solution(n, stations, w));
    }
}
