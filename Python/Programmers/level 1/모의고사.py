def solution(answers):
    p = []
    p.append([1, 2, 3, 4, 5])
    p.append([2, 1, 2, 3, 2, 4, 2, 5])
    p.append([3, 3, 1, 1, 2, 2, 4, 4, 5, 5])

    cnt = [0, 0, 0]

    for i in range(len(p)):
        for j in range(len(answers)):
            if p[i][j % len(p[i])] == answers[j]:
                cnt[i] += 1

    score = [cnt[0], cnt[1], cnt[2]]

    answer = []
    # enumerate는 0 부터 시작됨을 주의
    for p, s in enumerate(score):
        if s == max(score):
            answer.append(p + 1)

    return answer


answers = [1, 2, 3, 4, 5]
print(solution(answers))
answers = [1, 3, 2, 4, 2]
print(solution(answers))

'''
from itertools import cycle

def solution(answers):
    giveups = [
        cycle([1,2,3,4,5]),
        cycle([2,1,2,3,2,4,2,5]),
        cycle([3,3,1,1,2,2,4,4,5,5]),
    ]
    scores = [0, 0, 0]
    for num in answers:
        for i in range(3):
            if next(giveups[i]) == num:
                scores[i] += 1
    highest = max(scores)

    return [i + 1 for i, v in enumerate(scores) if v == highest]
'''

