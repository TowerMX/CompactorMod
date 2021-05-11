package net.mcreator.compactor.procedures;

import java.util.ArrayList;

import net.mcreator.compactor.CompactorModElements;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import com.heroes.compactormod.core.util.CompactorFunctions;




/***
 * 
 * El procedimiento se hace en este orden lo siguiente:
 * 
 *	1. Se identifica la posición del bloque y la entidad asociada a esa posición.
 *	2. Se recoge su inventario para ser procesado.
 *	3. Se procesa su inventario, siendo guardado lo más importante en variables dentro de la clase.
 *	4. Se verifica que en los stacks de input (que son todos los que comprenden entre el slot 0 y 
 *		el slot tamaño máximo de inventario - 2) haya suficientes items de los que se piden.
 *	5. Se verifica que en los stacks de output (que son todos los que comprenden entre slot tamaño
 *		máximo de inventario - 2 hasta el slot tamaño máximo de inventario) haya suficiente espacio
 *		para los items requeridos.
 *	6. Se efectúa primero el borrado de los inputs y después se introduce el output.
 * 
 * @author Nelnitorian
 *	
 */


public class CompactorTryProcedure {
	public CompactorTryProcedure() {
	
	}

	BlockPos blockPos = null;
	ArrayList<ItemStack> inputStack = null;
	ArrayList<ItemStack> outputStack = null;
	Item inputItem = null;
	Item outputItem = null;
	int inputButtonState = 0;
	int outputButtonState = 0;
	int compactorInventorySize = 0;
	int minimumStack = 0;
	int addAmount = 0;
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

		//Configuración
		this.compactorInventorySize = handler.getSlots();
		int i = 0;
		for (; i < (this.compactorInventorySize - 2); i++)
			this.inputStack.add(handler.getStackInSlot(i));
		for (; i < this.compactorInventorySize; i++)
			this.outputStack.add(handler.getStackInSlot(i));

		Item[] ioItemArray = CompactorFunctions.ioItem(this.inputStack, this.inputButtonState, this.outputButtonState); /* FUNCIÓN DE TOWER */
		this.inputItem = ioItemArray[0];
		this.outputItem = ioItemArray[1];
		int[] amounts = CompactorFunctions.stackAmounts(this.inputButtonState, this.outputButtonState); /* FUNCIÓN DE TOWER */
		this.minimumStack = amounts[0];
		this.addAmount = amounts[1];

//		MAIN CODE
		if (this.minimumStackAchieved())
			if (this.outputAvailableSpace())
				this.effectuateOperation();
	}
	
//	Chequea que haya suficientes items en el input 
	public boolean minimumStackAchieved() {
		int count = 0;
		for (ItemStack stack : inputStack) {
			if (stack.getItem() == inputItem)
				count += stack.getCount();
		}
		return count >= this.minimumStack;
	}

//	Chequea que haya suficiente espacio en el output
	private boolean outputAvailableSpace() {
		int availableSpace = 0;
		int stackAmount;
		int difference;
		for (ItemStack stack : outputStack) {
			if (stack.getItem() == outputStack) {
				stackAmount = stack.getCount();
				difference = stack.getMaxStackSize()-stackAmount;
				availableSpace += difference > 0 ? difference : 0;
			} else if(stack == ItemStack.EMPTY)
				availableSpace += stack.getMaxStackSize();
		}
		return availableSpace >= addAmount;
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
					stack = ItemStack.EMPTY;
					shrink -= amount;
				} else { // amount > shrink
					stack.setCount(amount - shrink);
					shrink = 0;
				}
				((IItemHandlerModifiable) handler).setStackInSlot(slot, stack);
			}
//					Now adding amount

			int add = this.addAmount;
			for (; slot < this.compactorInventorySize && add != 0; slot++) {
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
