package com.azxx.demon.exceptions;

import com.dexcoder.commons.exceptions.DexcoderException;

/**
 * author : benjamin
 * createTime : 2017.06.06
 * description : 定时任务自定义异常
 * version : 1.0
 */
public class ScheduleException extends DexcoderException {

    /** serialVersionUID */
    private static final long serialVersionUID = -1921648378954132894L;

    /**
     * Instantiates a new ScheduleException.
     *
     * @param e the e
     */
    public ScheduleException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message the message
     */
    public ScheduleException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code the code
     * @param message the message
     */
    public ScheduleException(String code, String message) {
        super(code, message);
    }
}
