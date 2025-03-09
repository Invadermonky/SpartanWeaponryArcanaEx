package com.invadermonky.spartanweaponryarcanaex.handlers;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.registry.Registrar;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = SpartanWeaponryArcanaEx.MOD_ID)
public class EventHandler {
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.onProjectileImpact(event));
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Registrar.getLoadedCompat().forEach(iModCompat -> iModCompat.onLivingHurt(event));
    }
}
