import re
from itertools import combinations


# 주문한 사람들의 각 메뉴들을 알파벳순으로 정렬하여 course에 있는 길이만큼의 조합을 만들어 조합에서 최대값만큼 뽑힌 조합을 선택하면 된다.
def solution(orders, course):
    answer = []

    # orders 리스트 안의 내용들을 각각 알파벳 순서대로 정렬
    orders = [''.join(sorted(order)) for order in orders]

    all_candidate_course_list = dict()
    for cnt in course:
        for order in orders:
            if cnt > len(order):
                continue

            candidate_course_list = list(map(''.join, combinations(order, cnt)))

            for candidate_course in candidate_course_list:
                if candidate_course in all_candidate_course_list:
                    all_candidate_course_list[candidate_course] += 1
                else:
                    all_candidate_course_list[candidate_course] = 1

        max_cnt = max(all_candidate_course_list.values())

        if max_cnt == 1:
            continue

        for candidate_course in all_candidate_course_list:
            if all_candidate_course_list[candidate_course] == max_cnt:
                answer.append(candidate_course)

        all_candidate_course_list.clear()

    return sorted(answer)


print(solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], [2, 3, 4]))
print(solution(["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"], [2, 3, 5]))
print(solution(["XYZ", "XWY", "WXA"], [2, 3, 4]))
