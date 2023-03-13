'''
mudManObject
所有对象的父类
'''
class MudManObject:
    # 1.设置初始化函数（4个属性  绘制相关）
    def __init__(self, screen, img, row, col):
        # 公共属性
        self.screen = screen
        self.img = img
        self.row = row
        self.col = col

    # 2.公共函数（绘制自己）
    def blitMe(self):
        x = 0 + 26 * self.col
        y = 0 + 24 * self.row
        self.screen.blit(self.img, (x, y))