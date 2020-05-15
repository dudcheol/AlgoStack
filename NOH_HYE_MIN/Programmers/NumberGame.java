import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int aidx = 0;
        int bidx = 0;
        int len	 = A.length;
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        while(true) {
            if (bidx == len) break;
            if (B[bidx] > A[aidx]) {
                answer++;
                aidx++;
                bidx++;
            } else {
                bidx++;
            }
        }
        return answer;
    }
}