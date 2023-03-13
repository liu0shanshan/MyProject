#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>//bool���� 
#define NAME_LEN 40
#define SCORE_CNT 3
#define STU_INFO_FILE "stu.dat"
//����ö��  ����
enum OPER{QUIT=0,ADD,DEL,FIND,FINDna,MOD,LIST};
//����ѧ���ṹ��  ���� 
 struct Stu
 {
 	int no;				  //ѧ�� 
 	char name[NAME_LEN];  //���� 
 	bool gender;		  //�Ա�    ture "��"   false "Ů"
 	int score[SCORE_CNT]; //�ɼ� 
 };

//����ȫ�ֱ������ڴ洢ѧ����Ϣ   ȫ�ֱ���  ���κκ����ж�����ֱ�ӷ��� 
struct Stu *pstus=NULL;   //ȫ�ֱ���  ָ��洢ѧ����Ϣ���ݵ��ڴ�
size_t capcity=10;        //������С  ����ܴ洢����ѧ��   
size_t size=0;            //Ŀǰ��ѧ������
//pstus��Ҫ��malloc/calloc�����ڴ棬 size>=capacity��Ҫ��realloc����

void init()
{
	pstus=calloc(capcity,sizeof(struct Stu));//����capacity��struct Stu�ڴ�
	if(pstus==NULL){
		printf("calloc memory failed\n");
		exit(-1);//�ڴ����ʧ��  ��Ŀֱ�ӽ��� 
	} 
} 
 
void showMenu(void)
{
	printf("****ѧ���ɼ���Ϣ����ϵͳ****\n");
	printf("** %d.����ѧ��  \n",ADD);
	printf("** %d.ɾ��ѧ��  \n",DEL);
	printf("** %d.����ѧ��  \n",FIND);
	printf("** %d.����ѧ������  \n",FINDna);
	printf("** %d.�޸�ѧ����Ϣ  \n",MOD);
	printf("** %d.�г�ѧ����Ϣ  \n",LIST);
	printf("** %d.�˳�  \n",QUIT);
	printf(">>>>");
}

/*
	�������ļ���
		������Ҫ���浽�ļ���
		��1����Ŀ����ʱ�����ļ��м�������
		��2�������˳�֮ǰ�����ڴ������ݱ��浽�ļ���
*/

void load(void)
{
	printf("-------------------------\n");
	printf("��������ʱ����������....\n");
	FILE *fp = fopen(STU_INFO_FILE,"r");
	if(fp == NULL){
		printf("fopen failed!\n");
		//exit(-1);
	}
	int cnt = 0,i = 0;
	fread(&cnt,sizeof(int),1,fp);
	for(i=0;i<cnt;i++)
	{
		struct Stu s ={};
		fread(&s,sizeof(struct Stu),1,fp);
		addStuToMem(s);
		showStu(s);
	}
	fclose(fp);
	printf("-------------------------\n");
}

void save(void)
{
	printf("----------------------------\n");
	printf("�����˳�֮ǰ��Ҫ��������....\n");
	FILE *fp = fopen(STU_INFO_FILE,"w");
	if(fp == NULL)
	{
		printf("fopen failed!\n");
		//exit(-1);
	}
	fwrite(&size,sizeof(int),1,fp);
	int i;
	for(i=0;i<size;i++)
	{
		fwrite(pstus+i,sizeof(struct Stu),1,fp);
	}
	fclose(fp);
	printf("----------------------------\n");
}
void addStuToMem(struct Stu s)
{
	if(size>=capcity)
	{//�ڴ治��  ���� 
		capcity = 2*capcity;//2������ 
		pstus=realloc(pstus,capcity*sizeof(struct Stu));
		if(pstus==NULL)
		{
			printf("realloc memory failed!\n");
			exit(-1);//�˳���Ŀ 
		}
	}
	pstus[size]=s;//������±�Ϊsize���ڴ��� 
	++size;//ѧ��������1 
}
void addStu(void)
{
	printf("Add stduent.\n");
	struct Stu s={};
	printf("������ѧ�ţ�");
	scanf("%d",&s.no);
	printf("������������");
	scanf("%s",s.name);
	printf("�������Ա�(1:��,0Ů)��");
	int gender=0;
	scanf("%d",&gender);
	s.gender=gender!=0;
	int i;
	for(i=0;i<SCORE_CNT;i++)
	{
		printf("�������%d�Ź��εĳɼ���",i+1);
		scanf("%d",&s.score[i]);
	} 
	addStuToMem(s);
	printf("Add Stu Success!\n");
}

int findStuByNo(int no)
{
	int i;
	for(i=0;i<size;i++)
	{
		if(pstus[i].no==no)
		{
			return i;
		}
	}
	return -1;
}
void showStu(struct Stu s)
{
	printf("ѧ��:%d ����:%s �Ա�:%s �ɼ�:",s.no,s.name,s.gender?"��":"Ů");
	int j;
	for(j=0;j<SCORE_CNT;j++)
	{
		printf("%3d",s.score[j]);
	}
	printf("\n");
}
void delStu(void)
{
	printf("Delelet student.\n");
	int no=0;
	printf("������Ҫɾ��ѧ����ѧ�ţ�");
	scanf("%d",&no);
	int pos=findStuByNo(no);//ͨ��ѧ��no���Ҹ�ѧ�����ڵ�λ�ã��±꣩
	 if(pos>=0)
	 {
	 	showStu(pstus[pos]);//ɾ��֮ǰ  ��ʾһ��
		 pstus[pos]=pstus[size-1];
		 --size;
		 printf("Delete the %d student success!\n",no);
	 }else
	 {
	 	printf("���޴�(ѧ��:%d)ѧ�����ڣ�\n",no);
	 }
}

int findStuByN(char name[])
{
	int i;
	for(i=0;i<size;i++)
	{	
		//printf("%d",i);
		if(strcmp(pstus[i].name,name) == 0)
		{
			return i;
		}else printf("û����") ;
	}
	return -1;
}

void findStuN(void)
{
	printf("Find student name.\n");
	char name[NAME_LEN];
	printf("������Ҫ����ѧ����������");
	scanf("%s",&name);
	int pos=findStuByN(name);
	//printf("%d",pos);
	 if(pos>=0)
	 {
	 	printf("��ѧ����Ϣ����:\n\t");
	 	showStu(pstus[pos]);
	 }else
	 {
	 	printf("���޴�(����:%s)ѧ�����ڣ�\n",name);
	 }
}
void findStu(void)
{
	printf("Find student number.\n");
	int no=0;
	printf("������Ҫ����ѧ����ѧ�ţ�");
	scanf("%d",&no);
	int pos=findStuByNo(no);
	 if(pos>=0)
	 {
	 	printf("��ѧ����Ϣ����:\n\t");
	 	showStu(pstus[pos]);
	 }else
	 {
	 	printf("���޴�(ѧ��:%d)ѧ�����ڣ�\n",no);
	 }
}
void modStu(void){
	printf("Mod student.\n");
	int no=0;
	printf("������Ҫ�޸�ѧ����ѧ�ţ�");
	scanf("%d",&no);
	int pos=findStuByNo(no);
	 if(pos>=0)
	 {
	 	showStu(pstus[pos]);
	 	printf("����������������");
	 	scanf("%s",pstus[pos].name);
	 	
	 	int gender=0;
	 	printf("�����������Ա�(1.�� 0.Ů)��");
		scanf("%d",&gender);
		pstus [pos].gender=gender!=0;
		
		int j=0;
		for(j=0;j<SCORE_CNT;j++)
		{
			printf("�����������%d�Ź��γɼ�:",j+1);
			scanf("%d",&pstus[pos].score[j]);
		}
		showStu(pstus[pos]);
		printf("Modify student info success!\n");
	 }else{
	 	printf("���޴�(ѧ��:%d)ѧ�����ڣ�\n",no);
	 }
}


/*void shunxu(struct Stu s)
{
	//��ѧ���ţ�С����
   struct Stu t=s;
   int i;
   for(i=0;i<size;i++)
    {
      if(s+i.no>s+i+1.no)
        {
            s+i=t;
            s+i+1=s+i;
            s+i+1=t;
        }
    }
}
*/
void listStu(void)
{
	
	printf("List all student.\n");
	if(size==0)
	{
		printf("����ѧ����\n");
	}
	else
	{	
		//shunxu(pstus[i]);
		int i,j;
		for(i=0;i<size;i++)
		{
			
			showStu(pstus[i]);
		}
		printf("--------------------------------\n");
	}
}

//����Ŀ�߼����ܵ����
void run(void)
{
	init();//��ʼ������  �����ڴ� 
	load();//ֻ��Ҫ����һ��  ��Ҫ�ŵ�whileѭ���� 
	while(1)
	{	
		//����������� 
		//��ʾ�ַ���������  ͼ�ν���
		showMenu();
		int oper=0;
		scanf("%d",&oper);
		switch(oper)
		{
			case ADD:
				addStu();
				break;
			case DEL:
				delStu();
				break;
			case FIND:
				findStu();
				break;
			case FINDna:
				findStuN();
				break;
			case MOD:
				modStu();
				break;
			case LIST:
				listStu();
				break;
			case QUIT:
				printf("Goodbye!\n");
				save();//�˳�֮ǰ  �����ڴ��е����� 
				return;
			
			default:
				printf("Operator Error!\n");
				break;
		} 
	}
}

int main(int argc, char *argv[]) 
{
	//������Ŀ  ��ں���  ��ʵ����Ŀmain���������û��ʲô�߼�����
	//��������Ŀ�߼�����ں���
	run();
	return 0;
}

