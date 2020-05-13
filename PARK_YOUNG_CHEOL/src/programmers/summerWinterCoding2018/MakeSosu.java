package programmers.summerWinterCoding2018;

/**
 * 30분
 */
public class MakeSosu {
    static int[] numAry;
    static boolean[] visited;

    static int solution(int[] nums) {
        int answer = -1;

        numAry = nums;
        visited = new boolean[nums.length];
        answer = recursion(0, 0, 0);

        return answer;
    }

    static int recursion(int k, int pos, int added) { // 더한 수
        // 기저
        if (k == 3) {
            if (isSosu(added)) {
                return 1;
            } else return 0;
        }

        int ret = 0;
        for (int i = pos; i < numAry.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            ret += recursion(k + 1, i + 1, added + numAry[i]);
            visited[i] = false;
        }

        return ret;
    }

    static boolean isSosu(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
//        int[] nums = {1, 2, 7, 6, 4};

        System.out.println(solution(nums));
    }
}
