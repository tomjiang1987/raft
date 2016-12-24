package com.tj;

import java.io.File;

public class Constants {
    /**
     * the magic code of the remoting header
     */
    public static final short MAGIC_CODE= (short)0xCFEE;
    /**
     * the max length of the packet
     */
    public static final int MAX_PACKET_LENGTH=1*1024*1024;
    /**
     * the total count byte that the field of header length occupy.
     */
    public static final int HEADER_LENGTH_BYTE_COUNT=2;

    /**
     * the default timeout of queue
     */
    public static final int DEFAULT_QUEUE_TIMEOUT = 3*1000;

    /**
     * the default timeout of read-write lock
     */
    public static final int DEFAULT_READ_WRITE_LOCK_TIMEOUT = 3*1000;

    /**
     * the default timeout of socket reading timeout.
     */
    public static final int DEFAULT_SOCKET_READING_TIMEOUT = 5*1000;

    /**
     * the default max queue item
     */
    public static final int DEFAULT_MAX_QUEUE_ITEM = 1024;

    /**
     * default sleep time of Thread
     */
    public static final int DEFAULT_THREAD_SLEEP_TIME = 1000;
    
}