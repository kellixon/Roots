package epicsquid.roots.ritual;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.entity.ritual.EntityRitualNaturalAura;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualNaturalAura extends RitualBase {

  public RitualNaturalAura(String name, int duration) {
    super(name, duration);
    addIngredients(
            new ItemStack(Items.WHEAT), 
            new ItemStack(ModItems.wildroot), 
            new ItemStack(ModItems.aubergine),
            new ItemStack(Items.BONE), 
            new ItemStack(Items.BONE)
    );
  }

  @Override
  public boolean canFire(World world, BlockPos pos, EntityPlayer player) {
    return this.getStandingStones(world, pos, 3, null) >= 3;
  }

  @Override
  public void doEffect(World world, BlockPos pos) {
    this.spawnEntity(world, pos, EntityRitualNaturalAura.class);
  }
}
