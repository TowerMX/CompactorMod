package net.mcreator.compactor.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.compactor.CompactorModElements;
import net.mcreator.compactor.CompactorMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Map;

@CompactorModElements.ModElement.Tag
public class CompactorTryProcedure extends CompactorModElements.ModElement {
	public CompactorTryProcedure(CompactorModElements instance) {
		super(instance, 5);
	}

	BlockPos blockPos = null;
	ArrayList<ItemStack> inputStack = null;
	ArrayList<ItemStack> outputStack = null;
	Item inputItem = null;
	Item outputItem = null;
	int compactorInventorySize = 0;
	int minimumStack = 0;
	IItemHandler handler = null;

	public static void executeProcedure(IWorld world, int x, int y, int z) {

		//El bloque no va a cambiar de posición
		blockPos = new BlockPos(x, y, z);

//		Conseguir el inventario de la entidad
		TileEntity entity = world.getTileEntity(blockPos);
		if (entity != null) {
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				this.handler = capability;
			});
		}

		this.compactorInventory = handler.getSlots();
		int i = 0;
		for (; i < (compactorInventory - 2); i++)
			inputStack.add(handler.getStackInSlot(i));
		for (; i < compactorInventory; i++)
			outputStack.add(handler.getStackInSlot(i));

		this.inputItem = this.inputItem(); /* FUNCIÓN DE TOWER */
		this.outputItem = this.outputItem(); /* FUNCIÓN DE TOWER */
		this.minimumStack = this.minimumStack(); /* FUNCIÓN DE TOWER */
		this.addAmount() = this.addAmount(); /* FUCIÓN DE TOWER */

		if (this.minimumStackAchieved())
			if (this.itemMatches())
				this.effectuateOperation();
	}

	public boolean minimumStackAchieved() {
		int count = 0;
		for (ItemStack stack : inputStack) {
			if (stack.getItem() == inputItem)
				count += stack.getCount();
		}
		return count >= this.minimumStack;
	}

//	Chequea que el item de la derecha es el que debería

	private boolean itemMatches() {
		return outputStack.getItem() == outputItem || outputStack == ItemStack.EMPTY;
	}

//	Efectúa la operación
	private void effectuateOperation() {

		if (handler instanceof IItemHandlerModifiable) {
//					Shrinking amount
			int amount = 0;
			int slot = 0;
			ItemStack stack = null;

			int inputStackSize = inputStack.size();
			int shrink = this.minimumStack;

			for (; slot < inputStackSize && shrink != 0; slot++) {
				stack = inputStack.get(slot);
				amount = stack.getCount();
				if (amount < shrink) {
					stack.setCount(0);
					shrink -= amount;
				} else { // amount > shrink
					stack.setCount(amount - shrink);
					shrink = 0;
				}
				((IItemHandlerModifiable) handler).setStackInSlot(slot, stack);
			}
//					Now adding amount
			boolean finished = false;
			for (; slot < this.compactorInventory && add != 0; slot++) {
				stack = inputStack.get(slot - inputStackSize);
				amount = stack.getCount();
				int add = this.addAmount;
				int addable = (stack.getMaxStackSize() - amount);
				if (addable < add) {
					stack.setCount(amount + addable);
					add -= addable;
				} else { // addable>add
					stack.setCount(amount + add);
					add = 0;
				}
				((IItemHandlerModifiable) handler).setStackInSlot(slot, stack);
			}
		}
	}
}
