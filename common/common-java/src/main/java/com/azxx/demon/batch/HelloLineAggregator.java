package com.azxx.demon.batch;

import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * Created by Smile on 2018/9/25.
 */
public class HelloLineAggregator implements LineAggregator<DeviceCommand> {

    @Override
    public String aggregate(DeviceCommand deviceCommand) {

        StringBuffer sb = new StringBuffer();
        sb.append(deviceCommand.getId());
        sb.append(",");
        sb.append(deviceCommand.getStatus());
        return sb.toString();

    }
}
