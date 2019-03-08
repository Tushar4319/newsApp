package com.androtush.newsapp.Response;

import android.os.Parcel;
import android.os.Parcelable;

public class Articles implements Parcelable
    {
        private String publishedAt;

        private String author;

        private String urlToImage;

        private String description;

        private Source source;

        private String title;

        private String url;

        private String content;

        protected Articles(Parcel in) {
            publishedAt = in.readString();
            author = in.readString();
            urlToImage = in.readString();
            description = in.readString();
            title = in.readString();
            url = in.readString();
            content = in.readString();
        }

        public static final Creator<Articles> CREATOR = new Creator<Articles>() {
            @Override
            public Articles createFromParcel(Parcel in) {
                return new Articles(in);
            }

            @Override
            public Articles[] newArray(int size) {
                return new Articles[size];
            }
        };

        public String getPublishedAt ()
        {
            return publishedAt;
        }

        public void setPublishedAt (String publishedAt)
        {
            this.publishedAt = publishedAt;
        }

        public String getAuthor ()
        {
            return author;
        }

        public void setAuthor (String author)
        {
            this.author = author;
        }

        public String getUrlToImage ()
        {
            return urlToImage;
        }

        public void setUrlToImage (String urlToImage)
        {
            this.urlToImage = urlToImage;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public Source getSource ()
        {
            return source;
        }

        public void setSource (Source source)
        {
            this.source = source;
        }

        public String getTitle ()
        {
            return title;
        }

        public void setTitle (String title)
        {
            this.title = title;
        }

        public String getUrl ()
        {
            return url;
        }

        public void setUrl (String url)
        {
            this.url = url;
        }

        public String getContent ()
        {
            return content;
        }

        public void setContent (String content)
        {
            this.content = content;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [publishedAt = "+publishedAt+", author = "+author+", urlToImage = "+urlToImage+", description = "+description+", source = "+source+", title = "+title+", url = "+url+", content = "+content+"]";
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(publishedAt);
            parcel.writeString(author);
            parcel.writeString(urlToImage);
            parcel.writeString(description);
            parcel.writeString(title);
            parcel.writeString(url);
            parcel.writeString(content);
        }

        public class Source
        {
            private String name;

            private String id;

            public String getName ()
            {
                return name;
            }

            public void setName (String name)
            {
                this.name = name;
            }

            public String getId ()
            {
                return id;
            }

            public void setId (String id)
            {
                this.id = id;
            }

            @Override
            public String toString()
            {
                return "ClassPojo [name = "+name+", id = "+id+"]";
            }
        }

}
