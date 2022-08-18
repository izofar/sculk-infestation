package com.izofar.sculkinfestation.client.renderer.sculkipede;

import com.izofar.sculkinfestation.client.renderer.centipede.CentipedeBodyRenderer;
import com.izofar.sculkinfestation.entity.sculkipede.SculkipedeTail;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkipedeTailRenderer extends CentipedeBodyRenderer<SculkipedeTail> {
    public SculkipedeTailRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, 2);
    }
}
