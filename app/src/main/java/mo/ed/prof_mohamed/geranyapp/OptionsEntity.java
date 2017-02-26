package mo.ed.prof_mohamed.geranyapp;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed on 2/7/2017.
 */
public class OptionsEntity implements Serializable{
    String ID, UserName, Description,Post_Content, Post_images, Profile_Images, Volunteer, Donate, both, email, mobile, distance, CollaborationType,areaname,areaaddress;

    public OptionsEntity() {
    }

    public OptionsEntity(String id,String userName, String description, String post_Content, String post_images, String profile_Images, String volunteer, String donate, String both, String email, String mobile, String distance, String collaborationType) {
        ID=id;
        UserName = userName;
        Description = description;
        Post_Content = post_Content;
        Post_images = post_images;
        Profile_Images = profile_Images;
        Volunteer = volunteer;
        Donate = donate;
        this.both = both;
        this.email = email;
        this.mobile = mobile;
        this.distance = distance;
        CollaborationType = collaborationType;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreaaddress() {
        return areaaddress;
    }

    public void setAreaaddress(String areaaddress) {
        this.areaaddress = areaaddress;
    }

    public OptionsEntity(String id_str, String areaname_str, String areaaddress_str) {
        ID=id_str;
        areaname=areaname_str;
        areaaddress=areaaddress_str;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPost_Content() {
        return Post_Content;
    }

    public void setPost_Content(String post_Content) {
        Post_Content = post_Content;
    }

    public String getPost_images() {
        return Post_images;
    }

    public void setPost_images(String post_images) {
        Post_images = post_images;
    }

    public String getProfile_Images() {
        return Profile_Images;
    }

    public void setProfile_Images(String profile_Images) {
        Profile_Images = profile_Images;
    }

    public String getVolunteer() {
        return Volunteer;
    }

    public void setVolunteer(String volunteer) {
        Volunteer = volunteer;
    }

    public String getDonate() {
        return Donate;
    }

    public void setDonate(String donate) {
        Donate = donate;
    }

    public String getBoth() {
        return both;
    }

    public void setBoth(String both) {
        this.both = both;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCollaborationType() {
        return CollaborationType;
    }

    public void setCollaborationType(String collaborationType) {
        CollaborationType = collaborationType;
    }
}
