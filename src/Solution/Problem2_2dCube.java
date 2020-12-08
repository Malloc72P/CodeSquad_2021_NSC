package Solution;

import java.util.Scanner;

public class Problem2_2dCube implements MyRunnable{

    @Override
    public void runProblem() {
        char[][] m_cube2D = {
                {'R', 'R', 'W'},
                {'G', 'C', 'W'},
                {'G', 'B', 'B'}
        };
        Scanner sc = new Scanner(System.in);
        String inputedCmd;
        printCube(m_cube2D);
        while(true){
            System.out.print("CUBE> ");
            inputedCmd = sc.nextLine().toUpperCase();
            if(inputedCmd.equals("Q")){
                System.out.println("Bye~");
                break;
            }

            //명령 파싱
            for (int i = 0 ; i < inputedCmd.length(); i++){
                String currentCmd = Character.toString(inputedCmd.charAt(i));
                if(i + 1 != inputedCmd.length()){
                    //다음 문자가 존재한다면,
                    if(inputedCmd.charAt(i+1) == '\''){//다음문자가 따옴표면, 현재명령에 붙인다
                        currentCmd += '\'';
                        i++;//인덱스 한칸 더 전진
                    }
                }
                System.out.println(currentCmd);
                processCmd(currentCmd, m_cube2D);//현재 명령어 처리
                printCube(m_cube2D);
            }
        }
    }
    private void processCmd(String currentCmd, char[][] m_cube2D){
        switch (currentCmd){
            case "U" :
                pushUpperToLeft(m_cube2D);
                break;
            case "U\'" :
                pushUpperToRight(m_cube2D);
                break;
            case "R" :
                pushRightToTop(m_cube2D);
                break;
            case "R\'" :
                pushRightToBottom(m_cube2D);
                break;
            case "L" :
                pushLeftToBottom(m_cube2D);
                break;
            case "L\'" :
                pushLeftToTop(m_cube2D);
                break;
            case "B" :
                pushBottomToRight(m_cube2D);
                break;
            case "B\'" :
                pushBottomToLeft(m_cube2D);
                break;
        }

    }
    private void printCube(char[][] m_cube2D){
        for(int i = 0 ; i < 3; i++){
            for(int j = 0 ; j < 3; j++){
                System.out.print(m_cube2D[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private void pushUpperToLeft(char[][] m_cube2D){//U
        char tmp = m_cube2D[0][0];
        m_cube2D[0][0] = m_cube2D[0][1];
        m_cube2D[0][1] = m_cube2D[0][2];
        m_cube2D[0][2] = tmp;
    }
    private void pushUpperToRight(char[][] m_cube2D){//U'
        char tmp = m_cube2D[0][2];
        m_cube2D[0][2] = m_cube2D[0][1];
        m_cube2D[0][1] = m_cube2D[0][0];
        m_cube2D[0][0] = tmp;
    }
    private void pushRightToTop(char[][] m_cube2D){//R
        char tmp = m_cube2D[0][2];
        m_cube2D[0][2] = m_cube2D[1][2];
        m_cube2D[1][2] = m_cube2D[2][2];
        m_cube2D[2][2] = tmp;
    }
    private void pushRightToBottom(char[][] m_cube2D){//R'
        char tmp = m_cube2D[2][2];
        m_cube2D[2][2] = m_cube2D[1][2];
        m_cube2D[1][2] = m_cube2D[0][2];
        m_cube2D[0][2] = tmp;
    }
    private void pushLeftToBottom(char[][] m_cube2D){//L
        char tmp = m_cube2D[2][0];
        m_cube2D[2][0] = m_cube2D[1][0];
        m_cube2D[1][0] = m_cube2D[0][0];
        m_cube2D[0][0] = tmp;
    }
    private void pushLeftToTop(char[][] m_cube2D){//L'
        char tmp = m_cube2D[0][0];
        m_cube2D[0][0] = m_cube2D[1][0];
        m_cube2D[1][0] = m_cube2D[2][0];
        m_cube2D[2][0] = tmp;
    }
    private void pushBottomToRight(char[][] m_cube2D){//B
        char tmp = m_cube2D[2][2];
        m_cube2D[2][2] = m_cube2D[2][1];
        m_cube2D[2][1] = m_cube2D[2][0];
        m_cube2D[2][0] = tmp;
    }
    private void pushBottomToLeft(char[][] m_cube2D){//B'
        char tmp = m_cube2D[2][0];
        m_cube2D[2][0] = m_cube2D[2][1];
        m_cube2D[2][1] = m_cube2D[2][2];
        m_cube2D[2][2] = tmp;
    }
}
