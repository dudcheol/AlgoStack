import java.util.*;

// First Solution

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        int pre_idx = -1;
        for(int j=0; j<skill_trees.length; j++){
            ArrayList<Integer> sort_idx = new ArrayList<>();
            ArrayList<Integer> skill_idx = new ArrayList<>();
            String[] tree = skill_trees[j].split("");
            for(int k=0; k<tree.length; k++){
                if(skill.contains(tree[k])){
                    sort_idx.add(skill.indexOf(tree[k]));
                    skill_idx.add(skill.indexOf(tree[k]));
                }
            }
            sort_idx.sort(Comparator.naturalOrder());

            if(!sort_idx.equals(skill_idx) || sort_idx.indexOf(0)!=0){
                answer--;
            }
        }
        return answer;
    }
}

// Second Solution (test 3,5,13 fail)

class Solution2 {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        for(int j=0; j<skill_trees.length; j++){
            int pre_idx = -1;
            String[] tree = skill_trees[j].split("");
            for(int k=0; k<tree.length; k++){
                if(skill.contains(tree[k])){
                    if(pre_idx==-1 && skill.indexOf(tree[k])!=0){
                        answer--;
                        break;
                    }
                    else if(pre_idx!=-1 && pre_idx>skill.indexOf(tree[k])){
                        answer--;
                        break;
                    }
                    else{
                        pre_idx = skill.indexOf(tree[k]);
                    }
                }
            }
        }
        return answer;
    }
}

// Correct Solution

class Solution3 {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;
        for(int j=0; j<skill_trees.length; j++){
            int cnt = 0;
            String[] tree = skill_trees[j].split("");
            for(int k=0; k<tree.length; k++){
                if(skill.contains(tree[k])){
                    if(cnt<skill.indexOf(tree[k])){
                        answer--;
                        break;
                    }else if(cnt == skill.indexOf(tree[k])){
                        cnt++;
                    }
                }
            }
        }
        return answer;
    }
}