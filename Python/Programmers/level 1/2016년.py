def solution(a, b):
    answer = ''

    month = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    day = ["THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"]

    total_day = b
    for i in range(0, a - 1):
        total_day += month[i]

    answer = day[total_day % 7]

    return answer


def solution2(a, b):
    day = ["THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"]
    mon = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    return day[(sum(mon[:a - 1]) + b) % 7]


import datetime


def solution3(a, b):
    t = 'MON TUE WED THU FRI SAT SUN'.split()
    return t[datetime.datetime(2016, a, b).weekday()]


print(solution(5, 24))
# print(solution2(5, 24))
# print(solution3(5, 24))
