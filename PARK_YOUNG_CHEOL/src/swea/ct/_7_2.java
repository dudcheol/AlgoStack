package swea.ct;

/**
 * 과제2
 * x + y + z = 100 의 자연수 해를 모두 출력하는 프로그램을 작성하라
 */
public class _7_2 {
    static int answer;

    static void printAnswer(int k, int x, int y) {
        if (k == 2) {
            int z = 100 - x - y;
            if (!(z <= 0)) {
                System.out.println(x + " " + y + " " + (100 - x - y));
                answer++;
            }
        } else if (k == 1) {
            for (int i = 1; i <= 100; i++) {
                printAnswer(k + 1, x, i);
            }
        } else if (k == 0) {
            for (int i = 1; i <= 100; i++) {
                printAnswer(k + 1, i, y);
            }
        }
    }

    public static void main(String[] args) {
        printAnswer(0, 0, 0);
        System.out.println(answer);
    }
}
