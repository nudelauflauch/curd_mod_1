package nudelauflauch.curd_mod.common.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import nudelauflauch.curd_mod.core.init.EntityInit;
import nudelauflauch.curd_mod.core.init.ItemInit;

public class PitEntity extends ProjectileItemEntity {

	public PitEntity(EntityType<PitEntity> type, World world) {
		super(type, world);
	}
	
	public PitEntity(LivingEntity entity, World world) {
		super(EntityInit.PIT.get(), entity, world);
	}
	
	public PitEntity(double x, double y, double z, World world) {
		super(EntityInit.PIT.get(), x, y, z, world);
	}

	/*
	 * This gets the default item that the rock will be thrown by. An example would
	 * be the snowball throwing the snowball. If you choose to use a block for this,
	 * then you will need to add .asItem() onto the end to ensure you are gettin the
	 * block item
	 */
	@Override
	protected Item getDefaultItem() {
		return ItemInit.PIT.get().asItem();
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult result) {
	}

	@Override
	protected void onHit(RayTraceResult result) {
		super.onHit(result);
		if (!this.level.isClientSide) {
			Entity entity = ((EntityRayTraceResult)result).getEntity();
			entity.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0.0F);
		}
	}

}