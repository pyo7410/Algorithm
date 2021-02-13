def solution(n, lost, reserve):
    answer = 0

    # 여벌이 있더라도 도난체크
    lost_student = [i for i in lost if i not in reserve]
    reserve_student = [i for i in reserve if i not in lost]
    students = [i for i in range(1, n + 1)]

    for student in lost_student:
        if student - 1 in reserve_student:
            reserve_student.remove(student - 1)
        elif student + 1 in reserve_student:
            reserve_student.remove(student + 1)
        else:
            students.remove(student)

    answer = len(students)

    return answer


print(solution(5, [2, 4], [1, 3, 5]))
print(solution(5, [2, 4], [3]))
print(solution(3, [3], [1]))
print(solution(7, [2, 3, 4, 6], [1, 2, 3]))
