package epicsquid.roots.spell;

import java.util.List;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticalworld.init.ModBlocks;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.init.HerbRegistry;
import epicsquid.roots.network.fx.MessageAcidCloudFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class SpellAcidCloud extends SpellBase {

  public SpellAcidCloud(String name) {
    super(name, TextFormatting.DARK_GREEN, 80f / 255f, 160f / 255f, 40f / 255f, 64f / 255f, 96f / 255f, 32f / 255f);
    this.castType = SpellBase.EnumCastType.CONTINUOUS;
    this.cooldown = 24;

    addCost(HerbRegistry.getHerbByName("terra_moss"), 0.0625f);
    addCost(HerbRegistry.getHerbByName("baffle_cap"), 0.05f);
    addIngredients(
        new ItemStack(Items.SPIDER_EYE),
        new ItemStack(Item.getItemFromBlock(ModBlocks.baffle_cap_mushroom)),
        new ItemStack(Items.SLIME_BALL),
        new ItemStack(ModItems.terra_moss),
        new ItemStack(ModItems.wildroot)
    );
  }

  @Override
  public void cast(EntityPlayer player) {
    if (!player.world.isRemote) {
      List<EntityLivingBase> entities = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
          new AxisAlignedBB(player.posX - 4.0, player.posY - 1.0, player.posZ - 4.0, player.posX + 4.0, player.posY + 3.0, player.posZ + 4.0));
      for (EntityLivingBase e : entities) {
        if (!(e instanceof EntityPlayer && !FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled())
            && e.getUniqueID().compareTo(player.getUniqueID()) != 0) {
          e.attackEntityFrom(DamageSource.causeMobDamage(player), 1.0f);
          e.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 80, 0));
          e.setRevengeTarget(player);
          e.setLastAttackedEntity(player);
        }
      }
      PacketHandler.INSTANCE.sendToAll(new MessageAcidCloudFX(player.posX, player.posY + player.getEyeHeight(), player.posZ));
    }
  }

}
