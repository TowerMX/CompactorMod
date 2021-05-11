
package com.heroes.compactormod.common.entities;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.container.CompactorContainer;
import com.heroes.compactormod.core.init.TileEntityTypesInit;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CompactorTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

	private static int TICKS = 100; // Cantidad de ticks a los que mirar el inventario
	private int tickCounter = 1; // Contador de ticks

	public static int slots = 2;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	public CompactorTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public CompactorTileEntity() {
		this(TileEntityTypesInit.COMPACTOR_TILE_ENTITY_TYPE.get());
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + CompactorMod.MOD_ID + ".compactor");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new CompactorContainer(id, player, this);
	}

	@Override
	public boolean tryLoadLootTable(CompoundNBT compound) {
		return super.tryLoadLootTable(compound);
	}

	@Override
	protected boolean trySaveLootTable(CompoundNBT compound) {
		return super.trySaveLootTable(compound);
	}

	@Override
	public void tick() {
		if (tickCounter++ == CompactorTileEntity.TICKS) {

		}

	}

//	Dónde se suscribe? Creo que en CompactorMod, pero no estoy seguro.

	@SubscribeEvent
	public static void registerTE(RegistryEvent.Register<TileEntityType<?>> evt) {
		TileEntityType<?> type = TileEntityType.Builder.create(factory, BlockInit.BLOCKS).build(null);
		type.setRegistryName("mymod", "myte");
		evt.getRegistry().register(type);
	}

}
