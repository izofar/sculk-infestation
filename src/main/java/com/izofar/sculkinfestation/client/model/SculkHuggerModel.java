package com.izofar.sculkinfestation.client.model;

import com.google.common.collect.ImmutableList;
import com.izofar.sculkinfestation.entity.SculkHugger;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.model.geom.ModelPart;

import java.util.List;

public class SculkHuggerModel extends SpiderModel<SculkHugger> implements IPartedModel {

    private final ModelPart head, body;

    public SculkHuggerModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body1");
    }

    @Override
    public ModelPart getPart() {
        return this.root();
    }

    public List<ModelPart> getParts() {
        return ImmutableList.of(this.head, this.body);
    }
}
