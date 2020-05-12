# 나의 코드
def solution(n, words):
    answer = [0] * 2
    
    # 이전 단어
    prev = words[0]
    # 끝말잇기에 사용한 단어
    end = [words[0]]
    
    for i in range(1, len(words)):
        if prev[-1] == words[i][0] and words[i] not in end:
            end.append(words[i])
            prev = words[i]
        else:
            answer = [(i%n)+1, (i//n)+1]
            break

    return answer

