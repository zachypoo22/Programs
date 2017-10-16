import sqlite3
import time
import datetime
import random

conn = sqlite3.connect('dbase.db')
c = conn.cursor()

def createTable():
    c.execute('CREATE TABLE IF NOT EXISTS  stuffToPlot(unix REAL, datestamp TEXT, keyword TEXT, value REAL)')

def dataEntry():
    c.execute("INSERT INTO stuffToPlot VALUES(15654, '2016-01-01', 'python', 5 )")
    conn.commit()
    c.close()
    conn.close()
def varDataEntry():
    v1 = time.time()
    date = str(datetime.datetime.fromtimestamp(v1).strftime('%Y-%m-%d %H:%M:%S'))
    keyword = 'worddog'
    value = random.randrange(0,10)
    c.execute("INSERT INTO stuffToPlot (unix, datestamp, keyword, value) VALUES (?, ?, ?, ?)", (v1, date, keyword, value))
    conn.commit()

def readDB():
    c.execute("SELECT * FROM stuffToPlot WHERE VALUE = 3")
    # data = c.fetchall()
    for row in c.fetchall():
        print(row)

readDB()
# createTable()
# #dataEntry()
#
# for i in range(10):
#     varDataEntry()
#     time.sleep(1)

c.close()
conn.close()

