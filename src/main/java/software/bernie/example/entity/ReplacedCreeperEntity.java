package software.bernie.example.entity;

import software.bernie.geckolib.core.IAnimatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.controller.AnimationController;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;

public class ReplacedCreeperEntity implements IAnimatable
{
	AnimationFactory factory = new AnimationFactory(this);

	@Override
	public void registerControllers(AnimationData data)
	{
		data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
	}

	private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event)
	{
		if (!(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("creeper_walk", true));
		}
		else
		{
			event.getController().setAnimation(new AnimationBuilder().addAnimation("creeper_idle", true));
		}
		return PlayState.CONTINUE;
	}

	@Override
	public AnimationFactory getFactory()
	{
		return factory;
	}
}
