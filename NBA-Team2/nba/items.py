# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class NbaItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    valid = scrapy.Field()
    id = scrapy.Field()

    name = scrapy.Field()
    imgSrc = scrapy.Field()
    position = scrapy.Field()
    height = scrapy.Field()
    weight = scrapy.Field()
    day = scrapy.Field()
    city = scrapy.Field()
    hSch = scrapy.Field()
    university = scrapy.Field()
    number = scrapy.Field()
    number_Detail = scrapy.Field()
    draft = scrapy.Field()
    last_season = scrapy.Field()
    last_tm = scrapy.Field()
    last_salary = scrapy.Field()
    normal_season = scrapy.Field()
    normal_tm = scrapy.Field()
    normal_g = scrapy.Field()
    normal_gs = scrapy.Field()
    normal_mp = scrapy.Field()
    normal_fgper = scrapy.Field()
    normal_fg = scrapy.Field()
    normal_fga = scrapy.Field()
    normal_threepper = scrapy.Field()
    normal_threep = scrapy.Field()
    normal_threepa = scrapy.Field()
    normal_ftper = scrapy.Field()
    normal_ft = scrapy.Field()
    normal_fta = scrapy.Field()
    normal_trb = scrapy.Field()
    normal_orb = scrapy.Field()
    normal_drb = scrapy.Field()
    normal_ast = scrapy.Field()
    normal_stl = scrapy.Field()
    normal_blk = scrapy.Field()
    normal_tov = scrapy.Field()
    normal_pf = scrapy.Field()
    normal_pts = scrapy.Field()
    normal_w = scrapy.Field()
    normal_l = scrapy.Field()
    normal_ortg = scrapy.Field()
    normal_drtg = scrapy.Field()


    pass
