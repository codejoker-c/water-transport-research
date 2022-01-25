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


def init_db(db):
    with open('init.sql', encoding='utf-8', mode='r') as f1:
        db.executescript(f1.read())
    with open('test.sql', encoding='utf-8', mode='r') as f2:
        db.executescript(f2.read())


def close_db(db):
    if db is not None:
        db.close()
