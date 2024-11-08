package pojo;

public class Support {

    private String supportURL;
    private String supportText;

    public String getSupportURL() {
        return supportURL;
    }

    public void setSupportURL(String supportURL) {
        this.supportURL = supportURL;
    }

    public String getSupportText() {
        return supportText;
    }

    public void setSupportText(String supportText) {
        this.supportText = supportText;
    }
    @Override
    public String toString() {
        return "Support{" +
                "url='" + supportURL + '\'' +
                ", text='" + supportText + '\'' +
                '}';
    }

}
