package zbjsf;
import java.util.Scanner;

public class Zbjsf {

	public static void main(String[] args) {
		//数据定义与输入
		double a,qnF,xF,q,R,xD,xW,qnD,qnW,qnL,xq,xj,yj;
		int NF=0,j;
		Scanner in = new Scanner(System.in);
		System.out.print("请输入相对挥发度a:");
		a = in.nextDouble();
		System.out.print("请输入进料流量qnF:");
		qnF = in.nextDouble();
		System.out.print("请输入进料易挥发组分摩尔分数xF:");
		xF = in.nextDouble();
		System.out.print("请输入热状态参数q:");
		q = in.nextDouble();
		System.out.print("请输入回流比R:");
		R = in.nextDouble();
		System.out.print("请输入顶部馏出液易挥发组分摩尔分数xD:");
		xD = in.nextDouble();
		System.out.print("请输入底部产出液易挥发组分摩尔分数xW:");
		xW = in.nextDouble();
		//输入数据的检查
		if( a < 0 || qnF < 0 || xF < 0 || xF > 1 || xD < 0 || xD > 1 || xW < 0 || xW > 1 || R < 0 )
		{
			System.out.println("请检查你输入的数据是否有误！");
		}else {
			qnD = qnF*((xF/xW)-1)/((xD/xW-1));
			qnL = R*qnD;
			qnW = qnF - qnD;
			xq = ((R + 1)*xF + (q - 1)*xD)/(R + q);
//			yq = (R*xF + q*xD)/(R + q);
			yj = xD;
			j = 1;
			while(true) {
				xj = yj/(a - (a-1)*yj);
				if(xj < xW) {
					System.out.println("进料板NF:" + NF + '\n' + "理论塔板数NT:" + (j-1));
					break;
				}else {
					if(xj < xq) {
						yj = ((qnL + q*qnF)/(qnL + q*qnF - qnW))*xj - (qnW/(qnL + q*qnF - qnW))*xW;
						j++;
					}else {
						yj = (R/(R + 1))*xj + xD/(R + 1);
						NF = j+1;
						j++;
					}
				}
			}
		}

	}

}
