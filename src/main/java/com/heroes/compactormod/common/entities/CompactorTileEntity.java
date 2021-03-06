
package com.heroes.compactormod.common.entities;

import java.util.stream.IntStream;

import javax.annotation.Nullable;

import com.heroes.compactormod.common.gui.CompactorGui;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.minecraftforge.registries.ObjectHolder;

public class CompactorTileEntity extends LockableLootTileEntity implements ISidedInventory {

	public static final int INVENTORY_SIZE = 4;
	public static final int OUTPUT_INVENTORY_SIZE = 2;
	public static final int INPUT_INVENTORY_SIZE = 2;

	private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(INVENTORY_SIZE, ItemStack.EMPTY);

	@ObjectHolder("heroes_compactor_mod:compactor_te")
	public static final TileEntityType<CompactorTileEntity> tileEntityType = null;

	public CompactorTileEntity() {
		super(tileEntityType);
	}

	@Override
	public boolean tryLoadLootTable(CompoundNBT compound) {
		super.tryLoadLootTable(compound);
		this.stacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.stacks);
		return true;
	}

	@Override
	public boolean trySaveLootTable(CompoundNBT compound) {
		super.trySaveLootTable(compound);
		ItemStackHelper.saveAllItems(compound, this.stacks);
		return true;
	}

//
///*		Creo que es comentable. Creo que es de servidor y eso nosotros no lo tocamos?*/
//
//		@Override
//		public SUpdateTileEntityPacket getUpdatePacket() {
//			return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
//		}
//
//	@Override
//	public CompoundNBT getUpdateTag() {
//		return this.write(new CompoundNBT());
//	}
//
///*		Creo que es comentable. Creo que es de servidor y eso nosotros no lo tocamos?*/
//
//		@Override
//		public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//			this.read(this.getBlockState(), pkt.getNbtCompound());
//		}
//
	@Override
	public int getContainerSize() {
		return stacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.stacks)
			if (!itemstack.isEmpty())
				return false;
		return true;
	}

	@Override
	public ITextComponent getDefaultName() {
		return new StringTextComponent("compactor");
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

//	/*MENU*/
//	
	@Override
	public Container createMenu(int id, PlayerInventory player) {
		return new CompactorGui.GuiContainerMod(id, player,
				new PacketBuffer(Unpooled.buffer()).writeBlockPos(this.getBlockPos()));
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Compactor");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.stacks;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stacks) {
		this.stacks = stacks;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return IntStream.range(0, this.getContainerSize()).toArray();
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		if (index > INPUT_INVENTORY_SIZE)
			return false;
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		if (index <= INPUT_INVENTORY_SIZE)
			return false;
		return true;
	}

	private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (/* !this.removed && */facing != null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return handlers[facing.ordinal()].cast();
		return super.getCapability(capability, facing);
	}

//	@Override
//	public void remove() {
//		super.remove();
//		for (LazyOptional<? extends IItemHandler> handler : handlers)
//			handler.invalidate();
//	}
}