def solution(participant, completion):
    answer = ''

    participant.sort()
    completion.sort()

    for i in range(0, len(completion)):
        if completion[i] != participant[i]:
            answer = participant[i]
            break

    if not answer:
        answer = participant[-1]

    return answer

"""
import collections 

'''
collections에 내장된 함수인 Counter()는 Dic과 같이 hash형 자료들의 값의 개수를 셀 때 사용 
Dic처럼 {key:value} 형식으로 만들어짐 
Counter() 객체끼리 빼는 것도 가능
0 (zero) 나 음수 (minus)의 값들도 가능
해당하는 값이 없더라도 error가 아닌 0을 반환
오름차순으로 정렬됨

import collections
lst = ['aa', 'cc', 'dd', 'aa', 'bb', 'ee']
print(collections.Counter(lst))

결과
Counter({'aa': 2, 'cc': 1, 'dd': 1, 'bb': 1, 'ee': 1})
'''

import collections 
def solution(p, c): 
    p.sort() 
    c.sort() 
    # 카운터가 오름차순으로 정렬되므로 1이 남는 곳이 맨 앞으로 오게된다.
    result = collections.Counter(p) - collections.Counter(c) 
    return list(result)[0]

"""

participant = ["marina", "josipa", "nikola", "vinko", "filipa"]
completion = ["josipa", "filipa", "marina", "nikola"]
print(solution(participant, completion))

