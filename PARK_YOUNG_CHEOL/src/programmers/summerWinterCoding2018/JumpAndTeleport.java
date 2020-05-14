package programmers.summerWinterCoding2018;

/**
 * 알고보니 정말 쉬운문제..
 * 1부터 곱해가며 풀어나가려고 하니 정확성은 맞았어도 효율성에서 X
 * n부터 나눠가며 풀어나가면 정말 쉬운 문제...
 */
public class JumpAndTeleport {

    static int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else{
                n -= 1;
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 5000;

        System.out.println(solution(n));
    }
}
