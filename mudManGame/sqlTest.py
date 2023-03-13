"""
数据库
"""
from DBUtil import DBUtil
from mudManGame import MudManGame
from save import Save

class SqlTest:
    # 1.初始化函数
    def __init__(self):
        # 1.1 获取工具类
        self.db = DBUtil()
        self.m = MudManGame()

    # 创建表
    def createTable(self):
        results = self.db.cursor.execute("CREATE TABLE MudManGame (number INT auto_increment PRIMARY KEY,level VARCHAR (10))")


    # 2.查询多条数据
    def selectAll(self):
        # 2.1 设置sql语句
        results = self.db.fetchall("select * from MudManGame")
        # 2.2 设置存储列表
        saveList = []
        # 2.3 根据结果进行设置对象
        for row in results:
            save = Save(row[0], row[1])
            saveList.append(save)
        # 2.4 关闭连接通道
        self.db.close()
        # 2.5 循环遍历所有的信息
        for save in saveList:
            print()

    # 列出单条语句
    def addOne(self):
        level = self.m.judgeClass()
        sql = f"insert into MudManGame values(null,'{level}')"
        # 4.1 执行sql语句
        self.db.execute(sql)
        # 4.2 关闭
        self.db.close()



# if __name__ == '__main__':
#     te = SqlTest()
#     # te.createTable()
#     te.selectAll()


