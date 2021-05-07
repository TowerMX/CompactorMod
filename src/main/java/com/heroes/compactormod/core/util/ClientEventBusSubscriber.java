package com.heroes.compactormod.core.util;

import com.heroes.compactormod.CompactorMod;
import com.heroes.compactormod.client.screen.CompactorScreen;
import com.heroes.compactormod.core.init.ContainerTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CompactorMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.register(ContainerTypesInit.COMPACTOR_CONTAINER_TYPE.get(), CompactorScreen::new);
	}
}
