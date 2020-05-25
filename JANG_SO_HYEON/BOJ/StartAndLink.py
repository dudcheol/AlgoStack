# 나의 코드(수정)
import itertools

N = int(input())

# 능력치
Ability = [list(map(int, input().split())) for _ in range(N)]

# 차이가 최소인 값
answer = 987654321

members = list(range(N))

# 그룹 짓기
A = list(itertools.combinations(members, N // 2))

for i in A:
    # A 그룹을 제외한 나머지 그룹
    B = [x for x in members if x not in i]
    
    A_sum, B_sum = 0, 0
    
    # 그룹 안에서 두 명씩 시너지를 더해주기 위해
    for x, y in list(itertools.combinations(i, 2)):
        A_sum += (Ability[x][y] + Ability[y][x])
    
    for x, y in list(itertools.combinations(B, 2)):
        B_sum += (Ability[x][y] + Ability[y][x])
    
    # 차이가 더 작은 것을 선택
    answer = min(answer, abs(A_sum - B_sum))

print(answer)
