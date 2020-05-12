package programmers.summerWinterCoding2018;

import java.util.HashSet;

/**
 * 26분
 */
public class EnglishWordChain {

    static int[] solution(int n, String[] words) {
        int[] answer = {};

        HashSet<String> set = new HashSet<>();

        boolean finishable = false;
        int order = 0;

        //1번부터 번호 순서대로 한 사람씩 차례대로 단어를 말합니다.
        String preWord = "";
        for (String w : words) {
            order++;

            //한 글자인 단어는 인정되지 않습니다.
            if (w.length() == 1) {
                finishable = true;
                break;
            }

            //이전에 등장했던 단어는 사용할 수 없습니다.
            if (set.contains(w)) {
                finishable = true;
                break;
            } else {
                //끝말잇기 규칙을 지키고 있는가?
                if (preWord.isEmpty()) { // 첫번째 단어이면
                    preWord = w;
                    set.add(w);
                } else { // 첫번째 단어가 아니면 preWord와 현재 word의 끝과 앞을 비교
                    if (preWord.charAt(preWord.length() - 1) == w.charAt(0)) {
                        preWord = w;
                        set.add(w);
                    } else {
                        finishable = true;
                        break;
                    }
                }
            }
        }

        if (!finishable) return new int[]{0, 0};

        // 가장 먼저 탈락하는 사람 번호
        int num = order % n == 0 ? n : order % n;
        // 그 사람이 자신의 몇 번째 차례에 탈락하나?
        int when = order % n != 0 ? order / n + 1 : order / n;

        return new int[]{num, when};
    }

    public static void main(String[] args) {
//        int n = 3;
//        int n = 5;
        int n = 2;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};

        int[] answer = solution(n, words);

        for (int ans : answer) {
            System.out.println(ans);
        }
    }
}
