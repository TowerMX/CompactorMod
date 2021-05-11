package com.heroes.compactormod.core.util;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

/***
 * 
 * Viendo que esto queda chulo yo también lo pongo 8)
 * 
 * En los buttonState se debe introducir un valor entre 0 y 2 que corresponde
 * al estado del botón, que significan lo siguiente:
 * 
 * 0: Bloque 
 * 1: Lingote 
 * 2: Nugget 
 * 
 * Devuelve el item válido más a la izquierda, null e caso de no haber ningún
 * item válidp
 * 
 * @author TowerMX
 * 
 */

public class CompactorFunctions {

	public static Item[] ioItem(ArrayList<ItemStack> itemStackArray, int inputButtonState, int outputButtonState) {

		Item inputItem = null;
		Item outputItem = null;
		Item currentItem;
		String currentItemDesc;
		String[] buttonDecoder1 = { "block.", "item.", "item." };
		String[] buttonDecoder2 = { "block", "ingot", "nugget" };
		int arraySize = itemStackArray.size();

		for (int i = 0; i < arraySize; i++) {
			currentItem = itemStackArray.get(i).getItem();
			currentItemDesc = currentItem.getDescriptionId();
			if (currentItemDesc.contains(".minecraft.iron_" + buttonDecoder2[inputButtonState])
					|| currentItemDesc.contains(".minecraft.gold_" + buttonDecoder2[inputButtonState])) {
				// Ahora vuestros ojos no necesitan protección para ver esto 8)
				inputItem = currentItem;
				outputItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(
						currentItemDesc.replace(buttonDecoder1[inputButtonState], buttonDecoder1[outputButtonState])
								.replace(buttonDecoder2[inputButtonState], buttonDecoder2[outputButtonState])));
				break;
			}
		}

		Item[] ioItemArray = { inputItem, outputItem };
		return ioItemArray;

	}

	public static int[] stackAmounts(int inputButtonState, int outputButtonState) {

		int input = inputButtonState;
		int output = outputButtonState;

		if (input >= output) {
			input -= output;
			output = 0;
		} else {
			output -= input;
			input = 0;
		}

		int[] amounts = { 9 ^ input, 9 ^ output };
		return amounts;

	}

}
