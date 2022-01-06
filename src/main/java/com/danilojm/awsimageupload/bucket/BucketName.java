package com.danilojm.awsimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("jm-springboot-s3");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
