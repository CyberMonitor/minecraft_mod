package com.ithome.mymod.events;

import com.ithome.mymod.entities.EntityScalePig;
import com.ithome.mymod.entities.RenderScalePig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PigDoll {

    private float scale = 1.0f;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicBoolean flag = new AtomicBoolean(false);
    private List<String> firstRenderPig = new ArrayList<String>();

    @SubscribeEvent
    public void preRender(RenderLivingEvent.Pre event) {
        if (event.entity.getCustomNameTag().startsWith("myPig")) {

            String tag = event.entity.getCustomNameTag();
            float parserScale = Float.parseFloat(tag.split("-")[1]);

            if (flag.compareAndSet(false, true)) {
                Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntityScalePig.class,
                        new RenderScalePig(Minecraft.getMinecraft().getRenderManager(), new ModelPig(), 0.5f * parserScale));
            }

            if(event.renderer instanceof RenderScalePig) {
                RenderScalePig render = (RenderScalePig) event.renderer;
                render.setScale(parserScale);
            }

            if(!firstRenderPig.contains(tag)) {
                EntityScalePig pig = (EntityScalePig) event.entity;
                pig.scale(parserScale);
                System.out.println("box: " + pig.getEntityBoundingBox().toString());
                System.out.println("myPig: " + pig.getPosition().toString());
                System.out.println("render: " + event.renderer.getClass());
                firstRenderPig.add(tag);
            }
        }
    }

    @SubscribeEvent
    public void pigDoll(EntityJoinWorldEvent event) {
        if (event.entity.worldObj.isRemote) return;
        if (event.world.playerEntities.isEmpty()) return;

        EntityPlayer player = (EntityPlayer) event.entity.worldObj.playerEntities.get(0);
        float distance = event.entity.getDistanceToEntity(player);

        if (distance < 7.0f) {
            if (EntityPig.class.isAssignableFrom(event.entity.getClass())) {

                if(!event.entity.getCustomNameTag().startsWith("myPig")) {
                    event.entity.setDead();
                }

                if(count.compareAndSet(5, 0)) {
                    scale = 1.0f;
                    firstRenderPig.clear();
                    return;
                }

                scale = scale * 0.8f;
                EntityScalePig pig = new EntityScalePig(event.entity.worldObj);
                pig.setPosition(event.entity.posX - 1.2f, event.entity.posY, event.entity.posZ);
                pig.setCustomNameTag(String.format("myPig-%f", scale));

                // 將豬的AI行為去除
                EntityAIBase ai = new EntityAIBase() {
                    @Override
                    public boolean shouldExecute() {
                        return true;
                    }
                };
                ai.setMutexBits(0xFFFFFF);
                pig.tasks.addTask(0, ai);

                count.getAndIncrement();
                System.out.println("scale: " + scale);
                event.entity.worldObj.spawnEntityInWorld(pig);
            }
        }
    }
}
