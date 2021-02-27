package net.soup.pvp.mod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InGameHud.class)
public abstract class ShowStuffHUDMixin {
    @Shadow protected abstract void drawTextBackground(MatrixStack matrices, TextRenderer textRenderer, int yOffset, int width, int color);

    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {

        String fps = String.format("%d FPS", ((MinecraftClientMixin) MinecraftClient.getInstance()).getCurrentFps());


        float textPosX = 10, textPosY = 10;

        client.textRenderer.drawWithShadow(matrixStack, fps, textPosX, textPosY, 0xffffff);
    }
}
