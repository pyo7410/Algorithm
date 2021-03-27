import re
import collections

def solution(s):
    answer = []

    num_rank = {}
    temp_s = s[2:-2].replace('},{', ' ').replace(',', ' ')
    # temp_s = re.sub('},{', ' ', s[2:-2])
    # temp_s = re.sub(',', ' ', temp_s)

    temp_s = temp_s.split(" ")

    for num in temp_s:
        if num in num_rank:
            num_rank[num] += 1
        else:
            num_rank[num] = 1

    answer = list(map(int, sorted(num_rank, key=lambda x: num_rank[x], reverse=True)))

    # num_rank = collections.Counter(re.findall('\d+', s))
    # answer = list(map(int, sorted(num_rank, key=lambda x: num_rank[x], reverse=True)))

    return answer


print(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))
print(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"))
print(solution("{{20,111},{111}}"))
print(solution("{{123}}"))
print(solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"))
