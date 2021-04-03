dx = [0, 0, 1, 1]
dy = [0, 1, 1, 0]


def solution(m, n, board):
    answer = 0
    board = list(list(b) for b in board)

    flag = True
    while flag:
        flag = False
        visited = [[False for j in range(n)] for i in range(m)]

        for i in range(m - 1):
            for j in range(n - 1):
                if board[i][j] == '-':
                    continue

                if check(i, j, board):
                    flag = True
                    for k in range(4):
                        visited[i + dy[k]][j + dx[k]] = True

        for i in range(m):
            for j in range(n):
                if visited[i][j]:
                    answer += 1

                    for k in range(i - 1, -1, -1):
                        board[k + 1][j] = board[k][j]
                        board[k][j] = '-'

    return answer


def check(i, j, board):
    target = board[i][j]

    for idx in range(1, 4):
        if target != board[i + dy[idx]][j + dx[idx]]:
            return False

    return True


print(solution(4, 5, ["CCBDE", "AAADE", "AAABF", "CCBBF"]))
print(solution(6, 6, ["TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"]))
