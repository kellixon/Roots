package epicsquid.roots.ritual;

import epicsquid.mysticalworld.init.ModItems;
import epicsquid.roots.entity.ritual.EntityRitualWindwall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualWindwall extends RitualBase {
  public RitualWindwall(String name, int duration) {
    super(name, duration);
    addIngredients(
            new ItemStack(ModItems.pereskia),
            new ItemStack(ModItems.pereskia_bulb),
            new ItemStack(ModItems.bark_spruce),
            new ItemStack(ModItems.bark_birch),
            new ItemStack(Items.FEATHER)
    );
  }

  @Override
  public boolean canFire(World world, BlockPos pos, EntityPlayer player) {
    BlockPos topPos = world.getTopSolidOrLiquidBlock(pos);
    return topPos.getY() == pos.getY() || topPos.getY() == pos.getY() + 1;
  }

  @Override
  public void doEffect(World world, BlockPos pos) {
    this.spawnEntity(world, pos, EntityRitualWindwall.class);
  }
}