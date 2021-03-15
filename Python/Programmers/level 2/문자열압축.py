def solution(s):
    answer = 987654321

    # 문자열이 한개일 수 있다
    if len(s) == 1:
        return 1

    # 압축할 수 있는 문자열의 수는 전체 문자열의 절반까지 가능하다
    max_zip_len = len(s) // 2
    str_len = len(s)

    for i in range(1, max_zip_len + 1):
        # 문자열을 i 길이만큼 일정하게 자른 문자를 zip_str_list에 저장해준다.
        # range의 세번째 파라미터는 몇 개씩 넘길지를 넘겨준다
        # ex) range(0, 10, 2) -> for (int i = 0; i < 10; i += 2)
        zip_str_list = [s[j:j + i] for j in range(0, str_len, i)]

        cnt = 1
        # 압축될 문자열을 저장할 변수
        zip_result = ""
        # i 길이만큼 일정하게 자른 문자열
        zip_str_list_len = len(zip_str_list)
        for idx in range(1, zip_str_list_len):
            # 이전 인덱스와 같다면 압축 가능
            if zip_str_list[idx - 1] == zip_str_list[idx]:
                cnt += 1
            else:
                # 만약 잘린 문자열이 한개 뿐이라면 앞에 1을 붙이지 않는다
                if cnt > 1:
                    zip_result += str(cnt)
                # 압축 문자열에 추가
                # 이때 현재 인덱스는 이전과 다른 값이므로 이전 값을 추가해주어야한다.
                zip_result += zip_str_list[idx - 1]
                # 현재 인덱스는 추가가 안되고 처음 나왔으므로 1로 초기화
                cnt = 1

        # 배열의 마지막은 처리되지 않으므로 배열 밖에서 처리
        if cnt > 1:
            zip_result += str(cnt)
        zip_result += zip_str_list[zip_str_list_len - 1]

        answer = min(answer, len(zip_result))

    return answer


print(solution("aabbaccc"))
print(solution("ababcdcdababcdcd"))
print(solution("abcabcdede"))
print(solution("abcabcabcabcdededededede"))
print(solution("xababcdcdababcdcd"))

print(solution("xxxxxxxxxxyyy"))
