package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.entity.mob.EntitySentientSpecter;
import WayofTime.bloodmagic.iface.IMultiWillTool;
import WayofTime.bloodmagic.iface.ISentientTool;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.soul.IDemonWill;
import WayofTime.bloodmagic.soul.IDemonWillWeapon;
import WayofTime.bloodmagic.soul.PlayerDemonWillHandler;
import WayofTime.bloodmagic.util.helper.NBTHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes.Swords;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.ArrayList;
import java.util.List;

import static WayofTime.bloodmagic.util.Constants.NBT.*;

@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.IMultiWillTool", striprefs = true)
@Optional.Interface(modid = ModIds.ConstIds.blood_magic, iface = "WayofTime.bloodmagic.iface.ISentientTool", striprefs = true)
public interface ISpartanWillWeapon extends IDemonWillWeapon, IMultiWillTool, ISentientTool {
    default void recalculatePowers(ItemStack stack, World world, EntityPlayer player) {
        EnumDemonWillType type = PlayerDemonWillHandler.getLargestWillType(player);
        double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
        this.recalculatePowers(stack, type, soulsRemaining);
        this.syncWeaponValues(stack);
    }

    default void recalculatePowers(ItemStack stack, EnumDemonWillType type, double will) {
        this.setCurrentType(stack, will > 0.0 ? type : EnumDemonWillType.DEFAULT);
        int level = this.getLevel(stack, will);
        double drain = level >= 0 ? Swords.soulDrainPerSwing[level] : 0.0;
        double baseDamage = this.getBaseAttackDamage();
        double baseAttackSpeed = this.getBaseAttackSpeed();
        double extraDamage = this.getExtraDamage(type, level);
        double attackSpeedModifier = this.getAttackSpeedModifier(type, level);
        this.setCurrentWillBracket(stack, level);
        this.setDrainOfActivatedWeapon(stack, drain);
        this.setDamageOfActivatedWeapon(stack, baseDamage + extraDamage);
        this.setAttackSpeedOfWeapon(stack, level >= 0 ? (baseAttackSpeed + attackSpeedModifier) : this.getBaseAttackSpeed());
        this.setStaticDropOfActivatedWeapon(stack, level >= 0 ? Swords.staticDrop[level] : 1.0);
        this.setDropOfActivatedWeapon(stack, level >= 0 ? Swords.soulDrop[level] : 0.0);
        this.setHealthBonusOfWeapon(stack, level >= 0 ? this.getHealthBonus(type, level) : 0.0);
        this.setMovementSpeedOfWeapon(stack, level >= 0 ? this.getMovementSpeed(type, level) : 0.0);
    }

    void syncWeaponValues(ItemStack stack);

    double getBaseAttackDamage();

    double getDamageMultiplier();

    double getBaseAttackSpeed();

    double getBonusDamageMultiplier();

    @Optional.Method(modid = ModIds.ConstIds.blood_magic)
    @Override
    default EnumDemonWillType getCurrentType(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return !tag.hasKey(WILL_TYPE) ? EnumDemonWillType.DEFAULT : EnumDemonWillType.valueOf(tag.getString(WILL_TYPE).toUpperCase());
    }

    default void setCurrentType(ItemStack stack, EnumDemonWillType type) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setString(WILL_TYPE, type.toString());
    }

    default int getLevel(ItemStack stack, double soulsRemaining) {
        int level = -1;
        for (int i = 0; i < Swords.soulBracket.length; i++) {
            if (soulsRemaining >= (double) Swords.soulBracket[i]) {
                level = i;
            } else {
                break;
            }
        }
        return level;
    }

    default double getExtraDamage(EnumDemonWillType type, int willBracket) {
        if (willBracket < 0) {
            return 0.0;
        } else {
            double damage;
            switch (type) {
                case CORROSIVE:
                case DEFAULT:
                    damage = Swords.defaultDamageAdded[willBracket];
                    break;
                case DESTRUCTIVE:
                    damage = Swords.destructiveDamageAdded[willBracket];
                    break;
                case VENGEFUL:
                    damage = Swords.vengefulDamageAdded[willBracket];
                    break;
                case STEADFAST:
                    damage = Swords.steadfastDamageAdded[willBracket];
                    break;
                default:
                    damage = 0;
                    break;
            }
            return damage * this.getBonusDamageMultiplier();
        }
    }

    default double getAttackSpeedModifier(EnumDemonWillType type, int willBracket) {
        switch (type) {
            case DESTRUCTIVE:
                return Swords.destructiveAttackSpeedModifier[willBracket];
            case VENGEFUL:
                return Swords.vengefulAttackSpeedModifier[willBracket];
            default:
                return 0;
        }
    }

    default double getMovementSpeed(EnumDemonWillType type, int willBracket) {
        return type == EnumDemonWillType.VENGEFUL ? Swords.movementSpeed[willBracket] : 0.0;
    }

    default double getHealthBonus(EnumDemonWillType type, int willBracket) {
        return type == EnumDemonWillType.STEADFAST ? Swords.healthBonus[willBracket] : 0.0;
    }

    default void applyEffectToEntity(EnumDemonWillType type, int willBracket, EntityLivingBase target, EntityLivingBase attacker) {
        switch (type) {
            case CORROSIVE:
                target.addPotionEffect(new PotionEffect(MobEffects.WITHER, Swords.poisonTime[willBracket], Swords.poisonLevel[willBracket]));
                break;
            case STEADFAST:
                if (!target.isEntityAlive()) {
                    float absorption = attacker.getAbsorptionAmount();
                    attacker.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, Swords.absorptionTime[willBracket], 127));
                    attacker.setAbsorptionAmount((float) Math.min((absorption + target.getMaxHealth() * 0.05f), Swords.maxAbsorptionHearts));
                }
                break;
            default:
                break;
        }
    }

    @Override
    default List<ItemStack> getRandomDemonWillDrop(EntityLivingBase killedEntity, EntityLivingBase attackingEntity, ItemStack stack, int looting) {
        List<ItemStack> soulList = new ArrayList<>();
        if (killedEntity.getEntityWorld().getDifficulty() == EnumDifficulty.PEACEFUL || killedEntity instanceof IMob) {
            double willModifier = killedEntity instanceof EntitySlime ? 0.67 : 1.0;
            IDemonWill soul = (IDemonWill) RegistrarBloodMagicItems.MONSTER_SOUL;
            EnumDemonWillType type = this.getCurrentType(stack);

            for (int i = 0; i <= looting; ++i) {
                if (i == 0 || attackingEntity.getEntityWorld().rand.nextDouble() < 0.4) {
                    ItemStack soulStack = soul.createWill(type.ordinal(), willModifier * (this.getDropOfActivatedWeapon(stack) * attackingEntity.getEntityWorld().rand.nextDouble() + this.getStaticDropOfActivatedWeapon(stack)) * (double) killedEntity.getMaxHealth() / 20.0);
                    soulList.add(soulStack);
                }
            }

        }
        return soulList;
    }

    @Optional.Method(modid = ModIds.ConstIds.blood_magic)
    @Override
    default boolean spawnSentientEntityOnDrop(ItemStack stack, EntityPlayer player) {
        World world = player.world;
        if (!world.isRemote) {
            this.recalculatePowers(stack, world, player);
            EnumDemonWillType type = this.getCurrentType(stack);
            double soulsRemaining = PlayerDemonWillHandler.getTotalDemonWill(type, player);
            if (soulsRemaining < 1024.0) {
                return false;
            } else {
                PlayerDemonWillHandler.consumeDemonWill(type, player, 100.0);
                EntitySentientSpecter entity = new EntitySentientSpecter(world);
                entity.setPosition(player.posX + player.getHorizontalFacing().getDirectionVec().getX(), player.posY, player.posZ + player.getHorizontalFacing().getDirectionVec().getZ());
                world.spawnEntity(entity);
                entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
                entity.setType(type);
                entity.setOwner(player);
                entity.setTamed(true);
                return true;
            }
        }
        return false;
    }

    default double getDamageOfActivatedWeapon(ItemStack stack) {
        return stack.hasTagCompound() ? stack.getTagCompound().getDouble(SOUL_SWORD_DAMAGE) : this.getBaseAttackDamage();
    }

    default void setDamageOfActivatedWeapon(ItemStack stack, double damage) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_DAMAGE, damage);
    }

    default double getAttackSpeedOfWeapon(ItemStack stack) {
        return stack.hasTagCompound() ? stack.getTagCompound().getDouble(SOUL_SWORD_ATTACK_SPEED) : this.getBaseAttackSpeed();
    }

    default void setCurrentWillBracket(ItemStack stack, int willBracket) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setInteger(LibTags.will_bracket, willBracket);
    }

    default int getCurrentWillBracket(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getInteger(LibTags.will_bracket);
    }

    default void setAttackSpeedOfWeapon(ItemStack stack, double speed) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_ATTACK_SPEED, speed);
    }

    default double getDrainOfActivatedWeapon(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(SOUL_SWORD_ACTIVE_DRAIN);
    }

    default void setDrainOfActivatedWeapon(ItemStack stack, double drain) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_ACTIVE_DRAIN, drain);
    }

    default double getStaticDropOfActivatedWeapon(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(SOUL_SWORD_STATIC_DROP);
    }

    default void setStaticDropOfActivatedWeapon(ItemStack stack, double drop) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_STATIC_DROP, drop);
    }

    default double getDropOfActivatedWeapon(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(SOUL_SWORD_DROP);
    }

    default void setDropOfActivatedWeapon(ItemStack stack, double drop) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_DROP, drop);
    }

    default double getHealthBonusOfWeapon(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(SOUL_SWORD_HEALTH);
    }

    default void setHealthBonusOfWeapon(ItemStack stack, double hp) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_HEALTH, hp);
    }

    default double getMovementSpeedOfWeapon(ItemStack stack) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getDouble(SOUL_SWORD_SPEED);
    }

    default void setMovementSpeedOfWeapon(ItemStack stack, double speed) {
        NBTHelper.checkNBT(stack);
        NBTTagCompound tag = stack.getTagCompound();
        tag.setDouble(SOUL_SWORD_SPEED, speed);
    }
}
