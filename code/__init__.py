import os
import db
import numpy as np
import pandas as pd
import csv

from km_code.dist_km import KM, compute_km

mydb = db.get_db()

db.init_db(mydb)

cur = mydb.cursor()

Loc_Correspond = {"重庆": 15, "大连": 20, "东莞": 22, "福州": 28, "泰兴": 42, "南通": 73, "海安": 56, "盐城": 35, "宣城": 5}

Ship_info_array = cur.execute(
    'SELECT p.id, loc, M_ship, V_ship, Cost_trans, Cost_live '
    ' FROM ship p '
).fetchall()
Cargo_info_array = cur.execute(
    'SELECT p.id, depart, destin, m_cargo, target_money '
    ' FROM cargo p '
).fetchall()

result = [[0 for i in range(len(Ship_info_array))] for j in range(len(Cargo_info_array))]
# print(result)

for i in range(len(Ship_info_array)):
    for j in range(len(Cargo_info_array)):
        M = Ship_info_array[i]["M_ship"]
        V = Ship_info_array[i]["V_ship"]
        m = Cargo_info_array[j]["m_cargo"]

        dis_ship_cargo = Loc_Correspond[Ship_info_array[i]["loc"]] - Loc_Correspond[Cargo_info_array[j]["depart"]]
        if dis_ship_cargo < 0:
            dis_ship_cargo = -dis_ship_cargo

        dis_transport = Loc_Correspond[Cargo_info_array[j]["depart"]] - Loc_Correspond[Cargo_info_array[j]["destin"]]
        if dis_transport < 0:
            dis_transport = -dis_transport

        time = (dis_ship_cargo + dis_transport) / V
        Cost = Ship_info_array[i]["Cost_trans"] * ((M + m) * dis_transport + M * dis_ship_cargo) + Ship_info_array[i][
            "Cost_live"] * time
        Profit = Cargo_info_array[j]["target_money"] - Cost

        result[i][j] = Profit

output = pd.DataFrame(data=result)


path = os.path.join(os.getcwd(), 'csv', 'result.csv')
output.to_csv(path, encoding='utf-8', index=False, header=False)
db.close_db(mydb)

input_data_path = path
input_data = pd.read_csv(input_data_path,header=None)  # 读取csv文件

# print(input_data)

input_data.head()
X = input_data.values.tolist()  # 将每一行数据转化成一个列表
graphlist = X

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

calout_path = os.path.join(os.getcwd(), 'csv', 'match_result.csv')

# 开始执行
if __name__ == '__main__':
    km = KM()  # 声明对象
    max_ = compute_km(a.copy())  # 使用a的一个副本，copy一个使用   max_是一个一维数组存匹配结果

    print("最大组合:", max_, a[[i[0] for i in max_], [i[1] for i in max_]])
    csvout = []
    for i in range(len(max_)):
        if max_[i][1] != -1:  # 有最佳匹配，则将匹配结果写入输出的csv二维数组
            csvout.append([max_[i][0], max_[i][1]])
    # with open一种文件打开机制，
    with open(calout_path, "w", encoding='utf-8', newline="") as f:

        writer = csv.writer(f)
        # writer.writerow(["index","a_name"])
        writer.writerows(csvout)
# 需要的工作是将这些信息进行计算，之后将权值存到一个文件中去
