package com.iwax.film.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

public class Movie implements Parcelable {

    private static final String TAG = "Movie";

    private int id;
    private String title;
    private String description;
    private int image;
    private String poster;
    private String release_date;
    private BigDecimal popularity;
    private int num_ratings;
    private BigDecimal rating;
    private boolean adult;


    public Movie(String title, String description, int image, BigDecimal popularity, int num_ratings, BigDecimal rating, int id) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.popularity = popularity;
        this.num_ratings = num_ratings;
        this.rating = rating;
        this.id = id;
    }

    public Movie() {

    }

    protected Movie(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readInt();
        num_ratings = in.readInt();
        id = in.readInt();
        poster = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(image);
        dest.writeString(release_date);
        dest.writeInt(num_ratings);
        dest.writeInt(id);
        dest.writeString(poster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public String getNumberRatingsString(){
        return ("(" + getNum_ratings() + ")");
    }

    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_ratings() {
        return num_ratings;
    }

    public void setNum_ratings(int num_ratings) {
        this.num_ratings = num_ratings;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public void setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
