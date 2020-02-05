package org.bouncycastle.tls;

public class SignatureScheme
{
    /*
     * RFC 8446
     */

    public static final int rsa_pkcs1_sha1 = 0x0201;
    public static final int ecdsa_sha1 = 0x0203;

    public static final int rsa_pkcs1_sha256 = 0x0401;
    public static final int rsa_pkcs1_sha384 = 0x0501;
    public static final int rsa_pkcs1_sha512 = 0x0601;

    public static final int ecdsa_secp256r1_sha256 = 0x0403;
    public static final int ecdsa_secp384r1_sha384 = 0x0503;
    public static final int ecdsa_secp521r1_sha512 = 0x0603;

    public static final int rsa_pss_rsae_sha256 = 0x0804;
    public static final int rsa_pss_rsae_sha384 = 0x0805;
    public static final int rsa_pss_rsae_sha512 = 0x0806;

    public static final int ed25519 = 0x0807;
    public static final int ed448 = 0x0808;

    public static final int rsa_pss_pss_sha256 = 0x0809;
    public static final int rsa_pss_pss_sha384 = 0x080A;
    public static final int rsa_pss_pss_sha512 = 0x080B;

    /*
     * RFC 8446 reserved for private use (0xFE00..0xFFFF)
     */

    public static String getName(int signatureScheme)
    {
        switch (signatureScheme)
        {
        case rsa_pkcs1_sha1:
            return "rsa_pkcs1_sha1";
        case ecdsa_sha1:
            return "ecdsa_sha1";
        case rsa_pkcs1_sha256:
            return "rsa_pkcs1_sha256";
        case rsa_pkcs1_sha384:
            return "rsa_pkcs1_sha384";
        case rsa_pkcs1_sha512:
            return "rsa_pkcs1_sha512";
        case ecdsa_secp256r1_sha256:
            return "ecdsa_secp256r1_sha256";
        case ecdsa_secp384r1_sha384:
            return "ecdsa_secp384r1_sha384";
        case ecdsa_secp521r1_sha512:
            return "ecdsa_secp521r1_sha512";
        case rsa_pss_rsae_sha256:
            return "rsa_pss_rsae_sha256";
        case rsa_pss_rsae_sha384:
            return "rsa_pss_rsae_sha384";
        case rsa_pss_rsae_sha512:
            return "rsa_pss_rsae_sha512";
        case ed25519:
            return "ed25519";
        case ed448:
            return "ed448";
        case rsa_pss_pss_sha256:
            return "rsa_pss_pss_sha256";
        case rsa_pss_pss_sha384:
            return "rsa_pss_pss_sha384";
        case rsa_pss_pss_sha512:
            return "rsa_pss_pss_sha512";
        default:
            return "UNKNOWN";
        }
    }

    public static String getText(int signatureScheme)
    {
        return getName(signatureScheme) + "(" + signatureScheme + ")";
    }

    public static boolean isPrivate(int signatureScheme)
    {
        return (signatureScheme >>> 9) == 0xFE; 
    }
}
