def solution(n, t, m, p):
    answer = ''

    num = list()

    i = 0
    while len(num) <= (t * m):
        num.extend(convert(i, n))
        i += 1

    idx = p - 1
    for i in range(0, t):
        answer += num[idx + (i * m)]

    return answer


def convert(num, base):
    temp = "0123456789ABCDEF"
    q, r = divmod(num, base)

    if q == 0:
        return temp[r]
    else:
        # q를 base로 변환
        # 즉, n진수의 다음 자리를 구함
        return convert(q, base) + temp[r]


print(solution(2, 4, 2, 1))
print(solution(16, 16, 2, 1))
print(solution(16, 16, 2, 2))
