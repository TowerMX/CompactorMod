package com.heroes.compactormod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heroes.compactormod.core.init.BlockInit;
import com.heroes.compactormod.core.init.ItemInit;

import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CompactorMod.MOD_ID)
public class CompactorMod {
	
	public static final String MOD_ID = "heroes_compactor_mod";
	
    public static final Logger LOGGER = LogManager.getLogger();

    public CompactorMod() {
    	
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    	
        ItemInit.ITEMS.register(bus);
    	BlockInit.BLOCKS.register(bus);
    	//ContainerTypesInit.CONTAINER_TYPES.register(bus);

		//LOGGER.debug("buenas");
    	
    }

	private void setup(final FMLCommonSetupEvent event) {
	}

}