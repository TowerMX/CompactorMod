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

//    Comentario a�adido por Nelio para ver si est� bien linkado el repositorio 8)
//    Second test
//    Third test
    
//	  Ahora pruebo yo a ver si sube esto
//    Otra linea nueva por favor dejame subirlo dale carla aunque sea medio bit de codigo
//    olam e dej as�
//    As� es amigos, ya funciona :')
}