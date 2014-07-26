package com.zippy.ig.common.tic;

import java.util.List;

import mantle.items.abstracts.CraftingItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.util.IToolPart;
import tconstruct.tools.TinkerTools;

public class Part extends CraftingItem implements IToolPart
{
    public String partName;

    public Part(String textureType, String name)
    {
        super(toolMaterialNames, buildTextureNames(textureType), "parts/", "tinker", TConstructRegistry.materialTab);
        this.setCreativeTab(TConstructRegistry.partTab);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.partName = name;
    }

    @Override
    public String getItemStackDisplayName (ItemStack par1ItemStack)
    {
        String material = StatCollector.translateToLocal("parttype." + toolTextureNames[par1ItemStack.getItemDamage()]);
        String name = StatCollector.translateToLocal("toolpart." + partName);
        name = name.replaceAll("%%material", material);

        return name;

        /*
         * StringBuilder builder = new StringBuilder();
         * builder.append(StatCollector.translateToLocal("parttype." +
         * toolTextureNames
         * [par1ItemStack.getItemDamage()])).append(" ").append(StatCollector
         * .translateToLocal("toolpart." + partName)); return
         * builder.toString(); -- String s = ("" +
         * StatCollector.translateToLocal("toolpart."+partName)).trim(); String
         * s1 = toolMaterialNames[par1ItemStack.getItemDamage()];
         * 
         * if (s1 != null) { s = s + " " + StatCollector.translateToLocal(s1); }
         * 
         * return s;
         */
    }

    private static String[] buildTextureNames (String textureType)
    {
        String[] names = new String[toolMaterialNames.length];
        for (int i = 0; i < toolMaterialNames.length; i++)
        {
            if (toolTextureNames[i].equals(""))
                names[i] = "";
            else
                names[i] = toolTextureNames[i] + textureType;
        }
        return names;
    }

    public static final String[] toolMaterialNames = new String[] { "Unobtanium", "Iridium" };

    public static final String[] toolTextureNames = new String[] { "Unobtanium", "Iridium" };

    @Override
    public int getMaterialID (ItemStack stack)
    {
        return stack.getItemDamage();
    }
}

