def solution(lines):
    answer = 0

    start_times = list()
    end_times = list()

    for line in lines:
        logs = line.split(" ")
        time = logs[1].split(":")

        process_time = float(logs[2][:-1]) * 1000 - 1

        end = (int(time[0]) * 60 * 60 * 1000) + (int(time[1]) * 60 * 1000) + (float(time[2]) * 1000)
        end_times.append(end)

        start = end - process_time
        start_times.append(start)

    for i in range(len(end_times)):
        cnt = 0
        start_range = start_times[i] - 999

        for j in range(len(start_times)):
            if start_range <= start_times[j] <= start_times[i]:
                cnt += 1
            elif start_times[j] <= start_range <= end_times[j]:
                cnt += 1

        answer = max(answer, cnt)

    return answer


# print(solution(["2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"]))
# print(solution(["2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"]))
# print(solution(["2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"]))
print(solution(["2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"]))
