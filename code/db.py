import sqlite3
import os
import pandas

Database = os.path.join(os.getcwd(), 'database', 'data.db')  # 获取database下data.db的文件路径


def get_db():
    db = sqlite3.connect(
        Database,
        detect_types=sqlite3.PARSE_DECLTYPES
    )
    db.row_factory = sqlite3.Row
    return db


def init_db():
    db = get_db()
    with open('init.sql',encoding='utf-8',mode='r') as f:
        db.executescript(f.read())


