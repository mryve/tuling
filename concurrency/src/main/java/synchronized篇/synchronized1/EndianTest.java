package synchronized篇.synchronized1;

import java.lang.reflect.Field;
import java.nio.ByteOrder;


import sun.misc.Unsafe;

/**
 * @author Fox
 * 判断当前环境字节是大端还是小端字节序存储
 * Little-Endian 高位字节在前，低位字节在后。
 * Big-Endian 低位字节在前，高位字节在后。
 * 在x86的计算机中，一般采用的是小端字节序
 */
public class EndianTest {

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            ByteOrder byteOrder;
            switch (b) {
                case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
                case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
                default:
                    byteOrder = null;
            }
            System.out.println(byteOrder);
        } finally {
            unsafe.freeMemory(a);
        }

    }
}

class UnsafeFactory {

    /**
     * 获取 Unsafe 对象
     * @return
     */
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字段的内存偏移量
     * @param unsafe
     * @param clazz
     * @param fieldName
     * @return
     */
    public static long getFieldOffset(Unsafe unsafe, Class clazz, String fieldName) {
        try {
            return unsafe.objectFieldOffset(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
}
