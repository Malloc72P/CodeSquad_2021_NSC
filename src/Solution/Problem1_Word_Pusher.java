package Solution;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem1_Word_Pusher implements MyRunnable {
    public void runProblem(){
        Scanner sc = new Scanner(System.in);
        String _inputCmd;
        System.out.print("> ");
        _inputCmd = sc.nextLine();//사용자의 입력을 받는다.
        String _word;//단어를 입력받을 변수
        int _range;// 두번째 패러미터를 입력받을 변수
        char _inputDirection;//세번째 패러미터 L | R을 입력받을 변수
        String[] tokens = _inputCmd.split(" ");//받은 입력을 쪼갠다.
        _word = tokens[0];
        _range = Integer.parseInt(tokens[1]);
        _inputDirection = tokens[2].charAt(0);
        int _realDirection = getDirection(_range, _inputDirection);
        int _realRange = getRange(_range, _word);
        if(_realRange == 0){//한칸도 밀리지 않은 경우
            System.out.println(_word);
            return;
        }
        pushString(_word, _realRange, _realDirection);
    }
    private int getRange(int _range, String _word){
        int _rangeAbs = Math.abs(_range);
        if(_word.length() > _rangeAbs){
            return _rangeAbs;//단어 길이가 입력한 이동값의 절대값보다 긴 경우엔, 입력값만큼 밀어도 된다.
        }
        else{//짧거나 같으면 나머지연산한 결과를 넣는다.
            //한바퀴 밀면 원래 문자열이 나와서 무의미하다. 이를 방지하기 위함이다.
            return _rangeAbs % _word.length();
        }
    }
    private int getDirection(int _range, int _inputDirection){

        //R을 1로 보고, L을 0으로 생각한다. 또 이동범위가 양수면 1, 음수면 0으로 본다.
        //이 두 결과를 XOR연산한다음, 결과를 역전시킨다(0은 1로, 1은 0으로)
        //그러면 사용자 입력2, 3의 결과가 최종적으로 어떤 방향으로 미는건지 알 수 있다. 1이면 오른쪽으로 이동
        int _p1 = (_range >= 0)? 1 : 0;//양수면 1, 음수면 0
        int _p2 = (_inputDirection == 'R' || _inputDirection == 'r')? 1 : 0;//오른쪽이면 1, 왼쪽이면 0

        return ((_p1 ^ _p2) == 1)? 0 : 1 ;
    }
    private void pushString(String _word, int _realRange, int _realDirection){
        ArrayList<Character> _charArr = new ArrayList<Character>();
        for(int i = 0 ; i < _word.length(); i++){
            _charArr.add(_word.charAt(i));
        }

        for(int i = 0 ; i < _realRange; i++){
            if(_realDirection == 1){//우로 푸시
                char tmp = _charArr.remove(_word.length() - 1);//맨 뒤에서 제거
                _charArr.add(0, tmp);//맨 앞에 추가
            }else{//좌로 푸시
                char tmp = _charArr.remove(0);//맨 앞에서 제거
                _charArr.add(_word.length() - 1, tmp);//맨 뒤에 추가
            }
        }
        for(char ch : _charArr){
            System.out.print(ch);
        }
    }

}

