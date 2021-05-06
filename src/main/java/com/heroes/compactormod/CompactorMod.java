package com.heroes.compactormod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("heroes_compactor_mod")
public class CompactorMod {
	
	public static final String MOD_ID = "heroes_compactor_mod";
	
    public static final Logger LOGGER = LogManager.getLogger();

    public CompactorMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

//    Comentario añadido por Nelio para ver si está bien linkado el repositorio 8)
//    Second test
//    Third test
    
//	  Ahora pruebo yo a ver si sube estp
}
