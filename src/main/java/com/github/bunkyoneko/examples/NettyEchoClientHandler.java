package com.github.bunkyoneko.examples;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Netty echo client handler
 *
 * @author bunkyoneko
 * @date 2019-05-06
 */
@Sharable
public class NettyEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
   @Override
   public void channelActive(ChannelHandlerContext ctx) {
       ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
   }

   @Override
   public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
      System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
