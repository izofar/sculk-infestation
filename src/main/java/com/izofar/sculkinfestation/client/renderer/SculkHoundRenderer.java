package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkHoundModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkHound;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkHoundRenderer extends MobRenderer<SculkHound, SculkHoundModel> implements ISculkEmissive {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_hound/sculk_hound.png");
    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculk_hound/sculk_hound_glow.png");

    public SculkHoundRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkHoundModel(SculkHoundModel.createBodyLayer().bakeRoot()), 0.5F);
        this.addLayer(new SculkEmissiveLayer<>(this, GLOW_TEXTURE, this::getHeartBeatingAlpha,  this.getDrawSelector()));
    }

    @Override
    protected float getBob(SculkHound entity, float partialTicks) {
        return entity.getTailAngle();
    }

    @Override
    public ResourceLocation getTextureLocation(SculkHound entity) {
        return TEXTURE;
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkHound, SculkHoundModel>) SculkHoundModel::getParts;
    }
}
