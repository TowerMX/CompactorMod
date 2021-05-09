package com.heroes.compactormod.core.util;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CompactorFunctions {

	// Se debe introducir un valor entre 0 y 2 que corresponde al estado del botón
	// 0: Bloque
	// 1: Lingote
	// 2: Nugget
	// Devuelve el item válido más a la izquierda, null e caso de no haber ningún
	// item válido

	public static Item ioItem(ArrayList<ItemStack> itemStackArray, int buttonState) {
		Item itemType = null;
		int arraySize = itemStackArray.size();
		for (int i = 0; i < arraySize; i++) {
			Item currentItem = itemStackArray.get(i).getItem();
			if ((buttonState == 0 && (currentItem == Items.IRON_BLOCK || currentItem == Items.GOLD_BLOCK))
					|| (buttonState == 1 && (currentItem == Items.IRON_INGOT || currentItem == Items.GOLD_INGOT))
					|| (buttonState == 2 && (currentItem == Items.IRON_NUGGET || currentItem == Items.GOLD_NUGGET))) {
				itemType = currentItem;
			}
		}
		return itemType;
	}

	public static int minimumStack() {
		return 0;
	}

	public static void addAmount() {

	}

}
