#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>//bool类型 
#define NAME_LEN 40
#define SCORE_CNT 3
#define STU_INFO_FILE "stu.dat"
//定义枚举  操作
enum OPER{QUIT=0,ADD,DEL,FIND,FINDna,MOD,LIST};
//定义学生结构体  类型 
 struct Stu
 {
 	int no;				  //学号 
 	char name[NAME_LEN];  //姓名 
 	bool gender;		  //性别    ture "男"   false "女"
 	int score[SCORE_CNT]; //成绩 
 };

//定义全局变量用于存储学生信息   全局变量  在任何函数中都可以直接访问 
struct Stu *pstus=NULL;   //全局变量  指向存储学生信息数据的内存
size_t capcity=10;        //容量大小  最多能存储多少学生   
size_t size=0;            //目前的学生数量
//pstus需要用malloc/calloc分配内存， size>=capacity需要用realloc扩容

void init()
{
	pstus=calloc(capcity,sizeof(struct Stu));//申请capacity个struct Stu内存
	if(pstus==NULL){
		printf("calloc memory failed\n");
		exit(-1);//内存分配失败  项目直接结束 
	} 
} 
 
void showMenu(void)
{
	printf("****学生成绩信息管理系统****\n");
	printf("** %d.增加学生  \n",ADD);
	printf("** %d.删除学生  \n",DEL);
	printf("** %d.查找学生  \n",FIND);
	printf("** %d.查找学生姓名  \n",FINDna);
	printf("** %d.修改学生信息  \n",MOD);
	printf("** %d.列出学生信息  \n",LIST);
	printf("** %d.退出  \n",QUIT);
	printf(">>>>");
}

/*
	基本于文件的
		数据需要保存到文件中
		（1）项目启动时，从文件中加载数据
		（2）程序退出之前，把内存中数据保存到文件中
*/

void load(void)
{
	printf("-------------------------\n");
	printf("程序启动时，加载数据....\n");
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
	printf("程序退出之前，要保存数据....\n");
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
	{//内存不够  扩容 
		capcity = 2*capcity;//2倍增长 
		pstus=realloc(pstus,capcity*sizeof(struct Stu));
		if(pstus==NULL)
		{
			printf("realloc memory failed!\n");
			exit(-1);//退出项目 
		}
	}
	pstus[size]=s;//存放于下标为size的内存中 
	++size;//学生数量加1 
}
void addStu(void)
{
	printf("Add stduent.\n");
	struct Stu s={};
	printf("请输入学号：");
	scanf("%d",&s.no);
	printf("请输入姓名：");
	scanf("%s",s.name);
	printf("请输入性别(1:男,0女)：");
	int gender=0;
	scanf("%d",&gender);
	s.gender=gender!=0;
	int i;
	for(i=0;i<SCORE_CNT;i++)
	{
		printf("请输入第%d门功课的成绩：",i+1);
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
	printf("学号:%d 姓名:%s 性别:%s 成绩:",s.no,s.name,s.gender?"男":"女");
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
	printf("请输入要删除学生的学号：");
	scanf("%d",&no);
	int pos=findStuByNo(no);//通过学号no查找该学生所在的位置（下标）
	 if(pos>=0)
	 {
	 	showStu(pstus[pos]);//删除之前  显示一下
		 pstus[pos]=pstus[size-1];
		 --size;
		 printf("Delete the %d student success!\n",no);
	 }else
	 {
	 	printf("查无此(学号:%d)学生存在！\n",no);
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
		}else printf("没这人") ;
	}
	return -1;
}

void findStuN(void)
{
	printf("Find student name.\n");
	char name[NAME_LEN];
	printf("请输入要查找学生的姓名：");
	scanf("%s",&name);
	int pos=findStuByN(name);
	//printf("%d",pos);
	 if(pos>=0)
	 {
	 	printf("该学生信息如下:\n\t");
	 	showStu(pstus[pos]);
	 }else
	 {
	 	printf("查无此(姓名:%s)学生存在！\n",name);
	 }
}
void findStu(void)
{
	printf("Find student number.\n");
	int no=0;
	printf("请输入要查找学生的学号：");
	scanf("%d",&no);
	int pos=findStuByNo(no);
	 if(pos>=0)
	 {
	 	printf("该学生信息如下:\n\t");
	 	showStu(pstus[pos]);
	 }else
	 {
	 	printf("查无此(学号:%d)学生存在！\n",no);
	 }
}
void modStu(void){
	printf("Mod student.\n");
	int no=0;
	printf("请输入要修改学生的学号：");
	scanf("%d",&no);
	int pos=findStuByNo(no);
	 if(pos>=0)
	 {
	 	showStu(pstus[pos]);
	 	printf("请重新输入姓名：");
	 	scanf("%s",pstus[pos].name);
	 	
	 	int gender=0;
	 	printf("请重新输入性别(1.男 0.女)：");
		scanf("%d",&gender);
		pstus [pos].gender=gender!=0;
		
		int j=0;
		for(j=0;j<SCORE_CNT;j++)
		{
			printf("请重新输入第%d门功课成绩:",j+1);
			scanf("%d",&pstus[pos].score[j]);
		}
		showStu(pstus[pos]);
		printf("Modify student info success!\n");
	 }else{
	 	printf("查无此(学号:%d)学生存在！\n",no);
	 }
}


/*void shunxu(struct Stu s)
{
	//按学号排，小到大
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
		printf("暂无学生！\n");
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

//本项目逻辑功能的入口
void run(void)
{
	init();//初始化工作  分配内存 
	load();//只需要加载一次  不要放到while循环中 
	while(1)
	{	
		//功能主体代码 
		//显示字符操作界面  图形界面
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
				save();//退出之前  保存内存中的数据 
				return;
			
			default:
				printf("Operator Error!\n");
				break;
		} 
	}
}

int main(int argc, char *argv[]) 
{
	//运行项目  入口函数  真实的项目main函数里基本没有什么逻辑代码
	//调用了项目逻辑的入口函数
	run();
	return 0;
}

