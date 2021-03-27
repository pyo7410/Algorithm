import re
from itertools import permutations


def solution(expression):
    answer = 0

    num_list = list(map(int, re.findall('\d+', expression)))
    op_list = re.findall('\D+', expression)
    op_set = set(op_list)

    op_priority_list = list(permutations(op_set, len(op_set)))

    for op_priority in op_priority_list:
        # 원본은 유지해야 모든경우에서의 최대값을 찾을 수 있다.
        calc_num_list = num_list[:]
        calc_op_list = op_list[:]

        for opp in op_priority:
            idx = 0
            while idx < len(calc_op_list):
                if opp == calc_op_list[idx]:
                    num = eval(str(calc_num_list[idx]) + calc_op_list[idx] + str(calc_num_list[idx + 1]))

                    del calc_op_list[idx]
                    del calc_num_list[idx:idx+2]

                    calc_num_list.insert(idx, num)
                    idx -= 1
                idx += 1

        if answer < abs(int(calc_num_list[0])):
            answer = abs(int(calc_num_list[0]))

    return answer


print(solution("100-200*300-500+20"))
print(solution("50*6-3*2"))
print(solution("2-990-5+2"))
print(solution("2-990-5+2+32"))
