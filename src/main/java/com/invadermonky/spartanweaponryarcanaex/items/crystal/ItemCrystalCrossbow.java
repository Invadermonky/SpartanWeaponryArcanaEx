package com.invadermonky.spartanweaponryarcanaex.items.crystal;

import com.invadermonky.spartanweaponryarcanaex.entities.EntityCrystalWeapon;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemCrossbowSE;
import com.invadermonky.spartanweaponryarcanaex.registry.ModItemsSE;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import com.oblivioussp.spartanweaponry.api.IWeaponCallback;
import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import hellfirepvp.astralsorcery.common.item.crystal.ToolCrystalProperties;
import hellfirepvp.astralsorcery.common.item.tool.ChargedCrystalToolBase;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(modid = ModIds.ConstIds.astral_sorcery, iface = "hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem", striprefs = true)
@Optional.Interface(modid = ModIds.ConstIds.astral_sorcery, iface = "hellfirepvp.astralsorcery.common.item.crystal.ChargedCrystalToolBase", striprefs = true)
public class ItemCrystalCrossbow extends ItemCrossbowSE implements CrystalPropertyItem, ChargedCrystalToolBase {
    public ItemCrystalCrossbow(String unlocName, ToolMaterialEx material, IWeaponCallback callback) {
        super(unlocName, material, callback);
    }

    @Optional.Method(modid = ModIds.ConstIds.astral_sorcery)
    @Nonnull
    @Override
    public Item getInertVariant() {
        return ModItemsSE.crystal_crossbow;
    }

    @Optional.Method(modid = ModIds.ConstIds.astral_sorcery)
    @Override
    public int getMaxSize(ItemStack itemStack) {
        return 1800;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ToolCrystalProperties prop = ItemCrystalSword.getToolProperties(stack);
        CrystalProperties.addPropertyTooltip(prop, tooltip, this.getMaxSize(stack));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Nullable
    @Override
    public CrystalProperties provideCurrentPropertiesOrNull(ItemStack stack) {
        return ItemCrystalSword.getToolProperties(stack);
    }

    @Override
    public int getMaxDamage() {
        return 10;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(World world, Entity ei, ItemStack itemstack) {
        EntityCrystalWeapon newItem = new EntityCrystalWeapon(ei.world, ei.posX, ei.posY, ei.posZ, itemstack);
        newItem.motionX = ei.motionX;
        newItem.motionY = ei.motionY;
        newItem.motionZ = ei.motionZ;
        newItem.setDefaultPickupDelay();
        if (ei instanceof EntityItem) {
            newItem.setThrower(((EntityItem)ei).getThrower());
            newItem.setOwner(((EntityItem)ei).getOwner());
        }
        return newItem;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
        this.damageProperties(stack, damage);
    }

    private void damageProperties(ItemStack stack, int damage) {
        ToolCrystalProperties prop = ItemCrystalSword.getToolProperties(stack);
        if (prop == null) {
            stack.setItemDamage(stack.getMaxDamage());
        } else if (prop.getSize() <= 0) {
            super.setDamage(stack, 11);
        } else if (damage >= 0) {
            for(int i = 0; i < damage; ++i) {
                double chance = Math.pow((double)prop.getCollectiveCapability() / 100.0, 2.0);
                if (chance >= (double)itemRand.nextFloat()) {
                    if (itemRand.nextInt(3) == 0) {
                        prop = prop.copyDamagedCutting();
                    }

                    double purity = (double)prop.getPurity() / 100.0;
                    if (purity <= (double)itemRand.nextFloat() && itemRand.nextInt(3) == 0) {
                        prop = prop.copyDamagedCutting();
                    }
                }
            }
            ItemCrystalSword.setToolProperties(stack, prop);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Override
    public boolean isRepairable() {
        return false;
    }
}
