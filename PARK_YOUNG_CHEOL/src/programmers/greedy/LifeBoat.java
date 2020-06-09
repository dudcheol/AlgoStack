package programmers.greedy;

import java.util.ArrayList;
import java.util.Collections;

public class LifeBoat {
    static int solution(int[] people, int limit) {
        int answer = 0;

        ArrayList<Integer> list = new ArrayList<>();
        for (int p : people) list.add(p);

        Collections.sort(list);

        while (list.size() > 1) {
            int weight = list.get(list.size() - 1);
            for (int i = 0; i < list.size() - 1; i++) {
                weight += list.get(i);
                if (weight > limit) {
                    list.subList(0, i).clear();
                    list.remove(list.size() - 1);
                    answer++;
                    break;
                } else if (weight == limit) {
                    list.subList(0, i + 1).clear();
                    list.remove(list.size() - 1);
                    answer++;
                    break;
                }
            }
        }

        return list.size() == 0 ? answer : answer + 1;
    }

    public static void main(String[] args) {
//        int[] people = {70, 50, 80, 50};
//        int limit = 100;
//        int[] people = {70, 50, 80};
//        int limit = 100;
        int[] people = {10, 20, 30, 70, 80, 90};
        int limit = 100;

        System.out.println(solution(people, limit));
    }
}
