def solution(board, moves):
    answer = 0

    basket = []
    board_len = len(board[0])

    # 2차원 리스트 열만 뽑는 방법?
    for i in moves:
        for j in range(0, board_len):
            if board[j][i - 1] > 0:
                if len(basket) != 0 and basket[-1] == board[j][i - 1]:
                    basket.pop()
                    answer += 2
                else:
                    basket.append(board[j][i - 1])
                board[j][i - 1] = 0
                break

    return answer


board = [list(map(int, input().split())) for _ in range(5)]
moves = list(map(int, input().split()))

print(solution(board, moves))
