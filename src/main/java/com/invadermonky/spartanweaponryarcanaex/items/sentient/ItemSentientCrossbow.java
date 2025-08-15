package com.invadermonky.spartanweaponryarcanaex.items.sentient;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import WayofTime.bloodmagic.util.helper.TextHelper;
import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import com.invadermonky.spartanweaponryarcanaex.client.CreativeTabSE;
import com.invadermonky.spartanweaponryarcanaex.items.base.ItemCrossbowSE;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.ISpartanWillBow;
import com.invadermonky.spartanweaponryarcanaex.materials.bloodmagic.WeaponPropertySentient;
import com.invadermonky.spartanweaponryarcanaex.util.libs.LibNames;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSentientCrossbow extends ItemCrossbowSE implements ISpartanWillBow {
    public ItemSentientCrossbow() {
        super(LibNames.sentient, WeaponPropertySentient.SENTIENT_MATERIAL_EX, WeaponPropertySentient.SENTIENT_PROPERTY);
        this.setCreativeTab(CreativeTabSE.TAB_SE);
        this.addPropertyOverride(new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, "sentient"), (stack, worldIn, entityIn) -> this.getCurrentType(stack).ordinal());
        this.setNoReequipAnimation();
        this.setHasCustomDisplayName();
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        this.recalculatePowers(playerIn.getHeldItem(handIn), playerIn.world, playerIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == RegistrarBloodMagicItems.ITEM_DEMON_CRYSTAL || super.getIsRepairable(toRepair, repair);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound()) {
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.currentType." + this.getCurrentType(stack).getName().toLowerCase()));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
