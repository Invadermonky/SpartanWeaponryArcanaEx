package com.invadermonky.spartanweaponryarcanaex.api;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IProxy {
    default void preInit(FMLPreInitializationEvent event) {}

    default void init(FMLInitializationEvent event) {}

    default void postInit(FMLPostInitializationEvent event) {}

    @SideOnly(Side.CLIENT)
    default void preInitClient(FMLPreInitializationEvent event) {}

    @SideOnly(Side.CLIENT)
    default void initClient(FMLInitializationEvent event) {}

    @SideOnly(Side.CLIENT)
    default void postInitClient(FMLPostInitializationEvent event) {}
}
