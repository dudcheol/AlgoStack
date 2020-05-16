dx = [0, 0, -1, 1] # 위, 아래, 오른쪽, 왼쪽
dy = [1, -1, 0, 0]

d = {"U": 0, "D": 1, "L": 2, "R": 3}

def solution(dirs):
    answer = 0
    x, y = 0, 0
    location = set()
    
    for dir in dirs:
        i = d[dir]
        
        nx, ny = x + dx[i], y + dy[i]
        
        # 좌표평면
        if nx < -5 or nx > 5 or ny < -5 or ny > 5:
            continue
        
        if (x, y, nx, ny) not in location:
            # 이미 지나간 길(양방향)
            location.add((x, y, nx, ny))
            location.add((nx, ny, x, y))
            answer += 1
        
        # 이전의 좌표에서 현재 움직인 좌표로 바꿔주기
        x, y = nx, ny
    
    return answer
