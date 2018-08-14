package pro.savichev.utils

import org.spongycastle.jcajce.provider.digest.SHA3

object SHA {

    @JvmStatic
    fun sha3_256(data: ByteArray): ByteArray{
        val digest = SHA3.Digest256()
        return digest.digest(data)
    }

}