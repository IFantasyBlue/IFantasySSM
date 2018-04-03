# -*- coding: utf-8 -*-
import scrapy
import json
from nba.items import NbaItem

class NbastatsSpider(scrapy.Spider):
    name = 'nbastats'
    allowed_domains = ['www.stat-nba.com']
    start_urls = ['http://www.stat-nba.com/player/%d.html' %i for i in range(1,4649,1)]

    def parse(self, response):

        print('parse start')
        details = response.xpath('/html/body/div[1]/div/div[4]/div[3]')
        datas = response.xpath('/html/body/div[1]/div/div[7]/div[3]/div[3]/table/tbody')
        rtgs = response.xpath('/html/body/div[1]/div/div[7]/div[3]/div[6]/table/tbody')
        item = NbaItem()
        item['valid'] = 1

        item['name'] = details.xpath(u"./div[contains(./div/text(),'全　　名:')]/text()").extract()[0]
        item['imgSrc'] = 'http://www.stat-nba.com'+response.xpath('/html/body/div[1]/div/div[4]/div[1]/img/@src').extract()[0]
        try:
            item['position'] = details.xpath(u"./div[contains(./div/text(),'位　　置:')]/text()").extract()[0]
        except IndexError:
            item['valid'] = 0
            pass
        try:
            item['height'] = details.xpath(u"./div[contains(./div/text(),'身　　高:')]/text()").extract()[0]
            item['height'] = item['height'][:item['height'].index(u'米')]
            item['weight'] = details.xpath(u"./div[contains(./div/text(),'体　　重:')]/text()").extract()[0]
            item['weight'] = item['weight'][:item['weight'].index(u'公斤')]
        except IndexError:
            item['valid'] = 0
            pass
        try:
            item['day'] = details.xpath(u"./div[contains(./div/text(),'出生日期:')]/text()").extract()[0]
            item['day'] = item['day'].replace(u'年','-').replace(u'月','-').replace(u'日','')
        except IndexError:
            item['valid'] = 0
            pass
        try:
            item['city'] = details.xpath(u"./div[contains(./div/text(),'出生城市:')]/text()").extract()[0]
        except IndexError:
            item['valid'] = 0
            pass
        try:
            item['hSch'] = details.xpath(u"./div[contains(./div/text(),'高　　中:')]/text()").extract()[0]
        except IndexError:
            item['hSch'] = 'null'
        try:
            item['university'] = details.xpath(u"./div[contains(./div/text(),'大　　学:')]/text()").extract()[0]
        except IndexError:
            item['university'] = 'null'
        try:
            item['number'] = details.xpath(u"./div[contains(./div/text(),'球衣号码:')]/text()").extract()[0]
        except IndexError:
            item['valid'] = 0
            pass

        try:
            item['draft'] = details.xpath(u"./div[contains(./div/text(),'选秀情况:')]").xpath('string(.)').extract()[0]
            item['draft'] = item['draft'].replace(u'选秀一览>>','')
        except IndexError:
            item['valid'] = 0
            pass

        item['last_season'] = datas.xpath('./tr[1]/td[2]/a/text()').extract()[0]

        if(datas.xpath('./tr[1]/td[3]/a/text()').extract()[0] == u'总计'):
            item['last_tm'] = datas.xpath('./tr[2]/td[3]/a/text()').extract()[0]
        else:
            item['last_tm'] = datas.xpath('./tr[1]/td[3]/a/text()').extract()[0]
        try:
            item['normal_season'] = datas.xpath('./tr[last()]/td[@class="normal season"]/text()').extract()[0]
            item['normal_tm'] = datas.xpath('./tr[last()]/td[@class="normal tm"]/text()').extract()[0]
            item['normal_g'] = datas.xpath('./tr[last()]/td[@class="normal g"]/text()').extract()[0]
            item['normal_gs'] = datas.xpath('./tr[last()]/td[@class="normal gs"]/text()').extract()[0]
            item['normal_mp'] = datas.xpath('./tr[last()]/td[@class="normal mp"]/text()').extract()[0]
            item['normal_fgper'] = datas.xpath('./tr[last()]/td[@class="normal fgper"]/text()').extract()[0]
            item['normal_fg'] = datas.xpath('./tr[last()]/td[@class="normal fg"]/text()').extract()[0]
            item['normal_fga'] = datas.xpath('./tr[last()]/td[@class="normal fga"]/text()').extract()[0]
            try:
                item['normal_fgper'] = float(item['normal_fgper'].strip("%")) / 100
            except ValueError:
                item['valid'] = 0
                pass
            item['normal_threepper'] = datas.xpath('./tr[last()]/td[@class="normal threepper"]/text()').extract()[0]
            try:
                item['normal_threepper'] = float(item['normal_threepper'].strip("%")) / 100
            except ValueError:
                item['valid'] = 0
                pass
            item['normal_threep'] = datas.xpath('./tr[last()]/td[@class="normal threep"]/text()').extract()[0]
            item['normal_threepa'] = datas.xpath('./tr[last()]/td[@class="normal threepa"]/text()').extract()[0]
            item['normal_ftper'] = datas.xpath('./tr[last()]/td[@class="normal ftper"]/text()').extract()[0]
            try:
                item['normal_ftper'] = float(item['normal_ftper'].strip("%")) / 100
            except ValueError:
                item['valid'] = 0
                pass
            item['normal_ft'] = datas.xpath('./tr[last()]/td[@class="normal ft"]/text()').extract()[0]
            item['normal_fta'] = datas.xpath('./tr[last()]/td[@class="normal fta"]/text()').extract()[0]
            item['normal_trb'] = datas.xpath('./tr[last()]/td[@class="normal trb"]/text()').extract()[0]
            item['normal_orb'] = datas.xpath('./tr[last()]/td[@class="normal orb"]/text()').extract()[0]
            item['normal_drb'] = datas.xpath('./tr[last()]/td[@class="normal drb"]/text()').extract()[0]
            item['normal_ast'] = datas.xpath('./tr[last()]/td[@class="normal ast"]/text()').extract()[0]
            item['normal_stl'] = datas.xpath('./tr[last()]/td[@class="normal stl"]/text()').extract()[0]
            item['normal_blk'] = datas.xpath('./tr[last()]/td[@class="normal blk"]/text()').extract()[0]
            item['normal_tov'] = datas.xpath('./tr[last()]/td[@class="normal tov"]/text()').extract()[0]
            item['normal_pf'] = datas.xpath('./tr[last()]/td[@class="normal pf"]/text()').extract()[0]
            item['normal_pts'] = datas.xpath('./tr[last()]/td[@class="normal pts"]/text()').extract()[0]
        except IndexError:
            item['valid'] = 0
            pass
        if(datas.xpath('//table[@id="stat_box_avg"]/thead/tr[1]/th[last()]/text()').extract()[0] == u'负'):
            item['normal_w'] = datas.xpath('./tr[last()]/td[@class="normal w"]/text()').extract()[0]
            item['normal_l'] = datas.xpath('./tr[last()]/td[@class="normal l"]/text()').extract()[0]
        try:
            item['normal_ortg'] = rtgs.xpath('./tr[last()]/td[@class="normal ortg"]/text()').extract()[0]
            item['normal_drtg'] = rtgs.xpath('./tr[last()]/td[@class="normal drtg"]/text()').extract()[0]
        except IndexError:
            item['valid'] = 0
            pass
        try:
            item['last_salary'] = response.xpath('//div[@id="player_salary"]/div[2]/div[1]/div[1]/table/tbody/tr[last()-2]/td[last()]/text()').extract()[0]
            item['last_salary'] = item['last_salary'][:-3]
        except IndexError:
            item['valid'] = 0
            pass

        yield item

        pass
