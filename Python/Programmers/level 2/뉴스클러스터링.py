def solution(str1, str2):
    answer = 0

    str1_list = [str1[i-2:i].upper() for i in range(2, len(str1) + 1) if str1[i-2:i].isalpha()]
    str2_list = [str2[i-2:i].upper() for i in range(2, len(str2) + 1) if str2[i-2:i].isalpha()]

    inter = set(str1_list) & set(str2_list)
    union = set(str1_list) | set(str2_list)

    # 만약 공집합일경우 65536이라는 조건이 있다
    if len(union) == 0:
        answer = 65536
    else:
        # 각 부분 문자열에서 교집합의 원소의 해당하는 값의 개수가 가장 적게나온쪽이 교집합의 수가된다.
        inter_cnt_list = [min(str1_list.count(s), str2_list.count(s)) for s in inter]
        # 각 부분 문자열에서 합집합의 원소의 해당하는 값의 개수가 가장 많이나온쪽이 합집합의 개수가된다.
        union_cnt_list = [max(str1_list.count(s), str2_list.count(s)) for s in union]

        inter_cnt = sum(inter_cnt_list)
        union_cnt = sum(union_cnt_list)

        answer = (inter_cnt / union_cnt) * 65536

    return int(answer)


# print(solution("FRANCE", "french"))
print(solution("handshake", "shake hands"))
# print(solution("aa1+aa2", "AAAA12"))
# print(solution("E=M*C^2", "e=m*c^2"))
