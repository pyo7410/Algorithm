def solution(record):
    answer = []

    uid_logs = dict()

    # 아이디의 닉네임과 명령어를 기록
    # 이때, change는 출력할 필요 X
    for info in record:
        log = info.split(" ")

        command = log[0]
        uid = log[1]

        if command != "Leave":
            nickname = log[2]
            uid_logs[uid] = nickname

    # 처리한 명령어와 최종 닉네임으로 결과저장
    for info in record:
        log = info.split(" ")

        command = log[0]
        uid = log[1]

        if command == "Enter":
            answer.append(uid_logs[uid] + "님이 들어왔습니다.")
        elif command == "Leave":
            answer.append(uid_logs[uid] + "님이 나갔습니다.")

    return answer


print(solution(["Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"]))
