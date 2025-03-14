package com.invadermonky.spartanweaponryarcanaex.compat;

import WayofTime.bloodmagic.alchemyArray.AlchemyArrayEffectBinding;
import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.client.render.alchemyArray.BindingAlchemyCircleRenderer;
import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.core.registry.AlchemyArrayRecipeRegistry;
import WayofTime.bloodmagic.item.types.ComponentTypes;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.util.Utils;
import com.invadermonky.spartanweaponryarcanaex.api.IModCompat;
import com.invadermonky.spartanweaponryarcanaex.config.ConfigHandlerSE;
import com.invadermonky.spartanweaponryarcanaex.items.bound.*;
import com.invadermonky.spartanweaponryarcanaex.items.sentient.*;
import com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE.*;

public class BloodMagicCompat implements IModCompat {
    @Override
    public void initializeWeapons() {
        if(ConfigHandlerSE.blood_magic.enableBoundWeapons) {
            bound_battleaxe = new ItemBoundBattleaxe();
            bound_boomerang = new ItemBoundBoomerang();
            bound_crossbow = new ItemBoundCrossbow();
            bound_dagger = new ItemBoundDagger();
            bound_glaive = new ItemBoundGlaive();
            bound_greatsword = new ItemBoundGreatsword();
            bound_halberd = new ItemBoundHalberd();
            bound_hammer = new ItemBoundHammer();
            bound_javelin = new ItemBoundJavelin();
            bound_katana = new ItemBoundKatana();
            bound_lance = new ItemBoundLance();
            bound_longbow = !ConfigHandler.woodenLongbowOnly ?new ItemBoundLongbow() : null;
            bound_longsword = new ItemBoundLongsword();
            bound_mace = new ItemBoundMace();
            bound_parrying_dagger = new ItemBoundParryingDagger();
            bound_pike = new ItemBoundPike();
            bound_quarterstaff = new ItemBoundQuarterstaff();
            bound_rapier = new ItemBoundRapier();
            bound_saber = new ItemBoundSaber();
            bound_scythe = new ItemBoundScythe();
            //TODO:
            //  bound_shield_basic = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.bound_shield_basic) : null;
            //  bound_shield_tower = ModIds.spartan_shields.isLoaded ? ItemBoundShieldWrapper.build(LibNames.bound_shield_tower) : null;
            bound_spear = new ItemBoundSpear();
            bound_throwing_axe = new ItemBoundThrowingAxe();
            bound_throwing_knife = new ItemBoundThrowingKnife();
            bound_warhammer = new ItemBoundWarhammer();
        }

        if(ConfigHandlerSE.blood_magic.enableSentientWeapons) {
            sentient_battleaxe = new ItemSentientBattleaxe();
            sentient_boomerang = new ItemSentientBoomerang();
            sentient_crossbow = new ItemSentientCrossbow();
            sentient_dagger = new ItemSentientDagger();
            sentient_glaive = new ItemSentientGlaive();
            sentient_greatsword = new ItemSentientGreatsword();
            sentient_halberd = new ItemSentientHalberd();
            sentient_hammer = new ItemSentientHammer();
            sentient_javelin = new ItemSentientJavelin();
            sentient_katana = new ItemSentientKatana();
            sentient_lance = new ItemSentientLance();
            sentient_longbow = new ItemSentientLongbow();
            sentient_longsword = new ItemSentientLongsword();
            sentient_mace = new ItemSentientMace();
            sentient_parrying_dagger = new ItemSentientParryingDagger();
            sentient_pike = new ItemSentientPike();
            sentient_quarterstaff = new ItemSentientQuarterstaff();
            sentient_rapier = new ItemSentientRapier();
            sentient_saber = new ItemSentientSaber();
            sentient_scythe = new ItemSentientScythe();
            //TODO:
            //  sentient_shield_basic = ModIds.spartan_shields.isLoaded ? ItemSentientShieldWrapper.build(LibNames.sentient_shield_basic) : null;
            //  sentient_shield_tower = ModIds.spartan_shields.isLoaded ? ItemSentientShieldWrapper.build(LibNames.sentient_shield_tower) : null;
            sentient_spear = new ItemSentientSpear();
            sentient_throwing_axe = new ItemSentientThrowingAxe();
            sentient_throwing_knife = new ItemSentientThrowingKnife();
            sentient_warhammer = new ItemSentientWarhammer();
        }
    }

    @Override
    public void initializeRecipes(IForgeRegistry<IRecipe> registry) {
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_battleaxe, ItemRegistrySW.battleaxeDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_boomerang, ItemRegistrySW.boomerangDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_crossbow, ItemRegistrySW.crossbowDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_dagger, ItemRegistrySW.daggerDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_glaive, ItemRegistrySW.glaiveDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_greatsword, ItemRegistrySW.greatswordDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_halberd, ItemRegistrySW.halberdDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_hammer, ItemRegistrySW.hammerDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_javelin, ItemRegistrySW.javelinDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_katana, ItemRegistrySW.katanaDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_lance, ItemRegistrySW.lanceDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_longbow, ItemRegistrySW.longbowDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_longsword, ItemRegistrySW.longswordDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_mace, ItemRegistrySW.maceDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_parrying_dagger, ItemRegistrySW.parryingDaggerDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_pike, ItemRegistrySW.pikeDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_quarterstaff, ItemRegistrySW.quarterstaffDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_rapier, ItemRegistrySW.rapierDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_saber, ItemRegistrySW.saberDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_scythe, ItemRegistrySW.scytheDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_spear, ItemRegistrySW.spearDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_throwing_axe, ItemRegistrySW.throwingAxeDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_throwing_knife, ItemRegistrySW.throwingKnifeDiamond);
        BloodMagicCompat.registerBoundToolRecipe(ModItemsSE.bound_warhammer, ItemRegistrySW.warhammerDiamond);

        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_battleaxe, ItemRegistrySW.battleaxeIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_boomerang, ItemRegistrySW.boomerangIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_crossbow,  70.0, 0.0, new ItemStack(ItemRegistrySW.crossbowIron), new ItemStack(RegistrarBloodMagicItems.SOUL_GEM, 1, 1), "string", "string");
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_dagger, ItemRegistrySW.daggerIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_glaive, ItemRegistrySW.glaiveIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_greatsword, ItemRegistrySW.greatswordIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_halberd, ItemRegistrySW.halberdIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_hammer, ItemRegistrySW.hammerIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_javelin, ItemRegistrySW.javelinIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_katana, ItemRegistrySW.katanaIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_lance, ItemRegistrySW.lanceIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_longbow, 70.0, 0.0, new ItemStack(ItemRegistrySW.longbowIron), new ItemStack(RegistrarBloodMagicItems.SOUL_GEM, 1, 1), "string", "string");
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_longsword, ItemRegistrySW.longswordIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_mace, ItemRegistrySW.maceIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_parrying_dagger, ItemRegistrySW.parryingDaggerIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_pike, ItemRegistrySW.pikeIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_quarterstaff, ItemRegistrySW.quarterstaffIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_rapier, ItemRegistrySW.rapierIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_saber, ItemRegistrySW.saberIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_scythe, ItemRegistrySW.scytheIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_spear, ItemRegistrySW.spearIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_throwing_axe, ItemRegistrySW.throwingAxeIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_throwing_knife, ItemRegistrySW.throwingKnifeIron);
        BloodMagicCompat.registerSoulForgeToolRecipe(ModItemsSE.sentient_warhammer, ItemRegistrySW.warhammerIron);
    }

    @Override
    public void onProjectileImpact(ProjectileImpactEvent event) {
        Entity projectile = event.getEntity();
        World world = projectile.world;
        if(!world.isRemote) {
            NBTTagCompound entityTag = projectile.getEntityData();
            if(entityTag.hasKey(LibTags.sentient_attributes)) {
                Entity target = event.getRayTraceResult().entityHit;
                EnumDemonWillType type = EnumDemonWillType.values()[entityTag.getCompoundTag(LibTags.sentient_attributes).getInteger(LibTags.will_type)];
                int willBracket = entityTag.getCompoundTag(LibTags.sentient_attributes).getInteger(LibTags.will_bracket);
                int duration;
                int amplifier;

                switch (type) {
                    case CORROSIVE:
                        if(target instanceof EntityLivingBase) {
                            duration = willBracket >= 0 ? LibAttributes.Ranged.poisonDuration[willBracket] : 0;
                            if(duration > 0) {
                                amplifier = LibAttributes.Ranged.poisonLevel[willBracket];
                                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.POISON, duration, amplifier));
                            }
                        }
                        break;
                    case DESTRUCTIVE:
                        float radius = willBracket >= 0 ? LibAttributes.Ranged.destructiveExplosionRadius[willBracket] : 0.0f;
                        projectile.world.createExplosion(projectile, projectile.posX, projectile.posY, projectile.posZ, radius, false);
                        break;
                    case VENGEFUL:
                        if(target instanceof EntityLivingBase) {
                            duration = willBracket >= 0 ? LibAttributes.Ranged.slownessDuration[willBracket] : 0;
                            if(duration > 0) {
                                amplifier = LibAttributes.Ranged.slownessLevel[willBracket];
                                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, amplifier));
                            }
                        }
                        break;
                    case STEADFAST:
                        if(target instanceof EntityLivingBase) {
                            duration = willBracket >= 0 ? LibAttributes.Ranged.levitationDuration[willBracket] : 0;
                            if(duration > 0) {
                                amplifier = LibAttributes.Ranged.levitationLevel[willBracket];
                                ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, duration, amplifier));
                            }
                        }
                }
            }
        }
    }

    public static void registerSoulForgeToolRecipe(Item outputItem, Item catalystItem) {
        registerSoulForgeToolRecipe(outputItem, 0, 0, new ItemStack(RegistrarBloodMagicItems.SOUL_GEM), new ItemStack(catalystItem));
    }

    public static void registerSoulForgeToolRecipe(Item outputItem, double minimumSouls, double soulDrain, Object... ingredients) {
        if(outputItem == null) return;
        BloodMagicAPI.INSTANCE.getRecipeRegistrar().addTartaricForge(new ItemStack(outputItem), minimumSouls, soulDrain, ingredients);
    }

    public static void registerBoundToolRecipe(Item outputItem, Item catalystItem) {
        if(outputItem == null) return;
        AlchemyArrayRecipeRegistry.registerRecipe(
                ComponentTypes.REAGENT_BINDING.getStack(),
                new ItemStack(catalystItem),
                new AlchemyArrayEffectBinding(outputItem.getRegistryName().getPath(), Utils.setUnbreakable(new ItemStack(outputItem))),
                new BindingAlchemyCircleRenderer()
        );
    }
}
