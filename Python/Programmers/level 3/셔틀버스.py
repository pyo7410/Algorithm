def solution(n, t, m, timetable):
    answer = ''

    timetable.sort()
    timetable_min = [int(time[:2]) * 60 + int(time[3:5]) for time in timetable]

    bus_time = (9 * 60)
    idx = 0
    max_time = (60 * 9) + (n - 1) * t

    # for i in range(n):
    #     if len(timetable_min) < m:
    #         return '%02d:%02d' % (max_time // 60, max_time % 60)
    #     if i == n - 1:
    #         if timetable_min[0] <= max_time:
    #             max_time = timetable_min[m - 1] - 1
    #         return '%02d:%02d' % (max_time // 60, max_time % 60)
    #     for j in range(m - 1, -1, -1):
    #         bus_arrive = (60 * 9) + i * t
    #         if timetable_min[j] <= bus_arrive:
    #             del timetable_min[j]

    for bus_cnt in range(n):
        cur_crew_cnt = 0
        for crew_cnt in range(idx, len(timetable_min)):
            if timetable_min[idx] <= bus_time:
                idx += 1
                cur_crew_cnt += 1

            if cur_crew_cnt == m:
                break

        if bus_cnt == n - 1:
            if cur_crew_cnt == m:
                bus_time = timetable_min[idx - 1] - 1
            break

        bus_time += t

    answer = '%02d:%02d' % (bus_time // 60, bus_time % 60)
    return answer


# print(solution(3, 1, 5, ["09:02", "23:59"]))
print(solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03"]))
print(solution(2, 10, 2, ["09:10", "09:09", "08:00"]))
print(solution(2, 1, 2, ["09:00", "09:00", "09:00", "09:00"]))
print(solution(1, 1, 5, ["00:01", "00:01", "00:01", "00:01", "00:01"]))
print(solution(1, 1, 1, ["23:59"]))
print(solution(10, 60, 45, ["23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"]))
