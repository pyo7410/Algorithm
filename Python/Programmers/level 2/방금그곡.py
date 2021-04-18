def solution(m, musicinfos):
    answer = '(None)'

    max_play_time = 0

    # "#"이 붙은 음계를 바꾸어주기위함
    change_rhythm = {"C#": "c", "D#": "d", "F#": "f", "G#": "g", "A#": "a"}

    # "#"이 붙은 음계를 바꿈
    for rhythm in change_rhythm:
        m = m.replace(rhythm, change_rhythm[rhythm])

    for musicinfo in musicinfos:
        info = musicinfo.split(",")

        st_time = int(info[0][:2]) * 60 + int(info[0][3:])
        en_time = int(info[1][:2]) * 60 + int(info[1][3:])

        # 총 재생시간
        play_time = en_time - st_time

        # 음악의 정보
        music_m = info[3]

        # "#"이 붙은 음계를 바꿈
        for rhythm in change_rhythm:
            music_m = music_m.replace(rhythm, change_rhythm[rhythm])

        # 총 재생시간동안의 음악 정보
        full_m = ""

        # 만약 재생시간이 음악의 정보의 길이 즉, 음악의 원래 시간보다 짧다면
        # 총 재생시간동안의 음악 정보는 재생된 길이만큼만 생성
        if len(music_m) > play_time:
            full_m = music_m[:play_time]
        else:
            full_m = music_m

        # 총 재생시간에 맞게 총 재생시간동안의 음악 정보를 갱신
        for i in range(len(music_m), play_time):
            full_m += music_m[i % len(music_m)]

        if m in full_m:
            # 문제의 조건에 따라 노래가 여러개일 경우
            # 재생시간이 가장 긴 노래가 저장되어야함
            if answer != "(None)":
                if max_play_time < play_time:
                    max_play_time = play_time
                    answer = info[2]
            else:
                max_play_time = play_time
                answer = info[2]

    return answer


print(solution("ABCDEFG", ["12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"]))
print(solution("CC#BCC#BCC#BCC#B", ["03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"]))
print(solution("ABC", ["12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"]))
