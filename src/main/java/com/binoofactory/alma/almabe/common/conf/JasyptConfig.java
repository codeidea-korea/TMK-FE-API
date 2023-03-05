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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

// @Profile({"dev", "prod"})
@Configuration
@EnableEncryptableProperties
public class JasyptConfig {
    @Value("${jasypt.encryptor.algorithm}")
    private String algorithm;
    @Value("${jasypt.encryptor.pool-size}")
    private int poolSize;
    @Value("${jasypt.encryptor.string-output-type}")
    private String stringOutputType;
    @Value("${jasypt.encryptor.key-obtention-iterations}")
    private int keyObtentionIterations;

    @Bean
    public StringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(poolSize);
        encryptor.setAlgorithm(algorithm);
        encryptor.setPassword(getJasyptEncryptorPassword());
        encryptor.setStringOutputType(stringOutputType);
        encryptor.setKeyObtentionIterations(keyObtentionIterations);
        return encryptor;
    }

    private String getJasyptEncryptorPassword() {
        
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