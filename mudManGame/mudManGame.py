"""
小泥人历险记
小游戏

"""

import pygame,sys
from setImg import SetImg
from mudMan import MudMan
import time
from yellowBall import YellowBall

class MudManGame(object):
    """第一区域：初始化区域"""
    def __init__(self):
        # 1.1 设置窗口大小（960*720）元组 尺寸
        self.screen = pygame.display.set_mode((960, 720))
        # 1.2 设置图片加载对象
        self.img = SetImg()
        # 1.3 设置小泥人对象
        self.mudMan = MudMan(self.screen,self.img.mudManImgs)
        # 1.4 设置建筑物二维列表
        self.builderMap = [
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 1, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 1, 0, 0, 0, 0, 0, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 40, 1, 0, 0, 0, 16, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 40, 40, 40, 40, 40, 0, 0, 0, 0, 0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 3, 40, 40, 40, 40, 0, 0, 0, 0, 0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 3, 3, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0, 0, 0, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 3, 3, 3, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0, 0, 0, 0, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 20, 20, 20, 21, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0, 15, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 19, 20, 20, 20, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 40, 40, 22, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0, 0, 0, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 22, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 0, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 11, 0, 0, 9, 10, 11, 0, 16, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 3, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 40, 40, 40, 0],
            [0, 3, 3, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 2, 0, 15, 40, 40, 40, 40, 40, 40, 2, 0, 0, 0, 15, 40, 40, 0],
            [0, 3, 3, 3, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 14, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 14, 0, 0, 0],
            [0, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 0, 0, 0, 0, 0, 9, 10, 11, 0, 0, 0, 0, 9, 10, 11, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 18, 13, 0, 0, 0, 0, 0, 0, 13, 13, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        ]
        # 1.5 控制重力
        self.jumpStale = False
        # 1.6 控制跳的高度
        self.value = 3
        # 1.7 按键状态
        self.key_right_status = False
        self.key_left_status = False
        self.key_up_status = False
        # 1.8 按钮状态
        self.buttonStatus1 = False
        self.buttonStatus2 = False
        # 1.9 设置泥球位置和对象
        self.yellowBallXY = [[27, 12], [26, 23], [23, 7], [20, 21], [13, 21], [12, 33], [4, 1], [3, 34]]
        self.yellowBalls = [YellowBall(self.screen, self.img.yellowBallImgs, self.yellowBallXY[i][0], self.yellowBallXY[i][1]) for i in range(len(self.yellowBallXY))]
        # 1.10 泥球采集数
        self.yellowBallNumber = 0
        # 1.11 死亡状态
        self.dieStatus = False
        # 1.12 菜单
        self.menuStatus = True
        # 1.13 开始游戏状态
        self.startGame = False
        # 1.14 操作说明状态
        self.opShowStatus = False
        # 1.15 继续游戏状态
        self.continueStatus = False
        # 1.16 特别鸣谢状态
        self.thanksStatus = False
        # 1.17 我知道了状态
        self.iKnowStatus = False
        # 1.18 游戏是否结束
        self.gameOver = False
        # 1.19 游戏结束菜单
        self.gameOverMenu = False
        # 1.20 再试一次状态
        self.tryAgain = False
        # 1.21 游戏暂停状态
        self.stopGame = False
        # 1.22 开始游戏时清空数据
        self.clearST = True
        # 1.23 设置门动画存储列表
        self.doorList = ["door/door_%02d.png" % i for i in range(13)]
        # 1.24 设置图片列表的下标
        self.ix = 0
        # 1.25 门数组动画的图片总数
        self.counts = -1
        # 1.26 设置连接数据库状态
        self.consql = True


    """第二区域：主架构区域"""
    def menu(self):
        # 2.1 设置窗口标题
        pygame.display.set_caption("小泥人历险记")

        # 2.2 背景音乐设置
        self.music()
        # 2.3 死循环
        while True:
            # 2.4 设置窗口背景颜色RGB（0,0,0）黑色
            self.screen.fill((0, 0, 0))
            # 2.5 业务执行函数
            self.action()
            # 2.6 连接数据库函数
            if self.consql:
                self.connectSql()
            # 2.7 绘制函数
            self.paint()
            # 2.8 设置刷新的频率 延迟10毫秒
            pygame.time.delay(100)
            # 2.9 设置刷新屏幕
            pygame.display.update()
            # 2.10 设置时间
            time.sleep(0.001)


    """第三区域：业务执行区域"""
    def action(self):
        # 3.1 循环遍历所有的监听事件
        for event in pygame.event.get():
            # 3.2 判断是否退出
            if event.type == pygame.QUIT:
                sys.exit()
            """3.3 鼠标监听事件"""
            if event.type == pygame.MOUSEBUTTONDOWN:
                # 获取鼠标的事件 pygame.mouse.get_pressed()返回一个列表
                # 0 代表单击  1代表鼠标双击  2代表鼠标右击
                leftFlag = pygame.mouse.get_pressed()[0]
                mx,my = pygame.mouse.get_pos()
                """主界面"""
                if not self.gameOverMenu and not self.dieStatus and not self.stopGame:
                    """开始游戏点击事件 325, 331, 300, 75"""
                    if leftFlag and 325<mx<325+300 and 331<my<331+75:
                        self.startGame = True
                    """特别鸣谢点击事件 365, 465, 225, 65"""
                    if leftFlag and 365<mx<365+225 and 465<my<465+65:
                        self.opShowStatus = True
                        self.menuStatus = False
                    """操作说明点击事件 355, 610, 255, 65"""
                    if leftFlag and 355<mx<355+255 and 610<my<610+65:
                        self.thanksStatus = True
                        self.menuStatus = False
                    """我知道了点击事件 405, 550, 155, 40"""
                    if leftFlag and 405<mx<405+155 and 550<my<550+40:
                        self.menuStatus = True
                        self.opShowStatus = False
                        self.thanksStatus = False
                """暂停界面"""
                """绿色按钮点击事件 415, 695, 55, 30"""
                if leftFlag and 415 < mx < 415 + 55 and 695 < my < 695 + 30:
                    self.stopGame = True
                if self.stopGame:
                    """返回菜单点击事件 372, 425, 220, 60"""
                    if leftFlag and 372 < mx < 372 + 220 and 425 < my < 425 + 60:
                        self.stopGame = False
                        self.dieStatus = False
                        self.startGame = False
                        self.continueStatus = False
                        self.menuStatus = True
                        self.clearST = True
                    """继续游戏点击事件 370, 310, 220, 60"""
                    if leftFlag and 370 < mx < 370 + 220 and 310 < my < 310 + 60:
                        self.stopGame = False
                        self.continueStatus = True
                        self.menuStatus = False
                """游戏结束界面"""
                if self.gameOver:
                    """再试一次点击事件 360, 320, 250, 80"""
                    if leftFlag and 360 < mx < 360 + 250 and 320 < my < 320 + 80:
                        self.dieStatus = False
                        self.stopGame = False
                        self.startGame = False
                        self.continueStatus = False
                        self.menuStatus = True
                        self.clearST = True
                        self.gameOver = False
                        self.startGame = True
                    """返回菜单点击事件 370, 450, 220, 80 返回菜单"""
                    if leftFlag and 370 < mx < 370 + 220 and 450 < my < 450 + 80:
                        print("返回菜单")
                        self.gameOverMenu = False
                        self.dieStatus = False
                        self.startGame = False
                        self.continueStatus = False
                        self.menuStatus = True
                        self.clearST = True
                        self.gameOver = False
                """游戏结束返回主菜单"""
                if self.gameOver:
                    # 380, 490, 200, 70 游戏结束返回主菜单
                    if leftFlag and 380 < mx < 380 + 200 and 490 < my < 490 + 70:
                        self.gameOverMenu = False
                        self.startGame = False
                        self.continueStatus = False
                        self.menuStatus = True
                        self.clearST = True
            # 3.4 键盘监听事件
            elif event.type == pygame.KEYDOWN:
                """门动画位置判定"""
                if 4 <= self.mudMan.row <= 5 and 31 <= self.mudMan.col <= 33:
                    self.counts = 13
                    self.paintDoor()
                    self.gameOver = True
                    self.gameOverMenu = True
                """按键判断 w,a,d 上，左，右"""
                if self.startGame or self.continueStatus:
                    if event.key == pygame.K_w:
                        self.key_up_status = True
                    elif event.key == pygame.K_a:
                        self.key_left_status = True
                    elif event.key == pygame.K_d:
                        self.key_right_status = True
            # 3.5 键盘松开事件
            elif event.type == pygame.KEYUP:
                if event.key == pygame.K_w:
                    self.key_up_status = False
                elif event.key == pygame.K_a:
                    self.key_left_status = False
                elif event.key == pygame.K_d:
                    self.key_right_status = False


        """门动画-修改图片内容"""
        # 3.6 修改下标值  0,1,2,3...... 21  doorList最大的下标是21
        self.ix += 1

        # 3.7 修改doorback的值 根据下标以及doorList将每张图片的路径拿出来进行加载
        """
        ix = 0 < counts -1 ===> False  不会修改图片
        ix = 0 < counts 12 ===> True   会修改图片
        ix = 12 < counts 12 ===> False  停止动画效果  ix=0  counts = -1
        """
        # 调整动画效果的频率
        """
        ix = 0 / 10 = 0 % 13 ===> 0
        ix = 1 / 10 = 0.1 % 13 ===> 0
        ......
        ix = 10 / 10 = 1 % 13 ===> 1
        程序每运行10次  修改1次图片
        """
        index = int(self.ix / 1 % self.counts + 1)
        if index < self.counts:
            self.img.doorback = pygame.image.load(self.doorList[index])
        else:
            self.ix = 0
            self.counts = -1
            # 如果动画停止  需要将列表情况
            del self.doorList[:]

        # 3.8 重力识别
        if not self.jumpStale and self.judgeOutType(self.mudMan.row + 1, self.mudMan.col):
                self.mudMan.row += 1

        # 3. 调用函数
        self.check_status()
        self.move()
        self.button(self.mudMan)
        self.banMove()
        self.eatDiamond(self.mudMan, self.yellowBalls)
        self.mudDie()

    # 3.9 水池位置判定
    def mudDie(self):
        for i in range(19, 22):
            if self.mudMan.row == 27 and self.mudMan.col == i:
                self.mudMan.die = True
                self.mudMan.img = self.img.dieImgs[0]
        for i in range(26, 29):
            if self.mudMan.row == 27 and self.mudMan.col == i:
                self.mudMan.die = True
                self.mudMan.img = self.img.dieImgs[0]
        for i in range(23, 26):
            if self.mudMan.row == 20 and self.mudMan.col == i:
                self.mudMan.die = True
                self.mudMan.img = self.img.dieImgs[0]
        for i in range(18, 21):
            if self.mudMan.row == 20 and self.mudMan.col == i:
                self.mudMan.die = True
                self.mudMan.img = self.img.dieImgs[0]

    # 3.10 吃泥球以及泥球采集数
    def eatDiamond(self,obj, objRD):
        for i in range(len(self.yellowBallXY)):
            if obj.col == objRD[i].col and obj.row == objRD[i].row:
                self.yellowBallNumber += 1
                objRD[i].row += 40

    # 3.11 按钮判定
    def button(self, obj):
        if (self.builderMap[obj.row][obj.col - 1] == 22 or self.builderMap[obj.row][obj.col + 1] == 22):
            self.builderMap[obj.row][obj.col - 1] = 40
            self.builderMap[obj.row][obj.col + 1] = 40
            if self.mudMan.row == 19:
                self.buttonStatus1 = True
            if self.mudMan.row == 15:
                self.buttonStatus2 = True

    # 3.12 按下按钮 板的移动
    def banMove(self):
        # 板1
        if self.buttonStatus1:
             for j in range(4):
                    for i in range(1, 6):
                        self.builderMap[14 + j][i] = 40
                    self.builderMap[15 + j][1] = 19
                    self.builderMap[15 + j][2] = 20
                    self.builderMap[15 + j][3] = 20
                    self.builderMap[15 + j][4] = 20
                    self.builderMap[15 + j][5] = 21
        # 板2
        if self.buttonStatus2:
            for j in range(3):
                for i in range(31, 36):
                    self.builderMap[11 + j][i] = 40
                self.builderMap[12 + j][31] = 19
                self.builderMap[12 + j][32] = 20
                self.builderMap[12 + j][33] = 20
                self.builderMap[12 + j][34] = 20
                self.builderMap[12 + j][35] = 21

    # 3.13 不同方向，小泥人图片的切换
    def check_status(self):
        if self.key_right_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            self.mudMan.img = self.mudMan.imgs[3]
        elif self.key_left_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            self.mudMan.img = self.mudMan.imgs[2]
        elif self.key_up_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            self.mudMan.img = self.mudMan.imgs[1]
        else:
            self.mudMan.img = self.mudMan.imgs[0]

    # 3.14 小泥人的移动
    def move(self):
        self.setpMudMan(1, self.mudMan)
        self.setpMudMan(2, self.mudMan)
        self.setpMudMan(3, self.mudMan)

    # 3.15 小泥人移动步数判断
    def setpMudMan(self, dir, obj):
        fRow = obj.row
        fCol = obj.col
        if dir == 1 and self.key_up_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            for i in range(1 , self.value + 1):
                if self.judgeOutType(fRow - i, fCol):
                    self.jumpStale = True
                    self.mudMan.row -= 1
                    self.jumpStale = False
                    self.key_up_status = False
        elif dir == 2 and self.judgeOutType(fRow, fCol - 1) and self.key_left_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            self.mudMan.col -= 1
        elif dir == 3 and self.judgeOutType(fRow, fCol + 1) and self.key_right_status and not self.dieStatus and not self.gameOver and not self.stopGame:
            self.mudMan.col += 1

    # 3.16 判断出界以及建筑物类型
    def judgeOutType(self, nextRow, nextCol):
        if 0 <= nextRow < 30 and 0 <= nextCol < 37:
            if self.builderMap[nextRow][nextCol] == 40:
                return True
        return False

    # 3.17小泥人通关等级判定
    def judgeClass(self):
        if self.yellowBallNumber <= 2:
            return "D"
        elif self.yellowBallNumber <= 4:
            return "C"
        elif self.yellowBallNumber <= 6:
            return "B"
        else:
            return "A"

    # 3.18 连接数据库操作，导入等级评判
    def connectSql(self):
        from sqlTest import SqlTest
        self.c = SqlTest()
        if self.gameOver or self.dieStatus:
            self.c.addOne()
            self.consql = False


    """第四区域：绘制函数"""
    def paint(self):
        # 4.1 绘制主界面图片
        self.paintOperation()
        # 4.2 绘制不同界面
        self.painMudMan()

    # 4.1 绘制主界面图片
    def paintOperation(self):
        # 主菜单
        if self.menuStatus:
            self.screen.blit(self.img.operationImgs[0], (0, 0))
        # 操作说明
        if self.opShowStatus:
            self.screen.blit(self.img.operationImgs[1], (0, 0))
        # 特别鸣谢
        if self.thanksStatus:
            self.screen.blit(self.img.operationImgs[2], (0, 0))

    # 4.2 绘制不同界面
    def painMudMan(self):
        # 开始界面
        if self.startGame:
            # 开始游戏，数据重置
            if self.clearST:
                self.clear()
                self.clearST = False
            # 4.0 绘制背景图
            self.screen.blit(self.img.back, (0, 0))
            # 4.5 绘制开始门图片
            self.paintBack2()
            # 4.6 绘制小泥人图片
            self.mudMan.blitMe()
            # 4.7 绘制泥球图片
            self.paintYellowBall()
            # 4.8 绘制建筑物图片
            self.paintBuilder()
            # 4.9 绘制小泥人死亡状态
            self.paintDie()
            # 4.10 绘制门动画图片
            self.paintDoor()
            # 4.11 绘制游戏暂停图片
            self.paintStopGame()
            # 4.12 绘制游戏结束图片
            self.paintGameover()
        # 继续界面
        if self.continueStatus:
            # 4.4 绘制背景图
            self.screen.blit(self.img.back, (0, 0))
            # 4.5 绘制开始门图片
            self.paintBack2()
            # 4.6 绘制小泥人图片
            self.mudMan.blitMe()
            # 4.7 绘制泥球图片
            self.paintYellowBall()
            # 4.8 绘制建筑物图片
            self.paintBuilder()
            # 4.9 绘制小泥人死亡状态
            self.paintDie()
            # 4.10 绘制门动画图片
            self.paintDoor()
            # 4.11 绘制游戏暂停图片
            self.paintStopGame()
            # 4.12 绘制游戏结束图片
            self.paintGameover()

    # 4.3 绘制小泥人属性
    def clear(self):
        if self.startGame:
            # 小泥人开始位置
            self.mudMan.img = self.img.mudManImgs[0]
            self.mudMan.row = 27
            self.mudMan.col = 1
            # 泥球位置
            for i in range(len(self.yellowBallXY)):
                self.yellowBalls[i].row = self.yellowBallXY[i][0]
                self.yellowBalls[i].col = self.yellowBallXY[i][1]
            # 泥球采集数
            self.yellowBallNumber = 0

    # 4.5 绘制开始门图片
    def paintBack2(self):
        self.screen.blit(self.img.doorback, (813, 75))

    # 4.7 绘制泥球图片
    def paintYellowBall(self):
        for i in range(len(self.yellowBallXY)):
            self.yellowBalls[i].blitMe()

    # 4.8 绘制建筑物图片
    def paintBuilder(self):
        for row in range(0, 30):
            for col in range(0, 37):
                bx = 0 + 26 * col
                by = 0 + 24 * row
                index = self.builderMap[row][col]
                if index != 40:
                    self.screen.blit(self.img.builderImgs[index], (bx, by))

    # 4.9 绘制小泥人死亡状态
    def paintDie(self):
        if self.mudMan.img == self.img.dieImgs[0]:
            self.gameOver = True
        if self.gameOver:
            self.screen.blit(self.img.dieImgs[1], (0, 0))
            self.dieStatus = True

    # 4.10 绘制门动画图片
    def paintDoor(self):
        for i in range(0, self.counts):
            self.doorList.append("img/door/door_%02d.png" % i)
            self.gameOver = True

    # 4.11 绘制游戏暂停图片
    def paintStopGame(self):
        # 游戏暂停
        if self.stopGame:
            self.screen.blit(self.img.operationImgs[4], (0, 0))

    # 4.12 绘制游戏结束图片
    def paintGameover(self):
        # 游戏结束界面
        if self.gameOver and self.gameOverMenu:
            self.screen.blit(self.img.operationImgs[3], (0, 0))
            self.paintFont()


    # 4.13 设置字体样式及字体大小（字体样式，字体大小）
    def paintFont(self):
        pygame.font.init()
        # 设置字体样式及字体大小（字体样式，字体大小）
        ft = pygame.font.Font("font/msyhbd.ttc", 60)
        finlallyGrade = ft.render("x %d"%self.yellowBallNumber, True, (240, 240, 50))
        finlallyClass = ft.render(" %s" % self.judgeClass(), True, (240, 240, 50))
        # 绘制到窗口中（绘制内容，坐标值（x，y））
        self.screen.blit(finlallyGrade, (515, 280))
        self.screen.blit(finlallyClass, (530, 375))



    """第五区域 添加背景音乐"""
    def music(self):
        file = r'music\mudManGameMusic.wav'  # 音乐的路径
        pygame.mixer.init()  # 初始化
        track = pygame.mixer.music.load(file)  # 加载音乐文件
        pygame.mixer.music.play()  # 开始播放音乐流


if __name__ == '__main__':
    MudManGame = MudManGame()
    MudManGame.menu()


