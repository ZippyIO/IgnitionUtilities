package com.zippy.ig.common;

public enum ModObject {
	  // Enderface
	  itemBasicCapacitor,
	  blockOven, // Enderface
	  blockEnderIo,
	  itemEnderface,

	  // Conduits
	  blockConduitBundle,
	  blockConduitFacade,
	  itemConduitFacade,
	  itemRedstoneConduit,
	  itemItemConduit,
	  itemMeConduit,
	  itemBasicFilterUpgrade,
	  itemExistingItemFilter,
	  itemExtractSpeedUpgrade,

	  // Power
	  itemPowerConduit,

	  // Liquid
	  itemLiquidConduit,

	  // Materials
	  itemAlloy,
	  itemMaterial,
	  itemMachinePart,
	  itemPowderIngot,
	  blockFusedQuartz,
	  itemFusedQuartzFrame,
	  blockDarkIronBars,

	  // Machines
	  blockStirlingGenerator,
	  blockCombustionGenerator,
	  blockZombieGenerator,
	  blockReservoir,
	  blockAlloySmelter,
	  blockSolarPanel,
	  blockCapacitorBank,
	  blockSagMill,
	  blockHyperCube,
	  blockPowerMonitor,
	  blockVat,
	  blockFarmStation,
	  blockTank,
	  blockCrafter,
	  blockVacuumChest, 
	  blockWirelessCharger,
	  blockEnchanter,
	  
	  blockPoweredSpawner,
	  itemBrokenSpawner,

	  blockElectricLight,
	  blockLightNode,
	  blockLight,
	  
	  //Blocks
	  blockDarkSteelPressurePlate,

	  // Painter
	  blockPainter,
	  blockPaintedFence,
	  blockPaintedFenceGate,
	  blockPaintedWall,
	  blockPaintedStair,
	  blockPaintedSlab,
	  blockPaintedDoubleSlab,
	  blockPaintedGlowstone,
	  blockPaintedCarpet,

	  itemConduitProbe,
	  itemYetaWrench,

	  blockTravelAnchor,
	  itemTravelStaff, 
	  itemMagnet, 
	  itemGliderWing;

	  public final String unlocalisedName;

	  private ModObject() {
	    this.unlocalisedName = name();
	  }

	}
