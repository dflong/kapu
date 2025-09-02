package com.dflong.kapu.fram.protocol.dubbo;

import com.dflong.kapu.fram.Invocation;
import com.dflong.kapu.fram.protocol.Protocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.val;

import java.util.Date;

public class NettyProtocol implements Protocol {
    @Override
    public void start(String hostname, int port) {
        // 引导服务端的启动
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 用于监听端口，接收新连接的线程组
        val boss = new NioEventLoopGroup();
        // 表示处理每一个连接的数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss, worker)
                // 指定IO模型为NIO
                .channel(NioServerSocketChannel.class)
                // 定义后面每一个连接的数据读写
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println("服务启动中......");
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(port);
    }

    @Override
    public String send(String hostname, int port, Invocation invocation) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) {
                channel.pipeline().addLast(new StringEncoder());
            }
        });
        Channel channel = bootstrap.connect(hostname, port).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
        }
    }
}
