#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h> 
#define BOOK_NAME_LEN 40
#define AUTHOR_NAME_LEN 40
#define PUBLISHER_LEN 40
#define NAME_LEN 40

#define BOOK_INFO_FILE "book.bin" 


//定义枚举  操作
enum OPER{ADD=1,LIST,FIND,DEL,MOD,QUIT};
enum OPERF{FINDB=1,FINDA,BACK};



//定义结构体 
 struct PubTime{
 	int Year;      //出版年
 	int Month;    //出版月 
 	int Day;     //出版日 
 };
 
 struct Borr
 {
 	char name[NAME_LEN];  //借书人姓名 
 	bool gender;		  //借书人性别    ture "男"   false "女"
 	int no;				  //借书人学号 
 };
 struct Book
 {
 	int LoginAccount;	//登录号 
 	char BookName[BOOK_NAME_LEN];  //书名 
 	char AuthorName[AUTHOR_NAME_LEN]; //作者名 
 	int ClassNumber;	//分类号 
 	char Publisher[PUBLISHER_LEN]; //出版单位
	struct  PubTime PublicationTime; //出版时间
	int Price;            //价格 
 	bool Existence;		  //存在状态    ture "已还"   false "已借"
 	struct Borr Borrower;
 };

//定义全局变量 
struct Book *pbooks=NULL;   
size_t capacity=10;        
size_t size=0;            



//主菜单 
void ShowMenu(void)
{
	printf("****图书信息管理系统****\n");
	printf("** %d.图书信息录入功能  \n",ADD);
	printf("** %d.图书信息浏览功能 \n",LIST);
	printf("** %d.图书查询功能\n",FIND);
	printf("** %d.图书信息的删除 \n",DEL);
	printf("** %d.图书信息的修改 \n",MOD);
	printf("** %d.退出  \n",QUIT);
	printf(">>>>");
}
//查询系统菜单 
void ShowMenuOfFind(){
	printf("****图书信息管理系统之查找图书系统****\n");
	printf("** %d.按书名查询\n",FINDB);
	printf("** %d.按作者名查询\n",FINDA);
	printf("** %d.返回上层  \n",BACK);
	printf(">>>>");
}

//初始化 
void Init()
{
	pbooks=calloc(capacity,sizeof(struct Book));
	if(pbooks==NULL){
		printf("calloc memory failed\n");
		exit(-1); 
	} 
} 
 
//分配内存 
void AddBookToMem(struct Book b)
{
	if(size>=capacity)
	{
		capacity = 2*capacity;
		pbooks=realloc(pbooks,capacity*sizeof(struct Book));
		if(pbooks==NULL)
		{
			printf("realloc memory failed!\n");
			exit(-1);
		}
	}
	pbooks[size]=b;
	++size; 
}

//展示 
void ShowBook(struct Book b)
{
	printf("登录号:%d 书名:%s 作者名:%s 分类号:%d 出版单位:%s 出版时间:%d.%d.%d 价格:%d 存在状态:%s ",b.LoginAccount,b.BookName,b.AuthorName,b.ClassNumber,b.Publisher,b.PublicationTime.Year,b.PublicationTime.Month,b.PublicationTime.Day,b.Price,b.Existence?"已还":"已借");
	if(!b.Existence){
		printf("借书人姓名:%s 借书人性别:%s 借书人学号:%d ",b.Borrower.name,b.Borrower.gender?"男":"女",b.Borrower.no);
	} 
	printf("\n");
}


//登录加载项 
void Load(void)
{
	printf("-------------------------\n");
	printf("程序启动成功！\n加载数据....\n");
	FILE *fp = fopen(BOOK_INFO_FILE,"r");
	if(fp == NULL){
		printf("No data!\n");
		//exit(-1);
	}
	int cnt = 0,i = 0;
	fread(&cnt,sizeof(int),1,fp);
	for(i=0;i<cnt;i++)
	{
		struct Book b ={};
		fread(&b,sizeof(struct Book),1,fp);
		AddBookToMem(b);
		ShowBook(b);
	}
	fclose(fp);
	printf("-------------------------\n");
}

//退出加载项 
void Save(void)
{
	printf("----------------------------\n");
	printf("程序关闭并保存数据中....\n");
	FILE *fp = fopen(BOOK_INFO_FILE,"w");
	if(fp == NULL)
	{
		printf("fopen failed!\n");
		exit(-1);
	}
	fwrite(&size,sizeof(int),1,fp);
	int i;
	for(i=0;i<size;i++)
	{
		fwrite(pbooks+i,sizeof(struct Book),1,fp);
	}
	fclose(fp);
	printf("----------------------------\n");
}

//图书信息录入功能
void AddBook(void)
{
	printf("Add book information.\n");
	struct Book b={};
	printf("请输入登录号：");
	scanf("%d",&b.LoginAccount);
	printf("请输入书名：");
	scanf("%s",b.BookName);
	printf("请输入作者名：");
	scanf("%s",b.AuthorName);
	printf("请输入分类号：");
	scanf("%d",&b.ClassNumber);
	printf("请输出版单位：");
	scanf("%s",b.Publisher);
	
	printf("请输入出版日期的年：");
	scanf("%d",&b.PublicationTime.Year);
	printf("请输入出版日期的月：");
	scanf("%d",&b.PublicationTime.Month);
	printf("请输入出版日期的日：");
	scanf("%d",&b.PublicationTime.Day);
	
	printf("请输入价格：");
	scanf("%d",&b.Price);
	
	printf("请输入该图书的借阅状态(1:已还,0:已借)：");
	int Existence=1;
	scanf("%d",&Existence);
	b.Existence = Existence != 0;
	
	if(!b.Existence){
		printf("请输入借书人的性别(1:男,0女)：");
		int gender=1;
		scanf("%d",&gender);
		b.Borrower.gender = gender != 0;
		printf("请输入借书人的姓名：");
		scanf("%s",b.Borrower.name);
		printf("请输入借书人的学号：");
		scanf("%d",&b.Borrower.no);
	}
	
	
	AddBookToMem(b);
	
	printf("Add book information Success!\n");
}

//图书信息浏览功能 
void ListBook(void)
{
	
	printf("List all books.\n");
	if(size==0)
	{
		printf("暂无图书！\n");
	}
	else
	{	
		
		int i,j;
		for(i=0;i<size;i++)
		{
			
			ShowBook(pbooks[i]);
		}
		printf("--------------------------------\n");
	}
}


//查询功能：按书名查询 
int FindBookBookName(char BookName[])
{
	int i;
	for(i=0;i<size;i++)
	{	
		
		if(strcmp(pbooks[i].BookName,BookName) == 0)
		{
			return i;
		}
	}
	return -1;
}

void FindBookByBookName(void)
{
	printf("Find book by book name.\n");
	char BookName[BOOK_NAME_LEN];
	printf("请输入要查找图书的书名：");
	scanf("%s",&BookName);
	int pos=FindBookBookName(BookName);
	if(pos>=0)
	{
	 	printf("该图书信息如下:\n\t");
	 	ShowBook(pbooks[pos]);
	}else
	{
		printf("查无此(书名:%s)图书存在！\n",BookName);
	}
	void FindSystem();
	FindSystem();
}

//查询功能：按作者名查询
void FindBookByAuthorName(void)
{
	printf("Find book by author name.\n");
	char AuthorName[AUTHOR_NAME_LEN];
	printf("请输入要查找图书的作者名：");
	scanf("%s",&AuthorName);
	int i,j = 0,k = 0;
	for(i=0;i<size;i++)
	{	
		if(strcmp(pbooks[i].AuthorName,AuthorName) == 0)
		{
			j++;
		}
	}
	int pos[j];
	for(i=0;i<size;i++)
	{	
		if(strcmp(pbooks[i].AuthorName,AuthorName) == 0)
		{
			pos[k] = i;
			k++;
		}
	}
	 if(j>0)
	 {
	 	printf("该图书信息如下:\n");
	 	for(k=0;k<j;k++){
	 		ShowBook(pbooks[pos[k]]);
		 }
	 }else
	 {
	 	printf("查无此(作者:%s)图书存在！\n",AuthorName);
	 }
	 void FindSystem();
	 FindSystem();
}

//图书查找系统 
void FindSystem(){
	ShowMenuOfFind();
	int operf=0;
	scanf("%d",&operf);
	switch(operf)
		{
			case FINDB:
				FindBookByBookName();
				break;
			case FINDA:
				FindBookByAuthorName();
				break;
			case BACK:
				printf("Back!\n");
				break;
			
			default:
				printf("Operator Error!\n");
				break;
		} 
}


//图书信息的删除 
void DeleteBook(void)
{
	printf("Delelet book.\n");
	char BookName[BOOK_NAME_LEN];
	printf("请输入要检查的图书书名：");
	scanf("%s",&BookName);
	int pos=FindBookBookName(BookName);
	 if(pos>=0)
	 {
	 	 
	 	ShowBook(pbooks[pos]);
		if(pbooks[pos].Existence){
			pbooks[pos]=pbooks[size-1];
			--size;
			printf("Delete the %s book success!\n",BookName);
		}else{
		 	printf("The %s book existence is borrowed, delete fail!\n",BookName);
		}
		 
	 }else
	 {
	 	printf("查无此(书名:%s)图书存在！\n",BookName);
	 }
}

//图书信息的修改 
void ModifyBook(void){
	printf("Modify book.\n");
	char BookName[BOOK_NAME_LEN];
	printf("请输入要借阅的图书书名：");
	scanf("%s",&BookName);
	int pos=FindBookBookName(BookName);
	 if(pos>=0)
	 {
	 	if(pbooks[pos].Existence){
	 	printf("Can be borrowed!\n");
		pbooks[pos].Existence = false;
		
			printf("请输入此次登录号：");
			scanf("%d",&pbooks[pos].LoginAccount);
			printf("输入借书人信息：\n请输入借书人的姓名：");
			scanf("%s",pbooks[pos].Borrower.name);
			printf("请输入借书人的性别(1:男,0女)：");
			int gender=1;
			scanf("%d",&gender);
			pbooks[pos].Borrower.gender = gender != 0;
			printf("请输入借书人的学号：");
			scanf("%d",&pbooks[pos].Borrower.no);
			ShowBook(pbooks[pos]);
			printf("Modify book existence success!\n");
	}else{
				printf("该图书已被借！");
				printf("Modify book existence fail!\n");
	}
	 }else{
	 	printf("查无此(书名:%s)图书存在！\n",BookName);
	 }
}



//本项目逻辑功能的入口
void run(void)
{
	Init(); 
	Load();
	while(1)
	{	
		ShowMenu();
		int oper=0;
		scanf("%d",&oper);
		switch(oper)
		{
			case ADD:
				AddBook();
				break;
			case LIST:
				ListBook();
				break;
			case FIND:
				FindSystem();
				break;
			case DEL:
				DeleteBook();
				break;
			case MOD:
				ModifyBook();
				break;
			case QUIT:
				printf("Goodbye!\n");
				Save(); 
				return;
			
			default:
				printf("Operator Error!\n");
				break;
		} 
	}
}

int main(int argc, char *argv[]) 
{
	
	run();
	return 0;
}

