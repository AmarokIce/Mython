package club.someoneice.mython.util;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemTool extends Item {
    private int meta;
    private IIcon[] icon;

    public ItemTool(String name, String modid, int size, int meta) {
        this.setUnlocalizedName(name);
        this.setTextureName(modid + ":" + name);
        this.setMaxStackSize(size);
        this.setHasSubtypes(meta > 1);

        this.meta = meta;

        GameRegistry.registerItem(this, name, modid);
    }

    public ItemTool(String name, String modid) {
        this(name, modid, 64, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return meta < this.icon.length ? this.icon[meta] : this.icon[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon) {
        this.icon = new IIcon[meta];
        for (int i = 0; i < meta; i ++) this.icon[i] = icon.registerIcon(this.iconString + i);
    }
}
