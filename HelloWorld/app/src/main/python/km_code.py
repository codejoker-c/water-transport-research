# import pandas as pd

import numpy as np


# import db
# import csv


class KM:
    def __init__(self):
        self.matrix = None
        self.max_weight = 0
        self.row, self.col = 0, 0  # 源数据行列
        self.size = 0  # 方阵大小
        self.lx = None  # 左侧权值
        self.ly = None  # 右侧权值
        self.match = None  # 匹配结果
        self.slack = None  # 边权和顶标最小的差值
        self.visx = None  # 左侧是否加入增广路
        self.visy = None  # 右侧是否加入增广路

    # 调整数据
    def pad_matrix(self, min):
        if min:
            max = self.matrix.max() + 1
            self.matrix = max - self.matrix

        # 变成方阵
        if self.row > self.col:  # 行大于列，添加列
            self.matrix = np.c_[self.matrix, np.array([[0] * (self.row - self.col)] * self.row)]
        elif self.col > self.row:  # 列大于行，添加行
            self.matrix = np.r_[self.matrix, np.array([[0] * self.col] * (self.col - self.row))]

    # 每次寻找增广路前修改slack为总和加一（无穷大）
    def reset_slack(self):
        self.slack.fill(self.max_weight + 1)

    # 每次寻找增广路前修改vis数组为False（都没有被访问过）
    def reset_vis(self):
        self.visx.fill(False)
        self.visy.fill(False)

    def find_path(self, x):  # 为x结点寻找增广路，能否向后继续扩展
        self.visx[x] = True
        for y in range(self.size):
            if self.visy[y]:
                continue
            tmp_delta = self.lx[x] + self.ly[y] - self.matrix[x][y]
            if tmp_delta == 0:  # 属于相等子图
                self.visy[y] = True
                if self.match[y] == -1 or self.find_path(
                        self.match[y]):  # 属于相等子图但还没有被匹配 或 被匹配了但是y匹配的x后面还能有增广路 也可以
                    # if self.match[y] == -1 :                               # 所以是符合增广路的定义，从未匹配点到未匹配点 从x到y（奇数条边）
                    self.match[y] = x
                    return True  # 找到了一条增广路
            elif self.slack[y] > tmp_delta:
                self.slack[y] = tmp_delta  # 修改slack[y]值为较小的

        return False

    def km_cal(self):
        for x in range(self.size):
            self.reset_slack()
            while True:
                self.reset_vis()

                if self.find_path(x):  # 如果x有增广路，则直接好，，如果没有则操作数据让它有
                    # print(x,int(self.visy.sum()),int(self.visx.sum()))
                    break
                else:  # update slack
                    delta = self.slack[~self.visy].min()  # 所有不在交错树中的y的slack的最小值作为d即可
                    self.lx[self.visx] -= delta  # 每个在交错树内的x都减去d
                    self.ly[self.visy] += delta  # 每个在交错树内的y都加上d  使得树内的匹配还在相等子图
                    self.slack[~self.visy] -= delta  # 每个不在交错树中的y都减去d，必有一个在下一轮会进入到相等子图中

    def compute(self, datas, min=False):
        """
        :param datas: 权值矩阵
        :param min: 是否取最小组合，默认最大组合
        :return: 输出行对应的结果位置
        """
        self.matrix = np.array(datas) if not isinstance(datas, np.ndarray) else datas
        self.max_weight = self.matrix.sum()
        self.row, self.col = self.matrix.shape  # 源数据行列
        self.size = max(self.row, self.col)  # 取较大的 后面要修改矩阵
        self.pad_matrix(min)  # min是False  则修改矩阵变成方阵
        # print(self.matrix)
        self.lx = self.matrix.max(1)  # x顶标是每行的最大值
        self.ly = np.array([0] * self.size, dtype=int)  # 初始化y顶标都是0
        self.match = np.array([-1] * self.size, dtype=int)  # 初始化匹配数组match都是-1，表示未匹配
        self.slack = np.array([0] * self.size, dtype=int)  # 初始化slack都为0
        self.visx = np.array([False] * self.size, dtype=bool)  # 都未访问
        self.visy = np.array([False] * self.size, dtype=bool)  # 都未访问

        self.km_cal()  # 每个x，寻找增广

        match = [i[0] for i in sorted(enumerate(self.match), key=lambda x: x[1])]
        result = []
        for i in range(self.row):
            result.append((i, match[i] if match[i] < self.col else -1))  # 没有对应的值给-1
        return result


def find_path(visx, visy, size, lx, ly, matrix, match, slack, x):
    find_path_flag = 0
    visx[x] = True
    for y in range(size):
        if visy[y] == True:
            continue
        tmp_delta = lx[x] + ly[y] - matrix[x][y]
        if tmp_delta == 0:
            visy[y] = True
            if match[y] == -1 or find_path(visx, visy, size, lx, ly, matrix, match, slack,
                                           match[y]):
                match[y] = x
                find_path_flag = 1
                break
        elif slack[y] > tmp_delta:
            slack[y] = tmp_delta
    return find_path_flag


# 整体步骤，重写了一遍
def compute_km(datas, min=False):
    # init
    matrix = None
    max_weight = 0
    row, col = 0, 0  # 源数据行列
    size = 0  # 方阵大小
    lx = None  # 左侧权值
    ly = None  # 右侧权值
    match = None  # 匹配结果
    slack = None  # 边权和顶标最小的差值
    visx = None  # 左侧是否加入增广路
    visy = None  # 右侧是否加入增广路

    matrix = np.array(datas) if not isinstance(datas, np.ndarray) else datas
    max_weight = matrix.sum()
    row, col = matrix.shape
    if row >= col:
        size = row
    else:
        size = col

    if min:  # min默认参数为False
        max = matrix.max() + 1
        matrix = max - matrix
    if row > col:  # 行大于列，添加列
        matrix = np.c_[matrix, np.array([[0] * (row - col)] * row)]
    elif col > row:  # 列大于行，添加行
        matrix = np.r_[matrix, np.array([[0] * col] * (col - row))]

    # print(matrix)

    # 初始化数据
    lx = matrix.max(1)  # 初始化x顶标是每行的最大值
    ly = np.array([0] * size, dtype=int)
    # print(lx)
    # print(ly)

    match = np.array([-1] * size, dtype=int)
    slack = np.array([0] * size, dtype=int)
    visx = np.array([False] * size, dtype=bool)
    visy = np.array([False] * size, dtype=bool)

    for x in range(size):
        slack.fill(max_weight + 1)
        while True:
            visx.fill(False)
            visy.fill(False)
            if find_path(visx, visy, size, lx, ly, matrix, match, slack, x):
                # print(x, int(visy.sum()), int(visx.sum()))
                break
            else:  # update slack
                # print(slack)
                # if len(slack[~visy]) == 0:
                #     walawala = 1
                #     slack_flag = 1
                delta = slack[~visy].min()
                lx[visx] -= delta
                ly[visy] += delta
                slack[~visy] -= delta

    match = [i[0] for i in sorted(enumerate(match), key=lambda x: x[1])]
    result = []
    for i in range(row):
        result.append((i, match[i] if match[i] < col else -1))  # 没有对应的值给-1
    return result  # 返回result二维数组，匹配的结果，，一个X，一个Y


class port(object):

    def __init__(self, name, route):
        self.name = name
        self.route = route


Loc_Correspond = {"重庆": 15, "大连": 20, "东莞": 22, "福州": 28, "泰兴": 42,
                  "南通": 73, "海安": 56, "盐城": 35, "宣城": 5, "上海": 63}

pindex = {"Port A": 99999}

gate = []


def final_func(Ship_info_array, Cargo_info_array, ship_length, cargo_length):
    alpha = 7.55E-6
    beta = 1492.2
    gama = 0.0145
    petrol_per = 163  # 燃油单价（瞎编）
    const_oneday = 200  # 每日固定开销（可能是瞎编的
    gate_fee = 400  # 过闸费
    bill_fee = 1.5  # 办单费（船舶总重）
    inout_fee = 0.35  # 进出港费（按船舶净吨位）
    construct_fee = 4  # 港口建设费
    insurance_fee = 0.112  # 保险费
    speed_ship = 22.2

    # 以下数据为瞎编
    sand_fare = 15
    coal_fare = 20
    mineral_fare = 25
    CargoType_Price = {"沙土石子": sand_fare, "煤炭": coal_fare, "矿石": mineral_fare}

    result = [[0 for i in range(cargo_length)] for j in range(ship_length)]

    for i in range(Ship_info_array.size()):
        for j in range(Cargo_info_array.size()):
            M_ship = Ship_info_array.get(i).weight
            # V=Ship_info_array[i]["V_ship"]
            M_cargo = Cargo_info_array.get(j).cargo_weight

            dis_ship_cargo = Loc_Correspond[Ship_info_array.get(i).depart] - Loc_Correspond[
                Cargo_info_array.get(j).depart]
            if dis_ship_cargo < 0:
                dis_ship_cargo = -dis_ship_cargo

            dis_transport = Loc_Correspond[Cargo_info_array.get(j).depart] - Loc_Correspond[
                Cargo_info_array.get(j).destin]
            if dis_transport < 0:
                dis_transport = -dis_transport

            time = (dis_transport + dis_ship_cargo) / speed_ship / 24
            #gate_num = 0
            #for i in range(pindex[Ship_info_array.get(i).depart],
            #               pindex[Cargo_info_array.get(j).depart]):
            #    gate_num += gate[i]
            #for i in range(pindex[Cargo_info_array.get(j).depart],
            #               pindex[Cargo_info_array.get(j).destin]):
            #    gate_num += gate[i]

            TransportFee1 = M_cargo * CargoType_Price[Cargo_info_array.get(j).cargo_type]
            TransportFee2 = 1. / 24 * alpha * dis_transport * speed_ship ** 2 * (
                    beta + gama * M_cargo)*petrol_per
            TransportFee3 = const_oneday * time
            TransportFee4 = (construct_fee + inout_fee + insurance_fee) * M_cargo
            TransportFee5 = bill_fee * (M_cargo + M_ship)
            #TransportFee6 = gate_num * gate_fee
            # 还有一项时间窗没有写

            #Weight = TransportFee1 - (
            #            TransportFee2 + TransportFee3 + TransportFee4 + TransportFee5 + TransportFee6)
            Weight = TransportFee1 - (
                    TransportFee2 + TransportFee3 + TransportFee4 + TransportFee5)

            result[i][j] = Weight

    graphlist = result

    ningraphlist_num = 1000000000
    for i in range(len(graphlist)):
        for j in range(len(graphlist[i])):
            if graphlist[i][j] < ningraphlist_num:
                ningraphlist_num = graphlist[i][j]  # 得到最小的

    if ningraphlist_num < 0:  # 若有小于0的值，则整体都变成正数再操作
        for i in range(len(graphlist)):
            for j in range(len(graphlist[i])):
                graphlist[i][j] = int(graphlist[i][j] - ningraphlist_num + 1)
    else:
        for i in range(len(graphlist)):
            for j in range(len(graphlist[i])):
                graphlist[i][j] = int(graphlist[i][j])

    # 声明数据结构  创建一个Array的数组a用作输入
    a = np.array(graphlist)  # np array with dimension N*N

    # 开始执行

    km = KM()  # 声明对象
    max_ = compute_km(a.copy())  # 使用a的一个副本，copy一个使用   max_是一个一维数组存匹配结果
    return max_

# 需要的工作是将这些信息进行计算，之后将权值存到一个文件中去
