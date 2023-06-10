import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.Serializable;
import java.util.ArrayList;

public class MoonPhase{
    String format = "png";
    MoonStyle style;
    MoonObserver observer;
    MoonView view = new MoonView("landscape-simple", "south-up");

    public MoonPhase(MoonStyle style, MoonObserver observer) {
        this.style = style;
        this.observer = observer;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public MoonStyle getStyle() {
        return style;
    }

    public void setStyle(MoonStyle style) {
        this.style = style;
    }

    public MoonObserver getObserver() {
        return observer;
    }

    public void setObserver(MoonObserver observer) {
        this.observer = observer;
    }

    public MoonView getView() {
        return view;
    }

    public void setView(MoonView view) {
        this.view = view;
    }
}
