def solution(N, stages):
    answer = []

    #stages.sort()

    #idx = 0
    total_player = len(stages)
    rate = {}

    '''
    for i in range(1, N + 1):
        cnt = 0

        while idx < len(stages) and i == stages[idx]:
            cnt += 1
            idx += 1

        if total_player <= 0:
            rate[i] = 0
        else:
            rate[i] = cnt / total_player
            total_player -= cnt
    '''

    for stage in range(1, N + 1):
        if total_player <= 0:
            rate[stage] = 0
        else:
            cnt = stages.count(stage)
            rate[stage] = cnt / total_player
            total_player -= cnt

    '''
    rate = sorted(rate.items(), key=lambda x: x[1], reverse=True)

    for i in rate:
        answer.append(i[0])
    '''

    # dict를 list에 넣을경우 key값이 들어가게된다.
    answer = sorted(rate, key=lambda x: rate[x], reverse=True)

    return answer


# [3,4,2,1,5]
print(solution(5, [2, 1, 2, 6, 2, 4, 3, 3]))
# [4,1,2,3]
print(solution(4, [4, 4, 4, 4, 4]))
