public class MoonStyle {
    String moonStyle;
    String backgroundStyle;
    String backgroundColor;
    String headingColor;
    String textColor;

    public MoonStyle(String moonStyle, String backgroundStyle,String backgroundColor ,String headingColor, String textColor) {
        this.moonStyle = moonStyle;
        this.backgroundStyle = backgroundStyle;
        this.backgroundColor = backgroundColor;
        this.headingColor = headingColor;
        this.textColor = textColor;
    }

    public String getMoonStyle() {
        return moonStyle;
    }

    public void setMoonStyle(String moonStyle) {
        this.moonStyle = moonStyle;
    }

    public String getBackgroundStyle() {
        return backgroundStyle;
    }

    public void setBackgroundStyle(String backgroundStyle) {
        this.backgroundStyle = backgroundStyle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getHeadingColor() {
        return headingColor;
    }

    public void setHeadingColor(String headingColor) {
        this.headingColor = headingColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
