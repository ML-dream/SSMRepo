package job;

public class FoEndJudge {
	public static String foEndJudge(int num,int accept){
//		判断工序是否完工
		String state="进行中";
		if(accept >= num){
			state="完成";
		}else if(accept == 0 ){
			state = "未开工";	//有可能已经投产，但是还未质检
		}
		return state;
	}
}
