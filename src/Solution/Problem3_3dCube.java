package Solution;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

enum CubeSide{
    U,L,F,R,B,D,
}

public class Problem3_3dCube implements MyRunnable{
    final private String DO_SHUFFLE = "S";
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
    /*
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
    * */
    @Override
    public void runProblem() {
        int moveCounter = 0;
        boolean isFirstTime = true;
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
        long start_t = System.currentTimeMillis();
        while(true){
            if(!isFirstTime){
                //첫 시도가 아니면 모든면을 다 맞췄는지 검사한다.
                if(testCompletion(m_cube3D)){
                    long end_t = System.currentTimeMillis();
                    System.out.println("축하해요 모든 면을 맞추셨어요!");
                    printGoodbyeMsg(start_t, end_t, moveCounter);
                    return;
                }
            }
            printMenu();
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
                if(processCmd(currentCmd, m_cube3D))//현재 명령어 처리
                {
                    moveCounter++;
                    if(isFirstTime)
                        isFirstTime = false;
                }
                advPrintCube(m_cube3D);
            }
        }
        long end_t = System.currentTimeMillis();
        printGoodbyeMsg(start_t, end_t, moveCounter);
        
    }
    private void printGoodbyeMsg(long start_t, long end_t, int moveCounter){
        long exec_second = (end_t - start_t) / 1000;
        long exec_minute = exec_second / 60;
        exec_second -= exec_minute * 60;

        System.out.printf("경과시간: %02d:%02d \n", exec_minute, exec_second);
        System.out.println("조작갯수 : " + moveCounter);
        System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
    }
    private void printMenu(){
        System.out.println("_________________________________");
        System.out.println("S : 큐브 섞기");
        System.out.println("q or Q : 프로그램 종료");
        System.out.println("_________________________________");
    }
    private boolean testCompletion(char[][][] m_cube3D){
        for (int side = 0; side < 6; side++) {
            char currentColor = m_cube3D[side][0][0];
            for (int i = 0 ; i < 3; i++){
                for (int j = 0; j < 3; j++) {
                    if (m_cube3D[side][i][j] != currentColor)
                        return false;
                }
            }
        }
        return true;
    }
    private boolean processCmd(String currentCmd, char[][][] m_cube3D){
        switch (currentCmd){
            case "U" :
                rotateUpper(m_cube3D);
                return true;
            case "U\'" :
                rotateUpperReverse(m_cube3D);
                return true;
            case "L" :
                rotateLeft(m_cube3D);
                return true;
            case "L\'" :
                rotateLeftReverse(m_cube3D);
                return true;
            case "F" :
                rotateFront(m_cube3D);
                return true;
            case "F\'" :
                rotateFrontReverse(m_cube3D);
                return true;
            case "R" :
                rotateRight(m_cube3D);
                return true;
            case "R\'" :
                rotateRightReverse(m_cube3D);
                return true;
            case "B" :
                rotateBack(m_cube3D);
                return true;
            case "B\'" :
                rotateBackReverse(m_cube3D);
                return true;
            case "D" :
                rotateDown(m_cube3D);
                return true;
            case "D\'" :
                rotateDownReverse(m_cube3D);
                return true;
            case "S" :
                doShuFFle(m_cube3D);
                return false;
            default: return false;
        }

    }
    private void doShuFFle(char[][][] m_cube3D){
        int act = 0;
        ArrayList<String> shuffleSequence = new ArrayList<String>();
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        int number_of_shuffle = rand.nextInt(5);
        number_of_shuffle += 5;
        for (int i = 0; i < number_of_shuffle; i++){
            act = rand.nextInt(13);
            switch (act){
                case 0 :
                    shuffleSequence.add("U");
                    rotateUpper(m_cube3D);
                    break;
                case 1 :
                    shuffleSequence.add("U\'");
                    rotateUpperReverse(m_cube3D);
                    break;
                case 2 :
                    shuffleSequence.add("L");
                    rotateLeft(m_cube3D);
                    break;
                case 3 :
                    shuffleSequence.add("L\'");
                    rotateLeftReverse(m_cube3D);
                    break;
                case 4 :
                    shuffleSequence.add("F");
                    rotateFront(m_cube3D);
                    break;
                case 5 :
                    shuffleSequence.add("F\'");
                    rotateFrontReverse(m_cube3D);
                    break;
                case 6 :
                    shuffleSequence.add("R");
                    rotateRight(m_cube3D);
                    break;
                case 7 :
                    shuffleSequence.add("R\'");
                    rotateRightReverse(m_cube3D);
                    break;
                case 8 :
                    shuffleSequence.add("B");
                    rotateBack(m_cube3D);
                    break;
                case 9 :
                    shuffleSequence.add("B\'");
                    rotateBackReverse(m_cube3D);
                    break;
                case 10 :
                    shuffleSequence.add("D");
                    rotateDown(m_cube3D);
                    break;
                case 11 :
                    shuffleSequence.add("D\'");
                    rotateDownReverse(m_cube3D);
                    break;
            }
        }
        System.out.println("다음 순서로 셔플되었습니다.");
        for (int i = 0 ; i < shuffleSequence.size(); i++){
            System.out.print(shuffleSequence.get(i) + ", ");
        }
        System.out.println();
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

    private void rotateSide(char[][][] m_cube3D, int side){
        //copy side
        //upper to right
        //left to upper
        //down to left
        //tmp to down
        char[][] tmp = new char[3][3];
        for (int i = 0 ; i < 3; i++){//copy right
            System.arraycopy(m_cube3D[side][i], 0, tmp[i], 0, 3);
        }
        for (int i = 0 ; i < 3; i++){//upper to right
            m_cube3D[side][i][2] = tmp[0][i];
        }
        for (int i = 0 ; i < 3; i++){//left to upper
            m_cube3D[side][0][2 - i] = tmp[i][0];
        }
        for (int i = 0 ; i < 3; i++){//down to left
            m_cube3D[side][i][0] = tmp[2][i];
        }
        for (int i = 0 ; i < 3; i++){//right to down
            m_cube3D[side][2][2 - i] = tmp[i][2];
        }
    }
    private void rotateSideReverse(char[][][] m_cube3D, int side){
        //copy right
        //down to right
        //left to down
        //upper to left
        //tmp to upper
        char[][] tmp = new char[3][3];
        for (int i = 0 ; i < 3; i++){//copy right
            System.arraycopy(m_cube3D[side][i], 0, tmp[i], 0, 3);
        }
        for (int i = 0 ; i < 3; i++){//down to right
            m_cube3D[side][2 - i][2] = tmp[2][i];
        }
        for (int i = 0 ; i < 3; i++){//left to down
            m_cube3D[side][2][i] = tmp[i][0];
        }
        for (int i = 0 ; i < 3; i++){//upper to left
            m_cube3D[side][2 - i][0] = tmp[0][i];
        }
        for (int i = 0 ; i < 3; i++){//tmp to upper
            m_cube3D[side][0][i] = tmp[i][2];
        }
    }

    private void rotateUpper(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.L.ordinal()][0], 0, tmp, 0, 3);
        for(int side = 1; side <= 3; side++){
            System.arraycopy(m_cube3D[side + 1][0], 0, m_cube3D[side][0], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.B.ordinal()][0], 0, 3);
        rotateSide(m_cube3D, CubeSide.U.ordinal());
    }
    private void rotateUpperReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.B.ordinal()][0], 0, tmp, 0, 3);
        for(int side = 4; side >= 2; side--){
            System.arraycopy(m_cube3D[side - 1][0], 0, m_cube3D[side][0], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.L.ordinal()][0], 0, 3);
        rotateSideReverse(m_cube3D, CubeSide.U.ordinal());
    }

    private void rotateLeft(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.D.ordinal()][row][0];
        }
        //front to down
        //upper to front
        //back to upper
        //down to back
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.D.ordinal()][row][0] = m_cube3D[CubeSide.F.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.F.ordinal()][row][0] = m_cube3D[CubeSide.U.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][2 - row][0] = m_cube3D[CubeSide.B.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][2 - row][2] = tmp[row];
        }

        rotateSide(m_cube3D, CubeSide.L.ordinal());
    }
    private void rotateLeftReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.D.ordinal()][row][0];
        }
        //back to down
        //upper to back
        //front to upper
        //down to front
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.D.ordinal()][2 - row][0] = m_cube3D[CubeSide.B.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][2 - row][2] = m_cube3D[CubeSide.U.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][row][0] = m_cube3D[CubeSide.F.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.F.ordinal()][row][0] = tmp[row];
        }

        rotateSideReverse(m_cube3D, CubeSide.L.ordinal());
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
            m_cube3D[CubeSide.U.ordinal()][2][2 - i] = m_cube3D[CubeSide.L.ordinal()][i][2];
        }
        for(int i = 0; i < 3 ; i++){ // Down to Left
            m_cube3D[CubeSide.L.ordinal()][i][2] = m_cube3D[CubeSide.D.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Down
            m_cube3D[CubeSide.D.ordinal()][0][2 - i] = tmp[i];
        }
        rotateSide(m_cube3D, CubeSide.F.ordinal());
    }
    private void rotateFrontReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0 ; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.R.ordinal()][row][0];
        }
        for(int i = 0; i < 3 ; i++){ // Down To Right
            m_cube3D[CubeSide.R.ordinal()][2 - i][0] = m_cube3D[CubeSide.D.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // Left to Down
            m_cube3D[CubeSide.D.ordinal()][0][i] = m_cube3D[CubeSide.L.ordinal()][i][2];
        }
        for(int i = 0; i < 3 ; i++){ // Upper to Left
            m_cube3D[CubeSide.L.ordinal()][2 - i][2] = m_cube3D[CubeSide.U.ordinal()][2][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Upper
            m_cube3D[CubeSide.U.ordinal()][2][i] = tmp[i];
        }
        rotateSideReverse(m_cube3D, CubeSide.F.ordinal());
    }

    private void rotateRight(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.D.ordinal()][row][2];
        }
        //back to down
        //upper to back
        //front to upper
        //down to front
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.D.ordinal()][2 - row][2] = m_cube3D[CubeSide.B.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][2 - row][0] = m_cube3D[CubeSide.U.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][row][2] = m_cube3D[CubeSide.F.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.F.ordinal()][row][2] = tmp[row];
        }
        rotateSide(m_cube3D, CubeSide.R.ordinal());
    }
    private void rotateRightReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.D.ordinal()][row][2];
        }
        //front to down
        //upper to front
        //back to upper
        //down to back
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.D.ordinal()][row][2] = m_cube3D[CubeSide.F.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.F.ordinal()][row][2] = m_cube3D[CubeSide.U.ordinal()][row][2];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.U.ordinal()][2 - row][2] = m_cube3D[CubeSide.B.ordinal()][row][0];
        }
        for(int row = 0; row < 3; row++){
            m_cube3D[CubeSide.B.ordinal()][2 - row][0] = tmp[row];
        }
        rotateSideReverse(m_cube3D, CubeSide.R.ordinal());
    }

    private void rotateBack(char[][][] m_cube3D){
        char[] tmp = new char[3];
        for(int row = 0 ; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.R.ordinal()][row][2];
        }
        for(int i = 0; i < 3 ; i++){ // Down To Right
            m_cube3D[CubeSide.R.ordinal()][2 - i][2] = m_cube3D[CubeSide.D.ordinal()][2][i];
        }
        for(int i = 0; i < 3 ; i++){ // Left to Down
            m_cube3D[CubeSide.D.ordinal()][2][i] = m_cube3D[CubeSide.L.ordinal()][i][0];
        }
        for(int i = 0; i < 3 ; i++){ // Upper to Left
            m_cube3D[CubeSide.L.ordinal()][2 - i][0] = m_cube3D[CubeSide.U.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Upper
            m_cube3D[CubeSide.U.ordinal()][0][i] = tmp[i];
        }
        rotateSide(m_cube3D, CubeSide.B.ordinal());
    }
    private void rotateBackReverse(char[][][] m_cube3D){
        //Front랑 Back이 회전하면 Upper, Right, Down, Left가 영향받는다.
        /*Left 복사
        Upper의 0행 0,1,2열 -> Right의 0,1,2행 2열
        Right의 0,1,2행 2열 -> Down의 2행 0,1,2열
        Down의 2행 0,1,2열 -> Left의 0,1,2행 0열
        Left의 0,1,2행 0열 -> Upper의 0행 0,1,2열
        * */
        char[] tmp = new char[3];
        for(int row = 0 ; row < 3; row++){
            tmp[row] = m_cube3D[CubeSide.R.ordinal()][row][2];
        }
        for(int i = 0; i < 3 ; i++){ // Upper To Right
            m_cube3D[CubeSide.R.ordinal()][i][2] = m_cube3D[CubeSide.U.ordinal()][0][i];
        }
        for(int i = 0; i < 3 ; i++){ // Left to Upper
            m_cube3D[CubeSide.U.ordinal()][0][2 - i] = m_cube3D[CubeSide.L.ordinal()][i][0];
        }
        for(int i = 0; i < 3 ; i++){ // Down to Left
            m_cube3D[CubeSide.L.ordinal()][i][0] = m_cube3D[CubeSide.D.ordinal()][2][i];
        }
        for(int i = 0; i < 3 ; i++){ // tmp(Right) to Down
            m_cube3D[CubeSide.D.ordinal()][2][2 - i] = tmp[i];
        }
        rotateSideReverse(m_cube3D, CubeSide.B.ordinal());
    }

    private void rotateDown(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.B.ordinal()][2], 0, tmp, 0, 3);
        for(int side = 4; side >= 2; side--){
            System.arraycopy(m_cube3D[side - 1][2], 0, m_cube3D[side][2], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.L.ordinal()][2], 0, 3);
        rotateSide(m_cube3D, CubeSide.D.ordinal());
    }
    private void rotateDownReverse(char[][][] m_cube3D){
        char[] tmp = new char[3];
        System.arraycopy(m_cube3D[CubeSide.L.ordinal()][2], 0, tmp, 0, 3);
        for(int side = 1; side <= 3; side++){
            System.arraycopy(m_cube3D[side + 1][2], 0, m_cube3D[side][2], 0, 3);
        }
        System.arraycopy(tmp, 0, m_cube3D[CubeSide.B.ordinal()][2], 0, 3);
        rotateSideReverse(m_cube3D, CubeSide.D.ordinal());
    }
}
