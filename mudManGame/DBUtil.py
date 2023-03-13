"""
数据库链接工具类
"""
import pymysql
class DBUtil:
    # 1.初始化链接
    def __init__(self):
        # 1.1 打开数据库链接()
        self.db = pymysql.connect(host='localhost', port=3306, user='root', password='root', db='py_test')
        # 1.2 使用cursor()方法获取游标
        self.cursor = self.db.cursor()
        # # 1.3 通过执行sql语句
        # self.cursor.execute(self.sql)

    # 2.查询获取多条数据
    def fetchall(self, sql):
        # 2.1 使用execute()方法执行sql语句
        self.cursor.execute(sql)
        # 2.2 使用游标中查询获取多条数据
        results = self.cursor.fetchall()
        return results
    # 3.查询单条数据
    def fetchone(self, sql):
        # 3.1 使用execute()方法执行sql语句
        self.cursor.execute(sql)
        # 3.2 使用fetchone()方法获取单条数据
        result = self.cursor.fetchone()
        return result
    # 4.添加删除更新操作
    def execute(self, sql):
        try:
            self.cursor.execute(sql)
            self.db.commit()
            print("数据库操作成功！")
        except:
            self.db.rollback()
            print("数据库操作失败！")
    # 5.关闭连接
    def close(self):
        self.cursor.close()
        self.db.close()