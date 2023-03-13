'''
加载游戏图片
'''
import pygame

class SetImg():
    # 初始化函数
    def __init__(self):
        # 1.加载背景图片
        self.back = pygame.image.load("img/map.jpg")
        # 2.加载门背景图片
        self.doorback = pygame.image.load("img/door_00.png")
        # 3.加载各个界面图片
        self.operationImgs = [
            pygame.image.load("img/operation/menu.jpg"),
            pygame.image.load("img/operation/opShow.png"),
            pygame.image.load("img/operation/thanks.png"),
            pygame.image.load("img/operation/gameover.png"),
            pygame.image.load("img/operation/stopGame.png")

        ]
        # 4.加载小泥人图片
        self.mudManImgs = [
            pygame.image.load("img/mudMan/mudMan.png"),
            pygame.image.load("img/mudMan/mudMan_up.png"),
            pygame.image.load("img/mudMan/mudMan_left.png"),
            pygame.image.load("img/mudMan/mudMan_right.png"),
        ]
        # 5.加载建筑物图片
        self.builderImgs = [
            pygame.image.load("img/fangzhuan.jpg"),  # 0方砖
            pygame.image.load("img/sanjiaozhuan.png"),  # 1左斜三角砖
            pygame.image.load("img/1sanjiaozhuan.png"),  # 2右正三角砖
            pygame.image.load("img/box.png"),  # 3箱子
            pygame.image.load("img/door.png"),  # 4门
            pygame.image.load("img/left river.png"),  # 5左部岩浆
            pygame.image.load("img/middle river.png"),  # 6中部岩浆
            pygame.image.load("img/right river.png"),  # 7右部岩浆
            pygame.image.load("img/yellowBall.png"),  # 8 红钻石
            pygame.image.load("img/left zaoze.png"),  # 9左沼泽
            pygame.image.load("img/middle zaoze.png"),  # 10中间沼泽
            pygame.image.load("img/right zaoze.png"),  # 11右沼泽
            pygame.image.load("img/河左下斜砖.png"),  # 12河左下斜砖
            pygame.image.load("img/河长砖.png"),  # 13河长砖
            pygame.image.load("img/河右斜砖.png"),  # 14河右斜砖
            pygame.image.load("img/2sanjiaozhuan.png"),  # 15右斜三角砖
            pygame.image.load("img/3sanjiaozhuan.png"),  # 16 左正三角砖
            pygame.image.load("img/menu1.png"),  # 17左菜单
            pygame.image.load("img/menu2.png"),  # 18右菜单
            pygame.image.load("img/pingban1.png"),  # 19左平板
            pygame.image.load("img/pingban2.png"),  # 20中间平板
            pygame.image.load("img/pingban3.png"),  # 21右平板
            pygame.image.load("img/按钮.png"),  #22 按钮
            pygame.image.load("img/按钮砖.png")   #23 按钮砖
        ]
        # 6.加载钻石图片
        self.yellowBallImgs = [
            pygame.image.load("img/yellowBall.png")
        ]
        # 7.加载小泥人死亡图片
        self.dieImgs = [
            pygame.image.load("img/die/die.png"),
            pygame.image.load("img/die/mudManDie.png")
        ]
