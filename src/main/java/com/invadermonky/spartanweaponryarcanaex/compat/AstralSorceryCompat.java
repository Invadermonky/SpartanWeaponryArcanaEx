package com.invadermonky.spartanweaponryarcanaex.compat;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.api.ISharpenableCrystalWeapon;
import com.invadermonky.spartanweaponryarcanaex.client.model.ModelShieldTowerSE;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.items.crystal.*;
import com.invadermonky.spartanweaponryarcanaex.materials.astralsorcery.WeaponPropertyCrystal;
import com.invadermonky.spartanweaponryarcanaex.materials.astralsorcery.WeaponPropertyInfusedCrystal;
import com.invadermonky.spartanweaponryarcanaex.proxy.ClientProxy;
import com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE;
import com.invadermonky.spartanweaponryarcanaex.util.PlayerHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import com.oblivioussp.spartanshields.init.ItemRegistrySS;
import com.oblivioussp.spartanshields.item.crafting.RecipeShieldBanners;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import hellfirepvp.astralsorcery.common.auxiliary.SwordSharpenHelper;
import hellfirepvp.astralsorcery.common.crafting.ItemHandle;
import hellfirepvp.astralsorcery.common.crafting.altar.AltarRecipeRegistry;
import hellfirepvp.astralsorcery.common.crafting.altar.recipes.CrystalToolRecipe;
import hellfirepvp.astralsorcery.common.crafting.grindstone.GrindstoneRecipe;
import hellfirepvp.astralsorcery.common.crafting.grindstone.GrindstoneRecipeRegistry;
import hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipe;
import hellfirepvp.astralsorcery.common.crafting.infusion.InfusionRecipeRegistry;
import hellfirepvp.astralsorcery.common.crafting.infusion.recipes.InfusionRecipeChargeTool;
import hellfirepvp.astralsorcery.common.entities.EntityFlare;
import hellfirepvp.astralsorcery.common.item.crystal.ToolCrystalProperties;
import hellfirepvp.astralsorcery.common.item.tool.ChargedCrystalToolBase;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import hellfirepvp.astralsorcery.common.util.MiscUtils;
import hellfirepvp.astralsorcery.common.util.OreDictAlias;
import hellfirepvp.astralsorcery.common.util.data.Vector3;
import hellfirepvp.astralsorcery.common.util.effect.CelestialStrike;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;
import static hellfirepvp.astralsorcery.common.crafting.helper.ShapedRecipeSlot.*;

public class AstralSorceryCompat implements IModCompat {
    @Override
    public void initializeWeapons() {
        if(ConfigHandlerSE.astral_sorcery.enableCrystalWeapons) {
            crystal_battleaxe = new ItemCrystalBattleaxe(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_boomerang = new ItemCrystalBoomerang(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_crossbow = new ItemCrystalCrossbow(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX, WeaponPropertyCrystal.CRYSTAL_PROPERTY);
            crystal_dagger = new ItemCrystalDagger(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_glaive = new ItemCrystalGlaive(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_greatsword = new ItemCrystalGreatsword(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_halberd = new ItemCrystalHalberd(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_hammer = new ItemCrystalHammer(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_javelin = new ItemCrystalJavelin(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_katana = new ItemCrystalKatana(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_lance = new ItemCrystalLance(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_longbow = new ItemCrystalLongbow(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX, WeaponPropertyCrystal.CRYSTAL_PROPERTY);
            crystal_longsword = new ItemCrystalLongsword(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_mace = new ItemCrystalMace(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_parrying_dagger = new ItemCrystalParryingDagger(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_pike = new ItemCrystalPike(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_quarterstaff = new ItemCrystalQuarterstaff(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_rapier = new ItemCrystalRapier(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_saber = new ItemCrystalSaber(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_scythe = new ItemCrystalScythe(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            //TODO:
            //  crystal_shield_basic = ModIds.spartan_shields.isLoaded ? ItemCrystalShieldWrapper.build(LibNames.crystal_shield_basic) : null;
            //  crystal_shield_tower = ModIds.spartan_shields.isLoaded ? ItemCrystalShieldWrapper.build(LibNames.crystal_shield_tower) : null;
            crystal_spear = new ItemCrystalSpear(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_throwing_axe = new ItemCrystalThrowingAxe(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_throwing_knife = new ItemCrystalThrowingKnife(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);
            crystal_warhammer = new ItemCrystalWarhammer(LibNames.crystal, WeaponPropertyCrystal.CRYSTAL_MATERIAL_EX);

            infused_crystal_battleaxe = new ItemCrystalBattleaxe(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_boomerang = new ItemCrystalBoomerang(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_crossbow = new ItemCrystalCrossbow(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_PROPERTY);
            infused_crystal_dagger = new ItemCrystalDagger(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_glaive = new ItemCrystalGlaive(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_greatsword = new ItemCrystalGreatsword(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_halberd = new ItemCrystalHalberd(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_hammer = new ItemCrystalHammer(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_javelin = new ItemCrystalJavelin(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_katana = new ItemCrystalKatana(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_lance = new ItemCrystalLance(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_longbow = new ItemCrystalLongbow(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_PROPERTY);
            infused_crystal_longsword = new ItemCrystalLongsword(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_mace = new ItemCrystalMace(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_parrying_dagger = new ItemCrystalParryingDagger(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_pike = new ItemCrystalPike(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_quarterstaff = new ItemCrystalQuarterstaff(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_rapier = new ItemCrystalRapier(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_saber = new ItemCrystalSaber(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_scythe = new ItemCrystalScythe(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            //TODO:
            //  infused_crystal_shield_basic = ModIds.spartan_shields.isLoaded ? ItemCrystalShieldWrapper.build(LibNames.infused_crystal_shield_basic, crystal_shield_basic) : null;
            //  infused_crystal_shield_tower = ModIds.spartan_shields.isLoaded ? ItemCrystalShieldWrapper.build(LibNames.infused_crystal_shield_tower, crystal_shield_tower) : null;
            infused_crystal_spear = new ItemCrystalSpear(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_throwing_axe = new ItemCrystalThrowingAxe(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_throwing_knife = new ItemCrystalThrowingKnife(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
            infused_crystal_warhammer = new ItemCrystalWarhammer(LibNames.infused_crystal, WeaponPropertyInfusedCrystal.INFUSED_CRYSTAL_MATERIAL_EX);
        }
    }

    @Override
    public void initializeRecipes(IForgeRegistry<IRecipe> registry) {
        registerWeaponSharpeningRecipe();

        //Crystal Weapons
        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_battleaxe, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_battleaxe, ModItemsSE.crystal_battleaxe)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(OreDictAlias.ITEM_STICKS, CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LEFT, RIGHT, UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LEFT, RIGHT, UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_boomerang, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_boomerang, ModItemsSE.crystal_boomerang)
                .addPart(OreDictAlias.BLOCK_WOOD_PLANKS, LOWER_LEFT, LEFT, UPPER_CENTER, UPPER_RIGHT)
                .addPart(ItemHandle.getCrystalVariant(false, false), UPPER_LEFT)
                .unregisteredAccessibleShapedRecipe(), UPPER_LEFT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_crossbow, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_crossbow, ModItemsSE.crystal_crossbow)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_RIGHT)
                .addPart("string", LEFT, UPPER_CENTER)
                .addPart(new ItemStack(Items.BOW), UPPER_LEFT)
                .addPart(OreDictAlias.BLOCK_WOOD_LOGS, CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_dagger, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_dagger, ModItemsSE.crystal_dagger)
                .addPart(new ItemStack(ItemRegistrySW.material), CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_glaive, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_glaive, ModItemsSE.crystal_glaive)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, CENTER, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_greatsword, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_greatsword, ModItemsSE.crystal_greatsword)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, LOWER_RIGHT, LEFT, CENTER, RIGHT, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, LOWER_RIGHT, LEFT, CENTER, RIGHT, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_halberd, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_halberd, ModItemsSE.crystal_halberd)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, LEFT, CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, LEFT, CENTER, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_hammer, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_hammer, ModItemsSE.crystal_hammer)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LEFT, CENTER, RIGHT, UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LEFT, CENTER, RIGHT, UPPER_LEFT, UPPER_CENTER, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_javelin, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_javelin, ModItemsSE.crystal_javelin)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), LEFT)
                .addPart(ItemHandle.getCrystalVariant(false, false), CENTER)
                .unregisteredAccessibleShapedRecipe(), CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_katana, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_katana, ModItemsSE.crystal_katana)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_LEFT)
                .addPart(ItemHandle.getCrystalVariant(false, false), CENTER, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), CENTER, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_lance, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_lance, ModItemsSE.crystal_lance)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_longbow, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_longbow, ModItemsSE.crystal_longbow)
                .addPart(new ItemStack(ItemRegistrySW.material), UPPER_LEFT)
                .addPart(OreDictAlias.ITEM_STICKS, LEFT, UPPER_CENTER)
                .addPart("string", LOWER_CENTER, LOWER_RIGHT, RIGHT)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_longsword, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_longsword, ModItemsSE.crystal_longsword)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, LOWER_RIGHT, CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, LOWER_RIGHT, CENTER, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_mace, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_mace, ModItemsSE.crystal_mace)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_LEFT)
                .addPart(OreDictAlias.ITEM_STICKS, CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), RIGHT, UPPER_CENTER, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), RIGHT, UPPER_CENTER, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_parrying_dagger, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_parrying_dagger, ModItemsSE.crystal_parrying_dagger)
                .addPart(new ItemStack(ItemRegistrySW.material), CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LEFT, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LEFT, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_pike, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_pike, ModItemsSE.crystal_pike)
                .addPart(new ItemStack(ItemRegistrySW.material,1 ,1), LOWER_CENTER, CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_quarterstaff, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_quarterstaff, ModItemsSE.crystal_quarterstaff)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_rapier, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_rapier, ModItemsSE.crystal_rapier)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_LEFT)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_CENTER, LEFT, CENTER, UPPER_RIGHT)
                .unregisteredAccessibleShapedRecipe(), LOWER_CENTER, LEFT, CENTER, UPPER_RIGHT));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_saber, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_saber, ModItemsSE.crystal_saber)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_LEFT, CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LOWER_LEFT, CENTER, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_scythe, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_scythe, ModItemsSE.crystal_scythe)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), RIGHT, UPPER_LEFT, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), RIGHT, UPPER_LEFT, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_spear, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_spear, ModItemsSE.crystal_spear)
                .addPart(new ItemStack(ItemRegistrySW.material, 1, 1), CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_throwing_axe, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_throwing_axe, ModItemsSE.crystal_throwing_axe)
                .addPart(new ItemStack(ItemRegistrySW.material), UPPER_LEFT)
                .addPart(ItemHandle.getCrystalVariant(false, false), CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), CENTER, UPPER_CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_throwing_knife, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_throwing_knife, ModItemsSE.crystal_throwing_knife)
                .addPart(new ItemStack(ItemRegistrySW.material), LEFT)
                .addPart(ItemHandle.getCrystalVariant(false, false), CENTER)
                .unregisteredAccessibleShapedRecipe(), CENTER));

        AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_warhammer, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_warhammer, ModItemsSE.crystal_warhammer)
                .addPart(new ItemStack(ItemRegistrySW.material), LOWER_CENTER)
                .addPart(ItemHandle.getCrystalVariant(false, false), LEFT, CENTER, UPPER_CENTER)
                .unregisteredAccessibleShapedRecipe(), LEFT, CENTER, UPPER_CENTER));


        //Infused Crystal Weapons
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_battleaxe);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_boomerang);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_crossbow);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_dagger);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_glaive);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_greatsword);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_halberd);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_hammer);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_javelin);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_katana);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_lance);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_longbow);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_longsword);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_mace);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_parrying_dagger);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_pike);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_quarterstaff);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_rapier);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_saber);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_scythe);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_spear);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_throwing_axe);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_throwing_knife);
        AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_warhammer);

        if(ModIds.spartan_shields.isLoaded) {
            AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_shield_basic, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_shield_basic, ModItemsSE.crystal_shield_basic)
                    .addPart(new ItemStack(ItemRegistrySS.shieldWood), CENTER)
                    .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_CENTER, LEFT, RIGHT, UPPER_CENTER)
                    .unregisteredAccessibleShapedRecipe(), LOWER_CENTER, LEFT, RIGHT, UPPER_CENTER));

            AstralSorceryCompat.registerToolAltarRecipe(ModItemsSE.crystal_shield_tower, new CrystalToolRecipe(ShapedRecipe.Builder.newShapedRecipe("internal/altar/" + LibNames.crystal_shield_tower, ModItemsSE.crystal_shield_tower)
                    .addPart(new ItemStack(ItemRegistrySS.shieldTowerWood), CENTER)
                    .addPart(ItemHandle.getCrystalVariant(false, false), LOWER_CENTER, LEFT, RIGHT, UPPER_CENTER)
                    .unregisteredAccessibleShapedRecipe(), LOWER_CENTER, LEFT, RIGHT, UPPER_CENTER));

            if(ModItemsSE.crystal_shield_tower != null) {
                registry.register(new RecipeShieldBanners(ModItemsSE.crystal_shield_tower).setRegistryName(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "shield_banner_crystal")));
            }

            AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_shield_basic);
            AstralSorceryCompat.registerToolInfusionRecipe(ModItemsSE.infused_crystal_shield_tower);
            if(ModItemsSE.infused_crystal_shield_tower != null) {
                registry.register(new RecipeShieldBanners(ModItemsSE.infused_crystal_shield_tower).setRegistryName(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "shield_banner_infused_crystal")));
            }
        }
    }

    @Override
    public void onLivingHurt(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayer && event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            if(!player.getHeldItemOffhand().isEmpty() && player.getHeldItemOffhand().getItem() == ModItemsSE.infused_crystal_shield_basic || player.getHeldItemOffhand().getItem() == ModItemsSE.infused_crystal_shield_tower) {
                BlockPos pos1 = new BlockPos(player.posX - 10, player.posY - 10, player.posZ - 10);
                BlockPos pos2 = new BlockPos(player.posX + 10, player.posY + 10, player.posZ + 10);
                List<EntityFlare> entityFlares = player.world.getEntitiesWithinAABB(EntityFlare.class, new AxisAlignedBB(pos1, pos2), entityFlare -> entityFlare.getEntityData().getBoolean(LibTags.crystal_shield_flare));
                for(EntityFlare flare : entityFlares) {
                    if(flare.getFollowingEntity() == player) {
                        flare.setAttackTarget((EntityLivingBase) event.getSource().getTrueSource());
                    }
                }
            }
        }
    }

    @Override
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if(event.getEntity().world.isRemote) return;

        Entity target = event.getRayTraceResult().entityHit;
        if(event.getEntity() instanceof EntityArrow && target instanceof EntityLivingBase) {
            NBTTagCompound tag = event.getEntity().getEntityData();
            if(tag.getBoolean(LibTags.stellar_arrow) && tag.hasKey(LibTags.owner)) {
                String owner = tag.getString(LibTags.owner);
                EntityPlayer player = PlayerHelper.getPlayerFromUUID(UUID.fromString(owner));
                if(player != null && player instanceof EntityPlayerMP) {
                    EntityPlayerMP playerMp = (EntityPlayerMP)player;
                    ItemStack stack = getHeldImbuedWeapon(player);
                    if(!stack.isEmpty()) {
                        if (!MiscUtils.isPlayerFakeMP(playerMp) && !player.isSneaking() && !playerMp.getCooldownTracker().hasCooldown(ItemsAS.chargedCrystalSword)) {
                            CelestialStrike.play(player, player.getEntityWorld(), Vector3.atEntityCorner(target), Vector3.atEntityCenter(target));
                            //The damage is dealt by the strike so the arrow is set dead.
                            event.getEntity().setDead();
                            stack.damageItem(1, player);
                            if (!ChargedCrystalToolBase.tryRevertMainHand(playerMp, stack)) {
                                playerMp.getCooldownTracker().setCooldown(ItemsAS.chargedCrystalSword, 80);
                            }
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initClient(FMLInitializationEvent event) {
        if(ModIds.spartan_shields.isLoaded) {
            ClientProxy.setShieldTEISR(ModItemsSE.crystal_shield_tower, LibNames.crystal_shield_tower, "S_CRYSTAL", new ModelShieldTowerSE());
            ClientProxy.setShieldTEISR(ModItemsSE.infused_crystal_shield_tower, LibNames.infused_crystal_shield_tower, "S_INFUSED_CRYSTAL", new ModelShieldTowerSE());
        }
    }

    private static ItemStack getHeldImbuedWeapon(EntityPlayer player) {
        if(player.getHeldItemMainhand().getItem() == ModItemsSE.infused_crystal_longbow || player.getHeldItemMainhand().getItem() == ModItemsSE.infused_crystal_crossbow) {
            return player.getHeldItemMainhand();
        } else if(player.getHeldItemOffhand().getItem() == ModItemsSE.infused_crystal_longbow || player.getHeldItemOffhand().getItem() == ModItemsSE.infused_crystal_crossbow) {
            return player.getHeldItemOffhand();
        }
        return ItemStack.EMPTY;
    }

    public static void registerToolAltarRecipe(Item crystalTool, CrystalToolRecipe recipe) {
        if(crystalTool == null) return;
        AltarRecipeRegistry.registerAltarRecipe(recipe);
    }

    public static void registerToolInfusionRecipe(Item infusedTool) {
        if(infusedTool instanceof ChargedCrystalToolBase) {
            InfusionRecipeRegistry.registerInfusionRecipe(new InfusionRecipeChargeTool((Item & ChargedCrystalToolBase) infusedTool));
        }
    }

    public static void registerWeaponSharpeningRecipe() {
        if(!ConfigHandlerSE.astral_sorcery.enableCrystalWeapons)
            return;

        // Need to blacklist the tool classes so they can't receive the 10% bonus sharpened damage.
        blacklistSharpenableWeapon(crystal_battleaxe);
        blacklistSharpenableWeapon(crystal_boomerang);
        blacklistSharpenableWeapon(crystal_crossbow);
        blacklistSharpenableWeapon(crystal_dagger);
        blacklistSharpenableWeapon(crystal_glaive);
        blacklistSharpenableWeapon(crystal_greatsword);
        blacklistSharpenableWeapon(crystal_halberd);
        blacklistSharpenableWeapon(crystal_hammer);
        blacklistSharpenableWeapon(crystal_javelin);
        blacklistSharpenableWeapon(crystal_katana);
        blacklistSharpenableWeapon(crystal_lance);
        blacklistSharpenableWeapon(crystal_longbow);
        blacklistSharpenableWeapon(crystal_longsword);
        blacklistSharpenableWeapon(crystal_mace);
        blacklistSharpenableWeapon(crystal_parrying_dagger);
        blacklistSharpenableWeapon(crystal_pike);
        blacklistSharpenableWeapon(crystal_quarterstaff);
        blacklistSharpenableWeapon(crystal_rapier);
        blacklistSharpenableWeapon(crystal_saber);
        blacklistSharpenableWeapon(crystal_scythe);
        //TODO:
        //  blacklistSharpenableWeapon(crystal_shield_basic);
        //  blacklistSharpenableWeapon(crystal_shield_tower);
        blacklistSharpenableWeapon(crystal_spear);
        blacklistSharpenableWeapon(crystal_throwing_axe);
        blacklistSharpenableWeapon(crystal_throwing_knife);
        blacklistSharpenableWeapon(crystal_warhammer);

        blacklistSharpenableWeapon(infused_crystal_battleaxe);
        blacklistSharpenableWeapon(infused_crystal_boomerang);
        blacklistSharpenableWeapon(infused_crystal_crossbow);
        blacklistSharpenableWeapon(infused_crystal_dagger);
        blacklistSharpenableWeapon(infused_crystal_glaive);
        blacklistSharpenableWeapon(infused_crystal_greatsword);
        blacklistSharpenableWeapon(infused_crystal_halberd);
        blacklistSharpenableWeapon(infused_crystal_hammer);
        blacklistSharpenableWeapon(infused_crystal_javelin);
        blacklistSharpenableWeapon(infused_crystal_katana);
        blacklistSharpenableWeapon(infused_crystal_lance);
        blacklistSharpenableWeapon(infused_crystal_longbow);
        blacklistSharpenableWeapon(infused_crystal_longsword);
        blacklistSharpenableWeapon(infused_crystal_mace);
        blacklistSharpenableWeapon(infused_crystal_parrying_dagger);
        blacklistSharpenableWeapon(infused_crystal_pike);
        blacklistSharpenableWeapon(infused_crystal_quarterstaff);
        blacklistSharpenableWeapon(infused_crystal_rapier);
        blacklistSharpenableWeapon(infused_crystal_saber);
        blacklistSharpenableWeapon(infused_crystal_scythe);
        //TODO:
        //  blacklistSharpenableWeapon(infused_crystal_shield_basic);
        //  blacklistSharpenableWeapon(infused_crystal_shield_tower);
        blacklistSharpenableWeapon(infused_crystal_spear);
        blacklistSharpenableWeapon(infused_crystal_throwing_axe);
        blacklistSharpenableWeapon(infused_crystal_throwing_knife);
        blacklistSharpenableWeapon(infused_crystal_warhammer);

        class CrystalWeaponSharpeningRecipe extends GrindstoneRecipe {
            public CrystalWeaponSharpeningRecipe() {
                super(ItemStack.EMPTY, ItemStack.EMPTY, 40);
            }

            @Override
            public boolean matches(ItemStack stackIn) {
                return !stackIn.isEmpty() && stackIn.getItem() instanceof ISharpenableCrystalWeapon;
            }

            @Override
            public boolean isValid() {
                return true;
            }

            @Nonnull
            @Override
            public GrindResult grind(ItemStack stackIn) {
                ToolCrystalProperties prop = ItemCrystalSword.getToolProperties(stackIn);
                ToolCrystalProperties result = prop.grindCopy(rand);
                if(result == null) {
                    return GrindResult.failBreakItem();
                } else {
                    ItemCrystalSword.setToolProperties(stackIn, result);
                    return result.getSize() <= 0 ? GrindResult.failBreakItem() : GrindResult.success();
                }
            }
        }
        //Registering the new weapon sharpening recipe.
        GrindstoneRecipeRegistry.registerGrindstoneRecipe(new CrystalWeaponSharpeningRecipe());
    }

    public static void blacklistSharpenableWeapon(Item item) {
        if(item != null) {
            SwordSharpenHelper.blacklistedSharpenableSwordClassNames.add(item.getClass().getName());
        }
    }
}
