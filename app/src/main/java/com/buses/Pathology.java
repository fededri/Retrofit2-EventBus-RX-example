package com.buses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;



/**
 * Created by Federico Torres on 22/3/2016.
 */
public class Pathology implements Parcelable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("name")
    public String name;


    @SerializedName("id")
    String id;

    @SerializedName("pathology_id")
    String pathology_id;

    @SerializedName("pathology_start_date")
    String pathology_start_date;

    @SerializedName("pathology_date")
    String pathology_date;



    public String getPathology_id() {
        return pathology_id;
    }

    public void setPathology_id(String pathology_id) {
        this.pathology_id = pathology_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathology_start_date() {
        return pathology_start_date;
    }

    public void setPathology_start_date(String pathology_start_date) {
        this.pathology_start_date = pathology_start_date;
    }


    public String getPathology_date() {
        return pathology_date;
    }

    public void setPathology_date(String pathology_date) {
        this.pathology_date = pathology_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.pathology_id);
        dest.writeString(this.pathology_start_date);
        dest.writeString(this.pathology_date);
    }

    public Pathology() {
    }

    protected Pathology(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.pathology_id = in.readString();
        this.pathology_start_date = in.readString();
        this.pathology_date = in.readString();
    }

    public static final Parcelable.Creator<Pathology> CREATOR = new Parcelable.Creator<Pathology>() {
        @Override
        public Pathology createFromParcel(Parcel source) {
            return new Pathology(source);
        }

        @Override
        public Pathology[] newArray(int size) {
            return new Pathology[size];
        }
    };
}
