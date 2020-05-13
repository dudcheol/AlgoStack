import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Map<String, Integer> password = new HashMap<String, Integer>();
        password.put(words[0], 0);
        int turn=0;
        char preChar = words[0].charAt(words[0].length()-1);
        for(int i=1; i<words.length; i++){
            char nowChar = words[i].charAt(0);
            if(password.containsKey(words[i]) || nowChar!=preChar){
                if((i+1)%n==0){
                    answer[0] = n;
                    answer[1] = (i+1)/n;
                }
                else{
                    answer[0] = (i+1)%n;
                    answer[1] = (i+1)/n+1;
                }
                return answer;
            }
            else{
                password.put(words[i], 0);
                preChar = words[i].charAt(words[i].length()-1);
            }
        }
        return answer;
    }
}