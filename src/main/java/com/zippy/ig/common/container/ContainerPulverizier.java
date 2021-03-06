package com.zippy.ig.common.container;

import com.zippy.ig.common.tile.TilePulverizier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerPulverizier extends Container {
	
	private TilePulverizier tileEntity;
	private int lastTimer;
	private int lastCookTime;
	private int lastEnergyLevel;
	private int lastBatteryLevel;
	private int lastBatteryMax;
	
	public ContainerPulverizier(InventoryPlayer inventoryPlayer, TilePulverizier tEntity) {
		tileEntity = tEntity;
		// the Slot constructor takes the IInventory and the slot number in that
		// it binds to
		// and the x-y coordinates it resides on-screen
		addSlotToContainer(new Slot(tileEntity, TilePulverizier.slotInput, 50, 58));
		addSlotToContainer(new SlotOutput(tileEntity, TilePulverizier.slotOutput, 109, 58));
		addSlotToContainer(new SlotBattery(tileEntity, TilePulverizier.slotBattery, 29, 82));
		addSlotToContainer(new SlotFurnaceUpgrade(tileEntity, TilePulverizier.slotUpgrade1, 152, 42));
		addSlotToContainer(new SlotFurnaceUpgrade(tileEntity, TilePulverizier.slotUpgrade2, 152, 60));
		addSlotToContainer(new SlotFurnaceUpgrade(tileEntity, TilePulverizier.slotUpgrade3, 152, 78));
				
		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tileEntity.isUseableByPlayer(entityplayer);
	}
	
	void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 119 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 177));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);
		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			
			// merges the item into player inventory since its in the tileEntity
			if (slot <= 6) {
				if (!this.mergeItemStack(stackInSlot, 7, 42, true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else {
				boolean foundSlot = false;
				for (int i = 0; i < 7; i++){
					if (((Slot)inventorySlots.get(i)).isItemValid(stackInSlot) && this.mergeItemStack(stackInSlot, i, i + 1, false)) {
						foundSlot = true;
						break;
					}
				}
				if (!foundSlot){
					return null;
				}
			}
			
			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			}
			else {
				slotObject.onSlotChanged();
			}
			
			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		
		return stack;
	}
	
	@Override
	protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards)
	{
		boolean flag1 = false;
		int k = (backwards ? end - 1 : start);
		Slot slot;
		ItemStack itemstack1;

		if (stack.isStackable())
		{
			while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start))
			{
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					continue;
				}

				if (itemstack1 != null && itemstack1.getItem() == stack.getItem() &&
						(!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage()) &&
						ItemStack.areItemStackTagsEqual(stack, itemstack1))
				{
					int l = itemstack1.stackSize + stack.stackSize;

					if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
						stack.stackSize = 0;
						itemstack1.stackSize = l;
						tileEntity.markDirty();
						flag1 = true;
					} else if (itemstack1.stackSize < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
						stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
						itemstack1.stackSize = stack.getMaxStackSize();
						tileEntity.markDirty();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}

		if (stack.stackSize > 0)
		{
			k = (backwards ? end - 1 : start);

			while (!backwards && k < end || backwards && k >= start) {
				slot = (Slot) inventorySlots.get(k);
				itemstack1 = slot.getStack();

				if (!slot.isItemValid(stack)) {
					continue;
				}

				if (itemstack1 == null) {
					int l = stack.stackSize;

					if (l <= slot.getSlotStackLimit()) {
						slot.putStack(stack.copy());
						stack.stackSize = 0;
						tileEntity.markDirty();
						flag1 = true;
						break;
					} else {
						putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
						stack.stackSize -= slot.getSlotStackLimit();
						tileEntity.markDirty();
						flag1 = true;
					}
				}

				k += (backwards ? -1 : 1);
			}
		}

		return flag1;
	}
	
	/**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastTimer != this.tileEntity.furnaceTimer)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.furnaceTimer);
            }
            if (this.lastCookTime != this.tileEntity.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.furnaceCookTime);
            }
            if (this.lastEnergyLevel != this.tileEntity.energyLevel)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.energyLevel);
            }
            if (this.lastBatteryLevel != this.tileEntity.batteryLevel)
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.batteryLevel);
            }
            if (this.lastBatteryMax != this.tileEntity.batteryMax)
            {
                icrafting.sendProgressBarUpdate(this, 4, (int) this.tileEntity.batteryMax);
            }
        }

        this.lastTimer = this.tileEntity.furnaceTimer;
        this.lastEnergyLevel = this.tileEntity.energyLevel;
        this.lastBatteryLevel = this.tileEntity.batteryLevel;
        this.lastBatteryMax = (int) this.tileEntity.batteryMax;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tileEntity.furnaceTimer = par2;
        }
        if (par1 == 1)
        {
            this.tileEntity.furnaceCookTime = par2;
        }
        if (par1 == 2)
        {
            this.tileEntity.energyLevel = par2;
        }
        if (par1 == 3)
        {
            this.tileEntity.batteryLevel = par2;
        }
        if (par1 == 4)
        {
            this.tileEntity.batteryMax = par2;
        }
    }
}
