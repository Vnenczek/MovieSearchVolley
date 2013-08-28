package com.example.MovieSearchVolley;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 8/22/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    private ImagesConfig images;
    private String[] change_keys;

    public String[] getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(String[] change_keys) {
        this.change_keys = change_keys;

    }

    public ImagesConfig getImages() {
        return images;
    }

    public void setImages(ImagesConfig images) {
        this.images = images;
    }

    class ImagesConfig {
        private String base_url;
        private String secure_base_url;
        private String[] poster_sizes;
        private String[] backdrop_sizes;
        private String[] profile_sizes;
        private String[] logo_sizes;

        public String[] getBackdrop_sizes() {
            return backdrop_sizes;
        }

        public void setBackdrop_sizes(String[] backdrop_sizes) {
            this.backdrop_sizes = backdrop_sizes;
        }

        public String getBase_url() {
            return base_url;
        }

        public void setBase_url(String base_url) {
            this.base_url = base_url;
        }

        public String[] getLogo_sizes() {
            return logo_sizes;
        }

        public void setLogo_sizes(String[] logo_sizes) {
            this.logo_sizes = logo_sizes;
        }

        public String[] getPoster_sizes() {
            return poster_sizes;
        }

        public void setPoster_sizes(String[] poster_sizes) {
            this.poster_sizes = poster_sizes;
        }

        public String[] getProfile_sizes() {
            return profile_sizes;
        }

        public void setProfile_sizes(String[] profile_sizes) {
            this.profile_sizes = profile_sizes;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }

        public void setSecure_base_url(String secure_base_url) {
            this.secure_base_url = secure_base_url;
        }

    }
}