from queue import PriorityQueue

def solution(N, road, K):
    MAX_TIME = 500001
    weight = [MAX_TIME for i in range(N+1)]
    q = PriorityQueue()
    q.put([0,1])
    weight[1] = 0

    road.sort()

    while not q.empty():
        w, pos = q.get()
        if w > weight[pos]: continue
        for i in range(len(road)):
            if road[i][0] == pos:
                nextPos, nextWeight = road[i][1], road[i][2]+w
            if nextWeight < weight[nextPos]:
                weight[nextPos] = nextWeight
                q.put([nextWeight, nextPos])
            elif road[i][1] == pos:
                nextPos, nextWeight = road[i][0], road[i][2]+w
                if nextWeight < weight[nextPos]:
                    weight[nextPos] = nextWeight
                    q.put([nextWeight, nextPos]);

    answer = 0
    for w in weight:
        if w<=K:
            answer+=1
    return answer


# Second Solution
from queue import PriorityQueue
def dijkstra(road,N):
    INF = 987654321
    dist = [INF for i in range(N+1)]
    road.sort()
    pq = PriorityQueue()
    pq.put([0,1])
    dist[1] = 0
    while not pq.empty():
        cost,here = pq.get()
        if cost > dist[here]: continue
        for i in range(len(road)):
            if road[i][0] == here:
                there,nextCost = road[i][1], road[i][2]+cost
            if nextCost < dist[there]:
                dist[there] = nextCost
                pq.put([nextCost,there])
            elif road[i][1] == here:
                there,nextCost = road[i][0], road[i][2]+cost
            if nextCost < dist[there]:
                dist[there] = nextCost
                pq.put([nextCost,there])
    return dist
def solution(N, road, K):
    answer = 0
    dist = dijkstra(road,N)
    for d in dist:
        if d <= K:
            answer+=1
    return answer