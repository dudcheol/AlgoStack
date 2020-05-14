// U, R 경우에 pos 위치 update 되는 타이밍때문에 수행시간이 많이 달라짐 ㅇㅅㅇ....

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        boolean[][] boardX = new boolean[11][11];
        boolean[][] boardY = new boolean[11][11];
        int[] pos = {5, 5};
        String[] dirsArray = dirs.split("");
        for(int i=0; i<dirsArray.length; i++){
            switch(dirsArray[i]){
                case "U":
                    if(pos[1]<10){
                        if(!boardX[pos[0]][pos[1]]) answer++;
                        boardX[pos[0]][pos[1]] = true;
                        pos[1]=pos[1]+1;
                    }
                    break;
                case "D":
                    if(pos[1]>0){
                        pos[1]=pos[1]-1;
                        if(!boardX[pos[0]][pos[1]]) answer++;
                        boardX[pos[0]][pos[1]] = true;
                    }
                    break;
                case "R":
                    if(pos[0]<10){
                        if(!boardY[pos[0]][pos[1]]) answer++;
                        boardY[pos[0]][pos[1]] = true;
                        pos[0]=pos[0]+1;
                    }
                    break;
                case "L":
                    if(pos[0]>0){
                        pos[0]=pos[0]-1;
                        if(!boardY[pos[0]][pos[1]]) answer++;
                        boardY[pos[0]][pos[1]] = true;
                    }
                    break;
            }
        }
        return answer;
    }
}