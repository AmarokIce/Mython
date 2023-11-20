# -*- coding: utf-8 -*-

from club.someoneice.mython.util import ItemTool


class Item(ItemTool):
    def __init__(self, name, modid, maxSize, maxSubMeta):
        super(Item, self).__init__(name, modid, maxSize, maxSubMeta)

    def set_creative_tab(self, tab):
        self.setCreativeTab(tab)


def create_item_simple(name, modid):
    return Item(name, modid, 64, 1)
