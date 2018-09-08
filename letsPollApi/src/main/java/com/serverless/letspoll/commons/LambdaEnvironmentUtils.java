package com.serverless.letspoll.commons;

//import com.amazonaws.services.kms.AWSKMS;
//import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.util.Base64;

/**
 * Created by Webonise on 08/09/18.
 */
public class LambdaEnvironmentUtils  {
    public static String getValue(String environmentKeyName) {
     /*   System.out.println("Decrypting key");
        byte[] encryptedKey = Base64.decode(System.getenv(environmentKeyName));
        AWSKMS client = AWSKMSClientBuilder.defaultClient();
        DecryptRequest request = new DecryptRequest()
            .withCiphertextBlob(ByteBuffer.wrap(encryptedKey));
        ByteBuffer plainTextKey = client.decrypt(request).getPlaintext();
        return new String(plainTextKey.array(), Charset.forName("UTF-8"));*/
        return System.getenv(environmentKeyName);
    }
}
