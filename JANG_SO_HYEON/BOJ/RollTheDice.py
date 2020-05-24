# 수정한 코드
from collections import deque

dice_ud = deque([0, 0, 0, 0])
dice_rl = deque([0, 0, 0, 0])
dice_di = [1, -1, -1, 1]

dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]

N, M, x, y, K = map(int, input().split())

List = [list(map(int, input().split())) for _ in range(N)]

Command = list(map(int, input().split()))

for i in Command:
    # 다음 이동 좌표의 범위 체크
    nx, ny = x + dx[i - 1], y + dy[i - 1]
    
    if nx < 0 or nx >= N or ny < 0 or ny >= M:
        continue

    x, y = nx, ny

    # 이동하는 방향이 오른쪽, 왼쪽
    if i in [1, 2]:
        dice_rl.rotate(dice_di[i - 1])
    
        # 주사위 (위, 아래), (왼, 오른) 둘로 나눴으므로, 동기화
        dice_ud[0], dice_ud[2] = dice_rl[0], dice_rl[2]

    # 이동하는 방향이 위, 아래
    elif i in [3, 4]:
        dice_ud.rotate(dice_di[i - 1])
        
        dice_rl[0], dice_rl[2] = dice_ud[0], dice_ud[2]

    # 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
    if List[x][y] == 0:
        List[x][y] = dice_ud[2]
    # 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
    else:
        dice_ud[2] = dice_rl[2] = List[x][y]
        List[x][y] = 0

    # 윗면 출력
    print(dice_ud[0])

