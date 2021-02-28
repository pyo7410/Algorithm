import re


def solution(n, arr1, arr2):
    answer = []

    for i in range(n):
        map = "{0:b}".format(arr1[i] | arr2[i]).zfill(n)

        # map = str(bin(arr1[i] | arr2[i]))[2:].rjust(n, '0')
        # map = str(bin(arr1[i] | arr2[i]))[2:].zfill(n)

        # map = format(arr1[i] | arr2[i], 'b').rjust(n, '0')
        map = re.sub('1', '#', map)
        map = re.sub('0', ' ', map)

        answer.append(map)

    return answer


# "#####","# # #", "### #", "# ##", "#####"
#print(solution(5, [9, 20, 28, 18, 11], 	[30, 1, 21, 17, 28]))
# "######", "### #", "## ##", " #### ", " #####", "### # "
print(solution(6, [46, 33, 33, 22, 31, 50], [27, 56, 19, 14, 14, 10]))
