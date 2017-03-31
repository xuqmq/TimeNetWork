package com.bowen.timenetwork.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class PayticketTwoInfo {

    private List<AttentionBean> attention;
    private List<MoviecomingsBean> moviecomings;

    public List<AttentionBean> getAttention() {
        return attention;
    }

    public void setAttention(List<AttentionBean> attention) {
        this.attention = attention;
    }

    public List<MoviecomingsBean> getMoviecomings() {
        return moviecomings;
    }

    public void setMoviecomings(List<MoviecomingsBean> moviecomings) {
        this.moviecomings = moviecomings;
    }

    public static class AttentionBean {
        /**
         * actor1 : 斯嘉丽·约翰逊
         * actor2 : 皮鲁·埃斯贝克
         * director : 鲁伯特·山德斯
         * id : 219801
         * image : http://img5.mtime.cn/mt/2017/03/07/093000.18906196_1280X720X2.jpg
         * isFilter : false
         * isTicket : true
         * isVideo : true
         * locationName : 美国
         * rDay : 7
         * rMonth : 4
         * rYear : 2017
         * releaseDate : 4月7日上映
         * title : 攻壳机动队
         * type : 动作 / 科幻 / 犯罪
         * videoCount : 3
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/03/06/211551.48127339.jpg","length":30,"title":"攻壳机动队 中国版预告","url":"http://vfx.mtime.cn/Video/2017/03/06/mp4/170306211533224813.mp4","videoId":64825},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/02/13/171057.76162134.jpg","length":129,"title":"攻壳机动队 中文版预告片2","url":"http://vfx.mtime.cn/Video/2017/02/13/mp4/170213170800740573.mp4","videoId":64589},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2016/11/13/235242.73440704.jpg","length":124,"title":"攻壳机动队 中文版预告片","url":"http://vfx.mtime.cn/Video/2016/11/13/mp4/161113235155560735.mp4","videoId":63309}]
         * wantedCount : 1277
         */

        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private boolean isFilter;
        private boolean isTicket;
        private boolean isVideo;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
        private int videoCount;
        private int wantedCount;
        private List<VideosBean> videos;

        public String getActor1() {
            return actor1;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isIsFilter() {
            return isFilter;
        }

        public void setIsFilter(boolean isFilter) {
            this.isFilter = isFilter;
        }

        public boolean isIsTicket() {
            return isTicket;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public boolean isIsVideo() {
            return isVideo;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public int getRDay() {
            return rDay;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public int getRMonth() {
            return rMonth;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public int getRYear() {
            return rYear;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class VideosBean {
            /**
             * hightUrl :
             * image : http://img5.mtime.cn/mg/2017/03/06/211551.48127339.jpg
             * length : 30
             * title : 攻壳机动队 中国版预告
             * url : http://vfx.mtime.cn/Video/2017/03/06/mp4/170306211533224813.mp4
             * videoId : 64825
             */

            private String hightUrl;
            private String image;
            private int length;
            private String title;
            private String url;
            private int videoId;

            public String getHightUrl() {
                return hightUrl;
            }

            public void setHightUrl(String hightUrl) {
                this.hightUrl = hightUrl;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }
        }
    }

    public static class MoviecomingsBean {
        /**
         * actor1 : 范伟
         * actor2 : 贾静雯
         * director : 王啸坤
         * id : 235312
         * image : http://img5.mtime.cn/mt/2017/03/24/150413.60961284_1280X720X2.jpg
         * isFilter : false
         * isTicket : true
         * isVideo : true
         * locationName : 中国
         * rDay : 1
         * rMonth : 4
         * rYear : 2017
         * releaseDate : 4月1日上映
         * title : 有完没完
         * type : 喜剧
         * videoCount : 3
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2016/12/01/180642.47863811.jpg","length":96,"title":"有完没完 先导预告","url":"http://vfx.mtime.cn/Video/2016/12/01/mp4/161201180604968347.mp4","videoId":63590},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/02/24/103942.33973843.jpg","length":101,"title":"有完没完 病毒视频","url":"http://vfx.mtime.cn/Video/2017/02/24/mp4/170224103852776809.mp4","videoId":64708},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/03/02/150933.83707353.jpg","length":63,"title":"有完没完 出不去的愚人节版预告","url":"http://vfx.mtime.cn/Video/2017/03/02/mp4/170302150852384300.mp4","videoId":64784}]
         * wantedCount : 1778
         */

        private String actor1;
        private String actor2;
        private String director;
        private int id;
        private String image;
        private boolean isFilter;
        private boolean isTicket;
        private boolean isVideo;
        private String locationName;
        private int rDay;
        private int rMonth;
        private int rYear;
        private String releaseDate;
        private String title;
        private String type;
        private int videoCount;
        private int wantedCount;
        private List<VideosBeanX> videos;

        public String getActor1() {
            return actor1;
        }

        public void setActor1(String actor1) {
            this.actor1 = actor1;
        }

        public String getActor2() {
            return actor2;
        }

        public void setActor2(String actor2) {
            this.actor2 = actor2;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isIsFilter() {
            return isFilter;
        }

        public void setIsFilter(boolean isFilter) {
            this.isFilter = isFilter;
        }

        public boolean isIsTicket() {
            return isTicket;
        }

        public void setIsTicket(boolean isTicket) {
            this.isTicket = isTicket;
        }

        public boolean isIsVideo() {
            return isVideo;
        }

        public void setIsVideo(boolean isVideo) {
            this.isVideo = isVideo;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public int getRDay() {
            return rDay;
        }

        public void setRDay(int rDay) {
            this.rDay = rDay;
        }

        public int getRMonth() {
            return rMonth;
        }

        public void setRMonth(int rMonth) {
            this.rMonth = rMonth;
        }

        public int getRYear() {
            return rYear;
        }

        public void setRYear(int rYear) {
            this.rYear = rYear;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public int getWantedCount() {
            return wantedCount;
        }

        public void setWantedCount(int wantedCount) {
            this.wantedCount = wantedCount;
        }

        public List<VideosBeanX> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBeanX> videos) {
            this.videos = videos;
        }

        public static class VideosBeanX {
            /**
             * hightUrl :
             * image : http://img5.mtime.cn/mg/2016/12/01/180642.47863811.jpg
             * length : 96
             * title : 有完没完 先导预告
             * url : http://vfx.mtime.cn/Video/2016/12/01/mp4/161201180604968347.mp4
             * videoId : 63590
             */

            private String hightUrl;
            private String image;
            private int length;
            private String title;
            private String url;
            private int videoId;

            public String getHightUrl() {
                return hightUrl;
            }

            public void setHightUrl(String hightUrl) {
                this.hightUrl = hightUrl;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }
        }
    }
}
