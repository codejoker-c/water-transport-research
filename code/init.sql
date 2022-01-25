DROP TABLE IF EXISTS ship;
DROP TABLE IF EXISTS cargo;


CREATE TABLE ship(
    id INTEGER PRIMARY KEY,
    loc TEXT NOT NULL,  /* 船所在地 */
    M_ship FLOAT NOT NULL, /* 船重  kg */
    V_ship FLOAT NOT NULL, /* 船速  km/h */
    Cost_trans FLOAT NOT NULL, /* 运输成本 元/km */
    Cost_live FLOAT NOT NULL /* 生活成本 元/day */
);


CREATE TABLE cargo(
    id INTEGER PRIMARY KEY,
    depart TEXT NOT NULL,  /* 出发地 */
    destin TEXT NOT NULL,  /* 终点 */
    m_cargo FLOAT NUO NULL, /* 货重 kg */
    target_money FLOAT NUO NULL /*目标价钱*/
);

