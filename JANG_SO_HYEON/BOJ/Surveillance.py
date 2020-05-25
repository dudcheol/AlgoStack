# 다른 코드 참고하여 코드 작성
from copy import deepcopy
import math
n, m = map(int, input().split())
maps = []
cctvs = []
for y in range(n):
    temp = list(map(int, input().split()))
    for x in range(m):
        if 1 <= temp[x] <= 5:
            cctvs.append((y,x,temp[x]))
    maps.append(deepcopy(temp))

MIN = math.inf

def check_map(maps, dirs, y, x):
    maps = deepcopy(maps)
    for looking in dirs:
        if looking == 0:
            for dx in range(x, len(maps[0])):
                if maps[y][dx] == 6:
                    break
                elif maps[y][dx] != 0:
                    continue
                else:
                    maps[y][dx] = "#"
    
        elif looking == 1:
            for dy in range(y, -1, -1):
                if maps[dy][x] == 6:
                    break
                elif maps[dy][x] != 0:
                    continue
                else:
                    maps[dy][x] = "#"

        elif looking == 2:
            for dx in range(x, -1, -1):
                if maps[y][dx] == 6:
                    break
                elif maps[y][dx] != 0:
                    continue
                else:
                    maps[y][dx] = '#'

        elif looking == 3:
            for dy in range(y, len(maps)):
                if maps[dy][x] == 6:
                    break
                elif maps[dy][x] != 0:
                    continue
                else:
                    maps[dy][x] = '#'

        return maps

def detecting(maps, cctvs, idx):
    global MIN
    if idx == len(cctvs):
        value = 0
        for y in range(len(maps)):
            value += maps[y].count(0)
        MIN = min(MIN, value)
        return
    
    cctv = cctvs[idx]
    y, x, kind = cctv
    if kind == 1:
        for i in range(4):
            next_maps = check_map(maps, [i], y, x)
            detecting(next_maps, cctvs, idx+1)

    # 사방향 탐색값 total_amount에 넣는다
    # 다음 탐색
    if kind == 2:
        for i in [(0,2),(1,3)]:
            next_maps = check_map(maps, i, y, x)
            detecting(next_maps, cctvs, idx+1)

    # 상하, 좌우 각각의 값을 total_amount에 넣는다
    # 다음 탐색
    if kind == 3:
        for i in [(0,1),(1,2),(2,3),(3,0)]:
            next_maps = check_map(maps, i, y, x)
            detecting(next_maps, cctvs, idx+1)

    # 90도 방향으로 4번. 탐색값
    if kind == 4:
        for i in [(0,1,2),(1,2,3),(2,3,0),(3,0,1)]:
            next_maps = check_map(maps, i, y, x)
            detecting(next_maps, cctvs, idx+1)

    # 사방향 중 하나 빼고 탐색.
    if kind == 5:
        # 사방향 전체 탐색
        next_maps = check_map(maps, (0,1,2,3), y, x)
        detecting(next_maps, cctvs, idx+1)

detecting(maps, cctvs, 0)
print(MIN)
