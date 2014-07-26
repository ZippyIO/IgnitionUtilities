package com.zippy.ig.common.container;

import java.util.Map;

import com.zippy.ig.common.tileentity.TileEntityMachineRoaster;

import mekanism.api.AdvancedInput;
import mekanism.api.infuse.InfuseRegistry;
import mekanism.api.infuse.InfusionInput;
import mekanism.common.inventory.slot.SlotMachineUpgrade;
import mekanism.common.inventory.slot.SlotOutput;
import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import mekanism.common.item.ItemMachineUpgrade;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.tile.TileEntityAdvancedElectricMachine;
import mekanism.common.util.ChargeUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMachineRoaster extends Container {
	private TileEntityMachineRoaster tileEntity;

	public ContainerMachineRoaster(InventoryPlayer inventory, TileEntityMachineRoaster tentity)
	{
		tileEntity = tentity;
		addSlotToContainer(new Slot(tentity, 0, 56, 17));
		addSlotToContainer(new Slot(tentity, 1, 56, 53));
		addSlotToContainer(new SlotOutput(tentity, 2, 116, 35));
		addSlotToContainer(new SlotDischarge(tentity, 3, 31, 35));
		addSlotToContainer(new SlotMachineUpgrade(tentity, 4, 180, 11));

		int slotX;

		for(slotX = 0; slotX < 3; ++slotX)
		{
			for(int slotY = 0; slotY < 9; ++slotY)
			{
				addSlotToContainer(new Slot(inventory, slotY + slotX * 9 + 9, 8 + slotY * 18, 84 + slotX * 18));
			}
		}

		for(slotX = 0; slotX < 9; ++slotX)
		{
			addSlotToContainer(new Slot(inventory, slotX, 8 + slotX * 18, 142));
		}

		tileEntity.open(inventory.player);
		tileEntity.openInventory();
	}

	@Override
	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);

		tileEntity.close(entityplayer);
		tileEntity.closeInventory();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return tileEntity.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack stack = null;
		Slot currentSlot = (Slot)inventorySlots.get(slotID);

		if(currentSlot != null && currentSlot.getHasStack())
		{
			ItemStack slotStack = currentSlot.getStack();
			stack = slotStack.copy();

			if(slotID == 2)
			{
				if(!mergeItemStack(slotStack, 5, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if(ChargeUtils.canBeDischarged(slotStack))
			{
				if(slotID != 3)
				{
					if(!mergeItemStack(slotStack, 3, 4, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 5, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}
			else if(isInputItem(slotStack))
			{
				if(slotID != 0)
				{
					if(!mergeItemStack(slotStack, 0, 1, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 5, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}
			else if(slotStack.getItem() instanceof ItemMachineUpgrade)
			{
				if(slotID != 4)
				{
					if(!mergeItemStack(slotStack, 4, 5, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 5, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}
			else {
				if(slotID >= 5 && slotID <= 31)
				{
					if(!mergeItemStack(slotStack, 32, inventorySlots.size(), false))
					{
						return null;
					}
				}
				else if(slotID > 31)
				{
					if(!mergeItemStack(slotStack, 5, 31, false))
					{
						return null;
					}
				}
				else {
					if(!mergeItemStack(slotStack, 5, inventorySlots.size(), true))
					{
						return null;
					}
				}
			}

			if(slotStack.stackSize == 0)
			{
				currentSlot.putStack((ItemStack)null);
			}
			else {
				currentSlot.onSlotChanged();
			}

			if(slotStack.stackSize == stack.stackSize)
			{
				return null;
			}

			currentSlot.onPickupFromSlot(player, slotStack);
		}

		return stack;
	}

	private boolean isInputItem(ItemStack itemstack)
	{
		for(Map.Entry<AdvancedInput, ItemStack> entry : ((Map<AdvancedInput, ItemStack>)tileEntity.getRecipes()).entrySet())
		{
			if(entry.getKey().itemStack.isItemEqual(itemstack))
			{
				return true;
			}
		}

		return false;
	}
}