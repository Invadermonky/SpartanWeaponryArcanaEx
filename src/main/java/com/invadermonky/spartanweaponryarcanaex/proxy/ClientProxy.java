package com.invadermonky.spartanweaponryarcanaex.proxy;

import com.invadermonky.spartanweaponryarcanaex.client.render.TileEntityItemStackRendererSE;
import com.invadermonky.spartanweaponryarcanaex.registry.Registrar;
import net.minecraft.client.model.ModelShield;
import net.minecraft.item.ItemShield;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.preInitClient(event));
    }

    @Override
    public void init(FMLInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.initClient(event));
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.postInitClient(event));
    }

    public static void setShieldTEISR(ItemShield shield, String textureName, String bannerId, ModelShield model) {
        if(shield != null) {
            shield.setTileEntityItemStackRenderer(new TileEntityItemStackRendererSE(shield, String.format("textures/entity/%s_nopattern.png", textureName), String.format("textures/entity/%s_pattern.png", textureName), bannerId, model));
        }
    }
}
