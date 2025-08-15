package com.invadermonky.spartanweaponryarcanaex.compat;

import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.items.base.*;
import com.invadermonky.spartanweaponryarcanaex.items.wildwood.ItemWildwoodCrossbow;
import com.invadermonky.spartanweaponryarcanaex.items.wildwood.ItemWildwoodLongbow;
import com.invadermonky.spartanweaponryarcanaex.materials.roots.WeaponPropertyLiving;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import epicsquid.roots.init.ModItems;
import epicsquid.roots.init.ModRecipes;
import epicsquid.roots.recipe.FeyCraftingRecipe;
import epicsquid.roots.recipe.ingredient.GoldOrSilverIngotIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;

public class RootsCompat implements IModCompat {
    @Override
    public void initializeWeapons() {
        if (ConfigHandlerSE.roots.enableLivingWeapons) {
            living_battleaxe = new ItemBattleaxeSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_boomerang = new ItemBoomerangSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_dagger = new ItemDaggerSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_glaive = new ItemGlaiveSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_greatsword = new ItemGreatswordSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_halberd = new ItemHalberdSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_hammer = new ItemHammerSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_javelin = new ItemJavelinSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_katana = new ItemKatanaSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_lance = new ItemLanceSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_longsword = new ItemLongswordSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_mace = new ItemMaceSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_parrying_dagger = new ItemParryingDaggerSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_pike = new ItemPikeSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_quarterstaff = new ItemQuarterstaffSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_rapier = new ItemRapierSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_saber = new ItemSaberSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_scythe = new ItemScytheSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            //TODO:
            //  living_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.living_shield) : null;
            //  living_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.living_tower_shield) : null;
            living_spear = new ItemSpearSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_throwing_axe = new ItemThrowingAxeSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_throwing_knife = new ItemThrowingKnifeSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
            living_warhammer = new ItemWarhammerSE(LibNames.living, WeaponPropertyLiving.LIVING_MATERIAL_EX).setNoReequipAnimation();
        }

        /*
            For the time being, runed weapons will not be included in the mod. They are closer to Thaumcraft's Primal weapons,
            in that they are powerful one-off tools. The power of these weapons goes somewhat against Spartan Weaponry's
            dozens of weapons per material design.

            Also I'm tired of doing sprites and want to move on to another project.

        if(ConfigHandlerSE.roots.enableRunedWeapons) {
            runed_battleaxe = new ItemBattleaxeSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_boomerang = new ItemBoomerangSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_crossbow = new ItemCrossbowSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX, WeaponPropertyLiving.RUNED_PROPERTY).setNoReequipAnimation().setHasCustomDisplayName();
            runed_dagger = new ItemDaggerSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_glaive = new ItemGlaiveSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_greatsword = new ItemGreatswordSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_halberd = new ItemHalberdSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_hammer = new ItemHammerSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_javelin = new ItemJavelinSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_katana = new ItemKatanaSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_lance = new ItemLanceSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_longbow = new ItemLongbowSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX, WeaponPropertyLiving.RUNED_PROPERTY).setNoReequipAnimation().setHasCustomDisplayName();
            runed_longsword = new ItemLongswordSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_mace = new ItemMaceSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_parrying_dagger = new ItemParryingDaggerSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_pike = new ItemPikeSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_quarterstaff = new ItemQuarterstaffSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_rapier = new ItemRapierSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_saber = new ItemSaberSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_scythe = new ItemScytheSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            //  runed_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.runed_shield) : null;
            //  runed_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.runed_tower_shield) : null;
            runed_spear = new ItemSpearSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_throwing_axe = new ItemThrowingAxeSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_throwing_knife = new ItemThrowingKnifeSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
            runed_warhammer = new ItemWarhammerSE(LibNames.runed, WeaponPropertyLiving.RUNED_MATERIAL_EX).setNoReequipAnimation();
        }
        */

        if (ConfigHandlerSE.roots.enableTerrastoneWeapons) {
            terrastone_battleaxe = new ItemBattleaxeSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_boomerang = new ItemBoomerangSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_dagger = new ItemDaggerSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_glaive = new ItemGlaiveSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_greatsword = new ItemGreatswordSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_halberd = new ItemHalberdSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_hammer = new ItemHammerSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_javelin = new ItemJavelinSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_katana = new ItemKatanaSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_lance = new ItemLanceSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_longsword = new ItemLongswordSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_mace = new ItemMaceSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_parrying_dagger = new ItemParryingDaggerSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_pike = new ItemPikeSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_quarterstaff = new ItemQuarterstaffSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_rapier = new ItemRapierSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_saber = new ItemSaberSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_scythe = new ItemScytheSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            //TODO:
            //  terrastone_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.terrastone_shield) : null;
            //  terrastone_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.terrastone_tower_shield) : null;
            terrastone_spear = new ItemSpearSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_throwing_axe = new ItemThrowingAxeSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_throwing_knife = new ItemThrowingKnifeSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
            terrastone_warhammer = new ItemWarhammerSE(LibNames.terrastone, WeaponPropertyLiving.TERRASTONE_MATERIAL_EX).setNoReequipAnimation();
        }

        if (ConfigHandlerSE.roots.enableWildwoodBows) {
            wildwood_crossbow = new ItemWildwoodCrossbow();
            wildwood_longbow = new ItemWildwoodLongbow();
        }
    }

    @Override
    public void initializeRecipes(IForgeRegistry<IRecipe> registry) {
        //Living
        registerFeyCraftingLivingWeaponRecipe(living_battleaxe, ItemRegistrySW.battleaxeWood);
        registerFeyCraftingLivingWeaponRecipe(living_boomerang, ItemRegistrySW.boomerangWood);
        registerFeyCraftingLivingWeaponRecipe(living_dagger, ItemRegistrySW.daggerWood);
        registerFeyCraftingLivingWeaponRecipe(living_glaive, ItemRegistrySW.glaiveWood);
        registerFeyCraftingLivingWeaponRecipe(living_greatsword, ItemRegistrySW.greatswordWood);
        registerFeyCraftingLivingWeaponRecipe(living_halberd, ItemRegistrySW.halberdWood);
        registerFeyCraftingLivingWeaponRecipe(living_hammer, ItemRegistrySW.hammerWood);
        registerFeyCraftingLivingWeaponRecipe(living_javelin, ItemRegistrySW.javelinWood);
        registerFeyCraftingLivingWeaponRecipe(living_katana, ItemRegistrySW.katanaWood);
        registerFeyCraftingLivingWeaponRecipe(living_lance, ItemRegistrySW.lanceWood);
        registerFeyCraftingLivingWeaponRecipe(living_longsword, ItemRegistrySW.longswordWood);
        registerFeyCraftingLivingWeaponRecipe(living_mace, ItemRegistrySW.maceWood);
        registerFeyCraftingLivingWeaponRecipe(living_parrying_dagger, ItemRegistrySW.parryingDaggerWood);
        registerFeyCraftingLivingWeaponRecipe(living_pike, ItemRegistrySW.pikeWood);
        registerFeyCraftingLivingWeaponRecipe(living_quarterstaff, ItemRegistrySW.quarterstaffWood);
        registerFeyCraftingLivingWeaponRecipe(living_rapier, ItemRegistrySW.rapierWood);
        registerFeyCraftingLivingWeaponRecipe(living_saber, ItemRegistrySW.saberWood);
        registerFeyCraftingLivingWeaponRecipe(living_scythe, ItemRegistrySW.scytheWood);
        registerFeyCraftingLivingWeaponRecipe(living_spear, ItemRegistrySW.spearWood);
        registerFeyCraftingLivingWeaponRecipe(living_throwing_axe, ItemRegistrySW.throwingAxeWood);
        registerFeyCraftingLivingWeaponRecipe(living_throwing_knife, ItemRegistrySW.throwingKnifeWood);
        registerFeyCraftingLivingWeaponRecipe(living_warhammer, ItemRegistrySW.warhammerWood);

        //Runed
        registerFeyCraftingRunedWeaponRecipe(runed_battleaxe, ItemRegistrySW.battleaxeDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_boomerang, ItemRegistrySW.boomerangDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_crossbow, ItemRegistrySW.crossbowDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_dagger, ItemRegistrySW.daggerDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_glaive, ItemRegistrySW.glaiveDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_greatsword, ItemRegistrySW.greatswordDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_halberd, ItemRegistrySW.halberdDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_hammer, ItemRegistrySW.hammerDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_javelin, ItemRegistrySW.javelinDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_katana, ItemRegistrySW.katanaDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_lance, ItemRegistrySW.lanceDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_longbow, ItemRegistrySW.longbowDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_longsword, ItemRegistrySW.longswordDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_mace, ItemRegistrySW.maceDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_parrying_dagger, ItemRegistrySW.parryingDaggerDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_pike, ItemRegistrySW.pikeDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_quarterstaff, ItemRegistrySW.quarterstaffDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_rapier, ItemRegistrySW.rapierDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_saber, ItemRegistrySW.saberDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_scythe, ItemRegistrySW.scytheDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_spear, ItemRegistrySW.spearDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_throwing_axe, ItemRegistrySW.throwingAxeDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_throwing_knife, ItemRegistrySW.throwingKnifeDiamond);
        registerFeyCraftingRunedWeaponRecipe(runed_warhammer, ItemRegistrySW.warhammerDiamond);

        //Terrastone
        boolean altRecipes = ConfigHandlerSE.roots.useAlternateRecipes && ConfigHandlerSE.roots.enableLivingWeapons;
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_battleaxe, altRecipes ? living_battleaxe : ItemRegistrySW.battleaxeStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_boomerang, altRecipes ? living_boomerang : ItemRegistrySW.boomerangStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_dagger, altRecipes ? living_dagger : ItemRegistrySW.daggerStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_glaive, altRecipes ? living_glaive : ItemRegistrySW.glaiveStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_greatsword, altRecipes ? living_greatsword : ItemRegistrySW.greatswordStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_halberd, altRecipes ? living_halberd : ItemRegistrySW.halberdStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_hammer, altRecipes ? living_hammer : ItemRegistrySW.hammerStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_javelin, altRecipes ? living_javelin : ItemRegistrySW.javelinStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_katana, altRecipes ? living_katana : ItemRegistrySW.katanaStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_lance, altRecipes ? living_lance : ItemRegistrySW.lanceStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_longsword, altRecipes ? living_longsword : ItemRegistrySW.longswordStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_mace, altRecipes ? living_mace : ItemRegistrySW.maceStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_parrying_dagger, altRecipes ? living_parrying_dagger : ItemRegistrySW.parryingDaggerStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_pike, altRecipes ? living_pike : ItemRegistrySW.pikeStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_quarterstaff, altRecipes ? living_quarterstaff : ItemRegistrySW.quarterstaffStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_rapier, altRecipes ? living_rapier : ItemRegistrySW.rapierStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_saber, altRecipes ? living_saber : ItemRegistrySW.saberStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_scythe, altRecipes ? living_scythe : ItemRegistrySW.scytheStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_spear, altRecipes ? living_spear : ItemRegistrySW.spearStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_throwing_axe, altRecipes ? living_throwing_axe : ItemRegistrySW.throwingAxeStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_throwing_knife, altRecipes ? living_throwing_knife : ItemRegistrySW.throwingKnifeStone);
        registerFeyCraftingTerrastoneWeaponRecipe(terrastone_warhammer, altRecipes ? living_warhammer : ItemRegistrySW.warhammerStone);

        registerFeyCraftingWildwoodBowRecipe(wildwood_crossbow, ItemRegistrySW.crossbowWood);
        registerFeyCraftingWildwoodBowRecipe(wildwood_longbow, ItemRegistrySW.longbowWood);
    }

    private void registerFeyCraftingLivingWeaponRecipe(Item outputItem, Item inputItem) {
        if (inputItem != null && outputItem != null) {
            ModRecipes.addFeyCraftingRecipe(outputItem.getRegistryName(), new FeyCraftingRecipe(new ItemStack(outputItem), 1)
                    .addIngredients(
                            new GoldOrSilverIngotIngredient(),
                            Ingredient.fromItem(inputItem),
                            new OreIngredient("wildroot"),
                            new OreIngredient("rootsBark"),
                            new OreIngredient("rootsBark")
                    ));
        }
    }

    private void registerFeyCraftingRunedWeaponRecipe(Item outputItem, Item inputItem) {
        if (inputItem != null && outputItem != null) {
            ModRecipes.addFeyCraftingRecipe(outputItem.getRegistryName(), new FeyCraftingRecipe(new ItemStack(outputItem), 1)
                    .addIngredients(
                            new OreIngredient("runedObsidian"),
                            new OreIngredient("runedObsidian"),
                            Ingredient.fromItem(inputItem),
                            new OreIngredient("feyLeather"),
                            new ItemStack(ModItems.infernal_bulb)
                    ));
        }
    }

    private void registerFeyCraftingTerrastoneWeaponRecipe(Item outputItem, Item inputItem) {
        if (inputItem != null && outputItem != null) {
            ModRecipes.addFeyCraftingRecipe(outputItem.getRegistryName(), new FeyCraftingRecipe(new ItemStack(outputItem), 1)
                    .addIngredients(
                            new OreIngredient("runestone"),
                            Ingredient.fromItem(inputItem),
                            new ItemStack(ModItems.terra_moss),
                            new OreIngredient("gemDiamond"),
                            new OreIngredient("mossyCobblestone")
                    ));
        }
    }

    private void registerFeyCraftingWildwoodBowRecipe(Item outputItem, Item inputItem) {
        if (inputItem != null && outputItem != null) {
            ModRecipes.addFeyCraftingRecipe(outputItem.getRegistryName(), new FeyCraftingRecipe(new ItemStack(outputItem), 1)
                    .addIngredients(
                            Ingredient.fromItem(inputItem),
                            new ItemStack(ModItems.bark_wildwood),
                            new ItemStack(ModItems.bark_wildwood),
                            new ItemStack(ModItems.terra_moss),
                            new ItemStack(ModItems.spirit_herb)
                    ));
        }
    }
}
