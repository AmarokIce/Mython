# -*- coding: utf-8 -*-

from lib.ItemTool import Item
from net.minecraft.creativetab import CreativeTabs


class Item(Item):
    def __init__(self):
        super(Item, self).__init__('item_name', 'example_modid', 64, 1)
        self.setCreativeTab(CreativeTabs.tabMisc)


item = Item()
