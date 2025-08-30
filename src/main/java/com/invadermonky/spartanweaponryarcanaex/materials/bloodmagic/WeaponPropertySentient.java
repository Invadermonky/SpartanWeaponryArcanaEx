package com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.iface.ISentientSwordEffectProvider;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import WayofTime.bloodmagic.soul.PlayerDemonWillHandler;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.materials.util.WeaponPropertyWithCallbackSE;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibAttributes.Ranged;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class WeaponPropertySentient extends WeaponPropertyWithCallbackSE implements IWeaponCallback {
    public static final WeaponPropertySentient SENTIENT_PROPERTY;
    public static final ToolMaterialEx SENTIENT_MATERIAL_EX;

    public WeaponPropertySentient() {
        super(LibNames.material_sentient);
    }

    @Override
    public void onItemUpdate(ToolMaterialEx material, ItemStack stack, World world, EntityLivingBase entity, int itemSlot, boolean isSelected) {
        super.onItemUpdate(material, stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public void onHitEntity(ToolMaterialEx material, ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, Entity projectile) {
        if (projectile instanceof EntityThrownWeapon) {
            this.handleSentientProjectileHit(stack, target, attacker, (EntityThrownWeapon) projectile);
        } else {
            this.handleSentientMeleeHit(stack, target, attacker);
        }
    }

    @Override
    public void onCreateItem(ToolMaterialEx material, ItemStack stack) {
        super.onCreateItem(material, stack);
    }

    @Override
    public void applyAttributeToArrow(World world, EntityArrow entityArrow, EntityPlayer player, ItemStack bowStack) {
        if (bowStack.getItem() instanceof ISpartanWillBow) {
            ((ISpartanWillBow) bowStack.getItem()).applySentientArrowAttributes(bowStack, player, entityArrow);
        }
    }

    protected void handleSentientProjectileHit(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker, EntityThrownWeapon projectile) {
        if (stack.getItem() instanceof ISpartanWillWeapon && attacker instanceof EntityPlayer) {
            ISpartanWillWeapon willWeapon = (ISpartanWillWeapon) stack.getItem();
            EntityPlayer player = (EntityPlayer) attacker;
            willWeapon.recalculatePowers(stack, player.world, player);
            EnumDemonWillType type = willWeapon.getCurrentType(stack);
            int willBracket = willWeapon.getLevel(stack, PlayerDemonWillHandler.getTotalDemonWill(type, player));
            int duration;
            int amplifier;

            switch (type) {
                case CORROSIVE:
                    duration = willBracket >= 0 ? Ranged.poisonDuration[willBracket] : 0;
                    if (duration > 0) {
                        amplifier = Ranged.poisonLevel[willBracket];
                        target.addPotionEffect(new PotionEffect(MobEffects.POISON, duration, amplifier));
                    }
                    break;
                case DESTRUCTIVE:
                    float radius = willBracket >= 0 ? Ranged.destructiveExplosionRadius[willBracket] : 0.0f;
                    target.world.createExplosion(projectile, projectile.posX, projectile.posY, projectile.posZ, radius, false);
                    break;
                case VENGEFUL:
                    duration = willBracket >= 0 ? Ranged.slownessDuration[willBracket] : 0;
                    if (duration > 0) {
                        amplifier = Ranged.slownessLevel[willBracket];
                        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, amplifier));
                    }
                    break;
                case STEADFAST:
                    duration = willBracket >= 0 ? Ranged.levitationDuration[willBracket] : 0;
                    if (duration > 0) {
                        amplifier = Ranged.levitationLevel[willBracket];
                        target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, duration, amplifier));
                    }
                    break;
            }
        }
    }

    protected void handleSentientMeleeHit(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (stack.getItem() instanceof ISpartanWillWeapon && attacker instanceof EntityPlayer) {
            ISpartanWillWeapon willWeapon = (ISpartanWillWeapon) stack.getItem();
            EntityPlayer player = (EntityPlayer) attacker;
            willWeapon.recalculatePowers(stack, player.world, player);
            EnumDemonWillType type = willWeapon.getCurrentType(stack);
            int willBracket = willWeapon.getLevel(stack, PlayerDemonWillHandler.getTotalDemonWill(type, player));
            willWeapon.applyEffectToEntity(type, willBracket, target, attacker);

            ItemStack offStack = player.getHeldItemOffhand();
            if (offStack.getItem() instanceof ISentientSwordEffectProvider) {
                ISentientSwordEffectProvider provider = (ISentientSwordEffectProvider) offStack.getItem();
                if (provider.providesEffectForWill(type))
                    provider.applyOnHitEffect(type, stack, offStack, attacker, target);
            }
        }
    }

    @Override
    public void onTooltip(ToolMaterialEx toolMaterialEx, ItemStack itemStack, World world, List<String> tooltip, ITooltipFlag iTooltipFlag) {
        tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));
        tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_sentient", "tooltip")));
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_sentient", "tooltip", "desc")));
        }
    }

    static {
        SENTIENT_PROPERTY = new WeaponPropertySentient();
        SENTIENT_MATERIAL_EX = new ToolMaterialEx(
                LibNames.material_sentient,
                RegistrarBloodMagicItems.SOUL_TOOL_MATERIAL,
                "bindingReagent",
                SpartanWeaponryArcanaEx.MOD_ID,
                Item.ToolMaterial.IRON.getAttackDamage(),
                SENTIENT_PROPERTY
        );
    }
}
