# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from twisted.enterprise import adbapi
import MySQLdb
import MySQLdb.cursors

class NbaPipeline(object):

    i = 0
    def __init__(self):

        dbargs = dict(
            host='192.168.0.251',
            db='team2',
            user='team2',
            passwd='12345qwert',
            charset='utf8',
            cursorclass=MySQLdb.cursors.DictCursor,
            use_unicode=True,
            )
        self.dbpool = adbapi.ConnectionPool('MySQLdb',**dbargs)


    def process_item(self, item, spider):

        if (item['valid'] == 1):
            item['id'] = self.i
            self.i+=1
            res = self.dbpool.runInteraction(self.insert_into_table,item)
        return item

    def insert_into_table(self,conn,item):
        print('import stats started')
        conn.execute(
            """insert into players_stats(
              ID,gtime,point,rebound,ato,
              assist,steal,block,
              shot,shotRate,
              threeShot,threeRate,
              freeShot,freeRate,
              efg,
              TS,
              offRtg,
              defRtg
              ) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)""",
             (item['id'],item['normal_mp'],item['normal_pts'],
              item['normal_trb'],0,item['normal_ast'],item['normal_stl'],
              item['normal_blk'],
              item['normal_fga'],item['normal_fgper'],
              item['normal_threepa'],item['normal_threepper'],
              item['normal_fta'], item['normal_ftper'],
              round((float(item['normal_fg'])+0.5*float(item['normal_threep']))/(float(item['normal_fga'])+float(item['normal_threepa'])),3),
              round(float(item['normal_pts'])/(2*(float(item['normal_fga'])+0.44*float(item['normal_fta']))),3),
              item['normal_ortg'],item['normal_drtg'])
        )
        print('import stats finished')
        print('import info started')
        conn.execute(
            """insert into players_info(
              id,birth,nation,draft,height,weight,number
            ) values(%s,%s,%s,%s,%s,%s,%s)
            """,
            (item['id'],item['day'],item['city'],item['draft'],
             item['height'],item['weight'],item['number'])
        )
        print('import info finished')
        conn.execute(
            """insert into players(
              ID,name,salary,info_id,stats_ID,team,position,contract
            ) values(%s,%s,%s,%s,%s,%s,%s,%s)
            """,
            (item['id'], item['name'], item['last_salary'], item['id'], item['id'], item['last_tm'], item['position'],
             "null")
        )

