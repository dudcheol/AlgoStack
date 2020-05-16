# 나의 코드(시간초과) - 정확성 O, 효율성 X
import math

def solution(n, stations, w):
    answer = 0
    spread = [False]*n
    
    # 전파 도달하는 곳 : True
    for i in stations:
        if i-w-1 >= 0 and i+w < n:
            for j in range(i-w-1, i+w):
                spread[j] = True
        elif i-w-1 >= 0:
            for j in range(i-w-1, n):
                spread[j] = True
        elif i+w < n:
            for j in range(0, i+w):
                spread[j] = True
        else:
            for j in range(0, n):
                spread[j] = True
    count = 0
    
    for i in range(n):
        if spread[i] == False:
            count += 1
        else:
            answer += math.ceil(count/(w*2+1))
            count = 0

    if count > 0:
        answer += math.ceil(count/(w*2+1))
    
    return answer


# 수정한 코드(다른 코드 참고)
import math

def solution(n, stations, w):
    answer = 0
    
    spread = []
    
    # 설치된 기지국 사이에 전파가 닿지 않는 거리를 저장
    for i in range(1, len(stations)):
        spread.append((stations[i]-w-1)-(stations[i-1]+w))

    # 맨 앞 기지국에서 첫 번째 아파트 사이에 전파가 닿지 않는 거리
    spread.append(stations[0]-w-1)

    # 맨 뒤 기지국에서 마지막 아파트 사이에 전파가 닿지 않는 거리
    spread.append(n-(stations[-1]+w))
    
    for i in spread:
        if i <= 0:
            continue
        
        answer += math.ceil(i/((w*2)+1))
    
    return answer

