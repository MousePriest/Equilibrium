package com.mouse.mousesmagics.effect;

import com.mouse.mousesmagics.registries.MMSoundRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.datagen.DamageTypeTagGenerator;
import io.redspace.ironsspellbooks.effect.CustomDescriptionMobEffect;
import io.redspace.ironsspellbooks.effect.ISyncedMobEffect;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;


public class BlossomBulwarkEffect extends CustomDescriptionMobEffect {

    public BlossomBulwarkEffect(MobEffectCategory pCategory, int pColor) {super(pCategory, pColor);}

    public static boolean doEffect(LivingEntity livingEntity, DamageSource damageSource) {
        if (livingEntity.level.isClientSide || damageSource.is(DamageTypeTagGenerator.BYPASS_EVASION) || damageSource.is(DamageTypeTags.IS_FALL) || damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }
        var random = livingEntity.getRandom();
        var level = livingEntity.level;

        particleCloud(livingEntity);

        level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), MMSoundRegistries.DEW_CAST.get(), SoundSource.AMBIENT, 1.0F, .9F + random.nextFloat() * .2f);
        return true;
    }

    private static void particleCloud(LivingEntity entity) {
        Vec3 pos = entity.position().add(0, entity.getBbHeight() / 2, 0);
        MagicManager.spawnParticles(entity.level(), MMParticleHelper.FADING_FLOWER, pos.x, pos.y, pos.z, 70, entity.getBbWidth() / 4, entity.getBbHeight() / 5, entity.getBbWidth() / 4, .035, false);
    }

    @Override
    public Component getDescriptionLine(MobEffectInstance instance) {
        return Component.translatable("tooltip.irons_spellbooks.hastened_description").withStyle(ChatFormatting.LIGHT_PURPLE);
    }
}
