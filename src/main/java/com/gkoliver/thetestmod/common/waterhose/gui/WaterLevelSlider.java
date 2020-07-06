package com.gkoliver.thetestmod.common.waterhose.gui;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

import javax.annotation.Nullable;

public class WaterLevelSlider extends Slider {
    int amt;
    public WaterLevelSlider(int xPos, int yPos, int width, int height, ITextComponent prefix, ITextComponent suf, double currentVal, boolean showDec, boolean drawStr, IPressable handler) {
        super(xPos, yPos, width, height, prefix, suf, 0, 100, currentVal, showDec, drawStr, handler);
    }

    public WaterLevelSlider(int xPos, int yPos, int width, int height, ITextComponent prefix, ITextComponent suf, double currentVal, boolean showDec, boolean drawStr, IPressable handler, @Nullable ISlider par) {
        super(xPos, yPos, width, height, prefix, suf, 0, 100, currentVal, showDec, drawStr, handler, par);
    }

    public WaterLevelSlider(int xPos, int yPos, ITextComponent displayStr, double currentVal, IPressable handler, ISlider par) {
        super(xPos, yPos, displayStr, 0, 100, currentVal, handler, par);
    }

    public void func_231000_a__(double mouseX, double mouseY) {
        super.func_231000_a__(mouseX, mouseY);
        onRelease(mouseX, mouseY);
    }

    public void onRelease(double mouseX, double mouseY) {
        amt = getValueInt();
    }


}
