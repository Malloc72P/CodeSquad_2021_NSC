package Solution;

import java.util.Scanner;

enum CubeSide{
    U,L,F,R,B,D,
}

public class Problem3_3dCube implements MyRunnable{

    /*
                    {//U
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'}
                },
                {//L
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'}
                },
                {//F
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'}
                },
                {//R
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'}
                },
                {//B
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'}
                },
                {//D
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'}
                }
    */
    @Override
    public void runProblem() {
        char[][][] m_cube3D ={
                {//U
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'},
                        {'W', 'W', 'W'}
                },
                {//L
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'},
                        {'O', 'O', 'O'}
                },
                {//F
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'},
                        {'G', 'G', 'G'}
                },
                {//R
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'},
                        {'R', 'R', 'R'}
                },
                {//B
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'},
                        {'B', 'B', 'B'}
                },
                {//D
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'},
                        {'Y', 'Y', 'Y'}
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
                rotateLeft(m_cube3D);
                break;
            case "L\'" :
                rotateLeftReverse(m_cube3D);
                break;
            case "F" :
                rotateFront(m_cube3D);
                break;
            case "F\'" :
                rotateFrontReverse(m_cube3D);
                break;
            case "R" :
                rotateRight(m_cube3D);
                break;
            case "R\'" :
                rotateRightReverse(m_cube3D);
                break;
            case "B" :
                rotateBack(m_cube3D);
                break;
            case "B\'" :
                rotateBackReverse(m_cube3D);
                break;
            case "D" :
                rotateDown(m_cube3D);
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
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.L.ordinal()][0], 0, tmp, 0, 3);
        for(int side = 1; side <= 3; side++){
            System.arraycopy(m_cube3D[side + 1][0], 0, m_cube3D[side][0], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.B.ordinal()][0], 0, 3);
    }
    private void rotateUpperReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.B.ordinal()][0], 0, tmp, 0, 3);
        for(int side = 4; side >= 2; side--){
            System.arraycopy(m_cube3D[side - 1][0], 0, m_cube3D[side][0], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.L.ordinal()][0], 0, 3);
    }

    private void rotateLeft(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.B.ordinal()][row][2];
        }
        int[] tgts = { CubeSide.U.ordinal(), CubeSide.F.ordinal(), CubeSide.D.ordinal(), CubeSide.B.ordinal() };//tgts = targets
        for(int i = 3 ; i >= 1; i--){
            for(int row = 0; row < 3; row++){
                if( i == 3 ){
                    m_cube3D[tgts[i]][row][2] = m_cube3D[tgts[i - 1]][row][0];
                    continue;
                }
                m_cube3D[tgts[i]][row][0] = m_cube3D[tgts[i - 1]][row][0];
            }
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][row][0] = tmp[row];
        }
    }
    private void rotateLeftReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.U.ordinal()][row][0];
        }
        int[] tgts = { CubeSide.U.ordinal(), CubeSide.F.ordinal(), CubeSide.D.ordinal(), CubeSide.B.ordinal() };//tgts = targets
        for(int i = 0 ; i < 3; i++){
            for(int row = 0; row < 3; row++){
                if( i+1 == 3 ){
                    m_cube3D[tgts[i]][row][0] = m_cube3D[tgts[i + 1]][row][2];
                    continue;
                }
                m_cube3D[tgts[i]][row][0] = m_cube3D[tgts[i + 1]][row][0];
            }
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][row][2] = tmp[row];
        }
    }

    private void rotateFront(char[][][] m_cube3D){
        //Front랑 Back이 회전하면 Upper, Right, Down, Left가 영향받는다.
        /*Left 복사
        Upper의 2행 0,1,2열 -> Right의 0,1,2행 0열
        Right의 0,1,2행 0열 -> Down의 0행 0,1,2열
        Down의 0행 0,1,2열 -> Left의 0,1,2행 2열
        Left의 0,1,2행 2열 -> Upper의 2행 0,1,2열
        * */
        char[] tmp = new char[3];
        for(int row = 0 ; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.R.ordinal()][row][0];
        }
        for(int i = 0; i < 3 ; i++){ // Upper To Right
            m_cube3D[CubeSide.R.ordinal()][i][0] = m_cube3D[CubeSide.U.ordinal()][2][i];
        }
        for(int i = 0; i < 3 ; i++){ // Left to Upper
            m_cube3D[CubeSide.U.ordinal()][2][i] = m_cube3D[CubeSide.L.ordinal()][i][2];
        }
        for(int i = 0; i < 3 ; i++){ // Down to Left
            m_cube3D[CubeSide.L.ordinal()][i][2] = m_cube3D[CubeSide.D.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Down
            m_cube3D[CubeSide.D.ordinal()][0][i] = tmp[i];
        }
    }
    private void rotateFrontReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0 ; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.R.ordinal()][row][0];
        }
        for(int i = 0; i < 3 ; i++){ // Down To Right
            m_cube3D[CubeSide.R.ordinal()][i][0] = m_cube3D[CubeSide.D.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // Left to Down
            m_cube3D[CubeSide.D.ordinal()][0][i] = m_cube3D[CubeSide.L.ordinal()][i][2];
        }
        for(int i = 0; i < 3 ; i++){ // Upper to Left
            m_cube3D[CubeSide.L.ordinal()][i][2] = m_cube3D[CubeSide.U.ordinal()][2][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Right
            m_cube3D[CubeSide.U.ordinal()][2][i] = tmp[i];
        }
    }

    private void rotateRight(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.U.ordinal()][row][2];
        }
        int[] tgts = { CubeSide.U.ordinal(), CubeSide.F.ordinal(), CubeSide.D.ordinal(), CubeSide.B.ordinal() };//tgts = targets
        for(int i = 0 ; i < 3; i++){
            for(int row = 0; row < 3; row++){
                if( i+1 == 3 ){
                    m_cube3D[tgts[i]][row][2] = m_cube3D[tgts[i + 1]][row][0];
                    continue;
                }
                m_cube3D[tgts[i]][row][2] = m_cube3D[tgts[i + 1]][row][2];
            }
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][row][0] = tmp[row];
        }
    }
    private void rotateRightReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.B.ordinal()][row][0];
        }
        int[] tgts = { CubeSide.U.ordinal(), CubeSide.F.ordinal(), CubeSide.D.ordinal(), CubeSide.B.ordinal() };//tgts = targets
        for(int i = 3 ; i >= 1; i--){
            for(int row = 0; row < 3; row++){
                if( i == 3 ){
                    m_cube3D[tgts[i]][row][0] = m_cube3D[tgts[i - 1]][row][2];
                    continue;
                }
                m_cube3D[tgts[i]][row][2] = m_cube3D[tgts[i - 1]][row][2];
            }
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][row][2] = tmp[row];
        }
    }

    private void rotateBack(char[][][] m_cube3D){

    }
    private void rotateBackReverse(char[][][] m_cube3D){
        
    }

    private void rotateDown(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.B.ordinal()][2], 0, tmp, 0, 3);
        for(int side = 4; side >= 2; side--){
            System.arraycopy(m_cube3D[side - 1][2], 0, m_cube3D[side][2], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.L.ordinal()][2], 0, 3);
    }
    private void rotateDownReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.L.ordinal()][2], 0, tmp, 0, 3);
        for(int side = 1; side <= 3; side++){
            System.arraycopy(m_cube3D[side + 1][2], 0, m_cube3D[side][2], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.B.ordinal()][2], 0, 3);
    }
}
