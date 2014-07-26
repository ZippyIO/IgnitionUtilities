package com.zippy.ig.common.registry;

import net.minecraft.item.Item;

import com.zippy.ig.common.tic.Part;

import tconstruct.library.TConstructRegistry;
import tconstruct.library.client.TConstructClientRegistry;
import tconstruct.tools.TinkerTools;
import tconstruct.tools.items.Bowstring;
import tconstruct.tools.items.Fletching;

public class IGTiC {

    public static Item binding;
    public static Item toughBinding;
    public static Item toughRod;
    public static Item largePlate;
    public static Item pickaxeHead;
    public static Item shovelHead;
    public static Item hatchetHead;
    public static Item frypanHead;
    public static Item signHead;
    public static Item chiselHead;
    public static Item scytheBlade;
    public static Item broadAxeHead;
    public static Item excavatorHead;
    public static Item hammerHead;
    public static Item swordBlade;
    public static Item largeSwordBlade;
    public static Item knifeBlade;
    public static Item wideGuard;
    public static Item handGuard;
    public static Item crossbar;
    public static Item fullGuard;
    public static Item bowstring;
    public static Item arrowhead;
    public static Item fletching;
	
	public static void preInit() {
		//TConstructRegistry.addToolMaterial(materialID, materialName, harvestLevel, durability, miningspeed, attack, handleModifier, reinforced, stonebound, style, ability);
		
		
		
		
		
		TConstructRegistry.addToolMaterial(200, "Iridium", 4, 1300, 1400, 3, 1.8F, 0, 0.0F, "§D", "");
		TConstructRegistry.addToolMaterial(201, "Unobtanium", 8, 6000, 1400, 8, 3.5F, 0, 0.0F, "", "");
		TConstructClientRegistry.addMaterialRenderMapping(200, "ExtraTiC", "metallurgy3/nether/amordrine", true);
		TConstructClientRegistry.addMaterialRenderMapping(201, "ExtraTiC", "cobalt", true);
		
		pickaxeHead = new Part("_pickaxe_head", "PickHead").setUnlocalizedName("ignitionutilities.PickaxeHead");
        shovelHead = new Part("_shovel_head", "ShovelHead").setUnlocalizedName("ignitionutilities.ShovelHead");
        hatchetHead = new Part("_axe_head", "AxeHead").setUnlocalizedName("ignitionutilities.AxeHead");
        binding = new Part("_binding", "Binding").setUnlocalizedName("ignitionutilities.Binding");
        toughBinding = new Part("_toughbind", "ToughBind").setUnlocalizedName("ignitionutilities.ThickBinding");
        toughRod = new Part("_toughrod", "ToughRod").setUnlocalizedName("ignitionutilities.ThickRod");
        largePlate = new Part("_largeplate", "LargePlate").setUnlocalizedName("ignitionutilities.LargePlate");

        swordBlade = new Part("_sword_blade", "SwordBlade").setUnlocalizedName("ignitionutilities.SwordBlade");
        wideGuard = new Part("_large_guard", "LargeGuard").setUnlocalizedName("ignitionutilities.LargeGuard");
        handGuard = new Part("_medium_guard", "MediumGuard").setUnlocalizedName("ignitionutilities.MediumGuard");
        crossbar = new Part("_crossbar", "Crossbar").setUnlocalizedName("ignitionutilities.Crossbar");
        knifeBlade = new Part("_knife_blade", "KnifeBlade").setUnlocalizedName("ignitionutilities.KnifeBlade");
        fullGuard = new Part("_full_guard", "FullGuard").setUnlocalizedName("ignitionutilities.FullGuard");

        frypanHead = new Part("_frypan_head", "FrypanHead").setUnlocalizedName("ignitionutilities.FrypanHead");
        signHead = new Part("_battlesign_head", "SignHead").setUnlocalizedName("ignitionutilities.SignHead");
        chiselHead = new Part("_chisel_head", "ChiselHead").setUnlocalizedName("ignitionutilities.ChiselHead");

        scytheBlade = new Part("_scythe_head", "ScytheHead").setUnlocalizedName("ignitionutilities.ScytheBlade");
        broadAxeHead = new Part("_lumberaxe_head", "LumberHead").setUnlocalizedName("ignitionutilities.LumberHead");
        excavatorHead = new Part("_excavator_head", "ExcavatorHead").setUnlocalizedName("ignitionutilities.ExcavatorHead");
        largeSwordBlade = new Part("_large_sword_blade", "LargeSwordBlade").setUnlocalizedName("ignitionutilities.LargeSwordBlade");
        hammerHead = new Part("_hammer_head", "HammerHead").setUnlocalizedName("ignitionutilities.HammerHead");
        arrowhead = new Part("_arrowhead", "ArrowHead").setUnlocalizedName("ignitionutilities.Arrowhead");
	}

}
