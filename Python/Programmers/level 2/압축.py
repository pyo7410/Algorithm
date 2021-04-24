def solution(msg):
    answer = []

    dictionary = dict()
    for i in range(ord('A'), ord('Z') + 1):
        dictionary[chr(i)] = i - ord('A') + 1

    idx = 0
    msg_len = len(msg)
    while idx < msg_len:
        temp = ""
        flag = False
        for idx in range(idx, len(msg)):
            temp += msg[idx]

            if temp not in dictionary:
                flag = True
                break

        if flag:
            dictionary[temp] = len(dictionary) + 1
            answer.append(dictionary[temp[:-1]])
        else:
            answer.append(dictionary[temp])
            break

    return answer


print(solution("KAKAO"))
print(solution("TOBEORNOTTOBEORTOBEORNOT"))
print(solution("ABABABABABABABAB"))
