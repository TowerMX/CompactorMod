package com.heroes.compactormod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.heroes.compactormod.core.init.BlockInit;
import com.heroes.compactormod.core.init.ItemInit;
import com.heroes.compactormod.core.init.TileEntityTypesInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(CompactorMod.MOD_ID)
public class CompactorMod {
	
	public static final String MOD_ID = "heroes_compactor_mod";
	
    public static final Logger LOGGER = LogManager.getLogger();
    
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("compactor", "compactor"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public CompactorMod() {
    	
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    	
        ItemInit.ITEMS.register(bus);
    	BlockInit.BLOCKS.register(bus);
    	TileEntityTypesInit.TILE_ENTITIES.register(bus);
    	//ContainerTypesInit.CONTAINER_TYPES.register(bus);
    	
    	ItemStack stack = new ItemStack(Items.HONEYCOMB_BLOCK, 0);
    	LOGGER.debug(stack.isEmpty());
    	LOGGER.debug(stack == ItemStack.EMPTY);
    	LOGGER.debug(stack.getItem().getDescriptionId());
    	LOGGER.debug(stack.getCount());

		LOGGER.debug("buenas");
    	
    }

	private void setup(final FMLCommonSetupEvent event) {
	}

}