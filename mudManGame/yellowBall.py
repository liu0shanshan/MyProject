'''
yellowBall
泥球对象
'''

from mudManObject import MudManObject
class YellowBall(MudManObject):
    # 初始化函数
    def __init__(self, screen, imgs, row, col):
        """1.私有属性"""
        self.imgs = imgs
        """2.公有属性"""
        self.screen = screen
        self.img = self.imgs[0]
        self.row = row
        self.col = col
        """3.调用 父类的初始化函数"""
        super(YellowBall,self).__init__(screen,self.img,self.row,self.col)


