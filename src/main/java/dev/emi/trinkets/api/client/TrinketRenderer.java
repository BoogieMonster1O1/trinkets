package dev.emi.trinkets.api.client;

import dev.emi.trinkets.api.Trinket;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;

public interface TrinketRenderer {
	public static final float MAGIC_ROTATION = 180f / (float) Math.PI;

	void render(ItemStack stack, Trinket.SlotReference slot, MatrixStack matrices, VertexConsumerProvider vertexConsumer, int light,
			PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float limbAngle, float limbDistance,
			float tickDelta, float animationProgress, float headYaw, float headPitch);

	/**
	 * Translates the rendering context to the center of the player's face
	 */
	static void translateToFace(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw,
			float headPitch) {

		if (player.isInSwimmingPose() || player.isFallFlying()) {
			matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(model.head.roll));
			matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
			matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-45.0F));
		} else {

			if (player.isInSneakingPose() && !model.riding) {
				matrices.translate(0.0F, 0.25F, 0.0F);
			}
			matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
			matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(headPitch));
		}
		matrices.translate(0.0F, -0.25F, -0.3F);
	}

	/**
	 * Translates the rendering context to the center of the player's chest/torso segment
	 */
	static void translateToChest(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {

		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrices.translate(0.0F, 0.2F, 0.0F);
			matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.torso.pitch * MAGIC_ROTATION));
		}
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.torso.yaw * MAGIC_ROTATION));
		matrices.translate(0.0F, 0.4F, -0.16F);
	}

	/**
	 * Translates the rendering context to the center of the bottom of the player's right arm
	 */
	static void translateToRightArm(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {

		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrices.translate(0.0F, 0.2F, 0.0F);
		}
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.torso.yaw * MAGIC_ROTATION));
		matrices.translate(-0.3125F, 0.15625F, 0.0F);
		matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(model.rightArm.roll * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.rightArm.yaw * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.rightArm.pitch * MAGIC_ROTATION));
		matrices.translate(-0.0625F, 0.625F, 0.0F);
	}

	/**
	 * Translates the rendering context to the center of the bottom of the player's left arm
	 */
	static void translateToLeftArm(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {

		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrices.translate(0.0F, 0.2F, 0.0F);
		}
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.torso.yaw * MAGIC_ROTATION));
		matrices.translate(0.3125F, 0.15625F, 0.0F);
		matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(model.leftArm.roll * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.leftArm.yaw * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.leftArm.pitch * MAGIC_ROTATION));
		matrices.translate(0.0625F, 0.625F, 0.0F);
	}

	/**
	 * Translates the rendering context to the center of the bottom of the player's right leg
	 */
	static void translateToRightLeg(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {

		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrices.translate(0.0F, 0.0F, 0.25F);
		}
		matrices.translate(-0.125F, 0.75F, 0.0F);
		matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(model.rightLeg.roll * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.rightLeg.yaw * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.rightLeg.pitch * MAGIC_ROTATION));
		matrices.translate(0.0F, 0.75F, 0.0F);
	}

	/**
	 * Translates the rendering context to the center of the bottom of the player's left leg
	 */
	static void translateToLeftLeg(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {

		if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
			matrices.translate(0.0F, 0.0F, 0.25F);
		}
		matrices.translate(0.125F, 0.75F, 0.0F);
		matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(model.leftLeg.roll * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.leftLeg.yaw * MAGIC_ROTATION));
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.leftLeg.pitch * MAGIC_ROTATION));
		matrices.translate(0.0F, 0.75F, 0.0F);
	}
}