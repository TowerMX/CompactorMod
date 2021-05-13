package com.heroes.compactormod.common.procedures;

import java.util.ArrayList;

import com.heroes.compactormod.common.entities.CompactorTileEntity;
import com.heroes.compactormod.core.util.CompactorFunctions;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;




/***
 * 
 * El procedimiento se hace en este orden lo siguiente:
 * 
 *	1. Se identifica la posici�n del bloque y la entidad asociada a esa posici�n.
 *	2. Se recoge su inventario para ser procesado.
 *	3. Se procesa su inventario, siendo guardado lo m�s importante en variables dentro de la clase.
 *	4. Se verifica que en los stacks de input (que son todos los que comprenden entre el slot 0 y 
 *		el slot tama�o m�ximo de inventario - 2) haya suficientes items de los que se piden.
 *	5. Se verifica que en los stacks de output (que son todos los que comprenden entre slot tama�o
 *		m�ximo de inventario - 2 hasta el slot tama�o m�ximo de inventario) haya suficiente espacio
 *		para los items requeridos.
 *	6. Se efect�a primero el borrado de los inputs y despu�s se introduce el output.
 * 
 * @author Nelnitorian
 *	
 */


public class CompactorTryProcedure {
	public CompactorTryProcedure() {
	
	}

	static BlockPos blockPos = null;
	static ArrayList<ItemStack> inputStack = new ArrayList<>(CompactorTileEntity.INPUT_INVENTORY_SIZE);
	static ArrayList<ItemStack> outputStack = new ArrayList<>(CompactorTileEntity.OUTPUT_INVENTORY_SIZE);
	static Item inputItem = null;
	static Item outputItem = null;
	static int inputButtonState = 2; // Test: int del 0 al 2
	static int outputButtonState = 1; // Test: int del 0 al 2
	static int compactorInventorySize = 0;
	static int minimumStack = 0;
	static int addAmount = 0;
	static IItemHandler handler = null;

	public static void executeProcedure(IWorld world, int x, int y, int z) { // Ahora el m�todo no es static (daba errores que lo fuera) - Tower

		//El bloque no va a cambiar de posici�n
		blockPos = new BlockPos(x, y, z);

//		Conseguir el inventario de la entidad
		TileEntity entity = world.getBlockEntity(blockPos);
		if (entity != null) {
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				handler = capability;
			});
		}

//		inputStack = new ArrayList<>(CompactorTileEntity.INPUT_INVENTORY_SIZE);
//		outputStack = new ArrayList<>(CompactorTileEntity.OUTPUT_INVENTORY_SIZE);
		
		//Configuraci�n
		compactorInventorySize = handler.getSlots();
		int i = 0;
		for (; i < (CompactorTileEntity.INPUT_INVENTORY_SIZE); i++)
			inputStack.set(i,handler.getStackInSlot(i));
		for (; i < compactorInventorySize; i++)
			outputStack.set(i-CompactorTileEntity.INPUT_INVENTORY_SIZE,handler.getStackInSlot(i));

		Item[] ioItemArray = CompactorFunctions.ioItem(inputStack, inputButtonState, outputButtonState); /* FUNCI�N DE TOWER */
		inputItem = ioItemArray[0];
		outputItem = ioItemArray[1];
		int[] amounts = CompactorFunctions.stackAmounts(inputButtonState, outputButtonState); /* FUNCI�N DE TOWER */
		minimumStack = amounts[0];
		addAmount = amounts[1];

//		MAIN CODE
		if (minimumStackAchieved())
			if (outputAvailableSpace())
				effectuateOperation();
	}
	
//	Chequea que haya suficientes items en el input 
	public static boolean minimumStackAchieved() {
		int count = 0;
		for (ItemStack stack : inputStack) {
			if (stack.getItem() == inputItem)
				count += stack.getCount();
		}
		return count >= minimumStack;
	}

//	Chequea que haya suficiente espacio en el output
	private static boolean outputAvailableSpace() {
		int availableSpace = 0;
		int stackAmount;
		int difference;
		for (ItemStack stack : outputStack) {
			if (stack.getItem() == outputItem) { // Cambiado outputStack por outputItem - Tower
				stackAmount = stack.getCount();
				difference = stack.getMaxStackSize()-stackAmount;
				availableSpace += difference > 0 ? difference : 0;
			} else if(stack == ItemStack.EMPTY)
				availableSpace += stack.getMaxStackSize();
		}
		return availableSpace >= addAmount;
	}

//	Efect�a la operaci�n
	private static void effectuateOperation() {
		if (handler instanceof IItemHandlerModifiable) {
//					Shrinking amount
			int amount = 0;
			int slot = 0;
			ItemStack stack = null;

			int inputStackSize = inputStack.size();
			int shrink = minimumStack;

			for (; slot < inputStackSize && shrink != 0; slot++) {
				stack = inputStack.get(slot);
				amount = stack.getCount();
				if (amount < shrink) {
					stack = ItemStack.EMPTY;
					shrink -= amount;
				} else { // amount > shrink
					stack.setCount(amount - shrink);
					shrink = 0;
				}
				((IItemHandlerModifiable) handler).setStackInSlot(slot, stack);
			}
//					Now adding amount

			int add = addAmount;
			for (; slot < compactorInventorySize && add != 0; slot++) {
				stack = outputStack.get(slot - inputStackSize);
				if(stack==ItemStack.EMPTY)
					stack = new ItemStack(outputItem, 0);
				amount = stack.getCount();
				int addable = (stack.getMaxStackSize() - amount);
				if (addable < add) {
					stack.setCount(amount + addable);
					add -= addable;
				} else { // addable > add
					stack.setCount(amount + add);
					add = 0;
				}
				((IItemHandlerModifiable) handler).setStackInSlot(slot, stack);
			}
		}
	}
}
