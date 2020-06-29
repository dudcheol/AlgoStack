# 규칙
1. 첫 번째 손가락 : 8, 16, 24, 32
2. 두 번째 손가락 : 7, 9, 15, 17, 23
3. 세 번째 손가락 : 6, 10, 14, 18
4. 네 번째 손가락 : 5, 11, 13, 19
5. 다섯 번째 손가락 : 12, 20, 28, 36

# 코드
InjuredFinger = int(input())
num = int(input())
count = 0

if InjuredFinger == 1:
    count = 8 * num
elif InjuredFinger == 2:
    if num % 2:
        count = (8 * (num // 2 + 1)) - 1
    else:
        count = 1 + 8 * num // 2
elif InjuredFinger == 3:
    count = 2 + num*4
elif InjuredFinger == 4:
    if num % 2:
        count = 8*(num//2+1)-3
    else:
        count = 3 + 8*num//2
else:
    count = 4 + num*8

print(count)
