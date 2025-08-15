package com.invadermonky.spartanweaponryarcanaex.compat;

import com.bewitchment.api.registry.FrostfireRecipe;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.items.base.*;
import com.invadermonky.spartanweaponryarcanaex.materials.bewitchment.WeaponPropertyColdIron;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;

@Mod.EventBusSubscriber(modid = SpartanWeaponryArcanaEx.MOD_ID)
public class BewitchmentCompat implements IModCompat {
    @Optional.Method(modid = ModIds.ConstIds.bewitchment)
    @SubscribeEvent
    public static void initializeRecipes(RegistryEvent.Register<FrostfireRecipe> event) {
        if (!ConfigHandlerSE.bewitchment.useFrostfireRecipes)
            return;

        IForgeRegistry<FrostfireRecipe> registry = event.getRegistry();
        registerFrostfireRecipe(registry, cold_iron_battleaxe, ItemRegistrySW.battleaxeIron);
        registerFrostfireRecipe(registry, cold_iron_boomerang, ItemRegistrySW.boomerangIron);
        registerFrostfireRecipe(registry, cold_iron_crossbow, ItemRegistrySW.crossbowIron);
        registerFrostfireRecipe(registry, cold_iron_dagger, ItemRegistrySW.daggerIron);
        registerFrostfireRecipe(registry, cold_iron_glaive, ItemRegistrySW.glaiveIron);
        registerFrostfireRecipe(registry, cold_iron_greatsword, ItemRegistrySW.greatswordIron);
        registerFrostfireRecipe(registry, cold_iron_halberd, ItemRegistrySW.halberdIron);
        registerFrostfireRecipe(registry, cold_iron_hammer, ItemRegistrySW.hammerIron);
        registerFrostfireRecipe(registry, cold_iron_javelin, ItemRegistrySW.javelinIron);
        registerFrostfireRecipe(registry, cold_iron_katana, ItemRegistrySW.katanaIron);
        registerFrostfireRecipe(registry, cold_iron_lance, ItemRegistrySW.lanceIron);
        registerFrostfireRecipe(registry, cold_iron_longbow, ItemRegistrySW.longbowIron);
        registerFrostfireRecipe(registry, cold_iron_longsword, ItemRegistrySW.longswordIron);
        registerFrostfireRecipe(registry, cold_iron_mace, ItemRegistrySW.maceIron);
        registerFrostfireRecipe(registry, cold_iron_parrying_dagger, ItemRegistrySW.parryingDaggerIron);
        registerFrostfireRecipe(registry, cold_iron_pike, ItemRegistrySW.pikeIron);
        registerFrostfireRecipe(registry, cold_iron_quarterstaff, ItemRegistrySW.quarterstaffIron);
        registerFrostfireRecipe(registry, cold_iron_rapier, ItemRegistrySW.rapierIron);
        registerFrostfireRecipe(registry, cold_iron_saber, ItemRegistrySW.saberIron);
        registerFrostfireRecipe(registry, cold_iron_scythe, ItemRegistrySW.scytheIron);
        registerFrostfireRecipe(registry, cold_iron_spear, ItemRegistrySW.spearIron);
        registerFrostfireRecipe(registry, cold_iron_throwing_axe, ItemRegistrySW.throwingAxeIron);
        registerFrostfireRecipe(registry, cold_iron_throwing_knife, ItemRegistrySW.throwingKnifeIron);
        registerFrostfireRecipe(registry, cold_iron_warhammer, ItemRegistrySW.warhammerIron);
    }

    private static void registerFrostfireRecipe(IForgeRegistry<FrostfireRecipe> registry, Item outputItem, Item inputItem) {
        if (inputItem != null && outputItem != null) {
            registry.register(new FrostfireRecipe(outputItem.getRegistryName(), Ingredient.fromItem(inputItem), new ItemStack(outputItem)));
        }
    }

    @Override
    public void initializeWeapons() {
        if (ConfigHandlerSE.bewitchment.enableColdIronWeapons) {
            cold_iron_battleaxe = new ItemBattleaxeSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_boomerang = new ItemBoomerangSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_crossbow = new ItemCrossbowSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_dagger = new ItemDaggerSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_glaive = new ItemGlaiveSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_greatsword = new ItemGreatswordSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_halberd = new ItemHalberdSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_hammer = new ItemHammerSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_javelin = new ItemJavelinSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_katana = new ItemKatanaSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_lance = new ItemLanceSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_longbow = new ItemLongbowSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_longsword = new ItemLongswordSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_mace = new ItemMaceSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_parrying_dagger = new ItemParryingDaggerSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_pike = new ItemPikeSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_quarterstaff = new ItemQuarterstaffSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_rapier = new ItemRapierSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_saber = new ItemSaberSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_scythe = new ItemScytheSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            //TODO:
            //  cold_iron_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.cold_iron_shield) : null;
            //  cold_iron_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.cold_iron_tower_shield) : null;
            cold_iron_spear = new ItemSpearSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_throwing_axe = new ItemThrowingAxeSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_throwing_knife = new ItemThrowingKnifeSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
            cold_iron_warhammer = new ItemWarhammerSE(LibNames.cold_iron, WeaponPropertyColdIron.COLD_IRON_MATERIAL_EX);
        }
    }
}
