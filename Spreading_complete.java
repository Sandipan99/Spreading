import java.io.*;
import java.util.*;
public class Spreading_complete{
	public static void main(String args[]){
		ArrayList<Integer> source=new ArrayList<Integer>();
		ArrayList<Integer> mid=new ArrayList<Integer>();
		ArrayList<Integer> intm=new ArrayList<Integer>();
		ArrayList<Integer> n_list=new ArrayList<Integer>(); 
		int no_of_nodes=Integer.parseInt(args[0]);
		int min=0;
		int max=no_of_nodes;
		int msg_size=Integer.parseInt(args[1]);
		int status[]=new int[msg_size+1];
		Node_com nod[]=new Node_com[no_of_nodes];
		for(int i=0;i<no_of_nodes;i++){
			nod[i]=new Node_com();
			nod[i].msg=new int[msg_size];
			nod[i].id=i;
		}	

		int iteration=0;
		double s_t_b=0.0;
		double s_t_1=0.0;
		int round;
		while(iteration<100){
			round=0;
			int flg=0;
			Random re=new Random();
			int start=re.nextInt(max);
			nod[start].src=1;
			for(int i=0;i<msg_size;i++)
				nod[start].msg[i]=1;
			source.add(start);
			for(int i=0;i<no_of_nodes;i++){
				if(i!=start){
					for(int j=0;j<msg_size;j++)
						n_list.add(i);
				}
			}
			//nod[start].k_stat=msg_size;
			while(true){
				for(int i=0;i<source.size();i++){
					int s=source.get(i);
					//max=no_of_nodes;
					//min=0;
					Collections.shuffle(n_list);
					//System.out.println("first element of the list: "+n_list.get(0));
					int t=n_list.get(0);
					while(t==s){
						Collections.shuffle(n_list);
						t=n_list.get(0);
					}
					if(intm.contains(s))
						continue;
					if(intm.contains(t)){
						intm.add(s);
						continue;
					}
					//System.out.println("element selected");
					for(int k=0;k<msg_size;k++){
						if((nod[s].msg[k]==1)&&(nod[t].msg[k]==0)){
							nod[t].msg[k]=1;
							//System.out.println("size of the n_list: "+n_list.size());
							n_list.remove((Integer)t);
							//nod[t].k_stat++;
							if(k==msg_size-1)
								mid.add(t);
							intm.add(t);
							break;
						}
					}
					if(n_list.size()==0)
						break;
					//System.out.println("Message transmission complete");
				}
				round++;
				
				for(int i=0;i<mid.size();i++)
					source.add(mid.get(i));
				//for(int i=0;i<no_of_nodes;i++)
				//	status[nod[i].k_stat]++;
				//String str1=Integer.toString(round)+"\t";
				//for(int i=0;i<msg_size+1;i++)
				//	str1+=Integer.toString(status[i])+"\t";
				//System.out.println(str1);
				//for(int i=0;i<msg_size+1;i++)
				//	status[i]=0;	
				mid.clear();
				intm.clear();
				if((source.size()==2)&&(flg==0)){
					//System.out.println("T1: "+round);
					s_t_1+=round;
					flg=1;
				}
				//System.out.println(round+"\t"+source.size());
				//System.out.println("-----------------------------------------");
				if(source.size()==no_of_nodes)
					break;
			}
			iteration++;
			s_t_b+=round;
			source.clear();
			n_list.clear();
			//System.out.println("Time: "+round);
			for(int i=0;i<no_of_nodes;i++){
				for(int k=0;k<msg_size;k++)
					nod[i].msg[k]=0;
			}	
			//System.out.println(s_t_1+"\t"+s_t_b);			
		}
	s_t_1/=iteration;
	s_t_b/=iteration;
	System.out.println(no_of_nodes+"\t"+s_t_1+"\t"+s_t_b);
	}
}
