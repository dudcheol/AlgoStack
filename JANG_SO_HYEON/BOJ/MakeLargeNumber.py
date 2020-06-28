# 나의 코드
N = int(input())
List = list(input().split())
MAX = 0

for i in List:
    MAX = max(MAX, len(i))

for i in range(len(List)):
    # 최대 길이에 맞춰 문자열 늘리기
    List[i] = [List[i] * (MAX // len(List[i])) + List[i][:(MAX % len(List[i]))], len(List[i])]

List = sorted(List, key=lambda x:x[0], reverse=True)

if List[0][0] == '0':
    print(0)
else:
    for i in List:
        print(i[0][:i[1]], end='')

# 이와 같이 코드를 작성하는 것도 좋지만 모듈을 이용해 볼 것
# functools.cmp_to_key
