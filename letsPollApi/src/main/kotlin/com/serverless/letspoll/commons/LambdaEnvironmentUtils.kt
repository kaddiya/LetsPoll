package com.serverless.letspoll.commons


object LambdaEnvironmentUtils {
    fun getValue(environmentKeyName: String): String {
        /*   System.out.println("Decrypting key");
        byte[] encryptedKey = Base64.decode(System.getenv(environmentKeyName));
        AWSKMS client = AWSKMSClientBuilder.defaultClient();
        DecryptRequest request = new DecryptRequest()
            .withCiphertextBlob(ByteBuffer.wrap(encryptedKey));
        ByteBuffer plainTextKey = client.decrypt(request).getPlaintext();
        return new String(plainTextKey.array(), Charset.forName("UTF-8"));*/
        return System.getenv(environmentKeyName)
    }
}
