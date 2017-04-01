import java.io.*;
import java.util.*;
public class Spreading{
	public static void main(String args[]){
		ArrayList<Integer> source=new ArrayList<Integer>();
		ArrayList<Integer> mid=new ArrayList<Integer>();
		ArrayList<Integer> intm=new ArrayList<Integer>();
		int no_of_nodes=1000;
		int min=0;
		int max=no_of_nodes;
		int msg_size=4;
		int round=0;
		int status[]=new int[msg_size+1];
		Node nod[]=new Node[no_of_nodes];
		for(int i=0;i<no_of_nodes;i++){
			nod[i]=new Node();
			nod[i].msg=new int[msg_size];
			nod[i].id=i;
		}
		try{
			FileInputStream fs=new FileInputStream("scalefree_50");
			DataInputStream in=new DataInputStream(fs);
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			String strLine;
			String temp[];
			while((strLine=br.readLine())!=null){
				temp=strLine.split("\t");
				int x=Integer.parseInt(temp[0]);
				int y=Integer.parseInt(temp[1]);
				nod[x].neighbor.add(y);
				nod[y].neighbor.add(x);
			}
			fs.close();
		}catch(Exception e){
			System.out.println("Error is: "+e);
		}
		Random re=new Random();
		int start=re.nextInt(max);
		nod[start].src=1;
		for(int i=0;i<msg_size;i++)
			nod[start].msg[i]=1;
		source.add(start);
		nod[start].k_stat=msg_size;
		while(true){
			for(int i=0;i<source.size();i++){
				int s=source.get(i);
				max=nod[s].neighbor.size()-1;
				min=0;
				int randNum=re.nextInt(max);
				//System.out.println(randNum);
				int t=nod[s].neighbor.get(randNum);
				if(intm.contains(s))
					continue;
				if(intm.contains(t)){
					intm.add(s);
					continue;
				}
				for(int k=0;k<msg_size;k++){
					if((nod[s].msg[k]==1)&&(nod[t].msg[k]==0)){
						nod[t].msg[k]=1;
						nod[t].k_stat++;
						if(k==msg_size-1)
							mid.add(t);
						intm.add(t);
						break;
					}
				}
			}
			round++;
			for(int i=0;i<mid.size();i++)
				source.add(mid.get(i));
			for(int i=0;i<no_of_nodes;i++)
				status[nod[i].k_stat]++;
			String str1=Integer.toString(round)+"\t";
			for(int i=0;i<msg_size+1;i++)
				str1+=Integer.toString(status[i])+"\t";
			System.out.println(str1);
			for(int i=0;i<msg_size+1;i++)
				status[i]=0;	
			mid.clear();
			intm.clear();
			//System.out.println(round+"\t"+source.size());
			if(source.size()==no_of_nodes)
				break;
		}
	//System.out.println("Time: "+round);
	}
}
