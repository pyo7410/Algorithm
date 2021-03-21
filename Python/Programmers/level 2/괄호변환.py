def solution(p):
    answer = ''

    if is_proper(p):
        return p

    answer = go(p)
    return answer


def go(p):
    if p == "":
        return p

    u, v = split_bracket(p)
    # print(u, v)

    if is_proper(u):
        u += go(v)
        return u
    else:
        s = "("
        s += go(v)
        s += ")"
        if len(u) > 0:
            temp = revers_str(u[1:-1])
            s += temp
        return s


def revers_str(u):
    answer = ""

    for bracket in u:
        if bracket == "(":
            answer += ")"
        else:
            answer += "("

    return answer


def split_bracket(p):
    u, v = p, ""
    # 균형잡힌괄호는 최소 (, ) 하나씩 2개가 있어야하므로 2부터 시작
    for i in range(2, len(p)):
        temp = p[:i]
        if is_balance(p[:i]):
            u = p[:i]
            v = p[i:]
            break
    return u, v


def is_balance(p):
    return p.count("(") == p.count(")")


def is_proper(p):
    bracket_cnt = 0

    for bracket in p:
        if bracket == "(":
            bracket_cnt += 1
        else:
            bracket_cnt -= 1

        if bracket_cnt < 0:
            return False

    return True


print(solution(""))
print(solution("(()())()"))
print(solution(")("))
print(solution("()))((()"))
