# 나의 코드
# 모든 경우를 확인하기 위해 DFS 이용
def DFS(i, sum):
    global answer
    
    # N+1일째 되는 날 퇴사
    if i == N+1:
        answer = max(answer, sum)
        return
    
    # 일을 할 경우
    if i + t[i-1] <= N+1:
        DFS(i + t[i-1], sum + p[i-1])
    
    # 일을 안할 경우
    if i+1 <= N+1:
        DFS(i+1, sum)


N = int(input())

t, p = [], []

for _ in range(N):
    i, j = map(int, input().split())
    t.append(i)
    p.append(j)

answer = 0

# 시작일자, 금액
DFS(1, 0)

print(answer)
