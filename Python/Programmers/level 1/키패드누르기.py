def solution(numbers, hand):
    answer = ''

    phone = [[1, 4, 7], [2, 5, 8, 0], [3, 6, 9]]
    # *, #의 위치도 처음에 초기화 해야함을 잊지말자
    # 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, *, #
    pad = [[3, 1], [0, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2], [3, 0], [3, 2]]
    left_cur = 10
    right_cur = 11

    for number in numbers:
        if number in phone[0]:
            answer += 'L'
            left_cur = number
        elif number in phone[2]:
            answer += 'R'
            right_cur = number
        elif number in phone[1]:
            l_len = abs(pad[left_cur][0] - pad[number][0]) + abs(pad[left_cur][1] - pad[number][1])
            r_len = abs(pad[right_cur][0] - pad[number][0]) + abs(pad[right_cur][1] - pad[number][1])

            if l_len > r_len:
                answer += 'R'
                right_cur = number
            elif l_len < r_len:
                answer += 'L'
                left_cur = number
            elif hand == 'right':
                answer += 'R'
                right_cur = number
            elif hand == 'left':
                answer += 'L'
                left_cur = number

    return answer


# print(solution([1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], "right")) # LRLLLRLLRRL
# print(solution([7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2],	"left")) # LRLLRRLLLRR
# print(solution([1, 2, 3, 4, 5, 6, 7, 8, 9, 0], "right")) # LLRLLRLL
print(solution([2, 5, 8, 0], "right")) # RRRR