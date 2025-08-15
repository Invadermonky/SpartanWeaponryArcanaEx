package com.invadermonky.spartanweaponryarcanaex.registry;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.entities.EntityCrystalWeapon;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntitiesSE {
    public static void registerEntities() {
        if (ModIds.astral_sorcery.isLoaded && ConfigHandlerSE.astral_sorcery.enableCrystalWeapons) {
            EntityRegistry.registerModEntity(
                    new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "crystal_weapon_item"),
                    EntityCrystalWeapon.class,
                    "crystal_weapon_item",
                    100,
                    SpartanWeaponryArcanaEx.instance,
                    50,
                    1,
                    true
            );
        }
    }
}
