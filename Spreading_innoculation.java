import java.io.*;
import java.util.*;
public class Spreading_innoculation{
	public static void main(String args[]){
		ArrayList<Integer> source=new ArrayList<Integer>();
		ArrayList<Integer> mid=new ArrayList<Integer>();
		ArrayList<Integer> intm=new ArrayList<Integer>();
		int no_of_nodes=Integer.parseInt(args[0]);
		int min=0;
		int max=no_of_nodes;
		double prob = 0.2;
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
			int node_rem=0;
			Random re=new Random();
			int start=re.nextInt(max);
			nod[start].src=1;
			for(int i=0;i<msg_size;i++)
				nod[start].msg[i]=1;
			source.add(start);
			int t_1=0;
			//nod[start].k_stat=msg_size;
			System.out.println("Initial node selected");
			while(true){
				for(int i=0;i<source.size();i++){
					int s=source.get(i);
					max=no_of_nodes;
					min=0;
					//System.out.println("first element of the list: "+n_list.get(0));
					int t=re.nextInt(max);
					while(t==s){
						t=re.nextInt(max);
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
							//nod[t].k_stat++;
							if(k==msg_size-1)
								mid.add(t);
							intm.add(t);
							break;
						}
					}
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
					System.out.println("T1: "+round);
					t_1=round;
					s_t_1+=round;
					flg=1;
				}
				
				if(round==200){
					for(int i=0;i<no_of_nodes;i++){
						if(i==start)
							continue;
						if(Math.random() < prob){
							int j=0;
							for(;j<msg_size;j++){
								if(nod[i].msg[j]==0)
									break;
							}
							if(j==0)
								continue;
							else{
								if(j==msg_size-1)
									source.remove(new Integer(i));	
								nod[i].msg[j-1]=0;	
							}
						}	
					}
				}
				//System.out.println(round+"\t"+source.size());
				//System.out.println("-----------------------------------------");
				//if(round==Math.round(2.5*t_1))
				//	break;
				if(source.size()==no_of_nodes)
					break;
			}
			iteration++;
			s_t_b+=source.size()+node_rem;
			source.clear();
			//System.out.println("Time: "+round);
			for(int i=0;i<no_of_nodes;i++){
				for(int k=0;k<msg_size;k++)
					nod[i].msg[k]=0;
			}	
			System.out.println(s_t_1);			
		}
	s_t_1/=iteration;
	s_t_b/=iteration;
	System.out.println(no_of_nodes+"\t"+s_t_1);
	}
}
