    package swea.ct;

    import java.util.ArrayList;

    /**
     * 과제 1
     * 52장의 카드에서 만들 수 있는 페어가 정확히 하나만 있는 5장 조합을 모두 출력하는 프로그램을 작성하라.
     * 출력이 너무 많으면 가드 수를 줄일 수 있다.
     * 페어 : 숫자가 같은 2장의 카드
     */
    public class _7_1 {
        static boolean[] visitedShape = new boolean[4];
        static boolean[] visitedNum = new boolean[14];
        static ArrayList<Integer> seleted = new ArrayList<>();
        static int[] selectedNum = new int[3];
        static int[] selectedShape = new int[3];
        static int answer = 0; // 2779920

        static void selectOtherShape(int k, int shape1, int shape2, int num) {
            if (k == 3) {
                System.out.print(convertShape(shape1) + num + " " + convertShape(shape2) + num + " ");
                for (int i = 0; i < 3; i++) {
                    System.out.print(convertShape(selectedShape[i]) + selectedNum[i] + " ");
                }
                System.out.println();
                answer++;
                return;
            }

            for (int i = 0; i < 3; i++) {
                selectedShape[k] = i;
                selectOtherShape(k + 1, shape1, shape2, num);
            }
        }

        static void selectOtherNum(int k, int shape1, int shape2, int num) {
            // 선택된 모양과 숫자를 바탕으로 가능한 경우의수 구하기
            if (k == 3) {
                selectOtherShape(0, shape1, shape2, num);
                return;
            }

            for (int j = 1; j <= 13; j++) {
                if (visitedNum[j] || j == num) continue;
                visitedNum[j] = true;
                selectedNum[k] = j;
                selectOtherNum(k + 1, shape1, shape2, num);
                visitedNum[j] = false;
            }
        }

        static void selectShape(int k, int idx) {
            // Pair를 만들기 위해선 다른 모양 2개를 선택해야 함
            if (k == 2) {
                for (int num = 1; num <= 13; num++) {
                    selectOtherNum(0, seleted.get(0), seleted.get(1), num);
                }
                return;
            }

            for (int i = idx; i < 4; i++) {
                if (visitedShape[i]) continue;
                visitedShape[i] = true;
                seleted.add(i);
                selectShape(k + 1, i);
                seleted.remove(new Integer(i));
                visitedShape[i] = false;
            }
        }

        static String convertShape(int shape) {
            switch (shape) {
                case 0:
                    return "♥";
                case 1:
                    return "♠";
                case 2:
                    return "♣";
                case 3:
                    return "♦";
            }
            return "?";
        }

        public static void main(String[] args) {
            selectShape(0, 0);
            System.out.println(answer);
        }
    }
