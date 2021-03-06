package epicsquid.roots.spell;

import java.util.List;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.entity.spell.EntityPetalShell;
import epicsquid.roots.init.HerbRegistry;
import epicsquid.roots.network.fx.MessagePetalShellBurstFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;

public class SpellPetalShell extends SpellBase {

  public SpellPetalShell(String name) {
    super(name, TextFormatting.LIGHT_PURPLE, 255f / 255f, 192f / 255f, 240f / 255f, 255f / 255f, 255f / 255f, 255f / 255f);
    this.castType = SpellBase.EnumCastType.INSTANTANEOUS;
    this.cooldown = 120;

    addCost(HerbRegistry.getHerbByName("aubergine_seed"), 0.5f);
    addCost(HerbRegistry.getHerbByName("moonglow_leaf"), 0.25f);
    addIngredients(
        new ItemStack(Items.MELON_SEEDS),
        new ItemStack(ModItems.wildroot),
        new ItemStack(ModItems.terra_moss),
        new ItemStack(Items.SHIELD),
        new ItemStack(ModItems.pereskia_bulb)
    );
  }

  @Override
  public void cast(EntityPlayer player) {
    if (!player.world.isRemote) {
      List<EntityPetalShell> shells = player.world.getEntitiesWithinAABB(EntityPetalShell.class,
          new AxisAlignedBB(player.posX - 0.5, player.posY, player.posZ - 0.5, player.posX + 0.5, player.posY + 2.0, player.posZ + 0.5));
      boolean hasShell = false;
      for (EntityPetalShell s : shells) {
        if (s.getPlayerId().compareTo(player.getUniqueID()) == 0) {
          hasShell = true;
          s.getDataManager().set(s.getCharge(), Math.min(3, s.getDataManager().get(s.getCharge()) + 1));
          s.getDataManager().setDirty(s.getCharge());
        }
      }
      if (!hasShell) {
        EntityPetalShell shell = new EntityPetalShell(player.world);
        shell.setPosition(player.posX, player.posY + 1.0f, player.posZ);
        shell.setPlayer(player.getUniqueID());
        player.world.spawnEntity(shell);
      }
      PacketHandler.INSTANCE.sendToAll(new MessagePetalShellBurstFX(player.posX, player.posY + 1.0f, player.posZ));
    }
  }

}
