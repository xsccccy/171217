/*
 *小学生四则运算练习软件第二版
 *使用方法
 *java ArithmeticTest2 运算符 运算数位数
 *运算符规定如下：加法+，减法-，乘法x（用英文字母x)，除法/ 随机产生运算符r
 *如：java ArithmeticTest2 x 1
 *    表示做一位数的乘法
 */
import java.util.Random;
import java.util.Date;
import java.io.*;
public class ArithmeticTest1
{
	public static void main (String[] args) throws Exception
    {
    	//numberOfDigit存放运算数的位数,score存放用户得分;
    	int operator,numberOfDigit,score=0;
    	String operation="";
    	final int ADDITION=0, SUBTRACTION=1,MULTIPLICATION=2,DIVISION=3,RANDOM=4;
    	final String OPERATION_STR[]={"+","-","×","÷"}; 
    	final String OPERATION_STR_CH[]={"加法","减法","乘法","除法","随机"}; 
    	
    	//数组questionAnswer存放题目和答案，每一行从第一个元素到第7个元素
    	//依次存放：第一个操作数、运算符、第二个操作数、正确答案、除法的余数、用户答案和用户输入除法余数
    	int questionAnswer[][]=new int[10][7];
    	int temp;
    	
    	//实例化随机数发生器
    	Random numberGenerator=new Random(new Date().getTime());
    	
    	//使用方法提示
    	if (args.length<2)
    	{
    		System.out.println("使用方法 java ArithmeticTest1 [+|-|x|/|r] 操作数位数");
    		System.out.println("如： java ArithmeticTest1 x 1");
    		System.out.println("表示做1位数的乘法。r表示每道题目的运算类型随机。");
    	    return;
    	}
    	
    	//获取运算符
    	if (args[0].trim().toUpperCase().equals("+"))
    		operator=ADDITION;
    	else if (args[0].trim().toUpperCase().equals("-"))
    		operator=SUBTRACTION;
    	else if (args[0].trim().toUpperCase().equals("X"))
    		operator=MULTIPLICATION;
    	else if (args[0].trim().toUpperCase().equals("/"))
    		operator=DIVISION;
    	else if (args[0].trim().toUpperCase().equals("R")) 
    		operator=RANDOM;
    	else
    	{
     		System.out.println("运算类型应该为+-x/r之一，请重新运行程序。");
   			return;
    	}
    	
    	//获取用户指定的操作数位数
    	numberOfDigit=Integer.parseInt(args[1]);
    	System.out.println ("下面进行"+numberOfDigit+"位数"+OPERATION_STR_CH[operator]+"运算练习，共10道题，每题10分。");
    	
    	//实例化键盘输入流
    	BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
    	
    	//生成显示题目，并保存用户输入的答案，统计得分
    	for (int i = 0; i<10; i++) 
	    {
	    	//随机生成两个操作数
	    	questionAnswer[i][0]=numberGenerator.nextInt((int)Math.pow(10,numberOfDigit));
	    	questionAnswer[i][2]=numberGenerator.nextInt((int)Math.pow(10,numberOfDigit));

	    	//如果用户要求随机生成运算类型，则随机生成运算类型
	    	if (operator==RANDOM)
	    		questionAnswer[i][1]=numberGenerator.nextInt(4);
	    	else
	    		questionAnswer[i][1]=operator;
	    		
	    	//若为减法，确保被减数不小于减数	
	    	if(questionAnswer[i][1]==SUBTRACTION && questionAnswer[i][0]<questionAnswer[i][2])
	    	{
	    		temp=questionAnswer[i][0];
	    		questionAnswer[i][0]=questionAnswer[i][2];
	    		questionAnswer[i][2]=temp;
	    	}
	    	
	    	//若为除法，确保除数不为0	
	    	if(questionAnswer[i][1]==DIVISION && questionAnswer[i][2]==0)
	    	{
	    		questionAnswer[i][2]=1;
	    	}
	    		

	    	//显示题目，接收用户输入的答案
		    String formatString="第%1$2d题："+"%2$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%3$"+numberOfDigit+"d = ";
	    	
    		System.out.printf(formatString,i+1,questionAnswer[i][0],questionAnswer[i][2]);
	    	switch (questionAnswer[i][1])
	    	{
		    	case ADDITION:
			    	questionAnswer[i][3]=questionAnswer[i][0]+questionAnswer[i][2];
			    	questionAnswer[i][5]=Integer.parseInt(keyboardIn.readLine());
			    	if(questionAnswer[i][3]==questionAnswer[i][5])
			    	{
			    		score+=10;
			    	}
			    	break;
		    	case SUBTRACTION:
			    	questionAnswer[i][3]=questionAnswer[i][0]-questionAnswer[i][2];
			    	questionAnswer[i][5]=Integer.parseInt(keyboardIn.readLine());
			    	if(questionAnswer[i][3]==questionAnswer[i][5])
			    	{
			    		score+=10;
			    	}
			    	break;
		    	case MULTIPLICATION:
			    	questionAnswer[i][3]=questionAnswer[i][0]*questionAnswer[i][2];
			    	questionAnswer[i][5]=Integer.parseInt(keyboardIn.readLine());
			    	if(questionAnswer[i][3]==questionAnswer[i][5])
			    	{
			    		score+=10;
			    	}
			    	break;
		    	case DIVISION:
			    	questionAnswer[i][3]=questionAnswer[i][0]/questionAnswer[i][2];
			    	questionAnswer[i][4]=questionAnswer[i][0]%questionAnswer[i][2];
			    	System.out.println();
			    	System.out.print("商=");
			    	questionAnswer[i][5]=Integer.parseInt(keyboardIn.readLine());
			    	System.out.print("余数=");
			    	questionAnswer[i][6]=Integer.parseInt(keyboardIn.readLine());
			    	if(questionAnswer[i][3]==questionAnswer[i][5] && questionAnswer[i][4]==questionAnswer[i][6])
			    	{
			    		score+=10;
			    	}
			    	break;
	    	}
	    }
	    //关闭键盘输入流
	    keyboardIn.close();
	    
	    //输出完整的练习结果。formatString保存printf()的输出格式串
	    System.out.println ();
    	System.out.println("练习结果如下，最后一栏是你的答案");
    	System.out.println ("_______________________________________");
	    for (int i = 0; i<10; i++) 
	    {
	    	if(questionAnswer[i][1]!=3)
	    	{
			    String formatString="%1$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%2$"+numberOfDigit+"d = %3$"+(numberOfDigit+2)+"d %4$"+(numberOfDigit+2)+"d\n";
		    	System.out.printf(formatString,questionAnswer[i][0],questionAnswer[i][2],questionAnswer[i][3],questionAnswer[i][5]);//使用格式输出
	    	}
	    	else
	    	{
			    String formatString="%1$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%2$"+numberOfDigit+"d = ";
				formatString=formatString+"商%3$"+numberOfDigit+"d 余数%4$"+numberOfDigit+"d 你的商%5$"+numberOfDigit+"d 你的余数%6$"+numberOfDigit+"d\n";
		    	System.out.printf(formatString,questionAnswer[i][0],questionAnswer[i][2],questionAnswer[i][3],questionAnswer[i][4],questionAnswer[i][5],questionAnswer[i][6]);//使用格式输出
	    	}
	    }
    	System.out.println ("_______________________________________");
	    System.out.println ("你本次练习的得分是："+score);
    }
}