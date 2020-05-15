package programmers.summerWinterCoding2018;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 13분
 */
public class SkillTree {

    static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Queue<Character> userQ = new LinkedList<Character>();

        for (String s : skill_trees) {
            for (int i = 0; i < s.length(); i++) {
                if (skill.contains(s.charAt(i)+"")){
                    userQ.offer(s.charAt(i));
                }
            }

            // 큐와 비교함
            boolean isCountable = true;
            int pos = 0;
            while (!userQ.isEmpty()){
                if (pos < skill.length() && userQ.poll()==skill.charAt(pos)){
                    pos++;
                } else {
                    isCountable = false;
                    break;
                }
            }

            if (isCountable) answer++;
            userQ.clear();
        }

        return answer;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};

        System.out.println(solution(skill, skill_trees));
    }
}
