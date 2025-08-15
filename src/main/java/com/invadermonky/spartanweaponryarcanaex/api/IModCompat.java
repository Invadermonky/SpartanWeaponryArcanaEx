package com.invadermonky.spartanweaponryarcanaex.api;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.registries.IForgeRegistry;

public interface IModCompat extends IProxy {
    void initializeWeapons();

    default void initializeRecipes(IForgeRegistry<IRecipe> registry) {}

    default void onLivingHurt(LivingHurtEvent event) {}

    default void onProjectileImpact(ProjectileImpactEvent event) {}
}
