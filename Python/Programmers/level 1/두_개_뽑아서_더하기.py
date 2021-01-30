from itertools import permutations

def solution(numbers):
    answer = set()

    select_num_list = list(permutations(list(numbers), 2))

    for select_nums in select_num_list:
        answer.add(sum(select_nums))

    # 그냥 list(answer) 할 경우 set을 사용한다 하더라도
    # 자동으로 오름차순으로 정렬되지 않는다!
    return sorted(list(answer))


numbers = list(map(int, input().split()))

print(solution(numbers))
