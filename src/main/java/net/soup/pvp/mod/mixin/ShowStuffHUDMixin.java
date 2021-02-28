package net.soup.pvp.mod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.soup.pvp.mod.util.ItemUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InGameHud.class)
public abstract class ShowStuffHUDMixin {

    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {

        int totemAmount = ItemUtil.getTotal(client, new ItemStack(Items.TOTEM_OF_UNDYING));
        String totems = String.format("%d totems", totemAmount);
        String totems1 = String.format("%d totem", totemAmount);

        float textPosX = 10, textPosY = 200;

        if (totemAmount <= 0) {
            client.textRenderer.drawWithShadow(matrixStack, totems, textPosX, textPosY, 0xFF0000);
        } else if (totemAmount == 1) {
            client.textRenderer.drawWithShadow(matrixStack, totems1, textPosX, textPosY, 0xFF0000);
        } else if (totemAmount == 2) {
            client.textRenderer.drawWithShadow(matrixStack, totems, textPosX, textPosY, 0xFF0000);
        } else if (totemAmount == 3 || totemAmount == 4) {
            client.textRenderer.drawWithShadow(matrixStack, totems, textPosX, textPosY, 0xFF6347);
        } else {
            client.textRenderer.drawWithShadow(matrixStack, totems, textPosX, textPosY, 0x00D100);
        }
    }
}
