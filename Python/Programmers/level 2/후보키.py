from itertools import combinations


def solution(relation):
    answer = 0

    temp = [i for i in range(len(relation[0]))]
    combi_lists = list()

    # 조합 생성
    for cnt in range(1, len(relation[0]) + 1):
        combi_lists.append(list(combinations(temp, cnt)))

    # 조합리스트를 문자열로 변경
    combi_str_list = list()
    for combi_list in combi_lists:
        for i in combi_list:
            combi_str_list.append(''.join(map(str, i)))

    while len(combi_str_list) > 0:
        candidate_key = list()
        is_candidate_key = True

        for r in relation:
            key = ""
            for j in combi_str_list[0]:
                key += r[int(j)]

            if key in candidate_key:
                is_candidate_key = False
                break
            else:
                candidate_key.append(key)

        if is_candidate_key is False:
            del combi_str_list[0]
            continue

        # 현재 조합을 리스트형태로 변경
        str_list = list(combi_str_list[0])

        # 현재 조합으로된 인덱스(str_list) 전부가 포함되지 않은 조합들만 combi_str_list에 새로저장
        combi_str_list = [s for s in combi_str_list if any(str not in s for str in str_list)]

        answer += 1

    return answer


print(solution([["100", "ryan", "music", "2"],
                ["200", "apeach", "math", "2"],
                ["300", "tube", "computer", "3"],
                ["400", "con", "computer", "4"],
                ["500", "muzi", "music", "3"],
                ["600", "apeach", "music", "2"]]))
