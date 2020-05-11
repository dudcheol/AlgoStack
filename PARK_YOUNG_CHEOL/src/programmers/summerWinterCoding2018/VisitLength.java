package programmers.summerWinterCoding2018;

import java.util.Arrays;

/**
 * 1시간 26분
 *
 * '재정의/추상화'는 잘했으나 '계획세우기'를 제대로 못함
 * 대충 이런식으로 풀면 되겠거니~ 하는 식으로 생각하고 바로 코드로 옮기는 버릇때문에 시간이 오래걸림
 * 문제에서 테스트케이스로 알려주는 경우 말고도 다른 경우도 있다는 것을 염두에 둘 것
 */
public class VisitLength {
    static String[][] map = new String[11][11];

    static int solution(String dirs) {
        int answer = 0;

        for (String[] m : map)
            Arrays.fill(m, "");

        return dfs(5, 5, dirs, 0);
    }

    static int dfs(int x, int y, String dirs, int k) {
        if (dirs.length() == 0) {
            return k;
        }

        char target = dirs.charAt(0);
        int isNewWay = map[x][y].contains(target + "") ? 0 : 1;

        map[x][y] += target;
        System.out.println("map[" + x + "][" + y + "] = " + map[x][y] + " / k -> " + k);

        int moveX = x;
        int moveY = y;
        switch (target) {
            case 'U':
                moveX = x - 1;
                moveY = y;
                if (!isInMap(moveX, moveY)) map[moveX][moveY] += 'D'; /* 바로 전에 갔던 곳을 다시 갈 수 있으므로 기록해둬야함 */
                break;
            case 'D':
                moveX = x + 1;
                moveY = y;
                if (!isInMap(moveX, moveY)) map[moveX][moveY] += 'U';
                break;
            case 'R':
                moveX = x;
                moveY = y + 1;
                if (!isInMap(moveX, moveY)) map[moveX][moveY] += 'L';
                break;
            case 'L':
                moveX = x;
                moveY = y - 1;
                if (!isInMap(moveX, moveY)) map[moveX][moveY] += 'R';
                break;
        }

        if (isInMap(moveX, moveY)) {
            // 범위를 벗어나면 이동시키면 안됨
            return dfs(x, y, dirs.substring(1), k);
        } else {
            // 지나왔던 길인지 아닌지 확인 후 k에 값 더할지 말지 결정
            return dfs(moveX, moveY, dirs.substring(1), k + isNewWay);
        }
    }

    static boolean isInMap(int x, int y) {
        return x < 0 || y < 0 || x > 10 || y > 10;
    }

    public static void main(String[] args) {
        String dirs = "LULLLLLLU";
        String dirs2 = "ULURRDLLU";
        String dirs3 = "LR";

        System.out.println(solution(dirs));
    }
}
