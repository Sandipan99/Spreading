k=4;
d=4;
a0=zeros(100,1);
a1=zeros(100,1);
a2=zeros(100,1);
a3=zeros(100,1);
a4=zeros(100,1);
a0(1)=d;
a1(1)=0;
a2(1)=0;
a3(1)=0;
a4(1)=1;
for i=2:100
    s=0;
    for j=1:i-1
        s=s+((1/k)*a3(j));
    end
    a4(i)=s;
    %end
    a3(i)=((k-1)/k)*a3(i-1)+(1/k)*a2(i-1);
    a2(i)=((k-1)/k)*a2(i-1)+(1/k)*a1(i-1);
    a1(i)=((k-1)/k)*a1(i-1)+(1/k)*a0(i-1);
    a0(i)=((k-1)/k)*a3(i-1)+((k-1)/k)*a0(i-1);
end

plot(a4,'b');
hold on;
fs=fopen('regular_tree_10_4_s','w');
for i=1:100
    fprintf(fs,'%d\t%6.2f\n',i,a4(i));
end
fclose(fs);
