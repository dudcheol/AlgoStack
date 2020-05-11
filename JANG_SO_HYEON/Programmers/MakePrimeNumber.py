# 나의 코드
import itertools

def solution(nums):
    answer = 0
    # 조합을 이용
    result = list(itertools.combinations(nums, 3))
    
    for i in result:
        hap = sum(i)
        for j in range(2, int(hap**(1/2))+1):
            if hap % j == 0:
                break
        else:
            answer += 1

    return answer
