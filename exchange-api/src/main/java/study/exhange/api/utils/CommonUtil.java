package study.exhange.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;

/**
 * @author chao
 * @since 2018-10-09
 */
public class CommonUtil {

    private static final DateTimeFormatter DF_T_SSS_Z = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static LocalDateTime convertMilliSeconds(long milliSeconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSeconds), ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
    }

    public static LocalDateTime convertSeconds(long seconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
    }

    /**
     * UTC时间格式(yyyy-MM-dd'T'HH:mm:ss.SSS'Z')字符串转时间戳
     */
    public static long timeToEpochMilli(String time) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, DF_T_SSS_Z);
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * 时间格式(yyyy-MM-dd'T'HH:mm:ss.SSS'Z')字符串转时间戳
     */
    public static long timeToEpochMilli(String time, ZoneOffset zoneOffset) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, DF_T_SSS_Z);
        return localDateTime.toInstant(zoneOffset).toEpochMilli();
    }

    /**
     * 今日总毫秒数
     */
    public static long todayTotalMillisecond() {
        return todayTotalMillisecond(LocalDateTime.now());
    }

    /**
     * 今日总毫秒数
     */
    public static long todayTotalMillisecond(LocalDateTime time) {
        return ((time.getHour() * 60 + time.getMinute()) * 60 + time.getSecond()) * 1000 + time.getNano() / (1000 * 1000);
    }

    /**
     * gzip解压客户端发来的数据
     */
    public static byte[] gzipDecompress(byte[] depressData) throws Exception {
        try (ByteArrayInputStream is = new ByteArrayInputStream(depressData);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                GZIPInputStream gis = new GZIPInputStream(is)) {

            byte data[] = new byte[1024];
            int offset;
            while (-1 != (offset = gis.read(data, 0, 1024))) {
                os.write(data, 0, offset);
            }
            return os.toByteArray();
        }
    }

    /**
     * deflate解压客户端发来的程序
     */
    public static byte[] deflateDecompress(byte[] bytes) throws Exception{
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                Deflate64CompressorInputStream zin = new Deflate64CompressorInputStream(in)) {

            byte[] buffer = new byte[1024];
            int offset;
            while (-1 != (offset = zin.read(buffer, 0 ,1024))) {
                out.write(buffer, 0, offset);
            }
            return out.toByteArray();
        }
    }

    /**
     * 解压客户端发来的程序
     */
    public static byte[] base64Decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * 解压客户端发来的程序
     */
    public static byte[] base64Decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
