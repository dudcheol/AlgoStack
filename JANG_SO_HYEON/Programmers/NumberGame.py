# 나의 코드(오답) - 대부분 시간초과
import itertools

def solution(A, B):
    answer = []
    temp = list(itertools.permutations(B))
    
    for i in temp:
        count = 0
        for j in range(len(A)):
            if A[j] < i[j]:
                count += 1
        
        answer.append(count)

    return max(answer)


# 수정한 코드(정답)
def solution(A, B):
    answer = 0
    
    if min(A) > max(B):
        return 0
    
    A = sorted(A)
    B = sorted(B)

    i = j = 0
    
    while j < len(A):
        if A[i] < B[j]:
            answer += 1
            i += 1
            j += 1
        else:
            j += 1  # B의 다음 팀원과 비교

    return answer


# deque도 고민할 것


