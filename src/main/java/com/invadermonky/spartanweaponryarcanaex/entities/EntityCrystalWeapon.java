package com.invadermonky.spartanweaponryarcanaex.entities;

import com.google.common.base.Predicates;
import com.invadermonky.spartanweaponryarcanaex.util.libs.ModIds;
import hellfirepvp.astralsorcery.client.effect.EffectHelper;
import hellfirepvp.astralsorcery.client.effect.fx.EntityFXFacingParticle;
import hellfirepvp.astralsorcery.common.block.network.BlockCollectorCrystalBase;
import hellfirepvp.astralsorcery.common.data.config.Config;
import hellfirepvp.astralsorcery.common.entities.EntityStarlightReacttant;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import hellfirepvp.astralsorcery.common.item.crystal.ToolCrystalProperties;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalSword;
import hellfirepvp.astralsorcery.common.item.tool.ItemCrystalToolBase;
import hellfirepvp.astralsorcery.common.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(modid = ModIds.ConstIds.astral_sorcery, iface = "hellfirepvp.astralsorcery.common.entities.EntityStarlightReacttant")
public class EntityCrystalWeapon extends EntityItem implements EntityStarlightReacttant {
    private static final AxisAlignedBB boxCraft = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    public static final int TOTAL_MERGE_TIME = 1000;
    private int inertMergeTick = 0;

    public EntityCrystalWeapon(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityCrystalWeapon(World worldIn, double x, double y, double z, ItemStack stack) {
        super(worldIn, x, y, z, stack);
    }

    public EntityCrystalWeapon(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.age + 5 >= this.lifespan) {
            this.age = 0;
        }

        if (Config.craftingLiqCrystalToolGrowth) {
            this.checkIncreaseConditions();
        }

    }

    private void checkIncreaseConditions() {
        if (this.world.isRemote) {
            if (this.canCraft()) {
                this.spawnCraftingParticles();
            }
        } else {
            if (this.getProperties() == null) {
                this.setDead();
            }

            if (this.canCraft()) {
                ++this.inertMergeTick;
                if (this.inertMergeTick >= 1000 && this.rand.nextInt(300) == 0) {
                    this.increaseSize();
                }
            } else {
                this.inertMergeTick = 0;
            }
        }
    }

    @Nullable
    private ToolCrystalProperties getProperties() {
        if(this.getItem().isEmpty()) {
            return null;
        } else {
            return this.getItem().getItem() instanceof CrystalPropertyItem ? ItemCrystalSword.getToolProperties(this.getItem()) : null;
        }
    }

    private void applyProperties(ToolCrystalProperties properties) {
        if (!this.getItem().isEmpty()) {
            if (this.getItem().getItem() instanceof CrystalPropertyItem) {
                ItemCrystalSword.setToolProperties(this.getItem(), properties);
            }
        }
    }

    private void increaseSize() {
        this.world.setBlockToAir(this.getPosition());
        List<Entity> foundItems = this.world.getEntitiesInAABBexcluding(this, boxCraft.offset(this.posX, this.posY, this.posZ).grow(0.1), Predicates.or(EntityUtils.selectItemClassInstaceof(ItemCrystalToolBase.class), EntityUtils.selectItemClassInstaceof(ItemCrystalSword.class)));
        if (foundItems.size() <= 0) {
            CrystalProperties prop = this.getProperties();
            if (prop != null) {
                int max = CrystalProperties.getMaxSize(this.getItem());
                int grow = this.rand.nextInt(250) + 100;
                max = Math.min(prop.getSize() + grow, max);
                int cut = Math.max(0, prop.getCollectiveCapability() - (this.rand.nextInt(10) + 10));
                this.applyProperties(new ToolCrystalProperties(max, prop.getPurity(), cut, prop.getFracturation(), prop.getSizeOverride()));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnCraftingParticles() {
        EntityFXFacingParticle p = EffectHelper.genericFlareParticle(this.posX + (double)this.rand.nextFloat() * 0.2 * (double)(this.rand.nextBoolean() ? 1 : -1), this.posY + (double)this.rand.nextFloat() * 0.2 * (double)(this.rand.nextBoolean() ? 1 : -1), this.posZ + (double)this.rand.nextFloat() * 0.2 * (double)(this.rand.nextBoolean() ? 1 : -1));
        p.motion((double)this.rand.nextFloat() * 0.02 * (double)(this.rand.nextBoolean() ? 1 : -1), (double)this.rand.nextFloat() * 0.04 * (double)(this.rand.nextBoolean() ? 1 : -1), (double)this.rand.nextFloat() * 0.02 * (double)(this.rand.nextBoolean() ? 1 : -1));
        p.gravity(0.01);
        p.scale(0.2F).setColor(BlockCollectorCrystalBase.CollectorCrystalType.ROCK_CRYSTAL.displayColor);
    }

    private boolean canCraft() {
        if (!this.isInLiquidStarlight(this)) {
            return false;
        } else {
            List<Entity> foundEntities = this.world.getEntitiesInAABBexcluding(this, boxCraft.offset(this.getPosition()), EntityUtils.selectEntities(Entity.class));
            return foundEntities.size() <= 0;
        }
    }
}
