package com.azxx.demon.batch;

import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Smile on 2018/9/25.
 */
public class HelloItemProcessor implements ItemProcessor<DeviceCommand, DeviceCommand> {

    @Override
    public DeviceCommand process(DeviceCommand deviceCommand) throws Exception {

        // 模拟下发命令给设备
        System.out.println("send command to device, id=" + deviceCommand.getId());

        // 更新命令状态
        deviceCommand.setStatus("SENT");

        // 返回命令对象
        return deviceCommand;

    }
}
