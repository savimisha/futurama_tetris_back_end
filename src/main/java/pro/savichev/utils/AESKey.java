package pro.savichev.utils;


public class AESKey {

    public static byte[] generate(byte[] array) {
        int AES_KEY_BYTES = 32;
        byte[] key = new byte[AES_KEY_BYTES];
        byte[] hash = SHA.sha3_256(array);
        System.arraycopy(hash, 0, key, 0, AES_KEY_BYTES);
        return key;
    }

}
