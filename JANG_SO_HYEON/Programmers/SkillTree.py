# 나의 코드
def solution(skill, skill_trees):
    # 선행 스킬 : 어떤 스킬을 배우기 전에 먼저 배워야 하는 스킬
    answer = 0
    for tree in skill_trees:
        temp = 0
        for s in tree:
            if s in skill:
                if skill.index(s) == temp:
                    temp += 1
                else:
                    break
        # 중간에 break 등으로 끊기지 않을 경우 else문이 실행됨
        else:
            answer += 1

    return answer
