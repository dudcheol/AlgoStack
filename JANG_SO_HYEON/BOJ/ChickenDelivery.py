# 참고하여 수정한 코드
import math
from itertools import combinations

n, m = map(int, input().split())

# 치킨집, 집 위치
chicken, house = [], []

# 치킨집, 집 위치 저장
for y in range(n):
    temp = list(map(int, input().split()))
    for x in range(len(temp)):
        if temp[x] == 1:
            house.append((y,x))
        elif temp[x] == 2:
            chicken.append((y,x))

# 치킨집 중 m개의 치킨집을 선택하는 경우의 수
candidate = list(combinations(chicken, m))

# 각 집 위치마다 모든 치킨집과의 거리를 구하고, 최솟값만 result에 더하기
def MIN_distance(candidate, house):
    result = 0
    for ny, nx in house:
        temp_min = math.inf
        for y, x in candidate:
            if abs(ny-y) + abs(nx-x) < temp_min:
                temp_min = abs(ny-y)+ abs(nx-x)
        result += temp_min
    return result

# 거리의 최솟값
min_result = math.inf
for i in candidate:
    result = MIN_distance(list(i),house)
    if min_result > result:
        min_result = result
print(min_result)
