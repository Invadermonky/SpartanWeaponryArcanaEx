package com.invadermonky.spartanweaponryarcanaex.proxy;

import com.invadermonky.spartanweaponryarcanaex.registry.Registrar;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.preInit(event));
    }

    public void init(FMLInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.postInit(event));
    }
}
