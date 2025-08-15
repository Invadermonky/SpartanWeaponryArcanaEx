package com.invadermonky.spartanweaponryarcanaex.items.shields;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.entities.EntityCrystalWeapon;
import com.invadermonky.spartanweaponryarcanaex.util.StringHelper;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibTags;
import com.oblivioussp.spartanshields.item.IDamageShield;
import com.oblivioussp.spartanshields.item.ItemShieldBasic;
import hellfirepvp.astralsorcery.common.entities.EntityFlare;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import hellfirepvp.astralsorcery.common.item.crystal.ToolCrystalProperties;
import hellfirepvp.astralsorcery.common.item.tool.ChargedCrystalToolBase;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemCrystalShield {
    protected final String unlocName;
    protected final Item inertVariant;

    public ItemCrystalShield(String unlocName, Item inertVariant) {
        this.unlocName = unlocName;
        this.inertVariant = inertVariant;
    }

    public ItemCrystalShield(String unlocName) {
        this(unlocName, Items.AIR);
    }

    public ItemShield createShield() {
        class ItemCrystalShieldBasic extends ItemShieldBasic implements CrystalPropertyItem, ChargedCrystalToolBase, IDamageShield {
            protected final Item inertVariant;
            protected final boolean isInfused;

            public ItemCrystalShieldBasic(String unlocName, Item inertVariant) {
                super(unlocName, 10, RegistryItems.crystalToolMaterial);
                this.inertVariant = inertVariant;
                this.isInfused = inertVariant != Items.AIR;
                this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "time"), (stack, worldIn, entityIn) -> {
                    if (worldIn != null) {
                        int time = ((int) (worldIn.getTotalWorldTime() % 28L)) / 2;
                        switch (time) {
                            case 1: //2, 3
                            case 7: //14, 15
                                return 1;
                            case 2: //4, 5
                            case 6: //12, 13
                                return 2;
                            case 3: //6, 7
                            case 5: //10, 11
                                return 3;
                            case 4: //8, 9
                                return 4;
                            default: //0, 1, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27
                                return 0;
                        }
                    }
                    return 0;
                });
            }

            @Nonnull
            @Override
            public Item getInertVariant() {
                return this.inertVariant;
            }

            @Override
            public int getMaxSize(ItemStack itemStack) {
                return 1800;
            }

            @Nullable
            @Override
            public CrystalProperties provideCurrentPropertiesOrNull(ItemStack stack) {
                return ItemCrystalSword.getToolProperties(stack);
            }

            @Override
            public void damageShield(ItemStack shieldStack, EntityPlayer player, Entity attacker, float damage) {
                if (!this.isInfused)
                    return;

                World world = player.world;
                if (!world.isRemote && world.rand.nextFloat() < 0.1f) {
                    EntityFlare entityFlare = new EntityFlare(player.world, player.posX, player.posY + 0.5, player.posZ);
                    entityFlare.setAmbient(false).setFollowingTarget(player);
                    entityFlare.getEntityData().setBoolean(LibTags.crystal_shield_flare, true);
                    world.spawnEntity(entityFlare);
                }
            }

            private void damageProperties(ItemStack stack, int damage) {
                ToolCrystalProperties prop = ItemCrystalSword.getToolProperties(stack);
                if (prop == null) {
                    stack.setItemDamage(stack.getMaxDamage());
                } else if (prop.getSize() <= 0) {
                    super.setDamage(stack, 11);
                } else if (damage >= 0) {
                    for (int i = 0; i < damage; ++i) {
                        double chance = Math.pow((double) prop.getCollectiveCapability() / 100.0, 2.0);
                        if (chance >= (double) itemRand.nextFloat()) {
                            if (itemRand.nextInt(3) == 0) {
                                prop = prop.copyDamagedCutting();
                            }

                            double purity = (double) prop.getPurity() / 100.0;
                            if (purity <= (double) itemRand.nextFloat() && itemRand.nextInt(3) == 0) {
                                prop = prop.copyDamagedCutting();
                            }
                        }
                    }
                    ItemCrystalSword.setToolProperties(stack, prop);
                }
            }

            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                ToolCrystalProperties prop = ItemCrystalSword.getToolProperties(stack);
                CrystalProperties.addPropertyTooltip(prop, tooltip, this.getMaxSize(stack));
                super.addInformation(stack, worldIn, tooltip, flagIn);
            }

            @Override
            public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
                return false;
            }

            @Override
            public void addEffectsTooltip(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                tooltip.add(TextFormatting.DARK_AQUA + I18n.format(StringHelper.getTranslationKey("material_bonus", "tooltip")));

                if (this.isInfused) {
                    tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_infused_crystal", "tooltip")));
                    if (GuiScreen.isShiftKeyDown()) {
                        tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_infused_crystal_shield", "tooltip", "desc")));
                    }
                } else {
                    tooltip.add(TextFormatting.GREEN + "- " + I18n.format(StringHelper.getTranslationKey("material_crystal", "tooltip")));
                    if (GuiScreen.isShiftKeyDown()) {
                        tooltip.add(TextFormatting.ITALIC + "  " + I18n.format(StringHelper.getTranslationKey("material_crystal_shield", "tooltip", "desc")));
                    }
                }
            }

            @Override
            public boolean isEnchantable(@NotNull ItemStack stack) {
                return true;
            }

            @Override
            public void getSubItems(@NotNull CreativeTabs tab, @NotNull NonNullList<ItemStack> items) {
                if (this.isInCreativeTab(tab)) {
                    ItemStack stack = new ItemStack(this);
                    CrystalProperties maxCelestial = CrystalProperties.getMaxCelestialProperties();
                    ItemCrystalSword.setToolProperties(stack, ToolCrystalProperties.merge(maxCelestial, maxCelestial));
                    items.add(stack);
                }
            }

            @Override
            public boolean isRepairable() {
                return false;
            }

            @Override
            public boolean hasCustomEntity(@NotNull ItemStack stack) {
                return true;
            }

            @Nullable
            @Override
            public Entity createEntity(@NotNull World world, Entity ei, @NotNull ItemStack itemstack) {
                EntityCrystalWeapon newItem = new EntityCrystalWeapon(ei.world, ei.posX, ei.posY, ei.posZ, itemstack);
                newItem.motionX = ei.motionX;
                newItem.motionY = ei.motionY;
                newItem.motionZ = ei.motionZ;
                newItem.setDefaultPickupDelay();
                if (ei instanceof EntityItem) {
                    newItem.setThrower(((EntityItem) ei).getThrower());
                    newItem.setOwner(((EntityItem) ei).getOwner());
                }
                return newItem;
            }

            @Override
            public void setDamage(@NotNull ItemStack stack, int damage) {
                super.setDamage(stack, 0);
                this.damageProperties(stack, damage);
            }
        }

        return new ItemCrystalShieldBasic(unlocName, inertVariant);
    }
}
