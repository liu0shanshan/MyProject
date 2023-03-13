#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h> 
#define BOOK_NAME_LEN 40
#define AUTHOR_NAME_LEN 40
#define PUBLISHER_LEN 40
#define NAME_LEN 40

#define BOOK_INFO_FILE "book.bin" 


//����ö��  ����
enum OPER{ADD=1,LIST,FIND,DEL,MOD,QUIT};
enum OPERF{FINDB=1,FINDA,BACK};



//����ṹ�� 
 struct PubTime{
 	int Year;      //������
 	int Month;    //������ 
 	int Day;     //������ 
 };
 
 struct Borr
 {
 	char name[NAME_LEN];  //���������� 
 	bool gender;		  //�������Ա�    ture "��"   false "Ů"
 	int no;				  //������ѧ�� 
 };
 struct Book
 {
 	int LoginAccount;	//��¼�� 
 	char BookName[BOOK_NAME_LEN];  //���� 
 	char AuthorName[AUTHOR_NAME_LEN]; //������ 
 	int ClassNumber;	//����� 
 	char Publisher[PUBLISHER_LEN]; //���浥λ
	struct  PubTime PublicationTime; //����ʱ��
	int Price;            //�۸� 
 	bool Existence;		  //����״̬    ture "�ѻ�"   false "�ѽ�"
 	struct Borr Borrower;
 };

//����ȫ�ֱ��� 
struct Book *pbooks=NULL;   
size_t capacity=10;        
size_t size=0;            



//���˵� 
void ShowMenu(void)
{
	printf("****ͼ����Ϣ����ϵͳ****\n");
	printf("** %d.ͼ����Ϣ¼�빦��  \n",ADD);
	printf("** %d.ͼ����Ϣ������� \n",LIST);
	printf("** %d.ͼ���ѯ����\n",FIND);
	printf("** %d.ͼ����Ϣ��ɾ�� \n",DEL);
	printf("** %d.ͼ����Ϣ���޸� \n",MOD);
	printf("** %d.�˳�  \n",QUIT);
	printf(">>>>");
}
//��ѯϵͳ�˵� 
void ShowMenuOfFind(){
	printf("****ͼ����Ϣ����ϵͳ֮����ͼ��ϵͳ****\n");
	printf("** %d.��������ѯ\n",FINDB);
	printf("** %d.����������ѯ\n",FINDA);
	printf("** %d.�����ϲ�  \n",BACK);
	printf(">>>>");
}

//��ʼ�� 
void Init()
{
	pbooks=calloc(capacity,sizeof(struct Book));
	if(pbooks==NULL){
		printf("calloc memory failed\n");
		exit(-1); 
	} 
} 
 
//�����ڴ� 
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

//չʾ 
void ShowBook(struct Book b)
{
	printf("��¼��:%d ����:%s ������:%s �����:%d ���浥λ:%s ����ʱ��:%d.%d.%d �۸�:%d ����״̬:%s ",b.LoginAccount,b.BookName,b.AuthorName,b.ClassNumber,b.Publisher,b.PublicationTime.Year,b.PublicationTime.Month,b.PublicationTime.Day,b.Price,b.Existence?"�ѻ�":"�ѽ�");
	if(!b.Existence){
		printf("����������:%s �������Ա�:%s ������ѧ��:%d ",b.Borrower.name,b.Borrower.gender?"��":"Ů",b.Borrower.no);
	} 
	printf("\n");
}


//��¼������ 
void Load(void)
{
	printf("-------------------------\n");
	printf("���������ɹ���\n��������....\n");
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

//�˳������� 
void Save(void)
{
	printf("----------------------------\n");
	printf("����رղ�����������....\n");
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

//ͼ����Ϣ¼�빦��
void AddBook(void)
{
	printf("Add book information.\n");
	struct Book b={};
	printf("�������¼�ţ�");
	scanf("%d",&b.LoginAccount);
	printf("������������");
	scanf("%s",b.BookName);
	printf("��������������");
	scanf("%s",b.AuthorName);
	printf("���������ţ�");
	scanf("%d",&b.ClassNumber);
	printf("������浥λ��");
	scanf("%s",b.Publisher);
	
	printf("������������ڵ��꣺");
	scanf("%d",&b.PublicationTime.Year);
	printf("������������ڵ��£�");
	scanf("%d",&b.PublicationTime.Month);
	printf("������������ڵ��գ�");
	scanf("%d",&b.PublicationTime.Day);
	
	printf("������۸�");
	scanf("%d",&b.Price);
	
	printf("�������ͼ��Ľ���״̬(1:�ѻ�,0:�ѽ�)��");
	int Existence=1;
	scanf("%d",&Existence);
	b.Existence = Existence != 0;
	
	if(!b.Existence){
		printf("����������˵��Ա�(1:��,0Ů)��");
		int gender=1;
		scanf("%d",&gender);
		b.Borrower.gender = gender != 0;
		printf("����������˵�������");
		scanf("%s",b.Borrower.name);
		printf("����������˵�ѧ�ţ�");
		scanf("%d",&b.Borrower.no);
	}
	
	
	AddBookToMem(b);
	
	printf("Add book information Success!\n");
}

//ͼ����Ϣ������� 
void ListBook(void)
{
	
	printf("List all books.\n");
	if(size==0)
	{
		printf("����ͼ�飡\n");
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


//��ѯ���ܣ���������ѯ 
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
	printf("������Ҫ����ͼ���������");
	scanf("%s",&BookName);
	int pos=FindBookBookName(BookName);
	if(pos>=0)
	{
	 	printf("��ͼ����Ϣ����:\n\t");
	 	ShowBook(pbooks[pos]);
	}else
	{
		printf("���޴�(����:%s)ͼ����ڣ�\n",BookName);
	}
	void FindSystem();
	FindSystem();
}

//��ѯ���ܣ�����������ѯ
void FindBookByAuthorName(void)
{
	printf("Find book by author name.\n");
	char AuthorName[AUTHOR_NAME_LEN];
	printf("������Ҫ����ͼ�����������");
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
	 	printf("��ͼ����Ϣ����:\n");
	 	for(k=0;k<j;k++){
	 		ShowBook(pbooks[pos[k]]);
		 }
	 }else
	 {
	 	printf("���޴�(����:%s)ͼ����ڣ�\n",AuthorName);
	 }
	 void FindSystem();
	 FindSystem();
}

//ͼ�����ϵͳ 
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


//ͼ����Ϣ��ɾ�� 
void DeleteBook(void)
{
	printf("Delelet book.\n");
	char BookName[BOOK_NAME_LEN];
	printf("������Ҫ����ͼ��������");
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
	 	printf("���޴�(����:%s)ͼ����ڣ�\n",BookName);
	 }
}

//ͼ����Ϣ���޸� 
void ModifyBook(void){
	printf("Modify book.\n");
	char BookName[BOOK_NAME_LEN];
	printf("������Ҫ���ĵ�ͼ��������");
	scanf("%s",&BookName);
	int pos=FindBookBookName(BookName);
	 if(pos>=0)
	 {
	 	if(pbooks[pos].Existence){
	 	printf("Can be borrowed!\n");
		pbooks[pos].Existence = false;
		
			printf("������˴ε�¼�ţ�");
			scanf("%d",&pbooks[pos].LoginAccount);
			printf("�����������Ϣ��\n����������˵�������");
			scanf("%s",pbooks[pos].Borrower.name);
			printf("����������˵��Ա�(1:��,0Ů)��");
			int gender=1;
			scanf("%d",&gender);
			pbooks[pos].Borrower.gender = gender != 0;
			printf("����������˵�ѧ�ţ�");
			scanf("%d",&pbooks[pos].Borrower.no);
			ShowBook(pbooks[pos]);
			printf("Modify book existence success!\n");
	}else{
				printf("��ͼ���ѱ��裡");
				printf("Modify book existence fail!\n");
	}
	 }else{
	 	printf("���޴�(����:%s)ͼ����ڣ�\n",BookName);
	 }
}



//����Ŀ�߼����ܵ����
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

