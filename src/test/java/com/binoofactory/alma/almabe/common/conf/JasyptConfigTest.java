package com.binoofactory.alma.almabe.common.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.core.io.ClassPathResource;

public class JasyptConfigTest extends BDDMockito {

    @Test
    @DisplayName("프로퍼티 암호화가 가능하다")
    public void shouldBeEncryptProperties() throws Exception {
        String url = "test_db_url";
        String username = "test_db_username";
        String password = "test_db_password";

        String encUrl = Assertions.assertDoesNotThrow(() -> encryptProperties(url));
        String encUsername = Assertions.assertDoesNotThrow(() -> encryptProperties(username));
        String encPassword = Assertions.assertDoesNotThrow(() -> encryptProperties(password));

        Assertions.assertNotEquals(url, encUrl);
        Assertions.assertNotEquals(username, encUsername);
        Assertions.assertNotEquals(password, encPassword);
    }

    public static String encryptProperties(String value) {
        return jasyptStringEncryptor().encrypt(value);
    }

    public static StringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(2);
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(getJasyptEncryptorPassword());
        encryptor.setStringOutputType("base64");
        encryptor.setKeyObtentionIterations(1620000);
        return encryptor;
    }

    private static String getJasyptEncryptorPassword() {
        
        File keyFile = new File("jasypt-encryptor-password.bfkey");
        if(!keyFile.exists()) {
            InputStream inputStream = null;
            try {
                inputStream = new ClassPathResource("jasypt-encryptor-password.bfkey").getInputStream();
                File file = File.createTempFile("jasypt-encryptor-password", ".bfkey");
                FileUtils.copyInputStreamToFile(inputStream, file);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Not make temp Jasypt password file.");
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
        try {
            // Reader 방식을 바꿈 (import java.io.File → import java.nio.file.Files)
            /*
            keyFile = new File("jasypt-encryptor-password.bfkey");
            FileReader reader = new FileReader(keyFile);
            BufferedReader bufReader = new BufferedReader(reader);
            String result = bufReader.readLine();
            bufReader.close();
            reader.close();

            return result;
            */
            ClassPathResource resource = new ClassPathResource("jasypt-encryptor-password.bfkey");
            return Files.readAllLines(Paths.get(resource.getURI())).stream().collect(Collectors.joining(""));
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            throw new RuntimeException("Not found Jasypt password file.");
        }
    }
}
