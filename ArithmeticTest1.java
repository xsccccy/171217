/*
 *Сѧ������������ϰ����ڶ���
 *ʹ�÷���
 *java ArithmeticTest2 ����� ������λ��
 *������涨���£��ӷ�+������-���˷�x����Ӣ����ĸx)������/ ������������r
 *�磺java ArithmeticTest2 x 1
 *    ��ʾ��һλ���ĳ˷�
 */
import java.util.Random;
import java.util.Date;
import java.io.*;
public class ArithmeticTest1
{
	public static void main (String[] args) throws Exception
    {
    	//numberOfDigit�����������λ��,score����û��÷�;
    	int operator,numberOfDigit,score=0;
    	String operation="";
    	final int ADDITION=0, SUBTRACTION=1,MULTIPLICATION=2,DIVISION=3,RANDOM=4;
    	final String OPERATION_STR[]={"+","-","��","��"}; 
    	final String OPERATION_STR_CH[]={"�ӷ�","����","�˷�","����","���"}; 
    	
    	//����questionAnswer�����Ŀ�ʹ𰸣�ÿһ�дӵ�һ��Ԫ�ص���7��Ԫ��
    	//���δ�ţ���һ������������������ڶ�������������ȷ�𰸡��������������û��𰸺��û������������
    	int questionAnswer[][]=new int[10][7];
    	int temp;
    	
    	//ʵ���������������
    	Random numberGenerator=new Random(new Date().getTime());
    	
    	//ʹ�÷�����ʾ
    	if (args.length<2)
    	{
    		System.out.println("ʹ�÷��� java ArithmeticTest1 [+|-|x|/|r] ������λ��");
    		System.out.println("�磺 java ArithmeticTest1 x 1");
    		System.out.println("��ʾ��1λ���ĳ˷���r��ʾÿ����Ŀ���������������");
    	    return;
    	}
    	
    	//��ȡ�����
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
     		System.out.println("��������Ӧ��Ϊ+-x/r֮һ�����������г���");
   			return;
    	}
    	
    	//��ȡ�û�ָ���Ĳ�����λ��
    	numberOfDigit=Integer.parseInt(args[1]);
    	System.out.println ("�������"+numberOfDigit+"λ��"+OPERATION_STR_CH[operator]+"������ϰ����10���⣬ÿ��10�֡�");
    	
    	//ʵ��������������
    	BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
    	
    	//������ʾ��Ŀ���������û�����Ĵ𰸣�ͳ�Ƶ÷�
    	for (int i = 0; i<10; i++) 
	    {
	    	//�����������������
	    	questionAnswer[i][0]=numberGenerator.nextInt((int)Math.pow(10,numberOfDigit));
	    	questionAnswer[i][2]=numberGenerator.nextInt((int)Math.pow(10,numberOfDigit));

	    	//����û�Ҫ����������������ͣ������������������
	    	if (operator==RANDOM)
	    		questionAnswer[i][1]=numberGenerator.nextInt(4);
	    	else
	    		questionAnswer[i][1]=operator;
	    		
	    	//��Ϊ������ȷ����������С�ڼ���	
	    	if(questionAnswer[i][1]==SUBTRACTION && questionAnswer[i][0]<questionAnswer[i][2])
	    	{
	    		temp=questionAnswer[i][0];
	    		questionAnswer[i][0]=questionAnswer[i][2];
	    		questionAnswer[i][2]=temp;
	    	}
	    	
	    	//��Ϊ������ȷ��������Ϊ0	
	    	if(questionAnswer[i][1]==DIVISION && questionAnswer[i][2]==0)
	    	{
	    		questionAnswer[i][2]=1;
	    	}
	    		

	    	//��ʾ��Ŀ�������û�����Ĵ�
		    String formatString="��%1$2d�⣺"+"%2$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%3$"+numberOfDigit+"d = ";
	    	
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
			    	System.out.print("��=");
			    	questionAnswer[i][5]=Integer.parseInt(keyboardIn.readLine());
			    	System.out.print("����=");
			    	questionAnswer[i][6]=Integer.parseInt(keyboardIn.readLine());
			    	if(questionAnswer[i][3]==questionAnswer[i][5] && questionAnswer[i][4]==questionAnswer[i][6])
			    	{
			    		score+=10;
			    	}
			    	break;
	    	}
	    }
	    //�رռ���������
	    keyboardIn.close();
	    
	    //�����������ϰ�����formatString����printf()�������ʽ��
	    System.out.println ();
    	System.out.println("��ϰ������£����һ������Ĵ�");
    	System.out.println ("_______________________________________");
	    for (int i = 0; i<10; i++) 
	    {
	    	if(questionAnswer[i][1]!=3)
	    	{
			    String formatString="%1$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%2$"+numberOfDigit+"d = %3$"+(numberOfDigit+2)+"d %4$"+(numberOfDigit+2)+"d\n";
		    	System.out.printf(formatString,questionAnswer[i][0],questionAnswer[i][2],questionAnswer[i][3],questionAnswer[i][5]);//ʹ�ø�ʽ���
	    	}
	    	else
	    	{
			    String formatString="%1$"+numberOfDigit+"d"+OPERATION_STR[questionAnswer[i][1]] +"%2$"+numberOfDigit+"d = ";
				formatString=formatString+"��%3$"+numberOfDigit+"d ����%4$"+numberOfDigit+"d �����%5$"+numberOfDigit+"d �������%6$"+numberOfDigit+"d\n";
		    	System.out.printf(formatString,questionAnswer[i][0],questionAnswer[i][2],questionAnswer[i][3],questionAnswer[i][4],questionAnswer[i][5],questionAnswer[i][6]);//ʹ�ø�ʽ���
	    	}
	    }
    	System.out.println ("_______________________________________");
	    System.out.println ("�㱾����ϰ�ĵ÷��ǣ�"+score);
    }
}