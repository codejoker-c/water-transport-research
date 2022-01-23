DROP TABLE IF EXISTS ship;
DROP TABLE IF EXISTS cargo;


CREATE TABLE ship(
    id INTEGER PRIMARY KEY,
    loc TEXT,  /* 船所在地 */
    M_ship FLOAT,
    V_ship FLOAT,
    Cost_trans FLOAT,
    Cost_live FLOAT
);


CREATE TABLE cargo(
    id INTEGER PRIMARY KEY,
    depart TEXT,  /* 出发地 */
    destin TEXT,  /* 终点 */
    m_cargo FLOAT
);