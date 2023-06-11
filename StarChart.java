public class StarChart {

    String style;

    StarView view;

    MoonObserver observer;

    public StarChart(String style, String constellation,MoonObserver observer) {
        this.style = style;
        this.view = new StarView(constellation);
        this.observer = observer;
    }

    public StarView getView() {
        return view;
    }

    public void setView(StarView view) {
        this.view = view;
    }

    public MoonObserver getObserver() {
        return observer;
    }

    public void setObserver(MoonObserver observer) {
        this.observer = observer;
    }
}
