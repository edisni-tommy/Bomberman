package UI;

public abstract class ScenceGraph {
    private boolean isDisplayed = true;

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public abstract void display();

    public abstract void remove();

    public abstract void update(float tpf);
}
