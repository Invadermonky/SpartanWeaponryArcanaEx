package com.invadermonky.spartanweaponryarcanaex.client.render;

import com.invadermonky.spartanweaponryarcanaex.SpartanWeaponryArcanaEx;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TileEntityItemStackRendererSE extends TileEntityItemStackRenderer {
    protected final BannerTextures.Cache SHIELD_BANNER_DESIGNS;
    protected final ResourceLocation TEXTURE_SHIELD_NO_PATTERN;
    protected final Item shield;
    protected final ModelShield shieldModel;
    protected final TileEntityBanner banner = new TileEntityBanner();

    public TileEntityItemStackRendererSE(ItemShield itemShield, String textureNoPattern, String texturePattern, String bannerID, ModelShield modelShield) {
        this.shield = itemShield;
        this.shieldModel = modelShield;
        this.TEXTURE_SHIELD_NO_PATTERN = new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, textureNoPattern);
        this.SHIELD_BANNER_DESIGNS = new BannerTextures.Cache(bannerID, new ResourceLocation(SpartanWeaponryArcanaEx.MOD_ID, texturePattern), "textures/entity/shield/");
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack) {
        this.renderByItem(stack, 1.0f);
    }

    @Override
    public void renderByItem(ItemStack stack, float partialTicks) {
        Item item = stack.getItem();
        if (item == this.shield) {
            if (stack.getSubCompound("BlockEntityTag") != null) {
                this.banner.setItemValues(stack, true);
                Minecraft.getMinecraft().getTextureManager().bindTexture(this.SHIELD_BANNER_DESIGNS.getResourceLocation(this.banner.getPatternResourceLocation(), this.banner.getPatternList(), this.banner.getColorList()));
            } else {
                Minecraft.getMinecraft().getTextureManager().bindTexture(this.TEXTURE_SHIELD_NO_PATTERN);
            }
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0f, -1.0f, -1.0f);
            this.shieldModel.render();
            GlStateManager.popMatrix();
        }
        super.renderByItem(stack, partialTicks);
    }
}
