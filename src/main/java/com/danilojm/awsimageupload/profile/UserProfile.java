package com.danilojm.awsimageupload.profile;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProfile {

    private UUID userProfileId;
    private String userName;
    private String userProfileImageLink;
}
