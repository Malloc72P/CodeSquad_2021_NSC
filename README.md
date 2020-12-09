# 2021 마스터즈 코스 테스트 1차 테스트
## 프로그램 작성환경
* OS        :   Windows 10
* IDE       :   IntelliJ IDEA 2020.2.1
* 사용언어    :   JAVA  
* JAVA 버전  :   java version "11.0.4" 2019-07-16 LTS

## 1단계 : 단어 밀어내기 구현하기
#### 1.1 split으로 사용자 입력을 쪼개기
<Pre>
    <code>String[] tokens = _inputCmd.split(" ");//받은 입력을 쪼갠다.</code>
</Pre>
#### 1.2 쪼갠 데이터를 이용해서 문자열을 어떤 방향으로 밀어낼지, 얼마나 밀어낼지를 계산하기
<Pre>
# 어떤 방향으로 밀어야 하는지 알아내기

    1) 2,3번째 패러미터를 조합하면 왼쪽으로 밀지 오른쪽으로 밀지를 알아낼 수 있다.
    2) 2번째 패러미터가 음수면 0, 양수면 1로 취급한다. 
    3) L은 0, R은 1로 취급한다.
    4) 0과 1로 변환된 2, 3번째 패러미터를 XOR 연산한다음, 0을 1로, 1을 0으로 변환한다
    5) 그 결과가 0이면 왼쪽으로 밀라는 소리고, 1이면 오른쪽으로 밀어낸다 
    
# 얼마나 밀어야 하는지 알아내기

    1) 단어의 길이가 두번째 패러미터의 절댓값보다 짧은 경우, 한바퀴를 넘어서 단어를 밀게 된다
    2) 한바퀴 밀어버리면 다시 제자리로 온다. 의미없다
    3) 이 경우, 두번째 패러미터의 절댓값을 문자길이로 나머지연산한 결과만큼 단어를 민다.
        3-1) 만약 나머지연산한 결과가 0이면, n바퀴 돌아서 제자리라는 것이므로, 원래 단어를 출력한다. 
    4) 그렇지 않은 경우에는 두번째 패러미터의 절댓값만큼만 민다.
</Pre>
#### 1.3 ArrayList를 이용해서 밀어내기
<pre>
    1) 우선 ArrayList<Character>에 문자열의 처음부터 끝까지 집어넣늗다.
    2) 왼쪽으로 밀어야 한다면, ArrayList의 맨 앞에서 요소를 제거하고, 제거한 값을 맨 뒤에 추가시킨다.
    3) 오른쪽이면 반대로 맨 뒤에서 제거하고 맨 앞에 추가한다.
    4) 1.1.2에서 얼마나 밀어야 하는지 구했는데, 이 값만큼 반복적으로 밀어준다.
    5) 다 끝나면 ArrayList의 처음부터 끝까지 출력한다.
</pre>