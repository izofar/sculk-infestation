package com.izofar.sculkinfestation.client.renderer;

import com.izofar.sculkinfestation.SculkInfestation;
import com.izofar.sculkinfestation.client.model.SculkedSpiderModel;
import com.izofar.sculkinfestation.client.renderer.layer.SculkEmissiveLayer;
import com.izofar.sculkinfestation.entity.SculkedSpider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SculkedSpiderRenderer extends MobRenderer<SculkedSpider, SculkedSpiderModel> implements ISculkEmissive {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_spider/sculked_spider.png");
    private static final ResourceLocation GLOW_TEXTURE = new ResourceLocation(SculkInfestation.MODID, "textures/entity/sculked_spider/sculked_spider_glow.png");

    public SculkedSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new SculkedSpiderModel(SculkedSpiderModel.createBodyLayer().bakeRoot()), 0.8F);
        this.addLayer(new SculkEmissiveLayer(this, GLOW_TEXTURE, this::getHeartBeatingAlpha, this.getDrawSelector()));
    }

    @Override
    public SculkEmissiveLayer.DrawSelector getDrawSelector() {
        return (SculkEmissiveLayer.DrawSelector<SculkedSpider, SculkedSpiderModel>) SculkedSpiderModel::getParts;
    }

    @Override
    public ResourceLocation getTextureLocation(SculkedSpider pEntity) {
        return TEXTURE;
    }
}
