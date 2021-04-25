from functools import cmp_to_key


def solution(files):
    answer = []

    files_list = list()

    for file in files:
        temp = ""
        flag = False
        file_info = list()

        for idx in range(len(file)):
            temp += file[idx]

            if file[idx].isdigit():
                file_info.append(temp[:-1].lower())
                temp = ""
                flag = True

                for i in range(5):
                    if file[idx].isdigit():
                        temp += file[idx]
                        idx += 1

                        if idx >= len(file):
                            break
                    else:
                        break

                file_info.append(int(temp))

            if flag:
                file_info.append(file)
                files_list.append(file_info)
                break

    files_list = sorted(files_list, key=cmp_to_key(compare))
    answer = [a[2] for a in files_list]

    return answer


def compare(x, y):
    if x[0] > y[0]:
        return 1
    elif x[0] < y[0]:
        return -1
    else:
        if x[1] > y[1]:
            return 1
        elif x[1] < y[1]:
            return -1
        else:
            return 0


print(solution(["img000012345", "img1.png", "img2", "IMG02"]))
# print(solution(["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]))
# print(solution(["F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"]))

'''
import re

def solution(files):
    a = sorted(files, key=lambda file : int(re.findall('\d+', file)[0]))
    b = sorted(a, key=lambda file : re.split('\d+', file.lower())[0])
    return b
'''
