class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int temp = 0;
        int isPrime=0;
        for(int i = 0 ; i < nums.length - 2 ; i++) {
            for(int j = (i+1) ; j < nums.length - 1 ; j++ ) {
                for(int k = (j+1) ; k < nums.length ; k++ ) {
                    temp = nums[i] + nums[j] + nums[k];
                    for(int n = 2 ; n < (temp/2) ; n++) {
                        if(temp % n == 0) {
                            isPrime = 1;
                            break;
                        }
                    }
                    if(isPrime == 0) {
                        answer ++;
                    }
                    isPrime = 0;
                }
            }
        }
        return answer;
    }
}