package com.danilojm.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.opencsv.CSVReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileReader;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                loadKeys("AWSAccessKeyId"),
                loadKeys("AWSSecretKey")
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public String loadKeys(String key){
        try {

            File file = new File("/home/danilo/Projetos/SpringBoot/AWS/rootkey.csv");

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    String[] values = cell.split("=");
                    if(values[0].equals(key)){
                        return values[1];
                    }
                }
            }
        }
        catch (Exception e) {
            throw new IllegalStateException("Erro ao tentar ler a chave " + key, e);
        }
        return "Não deu!";
    }
}
