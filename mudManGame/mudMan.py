'''
火人对象
'''

from mudManObject import MudManObject

class MudMan(MudManObject):
    # 1.初始化函数 screen窗口对象  images是图片列表
    def __init__(self,screen, imgs):
        """1.1 私有属性"""
        self.imgs = imgs
        self.die = False
        """1.2 公有属性"""
        self.screen = screen
        self.img = self.imgs[0]
        self.row = 27
        self.col = 1
        """1.3 调用 父类的初始化函数"""
        super(MudMan, self).__init__(screen, self.img, self.row, self.col)