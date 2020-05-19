# bfs 코드 참고하여 수정한 코드
from collections import deque

def bfs(start, maps, dist, K):
    # deque()는 스택과 큐를 지원
    queue = deque()
    queue.append(start)
    
    dist[start] = 0
    
    while queue:
        y = queue.pop()
        for x in range(1, len(maps)):
            if maps[y][x] != 0:
                # 최단 시간
                if dist[x] > dist[y] + maps[y][x]:
                    dist[x] = dist[y] + maps[y][x]
                    queue.append(x)

    # 1번 마을에 있는 음식점이 K 이하의 시간에 배달이 가능한 마을의 개수를 리턴
    return len([i for i in dist if i <= K])

def solution(N, road, K):
    MAX = 543210
    distance = [MAX for _ in range(N+1)]
    
    maps = [[0 for _ in range(N+1)] for _ in range(N+1)]
    
    for s, e, weight in road:
        if maps[s][e] == 0 and maps[e][s] == 0:
            maps[s][e], maps[e][s] = weight, weight
        else:
            # 중복값이 존재할 경우, 더 작은 weight 이용
            if weight < maps[s][e]:
                maps[s][e], maps[e][s] = weight, weight

    # 시작지점 1로부터 각각의 마을까지의 시간
    return bfs(1, maps, distance, K)
