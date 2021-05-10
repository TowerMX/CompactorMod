/*
package com.heroes.compactormod.container;

import java.util.Objects;

import com.heroes.compactormod.common.entities.CompactorTileEntity;
import com.heroes.compactormod.core.init.ContainerTypesInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class CompactorContainer extends Container {

	public final CompactorTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;

	protected CompactorContainer(final int windowId, final playerInventory playerInv, final CompactorTileEntity te) {
		super(ContainerTypesInit.COMPACTOR_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.of(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 47, 24)); // slot0 del gui, input de items, posicion pixel (47, 24)
		this.addSlot(new Slot((IInventory) te, 1, 110, 24)); // slot1 del gui, output de items, posicion pixel (110, 24)

		// Main PLayer Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
		}

	}

	protected CompactorContainer(final int windowId, final playerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static CompactorTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
		if (te instanceof CompactorTileEntity) {
			return (CompactorTileEntity) te;
		}
		throw new IllegalStateException("Tile entity is not correct");
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return false;
	}

	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (index < CompactorTileEntity.slots && !this.mergeItemStack(stack1, CompactorTileEntity.slots, this.inventorySlots.size(), false)) {
				return ItemStack.EMPTY;
			}
			if (!this.mergeItemStack(stack1, 0, CompactorTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}
			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY)
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}

}
*/