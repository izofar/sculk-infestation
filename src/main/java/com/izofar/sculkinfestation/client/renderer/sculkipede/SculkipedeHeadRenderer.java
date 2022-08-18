package com.izofar.sculkinfestation.client.renderer.sculkipede;

import com.izofar.sculkinfestation.client.renderer.centipede.CentipedeHeadRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkipedeHeadRenderer extends CentipedeHeadRenderer {
    public SculkipedeHeadRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn);
    }
}
