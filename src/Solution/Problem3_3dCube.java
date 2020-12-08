package Solution;

import java.util.Scanner;

enum CubeSide{
    U,F,R,B,L,D,
}

public class Problem3_3dCube implements MyRunnable{

    @Override
    public void runProblem() {
        char[][][] m_cube3D ={
                {//U
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'}
                },
                {//F
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'}
                },
                {//R
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'}
                },
                {//B
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'}
                },
                {//L
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'}
                },
                {//D
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'}
                }

        };

        Scanner sc = new Scanner(System.in);
        String inputedCmd;
        advPrintCube(m_cube3D);
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
                processCmd(currentCmd, m_cube3D);//현재 명령어 처리
                advPrintCube(m_cube3D);
            }
        }
    }
    private void processCmd(String currentCmd, char[][][] m_cube3D){
        switch (currentCmd){
            case "U" :
                rotateUpper(m_cube3D);
                break;
            case "U\'" :
                rotateUpperReverse(m_cube3D);
                break;
            case "L" :
                rotateLeft();
                break;
            case "L\'" :
                rotateLeftReverse(m_cube3D);
                break;
            case "F" :
                rotateFront();
                break;
            case "F\'" :
                rotateFrontReverse(m_cube3D);
                break;
            case "R" :
                rotateRight();
                break;
            case "R\'" :
                rotateRightReverse(m_cube3D);
                break;
            case "B" :
                rotateBack();
                break;
            case "B\'" :
                rotateBackReverse(m_cube3D);
                break;
            case "D" :
                rotateDown();
                break;
            case "D\'" :
                rotateDownReverse(m_cube3D);
                break;
        }

    }

    private void advPrintCube(char[][][] m_cube3D){
        for(int i = 0 ; i < 3; i++){
            System.out.printf("\t\t\t");
            for(int j = 0 ; j < 3; j++){
                System.out.print(m_cube3D[CubeSide.U.ordinal()][i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0 ; i < 3; i++){
            for(int side = 1; side <= 4; side++){
                for(int j = 0 ; j < 3; j++){
                    System.out.print(m_cube3D[side][i][j] + " ");
                }
                System.out.printf("\t\t");
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0 ; i < 3; i++){
            System.out.printf("\t\t\t");
            for(int j = 0 ; j < 3; j++){
                System.out.print(m_cube3D[CubeSide.D.ordinal()][i][j] + " ");
            }
            System.out.println();
        }
    }
    private void rotateUpper(char[][][] m_cube3D){

    }
    private void rotateUpperReverse(char[][][] m_cube3D){

    }

    private void rotateLeft(char[][][] m_cube3D){

    }
    private void rotateLeftReverse(char[][][] m_cube3D){

    }

    private void rotateFront(char[][][] m_cube3D){

    }
    private void rotateFrontReverse(char[][][] m_cube3D){

    }

    private void rotateRight(char[][][] m_cube3D){

    }
    private void rotateRightReverse(char[][][] m_cube3D){

    }

    private void rotateBack(char[][][] m_cube3D){

    }
    private void rotateBackReverse(char[][][] m_cube3D){

    }

    private void rotateDown(char[][][] m_cube3D){

    }
    private void rotateDownReverse(char[][][] m_cube3D){

    }
}
