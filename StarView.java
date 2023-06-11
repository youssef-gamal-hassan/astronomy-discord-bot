public class StarView {
    String type = "constellation";

    StarViewParams parameters;

    public StarView(String constellation) {
        this.parameters = new StarViewParams(constellation);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StarViewParams getParameters() {
        return parameters;
    }

    public void setParameters(StarViewParams parameters) {
        this.parameters = parameters;
    }
}
