import re

def solution(new_id):
    answer = ''

    # 알아서 알파벳만 소문자로 바꾸어 준다.
    # 1단계
    new_id = new_id.lower()

    # 2단계
    new_id = re.sub('[^a-z0-9-_.]', '', new_id)

    # 3단계
    new_id = re.sub('\.+', '.', new_id)

    # 4단계
    new_id = new_id.strip('.')

    # 5단계
    if not new_id :
        new_id = 'a'

    # 6단계
    if len(new_id) > 15:
        new_id = new_id[:15]
    new_id = new_id.strip('.')

    # 7단계
    if len(new_id) <= 2:
        c = new_id[-1]
        while len(new_id) < 3:
            new_id += c

    return new_id


print(solution("...!@BaT#*..y.abcdefghijklm"))
print(solution("z-+.^."))
print(solution("=.="))
print(solution("123_.def"))
print(solution("abcdefghijklmn.p"))