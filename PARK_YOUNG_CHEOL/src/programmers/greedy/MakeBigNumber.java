package programmers.greedy;

public class MakeBigNumber {
    static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int size = number.length();

        int start = 0;
        int cnt = size - k;
        int end = size - cnt; /* 여기를 생각 못함 */

        while (true) {
            if (answer.length() == size - k) break;
            int idx = start;
            int bigger = -1;
            for (int i = start; i <= end; i++) {
                // 가장 큰 수 찾기
                if (i >= number.length()) break;
                /* char형을 int로 변환할 때 좀 더 빠른 방법은 2번이다*/
                /*1*///int cur = Character.getNumericValue(number.charAt(i));
                /*2*/int cur = number.charAt(i) - '0';
                if (bigger < cur) {
                    bigger = cur;
                    idx = i;
                }
            }
            answer.append(bigger);
            start = idx + 1;
            end = size - --cnt;
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String number = "1924";
        int k = 2;
        String number2 = "1231234";
        int k2 = 3;
        String number3 = "4177252841";
        int k3 = 4;

        System.out.println(solution(number, k));
        System.out.println(solution(number2, k2));
        System.out.println(solution(number3, k3));
    }
}
