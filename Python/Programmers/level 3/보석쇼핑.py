def solution(gems):
    answer = []

    unique_gem = set(gems)
    select_gem = dict()
    left = 0
    right = 0
    select_gem[gems[0]] = 1
    minLen = 987654321

    while left < len(gems) and right < len(gems):
        curLen = 0
        if len(select_gem) == len(unique_gem):
            curLen = right - left

            if minLen > curLen:
                minLen = curLen
                answer = [left + 1, right + 1]

            select_gem[gems[left]] -= 1

            if select_gem[gems[left]] == 0:
                del select_gem[gems[left]]

            left += 1
        else:
            right += 1

            if right < len(gems):
                if gems[right] in select_gem:
                    select_gem[gems[right]] += 1
                else:
                    select_gem[gems[right]] = 1

    return answer


print(solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]))
print(solution(["AA", "AB", "AC", "AA", "AC"]))
print(solution(["XYZ", "XYZ", "XYZ"]))
print(solution(["ZZZ", "YYY", "NNNN", "YYY", "BBB"]))
