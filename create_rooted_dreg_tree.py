import networkx as nx
import sys
#import matplotlib.pyplot as plt
n=10000
d=5
count=1
#G=nx.Graph()
ft=open("1_5-regular_tree","w")
source=[]
intm=[]
start=0
for i in xrange(1,d):
	source.append(i)
	ft.write(str(start)+"\t"+str(i))
#	G.add_edge(start,i)
	ft.write("\n")
count=d-1
flag1=0
flag2=0
while(count<n):
	for i in source:
		for j in xrange(d-1):
			count+=1
			ft.write(str(i)+"\t"+str(count))
			#G.add_edge(i,count)
			ft.write("\n")
			intm.append(count)
			if(count==n-1):
				flag1=1
				break
		if(flag1==1):
			flag2=1
			break
	if(flag2==1):
		break
	source=[]
	for i in intm:
		source.append(i)
	intm=[]
ft.close()
#nx.draw(G)
#plt.show()		

