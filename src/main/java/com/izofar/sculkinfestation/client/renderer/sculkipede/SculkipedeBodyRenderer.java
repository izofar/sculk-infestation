package com.izofar.sculkinfestation.client.renderer.sculkipede;

import com.izofar.sculkinfestation.client.renderer.centipede.CentipedeBodyRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkipedeBodyRenderer extends CentipedeBodyRenderer {
    public SculkipedeBodyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, 1);
    }
}
