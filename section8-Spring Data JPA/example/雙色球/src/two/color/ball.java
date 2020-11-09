package two.color;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 1.用戶選擇隨機號還是自選號碼
 * 2.接收用戶選擇的號碼(紅6、藍1)
 * 3.系統產生的號碼(紅6、藍1)
 * 4.比較系統號碼跟用戶號碼,紀錄個數
 * 5.驗證是否中獎
 * 6.系統號碼排序
 * 7.公布結果
 */

public class ball {

    public static void main(String[] args){

        boolean start = true;
        while (start){
            //定義變量
            int[] userRedBall = new int[6];//用戶選擇的紅色號碼
            int[] sysRedBall = new int[6];//系統產生的籃球號碼
            int userBlueBall = 0;//用戶選擇的藍球號碼
            int sysBlueBall = 0;//系統產生的藍球號碼
            int redCount = 0;//紀錄用戶選擇的正確紅球數
            int blueCount = 0;//紀錄用戶選擇的正確藍球數
            int[] redBall = new int[33];//存取1-33的紅球號碼
            for(int i=0;i<redBall.length;i++){
                redBall[i] = i+1;
            }
            System.out.println("雙色球遊戲開始");
            System.out.println(("請問你要隨機還是自選號碼:(1)隨機(2)自選"));

            Scanner input = new Scanner(System.in);
            Random r = new Random();
            boolean flag = true;
            while(flag){
                int isAuto = input.nextInt();
                switch (isAuto){
                    case 1:
                        computerSelection(redBall,userRedBall);//隨機紅球
                        userBlueBall = r.nextInt(16)+1;//隨即藍球
                        flag = false;
                        break;
                    case 2:
                        System.out.println("請選擇六個紅球號碼(1-33):");
                        for (int i=0;i<userRedBall.length;i++){
                            userRedBall[i] = input.nextInt();
                        }
                        System.out.println("請選擇一個藍球號碼(1-16):");
                        userBlueBall = input.nextInt();
                        flag = false;
                        break;
                    default:
                        System.out.println(("請問你要隨機還是自選號碼:(1)隨機(2)自選"));
                        break;
                }
            }
            //系統隨機產生號碼
            //紅球
            computerSelection(redBall,sysRedBall);
            //藍球
            sysBlueBall = r.nextInt(16)+1;

            //統計結果
            //統計紅球
            for (int i=0;i<userRedBall.length;i++){
                for (int j=0;j<sysRedBall.length-redCount;j++){
                    if(userRedBall[i] == sysRedBall[j]){
                        int temp = sysRedBall[j];
                        sysRedBall[j] = sysRedBall[sysRedBall.length-1-redCount];
                        sysRedBall[sysRedBall.length-1-redCount] = temp;
                        redCount++;
                        break;
                    }
                }
            }
            //統計藍球
            if(userBlueBall == sysBlueBall){
                blueCount = 1;
            }

            //驗證是否中獎
            if (blueCount == 0 && redCount<=3){
                System.out.println("沒有中獎");
            } else if (blueCount == 1 && redCount < 3){
                System.out.println("中了六獎");
            } else if ((blueCount == 1 && redCount == 3) || (blueCount == 0 && redCount == 4)){
                System.out.println("中了五獎");
            } else if ((blueCount == 1 && redCount == 4) || (blueCount == 0 && redCount == 5)){
                System.out.println("中了四獎");
            } else if (blueCount == 1 && redCount == 5){
                System.out.println("中了三獎");
            } else if (blueCount == 0 && redCount == 6){
                System.out.println("中了二獎");
            } else if (blueCount == 1 && redCount == 6) {
                System.out.println("中了頭獎");
            } else {
                System.out.println("系統出錯");
            }

            //系統號碼
            System.out.println("紅球中獎號碼: ");
            sort(sysRedBall);
            System.out.println(Arrays.toString(sysRedBall));
            System.out.println("藍球中獎號碼: "+sysBlueBall);
            //用戶號碼
            System.out.println("你的紅球中獎號碼: ");
            sort(userRedBall);
            System.out.println(Arrays.toString(userRedBall));
            System.out.println("藍球中獎號碼: "+userBlueBall);

            System.out.println("是否還要繼續玩:(1)繼續玩 (2)不玩了");
            int restart = input.nextInt();
            switch (restart){
                case 1 :
                    start = true;
                    break;

                case 2 :
                    start = false;
                    System.out.println("再見");
                    break;
                default:
                    System.out.println("輸入錯誤請重新輸入");
                    System.out.println("是否還要繼續玩:(1)繼續玩 (2)不玩了");
                    restart = input.nextInt();
                    break;
            }
        }


    }
    //隨機生成六個數
    public static void computerSelection(int[] redBall,int[] userRedBall){

        Random r = new Random();
        int index = -1;
        for(int i=0;i<userRedBall.length;i++){
            index = r.nextInt(redBall.length-i);
            userRedBall[i] = redBall[index];
            int temp = redBall[index];
            redBall[index] = redBall[redBall.length-1-i];
            redBall[redBall.length-1-i] = temp;
        }
    }
    public static void sort(int[] ball){
        for(int i=0;i<ball.length-1;i++){
            for (int j=0;j<ball.length-1;j++){
                if (ball[j]>ball[j+1]){
                    ball[j] = ball[j]+ball[j+1];
                    ball[j+1] = ball[j]-ball[j+1];
                    ball[j] = ball[j]-ball[j+1];
                }
            }
        }
    }

}
