package com.invadermonky.spartanweaponryarcanaex.compat;

import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.items.base.*;
import com.invadermonky.spartanweaponryarcanaex.materials.naturesaura.WeaponPropertyAuraRepair;
import com.invadermonky.spartanweaponryarcanaex.materials.naturesaura.WeaponPropertyInfusedIron;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import de.ellpeck.naturesaura.api.recipes.AltarRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.registries.IForgeRegistry;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;

public class NaturesAuraCompat implements IModCompat {
    @Override
    public void initializeWeapons() {
        if(ConfigHandlerSE.natures_aura.enableInfusedIronWeapons) {
            infused_iron_battleaxe = new ItemBattleaxeSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_boomerang = new ItemBoomerangSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_crossbow = new ItemCrossbowSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX, WeaponPropertyAuraRepair.AURA_REPAIR_PROPERTY).setHasCustomDisplayName();
            infused_iron_dagger = new ItemDaggerSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_glaive = new ItemGlaiveSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_greatsword = new ItemGreatswordSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_halberd = new ItemHalberdSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_hammer = new ItemHammerSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_javelin = new ItemJavelinSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_katana = new ItemKatanaSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_lance = new ItemLanceSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_longbow = new ItemLongbowSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX, WeaponPropertyAuraRepair.AURA_REPAIR_PROPERTY).setHasCustomDisplayName();
            infused_iron_longsword = new ItemLongswordSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_mace = new ItemMaceSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_parrying_dagger = new ItemParryingDaggerSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_pike = new ItemPikeSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_quarterstaff = new ItemQuarterstaffSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_rapier = new ItemRapierSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_saber = new ItemSaberSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_scythe = new ItemScytheSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            //TODO:
            //  infused_iron_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.infused_iron_shield) : null;
            //  infused_iron_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.infused_iron_tower_shield) : null;
            infused_iron_spear = new ItemSpearSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_throwing_axe = new ItemThrowingAxeSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_throwing_knife = new ItemThrowingKnifeSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
            infused_iron_warhammer = new ItemWarhammerSE(LibNames.infused_iron, WeaponPropertyInfusedIron.INFUSED_IRON_MATERIAL_EX);
        }
    }

    @Override
    public void initializeRecipes(IForgeRegistry<IRecipe> registry) {
        if(!ConfigHandlerSE.natures_aura.useNaturalAltarRecipes) return;

        registerAltarWeaponRecipe(infused_iron_battleaxe, ItemRegistrySW.battleaxeIron);
        registerAltarWeaponRecipe(infused_iron_boomerang, ItemRegistrySW.boomerangIron);
        registerAltarWeaponRecipe(infused_iron_crossbow, ItemRegistrySW.crossbowIron);
        registerAltarWeaponRecipe(infused_iron_dagger, ItemRegistrySW.daggerIron);
        registerAltarWeaponRecipe(infused_iron_glaive, ItemRegistrySW.glaiveIron);
        registerAltarWeaponRecipe(infused_iron_greatsword, ItemRegistrySW.greatswordIron);
        registerAltarWeaponRecipe(infused_iron_halberd, ItemRegistrySW.halberdIron);
        registerAltarWeaponRecipe(infused_iron_hammer, ItemRegistrySW.hammerIron);
        registerAltarWeaponRecipe(infused_iron_javelin, ItemRegistrySW.javelinIron);
        registerAltarWeaponRecipe(infused_iron_katana, ItemRegistrySW.katanaIron);
        registerAltarWeaponRecipe(infused_iron_lance, ItemRegistrySW.lanceIron);
        registerAltarWeaponRecipe(infused_iron_longbow, ItemRegistrySW.longbowIron);
        registerAltarWeaponRecipe(infused_iron_longsword, ItemRegistrySW.longswordIron);
        registerAltarWeaponRecipe(infused_iron_mace, ItemRegistrySW.maceIron);
        registerAltarWeaponRecipe(infused_iron_parrying_dagger, ItemRegistrySW.parryingDaggerIron);
        registerAltarWeaponRecipe(infused_iron_pike, ItemRegistrySW.pikeIron);
        registerAltarWeaponRecipe(infused_iron_quarterstaff, ItemRegistrySW.quarterstaffIron);
        registerAltarWeaponRecipe(infused_iron_rapier, ItemRegistrySW.rapierIron);
        registerAltarWeaponRecipe(infused_iron_saber, ItemRegistrySW.saberIron);
        registerAltarWeaponRecipe(infused_iron_scythe, ItemRegistrySW.scytheIron);
        registerAltarWeaponRecipe(infused_iron_spear, ItemRegistrySW.spearIron);
        registerAltarWeaponRecipe(infused_iron_throwing_axe, ItemRegistrySW.throwingAxeIron);
        registerAltarWeaponRecipe(infused_iron_throwing_knife, ItemRegistrySW.throwingKnifeIron);
        registerAltarWeaponRecipe(infused_iron_warhammer, ItemRegistrySW.warhammerIron);
    }

    private void registerAltarWeaponRecipe(Item outputItem, Item inputItem) {
        if(inputItem != null && outputItem != null) {
            (new AltarRecipe(outputItem.getRegistryName(), Ingredient.fromItem(inputItem), new ItemStack(outputItem), Ingredient.EMPTY, 45000, 200)).register();
        }
    }
}
