from itertools import combinations
from bisect import bisect_left


def solution(info, query):
    answer = []

    conditions_dict = {}
    for i in info:
        i_list = i.split()
        # 조건들과 점수를 따로 나눔
        conditions = i_list[:-1]
        score = int(i_list[-1])

        combi_list = [0, 1, 2, 3]
        for cnt in range(5):  # 조건들에 대해 조합을 이용해서
            wildcard_idx_list = list(combinations(combi_list, cnt))
            for wildcard_idx in wildcard_idx_list:
                conditions_list = conditions.copy()
                for idx in wildcard_idx:
                    conditions_list[idx] = "-"

                conditions_str = "".join(conditions_list)

                if conditions_str in conditions_dict:
                    conditions_dict[conditions_str].append(score)
                else:
                    # 점수를 계속 추가하기위해 리스트를 딕셔너리 value에 추가
                    conditions_dict[conditions_str] = [score]

    for c in conditions_dict.values():
        c.sort()

    for q in query:
        q_list = [i for i in q.split(" ") if i != "and"]
        q_condition = "".join(q_list[:-1])
        q_score = int(q_list[-1])

        if q_condition in conditions_dict:
            scores = conditions_dict[q_condition]

            if len(scores) > 0:
                # 두번째 파라미터에 해당하는 값중 왼쪽의 인덱스를 반환
                # 만약 같은 값이 없다면 두번째 파라미터보다 바로 하나 작은 수의 위치가
                # 같은 값이 여러개라면 그 중 가장왼쪽에 있는 인덱스가 반환
                st = bisect_left(scores, q_score)
                answer.append(len(conditions_dict[q_condition]) - st)
        else:
            answer.append(0)

    return answer


print(solution(["java backend junior pizza 150", "python frontend senior chicken 210",
                "python frontend senior chicken 150", "cpp backend senior pizza 260",
                "java backend junior chicken 80", "python backend senior chicken 50"],
               ["java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250", "- and backend and senior and - 150",
                "- and - and - and chicken 100", "- and - and - and - 150"]))
