def solution(dartResult):
    answer = 0

    score = []
    num = ""
    for c in dartResult:
        if c.isdigit():
            num += c
        elif c == '*':
            score[-1] *= 2

            if len(score) >= 2:
                score[-2] *= 2
        elif c == '#':
            score[-1] *= -1
        else:
            score.append(int(num))
            num = ""

            if c == 'D':
                score[-1] = score[-1] ** 2
            elif c == 'T':
                score[-1] = score[-1] ** 3

    answer = sum(score)

    return answer


print(solution('1S2D*3T'))
print(solution('1D2S#10S'))
print(solution('1D2S0T'))
print(solution('1S*2T*3S'))
print(solution('1D#2S*3S'))
print(solution('1T2D3D#'))
print(solution('1D2S3T*'))
